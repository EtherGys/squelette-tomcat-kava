package com.example.controller;

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

import com.example.Model.Livreur;
import com.example.services.LivreurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/livreurs")
public class LivreurController {
    
    @Autowired
    private LivreurService livreurService;

    @Autowired
    private ObjectMapper objectMapper;
    
    
    @GetMapping
    public ResponseEntity<String> getAllLivreurs() {
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(livreurService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return ResponseEntity.ok(jsonData);
    }

    
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(livreurService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Error converting to JSON", HttpStatus.NOT_FOUND);
        }
    }
    // POST
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Livreur livreur){
        try {
            livreurService.insert(livreur);
            String jsonData = objectMapper.writeValueAsString("Livreur ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
    }

        // PATCH
    //exemple /{id}
    //Utilisateur va devoir aller sur /livreurs/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Livreur livreur, @PathVariable("id") int id) {
        try {
            Livreur existingLivreur = livreurService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            if (existingLivreur == null) {
                return new ResponseEntity<>("{\"error\": \"Livreur not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            // Mise à jour des champs
            if (livreur.getEmail() != null) existingLivreur.setEmail(livreur.getEmail());
            if (livreur.getPhone() != null) existingLivreur.setPhone(livreur.getPhone());
            if (livreur.getFirstname() != null) existingLivreur.setFirstname(livreur.getFirstname());
            if (livreur.getLastname() != null) existingLivreur.setLastname(livreur.getLastname());

            livreurService.update(existingLivreur);
            String jsonData = objectMapper.writeValueAsString("Livreur Modifié");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            livreurService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Livreur supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}