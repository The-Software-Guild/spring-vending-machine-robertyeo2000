package com.mthree.c130.VendingMachine.service;

import com.mthree.c130.VendingMachine.dao.IdWithNoItemsException;
import com.mthree.c130.VendingMachine.dao.VendingMachineAuditDao;
import com.mthree.c130.VendingMachine.dao.VendingMachineAuditDaoImpl;
import com.mthree.c130.VendingMachine.dao.VendingMachineDao;
import com.mthree.c130.VendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        VendingMachineMoney money = new VendingMachineMoneyStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, money, auditDao);
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllItemsInStock() {
        Item testItem1 = new Item(1, "Coca-Cola", new BigDecimal("1.50"));
        List<Item> allItemsInStock = service.getAllItemsInStock();

        assertEquals(1, allItemsInStock.size());
        assertTrue(allItemsInStock.contains(testItem1));
    }

    @Test
    void loadItemsIntoMemory() {
        // not sure how to test this
    }

    @Test
    void buyItem() {
        InsufficientFundsException exception1 = assertThrows(InsufficientFundsException.class, () -> service.buyItem(1));
        assertEquals("Item costs £1.50, only provided £1.00", exception1.getMessage());

        NoItemInventoryException exception2 = assertThrows(NoItemInventoryException.class, () -> service.buyItem(2));
        assertEquals("Item: 2: Crisps - £0.50 is out of stock", exception2.getMessage());
    }

    @Test
    void getMoneyOwed() {
        assertEquals(new BigDecimal("1.00"), service.getMoneyOwed());
    }

    @Test
    void setMoneyOwed() {
    }

    @Test
    void calculateChange() {
        assertEquals(List.of(Coin.ONE_POUND), service.calculateChange());
    }
}