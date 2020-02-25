package search;

import algorithms.Node;
import searchable.State;

import java.util.PriorityQueue;
import java.util.Vector;

public class BestFS extends Searcher {
    private PriorityQueue<Node> openList = new PriorityQueue<>((Node node, Node other) ->
            node.getMinPath() > other.getMinPath() ? 1 : -1);


    @Override
    public Vector<State> search() {
        return baseSearch(new DataStructure() {
            @Override
            public Node pull() {
                return openList.poll();
            }

            @Override
            public boolean add(Node var1) {
                return openList.add(var1);
            }

            @Override
            public int size() {
                return openList.size();
            }
        }, (State s) -> s.getCost());
    }

    @Override
    void visit(State next, Node current) {
        Node nextNode = isInTheOpenList(next);
        if (nextNode == null) {
            this.openList.add(new Node(next, current.getMinPath() + next.getCost(), current));
        } else {
            comperePath(nextNode, current);
        }
    }

    private Node isInTheOpenList(State s) {
        for (Node openNode : openList) {
            if (openNode.getCurrentState().isEqual(s)) {
                return openNode;
            }
        }
        return null;
    }

    private void comperePath(Node next, Node current) {
        double newPath = current.getMinPath() + next.getCurrentState().getCost();
        if (newPath < next.getMinPath()) {
            next.setParent(current);
            next.setMinPath(newPath);
        }
    }

}
