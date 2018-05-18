/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.dao;

import com.tsguild.movies.dto.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pspethmann
 */
@Component
public class JdbcTemplateDao implements MoviesDao {

    @Autowired
    private JdbcTemplate jt;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Movie add(Movie m) throws MoviesDaoException {

        String sqlCmd = "insert into Movie (Title, ReleaseDate, RatingMPAA,"
                + " DirectorName, Studio, UserNote)"
                + "values (?, ?, ?, ?, ?, ?);";
        jt.update(
                sqlCmd,
                m.getTitle(),
                m.getReleaseDate(),
                m.getRatingMPAA(),
                m.getDirectorName(),
                m.getStudio(),
                m.getUserNote()
        );
        
        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        m.setIdNumber(id);

        return m;
    }

    @Override
    public List<Movie> list() throws MoviesDaoException {
        return jt.query("select * from Movie;", new MovieMapper());
    }

    @Override
    public void edit(Movie m) throws MoviesDaoException {
        String sqlCmd = "update Movie "
                + "set Title = ?, "
                + "ReleaseDate = ?, "
                + "RatingMPAA = ?, "
                + "DirectorName = ?, "
                + "Studio = ?, "
                + "UserNote = ? "
                + "where MovieID = ?;";
        jt.update(
                sqlCmd,
                m.getTitle(),
                m.getReleaseDate(),
                m.getRatingMPAA(),
                m.getDirectorName(),
                m.getStudio(),
                m.getUserNote(),
                m.getIdNumber());
    }

    @Override
    public Movie lookup(String title) throws MoviesDaoException {

        try {
            String sqlCmd = "select * from Movie where Title = ?;";
            return jt.queryForObject(sqlCmd, new MovieMapper(), title);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Movie lookup(int id) throws MoviesDaoException {

        try {
            String sqlCmd = "select * from Movie where MovieID = ?;";
            return jt.queryForObject(sqlCmd, new MovieMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean remove(Movie m) throws MoviesDaoException {
        String sqlCmd = "delete from Movie where MovieID = ?;";
        return jt.update(sqlCmd, m.getIdNumber()) > 0;
        
    }

    @Override
    public List<Movie> search(String str) throws MoviesDaoException {

        String sqlCmd = "select * from Movie where Title like ?;";
        return jt.query(sqlCmd, new MovieMapper(), str + "%");
    }

    private static final class MovieMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int i) throws SQLException {

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

    }

}
