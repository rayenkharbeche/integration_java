/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;

/**
 *
 * @author rayen
 */
public class ConsultationService {
     public static String baseURL = "http://localhost:8000/";
     public static User userConnecte = new User();
       private ConnectionRequest con;
    private JSONParser jSONParser;

    public ConsultationService() {
        con = new ConnectionRequest();
        jSONParser = new JSONParser();
    }

    public ArrayList<Consultation> getAll() {
        ArrayList<Consultation> consultations = new ArrayList<>();
        con.setUrl(baseURL+"GetCons");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            try {
                Map<String, Object> ensJSON = jSONParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) ensJSON.get("consultations");
                //System.out.println(list);
                for (Map<String, Object> map : list) {
                    Consultation u = new Consultation();
                int id =Integer.parseInt(map.get("id").toString());
                u.setId_consultation(id);
                int categorie_id = Integer.parseInt(map.get("categorie_id").toString());
                u.setId_categorie(categorie_id);
                int users_id = Integer.parseInt(map.get("users_id").toString());
                u.setId_user(users_id);
               u.setTitre(map.get("titre") + "");
               u.setNbr_vus(Integer.parseInt(map.get("nbr_vus").toString()));
                u.setDate_creation(map.get("date_creation") + "");
                 u.setDescription(map.get("description") + "");

              
                
                    consultations.add(u);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return consultations;
    }
    public Consultation existe(String titre) {
       Consultation cons = null;
        for (Consultation consultation : getAll()){
            if (consultation.getTitre().equals(titre)){
                cons=consultation;
               
            }
        }
        System.out.println(cons);
     return  cons ;

    }
    
 public void ajoutConsultation(Consultation consultation) {
     // /Addcons/{categorie}/{users}/{titre}/{date_creation}/{description}/
     Date date1 = new Date();
        String account_Date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        consultation.setDate_creation(account_Date);
         String titre = "\""+consultation.getTitre()+"\"";
         String description = "\""+consultation.getDescription()+"\"";
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/Addcons/"+consultation.getId_categorie()+"/"+consultation.getId_user()+"/"+titre
                    +"/"+consultation.getDate_creation()+"/"+description+"/";
          
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
           System.out.println(data);
           
    }
  
 public void updateConsultation(Consultation consultation) {
    // /updatecons/{id}/{description}/{titre}
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
         // http://localhost:800/pepiniere123/web/app_dev.php/modifierreponseMobile/86/?descriptionReponse=wala%20wajaatouha&id=43
            con.setUrl("http://localhost:8000/updatecons/"+consultation.getId_consultation()+"/"+consultation.getDescription()+"/"+consultation.getId_categorie()+"/"+consultation.getId_user()+"/"+consultation.getTitre());// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            System.out.println(data);
         
    }
  
     public void deleteConsultation(int id_consultation) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     String Url ="http://localhost:8000/RemoveconsMobile/" +id_consultation+"/";
    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  
   
}
