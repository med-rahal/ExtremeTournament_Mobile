/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.entities.Reclamation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.PublicationService;
import com.mycompany.services.ReclamationServices;
import com.mycompany.services.SessionManager;

/**
 *
 * @author ASUS
 */
public class updatepubform extends BaseForm {
    
   

    public updatepubform(Resources res) {
        
        super(BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Publication r = new Publication();

        //  getTitleArea().setUIID("WalkthruTab1");
        //setTitle("Modifier Publication");
       // getContentPane().setScrollVisible(false);
        
     //   super.addSideMenu(res);
        
        TextField titre = new TextField(r.getTitre(), "titre", 20, TextField.ANY);
        TextField status = new TextField(r.getStatus(), "status", 20, TextField.ANY);
        
        titre.setUIID("WalkthruTab1");
        status.setUIID("WalkthruTab1");
        
        titre.setSingleLineTextArea(true);
        status.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modify");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            
            r.setTitre(r.getTitre());
            r.setStatus(status.getText());
            r.setId_user(SessionManager.getId_user());
            r.setImage("");
            //appel fonction modfier reclamation men service
            if (PublicationService.getInstance().modifierpub(r)) { // if true
                //   new ListReclamationForm(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new displaypubform(res).show();
        });
        
        Label l2 = new Label("");
        
        Label l3 = new Label("");
        
        Label l4 = new Label("");
        
        Label l5 = new Label("");
        
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(titre),
                createLineSeparator(),
                new FloatingHint(status),
                createLineSeparator(),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
        );
        
        add(content);
        show();
        
    }
}
