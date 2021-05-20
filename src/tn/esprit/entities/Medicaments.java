/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package Entities;

/**
 *
 * @author ASSOUMA
 */
public class Medicaments {
    private int id ;
    private Double code;
    private String Name;
    private String Categorie;
    private Double prix;
    private Double stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCode() {
        return code;
    }

    public void setCode(Double code) {
        this.code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Medicaments() {
    }

    @Override
    public String toString() {
        return "Medicaments{" + "id=" + id + ", code=" + code + ", Name=" + Name + ", Categorie=" + Categorie + ", prix=" + prix + ", stock=" + stock + '}';
    }
    
    
    
}
