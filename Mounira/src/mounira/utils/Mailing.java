
package mounira.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Molka
 */
public class Mailing{
    
    
    
private static Message prepareMessage(javax.mail.Session session, String myAccountEmail,String recepient,String subject,String messagevalue){
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            
            message.setText(messagevalue);
            return message;
        } catch (MessagingException ex) {
           // Logger.getLogger(JavamailUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }
        
        
    
     public static void sendMail(String recepient,String subject,String messagevalue) throws Exception{
        System.out.println("Preparing to send:");
        Properties properties = new Properties();
        

        
        String myAccountEmail ="allintravelapp@gmail.com";
        String password ="rootroot1";
        
        properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        
        
        
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message message = prepareMessage(session,myAccountEmail,recepient,subject,messagevalue);
        
        Transport.send(message);
        System.out.println("message send successfully");
    }
     
     
     
     
        
     
    
}
