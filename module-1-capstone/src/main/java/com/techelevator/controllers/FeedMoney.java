package com.techelevator.controllers;

import com.techelevator.data.Log;
import com.techelevator.util.Balance;

import java.math.BigDecimal;
import java.util.Scanner;

public class FeedMoney {
    private static final String FEED_MONEY = "FEED_MONEY";
    Log writer = new Log();


    public void feedMoney() {

        while (true) {
            Scanner scanner = new Scanner(System.in);


            System.out.println("Feed Money");




            System.out.println("1: $1");
            System.out.println("2: $5");
            System.out.println("3: $10");
            System.out.println("4: $20");
            System.out.println("0: Return to previous menu");
            String choice = scanner.nextLine();



            if (choice.equals("1")) {
                Balance.addToBalance(new BigDecimal (1));

                writer.writer(FEED_MONEY, new BigDecimal(1.00).setScale(2), Balance.getBalance());
                System.out.println("$1 is added, balance is now $" + Balance.getBalance());
            } else if (choice.equals("2")) {
                Balance.addToBalance(new BigDecimal(5));

                writer.writer(FEED_MONEY, new BigDecimal(2.00).setScale(2), Balance.getBalance());
                System.out.println("$5 is added, balance is now $" + Balance.getBalance());
            } else if (choice.equals("3")) {
                Balance.addToBalance(new BigDecimal(10));

                writer.writer(FEED_MONEY, new BigDecimal(5.00).setScale(2), Balance.getBalance());
                System.out.println("$10 is added, balance is now $" + Balance.getBalance());
            } else if (choice.equals("4")) {
                Balance.addToBalance(new BigDecimal(20));

                writer.writer(FEED_MONEY, new BigDecimal(10.00).setScale(2), Balance.getBalance());
                System.out.println("$20 is added, balance is now $" + Balance.getBalance());
            } else if (choice.equals("0")) {
                Balance.displayBalance();
                break;

            }


        }



    }



    }
