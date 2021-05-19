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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.Consultation;

/**
 *
 * @author rayen
 */
public class CategorieService {
     public static String baseURL = "http://localhost:8000/";
    
       private ConnectionRequest con;
    private JSONParser jSONParser;

    public CategorieService() {
        con = new ConnectionRequest();
        jSONParser = new JSONParser();
    }

    public ArrayList<Categorie> getAll() {
        ArrayList<Categorie> categories = new ArrayList<>();
        con.setUrl(baseURL+"GetCategorie");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            try {
                Map<String, Object> ensJSON = jSONParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) ensJSON.get("categorie");
                //System.out.println(list);
                for (Map<String, Object> map : list) {
                    Categorie u = new Categorie();
                int id =Integer.parseInt(map.get("id").toString());
                u.setId_categorie(id);
              
               u.setType_categorie(map.get("type_categorie") + "");
             

                
                    categories.add(u);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return categories;
    }
    
    
    public Categorie existe(String type) {
       Categorie categorie_exi = null;
       
        for (Categorie categorie : getAll()){
            if (categorie.getType_categorie().equals(type)){
               System.out.println("cat"+categorie);
                categorie_exi=categorie;
                               System.out.println("cat_exi"+categorie_exi);

            }
        }
       
     return  categorie_exi ;

    }
    
 public void ajoutCategorie(Categorie categorie) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
           String name2 = "\""+categorie.getType_categorie()+"\"";
            String Url ="http://localhost:8000/Addc/"+name2+"/";
          
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
          System.out.println(data);
           
    }
  
 public void updateCategorie(Categorie categorie) {
    
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
         // http://localhost:800/pepiniere123/web/app_dev.php/modifierreponseMobile/86/?descriptionReponse=wala%20wajaatouha&id=43
            con.setUrl("http://localhost:8000/updatecat/"+categorie.getId_categorie()+"/"+categorie.getType_categorie());// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            System.out.println(data);
         
    }
  
     public void deleteCategorie(int id_categorie) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        ArrayList<Consultation> consultations = new ArrayList<>();
        ConsultationService consultationService = new ConsultationService();
        consultations = consultationService.getAll();
        for (Consultation cons :consultations){
            if(cons.getId_categorie()==id_categorie){
                consultationService.deleteConsultation(cons.getId_consultation());
            }
        }
     String Url ="http://localhost:8000/removeCateg/" +id_categorie+"/";
    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  
   
}
