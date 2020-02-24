package server;

import java.net.ServerSocket;
import java.net.Socket;
import client.ClientHandler;

public class MySerialServer implements Server {
    private MyRunnableServer _runnableServer;

    @Override
    public void open(int port, ClientHandler clientHandler) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            this._runnableServer = new MyRunnableServer(clientHandler, serverSocket);
            Thread thread = new Thread(_runnableServer);
            thread.start();
            thread.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void stop() {
        this._runnableServer.doStop();
    }


    private class MyRunnableServer implements Runnable {
        private final int TIMEOUT = 60000; // timeout after 60 seconds
        private boolean _doStop = false;
        private ClientHandler _clientHandler;
        private ServerSocket _serverSocket;

        private MyRunnableServer(ClientHandler clientHandler, ServerSocket serverSocket) {
            this._clientHandler = clientHandler;
            this._serverSocket = serverSocket;
        }


        private synchronized void doStop() {
            this._doStop = true;
        }

        private synchronized boolean keepRunning() {
            return this._doStop == false;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                try {
                    _serverSocket.setSoTimeout(TIMEOUT);
                    Socket clientSocket = _serverSocket.accept(); // maybe need putאtimeout
                    System.out.println("get a client");
                    _clientHandler.handleClient(clientSocket);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
