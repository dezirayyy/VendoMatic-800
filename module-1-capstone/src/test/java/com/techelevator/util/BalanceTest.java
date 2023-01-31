package com.techelevator.util;

import com.techelevator.data.Repo;
import com.techelevator.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BalanceTest {

    @Test
    public void subtract_returns_true_when_called() {
        Repo.startup("vendingmachine.csv");
        Product product = Repo.getProductByProductCode("A1");
        Balance.addToBalance(new BigDecimal(5));
        Assert.assertTrue(Balance.subtractFromBalance(product.getPrice()));
    }
}
