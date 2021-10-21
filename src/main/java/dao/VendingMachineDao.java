package dao;

import dto.Item;
import service.NoItemInventoryException;

import java.io.FileNotFoundException;
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
