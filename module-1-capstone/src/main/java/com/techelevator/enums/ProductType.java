package com.techelevator.enums;

public enum ProductType {
    CANDY("Candy"),
    CHIP("Chip"),
    DRINK("Drink"),
    GUM("Gum");
    private String value;
    ProductType(String value) {
        this.value = value;
    }
}
