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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class LoginForm extends Form {
     Resources theme_1;
     public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome", "WelcomeWhite")
        );


     
       

        TextField login = new TextField("", "Login", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button loginButton = new Button("LOGIN");
        loginButton.addActionListener(e -> {
            //Toolbar.setGlobalToolbar(false);
            Toolbar.setGlobalToolbar(true);
         //   new ProfileForm(theme).show();
            //new PremierAffichageForm(theme).show();
        });

        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                InscriptionForm inscriptionForm = new InscriptionForm(theme);
                inscriptionForm.show();
            }
        });
       /* Button forgetpass= new Button("Forget password?");
        forgetpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                ForgetPassword Forgetpassword = new ForgetPassword(theme);
                ForgetPassword.show();
            }
        });*/
      Button loginfbButton = new Button("Login Facebook");
        loginfbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                FacebookLogin facebookLogin = new FacebookLogin(theme_1);
                facebookLogin.show();
            }
        });
        // We remove the extra space for low resolution devices so things fit better
      

        Container by = BoxLayout.encloseY(
                welcome,
             
                
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount,loginfbButton
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(false);
        by.setScrollVisible(false);

         UserService ser = new UserService();

        loginButton.addActionListener((evt) -> {
            User u = ser.existe(login.getText(), password.getText());
            if (u!= null) {
                //new ClasseAffiche(theme).show();
                if(u.getRoles().contains("ROLE_PATIENT")){
                    new HomePatientForm(theme,u).show();
                }
                else {
                    new HomeForm(theme, u).show();
                }
                //pgui.getF().show(); 
            } else {
              //  Dialog.show("Error", "Please verify your username or password", "OK", "");
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
        });

      /*  createNewAccount.addActionListener((evt) -> {
            new InscriptionForm(theme).show();
        });
*/ 
    }
}
