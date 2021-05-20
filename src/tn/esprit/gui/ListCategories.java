/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieService;

/**
 *
 * @author rayen
 */
public class ListCategories extends Form {
 public static ArrayList<Categorie>  listCategories = new ArrayList<>();
 public static CategorieService categorieService=new CategorieService();
 Container ctn, ctn1,ctn2;
 Resources th;
    public ListCategories(Resources theme,User user) {
        setTitle("Liste des categories");
          getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
                ctn2 = new Container(BoxLayout.x());

        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<Categorie > filtred_categories = new ArrayList<>();
              for (Categorie categorie : listCategories){
                  if (categorie.getType_categorie().startsWith(filter.getText()))
                      filtred_categories.add(categorie);
              }
              ctn.removeAll();
              setCategories(filtred_categories,user);
              
              revalidate();
          });
          ctn1.add(filter);
  
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listCategories=categorieService.getAll();
   setCategories(listCategories,user);

      add(ctn1);
         add(ctn);
         
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
    }
      private void setCategories(ArrayList<Categorie> listCategories,User user) {
    
       for (Categorie categorie :listCategories ){
     
          MultiButton mb= new MultiButton(categorie.getType_categorie());
             
          final FontImage placeholderImage =FontImage.createMaterial(FontImage.MATERIAL_ARTICLE,"label", 6);
           
          mb.setIcon(placeholderImage);
          
       mb.addActionListener( (evt) -> {
           
         
        
       }
       
       );
        ctn.add(mb);
         Button updateButton = new Button("modifier");
       
       Button btn_supprimer = new Button("supprimer");
         
         ctn.add(updateButton);
         ctn.add(btn_supprimer);
         
         
        btn_supprimer.addActionListener( (evt) -> {
            if(Dialog.show("voulez vous supprimer cette categorie?", "", "oui", "Non")){
                 CategorieService categorieService = new CategorieService();
          categorieService.deleteCategorie(categorie.getId_categorie());
          ctn.removeComponent(mb);
          ctn.removeComponent(updateButton);
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
          
       }  );
        
       
        updateButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 
                  new UpdateCategorie(th, categorie,user).show();
              }
          });
        
       }
    }
    
    
}
