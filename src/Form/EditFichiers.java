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
 * @author Jasser
 */
public class EditFichiers extends BaseForm{
    ComboBox<String> c;
    ComboBox<String> c1;
    private String im ;
    public static Fichier f;
    public EditFichiers(){
        im=f.getImage();
        c = new ComboBox();
        c1 = new ComboBox();
        setName("Edit Fichier");
        setTitle("Edit Fichier");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListFichiers lpa = new ListFichiers();
            lpa.show();
        });
       
         
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Dossier");
            Label l2 = new Label("Medecins");
            TextField nom = new TextField();
            nom.setText(f.getDescription());
           
           
           
            
          
            Button b = new Button("Edit");
            Button img = new Button("Ajouter une image",icone);
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("API/listdossiers");
            Service ds = new Service();
           ArrayList<Dossier> listCategorie = ds.getListdossier(x);
           for (Dossier e : listCategorie) {
               c.addItem(e.getId()+"");
           }
           c.setSelectedItem(f.getDossier());
           Map x1 = ws.getResponse("API/listMedecins");
           
           ArrayList<String> listMedecins = ds.getListMedecins(x1);
           for (String e : listMedecins) {
               c1.addItem(e);
           }
           c1.setSelectedItem(f.getMedecin());
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(l2);
           photos.add(c1);
           
           
            
            
      
            
            photos.add(img);
            photos.add(b);
            
            add(photos);
            img.addActionListener(e->{
                
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/Service/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
            });
            b.addActionListener(e->{
                
                f.setDescription(nom.getText());
                f.setMedecin(c1.getSelectedItem());
                f.setDossier(c.getSelectedItem());
                f.setImage(im);
                ws.EditFichier(f);
                ListFichiers lp = new ListFichiers();
                lp.show();
                });

           
      
        show();
    }
}
