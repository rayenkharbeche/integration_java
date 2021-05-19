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
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.Base64;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class AddFichiers extends BaseForm{
    ComboBox<String> c;
    ComboBox<String> c1;
    private String im ;
    public static final String ACCOUNT_SID = "ACd5a3841bd28dadbe5f9f6e82ba8885ba";
    public static final String AUTH_TOKEN ="b8a3469ab52fae7b58ce71a8b4a61267";
    public AddFichiers(){
        c = new ComboBox();
        c1 = new ComboBox();
        setName("Ajouter Fichier");
        setTitle("Ajouter Fichier");
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
            nom.setHint("Description");
           
           
           
            
          
            Button b = new Button("Ajouter");
            Button img = new Button("Ajouter une image",icone);
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("API/listdossiers");
            Service ds = new Service();
           ArrayList<Dossier> listCategorie = ds.getListdossier(x);
           for (Dossier e : listCategorie) {
               c.addItem(e.getId()+"");
           }
           Map x1 = ws.getResponse("API/listMedecins");
           
           ArrayList<String> listMedecins = ds.getListMedecins(x1);
           for (String e : listMedecins) {
               c1.addItem(e);
           }
           photos.add(nom);
           photos.add(l);
           photos.add(c);
           photos.add(l2);
           photos.add(c1);
           
           
            
            
      
            
            
            photos.add(b);
            photos.add(img);
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
                Fichier p = new Fichier();
                p.setDescription(nom.getText());
                p.setMedecin(c1.getSelectedItem());
                p.setDossier(c.getSelectedItem());
                p.setImage(im);
                ws.addFichier(p);
                
                 Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json").
        queryParam("To", "+21627020022").
        queryParam("From", "+12052933938").
        queryParam("Body", "Un fichier a été ajouté").
        header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
        getAsJsonMap();
                ListFichiers lp = new ListFichiers();
                lp.show();
                });

           
      
        show();
    }
    
}
