package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineMoney {
    
    private BigDecimal moneyOwed;

    public BigDecimal getMoneyOwed() {
        return moneyOwed;
    }

    public void setMoneyOwed(BigDecimal moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

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
