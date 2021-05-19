/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCategory {

    public ArrayList<Category> tasks;
    
    public static ServiceCategory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategory() {
         req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public boolean addCat(Category t) {
        String url = Statics.BASE_URL + "/addmobcat/new?nom=" + t.getNom()+ "&desc=" + t.getDescription();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

    public ArrayList<Category> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Category> getAllCat(){
        String url = Statics.BASE_URL+"/affmobcategory";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
        public boolean editCat(Category t) {
        String url = Statics.BASE_URL + "/cat/modif?id="+t.getId()+"&nom="+t.getNom()+ "&desc="+t.getDescription(); //création de l'URL
               System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteCat(Category t) {
        String url = Statics.BASE_URL + "/cat/del?id=" + t.getId();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
                public ArrayList<Category> getbyCat(String cat){
        String url = Statics.BASE_URL+"/affmobCategorynom/?nom="+cat;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
