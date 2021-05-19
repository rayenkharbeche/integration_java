/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Category;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.mycompany.myapp.entities.gui.Chambre.MenuChambre;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.MenuCategoryChambre;
import com.mycompany.myapp.entities.services.ServiceCategory;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amina
 */
public class Listcat extends Form{
        Form current;

        public Listcat(User u) {
        setTitle("Liste des Category");
        

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Category> cats = new ArrayList();
                        ServiceCategory sa =new ServiceCategory();
                    cats=sa.getAllCat();
                             for (Category fi : cats) {
                            Container ct = new Container(BoxLayout.y());
                            Label l = new Label("Id : "+fi.getId());
                            Label l2 = new Label("Nom : "+fi.getNom(),"SmallLabel");
                            Label l4 = new Label("Description: "+fi.getDescription(),"RedLabel");

                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l4);
                            
                            Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                             modfier.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette Category ?", "Modifier ", "Annuler")) {
                    new EditCat(current, fi,u).show();
            }    
            }
        });
                            Supp.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette Category ?", "Supprimer", "Annuler")) {
                Category t = new Category(fi.getId());
                        if( ServiceCategory.getInstance().deleteCat(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new Listcat(u).show();
                            }
                   
                }
            }    
            }
        });
                       ct.add(modfier);
                       ct.add(Supp);
                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
                             }
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Categorys", null, e -> new MenuCategory(u).show());
    }

    
}
