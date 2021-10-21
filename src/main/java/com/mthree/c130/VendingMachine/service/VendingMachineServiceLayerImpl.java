package com.mthree.c130.VendingMachine.service;

import com.mthree.c130.VendingMachine.dao.*;
import com.mthree.c130.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private final VendingMachineDao dao;
    private final VendingMachineMoney money;
    private final VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineMoney money, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.money = money;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItemsInStock() {
        return dao.getAllItemsInStock();
    }

    @Override
    public void loadItemsIntoMemory() throws VendingMachineStockFileException {
        dao.loadItemsIntoMemory();
    }

    @Override
    public void buyItem(int itemId) throws IdWithNoItemsException, IdWithMultipleItemsException, InsufficientFundsException, NoItemInventoryException, VendingMachineStockFileException {
        Item item = dao.getItem(itemId);
        BigDecimal cost = item.getCost();
        int stock = dao.getStock(item);
        BigDecimal moneyOwed = money.getMoneyOwed();
        if (stock <= 0) {
            throw new NoItemInventoryException("Item: " + item + " is out of stock");
        }
        if (cost.compareTo(moneyOwed) > 0) {
            throw new InsufficientFundsException("Item costs £" + cost + ", only provided £" + moneyOwed);
        }
        dao.reduceStockByOne(item);
        money.setMoneyOwed(moneyOwed.subtract(cost));
        auditDao.writeAuditEntry("ITEM " + itemId + " BOUGHT.");
    }

    @Override
    public BigDecimal getMoneyOwed() {
        return money.getMoneyOwed();
    }

    @Override
    public void setMoneyOwed(BigDecimal moneyOwed) {
        money.setMoneyOwed(moneyOwed);
    }

    @Override
    public List<Coin> calculateChange() {
        return money.calculateChange();
}
}
