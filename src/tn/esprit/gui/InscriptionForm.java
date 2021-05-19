/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class InscriptionForm extends Form {

    public InscriptionForm(Resources theme) {
        
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = theme.getImage("user-picture.jpg");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(theme.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  New ", "WelcomeBlue"),
                                        new Label("User", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
         Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        
        setTitle("Inscription");
        Label username = new Label("username");
        TextField txt_username = new TextField("","username");
        Label label_password = new Label("password");
        TextField txt_password = new TextField("", "Password", 20, TextField.PASSWORD);
        Label label_roles = new Label("role");
        ComboBox roles = new ComboBox("ROLE_PHARMACIEN", "ROLE_PATIENT", "ROLE_MEDECIN", "ROLE_SECRETAIRE");
        Label label_cin = new Label("CIN");
        TextField txt_cin = new TextField("", "cin");
        Label label_email = new Label("email");
        TextField txt_email = new TextField("","email");
        Button button_add = new Button("ajouter");
                Container cnt = BoxLayout.encloseY(username, BorderLayout.center(txt_username),label_password,BorderLayout.center(txt_password),label_roles, BorderLayout.center(roles),label_cin,
                         BorderLayout.center(txt_cin),label_email, BorderLayout.center(txt_email),button_add);

        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              UserService userService = new UserService();
              User user = new User();
              user.setUsername(txt_username.getText());
               user.setRoles(roles.getSelectedItem().toString());
               user.setEmail(txt_email.getText());
               user.setPassword(txt_password.getText());
               user.setCin(Integer.parseInt(txt_cin.getText()));
              userService.ajoutUser(user);
              new LoginForm(theme).show();
            }
        });
        add(cnt);

    }
    
}
