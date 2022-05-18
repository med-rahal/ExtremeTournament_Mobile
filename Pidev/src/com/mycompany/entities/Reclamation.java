/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author MR
 */
public class Reclamation {

    private int id_reclam;
    private String descriptionR,type;
    private String dateR,email;
    private String etat;
    private int iduser;

    public Reclamation() {
    }

        public Reclamation(String descriptionR, String type, String dateR, String email, String etat, int iduser) {
        this.descriptionR = descriptionR;
        this.type = type;
        this.dateR = dateR;
        this.email = email;
        this.etat = etat;
        this.iduser = iduser;
    }

    public Reclamation(int id_reclam, String descriptionR, String type, String email, String etat, int iduser) {
        this.id_reclam = id_reclam;
        this.descriptionR = descriptionR;
        this.type = type;
        this.email = email;
        this.etat = etat;
        this.iduser = iduser;
    }

 
    
    
    public Reclamation(int id_reclam, String descriptionR, String type, String dateR, String email, String etat, int iduser) {
        this.id_reclam = id_reclam;
        this.descriptionR = descriptionR;
        this.type = type;
        this.dateR = dateR;
        this.email = email;
        this.etat = etat;
        this.iduser = iduser;
    }

    public int getId_reclam() {
        return id_reclam;
    }

    public String getDescriptionR() {
        return descriptionR;
    }

    public String getType() {
        return type;
    }

    public String getDateR() {
        return dateR;
    }

    public String getEmail() {
        return email;
    }

    public String getEtat() {
        return etat;
    }

    public int getIduser() {
        return iduser;
    }

    public void setId_reclam(int id_reclam) {
        this.id_reclam = id_reclam;
    }

    public void setDescriptionR(String descriptionR) {
        this.descriptionR = descriptionR;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclam=" + id_reclam + ", descriptionR=" + descriptionR + ", type=" + type + ", dateR=" + dateR + ", email=" + email + ", etat=" + etat + ", iduser=" + iduser + '}';
    }
    
    
    
    

}
