package searchable;

import java.util.Vector;

public interface Searchable {

    void create(Vector<String> problem);

    boolean isGoalState(State state);

    State getStartState();

    Vector<State> getAllPossibleState(State state);
}
