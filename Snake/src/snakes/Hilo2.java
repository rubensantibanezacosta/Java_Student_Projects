/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread 2
 *
 * @author ruben
 */
public class Hilo2 implements Runnable {

    ImageSnake panel;
    static boolean estado = true;
    static int pausa=60;
        

    public Hilo2(ImageSnake panel) {
        this.panel = panel;
    }

    /**
     * Reescribir metodo run generar bucle controlado (TIMER) para los metodos
     * avanzar y repaint
     */
    @Override
    public void run() {

        while (estado) {
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(pausa);//milisegundos de pausa
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        this.estado = false;
    }

}
