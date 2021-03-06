package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import client.IClientHandler;

public class MySerialServer implements Server {
    private MyRunnableServer _runnableServer;

    @Override
    public void open(int port, IClientHandler clientHandler) {
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
        private final int TIMEOUT = 60000; // 60 seconds timeout.
        private boolean _doStop = false;
        private IClientHandler _clientHandler;
        private ServerSocket _serverSocket;

        private MyRunnableServer(IClientHandler clientHandler, ServerSocket serverSocket) {
            this._clientHandler = clientHandler;
            this._serverSocket = serverSocket;
        }


        private synchronized void doStop() {
            this._doStop = true;
        }

        private synchronized boolean keepRunning() {
            return !this._doStop;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                try {
                    _serverSocket.setSoTimeout(TIMEOUT);
                    Socket clientSocket = _serverSocket.accept();
                    System.out.println("Got client");
                    _clientHandler.handleClient(clientSocket);
                } catch (SocketTimeoutException s) {
                    System.out.println("Socket time out: " + s.getMessage());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
