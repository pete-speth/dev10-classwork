/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.model;

import java.time.LocalDateTime;

/**
 *
 * @author pspethmann
 */
public class PurchaseInfo {

    private LocalDateTime timestamp;
    private String itemName;
    private PurchaseOutcome outcome;
    private int itemsLeft;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public PurchaseOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(PurchaseOutcome outcome) {
        this.outcome = outcome;
    }

    public int getItemsLeft() {
        return itemsLeft;
    }

    public void setItemsLeft(int itemsLeft) {
        this.itemsLeft = itemsLeft;
    }

}
