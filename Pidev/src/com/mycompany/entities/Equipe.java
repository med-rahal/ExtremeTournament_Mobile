/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author acer
 */
public class Equipe {

    
    
    private String nom_equipe ;
    private String categorie ;
     private int id_user ;
      private int nb_participants ;
       private String image ;
         private String Epass ;
         private int id_match ;

    public Equipe() {
        
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  
    public String  getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEpass() {
        return Epass;
    }

    public void setEpass(String Epass) {
        this.Epass = Epass;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public Equipe(String nom_equipe, String categorie, int id_user, int nb_participants, String image, String Epass, int id_match) {
        this.nom_equipe = nom_equipe;
        this.categorie = categorie;
        this.id_user = id_user;
        this.nb_participants = nb_participants;
        this.image = image;
        this.Epass = Epass;
        this.id_match = id_match;
    }

  
   

    

   

    
       
    
}
