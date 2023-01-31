package com.techelevator.deprecated;

import com.techelevator.data.Repo;
import com.techelevator.models.Product;
import com.techelevator.view.Menu;
import java.util.Scanner;

@Deprecated
public class SelectProductNYI {

    private static final String ENTER_PRODUCT_CODE_TO_PURCHASE = "Enter product code to purchase";
    private static final String RETURN_TO_PREVIOUS_MENU = "Return To Previous Menu";
    private static final String[] SELECT_PRODUCT_MENU_OPTIONS = { ENTER_PRODUCT_CODE_TO_PURCHASE, RETURN_TO_PREVIOUS_MENU };
    private final Menu menu;

    public SelectProductNYI(Menu menu) {
        this.menu = menu;
    }

    public void displaySelectProductMenu() {
        while(true) {
            Repo.printListOfProducts();
            BalanceNYI.displayBalance();

            var choice = (String) menu.getChoiceFromOptions(SELECT_PRODUCT_MENU_OPTIONS);

            if(choice.equals(ENTER_PRODUCT_CODE_TO_PURCHASE)) {
                makeProductSelection();
            } else if (choice.equals(RETURN_TO_PREVIOUS_MENU)) { break; }
        }
    }

    public void makeProductSelection() {

        while(true) {
            System.out.print("Enter a code to select an item >>> ");
            var scanner = new Scanner(System.in);
            String productCode = scanner.nextLine().trim();

            Product product = Repo.getProductByProductCode(productCode);

            if(product != null) {

                if(product.getQuantity() > 0) {
                    var result= BalanceNYI.subtractFunds(product.getPrice());

                    if(result) {
                        Repo.updateProductQuantity(productCode);
                        System.out.println("Purchase was successful");
                    }
                    else { System.out.println("Insufficient funds."); }
                    break;
                }
                else
                    System.out.println("SOLD OUT!!!");
            }
        }
    }
}
