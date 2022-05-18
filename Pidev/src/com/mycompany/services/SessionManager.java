package com.mycompany.services;

import com.codename1.io.Preferences;
import java.util.Date;

public class SessionManager {

    public static Preferences pref;

    private static int id_user;
    private static String nom;
    private static String prenom;
    private static String username;
    private static Date date_naissance;
    private static String sexe;
    private static String roles;
    private static String email;
    private static String passw;
    private static String tel;
    private static String adresse;
    private static String image;

    public static Preferences getPref() {
        return pref;
    }

    public static int getId_user() {
        return id_user;
    }

    public static String getNom() {
        return nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static String getUsername() {
        return username;
    }

    public static Date getDate_naissance() {
        return date_naissance;
    }

    public static String getSexe() {
        return sexe;
    }

    public static String getRoles() {
        return roles;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPass() {
        return passw;
    }

    public static String getTel() {
        return tel;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static String getImage() {
        return image;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static void setId_user(int id_user) {
        SessionManager.id_user = id_user;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static void setUsername(String username) {
        SessionManager.username = username;
    }

    public static void setDate_naissance(Date date_naissance) {
        SessionManager.date_naissance = date_naissance;
    }

    public static void setSexe(String sexe) {
        SessionManager.sexe = sexe;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static void setPass(String password) {
        SessionManager.passw = passw;
    }

    public static void setTel(String tel) {
        SessionManager.tel = tel;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static void setImage(String image) {
        SessionManager.image = image;
    }

    public static void clear() {
        System.out.println("Clearing WebDriverManager preferences");
        pref.clearAll();
    }

}
