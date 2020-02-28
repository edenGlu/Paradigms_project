package searcher;

import algorithms.Node;
import searchable.State;

import java.util.Stack;
import java.util.Vector;

public class DFS extends Searcher {
    private Stack<Node> openList = new Stack<>();

    @Override
    public Vector<State> search() {
        return baseSearch(new DataStructure() {
            @Override
            public Node pull() {
                return openList.pop();
            }

            @Override
            public boolean add(Node var1) {
                openList.push(var1);
                return true;
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
