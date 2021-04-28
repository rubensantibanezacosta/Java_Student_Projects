/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ruben
 */
public class Item {
    private Alimento alimento;
     double racion=0;

    public Item(Alimento alimento, double racion) {
        this.alimento = alimento;
        this.racion = racion;
    }
     public Item(Alimento alimento) {
        this.alimento = alimento;
        racion=racion;
        
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public double getRacion() {
        return racion;
    }

    public void setRacion(double racion) {
        this.racion = racion;
    }


    
    
    
}
