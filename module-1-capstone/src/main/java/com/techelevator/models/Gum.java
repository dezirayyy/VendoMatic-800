package com.techelevator.models;

import com.techelevator.interfaces.TypeConstants;
import java.math.BigDecimal;

public class Gum extends Product implements TypeConstants {

    public Gum(String name, BigDecimal price) {
        super(name, price, TYPE_GUM);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}
