package com.techelevator.util;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Balance {

    private static BigDecimal balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    public static void addToBalance(BigDecimal funds) {
        balance = balance.add(funds);
    }

    public static boolean subtractFromBalance(BigDecimal priceOfProduct) {
        if(balance.compareTo(priceOfProduct) >= 0) {
            balance = balance.subtract(priceOfProduct);
            return true;
        }
        return false;
    }

    public static void displayBalance() { System.out.printf(System.lineSeparator() + "Current Money Provided: $%.2f" + System.lineSeparator(), balance); }

    public static BigDecimal getBalance() {
        return balance;
    }
}



