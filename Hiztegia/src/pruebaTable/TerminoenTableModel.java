/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaTable;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Terminoa;

/**
 *
 * @author ruben
 */
public class TerminoenTableModel extends AbstractTableModel {
    
    private String[] columnas= {"CASTELLANO","EUSKERA", "INGLES"};
    private ArrayList <Terminoa> datos= new ArrayList<>();

    public TerminoenTableModel() {
        datos.add(new Terminoa("fresa","marrubia","Strawberry"));
         datos.add(new Terminoa("rojo","gorri","red"));
         datos.add(new Terminoa("rosa","arrosa","pink"));
    }

    
    
    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
    
    
}
