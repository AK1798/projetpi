/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mounira.utils;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bouss
 */
public class Mailling {
        private static String username = "boussettaoussama7@gmail.com";
    private static String password = "55437981123@filo";

    public static void envoyer() {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("boussettaoussama7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("abdelkader.borcheni@esprit.tn"));
            message.setSubject("Bad Words");
            message.setText("Be careful the next time you will be banned !!! ");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
//Etape 4 : Tester la méthode
    
}
