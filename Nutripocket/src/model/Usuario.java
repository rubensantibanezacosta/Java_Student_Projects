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
public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private int peso;
    private int sexo;
    private int consumoCalorico;
    private int requisitoCalorico;
    private int requisitoProte;
    private int requisitoGrasas;

    public Usuario(String nombre, String apellido, int edad, int peso, int sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getConsumoCalorico() {
        return consumoCalorico;
    }

    public void setConsumoCalorico(int consumoCalorico) {
        this.consumoCalorico = consumoCalorico;
    }

    public int getRequisitoCalorico() {
        return requisitoCalorico;
    }

    public void setRequisitoCalorico(int requisitoCalorico) {
        this.requisitoCalorico = requisitoCalorico;
    }

    public int getRequisitoProte() {
        return requisitoProte;
    }

    public void setRequisitoProte(int requisitoProte) {
        this.requisitoProte = requisitoProte;
    }

    public int getRequisitoGrasas() {
        return requisitoGrasas;
    }

    public void setRequisitoGrasas(int requisitoGrasas) {
        this.requisitoGrasas = requisitoGrasas;
    }
    
    
}
