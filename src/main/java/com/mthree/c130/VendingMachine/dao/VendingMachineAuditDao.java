package com.mthree.c130.VendingMachine.dao;

public interface VendingMachineAuditDao {

    void writeAuditEntry(String entry) throws VendingMachineStockFileException;
}
