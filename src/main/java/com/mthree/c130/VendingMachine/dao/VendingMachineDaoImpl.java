package com.mthree.c130.VendingMachine.dao;

import com.mthree.c130.VendingMachine.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class VendingMachineDaoImpl implements VendingMachineDao {

    public static String ITEMS_FILE;
    public static final String DELIMITER = "::";
    private final Map<Item, Integer> itemStock = new HashMap<>();

    public VendingMachineDaoImpl() {
        ITEMS_FILE = "VendingMachineStock.txt";
    }

    public VendingMachineDaoImpl(String fileName) {
        ITEMS_FILE = fileName;
    }

    @Override
    public List<Item> getAllItemsInStock() {
        return itemStock.keySet()
                .stream()
                .filter((i) -> itemStock.get(i) != 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(itemStock.keySet());
    }

    @Override
    public void loadItemsIntoMemory() throws VendingMachineStockFileException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineStockFileException("File " + ITEMS_FILE + " not found", e);
        }
        String currentLine;
        Item currentItem;
        String[] currentLineSplit;
        int currentStock;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentLineSplit = currentLine.split(DELIMITER);
            currentItem = new Item(Integer.parseInt(currentLineSplit[0]), currentLineSplit[1], new BigDecimal(currentLineSplit[2]));
            currentStock = Integer.parseInt(currentLineSplit[3]);
            itemStock.put(currentItem, currentStock);
        }
    }

    @Override
    public void saveItemsToStorage() throws VendingMachineStockFileException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachineStockFileException("Cannot write to " + ITEMS_FILE, e);
        }

        String itemAsText;
        List<Item> items = this.getAllItems();
        for (Item item : items) {
            itemAsText = item.getId() + DELIMITER + item.getName() + DELIMITER + item.getCost() + DELIMITER + itemStock.get(item);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public Item getItem(int itemId) throws IdWithNoItemsException, IdWithMultipleItemsException{
        List<Item> items = itemStock.keySet().stream().filter((i) -> i.getId() == itemId).collect(Collectors.toList());
        if (items.size() == 0) {
            throw new IdWithNoItemsException("There are no items associated with ID: " + itemId);
        }
        if (items.size() >= 2) {
            throw new IdWithMultipleItemsException("Both " + items + " have ID " + itemId);
        }
        return items.get(0);
    }

    @Override
    //NOTE: Does NOT check that there are more than 0 of chosen item in stock
    public void reduceStockByOne(Item item) throws VendingMachineStockFileException {
        itemStock.put(item, itemStock.get(item) - 1);
        saveItemsToStorage();
    }

    @Override
    public int getStock(Item item) {
        return itemStock.get(item);
    }
}
