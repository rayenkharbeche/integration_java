/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.RendezVous;
import tn.esprit.utils.Statics;

/**
 *
 * @author SBS
 */
public class ServiceRendez {
    
     public ArrayList<RendezVous> tasks;
     public ArrayList<String> taskss;
    
    public static ServiceRendez instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRendez() {
         req = new ConnectionRequest();
    }

    public static ServiceRendez getInstance() {
        if (instance == null) {
            instance = new ServiceRendez();
        }
        return instance;
    }

  

    public boolean addRDV(RendezVous t) {

        int id=1;   
        String nom = "\""+t.getNomRDV()+"\"";
         String description = "\""+t.getDescriptionRDV()+"\"";
        String date = "\""+ t.getDateRDV()+"\"";
        String url = Statics.BASE_URL + "/apim/renders/addRDV/"+ t.getNomPatient()  + "/"+ t.getNomUser()  + "/"+ nom + "/"+ description + "/"+ date ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

      public ArrayList<RendezVous> parseRDV(String jsonText) throws ParseException{
      try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                RendezVous t = new RendezVous();
                float id = Float.parseFloat(obj.get("id").toString());
               // float id = Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nomRDV").toString();
                String desc = obj.get("description").toString();
               String user= obj.get("user").toString();
/*                float idUser = Float.parseFloat(obj.get("user").toString());
                float idplan = Float.parseFloat(obj.get("plannings").toString());
                float idpat = Float.parseFloat(obj.get("patient").toString());
               */
                t.setId((int)id);
                t.setNomRDV(nom);
                t.setDescriptionRDV(desc);
              //  t.setId_user(idUser);
                t.setNomUser(user);
                DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                java.util.Date date = (java.util.Date) simpleDateFormat.parse(obj.get("dateRDV").toString());
                t.setDateRDV(obj.get("dateRDV").toString());
              //  t.setNomPlanning( obj.get("plannings").toString());
              t.setNomPlanning( "test");
                t.setNomPatient(obj.get("patient").toString());
                  /* t.setId_user((int)idUser);
                   t.setId_planning((int)idplan);
                   t.setId_patient((int)idpat);*/
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
      
       return tasks; 
    }
    
    public ArrayList<RendezVous> getAllRDV(){
       String url = Statics.BASE_URL+"/apim/renders";
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    tasks = parseRDV(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.toString());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(tasks);
        return tasks;
    }
    public boolean delRDV(int id) {

     
        String url = Statics.BASE_URL + "/apim/renders/removePlan/"+ id ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    public boolean updRDV(RendezVous rdv) {

     
        String url = Statics.BASE_URL + "/apim/renders/updateRDV/"+ rdv.getId()  + "/"+ rdv.getNomRDV() + "/"+ rdv.getDescriptionRDV()+ "/"+ rdv.getDateRDV(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
       public ArrayList<RendezVous> getAllRDVByDate(String dated,String datef){
       String url = Statics.BASE_URL+"/apim/rendersD/"+ dated  + "/" +datef;
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    tasks = parseRDV(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.toString());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    

    
}
