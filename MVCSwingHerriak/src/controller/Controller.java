/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableModel;
import model.Model;

import model.Municipios;
import view.TableModels;

import view.View;

/**
 *
 * @author ruben
 */
public class Controller implements ActionListener {

    public Model model;
    public View view;
    public static List<Municipios> listMunicipioStatico;

    public Controller(Model model, View view) {

        this.model = model;
        listMunicipioStatico = model.leer();
        this.view = view;
        view.jButtonLocal.setBackground(Color.green);
        view.jButtonRemoto.setBackground(Color.red);
        anadirActionListener(this);

    }

    private void anadirActionListener(ActionListener listener) {
        //GUIaren konponente guztiei anadir listenerra
        view.jButtonAnadir.addActionListener(listener);
        view.jButtonBorrar.addActionListener(listener);
        view.jButtonEditar.addActionListener(listener);
        view.RESET.addActionListener(listener);
        view.jButtonLocal.addActionListener(listener);
        view.jButtonRemoto.addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //listenerrak entzun dezakeen eragiketa bakoitzeko. Konponenteek 'actionCommad' propietatea daukate
        switch (actionCommand) {
            case "AÑADIR":

                System.out.println("Has pulsado el boton añadir");
                Municipios mn = new Municipios(view.jTextFieldHerria.getText(), view.jComboBoxProvincias.getSelectedItem().toString(), view.jCheckBoxPlaya.isSelected(), view.jTextAreaComentario.getText());
                model.anadir(mn);
                listMunicipioStatico = model.leer();
                view.jTable1.setModel(new TableModels());
                resetear();
                break;

            case "BORRAR":

                System.out.println("Has pulsado el boton borrar");
                model.borrar(view.jTextFieldHerria.getText());
                listMunicipioStatico = model.leer();
                view.jTable1.setModel(new TableModels());
                resetear();

                break;

            case "EDITAR":

                System.out.println("Has pulsado el boton editar");
                mn = new Municipios(view.jTextFieldHerria.getText(), view.jComboBoxProvincias.getSelectedItem().toString(), view.jCheckBoxPlaya.isSelected(), view.jTextAreaComentario.getText());
                model.editar(mn);
                listMunicipioStatico = model.leer();
                view.jTable1.setModel(new TableModels());
                resetear();

                break;

            case "RESET":

                resetear();

                break;

            case "LOCAL":

                model.setLocal();
                view.jButtonLocal.setBackground(Color.green);
                view.jButtonRemoto.setBackground(Color.red);
                listMunicipioStatico = model.leer();
                view.jTable1.setModel(new TableModels());

                break;

            case "REMOTO":

                model.setRemoto();
                view.jButtonRemoto.setBackground(Color.green);
                view.jButtonLocal.setBackground(Color.red);
                listMunicipioStatico = model.leer();
                view.jTable1.setModel(new TableModels());

                break;

            default:
                System.out.println("???");
        }
    }

    public static List<Municipios> getListMunicipios() {
        return listMunicipioStatico;
    }

    public void setListMunicipios(List<Municipios> listMunicipios) {
        this.listMunicipioStatico = listMunicipios;
    }

    public void resetear() {
        view.jTextFieldHerria.setText("");
        view.jComboBoxProvincias.setSelectedItem("Seleccionar");
        view.jCheckBoxPlaya.setSelected(false);
        view.jTextAreaComentario.setText("");
    }
}
