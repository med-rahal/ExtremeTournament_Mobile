/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Equipe;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.services.ServiceEquipe;
import com.mycompany.services.SessionManager;
import java.util.Vector;

/**
 *
 * @author acer
 */
public class EquipeForm extends BaseForm {

    Form current;

    public EquipeForm(Resources res) {
        super(new BorderLayout());
        setUIID("LoginForm");
        //getTitleArea().setUIID("WalkthruTab1");   
        getContentPane().setScrollVisible(false);
        Container welcome = FlowLayout.encloseCenter(
                new Label("Create your Team ", "WelcomeWhite")
        );
        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        //   addTab(swipe, s1, res.getImage(".png"), "", "", res);
        addSideMenu(res);
        //
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        TextField nom_equipe = new TextField("Nom equipe", "Nom equipe", 20, TextField.ANY);
        // TextField categorie = new TextField("categorie", "categorie", 20, TextField.ANY);
        //  TextField id_user = new TextField("id_user", "id_user", 20, TextField.ANY);
        TextField nb_participants = new TextField("nb_participants", "nb_participants", 20, TextField.ANY);
        TextField image = new TextField("image", "image", 20, TextField.ANY);
        TextField Epass = new TextField("Password", "Password", 20, TextField.PASSWORD);
        //TextField id_match = new TextField("id_match", "id_match", 20, TextField.ANY);
        Vector<String> vectorType;
        vectorType = new Vector();

        vectorType.add("Sport");
        vectorType.add("Esport");

        ComboBox<String> cat = new ComboBox<>(vectorType);
        //   categorie.setUIID("TextFieldBlack");
        //addStringValue("type", type);

        nom_equipe.getAllStyles().setMargin(LEFT, 0);
        //  categorie.getAllStyles().setMargin(LEFT, 0);
        nb_participants.getAllStyles().setMargin(LEFT, 0);
        image.getAllStyles().setMargin(LEFT, 0);
        Epass.getAllStyles().setMargin(LEFT, 0);
        //id_match.getAllStyles().setMargin(LEFT, 0);

        Button signupButton = new Button("ajouter");
        signupButton.setUIID("WalkthruTab2");
        signupButton.addActionListener(e -> {
            if (Epass.getText().length() < 8) {
                ToastBar.showErrorMessage("password ne contient pas 8 caractéres");
            } else {
                ServiceEquipe.getInstance().ajouterEquipe(nom_equipe, cat.getSelectedItem().toString(), SessionManager.getId_user(), nb_participants, image, Epass, 1, res);
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
                BorderLayout.center(nom_equipe),
                BorderLayout.center(cat),
                BorderLayout.center(nb_participants),
                BorderLayout.center(image),
                BorderLayout.center(Epass),
                signupButton
        );

        add(BorderLayout.CENTER, x);

        //  for low res and landscape devices
        x.setScrollableY(true);
        x.setScrollVisible(false);

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        /*        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }*/
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

       // swipe.addTab("", res.getImage("back-logo.jpeg"), page1);

    }

}
