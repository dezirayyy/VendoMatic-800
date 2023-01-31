package com.techelevator;

import com.techelevator.controllers.FeedMoney;
import com.techelevator.controllers.SelectProduct;
import com.techelevator.interfaces.TypeConstants;
import com.techelevator.view.Menu;
import com.techelevator.controllers.FinishTransaction;

public class PurchasingMenu extends Menu implements TypeConstants {

    private static final String PURCHASING_MENU_FEED_MONEY = "Feed Money";

    private static final String PURCHASING_MENU_SELECT_PRODUCT = "Select Product";

    private static final String PURCHASING_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String PURCHASING_MENU_END = "Return to Main Menu";
    private static final String[] PURCHASING_MENU_OPTIONS = {PURCHASING_MENU_FEED_MONEY, PURCHASING_MENU_SELECT_PRODUCT, PURCHASING_MENU_FINISH_TRANSACTION, PURCHASING_MENU_END};
    private final Menu menu;

    public PurchasingMenu(Menu menu) {
        this.menu = menu;
    }
    
    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASING_MENU_OPTIONS);

            if (choice.equals(PURCHASING_MENU_FEED_MONEY)) {
                FeedMoney fm = new FeedMoney();
                fm.feedMoney();
            } else if (choice.equals(PURCHASING_MENU_SELECT_PRODUCT)) {
                SelectProduct sp = new SelectProduct();
                sp.showSelectProductMenu(menu);
            } else if (choice.equals(PURCHASING_MENU_FINISH_TRANSACTION)) {
                FinishTransaction ft = new FinishTransaction(menu);
                ft.displayFinishedTransaction();
            } else if (choice.equals(PURCHASING_MENU_END)) {
                break;
            }
        }
    }

}
