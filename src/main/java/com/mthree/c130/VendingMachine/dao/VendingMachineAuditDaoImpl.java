package com.mthree.c130.VendingMachine.dao;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao{

    public static String AUDIT_FILE;

    public VendingMachineAuditDaoImpl() {
        AUDIT_FILE = "audit.txt";
    }

    public VendingMachineAuditDaoImpl(String file) {
        AUDIT_FILE = file;
    }

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineStockFileException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachineStockFileException("Could not persist audit information.", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp + " : " + entry);
        out.flush();
    }
}
