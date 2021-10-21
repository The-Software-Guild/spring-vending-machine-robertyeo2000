package com.mthree.c130.VendingMachine.controller;

import com.mthree.c130.VendingMachine.dao.IdWithMultipleItemsException;
import com.mthree.c130.VendingMachine.dao.IdWithNoItemsException;
import com.mthree.c130.VendingMachine.dao.VendingMachineStockFileException;
import com.mthree.c130.VendingMachine.dto.Item;
import com.mthree.c130.VendingMachine.service.Coin;
import com.mthree.c130.VendingMachine.service.InsufficientFundsException;
import com.mthree.c130.VendingMachine.service.NoItemInventoryException;
import com.mthree.c130.VendingMachine.service.VendingMachineServiceLayer;
import com.mthree.c130.VendingMachine.ui.VendingMachineView;

import java.util.List;

public class VendingMachineController {

    private final VendingMachineView view;
    private final VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        if (!loadItemsIntoMemory()) return;
        while (true) {
            view.displayWelcomeBanner();
            if (view.askUserIfTheyWantToExit()) {
                break;
            }
            findAndDisplayItems();
            try {
                buyItem();
            } catch (Exception e) {
                view.displayErrorMessage(e);
            }
            giveChange();
        }
    }

    private void giveChange() {
        view.tellUserMoneyOwed(service.getMoneyOwed());
        List<Coin> change = service.calculateChange();
        view.tellUserChangeGiven(change);
    }

    private void buyItem() throws InsufficientFundsException, IdWithNoItemsException, IdWithMultipleItemsException, NoItemInventoryException, VendingMachineStockFileException {
        service.setMoneyOwed(view.askUserForMoney());
        int itemId = view.askUserForItemId();
        service.buyItem(itemId);
    }

    private boolean loadItemsIntoMemory() {
        try {
            service.loadItemsIntoMemory();
        } catch (VendingMachineStockFileException e) {
            view.showStockFileError(e);
            return false;
        }
        return true;
    }

    private void findAndDisplayItems() {
        List<Item> itemsInStock = service.getAllItemsInStock();
        view.displayItems(itemsInStock);
    }
}
