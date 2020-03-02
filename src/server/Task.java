package server;

import client.IClientHandler;

import java.net.Socket;

/* Class which representing task to be executed. */
class Task implements Runnable {
    private IClientHandler _clientHandler;
    private Socket _clientSocket;

    public Task(IClientHandler clientHandler, Socket clientSocket) {
        this._clientHandler = clientHandler;
        this._clientSocket = clientSocket;
    }

    public void run() {
        _clientHandler.handleClient(_clientSocket);
    }
}
