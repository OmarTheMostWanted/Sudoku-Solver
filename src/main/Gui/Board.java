package Gui;

import Solver.SudokuBoard;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private final GridLayout mainBoard = new GridLayout(3, 3);
    private final Cell[] cells = new Cell[9];
    private final SudokuBoard sudokuBoard;
    final int SCALE = 100;

    public Board(String title, SudokuBoard sudokuBoard) throws HeadlessException {
        super(title);
        setResizable(false);
        setLayout(mainBoard);
        this.sudokuBoard = sudokuBoard;
        InitializeCells();
    }

    private void InitializeCells() {
        for (int i = 0; i < 9; i++) {
            this.cells[i] = new Cell(this.SCALE, sudokuBoard.getBoard(), i);
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
        for (Cell cell : cells) {
            cell.UpdateCell(sudokuBoard);
        }
    }


}
