/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ReclamationServices;
import com.mycompany.services.SessionManager;

/**
 *
 * @author MR
 */
public class ModifierReclamationForm extends BaseForm {

    Form current;
    public ModifierReclamationForm(Resources res, Reclamation r) {
        super(BoxLayout.y()); 

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField description = new TextField(r.getDescriptionR(), "description", 20, TextField.ANY);
        TextField type = new TextField(r.getType(), "type", 20, TextField.ANY);
        TextField etat = new TextField(r.getEtat(), "Etat", 20, TextField.ANY);

      
        description.setUIID("WalkthruTab1");
        type.setUIID("WalkthruTab1");
        etat.setUIID("WalkthruTab1");

        description.setSingleLineTextArea(true);
        type.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);   

        Button btnModifier = new Button("Modify");
        btnModifier.setUIID("LoginButton");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            
            r.setId_reclam(r.getId_reclam());
            r.setDescriptionR(description.getText());
            r.setType(type.getText());
            r.setEmail(SessionManager.getEmail());
            r.setIduser(SessionManager.getId_user());
         //   r.setDateR(r.getDateR());
            r.setEtat(r.getEtat());
            
            System.out.println(r);

            //appel fonction modfier reclamation men service
            if (ReclamationServices.getInstance().modifierReclamation(r)) { // if true
              //   new ListReclamationForm(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
         btnAnnuler.setUIID("LoginButton");
        btnAnnuler.addActionListener(e -> {
            new ListReclamationForm(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator(),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }

}
