/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.dao.InventoryDao;
import com.tsguild.vendingmachine.dao.InventoryDaoException;
import com.tsguild.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class InventoryDaoStubImpl implements InventoryDao {
    
    List<Item> stock;
    
    @Override
    public List<Item> getStock() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Snickers");
        item1.setCost(new BigDecimal("1.25"));
        item1.setCount(10);
        
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Twix");
        item2.setCost(new BigDecimal("1.00"));
        item2.setCount(10);
        
        Item item3 = new Item();
        item3.setId(3);
        item3.setName("Peanuts");
        item3.setCost(new BigDecimal("0.75"));
        item3.setCount(0);
        
        stock = new ArrayList<>();
        
        stock.add(item1);
        stock.add(item2);
        stock.add(item3);
        
        return stock;
    
    }

    @Override
    public Item lookUpItem(int id) {
        return stock.get(id-1);
    }

    @Override
    public void update(Item item) throws InventoryDaoException {
        
    }
    
}
