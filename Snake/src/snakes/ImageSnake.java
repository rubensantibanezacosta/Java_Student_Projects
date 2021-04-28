/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel de la serpiente JPanel
 *
 * @author ruben
 */
public class ImageSnake extends JPanel {

    Color colorSnake = new Color(141, 249, 66);
    Color colorFruta = new Color(190, 65, 28);
    ArrayList<int[]> snake = new ArrayList<>();//array de coordenadas de cada cuadro de la serpiente
    int[] fruta = new int[2];
    public static String direccion = "derecha";
    public String direccionProxima = "derecha";
    Thread thread; //atributo de tipo Treah
    Hilo2 jugando;// atributo de clase Hilo2(Clase runnable)
    static String jugador = "";
    Fondo fondo;
    

    public int sizeMax, sizeSquares, numSquares, resto;

    public ImageSnake(int sizeMax, int numSquares) {
        //Panel por dnd se movera la serpiente

        this.sizeMax = sizeMax;
        this.numSquares = numSquares;
        this.sizeSquares = sizeMax / numSquares;
        this.resto = sizeMax % numSquares;
        
        //intanciamos las coordenadas de la serpiente de dos cuadritos
        fondo = new Fondo(600, Ventana.numCuadros);

        fondo.setBounds(0, 0, 600, 600);//https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#setBounds-int-int-int-int-
        this.add(fondo);//agregamos el fondo a la "this" clase
        int[] a = {numSquares / 2, numSquares / 2};
        int[] b = {numSquares / 2 + 1, numSquares / 2};
        //lo añadimos al atributo List Snake

        snake.add(a);
        snake.add(b);
        generarFruta();

        jugando = new Hilo2(this); //instanciar jugando 

        thread = new Thread(jugando);//instanciar thread con clase Runnable
        thread.start();//iniciar hilo
        System.out.println("Iniciar Hilo");

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(colorSnake);
        //pintar serpiente
        for (int i = 0; i < snake.size(); i++) {
            //g.fillRect(resto/2+snake.get(i)[0]*sizeSquares, resto/2+snake.get(i)[1]*sizeSquares, sizeSquares-1,sizeSquares-1);
            g.fillRect(resto / 2 + snake.get(i)[0] * sizeSquares, resto / 2 + snake.get(i)[1] * sizeSquares, sizeSquares - 1, sizeSquares - 1);

        }
        //pintar fruta
        g.setColor(colorFruta);
        g.fillRect(resto / 2 + fruta[0] * sizeSquares, resto / 2 + fruta[1] * sizeSquares, sizeSquares - 1, sizeSquares - 1);

    }

    public void avanzar() {
        igualarDireccion();
        int[] ultimo = snake.get(snake.size() - 1);
        //conseguir coordenadas del utlimo cuadrado
        int agregarX = 0;
        int agregarY = 0;
        //modificar la x o y segun la dirección de avance del Snake
        switch (direccion) {
            case "derecha":
                agregarX = 1;
                break;
            case "izquierda":
                agregarX = -1;
                break;
            case "arriba":
                agregarY = 1;
                break;
            case "abajo":
                agregarY = -1;
                break;

        }
        //https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#floorMod-int-int-
        //Math.floorMod(int x, int y)
        //Se usa para que cuando llegue al final del tablero vuelva al principio
        //Ejemplo de uso en Package pruebaDeMetodos; 
        //Clase FloorMood;
        int[] nuevo = new int[2];
        nuevo[0] = Math.floorMod(ultimo[0] + agregarX, numSquares);
        nuevo[1] = Math.floorMod(ultimo[1] + agregarY, numSquares);
        //comprbamos si el nuevo cuadro esta ocupado ya por la serpiente
        //Si chocado sacamos un mensaje que diga "GAME OVER"
        boolean chocado = false;
        for (int[] j : snake) {

            if (nuevo[1] == j[1] && nuevo[0] == j[0]) {
                chocado = true;

            }
        }
        if (chocado == true) {
            Hilo2.estado = false;
            Hilo3.estado = false;
            snake.clear();
            
            jugador = JOptionPane.showInputDialog(this, "GAME OVER|| Tu puntuación es: " + Ventana.puntuacion + " Introduce tu nombre");
            Puntuacion ultima = new Puntuacion(jugador, Ventana.puntuacion);
            metodosDB.insertarPuntuacion(ultima);
            PanelPuntuaciones P1 = new PanelPuntuaciones();
           
           fruta[0]=-2;
           fruta[1]=-2;
         
            P1.setBounds(0, 0, 635, 655);
         
            P1.setSize(635, 655);
          
            
            P1.setVisible(true);
            
            fondo.setVisible(false);
            
            this.add(P1);
           
            
            while(P1.isVisible()){
                
            }
            
           fondo.setVisible(true);
          
            
            

            

            int[] a = {numSquares / 2, numSquares / 2};
            int[] b = {numSquares / 2 + 1, numSquares / 2};
            generarFruta();
            Ventana.puntuacion += 0;

            snake.add(a);
            snake.add(b);
            direccion = "derecha";
            direccionProxima = "derecha";
           
                Hilo2.estado = true;
                Hilo3.estado = true;
            

        } else {
            //comprobamos si hay comida
            if (nuevo[0] == fruta[0] && nuevo[1] == fruta[1]) {
                snake.add(nuevo);//añadir cuadro a la cabeza para avanzar
                generarFruta();
                Ventana.puntuacion += 20;

            } else {
                snake.remove(0);//eliminar cuadro de cola
                snake.add(nuevo);//añadir cuadro a la cabeza para avanzar

            }
        }

    }

    public void generarFruta() {
        boolean existe = false;
        //generamos aleatoriamente valores entre cero y el limite de cuadricula para X e Y
        int a = (int) (Math.random() * numSquares);
        int b = (int) (Math.random() * numSquares);
        //recorremos todos los puntos del snake para comprobar que no generamos la fruta 
        //donde esta el gusano
        for (int[] par : snake) {

            if (par[0] == a && par[1] == b) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            fruta[0] = a;
            fruta[1] = b;
        }
    }

    public void cambiarDireccion(String direccion) {
        this.direccionProxima = direccion;

    }

    public void igualarDireccion() {
        this.direccion = this.direccionProxima;
    }

}
