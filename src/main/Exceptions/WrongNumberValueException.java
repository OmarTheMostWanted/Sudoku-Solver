package main.Exceptions;

public class WrongNumberValueException extends Exception{

    private int number;

    public WrongNumberValueException(int number){
        this.number = number;
    }

    @Override
    public String getMessage(){
        return "Number value must be between 1 and 9, but was " + this.number;
    }



}
