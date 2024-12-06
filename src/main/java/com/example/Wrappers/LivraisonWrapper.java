package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.Model.Livraison;
import com.example.services.LivreurService;

@Component
public class LivraisonWrapper implements RowMapper<Livraison> {
    @Autowired
    private LivreurService livreurService;

    @Override
    public Livraison mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Livraison(rs.getInt("id"),
        livreurService.getByID(rs.getInt("FK_livreur")),
         rs.getDate("createdAt"), 
         rs.getDate("updatedAt"), 
         rs.getString("address"),
         rs.getString("description"),
         rs.getString("status"),
         rs.getString("destination"),
         rs.getString("price"),
         rs.getBoolean("payment")
         );
    }
    
}
