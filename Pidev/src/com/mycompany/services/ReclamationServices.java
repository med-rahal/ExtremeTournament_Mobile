/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reclamation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MR
 */
public class ReclamationServices {

    //singleton 
    public static ReclamationServices instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ReclamationServices getInstance() {
        if (instance == null) {
            instance = new ReclamationServices();
        }
        return instance;
    }

    public ReclamationServices() {
        req = new ConnectionRequest();

    }

    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {

        // http://127.0.0.1:8000/addreclamationJSON/new?description_r=dxxd&type=mauvaisservice&etat_r=nontraitee&email=rahalmed467@gmail.com&date_r=now&id_user=9
        String url = Statics.BASE_URL + "addreclamationJSON/new?description_r=" + reclamation.getDescriptionR() + "&type=" + reclamation.getType() + "&etat_r=" + reclamation.getEtat() + "&email=" + reclamation.getEmail() + "&date_r=" + reclamation.getDateR() + "&id_user=" + reclamation.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//

    }

    //affichage
    public ArrayList<Reclamation> affichageReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "Allreclamations";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();

                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id_reclam").toString());

                        String type = obj.get("type").toString();

                        String description = obj.get("descriptionR").toString();
                        String etat = obj.get("etatR").toString();

                        re.setId_reclam((int) id);
                        re.setDescriptionR(description);
                        re.setEtat(etat);
                        re.setType(type);
                        //Date 
                        String date = obj.get("dateR").toString();
                        String dateString = date.substring(0, 10);

                        re.setDateR(dateString);

                        //insert data into ArrayList result
                        result.add(re);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }


    //Delete 
    public boolean deleteReclamation(int id) {
        String url = Statics.BASE_URL + "deleterecJSON/"+id+"";

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
    public boolean modifierReclamation(Reclamation reclamation) {
        //http://127.0.0.1:8000/updatereclamationJSON/71?description_r=dddd&type=mauvaisservice&etat_r=nontrait%C3%A9e&email=rahalmed467@gmail.com&date_r=2022-05-07&id_user=9
        String url = Statics.BASE_URL +"updatereclamationJSON/"+reclamation.getId_reclam()+"?description_r="+reclamation.getDescriptionR()+"&type="+reclamation.getType()+"&etat_r="+reclamation.getEtat()
                +"&email="+reclamation.getEmail()+"&id_user="+SessionManager.getId_user();
        
        System.out.println(url);
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
