/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Category;

import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ComponentStateChangeEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.services.ServiceCategory;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wajih
 */
public class EditCat extends Form{
    
        public EditCat(Form previous,Category cat,User u) {
        setTitle("Modifier Category");
        setLayout(BoxLayout.y());
        Label l = new Label("Category :");

        TextComponent tfNom= new TextComponent().label("Nom :");
        tfNom.text(cat.getNom());
        TextComponent tfDesc= new TextComponent().label("Description :");
        tfDesc.text(cat.getDescription());

        Button btnValider = new Button("Modifier Category");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfDesc.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {

                        Category e= new Category(tfNom.getText(), tfDesc.getText(),"");
                        e.setId(cat.getId());

                        if( ServiceCategory.getInstance().editCat(e))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(l,tfNom,tfDesc,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new Listcat(u).show());
                
    }

   
}
