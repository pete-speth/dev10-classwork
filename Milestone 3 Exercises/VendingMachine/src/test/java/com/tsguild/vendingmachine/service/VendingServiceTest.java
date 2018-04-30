/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.service;

import com.tsguild.vendingmachine.dao.InventoryDao;
import com.tsguild.vendingmachine.dao.InventoryDaoException;
import com.tsguild.vendingmachine.dao.InventoryDaoFileImpl;
import com.tsguild.vendingmachine.dao.LogDao;
import com.tsguild.vendingmachine.dao.LogDaoFileImpl;
import com.tsguild.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pspethmann
 */
public class VendingServiceTest {

    InventoryDao inventory = new InventoryDaoFileImpl("inventory_test.txt");
    LogDao log = new LogDaoFileImpl("log_test.txt");
    VendingService service = new VendingService(inventory, log);

    public VendingServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            inventory.getStock();
        } catch (InventoryDaoException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInventory() throws Exception {

        // Ensure that items with a count of 0 are filtered out
        List<Item> items = service.getInventory();
        Assert.assertEquals(items.size(), 2);
    }

    @Test
    public void testExecutePurchase() throws Exception {

        // Ensure that an item can be purchased and the count will decrement
        try {
            service.setMoney(BigDecimal.TEN);
            Item item = inventory.lookUpItem(1);
            int originalCount = item.getCount();
            service.executePurchase(1);
            assertEquals(item.getCount(), originalCount - 1);
        } catch (Exception ex) {
            fail("Purchase Unsuccessful");
        }

        // Check that insufficient funds exception can be thrown
        try {
            service.setMoney(BigDecimal.ZERO);
            service.executePurchase(1);
            fail("Insufficient funds but exception was not thrown");
        } catch (InsufficientFundsException ex) {

        }

        // Check that out of stock exception can be thrown
        try {
            service.setMoney(BigDecimal.TEN);
            service.executePurchase(3);
            fail("Out of stock but exception was not thrown");
        } catch (OutOfStockException ex) {
            
        }

    }

    @Test
    public void testGetItemName() {
        
        // Check that name of an item is returned using it's id
        String name = service.getItemName(1);
        assertEquals(name, "Snickers");
    }

}
