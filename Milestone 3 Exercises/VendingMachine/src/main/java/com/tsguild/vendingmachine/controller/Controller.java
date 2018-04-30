/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.controller;

import com.tsguild.vendingmachine.dao.InventoryDaoException;
import com.tsguild.vendingmachine.model.Change;
import com.tsguild.vendingmachine.model.Item;
import com.tsguild.vendingmachine.service.InsufficientFundsException;
import com.tsguild.vendingmachine.service.OutOfStockException;
import com.tsguild.vendingmachine.service.VendingService;
import com.tsguild.vendingmachine.ui.View;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class Controller {

    View view = new View();
    VendingService vendor;

    public Controller(VendingService vendor) {
        this.vendor = vendor;
    }

    public void run() {

        boolean running = true;

        while (running) {

            displayMenu();
            vendor.setMoney(view.getMoney());

            boolean done = false;

            while (!done) {
                int userChoice = view.getOption();
                Change change;

                if (userChoice == 0) {
                    change = new Change(vendor.getMoney());
                    view.printChange(change);
                    done = true;
                } else {
                    try {
                        change = vendor.executePurchase(userChoice);
                        view.printSuccessMessage(vendor.getItemName(userChoice));
                        view.printChange(change);
                        done = true;

                    } catch (InventoryDaoException ex) {
                        view.printErrorMessage(ex.getMessage());
                        done = true;

                    } catch (OutOfStockException ex) {
                        view.printErrorMessage(ex.getMessage());
                        displayMenu();

                    } catch (InsufficientFundsException ex) {
                        view.printErrorMessage(ex.getMessage());
                        view.printCurrentMoney(vendor.getMoney().toString());

                        if (view.willAddMoney()) {
                            BigDecimal extraMoney = view.getMoney();
                            vendor.setMoney(vendor.getMoney().add(extraMoney));
                        }

                        displayMenu();
                    }
                }
            }

            running = view.willMakeAnotherPurchase();
        }
        
        view.sayGoodbye();
    }

    private void displayMenu() {
        // Get items from service
        try {
            List<Item> inStock = vendor.getInventory();
            view.printMenu(inStock);
        } catch (InventoryDaoException ex) {
            view.printErrorMessage(ex.getMessage());
        }
    }

}
