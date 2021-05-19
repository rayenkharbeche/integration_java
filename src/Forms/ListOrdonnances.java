/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Medicaments;
import Entities.Ordonnance;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class ListOrdonnances extends BaseForm{
    
    public ListOrdonnances(){
        setName("Ordonnances");
    
        setTitle("Ordonnances");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
             
            AddOrdonnance ap = new AddOrdonnance();
            ap.show();
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            Home ap = new Home();
            ap.show();
        });
         
           
             WebService ws = new WebService();
    Service ds = new Service();
    Map x = ws.getResponse("ListOrdonnances");
        System.out.println(x);
    ArrayList<Ordonnance> listevents = ds.getListord(x);
             for (Ordonnance e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Patient : "+e.getPatient());
            
           Label Content = new Label("Medicament : "+e.getMedicaments());
           
           
           Button voir = new Button("Détails");
            
           
            
            photos.add(b);
            photos.add(Content);
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
                    OrdonnanceDetails.o = e;
                    OrdonnanceDetails md = new OrdonnanceDetails();
                    md.show();
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
