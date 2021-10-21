package dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private BigDecimal cost;

    public Item(int id, String name, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }


    public Item(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.name + " - £" + this.cost.setScale(2, RoundingMode.HALF_UP);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
