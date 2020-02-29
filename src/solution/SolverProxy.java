package solution;

/* Generic Proxy, checks for Solution(S) in cache and returns it,
otherwise - sends the problem to the solver */

import cache.CacheManager;
import problem.ProblemCreator;

import java.util.Vector;

public class SolverProxy<P> implements Solver< Vector<String>, String > {
    private Solver<P,String> solver;
    private CacheManager cacheManager;
    private ProblemCreator<P> pc;

    public SolverProxy(CacheManager c, Solver<P,String> s, ProblemCreator<P> p) {
        this.solver = s;
        this.cacheManager = c;
        this.pc = p;
    }


    @Override
    public String solve(Vector<String> problem) {
        System.out.println("in the Solver proxy.. "); //Remove
        String solution;
        if (cacheManager.isExist(problem)) {
            System.out.println("Found Cached result..."); //Remove
            return cacheManager.load(problem);
        }
        System.out.println("Did not find cached result, start solving.. "); //Remove
        //SAVE TO CACHE
        solution = this.solver.solve(this.pc.create(problem)); //SAVE TO CACHE
        this.cacheManager.save(problem,solution);
        return solution;

    }
}
