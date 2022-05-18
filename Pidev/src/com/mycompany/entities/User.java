/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author MR
 */
public class User {
    
    private int id_user ;
    private String nom ;
    private String prenom ;
    private String username ;
    private String date_naissance;
    private String sexe ;
    private String roles ;
    private String email ;
    private String password ;
    private String tel;
    private String adresse;

    public User(int id_user, String nom, String prenom, String username, String date_naissance, String sexe, String roles, String email, String password, String tel, String adresse) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.sexe = sexe;
        this.roles = roles;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User() {
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", date_naissance=" + date_naissance + ", sexe=" + sexe + ", roles=" + roles + ", email=" + email + ", password=" + password + ", tel=" + tel + ", adresse=" + adresse + '}';
    }
    
    
}
