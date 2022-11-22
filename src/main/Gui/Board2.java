package Gui;


import Solver.SudokuBoard2;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Board2 extends JFrame {
    private final GridLayout mainBoard = new GridLayout(3, 3);
    private final Cell2[] cells = new Cell2[9];
    private final SudokuBoard2 sudokuBoard;
    final int SCALE = 50;

    public Board2(String title, SudokuBoard2 sudokuBoard) throws HeadlessException {
        super(title);
        setResizable(false);
        setLayout(mainBoard);
        this.sudokuBoard = sudokuBoard;
        InitializeCells();
    }

    private void InitializeCells() {
        for (int i = 0; i < 9; i++) {
            this.cells[i] = new Cell2(this.SCALE, sudokuBoard, i);
            this.add(this.cells[i]);
        }
        this.mainBoard.setRows(3);
        this.mainBoard.setColumns(3);
        this.mainBoard.setHgap(SCALE / 10);
        this.mainBoard.setVgap(SCALE / 10);
        this.setSize(this.SCALE * 15, this.SCALE * 15);
        this.setVisible(true);
    }

    public void UpdateCells(){
        for (Cell2 cell : cells) {
            cell.UpdateCell(sudokuBoard);
        }
    }
}
