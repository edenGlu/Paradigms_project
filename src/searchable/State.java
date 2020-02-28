package searchable;

import utils.Pair;


public class State {

    private Pair<Integer, Integer> location;
    private double cost;

    public State(Pair<Integer, Integer> l, double c) {
        this.location = l;
        this.cost = c;
    }

    public double getCost() {
        return cost;
    }

    public Pair<Integer, Integer> getLocation() {
        return location;
    }

    public boolean isEqual(State other) {
        return this.location.isEqual(other.location);
    }

    @Override
    public String toString() {
        return this.location.toString();
    }
}
