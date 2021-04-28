/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import static db.DbMetodoak.autoIdioma;
import static db.DbMetodoak.borrarPalabra;
import static db.DbMetodoak.traducirPalabra;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Hiztegia;
import static db.DbMetodoak.anadirEditarPalabra;

/**
 *
 * @author ruben
 */
public class test {

    public static void main(String[] args) {
//        Hiztegia lista = new Hiztegia();
//         try {
//            lista.guardarDiccionario(lista);
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        lista=Hiztegia.cargarDiccionario();
        
//        lista.inicializar();
//        lista.print();
//        lista.anadirEditarPalabra("rojo", "gorri", "red");
//        lista.anadirEditarPalabra("verde", "berdea", "green");
//        lista.print();
//        lista.sobreecribirPalabra("rojoa", "gorria", "reda");
//        lista.print();
//        lista.borrarPalabra("gorria");
//        lista.print();
//        System.out.println(lista.autoBusqueda("zza"));
//        try {
//            lista.guardarDiccionario(lista);
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String palabra="wheel";
        String idioma=autoIdioma(palabra);
        System.out.println(idioma);
        
//        System.out.println(palabra);
        System.out.println(traducirPalabra(palabra,idioma,"Euskera")); 
        
      //  borrarPalabra("berdea");
       // anadirEditarPalabra("coche","autoa","car");
    }

}

