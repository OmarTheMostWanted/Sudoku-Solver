package main;

public class Pair<T, T1> {
    private T r;
    private T c;

    public Pair(T r, T c) {
        this.r = r;
        this.c = c;
    }

    public T getRow() {
        return this.r;
    }

    public T getColumn() {
        return this.c;
    }
}
