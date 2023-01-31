package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Product {
    private String name;
    private BigDecimal price;
    private int quantity = 5;
    private String type;

    public Product(String name, BigDecimal price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public Product() {}

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }
    public abstract String makeSound();
}