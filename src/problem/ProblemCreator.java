package problem;

import java.util.Vector;

public interface ProblemCreator<P> {
    P create(Vector<String> asString);
}
