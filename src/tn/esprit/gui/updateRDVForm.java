/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import tn.esprit.entities.RendezVous;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceRendez;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;


/**
 *
 * @author SBS
 */
public class updateRDVForm extends Form{
          ServiceRendez sr= ServiceRendez.getInstance();
    public updateRDVForm( RendezVous rdv,User user, Resources theme) {
        
     setTitle("Detail Rendez-vous");
        Button mod = new Button("Update");
        Button list = new Button("List");
       
        Label lnomrdv = new Label("Nom RDV");
        TextField nomrdv = new TextField(rdv.getNomRDV(),"Nom RDV");
        Container cnom = BoxLayout.encloseX(
                lnomrdv,
                nomrdv,
                createStatusBar()
        );
        Label ldes = new Label("Description RDV");
        TextField des = new TextField(rdv.getDescriptionRDV(),"Description RDV");
        Container cdes = BoxLayout.encloseX(
                ldes,
                des,
                createStatusBar()
        );
        Label ldate = new Label("Date RDV");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setSelectCommandText(rdv.getDateRDV());
       // Label date = new Label(rdv.getNomRDV());
        Container cdate = BoxLayout.encloseX(
                ldate,
                datePicker,
                createStatusBar()
        );
        Label luser = new Label("User");
        TextField userr = new TextField(rdv.getNomUser(),"User");
        Container cuser = BoxLayout.encloseX(
                luser,
                userr,
                createStatusBar()
        );
        Label lplan = new Label("Planning");
        TextField plan = new TextField(rdv.getNomPlanning(),"Planning");
        Container cplan = BoxLayout.encloseX(
                lplan,
                plan,
                createStatusBar()
        );
        Label lpat = new Label("Patient");
        TextField pat = new TextField(rdv.getNomPatient(),"Patient");
        Container cpat = BoxLayout.encloseX(
                lpat,
                pat,
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
                mod,
                list
                
        );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mod.addActionListener(evt->{
            RendezVous rdv2 = new RendezVous(nomrdv.getText().toString(), des.getText().toString(), dateFormat.format(datePicker.getDate()).toString(), userr.getText().toString(), plan.getText().toString(), pat.getText().toString());
            sr.updRDV(rdv);
            new ListRendezForm(theme,user).show();
        });
        list.addActionListener(evt->{
           sr.updRDV(rdv);
           new ListRendezForm(theme,user).show();
        });
        
        this.add(cont);
    }
    
    
    
}
