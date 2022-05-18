/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Tournoi;
import com.mycompany.services.ServiceTournoi;
import com.mycompany.services.UserServices;
import com.mycompany.utils.JavaMail;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author MR
 */
public class TournamentAddForm extends BaseForm {

public TournamentAddForm(Resources res){
     super(new BorderLayout());
        setUIID("LoginForm");
  
        Container welcome = FlowLayout.encloseCenter(
                new Label("Create your Tournament ", "WelcomeWhite")
        );
         
        TextField tfName = new TextField("","entrer nom!");
       
         super.addSideMenu(res);
       
        TextField tfemplacement= new TextField("", "emplacement: Tunis ");
 
        getTitleArea().setUIID("Container");
        
       

        tfName.getAllStyles().setMargin(LEFT, 0);
        tfemplacement.getAllStyles().setMargin(LEFT, 0);
       

        Label tfNameIcon = new Label("", "TextField");
        Label tfemplacementIcon = new Label("", "TextField");
       

        tfNameIcon.getAllStyles().setMargin(RIGHT, 0);
        tfemplacementIcon.getAllStyles().setMargin(RIGHT, 0);
      

        Label tfemplacementCheck = new Label("Votre nom doit contient au moins 3 caractéres");
        tfemplacementCheck.setVisible(false);

        Label tfNameCheck = new Label("Votre Prenom doit contient au moins 3 caractéres");
        tfNameCheck.setVisible(false);
     
        
        Button btnAjouter = new Button("Ajouter");
        btnAjouter.setUIID("LoginButton");
        btnAjouter.addActionListener((ActionEvent e) -> {
            if (tfName.getText().length() <= 3 || tfemplacement.getText().length() <= 3 ) {
                ToastBar.showErrorMessage("Les champs ne sont pas valides");
               
                if (tfName.getText().length() <= 3) {
                    tfNameCheck.setVisible(true);
                    tfNameCheck.getAllStyles().setFgColor(0xFF0000);
                }
                if (tfemplacement.getText().length() <= 3) {
                    tfemplacementCheck.setVisible(true);
                    tfemplacementCheck.getAllStyles().setFgColor(0xFF0000);
                }
             
            } else {
               Tournoi T = new Tournoi(tfName.getText(),
                             
                                  tfemplacement.getText()
                             
                                  );
                   
                   
                   
                    System.out.println("data  Tournoi == "+T);
                   
                                
                   
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                    ServiceTournoi.getInstance().addTournoi(T);
                   
                   // iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                   
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new ListTournament(res).show();
                   
                   
                   refreshTheme();//Actualisation
            }
            
        });
         
        // Form h1 =new Form("hi",BoxLayout.y());
        Button btCapture = new Button("Capture");
        Label lbltImage =new Label();
        btCapture.setUIID("LoginButton");
        btCapture.addActionListener((e)->{
            String path =Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
            if(path != null){
                try{
                    Image img =Image.createImage(path);
                    lbltImage.setIcon(img);
                    //current.revalidate();
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label("");
        }

        Container x = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(tfName).
                        add(BorderLayout.WEST, tfNameIcon).add(BorderLayout.NORTH, tfNameCheck),
                BorderLayout.center(tfemplacement).
                        add(BorderLayout.WEST, tfemplacementIcon).add(BorderLayout.NORTH, tfemplacementCheck),
              
                btnAjouter,lbltImage ,
                btCapture
        );

        add(BorderLayout.CENTER, x);

        //  for low res and landscape devices
        x.setScrollableY(true);
        x.setScrollVisible(false);

    }

}
