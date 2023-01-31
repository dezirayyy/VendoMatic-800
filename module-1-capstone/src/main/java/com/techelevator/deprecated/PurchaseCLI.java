package com.techelevator.deprecated;

import com.techelevator.controllers.FinishTransaction;
import com.techelevator.view.Menu;


@Deprecated
public class PurchaseCLI {

    private static final String PURCHASING_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASING_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASING_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String RETURN_TO_MAIN_MENU = "Return To Main Menu";

    private static final String[] PURCHASING_MENU_OPTIONS = { PURCHASING_MENU_FEED_MONEY, PURCHASING_MENU_SELECT_PRODUCT, PURCHASING_MENU_FINISH_TRANSACTION, RETURN_TO_MAIN_MENU };

    private Menu menu;

    public PurchaseCLI(Menu menu) {
        this.menu = menu;
    }

    public void displayPurchasingMenu() {
        while(true) {
            var choice = (String) menu.getChoiceFromOptions(PURCHASING_MENU_OPTIONS);

            if(choice.equals(PURCHASING_MENU_FEED_MONEY)) {
                FeedMoneyNYI feedMoney = new FeedMoneyNYI(menu);
                feedMoney.displayFeedMoneyMenu();
            } else if (choice.equals(PURCHASING_MENU_SELECT_PRODUCT)) {
                SelectProductNYI selectProduct = new SelectProductNYI(menu);
                selectProduct.displaySelectProductMenu();

            } else if (choice.equals(PURCHASING_MENU_FINISH_TRANSACTION)) {
                FinishTransaction finishTransaction = new FinishTransaction(menu);
                finishTransaction.displayFinishedTransaction();

            } else if (choice.equals(RETURN_TO_MAIN_MENU)) { break; }
        }
    }
}
