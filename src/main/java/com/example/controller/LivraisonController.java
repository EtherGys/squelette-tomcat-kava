package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Model.Livraison;
import com.example.services.LivraisonService;
import com.example.services.PackageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/livraisons")
public class LivraisonController {
    
    @Autowired
    private LivraisonService LivraisonService;
    private PackageService packageService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    // GET
    @GetMapping
    public ResponseEntity<String> getAll() {
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(LivraisonService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return ResponseEntity.ok(jsonData);
    }
    
    
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(LivraisonService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Error converting to JSON", HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
    // POST
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Livraison livraison){
        try {
            LivraisonService.insert(livraison);
            String jsonData = objectMapper.writeValueAsString("Livraison ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        
    }
    
    
    // PATCH
    //exemple /{id}
    //Utilisateur va devoir aller sur /livraisons/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Livraison livraison, @PathVariable("id") int id) {
        try {
            Livraison existingLivraison = LivraisonService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            
            if (existingLivraison == null) {
                return new ResponseEntity<>("{\"error\": \"Livraison not found\"}", headers, HttpStatus.NOT_FOUND);
            }
            
            // Mise à jour des champs
            if (livraison.getUpdatedAt() != null) existingLivraison.setUpdatedAt(new Date());
            if (livraison.getAddress() != null) existingLivraison.setAddress(livraison.getAddress());
            if (livraison.getDestination() != null) existingLivraison.setDestination(livraison.getDestination());
            if (livraison.getDescription() != null) existingLivraison.setDescription(livraison.getDescription());
            if (livraison.getPrice() != null) existingLivraison.setPrice(livraison.getPrice());
            if (livraison.getPayment() != null) existingLivraison.setPayment(livraison.getPayment());
            if (livraison.getStatus() != null) existingLivraison.setStatus(livraison.getStatus());
            if (livraison.getLivreur() != null) existingLivraison.setLivreur(livraison.getLivreur());
            
            LivraisonService.update(existingLivraison);
            String jsonData = objectMapper.writeValueAsString("Livraison Modifié");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
            
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            LivraisonService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Livraison supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
