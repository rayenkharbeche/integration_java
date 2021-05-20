/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;


import Entities.Catégorie;
import Services.ServiceCateg;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.util.ArrayList;

/**
 *
 * @author ASSOUMA
 */
public class ListCateg extends Form{
     Form current;
    
    public ListCateg(){
    
     setTitle("Liste des Catégories");
     
     
     
      Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Catégorie> cat = new ArrayList();
                    ServiceCateg sa =new ServiceCateg();
                    cat=sa.getAllCateg();
                             for (Catégorie fi : cat) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Nom Categorie : "+fi.getNom());
                         
                            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette Catégorie ?", "Supprimer", "Annuler")) {
                Catégorie c = new Catégorie(fi.getNom());
                        if( ServiceCateg.getInstance().deleteCateg(c)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListCateg().show();
                            }
                }
            }    
            }
        });
            m.addPointerPressedListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette Catégorie ?", "Modifier ", "Annuler")) {
                    new EditCateg(current, fi).show();
                }    
            }
        });
                            add(m);
                             }
                     revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
          
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
     }
     }, 4);
    
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            Home ap = new Home();
            ap.show();
        });
         
         }

}
