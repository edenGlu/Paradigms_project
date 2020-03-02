package solution;

import searchable.Searchable;
import searchAlgorithms.Searcher;


public class SearchSolver implements Solver<Searchable, String> {
    private Searcher searcher;

    public SearchSolver(Searcher s){
        this.searcher = s;
    }

    @Override
    public String solve(Searchable problem) {

        System.out.println("in the Search Solver.. ");
        searcher.setSearchable(problem);
        return searcher.search().toString();
    }
}
