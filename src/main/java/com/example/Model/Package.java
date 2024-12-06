package com.example.Model;

public class Package {
    private int id;
    private String content;
    private String weight;
    private Livraison livraison;


    public Package(int id, String content, String weight, Livraison livraison) {
        this.id = id;
        this.content = content;
        this.weight = weight;
        this.livraison = livraison;
    }
    public Package(String content, String weight) {
        this.content = content;
        this.weight = weight;
    }

    public Package() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }


    
}
