/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.Item;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public interface InventoryDao {

    List<Item> getStock() throws InventoryDaoException;

    Item lookUpItem(int id);

    void update(Item item) throws InventoryDaoException;
    
}
