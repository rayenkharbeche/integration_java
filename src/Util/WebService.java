/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;




import Entities.Dossier;
import Entities.Fichier;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    public void addDossier(Dossier p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/AddDossier";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("description", p.getDescription());
     con.addArgument("date", p.getDate());
     con.addArgument("patient", p.getPatient());
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void EditDossier(Dossier p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/EditDossier/"+p.getId();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("description", p.getDescription());
     con.addArgument("date", p.getDate());
     con.addArgument("patient", p.getPatient());
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addFichier(Fichier p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/AddFichier";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("description", p.getDescription());
     con.addArgument("medecin", p.getMedecin());
     con.addArgument("dossier", p.getDossier());
     
     con.addArgument("image", p.getImage());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
    public void EditFichier(Fichier p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/EditFichier/"+p.getId();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("description", p.getDescription());
     con.addArgument("medecin", p.getMedecin());
     con.addArgument("dossier", p.getDossier());
     
     con.addArgument("image", p.getImage());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
