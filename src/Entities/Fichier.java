/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jasser
 */
public class Fichier {
    private int id ;
    private String description;
    private String Dossier;
    private String image;
    private String Medecin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDossier() {
        return Dossier;
    }

    public void setDossier(String Dossier) {
        this.Dossier = Dossier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMedecin() {
        return Medecin;
    }

    public void setMedecin(String Medecin) {
        this.Medecin = Medecin;
    }

    public Fichier() {
    }

    @Override
    public String toString() {
        return "Fichier{" + "id=" + id + ", description=" + description + ", Dossier=" + Dossier + ", image=" + image + ", Medecin=" + Medecin + '}';
    }
    
    
    
}
