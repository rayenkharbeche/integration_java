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
import tn.esprit.entities.Planning;
import tn.esprit.entities.User;
import tn.esprit.services.ServicePlanning;

/**
 *
 * @author SBS
 */
public class ListPlanning extends Form{
    
    public static ArrayList<Planning>  listPlannings = new ArrayList<>();
 ServicePlanning sp= ServicePlanning.getInstance();
 Container ctn, ctn1,ctn2;
 Resources th;
    public ListPlanning(Resources theme,User user) {
        setTitle("Liste des Plannings");
        getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
                ctn2 = new Container(BoxLayout.x());

        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<Planning > filtred_categories = new ArrayList<>();
              for (Planning planning : listPlannings){
                  if (planning.getNomP().startsWith(filter.getText()))
                      filtred_categories.add(planning);
              }
              ctn.removeAll();
              setPlannings(filtred_categories,user);
              
              revalidate();
          });
          ctn1.add(filter);
  
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listPlannings=sp.getAllPlanning();
   setPlannings(listPlannings,user);

      add(ctn1);
         add(ctn);
         
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
    }
      private void setPlannings(ArrayList<Planning> listPlanings,User user) {
    
       for (Planning planning :listPlannings ){
     
          MultiButton mb= new MultiButton(planning.getNomP());
             
          final FontImage placeholderImage =FontImage.createMaterial(FontImage.MATERIAL_ARTICLE,"label", 6);
           
          mb.setIcon(placeholderImage);
          
          mb.addActionListener( (evt) -> {
           
         
        
       }
       
       );
        ctn.add(mb);
         Button updateButton = new Button("modifier");
       
       Button btn_supprimer = new Button("supprimer");
         
         ctn.add(updateButton);
         ctn.add(btn_supprimer);
         
         
        btn_supprimer.addActionListener( (evt) -> {
            if(Dialog.show("voulez vous supprimer cette Planning?", "", "oui", "Non")){
               //  sp categorieService = new CategorieService();
         sp.delPlan(planning.getId());
          ctn.removeComponent(mb);
          ctn.removeComponent(updateButton);
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
          
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
