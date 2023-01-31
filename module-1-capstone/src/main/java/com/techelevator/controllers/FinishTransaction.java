package com.techelevator.controllers;

import com.techelevator.data.Log;
import com.techelevator.data.Repo;
import com.techelevator.util.Balance;
import com.techelevator.view.Menu;

import java.math.BigDecimal;

//@Deprecated
public class FinishTransaction {

    private static final BigDecimal VALUE_OF_QUARTER = BigDecimal.valueOf(0.25);
    private static final BigDecimal VALUE_OF_DIME = BigDecimal.valueOf(0.10);
    private static final BigDecimal VALUE_OF_NICKLE = BigDecimal.valueOf(0.05);
    private final Menu menu;

    public FinishTransaction(Menu menu) {
        this.menu = menu;
    }

    public void displayFinishedTransaction() {
        Repo.totalSalesReport();
        FinishTransaction.calculateChange();
    }

    public static void calculateChange() {
        var quarters = 0;
        var dimes = 0;
        var nickles = 0;

        var startingBalance = Balance.getBalance();

        try {
            while(Balance.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                if(BigDecimal.ZERO.compareTo(Balance.getBalance().remainder(VALUE_OF_QUARTER)) == 0) {
                    quarters++;
                    Balance.subtractFromBalance(VALUE_OF_QUARTER);
                } else if (BigDecimal.ZERO.compareTo(Balance.getBalance().remainder(VALUE_OF_DIME)) == 0) {
                    dimes++;
                    Balance.subtractFromBalance(VALUE_OF_DIME);
                } else if (BigDecimal.ZERO.compareTo(Balance.getBalance().remainder(VALUE_OF_NICKLE)) == 0) {
                    nickles++;
                    Balance.subtractFromBalance(VALUE_OF_NICKLE);
                }
            }
            Log log = new Log();
            log.giveChangeLog(startingBalance, Balance.getBalance());
            System.out.printf("Balance: %s Quarters: %d Dimes: %d Nickles: %d", Balance.getBalance(), quarters, dimes, nickles);
        } catch (Exception e) {
            System.out.println("Error in giveCorrectChange");
        }
    }
}

