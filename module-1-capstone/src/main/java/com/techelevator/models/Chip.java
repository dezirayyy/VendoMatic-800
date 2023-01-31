package com.techelevator.models;

import com.techelevator.interfaces.TypeConstants;
import java.math.BigDecimal;

public class Chip extends Product implements TypeConstants {

    public Chip(String name, BigDecimal price) {
        super(name, price, TYPE_CHIP);
    }

    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}
