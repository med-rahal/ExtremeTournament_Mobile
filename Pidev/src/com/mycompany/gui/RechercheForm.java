/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UserServices;

/**
 *
 * @author MR
 */
public class RechercheForm extends BaseForm {

    public RechercheForm(Form previous,Resources res) {
        setTitle("Recherche User");
        super.addSideMenu(res);
        TextField nom = new TextField("", "recherche");
        Button btnValider = new Button(" recherche Users");
        btnValider.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((nom.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", "Annuler");
            } else {
                UserServices us = null;
                setTitle("liste des utilisateurs ");
                SpanLabel sp = new SpanLabel();
               
                sp.setText(UserServices.getInstance().recherche(nom.getText().toString()).toString());
                refreshTheme(); // Actualisation

                add(sp);
            }
        });

        addAll(nom, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
