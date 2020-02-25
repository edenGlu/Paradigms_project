package search;

import algorithms.Node;
import searchable.State;

import java.util.PriorityQueue;
import java.util.Vector;

public class BestFS extends Searcher {
    private PriorityQueue<Node> openList = new PriorityQueue<>((Node node, Node other) ->
            node.getMinPath() > other.getMinPath() ? 1 : -1);


    @Override
    Vector<State> search() {
        return null;
    }

    @Override
    void visit(State next, Node current) {

    }
}
