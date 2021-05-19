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
import tn.esprit.entities.User;

/**
 *
 * @author rayen
 */
public class UserService {
     public static String baseURL = "http://localhost:8000/";
     public static User userConnecte = new User();
       private ConnectionRequest con;
    private JSONParser jSONParser;

    public UserService() {
        con = new ConnectionRequest();
        jSONParser = new JSONParser();
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        con.setUrl(baseURL+"GetUsers");
        con.setPost(false);
        con.addResponseListener((evt) -> {
            try {
                Map<String, Object> ensJSON = jSONParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) ensJSON.get("users");
                //System.out.println(list);
                for (Map<String, Object> map : list) {
                    User u = new User();
                int id =Integer.parseInt(map.get("id").toString());
                u.setId(id);
                int cin = Integer.parseInt(map.get("cin").toString());
                u.setCin(cin);
               u.setRoles(map.get("roles") + "");
               u.setPassword(map.get("password") + "");
                u.setUsername(map.get("username") + "");
                 u.setEmail(map.get("email") + "");

              //  u.setPhoto(users.get("photo") + "");
                userConnecte = u;
                
                    users.add(u);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return users;
    }
    public User existe(String mail, String pwd) {
       User userConnecte = null;
        for (User user : getAll()){
            if ((user.getPassword().equals(pwd))&& (user.getEmail().equals(mail))){
                userConnecte=user;
               
            }
        }
        System.out.println(userConnecte);
     return  userConnecte ;

    }
    
 public void ajoutUser(User user) {
     
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
            String Url ="http://localhost:8000/singup?id="+user.getId()+"&email="+user.getEmail()+"&roles="+user.getRoles()+"&password="+user.getPassword()+"&cin="+user.getCin()+"&username="+user.getUsername();
          
            con.setUrl(Url);// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
           System.out.println(data);
           
    }
  
 public void updateUser(User user) {
    
            ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
         // http://localhost:800/pepiniere123/web/app_dev.php/modifierreponseMobile/86/?descriptionReponse=wala%20wajaatouha&id=43
            con.setUrl("http://localhost:8000/edituserM?id="+user.getId()+"&email="+user.getEmail()+"&roles="+user.getRoles()+"&password="+user.getPassword()+"&cin="+user.getCin()+"&username="+user.getUsername());// Insertion de l'URL de notre demande de connexion     
            NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            String data = new String(con.getResponseData());
            System.out.println(data);
         
    }
  
     public void deleteUser(int id_user) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     String Url ="http://localhost:8000/RemoveusertMobile/" +id_user+"/";
    
       con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
  
     
     public String findcodebyid(int id){
         return "";
     }
   
   
}
