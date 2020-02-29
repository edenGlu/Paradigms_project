package solution;

import searchable.Searchable;
import searchable.State;
import searchAlgorithms.Searcher;

import java.util.Vector;

public class SearchSolver implements Solver<Searchable, String> {
    private Searcher searcher;

    public SearchSolver(Searcher s){
        this.searcher = s;
    }

    @Override
    public String solve(Searchable problem) {
        System.out.println("in the Search Solver.. "); //Remove
        searcher.setSearchable(problem);
        return searcher.search().toString();
    }
}
