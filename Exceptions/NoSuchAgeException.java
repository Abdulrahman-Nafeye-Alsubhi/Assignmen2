package Exceptions;

public class NoSuchAgeException extends Exception{
    public NoSuchAgeException() {
        super("Age should be non negative and less than 150");
    }
}
