package searchAlgorithms;

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
        }, State::getCost);
    }

    @Override
    void visit(State next, Node current) {
        Node nextNode = InOpenList(next);
        if (nextNode == null) {
            this.openList.add(new Node(next, current.getMinPath() + next.getCost(), current));
        } else {
            comparePath(nextNode, current);
        }
    }

    private Node InOpenList(State s) {
        for (Node openNode : openList) {
            if (openNode.getState().isEqual(s)) {
                return openNode;
            }
        }
        return null;
    }

    private void comparePath(Node next, Node current) {
        double newPath = current.getMinPath() + next.getState().getCost();
        if (newPath < next.getMinPath()) {
            next.setParent(current);
            next.setMinPath(newPath);
        }
    }
}
