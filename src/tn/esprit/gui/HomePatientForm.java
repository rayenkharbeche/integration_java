/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
import tn.esprit.entities.User;

/**
 *
 * @author rayen
 */
public class HomePatientForm extends Form {

    public HomePatientForm(Resources theme,User user) {
         super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setTitle("Accueil");
        Button b = new Button("Modifier mon profil");
       
       
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new UpdatePatientMyProfil(theme, user).show();
            }
        });
        Button c = new Button("mes consultations");
      
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MesConsultations(theme, user).show();
            }
        });
         Button or = new Button("Liste des Ordonnances");
        or.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListOrdonnances().show();
            }
        });
         Button rdv= new Button("liste des Rendez-vous");
        rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRendezForm(theme, user).show();
            }
        });
        
         Button h = new Button("se déconnecter");
        h.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new LoginForm(theme).show();
            }
        });
             Container by = BoxLayout.encloseY(b,c,rdv,h);
             add(BorderLayout.CENTER,by) ;
        
    }

}
