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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.entities.Chambre;
import tn.esprit.utils.Statics;


/**
 *
 * @author amina
 */
public class ServiceChambre {

    public ArrayList<Chambre> tasks;
    
    public static ServiceChambre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceChambre() {
         req = new ConnectionRequest();
    }

    public static ServiceChambre getInstance() {
        if (instance == null) {
            instance = new ServiceChambre();
        }
        return instance;
    }

    public boolean addChambre(Chambre t) {

        String url = Statics.BASE_URL + "/addmobchambre/new?num=" + t.getNum()+ "&numetage=" + t.getNumetage()+"&nbrplace="+t.getNbrplace()+"&bloc="+t.getBloc()+"&service="+t.getService()+"&category="+t.getCategory_id()+"&etat="+t.getEtat()+"&traitement="+t.getTraitement();
        
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
    
        public boolean EditChambre(Chambre t) {

        String url = Statics.BASE_URL + "/chambre/edit?num=" + t.getNum()+ "&numetage=" + t.getNumetage()+"&nbrplace="+t.getNbrplace()+"&bloc="+t.getBloc()+"&service="+t.getService()+"&category="+t.getCategory_id()+"&etat="+t.getEtat()+"&traitement="+t.getTraitement();
        
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
    

    public ArrayList<Chambre> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Chambre t = new Chambre();
                float id = Float.parseFloat(obj.get("num").toString());
                t.setNum((int)id);
                t.setNbrplace((int) Float.parseFloat(obj.get("nbrplace").toString()));
                t.setNumetage((int) Float.parseFloat(obj.get("numetage").toString()));
                t.setBloc(obj.get("bloc").toString());
                t.setEtat(obj.get("etat").toString());
                t.setTraitement(obj.get("traitement").toString());
                t.setService(obj.get("service").toString());
                String test=obj.get("category").toString();
                
                float idc = Float.parseFloat(test.substring((test).indexOf("id=")+3 ,(test).indexOf(", nom")));
                t.setCategory_id((int)idc);
                t.setCategory(test.substring((test).indexOf("nom=")+4 ,(test).indexOf(", description")));
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Chambre> getAllchambres(){
        String url = Statics.BASE_URL+"/affmobchambre";
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
    
    public boolean deleteChambre(Chambre t) {
        String url = Statics.BASE_URL + "/chambre/del?id=" + t.getNum();
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

}