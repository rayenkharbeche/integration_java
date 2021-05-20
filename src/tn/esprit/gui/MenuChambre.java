/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.entities.Users;
import tn.esprit.gui.MenuCategory;
import tn.esprit.gui.MenuCategoryChambre;

/**
 *
 * @author amina
 */
public class MenuChambre extends Form{
    Form current;

    public MenuChambre(Users u) {
                current=this;
        setTitle("Gestions des Chambres");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutChambre = new Button("Ajouter Chambre");
        Button btnListChambres = new Button("Liste des Chambre");
        Button btnListChambresmed = new Button("Liste des Chambre medecin");
        Button btnStat= new Button("Stat");
        
        btnAjoutChambre.addActionListener(e-> new AjoutChambre(current,u).show());
        btnListChambres.addActionListener(e-> new ListChambre(u).show());
        btnListChambresmed.addActionListener(e-> new ListCham(u).show());
        btnStat.addActionListener(e-> new StatChambre(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Categorys", null, e -> new MenuCategory(u).show());

        addAll(btnAjoutChambre,btnListChambres,btnListChambresmed,btnStat);
            
    }

}

