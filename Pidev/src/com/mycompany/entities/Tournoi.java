/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ibrahim
 */


public class Tournoi {
  public int id_t;
  public String nomT;
  public String emplacementT;
  public String dateT;
  public int id_user;

    public Tournoi() {
    }

    public Tournoi(String nomT, String emplacementT) {
        this.nomT = nomT;
        this.emplacementT = emplacementT;
    }
    
    

    public Tournoi(int id_t, String nomT, String emplacementT, String dateT, int id_user) {
        this.id_t = id_t;
        this.nomT = nomT;
        this.emplacementT = emplacementT;
        this.dateT = dateT;
        this.id_user = id_user;
    }

    public Tournoi(String nomT, String emplacementT, String dateT, int id_user) {
        this.nomT = nomT;
        this.emplacementT = emplacementT;
        this.dateT = dateT;
        this.id_user = id_user;
    }

    public Tournoi(String nomT, String emplacementT, String dateT) {
        this.nomT = nomT;
        this.emplacementT = emplacementT;
        this.dateT = dateT;
    }
    

    public int getId_t() {
        return id_t;
    }

    public String getNomT() {
        return nomT;
    }

    public String getEmplacementT() {
        return emplacementT;
    }

    public String getDateT() {
        return dateT;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_t(int id_t) {
        this.id_t = id_t;
    }

    public void setNomT(String nomT) {
        this.nomT = nomT;
    }

    public void setEmplacementT(String emplacementT) {
        this.emplacementT = emplacementT;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id_t=" + id_t + ", nomT=" + nomT + ", emplacementT=" + emplacementT + ", dateT=" + dateT + ", id_user=" + id_user + '}';
    }

   

}
