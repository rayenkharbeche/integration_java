/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Medicaments;
import Services.Service;
import Utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class AddMedicament extends BaseForm{
    ComboBox<String> c;
    public AddMedicament(){
        c = new ComboBox();
        setName("Ajouter Medicament");
        setTitle("Ajouter Medicament");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListMedicaments lpa = new ListMedicaments();
            lpa.show();
        });
       
         
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            TextField nom = new TextField();
            nom.setHint("Nom");
            TextField marque = new TextField();
            marque.setHint("code");
            TextField prix = new TextField();
            prix.setHint("Prix");
           TextField sotck = new TextField();
            sotck.setHint("Stock");
           
            
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("categories");
            Service ds = new Service();
           ArrayList<String> listCategorie = ds.getListCategories(x);
           for (String e : listCategorie) {
               c.addItem(e);
           }
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(marque);
           photos.add(prix);
           
            
            
      
            
            photos.add(sotck);
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                Medicaments p = new Medicaments();
                p.setName(nom.getText());
                p.setPrix(Double.parseDouble(prix.getText()));
                p.setCode(Double.parseDouble(marque.getText()));
                p.setStock(Double.parseDouble(sotck.getText()));
                p.setCategorie(c.getSelectedItem());
                ws.addMediacament(p);
                ListMedicaments lp = new ListMedicaments();
                lp.show();
                });
            /**c.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                        
                    }

                }
            });**/
           
      
        show();
    }
    
}
