package ui;

import dao.VendingMachineStockFileException;
import dto.Item;
import service.Coin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineView {

    private final UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayWelcomeBanner() {
        io.print("Welcome to the vending machine!");
    }

    public void displayItems(List<Item> items) {
        io.print("The items in stock are");
        for (Item item : items) {
            io.print(item.toString());
        }
    }

    public void showStockFileError(VendingMachineStockFileException e) {
        io.print("Error. " + e.getMessage());
    }

    public BigDecimal askUserForMoney() {
        io.print("Please enter money.");
        String moneyStr = io.readString("How much are you entering?: £");
        return new BigDecimal(moneyStr).setScale(2, RoundingMode.HALF_UP);
    }

    public int askUserForItemId() {
        return io.readInt("What item do you want to buy? Enter the number: ");
    }

    public boolean askUserIfTheyWantToExit() {
        String input = io.readString("Do you want to continue?");
        return input.equalsIgnoreCase("no");
    }

    public void tellUserMoneyOwed(BigDecimal moneyOwed) {
        io.print("Your change is £ " + moneyOwed);
    }

    public void tellUserChangeGiven(List<Coin> change) {
        String s = change.stream().map(Coin::getNAME).collect(Collectors.toList()).toString();
        s = s.replaceAll("[\\[\\]]", "");
        io.print("The coins given to you are " + s);
    }
}
