/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Ordonnance;
import static Forms.MedicamentDetails.m;
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

/**
 *
 * @author ASSOUMA
 */
public class OrdonnanceDetails extends BaseForm{
    public static Ordonnance o ;
    public OrdonnanceDetails(){
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListOrdonnances lp = new ListOrdonnances();
            lp.show();
        });
           
           
       
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            Label ref = new Label("Patient : "+o.getPatient());
            Label desc = new Label("Medicament : "+o.getMedicaments());
            Label classe = new Label("Consultation : "+o.getConsultation());
            Label q = new Label("Catégorie : "+o.getCategorie());
            Label description = new Label("Description : "+o.getDescription());
            Label nbrJours = new Label("Nombre des jours : "+o.getNbrJours()+"");
            Label nbrDoses= new Label("Nombre des doses : "+o.getNbrDoses()+"");
            Label nbrfois = new Label("Nombre des Fois : "+o.getNbrFois()+"");
            Label nbrPaquets = new Label("Nombre des paquets : "+o.getNbrPaquets()+"");
            //Label souscat = new Label("Nombre des commentaires : "+p.getNbComments());
            //Label b = new Label("Date Debut - Date fin : "+e.getStart_date() +"-"+e.getEnd_date());
            
            
           
           Button voir = new Button("Edit");
           
           Button voir1 = new Button("Delete");
            
            
            photos.add(ref);
            //photos.add(b);
            photos.add(classe);
            photos.add(desc);
            photos.add(q);
            photos.add(description);
            photos.add(nbrJours);
            photos.add(nbrDoses);
            photos.add(nbrPaquets);
            photos.add(nbrfois);
           
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
                   EditOrdonnance.o = o;
                   EditOrdonnance em = new EditOrdonnance();
                   em.show();

                }
            });
            voir1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    ws.DeleteOrdonnance(o.getId());
                    ListOrdonnances lm = new ListOrdonnances();
                    lm.show();
                }
            });
        
        show();
    }
}
