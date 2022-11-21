package Gui;

import Solver.DoubleCellInLinePosition;
import Solver.SudokuBoard;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class Cell extends JPanel {
    GridLayout cell = new GridLayout();
    JTextPane[] boxes = new JTextPane[9];

    final int INDEX;
    final int SIZE;

    public Cell(int boxSize, int[][] board, int cellIndex) {

        SIZE = boxSize;

        StyleConstants.setAlignment(new SimpleAttributeSet(), StyleConstants.ALIGN_CENTER);
        cell.setRows(3);
        cell.setColumns(3);
        setLayout(cell);
        INDEX = cellIndex;

        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new JTextPane();
            boxes[i].setBorder(BorderFactory.createLineBorder(Color.blue));
            boxes[i].setFont(new Font("Courier", Font.BOLD, boxSize));
            boxes[i].setSize(boxSize, boxSize);
            var cellValue = cellNumber(board, cellIndex, i);
            if (cellValue != 0) {
                boxes[i].setEditable(false);
                boxes[i].setText(Integer.toString(cellValue));
                var center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                boxes[i].getStyledDocument().setParagraphAttributes(0, boxes[i].getStyledDocument().getLength(), center, false);
            }

            this.add(Integer.toString(i + 1), boxes[i]);
        }

    }

    HashMap<Integer , HashSet<Integer>> possibleSpots = new HashMap<>();

    public void UpdateCell(SudokuBoard sudokuBoard) {
        var board = sudokuBoard.getBoard();
        for (int i = 0; i < boxes.length && cellNumber(board , INDEX , i) != 0 ; i++) {
            if (boxes[i].isEditable()) {
                var value = cellNumber(board, INDEX, i);
                boxes[i].setEditable(false);
                boxes[i].setEnabled(false);
                boxes[i].setText(Integer.toString(value));
                boxes[i].setDisabledTextColor(Color.BLUE);
                boxes[i].setFont(new Font("Courier", Font.ITALIC, SIZE));
                var center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                boxes[i].getStyledDocument().setParagraphAttributes(0, boxes[i].getStyledDocument().getLength(), center, false);
            }
        }

        for (DoubleCellInLinePosition doubleCellInLinePosition : sudokuBoard.doubleCellInLinePositions) {
            var rA = (doubleCellInLinePosition.getRowA() % 3);
            var cA = (doubleCellInLinePosition.getColumnA() % 3);
            var rB = (doubleCellInLinePosition.getRowB() % 3);
            var cB = (doubleCellInLinePosition.getColumnB() % 3);

            var iA = ((rA * 3)  + cA);
            var iB = ((rB*3) + cB);


            var vA = cellNumber(board , INDEX , iA);
            var vB = cellNumber(board , INDEX , iB);

            if(vA != 0 && !possibleSpots.containsKey(iA)){
                possibleSpots.put(iA , new HashSet<>());
            }

            if(vB != 0 && !possibleSpots.containsKey(iB)){
                possibleSpots.put(iB , new HashSet<>());
            }


            FillPossibleBox(iA, vA);
            FillPossibleBox(iB, vB);
        }
//        for (ReservedTwoPositions reservedTwoPositions : sudokuBoard.reservedTwoPositionslist) {
//
//        }
    }

    private void FillPossibleBox(int iA, int vA) {
        if(boxes[iA].isEditable() && vA != 0  &&  !possibleSpots.get(iA).contains(vA)) {
            boxes[iA].setText(boxes[iA].getText() + vA);
            boxes[iA].setEnabled(false);
            boxes[iA].setDisabledTextColor(Color.RED);
            boxes[iA].setFont(new Font("Courier", Font.ITALIC, SIZE/4));
            var right = new SimpleAttributeSet();
            StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
            boxes[iA].getStyledDocument().setParagraphAttributes(0, boxes[iA].getStyledDocument().getLength(), right, false);
            possibleSpots.get(iA).add(vA);
        }
    }

    private static int cellNumber(int[][] board, int cellIndex, int index) {
        var cr = ((cellIndex / 3) % 3);
        var cc = (cellIndex % 3);
        return board[((index / 3) % 3 + cr * 3)][((index % 3) + cc * 3)];
    }
}
