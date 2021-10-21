package com.mthree.c130.VendingMachine.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineMoneyTest {

    // No need to test getters and setters

    @Test
    void calculateChange() {
        VendingMachineMoneyImpl money = new VendingMachineMoneyImpl();

        money.setMoneyOwed(new BigDecimal("3.88"));
        assertEquals(List.of(Coin.TWO_POUNDS, Coin.ONE_POUND, Coin.FIFTY_PENCE, Coin.TWENTY_PENCE, Coin.TEN_PENCE,
                Coin.FIVE_PENCE, Coin.TWO_PENCE, Coin.ONE_PENCE), money.calculateChange());

        money.setMoneyOwed(new BigDecimal("0.29"));
        assertEquals(List.of(Coin.TWENTY_PENCE, Coin.FIVE_PENCE, Coin.TWO_PENCE, Coin.TWO_PENCE),
                money.calculateChange());
    }
}