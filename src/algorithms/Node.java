package algorithms;

import searchable.State;

import java.util.Vector;

public class Node {
    private State currentState;
    private double minPath;
    private Node parent;

    public Node(State s, double cost, Node p) {
        this.currentState = s;
        this.minPath = cost;
        this.parent = p;
    }

    public void setMinPath(double minPath) {
        this.minPath = minPath;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getMinPath() {
        return minPath;
    }

    public Node getParent() {
        return parent;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Vector<State> solution() {
        Vector<State> path;
        if (this.parent != null) {
            path = this.parent.solution();
        } else {
            path = new Vector<State>();
        }
        path.add(this.currentState);
        return path;
    }

    public boolean isEqual(Node other) {
        return this.currentState.isEqual(other.currentState);
    }

}
