/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.gui.MenuChambre;
import tn.esprit.gui.MenuCategory;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.entities.Users;

/**
 *
 * @author amina
 */
public class MenuCategoryChambre extends Form{
    Form current;

    public MenuCategoryChambre() {
                current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
                Users u = new Users(1, "amina");

        //BUTTONS
        add(new Label("Choisissez une option"));
        Button btnAide = new Button("Gestions des Chambres");
        Button btnCat = new Button("Gestions des Category");
        
        btnAide.addActionListener(e-> new MenuChambre(u).show());
        btnCat.addActionListener(e-> new MenuCategory(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Category", null, e -> new MenuCategory(u).show());

        addAll(btnAide,btnCat);

    }

}

