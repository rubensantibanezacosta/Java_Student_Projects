/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author ruben
 */
public class SelectApp {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "C:/Users/ruben/Desktop/DAM/Programacion/UD2/GitExample/program20-21/Hiztegia/db.db";
        Connection conn = null;
      try {
     
     
     conn = DriverManager.getConnection("jdbc:sqlite:"+url);
     if (conn!=null) {
         System.out.println("Conectado");
     }
 }catch (SQLException ex) {
     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
 }
        return conn;
    }

    /**
     * select all rows in the warehouses table
     */
    public void selectAll() {
        String sql = "SELECT * FROM Hiztegia WHERE Castellano like 'rueda'";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t"
                        + rs.getString("Castellano") + "\t"
                        + rs.getString("Euskera") + "\t"
                        + rs.getString("English"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SelectApp app = new SelectApp();
        app.selectAll();
    }

}
