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
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.PublicationService;
import com.mycompany.services.SessionManager;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author ASUS
 */
public class displaypubform extends BaseForm {

    Form current;

    public displaypubform(Resources res) {

        super(BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        getTitleArea().setUIID("WalkthruTab1");
        setTitle("Forum");
        getContentPane().setScrollVisible(false);

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("BackgroundRect.png"), "", "", res);

        super.addSideMenu(res);
        // Welcome current user
        System.out.println("user connecté id =" + SessionManager.getId_user());

        System.out.println("user connecté username =" + SessionManager.getUsername());

        System.out.println("user connecté password =" + SessionManager.getPass());

        System.out.println("user connecté email =" + SessionManager.getEmail());

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Posts", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Add Post", barGroup);
        liste.setUIID("SelectBar");
        liste.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            addpublicationform addpubf = new addpublicationform(res);
            addpubf.show();
            refreshTheme();

        });
        RadioButton partage = RadioButton.createToggle("Stats", barGroup);
        partage.setUIID("SelectBar");
        partage.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            StatsForm stats = new StatsForm(res);
            stats.show();
            refreshTheme();

        });

        Label arrow = new Label(res.getImage(""), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            displaypubform a = new displaypubform(res);
            a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        //Appel affichage methode
        ArrayList<Publication> list = PublicationService.getInstance().affichagepub();

        for (Publication rec : list) {
            String urlImage = "arrow.png";

            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            addButton(urlim, rec, res);

            ScaleImageLabel image = new ScaleImageLabel(urlim);

            Container containerImg = new Container();

            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }

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

        swipe.addTab("", res.getImage(""), page1);

    }

    public void bindButtonSelection(Button btn, Label l) {

        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addButton(Image img, Publication rec, Resources res) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        Label typeTxt = new Label("titre : " + rec.getTitre(), "RemainingTasks");
        Label etatTxt = new Label("status : " + rec.getStatus(), "RemainingTasks");

        createLineSeparator(CENTER);

        //supprimer button
        Label lSupprimer = new Label("");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);

        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);

        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Suppression");

            if (dig.show("Suppression", "Vous voulez supprimer cette publication ?", "Annuler", "Oui")) {
                dig.dispose();
            } else {
                dig.dispose();
            }
            System.out.println(rec.getId_publication());
            //n3ayto l suuprimer men service Reclamation
            if (PublicationService.getInstance().deletepub(rec.getId_publication())) {
                new displaypubform(res).show();
//            
            }
        });

        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);

        lModifier.addPointerPressedListener(l -> {
            System.out.println("hello update");
            new ModifierPubForm(res, rec).show();
            rec.setTitre(rec.getTitre());
            rec.setStatus(rec.getStatus());
            rec.setId_publication(rec.getId_publication());
            rec.setDate_creation(rec.getDate_creation());
            rec.setImage(rec.getImage());
            rec.setId_user(SessionManager.getId_user());
        });

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                BoxLayout.encloseX(typeTxt),
                BoxLayout.encloseX(etatTxt),
                BoxLayout.encloseX(lModifier, lSupprimer)
        ));

        add(cnt);
    }

}
