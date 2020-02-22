package Solver;

public class ReservedTwoPositions {

    private int rowA , rowB , columnA , columnB , valueA , valueB;

    public ReservedTwoPositions(int rowA, int rowB, int columnA, int columnB, int valueA, int valueB) {
        this.rowA = rowA;
        this.rowB = rowB;
        this.columnA = columnA;
        this.columnB = columnB;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public boolean isReserved(int row, int column, int value){
        return ((this.rowA == row && this.columnA == column) || (this.rowB == row && this.columnB == column)) && this.valueA != value && this.valueB != value;
    }



}
