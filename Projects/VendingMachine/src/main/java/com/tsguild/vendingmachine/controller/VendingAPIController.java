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
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pspethmann
 */
@RestController
public class VendingAPIController {

    @Autowired
    VendingService service;

    @GetMapping("items")
    public List<Item> getAll() {

        try {
            return service.getInventory();
        } catch (InventoryDaoException ex) {
            return null;
        }
    }

    @GetMapping("money/{amount}/item/{id}")
    public ResponseEntity<Object> purchaseItem(@PathVariable BigDecimal amount,
            @PathVariable int id) {

        try {
            service.setMoney(amount);
            Change change = service.executePurchase(id);
            return ResponseEntity.ok(change);
        } catch (InsufficientFundsException ex) {
            BigDecimal cost = service.getItemCost(id);
            BigDecimal moneyNeeded = cost.subtract(amount);
            APIMessage msg = new APIMessage("Please deposit: " + moneyNeeded);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
        } catch (OutOfStockException ex) {
            APIMessage msg = new APIMessage("SOLD OUT!!!");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
        } catch (InventoryDaoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("change/{amount}")
    public Change getChange(@PathVariable BigDecimal amount) {
        
        return new Change(amount);          
    }
}
