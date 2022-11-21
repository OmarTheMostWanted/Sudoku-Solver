package Solver;

public abstract class AbstractSudokuBoard {
    public abstract int[][] getBoard();
    public abstract boolean isComplete();
    public abstract void scanInstance(String name);
    public abstract int getValue(int row , int column);
    public abstract int getValue(int index);
    public abstract int getValueFromCell(int cell, int index);
    public abstract void OneStep();
    public abstract void Solve();
    public abstract String toString();
}
