/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;

/**
 *
 * @author rayen
 */
public class ForgetPassword extends Form {
 
       public static String mails;
    public ForgetPassword(Resources theme) {
        TextField password = new TextField();
        Button btn_password = new Button("confirmer mot de passe");
        TextField email = new TextField();
        TextField code1 = new TextField();
        Button btn_send = new Button("envoyer email");
        Button btn_confirm = new Button("confirmer code");
        Label rec = new Label("mot de passe oublié");
        Label rec1= new Label("code envoyé par mail");
        Label rec2 = new Label("changer mot de passe");
    }
    
    
}
