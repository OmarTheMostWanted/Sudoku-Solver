package main;

public class main {

    public static void main(String[] args) {
        SudokuBoard  sudokuBoard = new SudokuBoard("Medium 1");
//        System.out.println(sudokuBoard.toString());
        sudokuBoard.solve();
    }

}
