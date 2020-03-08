package solution;

import searchAlgorithms.FactorySearcher;
import searchable.Searchable;
import searchAlgorithms.Searcher;


public class SearchSolver implements Solver<Searchable, String> {
    private FactorySearcher factorySearcher;

    public SearchSolver(FactorySearcher fs) {
        this.factorySearcher = fs;
    }

    @Override
    public String solve(Searchable problem) {
        Searcher searcher = factorySearcher.createSearcher();
        System.out.println("in the Search Solver.. ");
        searcher.setSearchable(problem);
        return searcher.search().toString();
    }
}
