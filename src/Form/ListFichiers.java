/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entities.Dossier;
import Entities.Fichier;
import Services.Service;
import Util.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
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
public class ListFichiers extends BaseForm{
    
    public ListFichiers(){
        setName("Fichiers");
    
        setTitle("Fichiers");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
             
            AddFichiers ap = new AddFichiers();
            ap.show();
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            Home ap = new Home();
            ap.show();
        });
         
           
             WebService ws = new WebService();
    Service ds = new Service();
    Map x = ws.getResponse("API/listFichiers");
        System.out.println(x);
    ArrayList<Fichier> listevents = ds.getListfichier(x);
             for (Fichier e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            Label b = new Label("Dossier : "+e.getDossier());
            
           Label Content = new Label("Medecin : "+e.getMedecin());
           Label description = new Label("Description : "+e.getDescription());
           
           Button voir = new Button("Edit");
            
           try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
           try{
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/uploads/13/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
           }catch(Exception ex){
               System.out.println(ex.getMessage());
           }
            
            
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
                    EditFichiers.f= e;
                    
                    EditFichiers d = new EditFichiers();
                    d.show();
                    //ws.DeleteComment(e.getId());
                    //ListPlaces lp = new ListPlaces();
                    //lp.show();
                   //EventDetails.e = e ;
                   //EventDetails pd = new EventDetails();
                   //pd.show();
                }
            });
        }
        show();
    }
}
