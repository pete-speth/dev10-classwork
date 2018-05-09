/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.service;

import com.tsguild.vendingmachine.dao.InventoryDao;
import com.tsguild.vendingmachine.dao.InventoryDaoException;
import com.tsguild.vendingmachine.dao.LogDao;
import com.tsguild.vendingmachine.dao.LogDaoException;
import com.tsguild.vendingmachine.model.Change;
import com.tsguild.vendingmachine.model.Item;
import com.tsguild.vendingmachine.model.PurchaseInfo;
import com.tsguild.vendingmachine.model.PurchaseOutcome;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pspethmann
 */
public class VendingService {

    InventoryDao inventory;
    LogDao log;

    public VendingService(InventoryDao inventory, LogDao log) {
        this.inventory = inventory;
        this.log = log;
    }

    BigDecimal money = new BigDecimal(0);

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<Item> getInventory() throws InventoryDaoException {
        return inventory.getStock().stream()
                .filter(item -> item.getCount() > 0)
                .collect(Collectors.toList());
    }

    public Change executePurchase(int userChoice) throws InventoryDaoException,
            InsufficientFundsException, OutOfStockException {

        Item item = inventory.lookUpItem(userChoice);
        PurchaseInfo transaction = new PurchaseInfo();
        transaction.setTimestamp(LocalDateTime.now());

        // Determine whether purchase was successful and gather purchase info
        if (item == null || item.getCount() == 0) {

            transaction.setOutcome(PurchaseOutcome.OUT_OF_STOCK);
            transaction.setItemName("N/A");
            transaction.setItemsLeft(0);

            // Write transaction to log
            try {
                log.addEntry(transaction);
            } catch (LogDaoException ex) {

            }

            throw new OutOfStockException("Selection is not in stock.");

        } else if (money.compareTo(item.getCost()) < 0) {

            transaction.setOutcome(PurchaseOutcome.INSUFFICIENT_FUNDS);
            transaction.setItemName(item.getName());
            transaction.setItemsLeft(item.getCount());

            // Write transaction to log
            try {
                log.addEntry(transaction);
            } catch (LogDaoException ex) {

            }

            throw new InsufficientFundsException("Insufficient funds.");
        }

        transaction.setOutcome(PurchaseOutcome.SUCCESS);
        transaction.setItemName(item.getName());

        // Update inventory and complete purchase info
        item.setCount(item.getCount() - 1);
        inventory.update();
        transaction.setItemsLeft(item.getCount());

        // Write transaction to log
        try {
            log.addEntry(transaction);
        } catch (LogDaoException ex) {

        }

        // Calculate and return change
        return new Change(money.subtract(item.getCost()));

    }

    public String getItemName(int id) {

        Item i = inventory.lookUpItem(id);
        return i.getName();
    }
}
