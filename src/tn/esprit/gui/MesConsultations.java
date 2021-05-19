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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.ConsultationService;

/**
 *
 * @author rayen
 */
public class MesConsultations extends Form {
    public static ArrayList<Consultation>  listConsultations = new ArrayList<>();
 public static ConsultationService consultationService=new ConsultationService();
 Container ctn, ctn1,ctn2;
 Resources th;
 User us = new User();
    public MesConsultations(Resources theme, User user) {
        setTitle("Mes Consultations");
        us=user; 
        getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
                ctn2 = new Container(BoxLayout.x());

        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<Consultation > filtred_consultations = new ArrayList<>();
              for (Consultation consultation : listConsultations){
                  if(consultation.getId_user()==user.getId()){
                       if (consultation.getTitre().startsWith(filter.getText()))
                      filtred_consultations.add(consultation);
                  }
                 
              }
              ctn.removeAll();
              setConsultations(filtred_consultations);
              
              revalidate();
          });
          ctn1.add(filter);
  
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listConsultations=consultationService.getAll();
   setConsultations(listConsultations);

      add(ctn1);
         add(ctn);
         
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomePatientForm(theme,user).show();
          });
        
    }
      private void setConsultations(ArrayList<Consultation> listConsultations) {
    
       for (Consultation consultation :listConsultations ){
     if(consultation.getId_user()==us.getId()){
          MultiButton mb= new MultiButton(consultation.getTitre());
             
          final FontImage placeholderImage =FontImage.createMaterial(FontImage.MATERIAL_ARTICLE,"label", 6);
           
          mb.setIcon(placeholderImage);
          
       mb.addActionListener( (evt) -> {
           
         
        
       }
       
       );
        ctn.add(mb);
        
       
       Button btn_supprimer = new Button("supprimer");
         
         
         ctn.add(btn_supprimer);
         
         
        btn_supprimer.addActionListener( (evt) -> {
            if(Dialog.show("voulez vous supprimer cette consultation?", "", "oui", "Non")){
                 ConsultationService consultationService = new ConsultationService();
          consultationService.deleteConsultation(consultation.getId_consultation());
          ctn.removeComponent(mb);
        
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
          
       }  );
        
       
       
        
       }}
    }
    
    
}
