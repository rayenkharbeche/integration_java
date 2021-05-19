/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entities.Dossier;
import Services.Service;
import Util.WebService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class DossierDetails extends BaseForm{
    
    public static Dossier d ;
    public DossierDetails(){
        setName("Détails");
    
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            ListDossiers ap = new ListDossiers();
            ap.show();
        });
         
           
             WebService ws = new WebService();
    Service ds = new Service();
    
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            
            Label b = new Label("Patient : "+d.getPatient());
            
           Label Content = new Label("Date creation : "+d.getDate());
           Label description = new Label("Description : "+d.getDescription());
           
           Button voir = new Button("Edit");
            
           
            
            photos.add(b);
            photos.add(Content);
            photos.add(description);
            photos.add(voir);
            
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
            
            voir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    
                   EditDossier.p = d ;
                   EditDossier pd = new EditDossier();
                   pd.show();
                }
            });
        
        show();
    }
}
