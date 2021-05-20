/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieService;

/**
 *
 * @author rayen
 */
public class UpdateCategorie extends Form {

    public UpdateCategorie(Resources theme,Categorie categorie,User user) {
        setTitle("modifier categorie");
      
        Label label_categorie = new Label("type categorie");
        TextField txt = new TextField(categorie.getType_categorie());
        Button modifButton = new Button("modifier");
         ComboBox<User> patients= new ComboBox<>();
           
        Container cnt = BoxLayout.encloseY(label_categorie, BorderLayout.center(txt),modifButton);
        modifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                categorie.setType_categorie(txt.getText());
                CategorieService categorieService = new CategorieService();
                categorieService.updateCategorie(categorie);
                new ListCategories(theme,user).show();
            }
        });
        add(cnt);
    }
    
    
}
