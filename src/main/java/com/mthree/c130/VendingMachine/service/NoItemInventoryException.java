package com.mthree.c130.VendingMachine.service;

public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }
}
