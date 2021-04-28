/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ruben
 */
public class metodosDB {

    public static ArrayList<Puntuacion> ranking = new ArrayList<>();

    public static Connection conexion;

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "puntuacionesDB.db";
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (conn != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
        return conn;
    }

    /**
     * select all rows in the warehouses table
     *
     * @return String[2][ranking.size]
     */
    public static String[][] recuperarPuntuaciones() {

        String sql = "SELECT * FROM RANKING ORDER BY puntuacion DESC";
        conexion = connect();

        try (
                Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {

                String nombres = rs.getString("nombre");
                int numeros = rs.getInt("puntuacion");
                ranking.add(new Puntuacion(nombres, numeros));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String[][] puntajeArray = new String[ranking.size()][2];
        for (int i = 0; i < ranking.size(); i++) {
            puntajeArray[i][0] = ranking.get(i).getNombre();
            puntajeArray[i][1] = "" + ranking.get(i).getNumero();

        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(metodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puntajeArray;
    }

    public static void insertarPuntuacion(Puntuacion registro) {

        try {

            conexion = connect();
            String sql = "INSERT INTO RANKING (nombre, puntuacion) values('"+ registro.getNombre() + "', " + registro.getNumero() + ")";
            
            Statement stmt = conexion.createStatement();
            stmt.execute(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(metodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(recuperarPuntuaciones()));
//        Puntuacion nueva=new Puntuacion("Ruben",20);
//        insertarPuntuacion(nueva);
//        System.out.println(Arrays.toString(recuperarPuntuaciones()));
//        
    }
    
}
