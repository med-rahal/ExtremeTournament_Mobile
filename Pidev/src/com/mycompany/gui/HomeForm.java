/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.ListTournament;
import com.mycompany.gui.TournamentAddForm;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ibrahim
 */
public class HomeForm extends Form {

    //Form current;
    public HomeForm(Resources res) {
        super(new BorderLayout());
        setTitle("Tournament ");

        //  add(new Label("Choose what you want "));
        Button btnAddTournois = new Button("Add Tournament ");
        Button btnListTasks = new Button("List Tournament");
     
        btnAddTournois.addActionListener(e -> new TournamentAddForm(res).show());
        btnListTasks.addActionListener(e -> new ListTournament(res).show());
        Container x = BoxLayout.encloseY(
                btnAddTournois, btnListTasks
        );

        add(BorderLayout.CENTER, x);

        //  for low res and landscape devices
        x.setScrollableY(true);
        x.setScrollVisible(false);

        //   addAll(btnAddTournois, btnListTasks);
    }

}
