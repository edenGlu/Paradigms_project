import cache.CacheManager;
import cache.FileCacheManager;
import client.IClientHandler;
import client.ClientHandler;
import problem.ProxyProblemCreator;
import searchAlgorithms.BFS;
import searchable.Matrix;
import searchable.Searchable;
import server.MySerialServer;
import server.Server;
import solution.SearchSolver;
import solution.Solver;
import solution.SolverProxy;

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

        Solver<Searchable, String> seachSolver = new SearchSolver(new BFS());
        CacheManager cm = new FileCacheManager(cachePath);

        Solver<Vector<String>, String> proxySolver = new SolverProxy<>(cm,seachSolver,Matrix::new);

        IClientHandler clientHandler = new ClientHandler.CHBuilder<>(proxySolver, new ProxyProblemCreator())
                .setUnsolvedMsg("override the Default message !")
                .setLogger(logger)
                .build();

        Server server = new MySerialServer();
        server.open(12359, clientHandler);

//        Solver<String,String> so = new StringReverser();
//        Solver<Searchable, Vector<State>> solver = new SearchSolver(new DFS());
//        CacheManager cacheManager = new FileCacheManager(cachePath);
//
//        ProblemCreator<Vector<String>> ppc = new ProxyProblemCreator(); //For CH if using Proxy
//        Solver<Searchable, Vector<State>> s = new SolverProxy(solver, cacheManager)
//        IClientHandler clientHandler = new ClientHandler.CHBuilder<>(solver, Matrix::new)
//                .setUnsolvedMsg("override the Default message !")
//                .setLogger(logger)
//                .build();
//
//        Server server = new MySerialServer();
//        server.open(12359, clientHandler);

    }
}
