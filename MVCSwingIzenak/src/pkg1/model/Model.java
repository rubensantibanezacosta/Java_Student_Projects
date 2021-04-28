/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pkg1.controller.Izena;

/**
 *
 * @author imadariaga
 */
public class Model {

    private  String db = "IzenenDBa.sqlite";

    public  Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + db;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //  System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public  String irakurri() {
        String lista="";
        
        String taula = "Izenak";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Izena iz = new Izena(rs.getString("izena"));
                lista=lista+iz.getIzena()+"\n";
               
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    /**
     * Si se ha insertado el nombre devuelve 1, si no devuelve 0
     *
     * @param i tipo Izena
     * @return numero de registros introducidos
     */
    public  int gehitu(Izena i) {

        String sql = "INSERT INTO Izenak(izena) VALUES('" + i.getIzena() + "')";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {

            return stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

}
