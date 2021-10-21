package com.mthree.c130.VendingMachine.dao;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineAuditDaoImplTest {

    String testAuditFile = "testAudit.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoImpl(testAuditFile);

    @Test
    void writeAuditEntry() throws Exception {
        String entry1 = "test entry 1";
        String entry2 = "test entry 2";
        testAuditDao.writeAuditEntry(entry1);
        testAuditDao.writeAuditEntry(entry2);

        Scanner testScanner = new Scanner(new BufferedReader(new FileReader(testAuditFile)));
        String currentLine = testScanner.nextLine();
        assertEquals(LocalDate.now().toString(), currentLine.split("T")[0]);
        assertEquals("test entry 1", currentLine.split(" : ")[1]);

        currentLine = testScanner.nextLine();
        assertEquals(LocalDate.now().toString(), currentLine.split("T")[0]);
        assertEquals("test entry 2", currentLine.split(" : ")[1]);
    }
}