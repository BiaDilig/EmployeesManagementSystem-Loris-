package EmailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import Login.Login;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class SMTP {
    
    
     public static void sendOTP(String recipientEmail){
   // public static void main(String[] args) {
        
        
        String senderEmail ="employeemanagementsystemloris@gmail.com";
        String senderPassword ="xaqv dzyy mxpy ytxh";
            
       
        int otp = generateOTP();

        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
        properties.put("mail.smtp.port", "587"); // Port
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
           
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(senderEmail));

           
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

           
            message.setSubject("Your One Time Password (OTP)");

          
            message.setText("Your OTP is: " + otp);

            
            Transport.send(message);
            System.out.println("OTP sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

   
    private static int generateOTP() {
        int min = 100000; 
        int max = 999999; 
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
