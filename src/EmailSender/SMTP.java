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
        
        // Sender's email credentials
        String senderEmail ="employeemanagementsystemloris@gmail.com";
        String senderPassword ="xaqv dzyy mxpy ytxh";
            
        // Recipient's email
       
       //String recipientEmail = "pagtalunan.aldreenfranz.lachica@gmail.com";
          
        // Generate OTP
        int otp = generateOTP();

        // Email configuration for Gmail SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
        properties.put("mail.smtp.port", "587"); // Port
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Your One Time Password (OTP)");

            // Set OTP in the message body
            message.setText("Your OTP is: " + otp);

            // Send message
            Transport.send(message);
            System.out.println("OTP sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Method to generate a 6-digit OTP
    private static int generateOTP() {
        int min = 100000; // minimum OTP value
        int max = 999999; // maximum OTP value
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
