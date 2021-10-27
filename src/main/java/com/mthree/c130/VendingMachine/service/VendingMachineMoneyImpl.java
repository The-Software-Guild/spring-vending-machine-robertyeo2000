package com.mthree.c130.VendingMachine.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class VendingMachineMoneyImpl implements VendingMachineMoney {
    
    private BigDecimal moneyOwed;

    @Override
    public BigDecimal getMoneyOwed() {
        return moneyOwed;
    }

    @Override
    public void setMoneyOwed(BigDecimal moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

    @Override
    public List<Coin> calculateChange() {
        List<Coin> coinsReturned = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            while (coin.getVALUE().compareTo(moneyOwed) <=     0) {
                setMoneyOwed(moneyOwed.subtract(coin.getVALUE()));
                coinsReturned.add(coin);
            }
        }
        return coinsReturned;
    }
}
