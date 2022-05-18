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
import com.mycompany.entities.User;
import com.mycompany.utils.Statics;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MR
 */
public class UserServices {

    public static UserServices instance = null;

    public static boolean resultOK = true;

    private ConnectionRequest req;

    public ArrayList<User> users;

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }

    public UserServices() {
        req = new ConnectionRequest();
    }

    //Signup
    public void signup(TextField nom, TextField prenom, TextField username, String date_naissance, TextField sexe, TextField email, TextField passw, TextField tel, TextField adresse, String image, Resources res) {

        String url = BASE_URL + "addUserJSON/new?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&username=" + username.getText() + "&date_naissance=" + date_naissance + "&sexe=" + sexe.getText()
                + "&email=" + email.getText() + "&passw=" + passw.getText() + "&telephone=" + tel.getText() + "&adresse=" + adresse.getText() + "&image=" + image;

        req.setUrl(url);

        req.addResponseListener(e -> {
            resultOK = req.getResponseCode() == 200;
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("data ===>" + responseData);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField email, TextField password, Resources rs) {

        String url = BASE_URL + "user/login/value=?username=" + email.getText().toString() + "&passw=" + password.getText().toString();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);

        req.addResponseListener(e -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Failed Authentification", "Username or password doesn't match", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println("map user ==" + user);
                    //Session 
                   
                    float id = Float.parseFloat(user.get("id_user").toString());
                    System.out.println("idUser ==" + id);
                    SessionManager.setId_user((int) id);    
                    SessionManager.setNom(user.get("nom").toString());
                    SessionManager.setPrenom(user.get("prenom").toString());
                    SessionManager.setUsername(user.get("username").toString());
                    SessionManager.setPass(user.get("passw").toString());
                    SessionManager.setSexe(user.get("sexe").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    SessionManager.setTel(user.get("tel").toString());
                    SessionManager.setAdresse(user.get("adresse").toString());

                    //  System.out.println("hh");
                    if (user.get("image") != null) {
                        SessionManager.setImage(user.get("image").toString());
                    }
                    //image

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public static void updateUser(String nom, String prenom, String username, String date_naissance, String sexe, String email, String passw, String tel, String adresse, String image) {

        String id = String.valueOf(SessionManager.getId_user());
        // Date date_n = SessionManager.getDate_naissance();
        String url = BASE_URL + "updateUserJSON/" + id + "?nom=" + nom + "&prenom=" + prenom + "&username=" + username + "&date_naissance=" + date_naissance + "&sexe=" + sexe
                + "&email=" + email + "&passw=" + passw + "&telephone=" + tel + "&adresse=" + adresse + "&image=" + image;
        MultipartRequest req = new MultipartRequest();

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id_user", id);
        req.addArgument("nom", nom);
        req.addArgument("prenom", prenom);
        req.addArgument("username", username);
        req.addArgument("date_naissance", date_naissance);
        req.addArgument("email", email);
        req.addArgument("passw", passw);
        req.addArgument("telephone", tel);
        req.addArgument("adresse", adresse);
        req.addArgument("image", image);
        req.addResponseListener((response) -> {
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            Dialog.show("Succes", "Your account has been updated", "OK", null);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        SessionManager.clear();
    }

    public boolean deleteUser(int id) {
        String url = BASE_URL + "deleteuserJSON/"+ id + "";

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        Dialog.show("Success", "Your account has been deleted", "OK", null);
        SessionManager.clear();
        return resultOK;
    }

    public ArrayList<User> recherche(String username) {
//        recherche?username=MR
        String url = Statics.BASE_URL +"recherche?username="+username+"";
        JSONParser jsonp;
        jsonp = new JSONParser();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = ParsingUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public ArrayList<User> ParsingUser(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MapUser
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) MapUser.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();

                float id = Float.parseFloat(obj.get("idUser").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();
                String username = obj.get("username").toString();
             //   String date = obj.get("date_naissance").toString();
               // String dateString = date.substring(0, 10);
                String sexe = obj.get("sexe").toString();
                String roles = obj.get("roles").toString();
                String email = obj.get("email").toString();
                String tel = obj.get("tel").toString();
                String adresse = obj.get("adresse").toString();
                u.setId_user((int) id);
                u.setNom(nom);
                u.setPrenom(prenom);
                u.setUsername(username);
                u.setSexe(sexe);
                u.setRoles(roles);
                u.setEmail(email);
                u.setTel(email);
                u.setAdresse(adresse);
             //   u.setDate_naissance(dateString);

                users.add(u);
            }
            System.out.println(users);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

}
