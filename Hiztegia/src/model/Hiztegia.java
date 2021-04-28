/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ruben
 */
public class Hiztegia implements Serializable {

    public static ArrayList<Terminoa> hiztegia = new ArrayList<>();

    public void inicializar() {

        hiztegia.add(new Terminoa("arbol", "zuhaitza", "tree"));
        hiztegia.add(new Terminoa("rueda", "gurpila", "wheel"));
        hiztegia.add(new Terminoa("bien", "ondo", "good"));

    }

    public Hiztegia() {

    }

    /**
     *
     * @param palabra
     * @return idioma (castellano por defecto)
     */
    public String autoBusqueda(String palabra) {
        String idioma = new String("castellano");

        for (int i = 0; i < hiztegia.size(); i++) {

            if (hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra)) {
                idioma = "euskera";
            }

            if (hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {
                idioma = "english";
            }
        }

        return idioma;
    }

    public String buscar(String idiomaSalida, String palabra) {
        String significado = "Error";

        for (int i = 0; i < hiztegia.size(); i++) {
            if (idiomaSalida.equalsIgnoreCase("castellano")) {
                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getCastellano();
                    break;

                }

            }
            if (idiomaSalida.equalsIgnoreCase("euskera")) {

                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getEuskera();
                    break;

                }
            }
            if (idiomaSalida.equalsIgnoreCase("english")) {

                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getEnglish();
                    break;

                }
            } else {
                if (idiomaSalida.equalsIgnoreCase("castellano")) {

                    significado = "Error";

                }
                if (idiomaSalida.equalsIgnoreCase("euskera")) {

                    significado = "Errorea";
                }

                if (idiomaSalida.equalsIgnoreCase("english")) {

                    significado = "Error";
                } else {
                    significado = "Error";
                }
            }

        }

        return significado;
    }

    public String traducir(String idiomaEntrada, String idiomaSalida, String palabra) {
        String significado = "La palabra no se encuentra en la base de datos";

        for (int i = 0; i < hiztegia.size(); i++) {
            if (idiomaSalida.equalsIgnoreCase("castellano")) {
                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getCastellano();
                    break;

                }

            }
            if (idiomaSalida.equalsIgnoreCase("euskera")) {

                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getEuskera();
                    break;

                }
            }
            if (idiomaSalida.equalsIgnoreCase("english")) {

                if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra) || hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra) || hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {

                    significado = hiztegia.get(i).getEnglish();
                    break;

                }
            } else {
                if (idiomaEntrada.equalsIgnoreCase("castellano")) {

                    significado = "La palabra no se encuentra en la base de datos";

                }
                if (idiomaEntrada.equalsIgnoreCase("euskera")) {

                    significado = "Hitza ez dago datu-basean";
                }

                if (idiomaEntrada.equalsIgnoreCase("english")) {

                    significado = "The word is not in database";
                } else {
                    significado = "La palabra no se encuentra en la base de datos";
                }
            }

        }

        return significado;
    }

    public void anadirPalabra(String castellano, String euskera, String english) {
        if (!hiztegia.contains(new Terminoa(castellano, euskera, english))) {
            hiztegia.add(new Terminoa(castellano, euskera,  english));
        } else {
            JOptionPane.showMessageDialog(null, "Already exists || Ya existe || Badago ");
        }
    }

    public void borrarPalabra(String palabra) {

        int indexPalabra = -1;

        for (int i = 0; i < hiztegia.size(); i++) {

            if (hiztegia.get(i).getCastellano().equalsIgnoreCase(palabra)) {
                indexPalabra = i;

            }
            if (hiztegia.get(i).getEuskera().equalsIgnoreCase(palabra)) {
                indexPalabra = i;
            }

            if (hiztegia.get(i).getEnglish().equalsIgnoreCase(palabra)) {
                indexPalabra = i;
            }
        }
        if (indexPalabra != -1) {
            hiztegia.remove(indexPalabra);
            JOptionPane.showMessageDialog(null, "borrada || ezabatua || deleted");

        } else {
            JOptionPane.showMessageDialog(null, "no existe || ez da existizen || not exists");
        }

    }

    public void sobreecribirPalabra(String castellano, String euskera, String english, Hiztegia lista) {

        int indexPalabra = -1;

        for (int i = 0; i < hiztegia.size(); i++) {

            if (hiztegia.get(i).getCastellano().equalsIgnoreCase(castellano)) {
                indexPalabra = i;
            }
            if (hiztegia.get(i).getEuskera().equalsIgnoreCase(euskera)) {
                indexPalabra = i;
            }

            if (hiztegia.get(i).getEnglish().equalsIgnoreCase(english)) {
                indexPalabra = i;
            }
        }

        if (indexPalabra != -1) {
            hiztegia.remove(indexPalabra);
            hiztegia.add(new Terminoa(castellano, euskera, english));
            JOptionPane.showMessageDialog(null, "Exito || Eginda || Well Done ");
        } else {
            if (castellano != "" && euskera != "" && english != "") {
                lista.anadirPalabra(castellano, euskera, english);
                JOptionPane.showMessageDialog(null, "Guardada || Gordeta || Saved ");
            } else {
                 JOptionPane.showMessageDialog(null, "Campos vacíos || Eremu hutsak || Empty areas ");

            }

           
        }

    }

    public void print() {

        System.out.println("LISTA DE TERMINOS\n\n"
                + "   CASTELLANO                           EUSKERA                                 ENGLISH\n"
                + "=========================================================================================\n");

        for (int z = 0; z < hiztegia.size(); z++) {
            out.printf("%d %8s %37s %38s%n", (z + 1), hiztegia.get(z).getCastellano(), hiztegia.get(z).getEuskera(), hiztegia.get(z).getEnglish());
        }
        System.out.println("");

    }

    public static void guardarDiccionario(Hiztegia lista) throws FileNotFoundException, IOException {

        try {

            //Creating stream and writing the object    
            FileOutputStream fileSalida = new FileOutputStream("diccionario.txt");
            ObjectOutputStream ObjetoOut = new ObjectOutputStream(fileSalida);
            ObjetoOut.writeObject(lista);

            //closing the stream    
            ObjetoOut.close();
            System.out.println("Datuak fitxategian idatzi dira.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Hiztegia cargarDiccionario() {
        Hiztegia p = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("diccionario.txt");
            ObjectInputStream inStream = new ObjectInputStream(fin);

            while (true) {
                p = (Hiztegia) inStream.readObject(); //
                System.out.println(p);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxategia ez dago bere lekuan.");
        } catch (IOException ex) {
            System.out.println("Ez dago objektu gehiagorik.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Salbuespena gertatu da.");
        } finally {

            try {
                fin.close();
            } catch (IOException ex) {

            }

        }
        return p;
    }
    
   

}
