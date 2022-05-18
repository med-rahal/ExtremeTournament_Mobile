/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.entities.Reclamation;
import com.mycompany.entities.User;
import com.mycompany.gui.BaseForm;
import static com.mycompany.services.ReclamationServices.resultOk;
import static com.mycompany.services.UserServices.resultOK;
import com.mycompany.utils.Statics;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class PublicationService extends BaseForm {

    public static PublicationService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }

    public PublicationService() {

        req = new ConnectionRequest();
    }

    public void ajouterpub(TextField status, TextField titre, Resources res) {

        String url = Statics.BASE_URL + "addpubmob?status=" + status.getText() + "&titre=" + titre.getText() + "&id_user=" + SessionManager.getId_user() + "";

        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener(e -> {
            resultOK = req.getResponseCode() == 200;
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("data ===>" + responseData);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Publication> affichagepub() throws ClassCastException {
        ArrayList<Publication> result = new ArrayList<>();
        String url = (Statics.BASE_URL + "listpubMob");
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapEquipe = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapEquipe.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                        Publication e = new Publication();

                        float id = Float.parseFloat(obj.get("id_publication").toString());

                        String status = obj.get("status").toString();

                        String titre = obj.get("titre").toString();
                       
                        /*    String date =  obj.get("date_creation").toString();*/
                        
                        e.setStatus(status);
                        e.setTitre(titre);
                        e.setId_publication((int) id); 
                
                        result.add(e);

                    }

                } catch (IOException ex) {

                }
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean deletepub(int id) {
        String url = Statics.BASE_URL + "deletepubmob/" + id + "";

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    //Update 
    public boolean modifierpub(Publication reclamation) {
        //http://127.0.0.1:8000/Updatepubmob/?titre=dddd&type=mauvaisservice&etat_r=nontrait%C3%A9e&email=rahalmed467@gmail.com&date_r=2022-05-07&id_user=9
        String url = Statics.BASE_URL + "Updatepubmob/" + reclamation.getId_publication() + "?titre=" + reclamation.getTitre() + "&status=" + reclamation.getStatus();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

}
