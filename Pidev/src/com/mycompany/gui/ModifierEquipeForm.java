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
import com.mycompany.entities.Equipe;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceEquipe;
import com.mycompany.services.SessionManager;

/**
 *
 * @author acer
 */
public class ModifierEquipeForm extends BaseForm {

    Form current;

    public ModifierEquipeForm(Resources res, Equipe r) {
        super(BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        getTitleArea().setUIID("WalkthruTab1");
        setTitle("Modifier Equipe");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField nom_equipe = new TextField(r.getNom_equipe(), "Nom equipe", 20, TextField.ANY);
        TextField categorie = new TextField(r.getCategorie(), "categorie", 20, TextField.ANY);
      //  TextField id_user = new TextField(r.getId_user());
        TextField nb_participants = new TextField(String.valueOf(r.getNb_participants()),"Nombre Participants",20,TextField.ANY);
        TextField image = new TextField(r.getImage(), "image", 20, TextField.ANY);
        TextField Epass = new TextField(r.getEpass(), "Password", 20, TextField.PASSWORD);
      //  TextField id_match = new TextField(r.getId_match());

        nom_equipe.setUIID("WalkthruTab1");
        categorie.setUIID("WalkthruTab1");
        nb_participants.setUIID("WalkthruTab1");
        Epass.setUIID("WalkthruTab1");

        nom_equipe.setSingleLineTextArea(true);
        categorie.setSingleLineTextArea(true);
        nb_participants.setSingleLineTextArea(true);
        Epass.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modify");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {

            r.setNom_equipe(nom_equipe.getText());  
            r.setCategorie(categorie.getText());
            r.setEpass(Epass.getText());
            r.setImage(r.getImage());
            r.setNb_participants(Integer.parseInt(nb_participants.getText()));
            r.setId_user(SessionManager.getId_user()); 
            
            System.out.println(r);  

            //appel fonction modfier reclamation men service
            if (ServiceEquipe.getInstance().modifierEquipe(r)) { // if true
                //   new ListReclamationForm(res).show();
            }

        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListeEquipe(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(nom_equipe),
                createLineSeparator(),
                new FloatingHint(categorie),
                createLineSeparator(),
                new FloatingHint(Epass),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(nb_participants),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler 
        );

        add(content);
        show();
        //

    }

}
