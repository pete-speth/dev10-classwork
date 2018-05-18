/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.dao;

import com.tsguild.movies.dto.Movie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */

//@Component
public class JdbcDao implements MoviesDao {

    @Override
    public Movie add(Movie m) throws MoviesDaoException {
        Movie inserted = insert(m);
        return inserted;
    }

    @Override
    public List<Movie> list() throws MoviesDaoException {
        
        List<Movie> movies = new ArrayList<>();
        
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()){
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from Movie");
            
            while (rs.next()){
                movies.add(read(rs));
            }
            
        } catch (SQLException ex) {
            throw new MoviesDaoException("DB Failure.", ex);
        }
        
        return movies;
    }

    @Override
    public void edit(Movie m) throws MoviesDaoException {
        
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()){
            
            String sqlCmd = "update Movie "
                    + "set Title = ?, "
                    + "ReleaseDate = ?, "
                    + "RatingMPAA = ?, "
                    + "DirectorName = ?, "
                    + "Studio = ?, " 
                    + "UserNote = ? "
                    + "where MovieID = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlCmd);
            statement.setString(1, m.getTitle());
            statement.setDate(2, Date.valueOf(m.getReleaseDate()));
            statement.setString(3, m.getRatingMPAA());
            statement.setString(4, m.getDirectorName());
            statement.setString(5, m.getStudio());
            statement.setString(6, m.getUserNote());
            statement.setInt(7,m.getIdNumber());
            statement.executeUpdate();    
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MoviesDaoException("DB Error.", ex);
        }
    }

    @Override
    public Movie lookup(String title) throws MoviesDaoException {
        
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()) {
            
            String sqlCmd = "select * from Movie where Title = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlCmd);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()){
                return read(rs);
            }
            
            return null;
            
        } catch (SQLException ex) {
            throw new MoviesDaoException("DB Error.", ex);
        }
    }

    @Override
    public Movie lookup(int id) throws MoviesDaoException {
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()) {
            
            String sqlCmd = "select * from Movie where MovieID = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlCmd);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()){
                return read(rs);
            }
            
            return null;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MoviesDaoException("DB Error.", ex);
        }
    }

    @Override
    public boolean remove(Movie m) throws MoviesDaoException {
        
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()) {
            
            String sqlCmd = "delete from Movie where MovieID = ?;";
            PreparedStatement statement = conn.prepareStatement(sqlCmd);
            statement.setInt(1, m.getIdNumber());
            return statement.executeUpdate()>0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MoviesDaoException("DB Error.", ex);
        }
        
    }

    @Override
    public List<Movie> search(String str) throws MoviesDaoException {
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()) {
            
            List<Movie> movies = new ArrayList<>();
            
            String sqlCmd = "select * from Movie where Title like ?;";
            PreparedStatement statement = conn.prepareStatement(sqlCmd);
            statement.setString(1, str+"%");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                movies.add(read(rs));
            }

            return movies;
            
        } catch (SQLException ex) {
            throw new MoviesDaoException("DB Error.", ex);
        }
    }
    
    
    
    private Movie read(ResultSet rs) throws SQLException {
        
        Movie m = new Movie();
        m.setIdNumber(rs.getInt("MovieID"));
        m.setTitle(rs.getString("Title"));
        m.setReleaseDate(rs.getDate("ReleaseDate").toLocalDate());
        m.setRatingMPAA(rs.getString("RatingMPAA"));
        m.setDirectorName(rs.getString("DirectorName"));
        m.setStudio(rs.getString("Studio"));
        m.setUserNote(rs.getString("UserNote"));
        
        
        return m;
    }
    
    private Movie insert(Movie movie) throws MoviesDaoException {
        
        try (Connection conn = MySQLDatabase.getDataSource().getConnection()){
            
            String sqlCmd = "insert into Movie (Title, ReleaseDate, RatingMPAA,"
                    + " DirectorName, Studio, UserNote)"
                    + "values (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(
                    sqlCmd,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, movie.getTitle());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));
            statement.setString(3, movie.getRatingMPAA());
            statement.setString(4, movie.getDirectorName());
            statement.setString(5, movie.getStudio());
            statement.setString(6, movie.getUserNote());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            movie.setIdNumber(rs.getInt(1));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MoviesDaoException("DB Error.", ex);
        }
        
        return movie;
    }
    
}
