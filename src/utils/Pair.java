package utils;

public class Pair<I, J> {
    private I _first;
    private J _second;

    public Pair(I f, J s) {
        _first = f;
        _second = s;
    }

    public I first() {
        return _first;
    }

    public J second() {
        return _second;
    }

    public boolean isEqual(Pair<I, J> other) {
        return _first.equals(other.first()) &&
                _second.equals(other.second());
    }

    @Override
    public String toString() {
        return "(" + _first.toString() + "," + _second.toString() + ")";
    }
}
