/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Services.Service;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASSOUMA
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
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_NOTIFICATIONS_ACTIVE, e -> {
               
            Notifications ap = new Notifications();
            ap.show();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           
            Button Products = new Button("Espace Medicaments");
          
            Button Events = new Button("Espace Ordonnances");
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            
            photos.add(Products);
            photos.add(Events);
            
            add(photos);
            
            Products.addActionListener(e->{
                ListMedicaments lp = new ListMedicaments();
                lp.show();
            });
            Events.addActionListener(e->{
                ListOrdonnances lp = new ListOrdonnances();
                lp.show();
            });
           
                
           
      
        show();
    }
}
