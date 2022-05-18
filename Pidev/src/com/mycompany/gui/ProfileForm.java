/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.services.SessionManager;
import com.mycompany.services.UserServices;
import java.util.Date;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author MR
 */
public class ProfileForm extends BaseForm {

    Form current;

    public ProfileForm(Resources res) {

        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        Button recherche = new Button("Rechercher user");
        recherche.setUIID("WalkthruTab1");
        recherche.addActionListener(e -> new RechercheForm(current,res).show());
        addAll(recherche);
        TextField nom = new TextField(SessionManager.getNom(), "Nom", 20, TextField.ANY);
        TextField prenom = new TextField(SessionManager.getPrenom(), "Prenom", 20, TextField.ANY);
        TextField username = new TextField(SessionManager.getUsername(), "Username", 20, TextField.USERNAME);
        TextField sexe = new TextField(SessionManager.getSexe(), "Sexe", 20, TextField.ANY);
        TextField email = new TextField(SessionManager.getEmail(), "Email", 20, TextField.EMAILADDR);
        TextField passw = new TextField(SessionManager.getPass(), "Password", 20, TextField.PASSWORD);
        TextField tel = new TextField(SessionManager.getTel(), "Telephone", 20, TextField.PHONENUMBER);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date_n = SessionManager.getDate_naissance();
        TextField adresse = new TextField(SessionManager.getAdresse(), "Adresse", 20, TextField.USERNAME);
        TextField image = new TextField(SessionManager.getImage(), "Image", 20, TextField.ANY);

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setUIID("WalkthruTab1");
        //addStringValue("datePicker", datePicker);
        addSideMenu(res);
        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        username.getAllStyles().setMargin(LEFT, 0);
        sexe.getAllStyles().setMargin(LEFT, 0);
        passw.getAllStyles().setMargin(LEFT, 0);
        tel.getAllStyles().setMargin(LEFT, 0);
        adresse.getAllStyles().setMargin(LEFT, 0);
        image.getAllStyles().setMargin(LEFT, 0);
        Label nomIcon = new Label("", "TextField");
        Label prenomIcon = new Label("", "TextField");
        Label usernameIcon = new Label("", "TextField");
        Label emailIcon = new Label("", "TextField");
        Label sexeIcon = new Label("", "TextField");
        Label passwIcon = new Label("", "TextField");
        Label telIcon = new Label("", "TextField");
        Label adresseIcon = new Label("", "TextField");
        Label dateIcon = new Label("", "TextField");
        Label imageIcon = new Label("", "TextField");
        nomIcon.getAllStyles().setMargin(RIGHT, 0);
        usernameIcon.getAllStyles().setMargin(RIGHT, 0);

        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        username.getAllStyles().setMargin(LEFT, 0);
        sexe.getAllStyles().setMargin(LEFT, 0);
        passw.getAllStyles().setMargin(LEFT, 0);
        tel.getAllStyles().setMargin(LEFT, 0);
        adresse.getAllStyles().setMargin(LEFT, 0);
        image.getAllStyles().setMargin(LEFT, 0);

        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_MAIL_OUTLINE, 3);
        FontImage.setMaterialIcon(usernameIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(telIcon, FontImage.MATERIAL_PHONE, 3);
        FontImage.setMaterialIcon(sexeIcon, FontImage.MATERIAL_CARD_MEMBERSHIP, 3);
        FontImage.setMaterialIcon(passwIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(adresseIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(imageIcon, FontImage.MATERIAL_IMAGE, 3);

        Button editButton = new Button("Modify");
        Button deleteButton = new Button("Delete");

        editButton.setUIID("LoginButton");
        deleteButton.setUIID("LoginButton");
        deleteButton.getAllStyles().setBgColor(0xffffff);
        deleteButton.getAllStyles().setFgColor(0x350a4e);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        editButton.addActionListener(e -> UserServices.updateUser(nom.getText(), prenom.getText(), username.getText(), format.format(datePicker.getDate()), sexe.getText(), email.getText(), passw.getText(), tel.getText(), adresse.getText(), image.getText()));
        deleteButton.addActionListener(e -> {
            UserServices.getInstance().deleteUser(SessionManager.getId_user());
            new LoginForm(res).show();
        });

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label("");
        }

        Container x = BoxLayout.encloseY(
                spaceLabel,
                BorderLayout.center(nom).
                        add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(prenom).
                        add(BorderLayout.WEST, prenomIcon),
                BorderLayout.center(username).
                        add(BorderLayout.WEST, usernameIcon),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(sexe).
                        add(BorderLayout.WEST, sexeIcon),
                BorderLayout.center(passw).
                        add(BorderLayout.WEST, dateIcon),
                BorderLayout.center(datePicker).
                        add(BorderLayout.WEST, passwIcon),
                BorderLayout.center(tel).
                        add(BorderLayout.WEST, telIcon),
                BorderLayout.center(adresse).
                        add(BorderLayout.WEST, adresseIcon),
                BorderLayout.center(image).
                        add(BorderLayout.WEST, imageIcon),
                editButton,
                deleteButton
        );

        x.setUIID("WalkthruTab1");
        add(x);

    }

    /*  
    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }
     */
}
