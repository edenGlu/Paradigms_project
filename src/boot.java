import cache.CacheManager;
import cache.FileCacheManager;
import client.IClientHandler;
import client.ClientHandler;
import problem.ProxyProblemCreator;
import searchAlgorithms.BFS;
import searchable.Matrix;
import searchable.Searchable;
import server.MyParallelServer;
import server.Server;
import solution.SearchSolver;
import solution.Solver;
import solution.SolverProxy;

import java.util.Vector;
import java.util.logging.Logger;

public class boot {
    private static Logger simpleLoggerExample() {
        return Logger.getLogger("my_logger");

    }

    public static void main(String[] args) {
        String cachePath = "C:\\Users\\Eden\\IdeaProjects\\Paradigms_project\\src\\cache_file.txt";
        Logger logger = boot.simpleLoggerExample();

        Solver<Searchable, String> searchSolver = new SearchSolver(new BFS());
        CacheManager cm = new FileCacheManager(cachePath);

        Solver<Vector<String>, String> proxySolver = new SolverProxy<>(cm,searchSolver,Matrix::new);

        IClientHandler clientHandler = new ClientHandler.CHBuilder<>(proxySolver, new ProxyProblemCreator())
                .setUnsolvedMsg("Some new Default message !!!!")
                .setLogger(logger)
                .build();

        Server server = new MyParallelServer();
        server.open(12359, clientHandler);
    }
}
