package main.Exceptions;

public class WrongNumberIndexException extends Exception {

    private int row;
    private int column;

    public WrongNumberIndexException(int row , int column){
        this.column = column;
        this.row = column;
    }

    @Override
    public String getMessage(){
        return "Wrong Number index row = " + row + " column = " + column;
    }

}
