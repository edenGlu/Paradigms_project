package searchAlgorithms;

import searchable.State;
import utils.Pair;

import java.util.PriorityQueue;
import java.util.Vector;

public class FactoryAStar implements FactorySearcher{
    @Override
    public Searcher createSearcher() {
        return new Astar();
    }

    private class Astar extends Searcher {
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
            }, this::heuristicFunction);
        }

        @Override
        void visit(State next, Node current) {
            Node nextNode = InOpenList(next);
            if (nextNode == null) {
                this.openList.add(new Node(next, costFunction(next, current), current));
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
            double newPath = costFunction(next.getState(), current);
            if (newPath < next.getMinPath()) {
                next.setParent(current);
                next.setMinPath(newPath);
            }
        }

        private double costFunction(State next, Node current) {
            return current.getMinPath() - heuristicFunction(current.getState()) + heuristicFunction(next);
        }

        private double heuristicFunction(State s) {
            Pair<Integer, Integer> goalLocation = this.searchable.getGoalState().getLocation();
            Pair<Integer, Integer> sLocation = s.getLocation();
            return calculateDistance(goalLocation, sLocation);
        }

        private double calculateDistance(Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
            return Math.sqrt(Math.pow(x.first() - y.first(), 2) +
                    Math.pow(x.second() - y.second(), 2));
        }

    }
}
