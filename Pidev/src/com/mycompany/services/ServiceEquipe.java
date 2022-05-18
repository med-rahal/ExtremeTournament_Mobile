/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.mycompany.entities.Equipe;
import com.mycompany.entities.User;
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
 * @author acer
 */
public class ServiceEquipe {

    public static ServiceEquipe instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }

    public ServiceEquipe() {

        req = new ConnectionRequest();
    }

    public void ajouterEquipe(TextField nom_equipe, String categorie, int id_user, TextField nb_participants, TextField image, TextField Epass, int id_match, Resources res) {

        String url = Statics.BASE_URL + "addteamBack?nom_equipe=" + nom_equipe.getText() + "&categorie="+categorie+ "&id_user=" + id_user + "&nb_participants=" + nb_participants.getText()
                + "&image=" + image.getText() + "&Epass=" + Epass.getText() + "&id_match="+id_match;

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

    public ArrayList<Equipe> affichageEquipe() throws ClassCastException {
        ArrayList<Equipe> result = new ArrayList<>();
        String url = (Statics.BASE_URL + "listTeamBack");
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
                        Equipe e = new Equipe();

                        String nom_equipe = obj.get("nom_equipe").toString();
                    //    float id_match = Float.parseFloat(obj.get("id_match").toString());

                        float nb_participants = Float.parseFloat(obj.get("nb_participants").toString());
                        String categorie = obj.get("categorie").toString();
                        String image = obj.get("image").toString();
                        String Epass = obj.get("Epass").toString();

                        e.setNom_equipe(nom_equipe);
                        e.setNb_participants((int) nb_participants);
                        e.setCategorie(categorie);
                        e.setImage(image);
                        e.setEpass(Epass);
                       // e.setId_match((int)id_match);
                        

                        result.add(e);

                    }

                } catch (IOException ex) {

                }
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

//Delete 
    public boolean deleteEquipe(String nom_equipe) throws NullPointerException {
        String url = Statics.BASE_URL + "deleteTeam/"+nom_equipe+"";

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
    public boolean modifierEquipe(Equipe equipe) {
        //http://127.0.0.1:8000/UpdateTeam/bejaaa?nom_equipe=tunis&nb_participants=4&image=src.png&categorie=Esport&Epass=12345zz
        String url = Statics.BASE_URL + "UpdateTeam/" + equipe.getNom_equipe() + "?nom_equipe=" + equipe.getNom_equipe() + "&nb_participants=" + equipe.getNb_participants() + "&image=" + equipe.getImage() + "&categorie=" + equipe.getCategorie() + "&Epass=" + equipe.getEpass();
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
