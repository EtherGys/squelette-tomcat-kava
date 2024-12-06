package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.Model.Livreur;


public class LivreurWrapper implements RowMapper<Livreur> {

    @Override
    public Livreur mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Livreur(
            rs.getInt("id"), 
            rs.getString("firstname"), 
            rs.getString("lastname"), 
            rs.getString("email"), 
            rs.getString("phone")
        );
    }
    
}

