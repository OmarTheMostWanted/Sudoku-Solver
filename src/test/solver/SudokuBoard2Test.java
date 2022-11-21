package solver;

import Solver.SudokuBoard2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoard2Test {

    @Test
    void getValue() {
        var s = new SudokuBoard2("Easy 1");
        s.scanInstance("Easy 1");
        int i = 1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                s.getBoard()[r][c] = 100 + i++;
            }
        }
        System.out.println(s);
        Assertions.assertEquals(124 , s.getValue(23));
        Assertions.assertEquals(101 , s.getValue(0));
        Assertions.assertEquals(104 , s.getValue(3));
    }

    @Test
    void getValueFromCell() {
        var s = new SudokuBoard2("Easy 1");
        s.scanInstance("Easy 1");
        int i = 1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                s.getBoard()[r][c] = 100 + i++;
            }
        }
        System.out.println(s);
        Assertions.assertEquals(101 , s.getValueFromCell(0 , 0));
        Assertions.assertEquals(121 , s.getValueFromCell(0 , 8));
        Assertions.assertEquals(181 , s.getValueFromCell(8 , 8));
        Assertions.assertEquals(161 , s.getValueFromCell(8 , 0));
        Assertions.assertEquals(141 , s.getValueFromCell(4 , 4));
    }

    @Test
    void TestIfNumberExists(){
        var s = new SudokuBoard2("Easy 1");
        s.scanInstance("Easy 1");
        int i = 1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                s.getBoard()[r][c] = 100 + i++;
            }
        }
        System.out.println(s);
        assertTrue(s.checkIfNumberInCell(165 , 6));
        assertFalse(s.checkIfNumberInCell(165 , 3));
        assertTrue(s.checkIfNumberInColumn(117 , 7));
        assertTrue(s.checkIfNumberInRow(170 , 7));
        assertFalse(s.checkIfNumberInColumn(117 , 8));
        assertFalse(s.checkIfNumberInRow(170 , 4));
    }

    @Test
    void getCellIndex(){
        var s = new SudokuBoard2("Easy 1");
        s.scanInstance("Easy 1");
        int i = 1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                s.getBoard()[r][c] = 100 + i++;
            }
        }
        System.out.println(s);
        Assertions.assertEquals(0 , s.getCellIndex(0 , 0));
        Assertions.assertEquals(2 , s.getCellIndex(2 , 8));
        Assertions.assertEquals(3 , s.getCellIndex(3 , 1));
        Assertions.assertEquals(6 , s.getCellIndex(8 , 0));
        Assertions.assertEquals(7 , s.getCellIndex(6 , 5));;
        Assertions.assertEquals(8 , s.getCellIndex(8 , 8));;
        Assertions.assertEquals(7 , s.getCellIndex(7 , 3));
        Assertions.assertEquals(7 , s.getCellIndex(6 , 4));;
    }
}