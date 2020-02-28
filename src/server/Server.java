package server;

import client.IClientHandler;

public interface Server {
    void open(int port, IClientHandler clientHandler);

    void stop();
}
