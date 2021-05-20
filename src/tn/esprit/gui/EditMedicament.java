/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import Entities.Medicaments;
import tn.esprit.services.Service;
import tn.esprit.utils.WebService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class EditMedicament extends BaseForm{
    public static Medicaments m;
    ComboBox<String> c;
    public EditMedicament(){
        c = new ComboBox();
        setName("Ajouter place");
        setTitle("Ajouter place");
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
            nom.setText(m.getName());
            TextField marque = new TextField();
            marque.setText(m.getCode()+"");
            TextField prix = new TextField();
            prix.setText(m.getPrix()+"");
           TextField sotck = new TextField();
            sotck.setText(m.getStock()+"");
           
            
          
            Button b = new Button("Edit");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("categories");
            Service ds = new Service();
           ArrayList<String> listCategorie = ds.getListCategories(x);
           for (String e : listCategorie) {
               c.addItem(e);
           }
           c.setSelectedItem(m.getCategorie());
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(marque);
           photos.add(prix);
           
            
            
      
            
            photos.add(sotck);
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                
                m.setName(nom.getText());
                m.setPrix(Double.parseDouble(prix.getText()));
                m.setCode(Double.parseDouble(marque.getText()));
                m.setStock(Double.parseDouble(sotck.getText()));
                m.setCategorie(c.getSelectedItem());
                ws.EditMediacament(m);
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
