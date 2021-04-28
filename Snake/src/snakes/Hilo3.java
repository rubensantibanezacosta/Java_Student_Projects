/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Thread 3
 * Hilo del reproductor
 * @author ruben
 */
public class Hilo3 implements Runnable {

    Reproductor player;
    static boolean estado = true;

    public Hilo3(Reproductor player) throws FileNotFoundException, JavaLayerException {
       
           this.player = player;
       

    }

    /**
     * Reescribir metodo run generar bucle controlado (TIMER) para los metodos
     * avanzar y repaint
     */
    @Override
    public void run() {
        
       
        try {
            Reproductor player=new Reproductor();
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(Hilo3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
           
    }

    public void stop() {
        this.estado = false;
    }

}
