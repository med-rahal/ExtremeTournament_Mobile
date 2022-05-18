/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class Publication {

    private int id_publication;
    private String titre;
    private String date_creation;
    private String status;
    private String image;
    private int id_user;

    public Publication() {
    }

    public Publication(String titre, String date_creation, String status, int id_user) {
        this.titre = titre;
        this.date_creation = date_creation;
        this.status = status;
        this.id_user = id_user;
    }

    public Publication(int id_publication, String titre, String date_creation, String status, String image, int id_user) {
        this.id_publication = id_publication;
        this.titre = titre;
        this.date_creation = date_creation;
        this.status = status;
        this.image = image;
        this.id_user = id_user;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
