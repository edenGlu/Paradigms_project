package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;

public class SimpleClient {
    /*
    * Simple program for you to check the server when necessary.
    */
    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket("localhost", 12359);

            DataOutputStream outToServer = new DataOutputStream(serverSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            Vector<String> problem = new Vector<>();
            problem.add("1,2,3");
            problem.add("4,5,6");
            problem.add("7,8,9");
            problem.add("0,0");
            problem.add("2,2");
            problem.add("end");

            for(var line : problem)
                outToServer.writeBytes(line + '\n');

            String solution = inFromServer.readLine();
            System.out.println("The problem that been asked:\n" + problem +
                             "\nThe answer given by the Server:\n" + solution);


            outToServer.close();
            inFromServer.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
