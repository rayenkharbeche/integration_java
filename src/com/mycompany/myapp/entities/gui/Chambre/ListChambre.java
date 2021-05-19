/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Chambre;

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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Chambre;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Category.MenuCategory;
import com.mycompany.myapp.entities.gui.MenuCategoryChambre;
import com.mycompany.myapp.entities.services.ServiceChambre;
import java.util.ArrayList;

/**
 *
 * @author amin
 */
public class ListChambre extends Form{
        Form current;

        public ListChambre(User u) {
        setTitle("Liste des Chambres");
        
          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Chambre> chambres = new ArrayList();
                        ServiceChambre sa =new ServiceChambre();
                    chambres=sa.getAllchambres();
                             for (Chambre fi : chambres) {
                            MultiButton m = new MultiButton();
                            m.setText("Numero : "+String.valueOf(fi.getNum()));
                            m.setTextLine2("Nombre de place : "+fi.getNbrplace());
                            m.setTextLine3("Service : "+fi.getService()+"  Bloc : "+fi.getBloc());
                            m.setTextLine4("Category : "+fi.getCategory());
            //Click barka yfassa5
            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette chambre ?", "Supprimer", "Annuler")) {
                Chambre t = new Chambre(fi.getNum());
                        if( ServiceChambre.getInstance().deleteChambre(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListChambre(u).show();
                            }
                   
                }
            }    
            }
        });
            //Long Click ymodifi
            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette Aide ?", "Modifier ", "Annuler")) {
                    new EditChambre(current, fi,u).show();
                }    
            }
        });

                            add(m);
                             }
                     revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        getToolbar().addCommandToSideMenu("Gestions des Chambres", null, e -> new MenuChambre(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Categorys", null, e -> new MenuCategory(u).show());
    }

    
}
