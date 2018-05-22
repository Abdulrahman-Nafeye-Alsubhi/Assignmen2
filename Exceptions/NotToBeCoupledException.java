package Exceptions;

public class NotToBeCoupledException extends Exception {
    public NotToBeCoupledException(){
        super("Both members should be adult.");
    }
}
