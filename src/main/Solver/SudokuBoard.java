package Solver;

import main.Exceptions.WrongCellIndexException;
import main.Exceptions.WrongNumberIndexException;
import main.Exceptions.WrongNumberValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class SudokuBoard implements Runnable{


    private int[][] board;
    private int numberOFEmptySpots;

    private final String boardName;

    public ArrayList<DoubleCellInLinePosition> doubleCellInLinePositions = new ArrayList<>();
    public ArrayList<ReservedTwoPositions> reservedTwoPositionslist = new ArrayList<>();

    private int count = 0;

    private int workingOnCellRow = 0;
    private int workingOnCellColumn = 0;

    private boolean testMode;
    private int runFor;

    public SudokuBoard(String filePath)  {

        Path path = Paths.get("assets", "games", (filePath + ".txt"));
        this.board = new int[9][9];
        this.boardName = filePath;

        try {

            Scanner scanner = new Scanner(new File(path.toString()));
            int r = 0;
            this.numberOFEmptySpots = 0;

            while (scanner.hasNext()) {
                String row = scanner.next();
                for (int c = 0; c < 9; c++) {
                    if (row.charAt(c) != '-') {
                        board[r][c] = Character.getNumericValue(row.charAt(c));
                    } else {
                        numberOFEmptySpots++;
                    }
                }
                r++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    /**
     * checks if a number is in a 3X3 sell.
     *
     * @param number number to find.
     * @param row    cell row.
     * @param column cell column.
     * @return if a cell contains the number.
     */
    public boolean checkIfNumberInCell(int number, int row, int column) {


        try {
            checkCellIndex(row, column);
            checkNumberValue(number);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }


        for (int r = row * 3; r < (row * 3 + 3); r++) {
            for (int c = column * 3; c < (column * 3 + 3); c++) {
                if (board[r][c] == number) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * checks if a number is in a row.
     *
     * @param number
     * @param row
     * @return
     */
    public boolean checkIfNumberInRow(int number, int row) {

        try {
            checkNumberValue(number);
            checkNumberIndex(row, 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

        for (int c = 0; c < 9; c++)
            if (board[row][c] == number)
                return true;

        return false;

    }

    public boolean checkIfNumberInColumn(int number, int column) {

        try {
            checkNumberValue(number);
            checkNumberIndex(1, column);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

        for (int r = 0; r < 9; r++)
            if (board[r][column] == number)
                return true;

        return false;

    }

    public ArrayList<Integer> getMissingNumbersInCell(int row, int column) {

        ArrayList<Integer> res = new ArrayList<>();

        for (int n = 1; n <= 9; n++) {
            if (!checkIfNumberInCell(n, row, column)) {
                res.add(n);

            }
        }
        return res;
    }

    public boolean checkBoardComplete() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean Compelted = false;

    public void solve() {

        ArrayList<Position> possibleSlots = new ArrayList<>();

        solve:
        while (numberOFEmptySpots != 0) {


            if (testMode) {
                if (runFor > 0) {
                    runFor--;
                } else return;
            }

            ArrayList<Integer> missingNumbers = this.getMissingNumbersInCell(workingOnCellRow, workingOnCellColumn);

            count++;
            for (int n : missingNumbers) {
                possibleSlots = new ArrayList<>();
                for (int r = workingOnCellRow * 3; r < workingOnCellRow * 3 + 3; r++) {
                    for (int c = workingOnCellColumn * 3; c < workingOnCellColumn * 3 + 3; c++) {
                        if (this.board[r][c] == 0 && !checkIfNumberInRowAndColumn(n, r, c)) {

//                            if(reservedTwoPositionslist.isEmpty()){
//                                possibleSlots.add(new Position(r, c, n));
//                            }

//                            for (ReservedTwoPositions reservedTwoPositions : reservedTwoPositionslist) {
//                                if(!reservedTwoPositions.isReserved(r , c , n))
                                    possibleSlots.add(new Position(r, c, n));
//                            }
                        }
                    }
                }

                if (possibleSlots.size() == 1) {
                    fillNumber(possibleSlots.get(0).getRow(), possibleSlots.get(0).getColumn(), possibleSlots.get(0).getValue());
                    count = 0;
                    continue solve;
                } else if (possibleSlots.size() == 2) {
                    if (possibleSlots.get(0).getRow() == possibleSlots.get(1).getRow() || possibleSlots.get(0).getColumn() == possibleSlots.get(1).getColumn()) {

                        DoubleCellInLinePosition add = new DoubleCellInLinePosition(possibleSlots.get(0), possibleSlots.get(1));
                        if (!doubleCellInLinePositions.contains(add))

                            for(DoubleCellInLinePosition d : doubleCellInLinePositions){
                                lockDoublePosition(d , add);
                            }

                            doubleCellInLinePositions.add(new DoubleCellInLinePosition(possibleSlots.get(0), possibleSlots.get(1)));
                    }
                } else count++;

            }

            if (workingOnCellColumn < 2) {
                workingOnCellColumn++;
            } else if (workingOnCellRow < 2) {
                workingOnCellColumn = 0;
                workingOnCellRow++;
            } else {
                workingOnCellColumn = 0;
                workingOnCellRow = 0;
            }

            if (count > 81) {
                for (DoubleCellInLinePosition out : doubleCellInLinePositions) {
                    for (DoubleCellInLinePosition in : doubleCellInLinePositions) {

                        lockDoublePosition(out, in);

                        if (checkCollision(out, in)) {
                            doubleCellInLinePositions.remove(in);
                            count = 0;
                            continue solve;
                        }
                    }
                }
            }
        }

    }

    public void OneStep() {

        ArrayList<Position> possibleSlots = new ArrayList<>();

        solve:
        if (numberOFEmptySpots != 0) {


            if (testMode) {
                if (runFor > 0) {
                    runFor--;
                } else return;
            }

            ArrayList<Integer> missingNumbers = this.getMissingNumbersInCell(workingOnCellRow, workingOnCellColumn);

            count++;
            for (int n : missingNumbers) {
                possibleSlots = new ArrayList<>();
                for (int r = workingOnCellRow * 3; r < workingOnCellRow * 3 + 3; r++) {
                    for (int c = workingOnCellColumn * 3; c < workingOnCellColumn * 3 + 3; c++) {
                        if (this.board[r][c] == 0 && !checkIfNumberInRowAndColumn(n, r, c)) {

//                            if(reservedTwoPositionslist.isEmpty()){
//                                possibleSlots.add(new Position(r, c, n));
//                            }

//                            for (ReservedTwoPositions reservedTwoPositions : reservedTwoPositionslist) {
//                                if(!reservedTwoPositions.isReserved(r , c , n))
                            possibleSlots.add(new Position(r, c, n));
//                            }
                        }
                    }
                }

                if (possibleSlots.size() == 1) {
                    fillNumber(possibleSlots.get(0).getRow(), possibleSlots.get(0).getColumn(), possibleSlots.get(0).getValue());
                    count = 0;
                    return;
                } else if (possibleSlots.size() == 2) {
                    if (possibleSlots.get(0).getRow() == possibleSlots.get(1).getRow() || possibleSlots.get(0).getColumn() == possibleSlots.get(1).getColumn()) {

                        DoubleCellInLinePosition add = new DoubleCellInLinePosition(possibleSlots.get(0), possibleSlots.get(1));
                        if (!doubleCellInLinePositions.contains(add))

                            for(DoubleCellInLinePosition d : doubleCellInLinePositions){
                                lockDoublePosition(d , add);
                            }

                        doubleCellInLinePositions.add(new DoubleCellInLinePosition(possibleSlots.get(0), possibleSlots.get(1)));
                    }
                } else count++;

            }

            if (workingOnCellColumn < 2) {
                workingOnCellColumn++;
            } else if (workingOnCellRow < 2) {
                workingOnCellColumn = 0;
                workingOnCellRow++;
            } else {
                workingOnCellColumn = 0;
                workingOnCellRow = 0;
            }

            if (count > 81) {
                for (DoubleCellInLinePosition out : doubleCellInLinePositions) {
                    for (DoubleCellInLinePosition in : doubleCellInLinePositions) {

                        lockDoublePosition(out, in);

                        if (checkCollision(out, in)) {
                            doubleCellInLinePositions.remove(in);
                            count = 0;
                            return;
                        }
                    }
                }
            }
        }
        Compelted = true;
    }



    public boolean lockDoublePosition(DoubleCellInLinePosition out, DoubleCellInLinePosition in) {
        if (out.checkOverLap(in)) {
            ReservedTwoPositions reservedTwoPositions = new ReservedTwoPositions(out.getRowA(), out.getRowB(), out.getColumnA(), out.getColumnB(), out.getValue(), in.getValue());

            if (!reservedTwoPositionslist.contains(reservedTwoPositions))
                this.reservedTwoPositionslist.add(reservedTwoPositions);
            return true;
        }
        return false;
    }

    private void fillNumber(int row, int column, int value) {
        if (board[row][column] == 0) {
            board[row][column] = value;
            numberOFEmptySpots--;

            log(new Position(row, column, value), workingOnCellRow, workingOnCellColumn);

        } else {
            System.err.println("Trying to overwrite a value at" + row + ',' + column);
            System.exit(1);
        }
    }

    public boolean checkCollision(DoubleCellInLinePosition out, DoubleCellInLinePosition in) {
        if (out == in) return false;
        if (out.getValue() != in.getValue()) return false;
        if (out.getOrientation() == in.getOrientation()) return false;

        if (out.getOrientation() == Orientation.Horizontal) {
            if (out.getRowA() == in.getRowA()) {
                fillNumber(in.getRowB(), in.getColumnB(), in.getValue());
                return true;
            }
            if (out.getRowA() == in.getRowB()) {
                fillNumber(in.getRowA(), in.getColumnA(), in.getValue());
                return true;
            }
        }

        if (out.getOrientation() == Orientation.Vertical) {
            if (out.getColumnA() == in.getColumnA()) {
                fillNumber(in.getRowB(), in.getColumnB(), in.getValue());
                return true;
            }
            if (out.getRowA() == in.getRowB()) {
                fillNumber(in.getRowA(), in.getColumnA(), in.getValue());
                return true;
            }
        }

        return false;
    }

    private void log(Position position, int r, int c) {
        System.out.println("Working on cell: " + r + " , " + c + " adding to " + position.toString());

        System.out.println("Options list of size " + doubleCellInLinePositions.size() + doubleCellInLinePositions.toString());

        System.out.println(this.toString());
    }

    public boolean checkIfNumberInRowAndColumn(int number, int row, int column) {
        return checkIfNumberInRow(number, row) || checkIfNumberInColumn(number, column);
    }

    private void checkCellIndex(int row, int column) throws WrongCellIndexException {
        if (row > 2 || row < 0 || column > 2 || column < 0) {
            throw new WrongCellIndexException(row, column);
        }
    }

    private void checkNumberValue(int number) throws WrongNumberValueException {
        if (number < 1 || number > 9) {
            throw new WrongNumberValueException(number);
        }
    }

    private void checkNumberIndex(int row, int column) throws WrongNumberIndexException {
        if (row > 9 || row < 0 || column > 9 || column < 0) {
            throw new WrongNumberIndexException(row, column);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setNumberOFEmptySpots(int numberOFEmptySpots) {
        this.numberOFEmptySpots = numberOFEmptySpots;
    }

    public ArrayList<DoubleCellInLinePosition> getDoubleCellInLinePositions() {
        return doubleCellInLinePositions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(boardName).append(": number of missing slots ").append(numberOFEmptySpots).append("\n");

        stringBuilder.append("  0  1  2  3  4  5  6  7  8 \n");

        for (int row = 0; row < 9; row++) {
            stringBuilder.append(row).append(Arrays.toString(this.board[row]));
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public void printBaord() {
        System.out.println(this.toString());
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void setRunFor(int runFor) {
        this.runFor = runFor;
    }

    @Override
    public void run() {
        this.solve();
    }
}
