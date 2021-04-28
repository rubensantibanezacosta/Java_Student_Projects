/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author ruben
 */
public class Connect {
   
     String url = "puntuacionesDB.db";
Connection conn;

public void connect() throws ClassNotFoundException{
 try {
     
     
     conn = DriverManager.getConnection("jdbc:sqlite:"+url);
     if (conn!=null) {
         System.out.println("Conectado");
     }
 }catch (SQLException ex) {
     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
 }
}
 public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    public static void main(String[] args) {
        Connect conexion=new Connect();
         try {
             conexion.connect();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
         }
         conexion.close();
        
    }
    
}
