package Gui;

import Solver.SudokuBoard;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

   private static Board board;

    private static SudokuBoard sudokuBoard;

    private static final String FILE  = "Easy 1";

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        createAndShowGUI();

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                board.UpdateCells();
//                sudokuBoard.OneStep();
//            }
//        });

        var run = (new Runnable() {
            public void run() {

                while (true) {
                    board.UpdateCells();
                    sudokuBoard.OneStep();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        run.run();

//        sudokuBoard.solve();

    }

    private static void createAndShowGUI() {
        sudokuBoard = new SudokuBoard(FILE);
        board = new Board("Sudoku Board" , sudokuBoard);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(board.SCALE * 15, board.SCALE * 15);
        board.setVisible(true);
    }

}
