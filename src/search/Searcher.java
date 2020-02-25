package search;

import algorithms.Node;
import searchable.Searchable;
import searchable.State;


import java.util.HashSet;
import java.util.Vector;

public abstract class Searcher {

    protected Searchable searchable;
    protected HashSet<Node> closeList = new HashSet<Node>();

    abstract Vector<State> search();

    abstract void visit(State next, Node current);

    private boolean isInTheCloseList(State s) {
        for (Node closeNode : closeList) {
            if (closeNode.getCurrentState().isEqual(s)) {
                return true;
            }
        }
        return false;
    }

    public void setSearchable(Searchable data) {
        this.searchable = data;
    }

    protected Vector<State> baseSearch(DataStructure openList, CostStart costStart) {
        State start = this.searchable.getStartState();
        Node startNode = new Node(start, costStart.calculateCost(start), null);
        openList.add(startNode);
        do {
            Node current = openList.pull();
            if (searchable.isGoalState(current.getCurrentState())) {
                return current.solution();
            }
            this.closeList.add(current);
            Vector<State> neighbors = this.searchable.getAllPossibleState(current.getCurrentState());
            for (State adj : neighbors) {
                if (!isInTheCloseList(adj)) {
                    visit(adj, current);
                }
            }
        } while (openList.size() > 0);
        return null;
    }

}

