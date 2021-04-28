/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;




/**
 *Tablero del juego CLASE JPANEL
 * @author ruben
 */
public class Fondo extends JPanel {
    Color colorFondo=new Color(51, 111, 247);
    public int sizeMax, sizeSquares, numSquares, borde;

    /**
     * Le pasamos tamaño maximo del tablero, y numero de cuadrados al constructor. 
     * En el atributo sizeSquares se calcula automaticamente el tamañao de los cuadros
     * @param sizeMax
     * @param numSquares 
     */
    public Fondo(int sizeMax, int numSquares) {
        this.sizeMax = sizeMax;
        this.numSquares = numSquares;
        this.sizeSquares=sizeMax/numSquares; //tamaño de cuadrados
        this.borde=sizeMax%numSquares;
    }
    
    
    /**
     * reescribimos el metodo paint de la clase abstracta "Graphics" para dibujar el tablero
     * @param g 
     */
    
    @Override
    public void paint(Graphics g){ 
        super.paint(g);
        g.setColor(colorFondo);
        //bucle anidado para dibujar un tablero de tamaño numSquares*numSquares
        for (int i = 0; i < numSquares; i++) {
            for (int j = 0; j < numSquares; j++) {
                g.fillRect(borde/2+i*sizeSquares, borde/2+j*sizeSquares, sizeSquares-1,sizeSquares-1);
                
            }
            
        }
    }
    
  
 
    
   
    
    
    
    
    
    
}
