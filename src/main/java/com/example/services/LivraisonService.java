package com.example.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Model.Livraison;
import com.example.Model.Livreur;
import com.example.Wrappers.LivraisonWrapper;

@Service
public class LivraisonService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LivraisonWrapper livraisonWrapper;
  
    public List<Livraison> getAll(){
        String sql = "SELECT * FROM livraison";
        return jdbcTemplate.query(sql, livraisonWrapper);
    }

    public Livraison getByID(int id){
        String sql = "SELECT * FROM Livraison WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, livraisonWrapper, id);
    }

    public int insert(Livraison livraison){
        String sql = "INSERT INTO Livraison(address, FK_livreur, destination, description, status, payment, price) VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, livraison.getAddress(),livraison.getLivreur().getId(), livraison.getDestination(), livraison.getDescription(),livraison.getStatus(), livraison.getPayment(), livraison.getPrice());
    }

    public int insert(Date updateDate, String address, Livreur livreur, String destination , String description, String status, Boolean payment, String price){
        String sql = "INSERT INTO Livraison(updatedAt, address, FK_livreur, destination, description, status, payment, price) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,updateDate, address,livreur, destination, description, status, payment, price);
    }

    public int update(Livraison livraison){
        String sql = "UPDATE Livraison set updatedAt = ?, address = ?,FK_livreur = ?, destination = ?, description = ?, status = ?, price = ? WHERE id=?";
        return jdbcTemplate.update(sql,livraison.getUpdatedAt(), livraison.getAddress(), livraison.getLivreur().getId(), livraison.getDestination(), livraison.getDescription(),livraison.getStatus(), livraison.getPrice(), livraison.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM Livraison WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
