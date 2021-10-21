package com.mthree.c130.VendingMachine.dao;

import com.mthree.c130.VendingMachine.dto.Item;
import com.mthree.c130.VendingMachine.service.NoItemInventoryException;

import java.util.List;

public interface VendingMachineDao {
    List<Item> getAllItemsInStock();

    List<Item> getAllItems();

    void loadItemsIntoMemory() throws VendingMachineStockFileException;

    void saveItemsToStorage() throws VendingMachineStockFileException;

    Item getItem(int itemId) throws IdWithNoItemsException, IdWithMultipleItemsException;

    void reduceStockByOne(Item item) throws VendingMachineStockFileException;

    int getStock(Item item);
}
