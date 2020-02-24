import cashe.FileCacheManager;
import client.MyTestClientHandler;
import server.MySerialServer;
import solution.StringReverser;

public class boot {

    public static void main(String[] args) {
        MySerialServer server = new MySerialServer();
        server.open(5004,
                new MyTestClientHandler(new StringReverser(), new FileCacheManager("cache")));

    }
}
