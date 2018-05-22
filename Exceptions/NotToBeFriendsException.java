package Exceptions;

public class NotToBeFriendsException extends Exception {

    public NotToBeFriendsException() {
        super("Adult can child cannot be friends and age group of children should be more than 3 years");
    }
}
