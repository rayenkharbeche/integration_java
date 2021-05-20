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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASSOUMA
 */
public class AddCateg extends Form {
    
    public AddCateg(Form previous){
        
        setTitle("Ajouter Catégorie");
         setLayout(BoxLayout.y());
        
        TextField tfNom= new TextField("", "nom");
        

        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill the field", new Command("OK"));
                else
                {
                    try {
                        Catégorie c = new Catégorie( tfNom.getText());
                        if( ServiceCateg.getInstance().addCateg(c))
                        { Dialog.show("Success","Connection accepted",new Command("OK"));
                             ListCateg ap = new ListCateg();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (StringIndexOutOfBoundsException e) {
                        Dialog.show("ERROR", "", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,btnValider);
        
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            ListCateg ap = new ListCateg();
            ap.show();
        });    }
    
    
}
