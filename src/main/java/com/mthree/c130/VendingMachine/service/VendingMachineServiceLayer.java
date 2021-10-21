package com.mthree.c130.VendingMachine.service;

import com.mthree.c130.VendingMachine.dao.IdWithMultipleItemsException;
import com.mthree.c130.VendingMachine.dao.IdWithNoItemsException;
import com.mthree.c130.VendingMachine.dao.VendingMachineStockFileException;
import com.mthree.c130.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getAllItemsInStock();

    void loadItemsIntoMemory() throws VendingMachineStockFileException;

    void buyItem(int itemId) throws IdWithNoItemsException, IdWithMultipleItemsException, InsufficientFundsException, NoItemInventoryException, VendingMachineStockFileException;

    BigDecimal getMoneyOwed();

    void setMoneyOwed(BigDecimal askUserForMoney);

    List<Coin> calculateChange();
}
