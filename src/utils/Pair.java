package utils;

public class Pair<I, J> {
    private I first;
    private J second;

    public Pair(I f, J s) {
        first = f;
        second = s;
    }

    public I First() {
        return first;
    }

    public J Second() {
        return second;
    }

    public boolean isEqual(Pair<I, J> other) {
        return first.equals(other.First()) &&
                second.equals(other.Second());
    }
}
