/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entities.RendezVous;

import tn.esprit.entities.User;
import tn.esprit.services.ServiceRendez;


/**
 *
 * @author SBS
 */
public class ListRendezForm extends Form{
    
    public static ArrayList<RendezVous>  listRDV = new ArrayList<>();
    ServiceRendez sp= ServiceRendez.getInstance();
 Container ctn, ctn1,ctn2;
 Resources th;
    public ListRendezForm(Resources theme,User user) {
        setTitle("Liste des Rendez-vous");
        getAllStyles().setBgColor(0xfff2e6);
        
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
                ctn2 = new Container(BoxLayout.x());

        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<RendezVous > filtred_categories = new ArrayList<>();
              for (RendezVous rendez : listRDV){
                  if (rendez.getNomRDV().startsWith(filter.getText()))
                      filtred_categories.add(rendez);
              }
              ctn.removeAll();
              setPlannings(filtred_categories,user,theme);
              
              revalidate();
          });
             Button add = new Button("Add New Rendez-vous");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new addRDVForm(theme,user).show();
            }
        });
        add.setVisible(false);
        
        if((user.getRoles().contains("ROLE_ADMIN")) || (user.getRoles().contains("ROLE_SECRETAIRE")) || (user.getRoles().contains("ROLE_PATIENT")) ){
            add.setVisible(true);
        }
          ctn1.add(filter);
          ctn1.add(add);
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listRDV=sp.getAllRDV();
   setPlannings(listRDV,user,theme);

      add(ctn1);
         add(ctn);
         
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
    }
      private void setPlannings(ArrayList<RendezVous> listRDV,User user,Resources theme) {
    
       for (RendezVous rdv :listRDV ){
     
          MultiButton mb= new MultiButton(rdv.getNomRDV());
             
          final FontImage placeholderImage =FontImage.createMaterial(FontImage.MATERIAL_ARTICLE,"label", 6);
           
          mb.setIcon(placeholderImage);
          
          mb.addActionListener( (evt) -> {
           
         
        
       }
       
       );
        ctn.add(mb);
         Button updateButton = new Button("modifier");
       
       Button btn_supprimer = new Button("supprimer");
       Button btn_show = new Button("SHOW");
         
         ctn.add(updateButton);
         ctn.add(btn_supprimer);
         ctn.add(btn_show);
         
         
        btn_supprimer.addActionListener( (evt) -> {
            if(Dialog.show("voulez vous supprimer cette RDV?", "", "oui", "Non")){
               //  sp categorieService = new CategorieService();
         sp.delRDV(rdv.getId());
          ctn.removeComponent(mb);
          ctn.removeComponent(updateButton);
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
          
       }  );
        btn_show.addActionListener( (evt) -> {
           new showRDVForm(rdv,user ,theme).show();
          // new showRDVForm(rdv, user)
          
       }  );
        
       
     /*   updateButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 
                  new UpdateCategorie(th, categorie,user).show();
              }
          });*/
        
       }
    }
    
    
}