package controller;

import dao.IdWithMultipleItemsException;
import dao.IdWithNoItemsException;
import dao.VendingMachineStockFileException;
import dto.Item;
import service.Coin;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

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
                // TODO: 19/10/2021 Get rid of this
                System.out.println(e.getMessage());
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
