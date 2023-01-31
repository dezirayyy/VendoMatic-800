package com.techelevator.deprecated;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Deprecated
public class BalanceNYI {

    private static BigDecimal balance = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_DOWN);

    public static void addFunds(BigDecimal funds) {
        balance = balance.add(funds);
    }

    public static boolean subtractFunds(BigDecimal priceOfProduct) {
        if(balance.compareTo(priceOfProduct) >= 0) {
            balance = balance.subtract(priceOfProduct);
            return true;
        }
        return false;
    }

    public static BigDecimal getBalance() { return balance; }

    public static void displayBalance() { System.out.printf(System.lineSeparator() + "Current Money Provided: $%.2f" + System.lineSeparator(), balance); }
}
