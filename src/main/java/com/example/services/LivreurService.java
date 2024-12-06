package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Model.Livreur;
import com.example.Wrappers.LivreurWrapper;


@Service
public class LivreurService {
    

    @Autowired
    private JdbcTemplate jdbcTemplate;


   public List<Livreur> getAll(){
        String sql = "SELECT * FROM livreur;";
        return this.jdbcTemplate.query(sql, new LivreurWrapper());
   } 

   public Livreur getByID(int id){
        String sql = "SELECT * FROM livreur WHERE id= ?";
        return this.jdbcTemplate.queryForObject(sql, new LivreurWrapper(),id);
   }

   public int insert(Livreur livreur){
     String sql = "INSERT INTO livreur(firstname,lastname,email,phone) VALUES (?,?,?,?)";
     return this.jdbcTemplate.update(sql, livreur.getFirstname(), livreur.getLastname(), livreur.getEmail(), livreur.getPhone());
   }

   public int update(Livreur livreur){
     String sql = "UPDATE Livreur set firstname=?,lastname=?,email=?,phone=? WHERE id=?";
     return this.jdbcTemplate.update(sql, livreur.getFirstname(), livreur.getLastname(), livreur.getEmail(), livreur.getPhone(), livreur.getId());
   }

   public int delete(int id){
     String sql= "DELETE FROM Livreur WHERE id=?";
     return this.jdbcTemplate.update(sql,id);
   }
   
}