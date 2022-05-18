/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UserServices;
import java.net.URI;
import java.net.URISyntaxException;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Preferences;
import com.codename1.ui.CN;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ext.filechooser.FileChooserNative;
import com.codename1.io.Log;
import com.codename1.ext.filechooser.*;
import com.mycompany.utils.JavaMail;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

public class SignupForm extends Form {

    String imageF = "";

    public SignupForm(Resources theme) {
        super(new BorderLayout());
        setUIID("LoginForm");
        getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e -> new LoginForm(theme).show());
        Container welcome = FlowLayout.encloseCenter(
                new Label("Create your account ", "WelcomeWhite")
        );

        getTitleArea().setUIID("Container");
        TextField nom = new TextField("Nom", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("Prenom", "Prenom", 20, TextField.ANY);
        TextField username = new TextField("Username", "Username", 20, TextField.USERNAME);
        TextField sexe = new TextField("Sexe", "Sexe", 20, TextField.ANY);
        TextField email = new TextField("Email", "Email", 20, TextField.EMAILADDR);
        TextField passw = new TextField("Password", "Password", 20, TextField.PASSWORD);
        TextField tel = new TextField("Telephone", "Telephone", 20, TextField.PHONENUMBER);
        // TextField date_naissance = new TextField("Date_naissance", "Date_naissance", 20, TextField.PHONENUMBER);
        TextField adresse = new TextField("Adresse", "Adresse", 20, TextField.USERNAME);
        // TextField image = new TextField("Image", "Image", 20, TextField.ANY);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setUIID("LoginForm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Button imagebutton = new Button("image");
        Label limg = new Label("");

        imagebutton.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .jpeg,.png, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        //add("No file was selected");
                        revalidate();
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        // add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            limg.setIcon(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            String namePic = saveFiletoDevice(file, ext);
                            imageF = namePic;
                            System.out.println("Image" + namePic);
                            revalidate();

                        }
                    }
                });
            }
        });

        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        username.getAllStyles().setMargin(LEFT, 0);
        sexe.getAllStyles().setMargin(LEFT, 0);
        passw.getAllStyles().setMargin(LEFT, 0);
        tel.getAllStyles().setMargin(LEFT, 0);
        adresse.getAllStyles().setMargin(LEFT, 0);
        //  image.getAllStyles().setMargin(LEFT, 0);

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
        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_MAIL_OUTLINE, 3);
        FontImage.setMaterialIcon(usernameIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(dateIcon, FontImage.MATERIAL_DATE_RANGE, 3);
        FontImage.setMaterialIcon(telIcon, FontImage.MATERIAL_PHONE, 3);
        FontImage.setMaterialIcon(sexeIcon, FontImage.MATERIAL_CARD_MEMBERSHIP, 3);
        FontImage.setMaterialIcon(passwIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(adresseIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(imageIcon, FontImage.MATERIAL_IMAGE, 3);

        Validator validator = new Validator();
        validator.addConstraint(email, RegexConstraint.validEmail());

        Label nomCheck = new Label("Votre nom doit contient au moins 3 caractéres");
        nomCheck.setVisible(false);

        Label prenomCheck = new Label("Votre Prenom doit contient au moins 3 caractéres");
        prenomCheck.setVisible(false);
        Label usernameCheck = new Label("Votre username doit contient au moins 3 caractéres");
        usernameCheck.setVisible(false);
        Label emailCheck = new Label("Ce n'est pas un Email Valide");
        emailCheck.setVisible(false);
        Label passwCheck = new Label("Ce n'est pas un username valide");
        passwCheck.setVisible(false);
        Label TelCheck = new Label("Ce n'est pas un Tel Valide");
        TelCheck.setVisible(false);

        Button signupButton = new Button("SIGN UP");

        signupButton.setUIID("LoginButton");
        signupButton.addActionListener((ActionEvent e) -> {
            if (!validator.isValid() || nom.getText().length() <= 3 || prenom.getText().length() <= 3 || passw.getText().length() <= 8 ) {
                ToastBar.showErrorMessage("Les champs ne sont pas valides");
                if (!validator.isValid()) {
                    emailCheck.setVisible(true);
                    emailCheck.getAllStyles().setFgColor(0xFF0000);
                }
                if (nom.getText().length() <= 3) {
                    nomCheck.setVisible(true);
                    nomCheck.getAllStyles().setFgColor(0xFF0000);

                }
                if (prenom.getText().length() <= 3) {
                    prenomCheck.setVisible(true);
                    prenomCheck.getAllStyles().setFgColor(0xFF0000);
                }
                if (passw.getText().length() <= 3) {
                    passwCheck.setVisible(true);
                    passwCheck.getAllStyles().setFgColor(0xFF0000);
                }
              
            } else {
                UserServices.getInstance().signup(nom, prenom, username, format.format(datePicker.getDate()), sexe, email, passw, tel, adresse, imageF, theme);
                try {
                    JavaMail.sendMail(email.getText());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                new LoginForm(theme).show();
            }

        });

        Button login = new Button("Already have an account");
        login.setUIID("CreateNewAccountButton");
        login.addActionListener(e -> new LoginForm(theme).show());
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label("");
        }

        Container x = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(nom).
                        add(BorderLayout.WEST, nomIcon).add(BorderLayout.NORTH, nomCheck),
                BorderLayout.center(prenom).
                        add(BorderLayout.WEST, prenomIcon).add(BorderLayout.NORTH, prenomCheck),
                BorderLayout.center(username).
                        add(BorderLayout.WEST, usernameIcon),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon).add(BorderLayout.NORTH, emailCheck),
                BorderLayout.center(sexe).
                        add(BorderLayout.WEST, sexeIcon),
                BorderLayout.center(passw).
                        add(BorderLayout.WEST, passwIcon).add(BorderLayout.NORTH, passwCheck),
                BorderLayout.center(datePicker).
                        add(BorderLayout.WEST, dateIcon),
                BorderLayout.center(tel).
                        add(BorderLayout.WEST, telIcon).add(BorderLayout.NORTH, TelCheck),
                BorderLayout.center(adresse).
                        add(BorderLayout.WEST, adresseIcon),
                signupButton, imagebutton,
                login
        );

        add(BorderLayout.CENTER, x);

        //  for low res and landscape devices
        x.setScrollableY(true);
        x.setScrollVisible(false);

    }

    protected String saveFiletoDevice(String hi, String ext) {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
        return "niniinii";
    }

}
