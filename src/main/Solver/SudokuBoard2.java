package Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SudokuBoard2 extends AbstractSudokuBoard {

    private int[][] board;
    private boolean[][] originalNumbers;
    private int numberOFEmptySpots;
    private final String boardName;

    public SudokuBoard2(String boardName) {
        this.boardName = boardName;
        scanInstance(boardName);
    }

    @Override
    public int[][] getBoard() {
        return board;
    }

    @Override
    public boolean isComplete() {
        return numberOFEmptySpots == 0;
    }

    @Override
    public void scanInstance(String name) {
        try {

            Path path = Paths.get("assets", "games", (name + ".txt"));
            this.board = new int[9][9];
            this.originalNumbers = new boolean[9][9];
            Scanner scanner = new Scanner(new File(path.toString()));
            int r = 0;
            this.numberOFEmptySpots = 0;

            while (scanner.hasNext()) {
                String row = scanner.next();
                for (int c = 0; c < 9; c++) {
                    if (row.charAt(c) != '-') {
                        board[r][c] = Character.getNumericValue(row.charAt(c));
                        originalNumbers[r][c] = true;
                    } else {
                        numberOFEmptySpots++;
                        originalNumbers[r][c] = false;
                    }
                }
                r++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    @Override
    public int getValue(int row, int column) {
        return board[row][column];
    }

    @Override
    public int getValue(int index) {
        int r = index / 9;
        int c = index % 9;
        return board[r][c];
    }

    @Override
    public int getValueFromCell(int cell, int index) {
        var cr = ((cell / 3) % 3);
        var cc = (cell % 3);
        return board[((index / 3) % 3 + cr * 3)][((index % 3) + cc * 3)];
    }

    public Map<Integer, Set<Integer>> superPositions = new HashMap<>();

    @Override
    public void OneStep() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if(board[r][c] == 0){
                    var possibleNumbers =getPossibleNumbers(r , c);
                    superPositions.put(getBoxIndex(r , c) , possibleNumbers);
                }
            }
        }
    }

    @Override
    public void Solve() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if(board[r][c] == 0){
                    var possibleNumbers =getPossibleNumbers(r , c);
                    superPositions.put(getBoxIndex(r , c) , possibleNumbers );
                }
            }
        }
    }

    public Set<Integer> getPossibleNumbers(int row, int column) {
        var res = new HashSet<Integer>();
        for (int i = 1; i <= 9; i++) {
            if (!checkIfNumberInCell(i , getCellIndex(row , column)) && !checkIfNumberInColumn(i , column) && !checkIfNumberInRow(i , row)) res.add(i);
        }
        return res;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append(boardName).append(": number of missing slots ").append(numberOFEmptySpots).append("\n");

        stringBuilder.append("  0  1  2  3  4  5  6  7  8 \n");

        for (int row = 0; row < 9; row++) {
            stringBuilder.append(row).append(Arrays.toString(this.board[row]));
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public boolean checkIfNumberInRow(int number, int row) {
        for (int c = 0; c < 9; c++)
            if (board[row][c] == number) return true;
        return false;
    }

    public boolean checkIfNumberInColumn(int number, int column) {
        for (int r = 0; r < 9; r++)
            if (board[r][column] == number) return true;
        return false;
    }

    public boolean checkIfNumberInCell(int number, int cell) {
        for (int r = (cell / 3) * 3; r < (cell / 3) * 3 + 3; r++) {
            for (int c = (cell % 3) * 3; c < (cell % 3) * 3 + 3; c++) {
                if (board[r][c] == number) return true;
            }
        }
        return false;
    }

    public int getCellIndex(int row, int column) {
        return ((row / 3) * 3) + (column / 3);
    }

    public int getCellIndex(int index) {
        return index / 9 - index % 9;
    }

    public List<Integer> getNumbersInCell(int cellIndex){
        ArrayList<Integer> numbers = new ArrayList<>(9);
        for (int r = (cellIndex / 3) * 3; r < (cellIndex / 3) * 3 + 3; r++) {
            for (int c = (cellIndex % 3) * 3; c < (cellIndex % 3) * 3 + 3; c++) {
                numbers.add(board[r][c]);
            }
        }
        return numbers;
    }

    public List<Integer> getIndiesInCell(int cellIndex){
        ArrayList<Integer> numbers = new ArrayList<>(9);
        for (int r = (cellIndex / 3) * 3; r < (cellIndex / 3) * 3 + 3; r++) {
            for (int c = (cellIndex % 3) * 3; c < (cellIndex % 3) * 3 + 3; c++) {
                numbers.add(getBoxIndex(r , c));
            }
        }
        return numbers;
    }

    public int getBoxIndex(int row, int column) {
        return ((row % 9) * 9) + (column % 9);
    }

}
