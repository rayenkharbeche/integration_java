/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tn.esprit.entities.RendezVous;
import tn.esprit.services.ServiceRendez;
import com.codename1.ui.util.Resources;
import tn.esprit.entities.User;

/**
 *
 * @author rayen
 */
public class addRDVForm extends Form{

    public addRDVForm(Resources theme,User user) {
        
        setTitle("Add a new Rendez-vous");
        setLayout(BoxLayout.y());
        TextField tfnomP = new TextField("","Rendez-vous Name");
        TextField tfdesc= new TextField("", "Description Rendez-vous");
        TextField tfuser= new TextField("", "User");
        TextField tfpatient= new TextField("", "Patient");
      
        Button btnValider = new Button("Add RDV");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        Container c= new Container();
        c.setLayout(BoxLayout.x());
       // TextField tfdate= new TextField(datePicker,"Entrer Date",);
       // tfdate.setUIID("TextFiledBlack");
        Label a= new Label("Date");
        c.addAll(a,datePicker);
      //  addStringValue("date Piker",tfdate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnomP.getText().length()==0)||(tfdesc.getText().length()==0)||
            (tfuser.getText().length()==0)||(tfpatient.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                       // java.util.Date datefff = (java.util.Date) dateFormat.parse(dateFormat.format(datePicker.getDate()));
                        RendezVous t = new RendezVous(tfnomP.getText().toString(), tfdesc.getText().toString(),dateFormat.format(datePicker.getDate()).toString(),tfuser.getText().toString(),tfpatient.getText().toString());
                        if( ServiceRendez.getInstance().addRDV(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } 
                }
            }
        });
        Button list=new Button("List");
        list.addActionListener(evt->{
           
           new ListRendezForm(theme,user).show();
        });
         
        // c.addAll();
        addAll(tfnomP,tfdesc,c,tfuser,tfpatient,btnValider,list);
    //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
      //          , e-> previous.showBack()); // Revenir vers l'interface précédente
 }    
    }
    
    
    

