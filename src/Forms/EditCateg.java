/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Catégorie;
import Services.ServiceCateg;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASSOUMA
 */
public class EditCateg extends Form {
    
       public EditCateg(Form previous,Catégorie cat) {
        setTitle("Modifier une Catégorie");
        setLayout(BoxLayout.y());
        
        TextComponent tfCateg= new TextComponent().label("Nom de Catégorie:");
       
        tfCateg.text(cat.getNom());
       
        Button btnValider = new Button("Update");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfCateg.getText().length()==0))
                    Dialog.show("Alert", "Please fill this field", new Command("OK"));
                else
                {
                    try {
                        Catégorie c = new Catégorie(cat.getId(),tfCateg.getText());
                        if( ServiceCateg.getInstance().editCateg(c))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (StringIndexOutOfBoundsException e) {
                        Dialog.show("ERROR", "Nom must be a String", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfCateg,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListCateg().show());
    
    }
    
}
