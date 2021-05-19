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
public class Ordonnance {
    private int id ;
    private String medicaments;
    private String categorie ;
    private String consultation;
    private String patient ;
    private String user ;
    private String description;
    private double nbrJours;
    private double nbrDoses;
    private double nbrFois;
    private double nbrPaquets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getNbrJours() {
        return nbrJours;
    }

    public void setNbrJours(double nbrJours) {
        this.nbrJours = nbrJours;
    }

    public double getNbrDoses() {
        return nbrDoses;
    }

    public void setNbrDoses(double nbrDoses) {
        this.nbrDoses = nbrDoses;
    }

    public double getNbrFois() {
        return nbrFois;
    }

    public void setNbrFois(double nbrFois) {
        this.nbrFois = nbrFois;
    }

    public double getNbrPaquets() {
        return nbrPaquets;
    }

    public void setNbrPaquets(double nbrPaquets) {
        this.nbrPaquets = nbrPaquets;
    }

    public Ordonnance() {
    }

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", medicaments=" + medicaments + ", categorie=" + categorie + ", consultation=" + consultation + ", patient=" + patient + ", user=" + user + ", description=" + description + ", nbrJours=" + nbrJours + ", nbrDoses=" + nbrDoses + ", nbrFois=" + nbrFois + ", nbrPaquets=" + nbrPaquets + '}';
    }
    
    
    
   
}
