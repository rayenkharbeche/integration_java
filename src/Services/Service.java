/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Dossier;
import Entities.Fichier;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class Service {
    public  ArrayList<Dossier> getListdossier(Map m){
        ArrayList<Dossier> listDossier = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Dossiers");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Dossier p = new Dossier();
            
            
            
            
            
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            Map Patient = ((Map) f.get("patient")) ;
            
            
            String description = (String) f.get("description");
            p.setDescription(description);
            Map map1 = ((Map) f.get("DateCreation"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            
            p.setPatient((String)Patient.get("nom"));
            p.setDate(s1);
            listDossier.add(p);  
        }        
        return listDossier;
        
    }
     public  ArrayList<String> getListPatients(Map m){
        ArrayList<String> listDossier = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Patients");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            listDossier.add((String) f.get("nom"));  
        }        
        return listDossier;
        
    }
     public  ArrayList<Fichier> getListfichier(Map m){
        ArrayList<Fichier> listFichier = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Fichiers");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Fichier p = new Fichier();
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            Map dossier = ((Map) f.get("dossier")) ;
            String description = (String) f.get("description");
            p.setDescription(description);
            Map map1 = ((Map) f.get("medecin"));
            p.setImage((String)f.get("image")); 
            p.setDossier(((Double)dossier.get("id")).intValue()+"");
            p.setMedecin((String) map1.get("nom"));
            
            listFichier.add(p);  
        }        
        return listFichier;
        
    }
     public  ArrayList<String> getListMedecins(Map m){
        ArrayList<String> getListMedecins = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Medecins");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            getListMedecins.add((String) f.get("nom"));  
        }        
        return getListMedecins;
        
    }
}
