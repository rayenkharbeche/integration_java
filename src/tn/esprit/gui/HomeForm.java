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
import tn.esprit.entities.Users;
import tn.esprit.gui.MenuChambre;


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
         Button t = new Button("Liste des médicaments");
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListMedicaments().show();
            }
        });
        t.setVisible(false);
        
        if((user.getRoles().contains("ROLE_PHARMACIEN")) || (user.getRoles().contains("ROLE_ADMIN"))){
            t.setVisible(true);
        }
        
         Button or = new Button("Liste des Ordonnances");
        or.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListOrdonnances().show();
            }
        });
        or.setVisible(false);
        
        if((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_ADMIN"))){
            or.setVisible(true);
        }
         Button cc = new Button("Liste Chambres");
        cc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Users u = new Users();
                new MenuChambre(u).show();
            }
        });
        cc.setVisible(false);
        
        if ((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_SECRETAIRE")) || (user.getRoles().contains("ROLE_ADMIN"))) {
            cc.setVisible(true);
        }
        
         Button ch = new Button("Category Chambres");
        ch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Users u = new Users();
                new MenuCategory(u).show();
            }
        });
        ch.setVisible(false);
        
        if ((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_SECRETAIRE"))|| (user.getRoles().contains("ROLE_ADMIN"))) {
            ch.setVisible(true);
        }
         Button plan = new Button("liste des Plannings");
        plan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListPlanning(theme, user).show();
            }
        });
         Button rdv= new Button("liste des Rendez-vous");
        rdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRendezForm(theme, user).show();
            }
        });
        
        rdv.setVisible(false);
        
        if ((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_SECRETAIRE"))|| (user.getRoles().contains("ROLE_ADMIN")) ) {
            rdv.setVisible(true);
        }
        
         Button fichier= new Button("liste des fichiers");
        fichier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRendezForm(theme, user).show();
            }
        });
        
        fichier.setVisible(false);
        
        if ((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_SECRETAIRE"))|| (user.getRoles().contains("ROLE_ADMIN"))) {
            fichier.setVisible(true);
        }
        
         Button doss= new Button("liste des dossiers");
        doss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRendezForm(theme, user).show();
            }
        });
        
        doss.setVisible(false);
        
        if ((user.getRoles().contains("ROLE_MEDECIN")) || (user.getRoles().contains("ROLE_SECRETAIRE"))|| (user.getRoles().contains("ROLE_ADMIN"))) {
            doss.setVisible(true);
        }
         Button h = new Button("se déconnecter");
        h.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new LoginForm(theme).show();
            }
        });
        Button stat = new Button("statistique");
        stat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                PieChartMobile p = new PieChartMobile();
                try {
                    p.createPieChartForm().show();
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });
        
         Container by = BoxLayout.encloseY(b,c,d,e,f,g,t,or,cc,ch,plan,rdv,fichier,doss,stat,h);
             add(BorderLayout.CENTER,by) ;
    }
    
    
}
