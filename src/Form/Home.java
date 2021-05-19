/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author Jasser
 */
public class Home extends BaseForm{
    
    public Home(){
         setName("Home");
        setTitle("Home");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           
            Button Products = new Button("Espace Dossiers");
          
            Button Events = new Button("Espace Fichiers");
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            
            photos.add(Products);
            photos.add(Events);
            
            add(photos);
            
            Products.addActionListener(e->{
                ListDossiers lp = new ListDossiers();
                lp.show();
            });
            Events.addActionListener(e->{
                ListFichiers lp = new ListFichiers();
                lp.show();
            });
           
                
           
      
        show();
    }
}
