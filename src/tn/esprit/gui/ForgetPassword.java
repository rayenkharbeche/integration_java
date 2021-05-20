/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.services.UserService;

/**
 *
 * @author rayen
 */
public class ForgetPassword extends Form {
 
      TextField email;
     public void sendMail(Resources theme1){
    try{    
        Properties props = new Properties();

        // Setup mail server
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
    Session session = Session.getInstance(props,null);
    MimeMessage msg = new MimeMessage (session);
    msg.setFrom(new InternetAddress("Réinitialisation de votre mot de passe <monEmail@domaine.com>"));
    msg.setRecipients(Message.RecipientType.TO, email.getText().toString()); 
    msg.setSubject("Softhealth : confirmation du ");
    msg.setSentDate(new Date(System.currentTimeMillis()));
    UserService userservice = new UserService();
    String mp = userservice.getPassswordByEmail(email.getText().toString(),theme1);
    String txt ="Bienvenue sur Soft health : Tapez ce mot de passe : "+mp+"dans le champs requis et appuiez sur valider ";
    msg.setText(txt);
    SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
    st.connect("smtp.gmail",465,"seifeddine.fathallah@esprit.tn","seif0404");
    st.sendMessage(msg, msg.getAllRecipients());
    System.out.println("server response : "+st.getLastServerResponse());
    
    
    
 }catch(Exception e){
     e.printStackTrace();
 }
}
      public ForgetPassword(Resources theme) {
        setTitle("Forget Password");
        Label label_email = new Label("email");
        TextField txt_email = new TextField("","email");
        Button button_add = new Button("Valider");
                Container cnt = BoxLayout.encloseY(label_email, BorderLayout.center(txt_email),button_add);
 //mailUtil a = new mailUtil();
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                try {
//                    File file=null;
//                   
//                    //a.sendMail("seifathallah7@gmail.com",  "jihene.sliti@esprit.tn", "syrine1997@mat", "votre mot de passe  ", file);
//                } catch (Exception ex) {
//                }
              sendMail(theme);
              new LoginForm(theme).show();
            }
        });
        add(cnt);

    }    
    
    
}
