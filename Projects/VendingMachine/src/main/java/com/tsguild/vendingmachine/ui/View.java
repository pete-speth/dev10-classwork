/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.ui;

import com.tsguild.vendingmachine.model.Change;
import com.tsguild.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class View {

    ConsoleIO io = new ConsoleIO();

    public void printMenu(List<Item> stock) {
        io.print("");
        for (Item item : stock) {
            io.print(item.getId() + ". "
                    + item.getName() + " - $"
                    + item.getCost());
        }
        io.print("0. Get change and exit");
    }

    public BigDecimal getMoney() {

        return io.readBigDecimal("Please input money to make a selection: ",
                0, 20);
    }

    public int getOption() {

        return io.readInt("What would you like to buy?: ");
    }

    public void printSuccessMessage(String name) {

        io.print("\nDispensing " + name + "... Thank you!");
    }

    public void printErrorMessage(String message) {

        io.print("\nERROR: " + message);
    }

    public void printCurrentMoney(String money) {
        io.print("You have inserted $" + money);
    }

    public boolean willAddMoney() {
        String response = io.readString(
                "Would you like to add more money? (y/n): ");

        return response.toLowerCase().startsWith("y");
    }

    public void printChange(Change change) {

        if (change.getQuarters() == 0
                && change.getDimes() == 0
                && change.getNickels() == 0
                && change.getPennies() == 0) {

            io.print("No change.");
        } else {

            io.print("\nYour change is: ");
            printCoin("Quarters", change.getQuarters());
            printCoin("Dimes", change.getDimes());
            printCoin("Nickels", change.getNickels());
            printCoin("Pennies", change.getPennies());
        }
    }
    
    public boolean willMakeAnotherPurchase(){
        
        String response = io.readString(
                "Would you like to buy anything else? (y/n): ");
        return response.toLowerCase().startsWith("y");
    }
    
    public void sayGoodbye(){
        io.print("Goodbye!");
    }

    private void printCoin(String coinName, int amount) {

        if (amount > 0) {
            io.print(coinName + ": " + amount);
        }
    }
}
