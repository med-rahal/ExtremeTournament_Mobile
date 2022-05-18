/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.SideMenuBaseForm;
import com.mycompany.services.PublicationService;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;

/**
 *
 * @author ASUS
 */
public class addpublicationform extends BaseForm {

    Form current;

    public addpublicationform(Resources Res) {

        super(new BorderLayout());
        setUIID("LoginForm");
       // getTitleArea().setUIID("WalkthruTab1");
        //setTitle("Add Post ");
        getContentPane().setScrollVisible(false);
        Container welcome = FlowLayout.encloseCenter(
                new Label("Add your post ", "WelcomeWhite")
        );

        super.addSideMenu(Res);
        TextField status = new TextField("status", "status", 20, TextField.ANY);
        TextField titre = new TextField("titre", "titre", 20, TextField.ANY);
        //  TextField id_user = new TextField("id_user", "id_user", 20, TextField.ANY) ;
        // TextField date_creation = new TextField("date", "date", 20, TextField.ANY) ;

        status.getAllStyles().setMargin(LEFT, 0);
        titre.getAllStyles().setMargin(LEFT, 0);
        //id_user.getAllStyles().setMargin(LEFT, 0);
        //  date_creation.getAllStyles().setMargin(LEFT, 0);

        Button signupButton = new Button("ajouter");
        signupButton.addActionListener(e -> {
            if (status.getText().length()<1 || titre.getText().length()<1) {
                ToastBar.showErrorMessage("Les champs ne sont pas valides");
            } else {
                PublicationService.getInstance().ajouterpub(status, titre, Res);
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
                BorderLayout.center(status),
                BorderLayout.center(titre),
                signupButton
        );

        add(BorderLayout.CENTER, x);

        //  for low res and landscape devices
        x.setScrollableY(true);
        x.setScrollVisible(false);

    }
}
