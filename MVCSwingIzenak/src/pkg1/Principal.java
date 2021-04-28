/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1;

import pkg1.controller.Controller;
import pkg1.model.Model;
import pkg1.view.View;

/**
 *
 * @author ruben
 */
public class Principal {
  
    public static void main(String args[]) {
        View view = View.viewaSortuBistaratu();
        Model model = new Model();
        Controller controller = new Controller(model, view);
    }

    
}
