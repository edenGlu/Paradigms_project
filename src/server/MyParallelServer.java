package server;

import client.IClientHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyParallelServer implements Server {

    private boolean _shouldStop = false;
    private final int TIMEOUT = 60000;
    private IClientHandler _clientHandler;
    private ServerSocket _serverSocket;
    static final int MAX_T = 3;

    @Override
    public void open(int port, IClientHandler clientHandler) {
        this._clientHandler = clientHandler;
        try {
            this._serverSocket = new ServerSocket(port);
            acceptClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        _shouldStop = true;
    }

    private void acceptClients() {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        while (!_shouldStop) {
            try {
                _serverSocket.setSoTimeout(TIMEOUT);

                Socket clientSocket = _serverSocket.accept();
                System.out.println("Client accepted !");

                pool.execute(new Task(_clientHandler, clientSocket));

            } catch (SocketTimeoutException s) {
                System.err.println("Socket time out: " + s.getMessage());
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }
}
