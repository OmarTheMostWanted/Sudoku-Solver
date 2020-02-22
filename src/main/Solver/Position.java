package Solver;

public class Position {
    private int row;
    private int column;
    private int value;

    public Position(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Position at row: ").append(row).append(", column: ").append(column).append(" value: ").append(value);

        return stringBuilder.toString();
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
