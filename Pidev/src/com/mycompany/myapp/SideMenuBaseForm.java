/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.LoginForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.services.SessionManager;

/**
 *
 * @author MR
 */
public abstract class SideMenuBaseForm extends Form {
    
       public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("user_picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(SessionManager.getUsername(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Game", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Tournaments", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Team ", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Poule", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Forum", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Reclamations", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Account", FontImage.MATERIAL_SETTINGS,e -> new ProfileForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> {
                                                                                                            new LoginForm(res).show();
                                                                                                            SessionManager.clear();
                                                                                                            Storage.getInstance().clearStorage();
                                                                                                            Storage.getInstance().clearCache();
                                                                                                        });
    }
    
    protected abstract void showOtherForm(Resources res);
}
    

