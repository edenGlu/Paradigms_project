import algorithms.Node;
import cashe.FileCacheManager;
import client.MyTestClientHandler;
import search.BFS;
import search.BestFS;
import search.DFS;
import searchable.State;
import server.MySerialServer;
import solution.StringReverser;
import searchable.Matrix;

import java.util.Vector;

public class boot {

    public static void main(String[] args) {
        /*MySerialServer server = new MySerialServer();
        server.open(12359,
                new MyTestClientHandler(new StringReverser(), new FileCacheManager("cache_file")));
*/
        Matrix m = new Matrix();
        Vector<String> data = new Vector<>();
        data.add("1,2,3");
        data.add("4,5,6");
        data.add("7,8,9");
        data.add("0,0");
        data.add("2,2");
        m.create(data);
        BFS bfs = new BFS();
        bfs.setSearchable(m);
        Vector<State> sol = bfs.search();
        BestFS bestFS = new BestFS();
        bestFS.setSearchable(m);
        Vector<State> sol2 = bestFS.search();
        DFS dfs = new DFS();
        dfs.setSearchable(m);
        Vector<State> sol3 = dfs.search();
        System.out.println("good");
    }
}
