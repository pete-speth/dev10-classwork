/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */

@Component
public class MySQLDatabase {
    
    @Bean
    public DataSourceTransactionManager getTransactionManager() 
            throws SQLException {
        return new DataSourceTransactionManager(getDataSource());
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate() throws SQLException {
        return new JdbcTemplate(getDataSource());
    }
    
    
    public static DataSource getDataSource() throws SQLException {
        
        Properties dbProperties = new Properties();
        
        try (InputStream stream = MySQLDatabase.class
                .getClassLoader().getResourceAsStream("db.properties")){
            
            dbProperties.load(stream);
            
        } catch (IOException ex){
            ex.printStackTrace();
            System.exit(100);
        }
        
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(dbProperties.getProperty("serverName"));
        ds.setDatabaseName(dbProperties.getProperty("databaseName"));
        ds.setUser(dbProperties.getProperty("userName"));
        ds.setPassword(dbProperties.getProperty("password"));
        ds.setUseSSL(false);
        ds.setServerTimezone("UTC");
        
        return ds;
    }
}
