package com.techelevator;

import com.techelevator.controllers.FinishTransaction;
import com.techelevator.util.Balance;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BalanceTest {


    @Test
    public void adding_funds_to_balance_and_subtracting_1() {
        Balance.addToBalance(BigDecimal.valueOf(2));
        Assert.assertFalse(Balance.subtractFromBalance(BigDecimal.valueOf(3)));

    }

    @Test
    public void adding_funds_to_balance_and_subtracting_2() {
        Balance.addToBalance(BigDecimal.valueOf(4));
        Assert.assertTrue(Balance.subtractFromBalance(BigDecimal.valueOf(2)));

    }

    @Test
    public void adding_funds_to_balance_and_subtracting_3() {
        Balance.addToBalance(BigDecimal.valueOf(7));
        Assert.assertTrue(Balance.subtractFromBalance(BigDecimal.valueOf(7)));
    }
}
