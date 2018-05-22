package Exceptions;

public class NoParentException extends Exception {

    public NoParentException() {
        super("a child or young child has no parent or has only one parent");
    }
}
