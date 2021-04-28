/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pkg1.model.Model;
import pkg1.view.View;

/**
 *
 * @author ruben
 */
public class Controller implements ActionListener {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        anadirActionListener(this);
         view.jTextAreaListaIzena.setText(model.irakurri());
    }

    private void anadirActionListener(ActionListener listener) {
        //GUIaren konponente guztiei gehitu listenerra
        view.jButtonGehitu.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //listenerrak entzun dezakeen eragiketa bakoitzeko. Konponenteek 'actionCommad' propietatea daukate
        switch (actionCommand) {
            case "GEHITU":
                System.out.println("Has pulsado el boton añadir");
                
                model.gehitu(new Izena(view.jTextFieldIzena.getText()));
                view.jTextAreaListaIzena.setText(model.irakurri());
                break;
            default:
                System.out.println("???");
        }
    }

}
