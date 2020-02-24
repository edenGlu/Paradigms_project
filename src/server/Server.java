package server;

import client.ClientHandler;

public interface Server {
    void open(int port, ClientHandler clientHandler);

    void stop();
}
