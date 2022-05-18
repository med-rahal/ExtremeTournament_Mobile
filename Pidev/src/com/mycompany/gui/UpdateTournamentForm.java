/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.ListTournament;
import com.mycompany.entities.Tournoi;
import com.mycompany.services.ServiceTournoi;
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

/**
 *
 * @author MAKREM
 */
public class UpdateTournamentForm extends BaseForm {

    Form current;

    public UpdateTournamentForm(Resources res, Tournoi c) {
        super("Update Tournament", BoxLayout.y());
        getTitleArea().setUIID("WalkthruTab1");
        getContentPane().setScrollVisible(false);

        // super.addSideMenu(res);
        // TextField type = new TextField(r.getType() , "Objet" , 20 , TextField.ANY);
        // name = new TextField(r.getName() , "Description" , 20 , TextField.ANY);
        TextField Name = new TextField(c.getNomT(), "Nom tournoi", 20, TextField.ANY);
        TextField Emplacement = new TextField((c.getEmplacementT()), "Emplacement", 20, TextField.ANY);

        super.addSideMenu(res);
        Name.setUIID("NewsTopLine");
        Emplacement.setUIID("NewsTopLine");

        Name.setSingleLineTextArea(true);
        Emplacement.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            c.setNomT(Name.getText());

            c.setEmplacementT(Emplacement.getText());

            if (ServiceTournoi.getInstance().modifierTournament(c)) { // if true
                new ListTournament(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListTournament(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(Name),
                //  createLineSeparator(),
                new FloatingHint(Emplacement),
                // createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
