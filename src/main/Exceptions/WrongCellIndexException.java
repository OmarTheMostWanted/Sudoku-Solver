package main.Exceptions;

public class WrongCellIndexException extends Exception {

    private int row;
    private int column;

    public WrongCellIndexException(int row , int column){
        this.column = column;
        this.row = column;
    }

    @Override
    public String getMessage(){
        return "Wrong Cell index row = " + row + " column = " + column;
    }

}
