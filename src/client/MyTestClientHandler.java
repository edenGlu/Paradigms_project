package client;

import cashe.CacheManager;
import solution.Solver;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class MyTestClientHandler implements ClientHandler {

    private Solver<String, String> _solver;
    private CacheManager _cacheManager;

    public MyTestClientHandler(Solver<String, String> solver, CacheManager cacheManager) {
        this._solver = solver;
        this._cacheManager = cacheManager;
    }

    @Override
    public void handleClient(Socket connSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(connSocket.getOutputStream());

            System.out.println("Wait for message");
            String checkLine = in.readLine();
            System.out.println("The client has sent: " + checkLine);

            String checkCapitalized = checkLine.toUpperCase() + '\n';
            System.out.println("Sending to client: " + checkCapitalized);
            out.writeBytes(checkCapitalized);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readAndAnsFromClientUntilEnd(String line, DataInputStream in, DataOutputStream out) {
        while (!line.equals("end")) {
            try {
                sendSolution(line, out);
                line = in.readUTF();
                System.out.println("get p: " + line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Closing connection");
    }

    private void sendSolution(String problem, DataOutputStream out) {
        Vector<String> problem_vec = new Vector<>();
        problem_vec.add(problem);
        String solution;
        if (_cacheManager.isExist(problem_vec)) {
            solution = _cacheManager.load(problem_vec);
        } else {
            solution = _solver.solve(problem);
        }
        try {
            System.out.println("send s: " + solution);
            out.writeBytes(solution);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
