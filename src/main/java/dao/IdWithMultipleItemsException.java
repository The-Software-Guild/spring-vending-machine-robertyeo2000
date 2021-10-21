package dao;

public class IdWithMultipleItemsException extends Exception {

    public IdWithMultipleItemsException (String message, Throwable cause) {
        super(message, cause);
    }

    public IdWithMultipleItemsException (String message) {
        super(message);
    }
}
