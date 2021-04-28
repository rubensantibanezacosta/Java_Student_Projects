/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

/**
 *
 * @author ruben
 */
public class Puntuacion {
    public String nombre;
    public int numero;

    public Puntuacion(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "puntuacion{" + "nombre=" + nombre + ", numero=" + numero + '}';
    }
    
    
    
    
    
    
}
