/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import model.Model;
import view.TableModels;
import view.View;

/**
 *
 * @author ruben
 */
public class main {

    public static void main(String args[]) {
        
        Model model = new Model();
        View view = View.crearView();
        
       
        Controller controller = new Controller(model, view);
        
        view.jTable1.setModel(new TableModels());
        
    }
}
