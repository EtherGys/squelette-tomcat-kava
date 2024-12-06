package com.example.Model;


import java.util.Date;

  

public class Livraison {
    private int id;
    private  Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String address;
    private String destination;
    private String description;
    private String status;
    private Boolean payment = false;
    private String price;
    private Livreur livreur;

    public Livraison(int id, Livreur livreur, Date createdAt, Date updatedAt, String address, String description, String status, String destination, String price, Boolean payment) {
        this.id = id;
        this.livreur = livreur;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.address = address;
        this.destination = destination;
        this.description = description;
        this.status = status;
        this.price = price;
        this.payment = false;
    }

    public Livraison(String address, Livreur livreur, String description, String status, String destination, String price, Boolean payment) {
        this.updatedAt = new Date();
        this.address = address;
        this.livreur = livreur;
        this.destination = destination;
        this.description = description;
        this.status = status;
        this.price = price;
        this.payment = payment;
    }


    public Livraison() {
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    
    
}
