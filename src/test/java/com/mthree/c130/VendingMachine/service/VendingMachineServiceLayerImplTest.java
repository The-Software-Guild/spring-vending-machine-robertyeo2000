package com.mthree.c130.VendingMachine.service;

import com.mthree.c130.VendingMachine.dao.*;
import com.mthree.c130.VendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendingMachineServiceLayerImplTest {

    @Mock
    private VendingMachineDao dao;
    @Mock
    private VendingMachineMoney money;

    private VendingMachineServiceLayer service;

    @BeforeEach
    void setUp() throws IdWithNoItemsException, IdWithMultipleItemsException {
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();

        MockitoAnnotations.openMocks(this);

        when(money.getMoneyOwed()).thenReturn(new BigDecimal("1.00"));
        when(money.calculateChange()).thenReturn(List.of(Coin.ONE_POUND));

        Item itemInStock = new Item(1, "Coca-Cola", new BigDecimal("1.50"));
        Item itemOutOfStock = new Item(2, "Crisps", new BigDecimal("0.50"));
        when(dao.getAllItemsInStock()).thenReturn(List.of(itemInStock));
        when(dao.getAllItems()).thenReturn(List.of(itemInStock, itemOutOfStock));
        when(dao.getItem(anyInt())).thenAnswer(
                invocationOnMock -> {
                    int argument = invocationOnMock.getArgument(0);
                    if (argument == 1) {
                        return itemInStock;
                    } else if (argument == 2) {
                        return itemOutOfStock;
                    } else {
                        return null;
                    }
                }
        );
        when(dao.getStock(any(Item.class))).thenAnswer(
                invocationOnMock -> {
                    Item argument = invocationOnMock.getArgument(0);
                    if (argument.equals(itemInStock)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
        );

        service = new VendingMachineServiceLayerImpl(dao, money, auditDao);
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
        // or this
    }

    @Test
    void calculateChange() {
        assertEquals(List.of(Coin.ONE_POUND), service.calculateChange());
    }
}