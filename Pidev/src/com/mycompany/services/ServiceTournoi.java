/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Tournoi;
import com.mycompany.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.properties.UiBinding.DateConverter;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ibrahim
 */
public class ServiceTournoi {
    public static boolean resultOk = true;
     public ArrayList<Tournoi> tasks;
    
    public static ServiceTournoi instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTournoi() {
         req = new ConnectionRequest();
    }

    public static ServiceTournoi getInstance() {
        if (instance == null) {
            instance = new ServiceTournoi();
        }
        return instance;
    }

    /////////////////// Ajout ///////////////
    public Boolean addTournoi(Tournoi t) {
       String url = Statics.BASE_URL + "AddM?nom_t=" +t.getNomT() + "&emplacement_t=" + t.getEmplacementT();
       
//       req.setPost(false);
//       req.addArgument("Name Of Tournament", t.getNomT()+"");
//       req.addArgument("Emplacement", t.getEmplacementT()+"");
//
//       req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;

        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
    }

    public ArrayList<Tournoi> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
            j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Tournoi t = new Tournoi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_t((int)id);
               // t.setNomT(((int)Float.parseFloat(obj.get("status").toString())));
               
                if (obj.get("Name Of Tournament")==null)
              t.setNomT("null");
                else
                    t.setNomT(obj.get("Name Of Tournament").toString());
                
                if (obj.get("Emplacement")==null)
              t.setEmplacementT("null");
                else
                    t.setEmplacementT(obj.get("Emplacement").toString());
                  
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    


    ////////////// Affichage //////////////////////////////
    public ArrayList<Tournoi>affichageTournoi() {
        
//        ArrayList<Tournoi> result = new ArrayList<>();
//        
//        String url = Statics.BASE_URL+"/DisplayM";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Tournoi To = new Tournoi();
//                        
//                        //dima id fi codename one float 5outhouha
//                        float id = Float.parseFloat(obj.get("id_t").toString());
//                        
//                        String nomT = obj.get("nomT").toString();
//                        
//                        String emplacementT = obj.get("emplacementT").toString();
//                        
//                        To.setId_t((int)id);
//                        To.setNomT(nomT);
//                        To.setEmplacementT(emplacementT);
//                        
//                        //Date 
//                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
//                        
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                        To.setDateT(dateString);
//                        
//                        //insert data into ArrayList result
//                        result.add(To);
//                       
//                    
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;

        ArrayList<Tournoi> result = new ArrayList<>();
       
        String url = Statics.BASE_URL+"DisplayM";
        req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
               
                try {
                    Map<String,Object>mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapEvents.get("root");
                   
                     for(Map<String, Object> obj : listOfMaps) {
                        Tournoi s = new Tournoi();
                       
                       //dima id fi codename one float 5outhouha
                float id = Float.parseFloat(obj.get("id_t").toString());
                s.setId_t((int)id);
                s.setNomT(obj.get("nomT").toString());
                s.setEmplacementT(obj.get("emplacementT").toString());
//                String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
//                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
//                        
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateString = formatter.format(currentTime);
//                        s.setDateT(dateString);

             
                //Ajouter la tâche extraite de la réponse Json à la liste
                       
                       
                       
                       
                        //Date
                       /* String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                       
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                       
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/
                        //String dateString = formatter.format(currentTime);
                        //re.setDate(dateString);
                       
                        //insert data into ArrayList result
                        result.add(s);
                       
                   
                    }
                   
                }catch(Exception ex) {
                   
                    ex.printStackTrace();
                }
           
            }
        });
       
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
       
       
    }  
        
        
    
    public Tournoi DetailRecalamation( int id_t , Tournoi Tournois) {
        
        String url = Statics.BASE_URL+"detailTournament?"+id_t;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                Tournois.setNomT(obj.get("nomT").toString());
                Tournois.setEmplacementT(obj.get("emplacementT").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return Tournois;
        
        
    }
    
    
      ///////////////////////////////////////////////// Update
    
    public boolean modifierTournament(Tournoi c) {
        String url = Statics.BASE_URL + "updateTournois?id_t="+c.getId_t() +"&nom_t=" +c.getNomT() + "&emplacement_t=" + c.getEmplacementT();

        req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
       
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
       
    }
        
    
    
      /////////////////////////////////Delete
    public boolean deleteService(int id_t ) {
        
        String url = Statics.BASE_URL +"DeleteMF/"+id_t+"";
       
        req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                   
                    req.removeResponseCodeListener(this);
            }
        });
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
        
        
        
        
        
        
        
        
        
        
  
}
