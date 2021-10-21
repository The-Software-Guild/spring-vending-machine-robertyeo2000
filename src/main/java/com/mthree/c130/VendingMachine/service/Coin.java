package com.mthree.c130.VendingMachine.service;

import java.math.BigDecimal;

public enum Coin {
    TWO_POUNDS(new BigDecimal("2.00"), "£2"),
    ONE_POUND(new BigDecimal("1.00"), "£1"),
    FIFTY_PENCE(new BigDecimal("0.50"), "50p"),
    TWENTY_PENCE(new BigDecimal("0.20"), "20p"),
    TEN_PENCE(new BigDecimal("0.10"), "10p"),
    FIVE_PENCE(new BigDecimal("0.05"), "5p"),
    TWO_PENCE(new BigDecimal("0.02"), "2p"),
    ONE_PENCE(new BigDecimal("0.01"), "1p");

    private final BigDecimal VALUE;
    private final String NAME;

    Coin(BigDecimal VALUE, String NAME) {
        this.VALUE = VALUE;
        this.NAME = NAME;
    }

    public BigDecimal getVALUE() {
        return VALUE;
    }

    public String getNAME() {
        return NAME;
    }
}
