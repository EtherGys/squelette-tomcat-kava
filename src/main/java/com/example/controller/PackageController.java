package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Package;
import com.example.services.PackageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private ObjectMapper objectMapper;

    // GET
    @GetMapping
    public List<String> getAll() {
        List<Package> PackageList = packageService.getAll();
        return PackageList.stream()
                        .map(Package -> {
                            try {
                                return objectMapper.writeValueAsString(Package);
                            } catch (Exception e) {
                                throw new RuntimeException("Error converting to JSON", e);
                            }
                        })
                        .collect(Collectors.toList());
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(packageService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Error converting to JSON", HttpStatus.NOT_FOUND);
        }
    }

    // POST : Créer un colis
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Package newPackage){
        try {
            packageService.insert(newPackage);
            String jsonData = objectMapper.writeValueAsString("Colis ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePackage(@RequestBody Package currentPackage, @PathVariable("id") int id) {
        try {
            Package existingPackage = packageService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingPackage == null) {
                return new ResponseEntity<>("{\"error\": \"Formateur not found\"}", headers , HttpStatus.NOT_FOUND);
            }

            existingPackage.setContent(currentPackage.getContent());
            existingPackage.setWeight(currentPackage.getWeight());

            packageService.update(existingPackage);
            String jsonData = objectMapper.writeValueAsString("Colis Modifié");
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
            packageService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Colis supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
