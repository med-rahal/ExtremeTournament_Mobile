package com.mycompany.gui;
        
        
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
import com.mycompany.entities.Publication;
import com.mycompany.services.PublicationService;
import com.mycompany.services.SessionManager;

/**
 *
 * @author MR
 */
public class ModifierPubForm extends BaseForm {
 Form current;
    public ModifierPubForm(Resources res , Publication c) {
         super(BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
   
        //Toolbar tb = new Toolbar(true);
        //current = this ;
        //setToolbar(tb);
        getTitleArea().setUIID("WalkthruTab1");
        setTitle("Modifier Publication");
        getContentPane().setScrollVisible(false);
       
       
      super.addSideMenu(res);
       
       // TextField type = new TextField(r.getType() , "Objet" , 20 , TextField.ANY);
        // name = new TextField(r.getName() , "Description" , 20 , TextField.ANY);
        TextField titre = new TextField(c.getTitre(), "Titre" , 20 , TextField.ANY);
        TextField status = new TextField((c.getStatus()) , "Status" , 20 , TextField.ANY);
       
             
       
        titre.setUIID("NewsTopLine");
        status.setUIID("NewsTopLine");
      
       
        titre.setSingleLineTextArea(true);
        status.setSingleLineTextArea(true);
        
       
       
       Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   {
           c.setTitre(titre.getText());
           c.setStatus(status.getText());
           c.setDate_creation(c.getDate_creation());
           c.setId_user(SessionManager.getId_user());
         
          // r.setType(type.getText());
           //r.setName(name.getText());
           
          /* if(etatCombo.getSelectedIndex() == 0 ) {
               r.setEtat(0);
           }
           else
               r.setEtat(1);*/
     
       
       //appel fonction modfier reclamation men service
       
       if(PublicationService.getInstance().modifierpub(c)) { // if true
//           new displaypubform(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new displaypubform(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
  
       Label l1 = new Label();
       
        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(titre),
              //  createLineSeparator(),
                new FloatingHint(status),
             
              
               // createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
               
               
        );
       
        add(content);
        show();
       
       
    }   
    
}