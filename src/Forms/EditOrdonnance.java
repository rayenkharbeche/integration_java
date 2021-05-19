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
public class EditOrdonnance extends BaseForm{
    public static Ordonnance o;
     ComboBox<String> medicament;
    ComboBox<String> categorie;
    ComboBox<String> consultation;
    ComboBox<String> patient;
    public EditOrdonnance(){
        medicament = new ComboBox();
        categorie = new ComboBox();
        consultation = new ComboBox();
        patient = new ComboBox();
        setName("Edit Ordonnance");
        setTitle("Edit Ordonnance");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListOrdonnances lpa = new ListOrdonnances();
            lpa.show();
        });
       
         
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            Label medicaments = new Label("Médicaments");
            Label consultations = new Label("Consultation");
            Label patients = new Label("Patient");
            TextField nom = new TextField();
            nom.setText(o.getDescription());
            TextField marque = new TextField();
            marque.setText(o.getNbrJours()+"");
            TextField prix = new TextField();
            prix.setText(o.getNbrDoses()+"");
           TextField sotck = new TextField();
            sotck.setText(o.getNbrFois()+"");
           TextField packets = new TextField();
            packets.setText(o.getNbrPaquets()+"");
            
          
            Button b = new Button("Edit");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("categories");
            Service ds = new Service();
           ArrayList<String> listCategorie = ds.getListCategories(x);
           for (String e : listCategorie) {
               categorie.addItem(e);
           }
           categorie.setSelectedItem(o.getCategorie());
           Map x1 = ws.getResponse("Consultations");
            
           ArrayList<String> listConsultations = ds.getListConsultations(x1);
           for (String e : listConsultations) {
               consultation.addItem(e);
           }
           consultation.setSelectedItem(o.getConsultation());
           Map x2 = ws.getResponse("Patients");
            
           ArrayList<String> listPatients = ds.getListPatients(x2);
           for (String e : listPatients) {
               patient.addItem(e);
           }
           patient.setSelectedItem(o.getPatient());
           Map x4 = ws.getResponse("ListMedicaments");
        System.out.println(x);
    ArrayList<Medicaments> listevents = ds.getListBlog(x4);
             for (Medicaments e : listevents) {
                 medicament.addItem(e.getName());
             }
             medicament.setSelectedItem(o.getMedicaments());
           photos.add(nom);
           photos.add(l);
           photos.add(categorie);
           photos.add(medicaments);
           photos.add(medicament);
           photos.add(consultations);
           photos.add(consultation);
           photos.add(patients);
           photos.add(patient);
           photos.add(marque);
           photos.add(prix);
           
            
            
      
            
            photos.add(sotck);
            photos.add(packets);
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                
                o.setCategorie(categorie.getSelectedItem());
                o.setConsultation(consultation.getSelectedItem());
                o.setDescription(nom.getText());
                o.setMedicaments(medicament.getSelectedItem());
                o.setNbrDoses(Double.parseDouble(prix.getText()));
                o.setNbrFois(Double.parseDouble(sotck.getText()));
                o.setNbrJours(Double.parseDouble(marque.getText()));
                o.setNbrPaquets(Double.parseDouble(packets.getText()));
                o.setPatient(patient.getSelectedItem());
                ws.EditOrdonnance(o);
                ListOrdonnances lp = new ListOrdonnances();
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
