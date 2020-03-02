package problem;

import java.util.Vector;


public class ProxyProblemCreator implements ProblemCreator<Vector<String>>{
    @Override
    public Vector<String> create(Vector<String> asString) {
        return asString;
    }
}