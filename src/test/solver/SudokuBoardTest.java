//package solver;
//
//import Solver.DoubleCellInLinePosition;
//import Solver.Orientation;
//import Solver.Position;
//import Solver.SudokuBoard;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SudokuBoardTest {
//
//    @Test
//    void checkIfNumberInCellTest() {
//
//        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//
//        assertTrue(sudokuBoard.checkIfNumberInCell(3, 0, 0));
//        assertTrue(sudokuBoard.checkIfNumberInCell(2, 0, 0));
//        assertTrue(sudokuBoard.checkIfNumberInCell(6, 0, 0));
//
//        assertTrue(sudokuBoard.checkIfNumberInCell(9, 1, 1));
//        assertTrue(sudokuBoard.checkIfNumberInCell(8, 2, 2));
//        assertTrue(sudokuBoard.checkIfNumberInCell(1, 2, 1));
//
//
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(1, 0, 0));
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(5, 2, 1));
//
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(0, 0, 0));
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInCell(1, 3, 0));
//
////            Assertions.assertThrows(WrongNumberValueException.class, () -> {
////                sudokuBoard.checkIfNumberInCell(0, 0, 0);
////            });
////
////            Assertions.assertThrows(WrongCellIndexException.class, () -> {
////                sudokuBoard.checkIfNumberInCell(1, 3, 0);
////            });
//
//
//    }
//
//    @Test
//    void checkIfNumberInRowTest() {
//        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//        assertTrue(sudokuBoard.checkIfNumberInRow(3, 0));
//        assertTrue(sudokuBoard.checkIfNumberInRow(5, 5));
//        assertTrue(sudokuBoard.checkIfNumberInRow(8, 8));
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInRow(1, 1));
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInRow(1, 7));
//    }
//
//    @Test
//    void checkIfNumberInColumn() {
//        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//        assertTrue(sudokuBoard.checkIfNumberInColumn(1, 0));
//        assertTrue(sudokuBoard.checkIfNumberInColumn(4, 3));
//        assertTrue(sudokuBoard.checkIfNumberInColumn(3, 8));
//
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInColumn(1, 1));
//
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInColumn(6, 8));
//    }
//
//    @Test
//    void checkIfNumberInRowAndColumn() {
//        SudokuBoard sudokuBoard = new SudokuBoard("Easy 1");
//        assertTrue(sudokuBoard.checkIfNumberInRowAndColumn(1, 4, 3));
//        assertTrue(sudokuBoard.checkIfNumberInRowAndColumn(8, 8, 7));
//
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInRowAndColumn(5, 0, 0));
//        Assertions.assertFalse(sudokuBoard.checkIfNumberInRowAndColumn(5, 8, 8));
//    }
//
//    @Test
//    void checkCollisionTest() {
//        Position positionA = new Position(1 , 2 , 1);
//        Position positionB = new Position(2 , 2 , 1);
//        DoubleCellInLinePosition doubleCellInLinePositionAB = new DoubleCellInLinePosition(positionA , positionB);
//
//        assertEquals(Orientation.Vertical , doubleCellInLinePositionAB.getOrientation());
//
//        Position positionC = new Position(6 , 2 , 1);
//        Position positionD = new Position(6 , 3 , 1);
//        DoubleCellInLinePosition doubleCellInLinePositionCD = new DoubleCellInLinePosition(positionC , positionD);
//
//        assertEquals(Orientation.Horizontal , doubleCellInLinePositionCD.getOrientation());
//
//        SudokuBoard sudokuBoard = new SudokuBoard("Blank");
//
//
//
//        boolean res =sudokuBoard.checkCollision(doubleCellInLinePositionAB , doubleCellInLinePositionCD);
//
//        assertTrue(res);
//
//    }
//
//    @Test
//    void DoubleCellInLineTest(){
//        SudokuBoard sudokuBoard = new SudokuBoard("Test Board 1");
//        sudokuBoard.setTestMode(true);
//        sudokuBoard.setRunFor(100);
//
//        sudokuBoard.solve();
//
//        assertEquals(0 , sudokuBoard.getDoubleCellInLinePositions().size());
//
//        assertEquals(1 , sudokuBoard.getBoard()[2][6]);
//
//    }
//
//
//}
