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
import tn.esprit.entities.Planning;
import tn.esprit.utils.Statics;

/**
 *
 * @author SBS
 */
public class ServicePlanning {
    
    public ArrayList<Planning> tasks;
    
    public static ServicePlanning instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePlanning() {
         req = new ConnectionRequest();
    }

    public static ServicePlanning getInstance() {
        if (instance == null) {
            instance = new ServicePlanning();
        }
        return instance;
    }

    public boolean addPlanning(Planning t) {
   //     Cursor c=MyApplication.db.executeQuery("SELECT * FROM etudient where email = '"+email+"' AND password = '"+pass+"';");
          String nomp = "\""+t.getNomP()+"\"";
         String description = "\""+t.getDescriptionP()+"\"";
         String debut = "\""+t.getDateDebut()+"\"";
         String fin = "\""+t.getDateFin()+"\"";
        String url = Statics.BASE_URL + "/apim/planning/AddPlan/"+ t.getId_user()  + "/"+ nomp + "/"+ debut + "/"+ fin + "/" + description; //création de l'URL
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

    public ArrayList<Planning> parsePlanning(String jsonText) throws ParseException{
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
        
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
         
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Planning t = new Planning();
                float id = Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nomP").toString();
                String desc = obj.get("descriptionP").toString();
               String user= obj.get("personnel").toString();
               // int idUser = Integer.parseInt(obj.get("personnel").toString());
               
                t.setId((int)id);
                t.setNomP(nom);
                t.setDescriptionP(desc);
              //  t.setId_user(idUser);
                t.setNom_user(user);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateD = obj.get("dateDebut").toString();
                String dateF = obj.get("dateFin").toString();
                t.setDateDebut(dateD);
                t.setDateFin(dateF);
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
    
    public ArrayList<Planning> getAllPlanning(){
        String url = Statics.BASE_URL+"/apim/planning";
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    tasks = parsePlanning(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.toString());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    public boolean delPlan(int id) {

     
        String url = Statics.BASE_URL + "/apim/planning/removePlan/"+ id ; //création de l'URL
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
}
