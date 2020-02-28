package searcher;

import algorithms.Node;
import searchable.State;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class BFS extends Searcher {
    private Queue<Node> openList = new LinkedList<>();

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
        if (!InOpenList(next)) {
            this.openList.add(new Node(next, current.getMinPath() + next.getCost(), current));
        }
    }


    private boolean InOpenList(State s) {
        for (Node openNode : openList) {
            if (openNode.getState().isEqual(s)) {
                return true;
            }
        }
        return false;
    }

}
