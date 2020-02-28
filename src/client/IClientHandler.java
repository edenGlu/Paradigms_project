package client;

import java.net.Socket;

public interface IClientHandler {
    void handleClient(Socket clientSocket);
}
