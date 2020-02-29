package problem;

import java.util.Vector;

// Given for the ClientHandler if we want to use Proxy Solver
public class ProxyProblemCreator implements ProblemCreator<Vector<String>>{
    @Override
    public Vector<String> create(Vector<String> asString) {
        return asString;
    }
}