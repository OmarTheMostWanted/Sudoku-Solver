package Gui;

import Solver.SudokuBoard;
import Solver.SudokuBoard2;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

   private static Board board;
    private static Board2 board2;

    private static SudokuBoard sudokuBoard;
    private static SudokuBoard2 sudokuBoard2;

    private static final String FILE  = "Medium 3";

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);


//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                board.UpdateCells();
//                sudokuBoard.OneStep();
//            }
//        });

//        createAndShowGUI();
//        var run = (new Runnable() {
//            public void run() {
//
//                while (true) {
//                    board.UpdateCells();
//                    sudokuBoard.OneStep();
//
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });
//        run.run();

        createAndShowGUI2();
        var run2 = (new Runnable() {
            public void run() {

                while (true) {
                    board2.UpdateCells();
                    sudokuBoard2.OneStep();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        run2.run();

    }

    private static void createAndShowGUI() {
        sudokuBoard = new SudokuBoard(FILE);
        board = new Board("Sudoku Board" , sudokuBoard);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(board.SCALE * 15, board.SCALE * 15);
        board.setVisible(true);
    }

    private static void createAndShowGUI2() {
        sudokuBoard2 = new SudokuBoard2(FILE);
        board2 = new Board2("Sudoku Board" , sudokuBoard2);
        board2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board2.setSize(board2.SCALE * 15, board2.SCALE * 15);
        board2.setVisible(true);
    }

}
