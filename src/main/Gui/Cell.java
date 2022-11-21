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


            var v = doubleCellInLinePosition.getValue();

            if(!possibleSpots.containsKey(iA)){
                possibleSpots.put(iA , new HashSet<>());
            }

            if(!possibleSpots.containsKey(iB)){
                possibleSpots.put(iB , new HashSet<>());
            }


            FillPossibleBox(iA, v);
            FillPossibleBox(iB, v);
        }
//        for (ReservedTwoPositions reservedTwoPositions : sudokuBoard.reservedTwoPositionslist) {
//
//        }
    }

    private void FillPossibleBox(int index, int value) {
        if(boxes[index].isEditable() &&  !possibleSpots.get(index).contains(value)) {
            boxes[index].setEnabled(false);
            boxes[index].setDisabledTextColor(Color.RED);
            boxes[index].setFont(new Font("Courier", Font.ITALIC, SIZE/4));
            var right = new SimpleAttributeSet();
            StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
            boxes[index].getStyledDocument().setParagraphAttributes(0, boxes[index].getStyledDocument().getLength(), right, false);
            possibleSpots.get(index).add(value);
            StringBuilder sb = new StringBuilder();
            for (Integer integer : possibleSpots.get(index)) {
                sb.append(integer);
            }
            boxes[index].setText(sb.toString());
        }
    }

    private static int cellNumber(int[][] board, int cellIndex, int index) {
        var cr = ((cellIndex / 3) % 3);
        var cc = (cellIndex % 3);
        return board[((index / 3) % 3 + cr * 3)][((index % 3) + cc * 3)];
    }
}
