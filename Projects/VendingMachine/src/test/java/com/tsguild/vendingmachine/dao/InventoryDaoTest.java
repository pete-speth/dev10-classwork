/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.Item;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pspethmann
 */
public class InventoryDaoTest {

    InventoryDao dao = new InventoryDaoFileImpl("inventory_test.txt");

    @Test
    public void testGetStock() {
        
        // Ensure that all items are returned from file
        try {
            List<Item> items = dao.getStock();
            assertEquals(items.size(), 3);
        } catch (InventoryDaoException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testLookUpItem() {
        
        // Ensure that item can be looked up by id
        try {
            List<Item> items = dao.getStock();
            Item item = dao.lookUpItem(2);
            assertEquals(item.getName(), "Twix");
        } catch (InventoryDaoException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        
        // Ensure that the file can be update
        try {
            List<Item> items = dao.getStock();
            Item item = dao.lookUpItem(1);
            int originalCount = item.getCount();
            item.setCount(originalCount + 1);
            dao.update(item);

            items = dao.getStock();
            item = dao.lookUpItem(1);
            assertEquals(originalCount+1, item.getCount());

        } catch (InventoryDaoException ex) {
            fail(ex.getMessage());
        }

    }

}
