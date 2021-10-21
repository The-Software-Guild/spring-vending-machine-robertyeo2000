package com.mthree.c130.VendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineMoney {

    List<Coin> calculateChange();

    BigDecimal getMoneyOwed();

    void setMoneyOwed(BigDecimal moneyOwed);
}
