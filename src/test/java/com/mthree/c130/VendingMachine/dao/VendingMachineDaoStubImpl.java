package com.mthree.c130.VendingMachine.dao;

import com.mthree.c130.VendingMachine.dao.IdWithMultipleItemsException;
import com.mthree.c130.VendingMachine.dao.IdWithNoItemsException;
import com.mthree.c130.VendingMachine.dao.VendingMachineDao;
import com.mthree.c130.VendingMachine.dao.VendingMachineStockFileException;
import com.mthree.c130.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item itemInStock;
    public Item itemOutOfStock;

    public VendingMachineDaoStubImpl() {
        itemInStock = new Item(1, "Coca-Cola", new BigDecimal("1.50"));
        itemOutOfStock = new Item(2, "Crisps", new BigDecimal("0.50"));
    }

    @Override
    public List<Item> getAllItemsInStock() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemInStock);
        return itemList;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemInStock);
        itemList.add(itemOutOfStock);
        return itemList;
    }

    @Override
    public void loadItemsIntoMemory() {

    }

    @Override
    public void saveItemsToStorage(){

    }

    @Override
    public Item getItem(int itemId) {
        if (itemId == 1) {
            return itemInStock;
        } else if (itemId == 2) {
            return itemOutOfStock;
        }
        return null;
    }

    @Override
    public void reduceStockByOne(Item item){

    }

    @Override
    public int getStock(Item item) {
        if (item.equals(itemInStock)) {
            return 1;
        }
        return 0;
    }
}
