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
import static com.codename1.ui.events.ActionEvent.Type.Theme;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.RendezVous;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceRendez;

/**
 *
 * @author SBS
 */
public class showRDVForm extends Form {
  ServiceRendez sr= ServiceRendez.getInstance();
    public showRDVForm(RendezVous rdv,User user,Resources theme) {
        
        setTitle("Detail Rendez-vous");
         getAllStyles().setBgColor(0xfff2e6);
        Button update =new Button("Update");
        Button del =new Button("Delate");
        Label lnomrdv = new Label("Nom RDV");
        Label nomrdv = new Label(rdv.getNomRDV());
        Container cnom = BoxLayout.encloseX(
                lnomrdv,
                nomrdv,
                createStatusBar()
        );
        Label ldes = new Label("Description RDV");
        Label des = new Label(rdv.getDateRDV());
        Container cdes = BoxLayout.encloseX(
                ldes,
                des,
                createStatusBar()
        );
        Label ldate = new Label("Date RDV");
        Label date = new Label(rdv.getNomRDV());
        Container cdate = BoxLayout.encloseX(
                ldate,
                date,
                createStatusBar()
        );
        Label luser = new Label("User");
        Label userr = new Label(rdv.getNomUser());
        Container cuser = BoxLayout.encloseX(
                luser,
                userr,
                createStatusBar()
        );
        Label lplan = new Label("Planning");
        Label plan = new Label(rdv.getNomPlanning());
        Container cplan = BoxLayout.encloseX(
                lplan,
                plan,
                createStatusBar()
        );
        Label lpat = new Label("Patient");
        Label pat = new Label(rdv.getNomPatient());
        Container cpat = BoxLayout.encloseX(
                lpat,
                pat,
                createStatusBar()
        );
        Container cb = BoxLayout.encloseX(
                update,
                del,
                createStatusBar()
        );
        
        Container cont = BoxLayout.encloseY(
                cnom,
                createStatusBar(),
                cdes,
                createStatusBar(),
                cdate,
                createStatusBar(),
                cuser,
                createStatusBar(),
                cplan,
                createStatusBar(),
                cpat,
                createStatusBar(),
                cb
        );
        del.addActionListener(evt->{
            sr.delRDV(rdv.getId());
           // Dialog.show("Bien Supprimer", "Suppression", "OK",  null);
          // new ListRendezForm().show();
        });
        update.addActionListener(evt->{
          // sr.updRDV(rdv);
           new updateRDVForm(rdv,user, theme).show();
        });
        
        this.add(cont);
    }
    
}
