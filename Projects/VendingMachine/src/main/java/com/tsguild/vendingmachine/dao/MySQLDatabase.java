/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;


import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
@Component
public class MySQLDatabase {
    
    @Bean
    public JdbcTemplate getJdbcTemplate() throws SQLException {
        return new JdbcTemplate(getDataSource());
    }
    
    
    public static DataSource getDataSource() throws SQLException {
        
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("MovieLibrary");
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setUseSSL(false);
        ds.setServerTimezone("UTC");
        
        return ds;
    }
}
