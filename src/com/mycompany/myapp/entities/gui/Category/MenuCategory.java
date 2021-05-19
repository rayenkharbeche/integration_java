/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Category;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Chambre.MenuChambre;
import com.mycompany.myapp.entities.gui.MenuCategoryChambre;

/**
 *
 * @author amina
 */
public class MenuCategory extends Form{
    Form current;

    public MenuCategory(User u) {
                current=this;
        setTitle("Gestions des categories");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutCat = new Button("Ajouter Category");
        Button btnListCat = new Button("Liste des Categorys");
        Button btnListCatClient = new Button("Liste des Categorys medecin");
        
        btnAjoutCat.addActionListener(e-> new AjoutCat(current,u).show());
        btnListCat.addActionListener(e-> new Listcat(u).show());
        btnListCatClient.addActionListener(e-> new ListCategory(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Categorys", null, e -> new MenuCategory(u).show());

        addAll(btnAjoutCat,btnListCat,btnListCatClient);

    }

}

