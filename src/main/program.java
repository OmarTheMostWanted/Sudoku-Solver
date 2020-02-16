package main;

public class program {

    public static void main(String[] args) {
        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//        System.out.println(sudokuBoard.toString());
        sudokuBoard.solve();
    }

}
