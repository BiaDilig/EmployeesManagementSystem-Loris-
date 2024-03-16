package Connection;

import java.sql.*;
import javax.swing.JOptionPane;

public class JDBConnection {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/lorisems?zeroDateTimeBehavior=CONVERT_TO_NULL";
    static final String USER = "root";
    static final String PASSWORD = "";
    
    
    
   // public static void main(String Args[]){
   public static Connection connectDB(){
         
        
        Connection conn = null;
        
            try{
                conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
             
                //Remove message if publish 
              //  JOptionPane.showMessageDialog(null," DATABASE CONNECTED");
                return conn;
                
            // updated naa to remie ;>
               
             //   System.out.print("connected");
            }catch(Exception ex){
               
               //System.out.println("DB is having some error. Please try again later.");
                
               JOptionPane.showMessageDialog(null,ex);
                
                return null;
            }
    }
}