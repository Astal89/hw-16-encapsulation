package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final int PRICE = 100;

    public FixPriceProduct(String name) {
        super(name);
    }

    public int getPrice() {
        return PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " с фиксированной ценой: Фиксированная цена " + getPrice();
    }
}
