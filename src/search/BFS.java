package search;

import algorithms.Node;
import searchable.State;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class BFS extends Search {
    private Queue<Node> openList = new LinkedList<Node>();

    @Override
    public Vector<State> search() {
        State start = this.dataStructure.getStartState();
        Node startNode = new Node(start, start.getCost(), null);
        openList.add(startNode);
        do {
            Node current = openList.poll();
            if (dataStructure.isGoalState(current.getCurrentState())) {
                return current.solution();
            }
            this.closeList.add(current);
            Vector<State> neighbors = this.dataStructure.getAllPossibleState(current.getCurrentState());
            for (State adj : neighbors) {
                if (!isInTheCloseList(adj) && !isInTheOpenList(adj)) {
                    this.openList.add(new Node(adj, current.getMinPath() + adj.getCost(), current));
                }
            }
        } while (openList.size() > 0);
        return null; // no path
    }


    private boolean isInTheOpenList(State s) {
        for (Node openNode : openList) {
            if (openNode.getCurrentState().isEqual(s)) {
                return true;
            }
        }
        return false;
    }

    private void comperePath(Node next, Node current) {
        double currentPath = current.getMinPath() + next.getCurrentState().getCost();
        if (next.getMinPath() > currentPath) {
            next.setMinPath(currentPath);
            next.setParent(current);
        }
    }
}
