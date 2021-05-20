/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;


/**
 *
 * @author rayen
 */
public class Consultation {
    private int id_consultation;
    private int id_user;
    private int id_categorie;
    private String titre;
    private String date_creation;
    private String description;
    private int nbr_vus ;

    public Consultation() {
    }

    public Consultation(int id_user, int id_categorie, String titre, String date_creation, String description) {
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.date_creation = date_creation;
        this.description = description;
    }

    
    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_vus() {
        return nbr_vus;
    }

    public void setNbr_vus(int nbr_vus) {
        this.nbr_vus = nbr_vus;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id_consultation=" + id_consultation + ", id_user=" + id_user + ", id_categorie=" + id_categorie + ", date_creation=" + date_creation + ", description=" + description + ", nbr_vus=" + nbr_vus + '}';
    }
    

   
    
}
