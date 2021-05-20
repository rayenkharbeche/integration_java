/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieService;
import tn.esprit.services.ConsultationService;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class AddConsultation extends Form{

    public AddConsultation(Resources theme,User user) {
        setTitle("ajouter une consultation");
         getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
          Label titre = new Label("titre");
        TextField txt_titre = new TextField("","titre");
        Label label_description = new Label("description");
        TextField txt_description = new TextField("", "description");
        CategorieService categorieService = new CategorieService();
        UserService userService = new UserService();
        ComboBox<Categorie> categories= new ComboBox<>();
        for (Categorie categorie : categorieService.getAll()){
            categories.addItem(categorie);
        }
       ComboBox<User> patients= new ComboBox<>();
        for (User us : userService.getAll()){
            if(us.getRoles().contains("ROLE_PATIENT")){
                  patients.addItem(us);
            }
          
        }
       
        Button button_add = new Button("ajouter");
                Container cnt = BoxLayout.encloseY(titre, BorderLayout.center(txt_titre),label_description,BorderLayout.center(txt_description)
                        ,categories,patients,button_add);

        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConsultationService consultationService = new ConsultationService();
                Consultation consultation = new Consultation();
                consultation.setId_user(patients.getSelectedItem().getId());
              consultation.setTitre(txt_titre.getText());
               consultation.setId_categorie(categories.getSelectedItem().getId_categorie());
               consultation.setDescription(txt_description.getText());
               if(consultationService.existe(txt_titre.getText())==null){
              consultationService.ajoutConsultation(consultation);
              new ListConsultations(theme, user).show();
               }
               else {
                           Dialog dlg = new Dialog("Verification");
             //   dlg.setLayout(new FlowLayout(CENTER, CENTER) );
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

                Label title = dlg.getTitleComponent();
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.CENTER);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Verifier vos champs svp !!");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
   
    ok.getAllStyles().setFgColor(0xff);
    dlg.add(ok);
    dlg.showDialog();
               }
            }
        });
        add(cnt);

    }
    
}
