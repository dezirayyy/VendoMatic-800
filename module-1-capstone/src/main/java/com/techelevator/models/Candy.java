package com.techelevator.models;

import com.techelevator.interfaces.TypeConstants;
import java.math.BigDecimal;

public class Candy extends Product implements TypeConstants {

    public Candy(String name, BigDecimal price) {
        super(name, price, TYPE_CANDY);
    }

    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}
