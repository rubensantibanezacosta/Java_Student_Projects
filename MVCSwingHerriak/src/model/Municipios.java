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
public class Municipios {
    String municipio;
    String provincia;
    boolean playa;
    String comentario;

    public Municipios(String municipio, String provincia, boolean playa, String comentario) {
        this.municipio = municipio;
        this.provincia = provincia;
        this.playa = playa;
        this.comentario = comentario;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public boolean isPlaya() {
        return playa;
    }

    public void setPlaya(boolean playa) {
        this.playa = playa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    @Override
    public String toString(){
        String palabra=new String();
        String playaTick=new String();
        if (playa){
            playaTick="true";
        }
        else
        {
            playaTick="false";
        }
        palabra=municipio+" "+provincia+" "+playaTick+" "+comentario;
        
        return palabra;
    }
}
