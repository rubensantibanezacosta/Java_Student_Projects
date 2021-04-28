/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ruben
 */
public class Terminoa implements Serializable {

    String castellano = "";
    String euskera = "";
    String english = "";

    public Terminoa(String castellano, String euskera, String English) {

        this.castellano = castellano;
        this.euskera = euskera;
        this.english = English;
    }

    public String getEuskera() {
        return euskera;
    }

    public void setEuskera(String euskera) {
        this.euskera = euskera;
    }

    public String getCastellano() {
        return castellano;
    }

    public void setCastellano(String castellano) {
        this.castellano = castellano;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String English) {
        this.english = English;
    }

}
