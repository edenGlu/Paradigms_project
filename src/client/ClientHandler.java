package client;

import java.net.Socket;

public interface ClientHandler {
    void handleClient(Socket new_socket_client);
}
