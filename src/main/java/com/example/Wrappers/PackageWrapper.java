package com.example.Wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.Model.Package;
import com.example.services.LivraisonService;

@Component
public class PackageWrapper implements RowMapper<Package> {

  @Autowired
    private LivraisonService livraisonService;

    @Override
    public Package mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Package(rs.getInt("id"),
        rs.getString("content"),
        rs.getString("weight"),
        this.livraisonService.getByID(rs.getInt("id_1"))
        );
    }
    
}
