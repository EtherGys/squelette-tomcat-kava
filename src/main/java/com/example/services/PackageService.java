package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Model.Package;
import com.example.Wrappers.PackageWrapper;

@Service
public class PackageService{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PackageWrapper packageWrapper;

    public List<Package> getAll(){
        String sql = "SELECT * FROM package";
        return jdbcTemplate.query(sql, packageWrapper);
    }

    public Package getByID(int id) {
        String sql = "SELECT * FROM package WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, packageWrapper, id);
    }

       public int insert(Package newPackage){
     String sql = "INSERT INTO package(content,weight, id_1) VALUES (?,?,?)";
     return jdbcTemplate.update(sql, newPackage.getContent(), newPackage.getWeight(), newPackage.getLivraison().getId());
   }

   public int update(Package newPackage){
     String sql = "UPDATE package set content=?,weight=? id_1=? WHERE id=?";
     return jdbcTemplate.update(sql, newPackage.getContent(), newPackage.getWeight(), newPackage.getId(), newPackage.getLivraison().getId());
   }

   public int delete(int id){
     String sql= "DELETE FROM package WHERE id=?";
     return jdbcTemplate.update(sql,id);
   }

   public List<Package> getByPackageID(int id){
    String sql = "SELECT * FROM package WHERE id=?;";
    return this.jdbcTemplate.query(sql, this.packageWrapper,id);
}

}
