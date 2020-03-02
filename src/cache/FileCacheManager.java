package cache;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FileCacheManager implements CacheManager {
    private Map<Vector<String>, String> _oldSolution = new HashMap<>();
    private String _fileCache;

    public FileCacheManager(String fileName) {
        _fileCache = fileName;
        loadFromFile();
    }

    @Override
    public void save(Vector<String> problem, String solution) {
        _oldSolution.put(problem, solution);
    }

    @Override
    public String load(Vector<String> problem) {
        return _oldSolution.get(problem);
    }

    @Override
    public boolean isExist(Vector<String> problem) {
        return _oldSolution.containsKey(problem);
    }

    private void loadFromFile() {
        File file = new File(_fileCache);
        if (file.exists()) {
            createMapCache(file);
        }
    }

    private void createMapCache(File file) {
        Vector<String> problem = new Vector<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("$")) {
                    line = br.readLine();
                    _oldSolution.put(problem, line);
                    problem = new Vector<>();
                } else {
                    problem.add(line);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Exception in createMapCache : " + e.getMessage());
        }
    }

    public void writeToFile() {
        File file = new File(_fileCache);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutput = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutput));
            saveMapToFile(bw);
            bw.close();
        } catch (Exception e) {
            System.out.println("Exception saveAllInFile: " + e.getMessage());
        }
    }

    private void saveMapToFile(BufferedWriter bw) {
        for (Vector<String> key : _oldSolution.keySet()) {
            for (String line : key) {
                try {
                    bw.write(line + "\n");
                    bw.write("$" + "\n");
                    bw.write(_oldSolution.get(key) + "\n");
                } catch (Exception e) {
                    System.out.println("Exception saveMapToFile : " + e.getMessage());
                }
            }
        }
    }
}
