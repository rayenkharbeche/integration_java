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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.User;

/**
 *
 * @author rayen
 */
public class HomeForm extends Form {

    public HomeForm(Resources theme,User user) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setTitle("Accueil");
         Button b = new Button("Modifier mon profil");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new UpdateMyProfil(theme, user).show();
            }
        });
        Button c = new Button("ajouter consultation");
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddConsultation(theme, user).show();
            }
        });
        Button d = new Button("liste des consultations");
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListConsultations(theme, user).show();
            }
        });
        Button e = new Button("ajouter categorie");
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddCategorie(theme, user).show();
            }
        });
        e.setVisible(false);
        
        if(user.getRoles().contains("ROLE_ADMIN")){
            e.setVisible(true);
        }
         Button f = new Button("Liste des categories");
        f.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListCategories(theme, user).show();
            }
        });
          f.setVisible(false);
        
        if(user.getRoles().contains("ROLE_ADMIN")){
            f.setVisible(true);
        }
        
         Button g = new Button("Liste des utilisateurs");
        g.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListUsers(theme, user).show();
            }
        });
        g.setVisible(false);
        
        if(user.getRoles().contains("ROLE_ADMIN")){
            g.setVisible(true);
        }
        
         Button h = new Button("se déconnecter");
        h.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new LoginForm(theme).show();
            }
        });
         Container by = BoxLayout.encloseY(b,c,d,e,f,g,h);
             add(BorderLayout.CENTER,by) ;
    }
    
    
}
