package com.techelevator.controllers;


import com.techelevator.PurchasingMenu;
import com.techelevator.data.Log;
import com.techelevator.data.Repo;
import com.techelevator.models.Product;
import com.techelevator.util.Balance;
import com.techelevator.view.Menu;
import java.math.BigDecimal;
import java.util.Scanner;

public class SelectProduct  {
    private final static String SELECT_PRODUCT_SELECT_PRODUCT = "Select product";
    private final static String SELECT_PRODUCT_RETURN_TO_PREVIOUS = "Return to previous menu";
    private final static String[] SELECT_PURCHASE_OPTIONS = {SELECT_PRODUCT_SELECT_PRODUCT, SELECT_PRODUCT_RETURN_TO_PREVIOUS};
    private Menu menu;

    Log log = new Log();

    public SelectProduct() {}

    public void showSelectProductMenu(Menu menu) {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(SELECT_PURCHASE_OPTIONS);

            if (choice.equals(SELECT_PRODUCT_SELECT_PRODUCT)) {
                PurchasingMenu pm = new PurchasingMenu(menu);
                Repo.printListOfProducts();
                selectProduct();
            } else if (choice.equals(SELECT_PRODUCT_RETURN_TO_PREVIOUS)) {
                break;
            }
        }
    }

    public void selectProduct()  {
        while (true) {
            System.out.println("Please select an item using a code from the list above: ");
            var scanner = new Scanner(System.in);
            String code = scanner.nextLine();
            Product product = Repo.getProductByProductCode(code.toLowerCase());
            BigDecimal productPrice = product.getPrice();


            if (product != null) {
                if (product.getQuantity() > 0) {
                    var result = Balance.subtractFromBalance(product.getPrice());

                    if (result == true) {
                        Repo.updateProductQuantity(code);
                        System.out.println(product.makeSound());
                        System.out.println("Your product was dispensed!");
                        Balance.subtractFromBalance(productPrice);
                        System.out.println("Remaining balance: " + Balance.getBalance());
                        log.writer(product.getType(), code, product.getPrice(), Balance.getBalance());
                    } else {
                        System.out.println("Insufficient funds. Please use Feed Money to add to your balance.");
                    } break;
                } else {
                    System.out.println("Your product is out of stock. Please try again later.");
                    break;
                }
            }
        }
    }
}
