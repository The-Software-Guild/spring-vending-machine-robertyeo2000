package service;

import dao.IdWithMultipleItemsException;
import dao.IdWithNoItemsException;
import dao.VendingMachineStockFileException;
import dto.Item;

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
