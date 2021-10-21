package com.mthree.c130.VendingMachine.dao;

import com.mthree.c130.VendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoImplTest {

    VendingMachineDao testDao;
    Item item1 = new Item(1, "Coca-Cola Can", new BigDecimal("1.50"));
    Item item2 = new Item(2, "Coca-Cola Bottle", new BigDecimal("2.40"));
    Item item3 = new Item(3, "Walkers Ready Salted", new BigDecimal("0.85"));

    @BeforeEach
    void setUp() throws Exception {
        String testFile = "testStock.txt";
        PrintWriter out = new PrintWriter(new FileWriter(testFile));
        out.println("3::Walkers Ready Salted::0.85::0\n" +
                "1::Coca-Cola Can::1.50::3\n" +
                "2::Coca-Cola Bottle::2.40::5");
        out.flush();
        out.close();
        testDao = new VendingMachineDaoImpl(testFile);
        // If all tests pass then this explicitly tests the load method
        testDao.loadItemsIntoMemory();
    }

    //NOTE

    @Test
    void getAllItemsInStock() {
        List<Item> allItemsInStock = testDao.getAllItemsInStock();

        assertNotNull(allItemsInStock);
        assertEquals(2, allItemsInStock.size());
        assertTrue(allItemsInStock.contains(item1));
        assertTrue(allItemsInStock.contains(item2));
        assertFalse(allItemsInStock.contains(item3)); // Technically not needed as previous 3 cover this, but I'm doing it anyway
    }

    @Test
    void getAllItems() {
        List<Item> allItems = testDao.getAllItems();

        assertNotNull(allItems);
        assertEquals(3, allItems.size());
        assertTrue(allItems.contains(item1));
        assertTrue(allItems.contains(item2));
        assertTrue(allItems.contains(item3));
    }

    @Test
    void getItem() throws Exception {
        Item testItem1 = testDao.getItem(1);
        assertEquals(item1, testItem1);

        Item testItem2 = testDao.getItem(2);
        assertEquals(item2, testItem2);

        Item testItem3 = testDao.getItem(3);
        assertEquals(item3, testItem3);

        IdWithNoItemsException thrown = assertThrows(IdWithNoItemsException.class, () -> testDao.getItem(4));
        assertEquals("There are no items associated with ID: 4", thrown.getMessage());
    }

    @Test
    void getStock() {
        int stock1 = testDao.getStock(item1);
        int stock2 = testDao.getStock(item2);
        int stock3 = testDao.getStock(item3);

        assertEquals(3, stock1);
        assertEquals(5, stock2);
        assertEquals(0, stock3);
    }

    @Test
    void reduceStockByOneSaveItemsToStorage() throws VendingMachineStockFileException {
        int stock = testDao.getStock(item1);
        assertEquals(3, stock);

        testDao.reduceStockByOne(item1);
        stock = testDao.getStock(item1);
        assertEquals(2, stock);

        testDao.saveItemsToStorage();
        testDao.loadItemsIntoMemory();
        stock = testDao.getStock(item1);
        assertEquals(2, stock);
    }
}