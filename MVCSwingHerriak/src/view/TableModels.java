/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ruben
 */
public class TableModels extends AbstractTableModel {

    public String[] columnas = {"Municipio", "Provincia", "Playa", "Comentario"};

    public TableModels() {

    }

    @Override
    public int getRowCount() {
        return Controller.listMunicipioStatico.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:

                return Controller.listMunicipioStatico.get(rowIndex).getMunicipio();

            case 1:

                return Controller.listMunicipioStatico.get(rowIndex).getProvincia();

            case 2:

                return Controller.listMunicipioStatico.get(rowIndex).isPlaya();

            case 3:

                return Controller.listMunicipioStatico.get(rowIndex).getComentario();
        }
        return null;
    }



    @Override
    public String getColumnName(int i) {
        return columnas[i];
    }
    @Override
 public Class<?> getColumnClass(int colIndex) {

                return getValueAt(0, colIndex).getClass();

}
}
