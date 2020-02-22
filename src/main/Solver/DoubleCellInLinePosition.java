package Solver;

import java.util.Objects;

public class DoubleCellInLinePosition {

    private int rowA;
    private int rowB;

    private int columnA;
    private int columnB;

    private int value = -1;

    private Orientation orientation;

    public DoubleCellInLinePosition(int rowA, int rowB, int columnA, int columnB, int value) {
        this.rowA = rowA;
        this.rowB = rowB;
        this.columnA = columnA;
        this.columnB = columnB;
        this.orientation = checkOrientation();
        this.value = value;
    }

    public DoubleCellInLinePosition(Position a, Position b) {
        this.rowA = a.getRow();
        this.rowB = b.getRow();
        this.columnA = a.getColumn();
        this.columnB = b.getColumn();
        this.orientation = checkOrientation();

        if (a.getValue() == b.getValue()) {
            this.value = a.getValue();
        }
    }

    private Orientation checkOrientation() {
        if (rowA == rowB && columnA != columnB) {
            return Orientation.Horizontal;
        } else if (rowA != rowB && columnA == columnB) {
            return Orientation.Vertical;
        }
        return Orientation.UnKnown;
    }

    public boolean checkIfNumberIsBlocked(Position p) {
        if (this.value == p.getValue()) {
            if (this.orientation == Orientation.Horizontal) {
                if (this.rowA == p.getRow()) {
                    return true;
                } else return false;
            } else if (this.orientation == Orientation.Vertical) {
                if (this.columnA == p.getColumn()) {
                    return true;
                } else return false;
            }
        } else return false;

        return false;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if (this.orientation == Orientation.Horizontal) {
            stringBuilder.append("In Row ").append(rowA).append(" Columns:").append(columnA).append(" and ").append(columnB).append(" with possible value ").append(value);
        } else
            stringBuilder.append("In Column ").append(columnA).append(" Rows:").append(rowA).append(" and ").append(rowB).append(" with possible value ").append(value);

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleCellInLinePosition)) return false;
        DoubleCellInLinePosition that = (DoubleCellInLinePosition) o;
        return rowA == that.rowA &&
                rowB == that.rowB &&
                columnA == that.columnA &&
                columnB == that.columnB &&
                value == that.value &&
                orientation == that.orientation;
    }

    public boolean checkOverLap(DoubleCellInLinePosition that) {
        if (rowA == that.rowA && rowB == that.rowB && columnA == that.columnA && columnB == that.columnB && value != that.value) {
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(rowA, rowB, columnA, columnB, value, orientation);
    }


    public int getRowA() {
        return rowA;
    }

    public int getRowB() {
        return rowB;
    }

    public int getColumnA() {
        return columnA;
    }

    public int getColumnB() {
        return columnB;
    }

    public int getValue() {
        return value;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
