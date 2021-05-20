/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author SBS
 */
public class RendezVous implements Comparable<RendezVous>{
    
      private int id;
    private String nomRDV, descriptionRDV;
    private String dateRDV;
    private int id_planning;
    private int id_user;
    private int id_patient;
    private String nomUser,nomPlanning,nomPatient;
 //   ServiceUser su= new ServiceUser();
 //   ServicePatient spatient = new ServicePatient();
//    ServicePlanning splanning = new ServicePlanning();

    public RendezVous(int id, String nomRDV, String descriptionRDV, String dateRDV ,String user, String planning,String id_patient) {
        this.id = id;
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        this.nomPlanning= planning;
        this.nomUser=user;
        this.nomPatient=id_patient;
   //     this.nomUser=su.getByID(user);
   //     this.nomPatient=spatient.getByID(id_patient);
   //     this.nomPlanning=splanning.getByID(planning);
    }
    public RendezVous(String nomRDV, String descriptionRDV, String dateRDV, String user, String planning,String patient) {
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        this.nomPlanning= planning;
        this.nomUser=user;
        this.nomPatient=patient;
   //     this.nomUser=su.getByID(user);
        
    }
    public RendezVous(String nomRDV, String descriptionRDV, String dateRDV, String user,String patient) {
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        
        this.nomUser=user;
        this.nomPatient=patient;
   //     this.nomUser=su.getByID(user);
        
    }
     public RendezVous(String nomRDV, String descriptionRDV, String dateRDV) {
        
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        
    }

    public RendezVous() {
        
    }
   
    public String getNomUser(){
        return nomUser;
    }

    public String getNomPlanning() {
        return nomPlanning;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setNomPlanning(String nomPlanning) {
        this.nomPlanning = nomPlanning;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRDV() {
        return nomRDV;
    }

    public void setNomRDV(String nomRDV) {
        this.nomRDV = nomRDV;
    }

    public String getDescriptionRDV() {
        return descriptionRDV;
    }

    public void setDescriptionRDV(String descriptionRDV) {
        this.descriptionRDV = descriptionRDV;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }
    

    @Override
    public String toString() {
        return "RendezVous{" +  "Nom RDV=" + nomRDV + ", descriptionRDV=" + descriptionRDV + ", dateRDV=" + dateRDV + ", planning=" + nomPlanning + ", user=" + nomUser + ", patient=" + nomPatient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RendezVous other = (RendezVous) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   /* @Override
    public int compareTo(RendezVous o) {
        return this.getDateRDV().compareTo(o.getDateRDV()) ;
    }*/

    @Override
    public int compareTo(RendezVous o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

