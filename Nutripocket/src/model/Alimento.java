/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Scanner;

/**
 *
 * @author ruben
 */
public class Alimento {
    
    private String nombre;
    private double calorias, protes, carbs, grasas, dosis=1;

    public double getCalorias() {
        return calorias;
    }

    public double getDosis() {
        return dosis;
    }
    

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtes() {
        return protes;
    }

    public void setProtes(double protes) {
        this.protes = protes;
    }

    public double getGrasas() {
        return grasas;
    }
     public String getNombre() {
        return nombre;
    }

    public void setGrasas(double grasas) {
        this.grasas = grasas;
    }

    public Alimento(String nombre,double calorias, double protes, double carbs, double grasas,double dosis) {
        this.nombre=nombre;
        this.calorias = calorias;
        this.carbs = carbs;
        this.protes = protes;
        this.grasas = grasas;
    }

   

  
}
