package searchAlgorithms;

import searchable.State;

import java.util.Vector;

public class Node {
    private State state;
    private double minPath;
    private Node parent;

    public Node(State s, double cost, Node p) {
        this.state = s;
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

    public State getState() {
        return state;
    }

    public Vector<State> solution() {
        Vector<State> path;
        if (this.parent != null)
            path = this.parent.solution();
        else
            path = new Vector<State>();

        path.add(this.state);
        return path;
    }

    // consider override '.equals()' method instead
    public boolean isEqual(Node other) {
        return this.state.isEqual(other.state);
    }

}
