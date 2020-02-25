package searchable;

import utils.Pair;

import java.util.Vector;

public class Matrix implements Searchable {
    private Vector<Vector<State>> matrixStates;
    private State startState;
    private State goalState;

    @Override
    public void create(Vector<String> problem) {
        int len = problem.size() - 2;
        matrixStates = new Vector<>();
        for (int i = 0; i < len; i++) {
            matrixStates.add(makeRow(problem.get(i), i));
        }
        markerStartAndGoal(problem.get(len).split(","), problem.get(len + 1).split(","));
    }

    private void markerStartAndGoal(String[] start, String[] goal) {
        this.startState = this.matrixStates.get(Integer.parseInt(start[0]))
                .get(Integer.parseInt(start[1]));
        this.goalState = this.matrixStates.get(Integer.parseInt(goal[0]))
                .get(Integer.parseInt(goal[1]));
    }

    private Vector<State> makeRow(String dataRow, int numRow) {
        Vector<State> rowStates = new Vector<>();
        String[] elements = dataRow.split(",");
        for (int i = 0; i < elements.length; i++) {
            rowStates.add(new State(new Pair<>(numRow, i), Integer.parseInt(elements[i])));
        }
        return rowStates;
    }


    @Override
    public boolean isGoalState(State state) {
        return this.goalState.isEqual(state);
    }

    @Override
    public State getStartState() {
        return this.startState;
    }

    @Override
    public Vector<State> getAllPossibleState(State state) {
        Vector<State> possibleState = new Vector<>();
        Vector<Pair<Integer, Integer>> neighbors = makeNeighbors(state.getLocation());
        for (Pair<Integer, Integer> pair : neighbors) {
            if ((pair.First() >=0 && pair.First() <= this.matrixStates.size()) &&
            (pair.Second() >=0 && pair.Second() <= this.matrixStates.size())){
                possibleState.add(this.matrixStates.get(pair.First()).get(pair.Second()));
            }
        }
        return possibleState;
    }

    private Vector<Pair<Integer, Integer>> makeNeighbors(Pair<Integer, Integer> location) {
        Vector<Pair<Integer, Integer>> neighbors = new Vector<>();
        neighbors.add(new Pair<>(location.First() - 1, location.Second()));
        neighbors.add(new Pair<>(location.First(), location.Second() - 1));
        neighbors.add(new Pair<>(location.First() + 1, location.Second()));
        neighbors.add(new Pair<>(location.First(), location.Second() + 1));
        return neighbors;
    }
}
