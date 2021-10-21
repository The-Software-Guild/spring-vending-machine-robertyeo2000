package com.mthree.c130.VendingMachine.dao;

public class IdWithNoItemsException extends Exception{

    public IdWithNoItemsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdWithNoItemsException(String message) {
        super(message);
    }


}
