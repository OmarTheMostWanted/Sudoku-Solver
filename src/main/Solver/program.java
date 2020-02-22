package Solver;

public class program {

    public static void main(String[] args) {
        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//        SudokuBoard sudokuBoard = new SudokuBoard("Easy 2");

        //SudokuBoard sudokuBoard = new SudokuBoard("Medium 2");
        sudokuBoard.solve();

        System.out.println(sudokuBoard.toString());
    }

}
