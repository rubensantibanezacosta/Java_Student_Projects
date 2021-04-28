/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author imadariaga
 */
public class Model {

    private String ubicacion = "local";

    public Connection connect(String ubicacion) {
        Connection conn = null;
        if (ubicacion == "local") {
            try {
                // db parameters

                // create a connection to the database
                conn = DriverManager.getConnection("jdbc:sqlite:HerrienDBa.sqlite");

                //  System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Conectado");
        } else {
            try {
                // db parameters
                //mariadb://localhost:3306/HerrienDBa
                // create a connection to the database
                conn = DriverManager.getConnection("jdbc:mysql://buiyrsw8e63tlwnrcdov-mysql.services.clever-cloud.com:3306/buiyrsw8e63tlwnrcdov", "u3ojlfpah26xzwwi", "q7mAUu49qVptCWdE8mHp");

                //  System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Conectado");
        }

        return conn;

    }

    public List leer() {
        List<Municipios> listMunicipiosLeer = new ArrayList<>();

        String taula = "herriak";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = connect(ubicacion);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Municipios mn = new Municipios(rs.getString("Herria"), rs.getString("Probintzia"), rs.getBoolean("Hondartza"), rs.getString("Oharrak"));

                listMunicipiosLeer.add(mn);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error de conexion");

        }

        return listMunicipiosLeer;
    }

    /**
     * Si se ha insertado el nombre devuelve 1, si no devuelve 0
     *
     * @param mn tipo Izena
     * @return numero de registros introducidos
     */
    public int anadir(Municipios mn) {
        int ejecutado = 0;
        if (mn.municipio != "" && mn.provincia != "Seleccionar") {

//        Comprobar si el termino ya existe en la lista
            List<Municipios> listMunicipiosLeer = new ArrayList<>();
            listMunicipiosLeer = leer();
            boolean existe = false;
            for (int j = 0; j < listMunicipiosLeer.size(); j++) {
                if (listMunicipiosLeer.get(j).getMunicipio().equals(mn.getMunicipio())) {
                    existe = true;

                }
            }

            if (!existe) {

                String nombre = mn.getMunicipio();
                String provincia = mn.getProvincia();
                boolean playa = mn.isPlaya();
                String comentario = mn.getComentario();
                String sql = "INSERT INTO herriak(Herria, Probintzia, Hondartza, Oharrak) VALUES(?,?,?,?)";

                try (Connection conn = connect(ubicacion);
                        PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, provincia);
                    pstmt.setBoolean(3, playa);
                    pstmt.setString(4, comentario);
                    pstmt.executeUpdate();
                    ejecutado = 1;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
            }
            if (ejecutado == 0) {
                JOptionPane.showMessageDialog(null, "El municipio ya existe");
            } else {
                JOptionPane.showMessageDialog(null, "El municipio ha sido registrado");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Rellena los campos");

        }

        return ejecutado;
    }

    public int borrar(String municipioABorrar) {
        //        Comprobar si el termino ya existe en la lista
        int ejecutado = 0;
        if (municipioABorrar != "") {
            List<Municipios> listMunicipiosLeer = new ArrayList<>();
            listMunicipiosLeer = leer();
            boolean existe = false;
            for (int j = 0; j < listMunicipiosLeer.size(); j++) {
                if (listMunicipiosLeer.get(j).getMunicipio().equals(municipioABorrar)) {
                    existe = true;
                }
            }

            if (existe) {

                String nombre = municipioABorrar;

                String sql = "DELETE FROM herriak WHERE Herria=(?)";

                try (Connection conn = connect(ubicacion);
                        PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.executeUpdate();
                    ejecutado = 1;

                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
            }

            if (ejecutado == 0) {
                JOptionPane.showMessageDialog(null, "El municipio " + municipioABorrar + " no existe");
            } else {
                JOptionPane.showMessageDialog(null, "El municipio " + municipioABorrar + " ha sido borrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellena los campos");
        }

        return ejecutado;
    }

    public int editar(Municipios mn) {
        int ejecutado = 0;
        if (mn.municipio != "" && mn.provincia != "Seleccionar") {
            //        Comprobar si el termino ya existe en la lista
            List<Municipios> listMunicipiosLeer = new ArrayList<>();
            listMunicipiosLeer = leer();
            boolean existe = false;
            for (int j = 0; j < listMunicipiosLeer.size(); j++) {
                if (listMunicipiosLeer.get(j).getMunicipio().equals(mn.getMunicipio())) {
                    existe = true;
                }
            }

            if (existe) {
                System.out.println("existe");

                String nombre = mn.getMunicipio();
                String provincia = mn.getProvincia();
                boolean playa = mn.isPlaya();
                String comentario = mn.getComentario();
                String sql = "UPDATE herriak SET Herria=(?), Probintzia=(?), Hondartza=(?), Oharrak=(?) WHERE Herria=(?)";

                try (Connection conn = connect(ubicacion);
                        PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, provincia);
                    pstmt.setBoolean(3, playa);
                    pstmt.setString(4, comentario);
                    pstmt.setString(5, nombre);

                    pstmt.executeUpdate();
                    ejecutado = 1;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
            }
            if (ejecutado == 0) {
                JOptionPane.showMessageDialog(null, "El municipio " + mn.getMunicipio() + " no existe");
            } else {
                JOptionPane.showMessageDialog(null, "El municipio " + mn.getMunicipio() + " ha sido actualizado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellena los campos");
        }
        return ejecutado;

    }

    public void setLocal() {
        ubicacion = "local";
    }

    public void setRemoto() {
        ubicacion = "remoto";
    }

}
