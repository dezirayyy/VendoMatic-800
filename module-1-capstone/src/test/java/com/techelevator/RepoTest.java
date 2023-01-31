package com.techelevator;

import com.techelevator.data.Repo;
import com.techelevator.interfaces.TypeConstants;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public class RepoTest implements TypeConstants {

    private final static String FILE_PATH = "vendingmachine.csv";

    @Test
    public void startup_successful_test_txt_file() {
        var filePath = "test.txt";
        var isStartupSuccessful = Repo.startup(filePath);
        Assert.assertFalse(isStartupSuccessful);
    }

    @Test
    public void startup_successful_vendingmachine_txt_file() {
        var filePath = "vendingmachine.txt";
        var isStartupSuccessful = Repo.startup(filePath);
        Assert.assertFalse(isStartupSuccessful);
    }

    @Test
    public void startup_successful_vendingmachine_csv_file() {
        var isStartupSuccessful = Repo.startup(FILE_PATH);
        Assert.assertTrue(isStartupSuccessful);
    }


    @Test
    public void return_correct_name_product_by_product_code(){
        Repo.startup(FILE_PATH);

        var productCode = "A1";
        var nameOfProductReceived = Repo.getProductByProductCode(productCode).getName();
        var nameOfProductExpected = "Potato Crisps";

        Assert.assertEquals(nameOfProductExpected, nameOfProductReceived);
        Repo.clearProductList();
    }

    @Test
    public void return_correct_product_price_by_product_code() {
        Repo.startup(FILE_PATH);

        var productCode = "B2";
        var priceOfProductReceived = Repo.getProductByProductCode(productCode).getPrice();
        var priceOfProductExpected = new BigDecimal("1.50").setScale(2, RoundingMode.HALF_DOWN);

        Assert.assertEquals(priceOfProductExpected, priceOfProductReceived);
        Repo.clearProductList();
    }

    @Test
    public void return_correct_product_type_by_product_code() {
        Repo.startup(FILE_PATH);

        var productCode = "C3";
        var typeOfProductReceived = Repo.getProductByProductCode(productCode).getType();

        Assert.assertEquals(TYPE_DRINK, typeOfProductReceived);
        Repo.clearProductList();
    }

    @Test
    public void return_correct_product_quantity_by_product_code() {
        Repo.startup(FILE_PATH);

        var productCode = "D4";
        var quantityOfProductReceived = Repo.getProductByProductCode(productCode).getQuantity();
        var quantityOfProductExpected = 5;

        Assert.assertEquals(quantityOfProductExpected, quantityOfProductReceived);
        Repo.clearProductList();
    }

    @Test
    public void random_product_code_generation_to_test_product_name() {
        Repo.startup(FILE_PATH);

        var randomizer = new Random();
        var index = randomizer.nextInt(PRODUCT_CODES.length);
        var productCode = PRODUCT_CODES[index];

        var nameOfProductReceived = Repo.getProductByProductCode(productCode).getName();
        var nameOfProductExpected = Repo.getListOfProducts().get(productCode).getName();

        Assert.assertEquals(nameOfProductExpected, nameOfProductReceived);

        Repo.clearProductList();
    }

}
