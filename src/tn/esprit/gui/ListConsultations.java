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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.ConsultationService;

/**
 *
 * @author rayen
 */
public class ListConsultations extends Form{

   public static ArrayList<Consultation>  listConsultations = new ArrayList<>();
 public static ConsultationService consultationService=new ConsultationService();
 Container ctn, ctn1,ctn2;
 Resources th;
    public ListConsultations(Resources theme, User user) {
        setTitle("Liste des consultations");
          getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
                ctn2 = new Container(BoxLayout.x());
                 Button pdf=new Button("pdf");
  
 pdf.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                        String path="";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"consultation.pdf"));
           document.open();
          PdfPTable tb1 = new PdfPTable(3);
          tb1.addCell("Titre");
          tb1.addCell("Date Creation");
          tb1.addCell("Description");
          
        
        ArrayList<Consultation> list = consultationService.getAll();
          for (Consultation cons : list) {
            
              String titre= cons.getTitre();
              String date= cons.getDate_creation();
              String description= cons.getDescription();
              
              
          tb1.addCell(titre);
          tb1.addCell(date);
          tb1.addCell(description);
         
         
          }
         document.add(new Paragraph("Consultation"));
         document.add(tb1);
         document.close();
         writer.close();
        com.codename1.io.File file=new com.codename1.io.File("consultation.pdf");
        //desktop.open(file);
      } catch (DocumentException e)
      {
         e.printStackTrace();
      }catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
                        //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                     
                    }
                    
                }
                
                
                );

        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<Consultation > filtred_consultations = new ArrayList<>();
              for (Consultation consultation : listConsultations){
                  if (consultation.getTitre().startsWith(filter.getText()))
                      filtred_consultations.add(consultation);
              }
              ctn.removeAll();
              setConsultations(filtred_consultations,user);
              
              revalidate();
          });
          ctn1.add(filter);
  
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listConsultations=consultationService.getAll();
   setConsultations(listConsultations,user);
add(pdf);
      add(ctn1);
         add(ctn);
         
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
    }
      private void setConsultations(ArrayList<Consultation> listConsultations,User user) {
    
       for (Consultation consultation :listConsultations ){
     
          MultiButton mb= new MultiButton(consultation.getTitre());
             
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
            if(Dialog.show("voulez vous supprimer cette consultation?", "", "oui", "Non")){
                 ConsultationService consultationService = new ConsultationService();
          consultationService.deleteConsultation(consultation.getId_consultation());
          ctn.removeComponent(mb);
          ctn.removeComponent(updateButton);
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
          
       }  );
        
       
        updateButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 
                  new UpdateConsultation(th, consultation,user).show();
              }
          });
        
       }
    }
    
    
    
}
