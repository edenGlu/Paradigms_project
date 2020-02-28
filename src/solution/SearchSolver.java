package solution;

import searchable.Searchable;
import searchable.State;
import searcher.Searcher;

import java.util.Vector;

public class SearchSolver implements Solver<Searchable, Vector<State>> {
    private Searcher searcher;

    public SearchSolver(Searcher s){
        this.searcher = s;
    }

    @Override
    public Vector<State> solve(Searchable problem) {
        searcher.setSearchable(problem);
        return searcher.search();
    }
}
