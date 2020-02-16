package test;

import main.Exceptions.WrongCellIndexException;
import main.Exceptions.WrongNumberValueException;
import main.SudokuBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    void checkIfNumberInCellTest() {

        SudokuBoard sudokuBoard = new SudokuBoard("easy1");

            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(3, 0, 0));
            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(2, 0, 0));
            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(6, 0, 0));

            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(9, 1, 1));
            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(8, 2, 2));
            Assertions.assertTrue(sudokuBoard.checkIfNumberInCell(1, 2, 1));


            Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(1, 0, 0));
            Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(5, 2, 1));

            Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(0, 0, 0));
            Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(1, 3, 0));

//            Assertions.assertThrows(WrongNumberValueException.class, () -> {
//                sudokuBoard.checkIfNumberInCell(0, 0, 0);
//            });
//
//            Assertions.assertThrows(WrongCellIndexException.class, () -> {
//                sudokuBoard.checkIfNumberInCell(1, 3, 0);
//            });



    }

    @Test
    void checkIfNumberInRowTest() {
        SudokuBoard sudokuBoard = new SudokuBoard("easy1");
        Assertions.assertTrue(sudokuBoard.checkIfNumberInRow(3, 0));
        Assertions.assertTrue(sudokuBoard.checkIfNumberInRow(5, 5));
        Assertions.assertTrue(sudokuBoard.checkIfNumberInRow(8, 8));
        Assertions.assertFalse(sudokuBoard.checkIfNumberInRow(1 , 1));
        Assertions.assertFalse(sudokuBoard.checkIfNumberInRow(1 , 7));
    }

    @Test
    void checkIfNumberInColumn() {
        SudokuBoard sudokuBoard = new SudokuBoard("easy1");
        Assertions.assertTrue(sudokuBoard.checkIfNumberInColumn(1 , 0));
        Assertions.assertTrue(sudokuBoard.checkIfNumberInColumn(4 , 3));
        Assertions.assertTrue(sudokuBoard.checkIfNumberInColumn(3 , 8));

        Assertions.assertFalse(sudokuBoard.checkIfNumberInColumn(1 , 1));

        Assertions.assertFalse(sudokuBoard.checkIfNumberInColumn(6 , 8));
    }

    @Test
    void checkIfNumberInRowAndColumn(){
        SudokuBoard sudokuBoard = new SudokuBoard("easy1");
        Assertions.assertTrue(sudokuBoard.checkIfNumberInRowAndColumn(1 , 4 , 3));
        Assertions.assertTrue(sudokuBoard.checkIfNumberInRowAndColumn(8 , 8 , 7));

        Assertions.assertFalse(sudokuBoard.checkIfNumberInRowAndColumn(5 , 0 , 0));
        Assertions.assertFalse(sudokuBoard.checkIfNumberInRowAndColumn(5 , 8 , 8));
    }


}
