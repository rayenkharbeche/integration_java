/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
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
import tn.esprit.entities.User;
import tn.esprit.services.CategorieService;

/**
 *
 * @author rayen
 */
public class AddCategorie extends Form {

    public AddCategorie(Resources theme,User user) {
       
        setTitle("ajotuter une categorie");
        Label label_type = new Label("type catégorie");
        TextField txt_type = new TextField("", "type");
        Button add = new Button("ajouter");
       Container by = BoxLayout.encloseY(
               label_type,
                BorderLayout.center(txt_type),
                add
        );
      
        getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
       add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Categorie categorie = new Categorie();
                categorie.setType_categorie(txt_type.getText());               
                CategorieService categorieService = new CategorieService();
                System.out.println(txt_type.getText());
                if(categorieService.existe(txt_type.getText())==null){
                    categorieService.ajoutCategorie(categorie);
                    new ListCategories(theme, user).show();
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
    add(by);
    }
    
}
