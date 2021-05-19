/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Chambre;

import com.mycompany.myapp.entities.gui.Category.*;
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
import com.mycompany.myapp.entities.Chambre;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.MenuCategoryChambre;
import com.mycompany.myapp.entities.services.ServiceCategory;
import com.mycompany.myapp.entities.services.ServiceChambre;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author amina
 */
public class ListCham extends Form{
        Form current;

        public ListCham(User u) {
        setTitle("Liste des Chambre");
        

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Chambre> chm = new ArrayList();
                        ServiceChambre sa =new ServiceChambre();
                    chm=sa.getAllchambres();
                             for (Chambre fi : chm) {
                            Container ct = new Container(BoxLayout.y());
                            Label l = new Label("Num : "+fi.getNum());
                            Label l2 = new Label("Numero etage : "+fi.getNumetage(),"SmallLabel");
                            Label l4 = new Label("Nombre de place : "+fi.getNbrplace(),"RedLabel");
                            Label l5= new Label("Service : "+fi.getService(),"RedLabel");
                            Label l6= new Label("Bloc : "+fi.getBloc());
                            Label l7= new Label("Category : "+fi.getCategory(),"RedLabel");
                            Label l8= new Label("Etat : "+fi.getEtat(),"RedLabel");
                            Label l9= new Label("Traitement : "+fi.getTraitement(),"RedLabel");

                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l4);
                            ct.add(l5);
                            ct.add(l6);
                            ct.add(l7);
                            ct.add(l8);
                            ct.add(l9);
                            
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
