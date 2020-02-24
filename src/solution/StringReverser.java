package solution;

public class StringReverser implements Solver<String,String> {
    @Override
    public String solve(String problem) {
        String solution = new StringBuilder(problem).reverse().toString();
        return solution;
    }
}
