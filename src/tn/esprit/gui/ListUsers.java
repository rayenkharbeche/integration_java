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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class ListUsers extends Form {
       public static ArrayList<User>  listUsers = new ArrayList<>();
 public static UserService userService=new UserService();
 Container ctn, ctn1;
    public ListUsers(Resources theme,User user) {
        setTitle("Liste des utilisateurs");
        getAllStyles().setBgColor(0xfff2e6);
        ctn = new Container(BoxLayout.y());
        ctn1 = new Container(BoxLayout.y());
        ctn.setScrollableY(true);
          ctn1.setScrollableY(true);
          TextField filter = new TextField();
          filter.addDataChangedListener((type, index) -> {
              ArrayList<User > filtred_users = new ArrayList<>();
              for (User userr : listUsers){
                  if (userr.getUsername().startsWith(filter.getText()))
                      filtred_users.add(userr);
              }
              ctn.removeAll();
              setUsers(filtred_users);
              
              revalidate();
          });
          ctn1.add(filter);
  
  
  
    
       

   //   lb.setText(serviceQuestion.getListQuestion().toString());//hatina fl lb resultat mtaa lmethode getList2()
     
 
   listUsers=userService.getAll();
   setUsers(listUsers);

      add(ctn1);
         add(ctn);
          getToolbar().addCommandToRightBar("retour", null, (ev)->{new HomeForm(theme,user).show();
          });
        
    }
      private void setUsers(ArrayList<User> listUsers) {
    
       for (User user :listUsers ){
     
          MultiButton mb= new MultiButton(user.getUsername());
             
          final FontImage placeholderImage =FontImage.createMaterial(FontImage.MATERIAL_PERSON,"label", 6);
           
          mb.setIcon(placeholderImage);
           mb.setTextLine2(user.getRoles());
          
       mb.addActionListener( (evt) -> {
           
         
        
       }
       
       );
        ctn.add(mb);
       Button btn_supprimer = new Button("supprimer");
         ctn.add(btn_supprimer);
    
         
        btn_supprimer.addActionListener( (evt) -> {
            if(Dialog.show("voulez vous supprimer cet utilisateur?", "", "oui", "Non")){
                 UserService userService = new UserService();
          userService.deleteUser(user.getId());
          ctn.removeComponent(mb);
          ctn.removeComponent(btn_supprimer);
          refreshTheme();
            }
         
  
          
       }  );
        
       }
    }
      
}
