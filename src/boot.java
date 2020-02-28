import cache.CacheManager;
import cache.FileCacheManager;
import client.IClientHandler;
import client.ClientHandler;
import searchable.Matrix;
import searchable.Searchable;
import searchable.State;
import searcher.*;
import server.MySerialServer;
import server.Server;
import solution.SearchSolver;
import solution.Solver;

import java.util.Vector;
import java.util.logging.Logger;

public class boot {
    private static Logger simpleLoggerExample() {
        /* nice feature for ClientHandler. An object which records the handler's operations,
        * like if was some Exception, or if some query has not been resolved.*/
        return Logger.getLogger("some_name");

    }

    public static void main(String[] args) {
        String cachePath = "C:\\Users\\Eden\\IdeaProjects\\Paradigms_project\\src\\cache_file.txt";
        Logger logger = boot.simpleLoggerExample();

        Solver<Searchable, Vector<State>> solver = new SearchSolver(new DFS());
        CacheManager cacheManager = new FileCacheManager(cachePath);

        IClientHandler clientHandler = new ClientHandler.CHBuilder<>(solver, cacheManager, Matrix::new)
                .setUnsolvedMsg("override the Default message !")
                .setLogger(logger)
                .build();

        Server server = new MySerialServer();
        server.open(12359, clientHandler);

    }
}
