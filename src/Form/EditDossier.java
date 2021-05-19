/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entities.Dossier;
import Services.Service;
import Util.WebService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class EditDossier extends BaseForm{
    public static Dossier p ;
    ComboBox<String> c;
    public EditDossier(){
        c = new ComboBox();
        setName("Ajouter Dossier");
        setTitle("Ajouter Dossier");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListDossiers lpa = new ListDossiers();
            lpa.show();
        });
       
         
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Patient");
            TextField nom = new TextField();
            nom.setText(p.getDescription());
            Label date = new Label("Date Naissance");
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
           
           
            
          
            Button b = new Button("Edit");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("API/listPatients");
            Service ds = new Service();
           ArrayList<String> listCategorie = ds.getListPatients(x);
           for (String e : listCategorie) {
               c.addItem(e);
           }
           c.setSelectedItem(p.getPatient());
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(date);
           photos.add(datePicker);
           
            
            
      
            
            
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                
                p.setDescription(nom.getText());
                p.setPatient(c.getSelectedItem());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               
               Date da = datePicker.getDate();
              
               String st1 = df.format(da);
               p.setDate(st1);
                ws.EditDossier(p);
                ListDossiers lp = new ListDossiers();
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
