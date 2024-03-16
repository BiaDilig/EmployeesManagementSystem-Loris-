package EmailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.*;

public class SMTP {

    public static void sendOTP(String recipientEmail) {
        String senderEmail = "employeemanagementsystemloris@gmail.com";
        String senderPassword = "xaqv dzyy mxpy ytxh";

        int otp = generateOTP();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

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

          
            storeOTPInDatabase(recipientEmail, otp);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static int generateOTP() {
        int min = 100000;
        int max = 999999;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static void storeOTPInDatabase(String recipientEmail, int otp) {
     
        String url = "jdbc:mysql://localhost:3306/lorisems";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE admin SET otp = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, otp);
                stmt.setString(2, recipientEmail);
                stmt.executeUpdate();
                System.out.println("OTP stored in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
