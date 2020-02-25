import cashe.FileCacheManager;
import client.MyTestClientHandler;
import searchable.State;
import server.MySerialServer;
import solution.StringReverser;
import searchable.Matrix;

import java.util.Vector;

public class boot {

    public static void main(String[] args) {
        /*MySerialServer server = new MySerialServer();
        server.open(12359,
                new MyTestClientHandler(new StringReverser(), new FileCacheManager("cache_file")));
*/
        Matrix m = new Matrix();
        Vector<String> data = new Vector<>();
        data.add("1,2,3");
        data.add("4,5,6");
        data.add("7,8,9");
        data.add("0,0");
        data.add("2,2");
        m.create(data);
        Vector<State> states = m.getAllPossibleState(m.getStartState());
        System.out.println(states);
    }
}
