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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static model.Hiztegia.hiztegia;
import model.Terminoa;

/**
 *
 * @author ruben
 */
public class DbMetodoak {

    public static Connection conexion;

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "C:/Users/ruben/Desktop/DAM/Programacion/UD2/GitExample/program20-21/Hiztegia/db.db";
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
     */
    public static String[][] objetoHiztegia() {
       
        String sql = "SELECT * FROM Hiztegia";
        conexion=connect();

        try (
                Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                int id=rs.getInt("Id");
                String idioma1=rs.getString("Castellano");
                String idioma2=rs.getString("Euskera");
                String idioma3=rs.getString("English");
                hiztegia.add(new Terminoa(idioma1, idioma2, idioma3));
                
                

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String [][] diccionario=new String[3][hiztegia.size()];
        for (int i = 0; i < hiztegia.size(); i++) {
            diccionario[0][i]=hiztegia.get(i).getCastellano();
            diccionario[1][i]=hiztegia.get(i).getEuskera();
            diccionario[2][i]=hiztegia.get(i).getEnglish();
            
        }
        try {        
                conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }
    return diccionario;
    }

    public static String traducirPalabra(String palabra, String idiomaEntrada, String idiomaSalida) {
        String traduccion = new String();
        boolean encontrada=false;

        palabra = palabra.toLowerCase();

        String sql = "SELECT * FROM Hiztegia WHERE " + idiomaEntrada + "='" + palabra + "'";

        try {

            conexion = connect();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                traduccion = rs.getString(idiomaSalida);
                encontrada=true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NOT FOUND");
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (encontrada==false){
            return "NOT FOUND";
        }else{
        return traduccion;
        }
    }

    public static String autoIdioma(String palabra) {
        palabra = palabra.toLowerCase();
        String idioma = "Castellano";
        conexion = connect();

        String sql = "SELECT * FROM Hiztegia WHERE Castellano='" + palabra + "'";

        try {

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String result = rs.getString("Castellano");
                
                if (result.equals(palabra)) {

                    idioma = "Castellano";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql2 = "SELECT * FROM Hiztegia WHERE Euskera='" + palabra + "'";

        try {

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);

            while (rs.next()) {

                String result = rs.getString("Euskera");
                
                if (result.equals(palabra)) {

                    idioma = "Euskera";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql3 = "SELECT * FROM Hiztegia WHERE English='" + palabra + "'";

        try {

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql3);

            while (rs.next()) {
        
                String result = rs.getString("English");
                if (result.equals(palabra)) {

                    idioma = "English";
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idioma;
    }

    public static void borrarPalabra(String palabra) {
        palabra = palabra.toLowerCase();
        String traduccion = new String();

        palabra = palabra.toLowerCase();

        try {

            conexion = connect();
            String sql = "DELETE FROM Hiztegia WHERE Castellano ='" + palabra + "' OR Euskera='" + palabra + "' OR English='" + palabra + "'";
            Statement stmt = conexion.createStatement();
            int rows = stmt.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("Deleted");
                JOptionPane.showMessageDialog(null, "DELETED");
            } else {
                System.out.println("NOT FOUND");
                JOptionPane.showMessageDialog(null, "NOT FOUND");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void anadirEditarPalabra(String palabraCastellano, String palabraEuskera, String palabraEnglish) {
        palabraCastellano = palabraCastellano.toLowerCase();
        palabraEuskera = palabraEuskera.toLowerCase();
        palabraEnglish = palabraEnglish.toLowerCase();
        String busquedaCastellano = "NOT FOUND";
        String busquedaEuskera = "NOT FOUND";
        String busquedaEnglish = "NOT FOUND";
        int rowsCreated = 0;
        int rowsUpdated = 0;

        conexion = connect();

        try {

            String sql = "SELECT * FROM Hiztegia WHERE Castellano='" + palabraCastellano + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                busquedaCastellano = "ALREADY EXISTS";

            }

        } catch (SQLException ex) {
            System.out.println("NO EXISTE");
        }

        try {

            String sql = "SELECT * FROM Hiztegia WHERE Euskera='" + palabraEuskera + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                busquedaEuskera = "ALREADY EXISTS";

            }

        } catch (SQLException ex) {
            System.out.println("NO EXISTE");
        }

        try {

            String sql = "SELECT * FROM Hiztegia WHERE English='" + palabraEnglish + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                busquedaEnglish = "ALREADY EXISTS";

            }

        } catch (SQLException ex) {
            System.out.println("NO EXISTE");
        }

        //si el termino existe en los tres idiomas aviso
        if (busquedaCastellano.equals("ALREADY EXISTS") && busquedaEuskera.equals("ALREADY EXISTS") && busquedaEnglish.equals("ALREADY EXISTS")) {

            JOptionPane.showMessageDialog(null, "ALREADY EXISTS");
        } else {

//si el termino no existe lo creamos
            if (busquedaCastellano.equals("NOT FOUND") && busquedaEuskera.equals("NOT FOUND") && busquedaEnglish.equals("NOT FOUND")) {
                try {

                    String sql = "INSERT INTO Hiztegia ('Castellano','Euskera','English')values ('" + palabraCastellano + "','" + palabraEuskera + "','" + palabraEnglish + "')";

                    Statement stmt = conexion.createStatement();
                    rowsCreated = stmt.executeUpdate(sql);
                    if (rowsCreated > 0) {
                        System.out.println("DONE");
                        JOptionPane.showMessageDialog(null, "CREATED");
                    } else {
                        System.out.println("FAILED");
                        JOptionPane.showMessageDialog(null, "FAILED");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR");
                }

                //si el termino existe, lo editamos
            } else {
                //editar palabra castellano si no existe

                if (busquedaCastellano.equals("NOT FOUND")) {
                    try {

                        String sql = "UPDATE Hiztegia SET Castellano ='" + palabraCastellano + "'WHERE Euskera='" + palabraEuskera + "' OR English='" + palabraEnglish + "'";
                        Statement stmt = conexion.createStatement();
                        rowsUpdated += stmt.executeUpdate(sql);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }

                }
                //editar palabra castellano si no existe

                if (busquedaEuskera.equals("NOT FOUND")) {
                    try {

                        String sql = "UPDATE Hiztegia SET Euskera ='" + palabraEuskera + "'WHERE Castellano='" + palabraCastellano + "' OR English='" + palabraEnglish + "'";
                        Statement stmt = conexion.createStatement();
                        rowsUpdated += stmt.executeUpdate(sql);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }

                }

                if (busquedaEnglish.equals("NOT FOUND")) {
                    try {

                        String sql = "UPDATE Hiztegia SET English ='" + palabraEnglish + "'WHERE Castellano='" + palabraCastellano + "' OR Euskera='" + palabraEuskera + "'";
                        Statement stmt = conexion.createStatement();
                        rowsUpdated += stmt.executeUpdate(sql);

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }

                }
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "EDITED");
                }
            }
        }

        //cerrar conexion
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbMetodoak.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static String buscarPalabra(String palabra, String idiomaSalida) {
        String idiomaEntrada = autoIdioma(palabra);
        String palabraSalida = traducirPalabra(palabra, idiomaEntrada, idiomaSalida);
        return palabraSalida;
    }
    
    public static void main(String[] args) {
        objetoHiztegia();
    }
}
