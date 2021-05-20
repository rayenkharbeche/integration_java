/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SBS
 */
public class Planning implements Comparable<Planning>{
      
     private int id;
    private String nomP, descriptionP;
    private String dateDebut, dateFin ;
    private int id_user;
    private String nom_user;
    List <RendezVous> listRDV; 

    public Planning(int id, String nomP, String descriptionP, String dateDebut, String dateFin,int user) {
        this.id = id;
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_user=user;
        listRDV = new ArrayList<>();
        
    }
    public Planning(String nomP, String descriptionP, String dateDebut, String dateFin,int user) {
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_user=user;
        listRDV = new ArrayList<>();
    }
    
    public Planning(String nomP, String descriptionP, String dateDebut, String dateFin) {
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        listRDV = new ArrayList<>();
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public Planning() {
            }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public List<RendezVous> getListRDV() {
        return listRDV;
    }

    public void setListRDV(List<RendezVous> listRDV) {
        this.listRDV = listRDV;
    }

    
    public void ajoutRDV(RendezVous rdv){
        listRDV.add(rdv);
    }

    @Override
    public String toString() {
        return "Planning{" + "id=" + id + ", nomP=" + nomP + ", descriptionP=" + descriptionP + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nom_user=" + nom_user + ", listRDV=" + listRDV + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Planning p = (Planning) obj;
        if (this.id == p.id) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (getClass() != p.getClass()) {
            return false;
        }
        return true;
    }
/*
    @Override
    public int compareTo(Planning o) {
        return o.getDateDebut().compareTo(this.getDateDebut()) ;
    }*/

    @Override
    public int compareTo(Planning o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
