package com.techelevator.deprecated;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

@Deprecated
public class FeedMoneyNYI {
    private static final String FEED_MONEY_MONEY_TO_DEPOSIT = "Deposit Money";

    private static final String RETURN_TO_PREVIOUS_MENU = "Return To Previous Menu";

    private static final String[] FEED_MONEY_MENU_OPTIONS = { FEED_MONEY_MONEY_TO_DEPOSIT, RETURN_TO_PREVIOUS_MENU };

    private static final BigDecimal[] DOLLAR_AMOUNTS = { new BigDecimal(1), new BigDecimal(5), new BigDecimal(10), new BigDecimal(20)};

    private static BigDecimal balance = BalanceNYI.getBalance();

    private final Menu menu;
    public FeedMoneyNYI(Menu menu) {
        this.menu = menu;
    }

    public void displayFeedMoneyMenu() {

        while(true) {
            var choice = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);

            if(choice.equals(FEED_MONEY_MONEY_TO_DEPOSIT)) {
                BalanceNYI.displayBalance();
                makeTransaction();

            } else if (choice.equals(RETURN_TO_PREVIOUS_MENU)) {break;}
        }
    }

    private void makeTransaction() {

        try {

          while(true) {
                System.out.print("Insert Dollar Amount >>> ");
                var scanner = new Scanner(System.in);

                BigDecimal inputDollarAmount = new BigDecimal(scanner.nextLine());
                var correctIncrement = Arrays.stream(DOLLAR_AMOUNTS).anyMatch(n -> Objects.equals(n, inputDollarAmount));

                if(correctIncrement) {
                    BalanceNYI.addFunds(inputDollarAmount);
                    balance = BalanceNYI.getBalance();
                    System.out.printf(System.lineSeparator() +"$%.2f was received, balance updated to $%.2f" + System.lineSeparator(),inputDollarAmount, balance);
                    break;
                }
                else {
                    System.out.println(System.lineSeparator() + "Please insert an amount in increments of $1, $5, $10 or $20."+ System.lineSeparator());
                }
            }

        } catch (NumberFormatException nfe) {
           System.out.println(System.lineSeparator() + "Input is not a number." + System.lineSeparator());
        }
    }
}
