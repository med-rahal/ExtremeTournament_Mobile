/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author MR
 */
public class JavaMail extends Thread {

       public static void sendMail(String to) throws Exception {
        String host="";
        final String user="xtreametournamnet@gmail.com";
        final String password="Xtreametournamnet22";
        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Reclamation sur Extreme Tournament");
            message.setContent(
                    "<h1 style =\"color:black\" > Nous tenons à vous informer que votre compte a été crée merci de choisir notre application  </h1> <br/> <img width=\"50%\" height=\"50%\">",
                    "text/html");

            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}
}
}
