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
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class InventoryDaoFailStubImpl implements InventoryDao {

    @Override
    public List<Item> getStock() throws InventoryDaoException {
        throw new InventoryDaoException("Always fails");
    }

    @Override
    public Item lookUpItem(int id) {
        Item item = new Item();
        item.setId(1);
        item.setName("Snickers");
        item.setCost(new BigDecimal("1.25"));
        item.setCount(10);
        
        return item;
    }

    @Override
    public void update(Item item) throws InventoryDaoException {
        throw new InventoryDaoException("Always fails");
    }

}
