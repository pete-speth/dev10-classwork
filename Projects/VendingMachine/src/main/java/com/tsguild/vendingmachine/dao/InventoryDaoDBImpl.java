/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
@Component
public class InventoryDaoDBImpl implements InventoryDao {

    @Autowired
    JdbcTemplate jt;
    
    @Override
    public List<Item> getStock() throws InventoryDaoException {
        
        return jt.query("select * from Inventory", new ItemMapper());
    }

    @Override
    public Item lookUpItem(int id) {
        
        try {
            String sql = "select * from Inventory where Id=?";
            return jt.queryForObject(sql, new ItemMapper(), id);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void update(Item item) throws InventoryDaoException {
        
        String sql = "update Inventory set Quantity=? where Id=?";
        jt.update(sql,item.getCount(),item.getId());
    }
    
    private static final class ItemMapper implements RowMapper<Item>{

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("Id"));
            item.setName(rs.getString("ItemName"));
            item.setCost(rs.getBigDecimal("Price"));
            item.setCount(rs.getInt("Quantity"));
            
            return item;
        }
        
    }
}
