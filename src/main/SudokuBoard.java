package main;

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

public class SudokuBoard {



    private int[][] board;
    private int numberOFEmptySpots;

    private final String boardName;

    public SudokuBoard(String filePath) {

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

        for (int c = 0; c < 9; c++) {
            if (board[row][c] == number) {
                return true;
            }
        }
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

        for (int r = 0; r < 9; r++) {
            if (board[r][column] == number) {
                return true;
            }
        }
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

    public void solve() {

        int workingOnCellRow = 0;
        int workingOnCellColumn = 0;

        ArrayList<Pair<Integer, Integer>> possibleSlots = new ArrayList<>();


        solve:
        while (numberOFEmptySpots != 0) {
            ArrayList<Integer> missingNumbers = this.getMissingNumbersInCell(workingOnCellRow, workingOnCellColumn);

            for (int n : missingNumbers) {
                for (int r = workingOnCellRow * 3; r < workingOnCellRow * 3 + 3; r++) {
                    for (int c = workingOnCellColumn * 3; c < workingOnCellColumn * 3 + 3; c++) {
                        if (this.board[r][c] == 0 && !checkIfNumberInRowAndColumn(n, r, c)) {
                            possibleSlots.add(new Pair<Integer, Integer>(r, c));
                        }
                    }
                }
                if (possibleSlots.size() == 1) {
                    board[possibleSlots.get(0).getRow()][possibleSlots.get(0).getColumn()] = n;
                    numberOFEmptySpots--;

                    System.out.println("Working on cell: " + workingOnCellRow + " , " + workingOnCellColumn + " adding " + n);
                    System.out.println(this.toString());

                    continue solve;
                } else if(possibleSlots.size() == 2) {
//
//                    System.out.println("Working on cell: " + workingOnCellRow + " , " + workingOnCellColumn + " adding " + n);
//                    System.out.println("two Options :\n" + possibleSlots.toString());

                }
                possibleSlots = new ArrayList<>();

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


        }

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(boardName + ": number of missing slots " + numberOFEmptySpots + "\n");

        for (int row = 0; row < 9; row++) {
            stringBuilder.append(Arrays.toString(this.board[row]));
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

}
