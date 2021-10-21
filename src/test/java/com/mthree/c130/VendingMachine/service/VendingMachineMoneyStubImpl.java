package com.mthree.c130.VendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineMoneyStubImpl implements VendingMachineMoney {

    @Override
    public List<Coin> calculateChange() {
        return List.of(Coin.ONE_POUND);
    }

    @Override
    public BigDecimal getMoneyOwed() {
        return new BigDecimal("1.00");
    }

    @Override
    public void setMoneyOwed(BigDecimal moneyOwed) {

    }
}
