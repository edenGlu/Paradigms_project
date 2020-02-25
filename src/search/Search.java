package search;

import algorithms.Node;
import searchable.Searchable;
import searchable.State;


import java.util.HashSet;
import java.util.Vector;

public abstract class Search {

    protected Searchable dataStructure;
    protected HashSet<Node> closeList = new HashSet<Node>();

    abstract Vector<State> search();

    protected boolean isInTheCloseList(State s) {
        for (Node closeNode : closeList) {
            if (closeNode.getCurrentState().isEqual(s)) {
                return true;
            }
        }
        return false;
    }

    public void setSearchable(Searchable data) {
        this.dataStructure = data;
    }

}

