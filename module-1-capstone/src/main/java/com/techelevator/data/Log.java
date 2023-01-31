package com.techelevator.data;

import com.techelevator.models.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String LOG_GIVE_CHANGE = "GIVE CHANGE";
    public Log(){}

    private String logTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
    }

    public void writer(String typeOfTransaction, BigDecimal amount, BigDecimal balance) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(("./log.txt"), true))) {

            String printToday = currentDate.toString();
            String printTime = currentTime.toString().substring(0, currentTime.toString().length() - 4);
            String printAmount = amount.toString();
            String printBalance = balance.toString();

            logWriter.println(printToday + " " + printTime + " " + typeOfTransaction + " $"
                    + printAmount + " $" + printBalance);

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    public void writer(String typeOfTransaction, String choice, BigDecimal amount, BigDecimal balance) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(("./log.txt"), true))) {

            String printToday = currentDate.toString();
            String printTime = currentTime.toString().substring(0, currentTime.toString().length() - 4);
            String printAmount = amount.toString();
            String printBalance = balance.toString();

            logWriter.println(printToday + " " + printTime + " " + typeOfTransaction + " " + choice.toUpperCase() + " $"
                    + printAmount + " $" + printBalance);

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }



    public void giveChangeLog(BigDecimal amount, BigDecimal balance) {

        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(("./log.txt"), true))) {

            logWriter.println(logTimeStamp() + " " + LOG_GIVE_CHANGE  + " $"
                    + amount + " $" + balance);

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

}
