/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieService;
import tn.esprit.services.ConsultationService;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class UpdateConsultation extends Form {

    public UpdateConsultation(Resources theme,Consultation consultation,User user) {
        setTitle("modifier consultation");
         getToolbar().addCommandToRightBar("retour", null, (ev)->{new ListConsultations(theme,user).show();
          });
        Categorie cat = new Categorie();
        User usss = new User();
       setTitle("modifier une consultation");
          Label titre = new Label("titre");
        TextField txt_titre = new TextField(consultation.getTitre());
        Label label_description = new Label("description");
        TextField txt_description = new TextField(consultation.getDescription());
        CategorieService categorieService = new CategorieService();
        UserService userService = new UserService();
        ComboBox<Categorie> categories= new ComboBox<>();
        for (Categorie categorie : categorieService.getAll()){
            categories.addItem(categorie);
            if(categorie.getId_categorie()==consultation.getId_categorie()){
                cat = categorie;
            }
        }
         ComboBox<User> patients= new ComboBox<>();
        for (User us : userService.getAll()){
            if(us.getRoles().contains("ROLE_PATIENT")){
                  patients.addItem(us);
                  if(us.getId()==consultation.getId_user()){
                      usss= us;
                  }
            }
          
        }
        patients.setSelectedItem(usss);
       categories.setSelectedItem(cat);
        Button modifButton = new Button("modifier");
                Container cnt = BoxLayout.encloseY(titre, BorderLayout.center(txt_titre),label_description,BorderLayout.center(txt_description),categories,patients,modifButton);
        modifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                consultation.setTitre(txt_titre.getText());
                consultation.setDescription(txt_description.getText());
                consultation.setId_categorie(categories.getSelectedItem().getId_categorie());
                consultation.setId_user(patients.getSelectedItem().getId());
                ConsultationService consultationService = new ConsultationService();
                consultationService.updateConsultation(consultation);
                new ListConsultations(theme,user).show();
            }
        });
        add(cnt);
    }
    
    
}
