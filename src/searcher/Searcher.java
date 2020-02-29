package searcher;

import algorithms.Node;
import searchable.Searchable;
import searchable.State;


import java.util.HashSet;
import java.util.Vector;

    public abstract class Searcher {

    protected Searchable searchable;
    private HashSet<Node> closedList = new HashSet<>();

    abstract public Vector<State> search();

    abstract void visit(State next, Node current);

    private boolean InClosedList(State s) {
        for (Node closeNode : closedList) {
            if (closeNode.getState().isEqual(s)) {
                return true;
            }
        }
        return false;
    }

    public void setSearchable(Searchable data) {
        this.searchable = data;
    }

    Vector<State> baseSearch(DataStructure openList, CostStart costStart) {
        State start = this.searchable.getStartState();
        Node startNode = new Node(start, costStart.calculateCost(start), null);
        openList.add(startNode);
        do {
            Node current = openList.pull();
            if (searchable.isGoalState(current.getState())) {
                return current.solution();
            }
            this.closedList.add(current);
            Vector<State> neighbors = this.searchable.getAllPossibleStates(current.getState());
            for (State adj : neighbors) {
                if (!InClosedList(adj)) {
                    visit(adj, current);
                }
            }
        } while (openList.size() > 0);
        return null;
    }
}

