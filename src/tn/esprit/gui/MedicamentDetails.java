/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import Entities.Medicaments;
import tn.esprit.utils.WebService;
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

/**
 *
 * @author ASSOUMA
 */
public class MedicamentDetails extends BaseForm{
    public static Medicaments m;
    public MedicamentDetails(){
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListMedicaments lp = new ListMedicaments();
            lp.show();
        });
           
           
       
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            Label ref = new Label("Nom : "+m.getName());
            Label desc = new Label("Code : "+m.getCode());
            Label classe = new Label("Prix : "+m.getPrix());
            Label q = new Label("Stock : "+m.getStock());
            //Label souscat = new Label("Nombre des commentaires : "+p.getNbComments());
            //Label b = new Label("Date Debut - Date fin : "+e.getStart_date() +"-"+e.getEnd_date());
            
            
           
           Button voir = new Button("Edit");
           
           Button voir1 = new Button("Delete");
            
            
            photos.add(ref);
            //photos.add(b);
            photos.add(classe);
            photos.add(desc);
            photos.add(q);
            //photos.add(souscat);
            
           
             photos.add(voir);
              photos.add(voir1);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            
            
            voir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   EditMedicament.m = m;
                   EditMedicament em = new EditMedicament();
                   em.show();

                }
            });
            voir1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    ws.DeleteMediacament(m.getId());
                    ListMedicaments lm = new ListMedicaments();
                    lm.show();
                }
            });
        
        show();
    }
}
