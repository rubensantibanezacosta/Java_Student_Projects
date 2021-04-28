/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
import model.Alimento;
import model.Item;
import model.RecetasAlimentos;
import static model.RecetasAlimentos.calcularCalorias4;
import static model.RecetasAlimentos.calcularCaloriasSelectivo;
import static model.RecetasAlimentos.calcularProtes4;
import static model.RecetasAlimentos.calcularGrasas4;
import static model.RecetasAlimentos.calcularCarbs4;

/**
 * 
 *
 * @author ruben
 */
public class test {

    public static void main(String[] args) {

        //declaracion de alimentos=(calorias, Proteina, Carbohidrato, Grasa)
        //en formato gr/100gr
        Alimento pollo = new Alimento("pollo", 1.65, 0.199, 0, 0.096, 1);
        Alimento verdura = new Alimento("verdura", 0.2, 0.014, 0.014, 0.0006, 1);
        Alimento leche = new Alimento("leche", 0.375, 0.039, 0.05, 0.001, 1);
        Alimento huevo = new Alimento("huevo", 1.13, 0.127, 0.0067, 0.097, 80);
        Alimento arroz = new Alimento("arroz", 1.3, 0.027, 0.28, 0.0003, 1);
        Alimento aceite = new Alimento("aceite", 8.84, 0, 0, 1, 1);
        double calorias = 1800;
        double protes = 150;
        double grasas = 60;

        Item item1 = new Item(pollo);
        Item item2 = new Item(huevo);
        Item item3 = new Item(verdura);
        Item item4 = new Item(aceite);

        RecetasAlimentos receta4 = new RecetasAlimentos(item1, item2, item3, item4);
        RecetasAlimentos recetaRecomendada = new RecetasAlimentos();
        recetaRecomendada = RecetasAlimentos.algoritmoGeneticoSelectivo(receta4, calorias, protes, grasas);

        System.out.println(recetaRecomendada.getItem1().getAlimento().getNombre() + " " + recetaRecomendada.getItem1().getRacion() + "g");
        System.out.println(recetaRecomendada.getItem2().getAlimento().getNombre() + " " + recetaRecomendada.getItem2().getRacion() + "g");
        System.out.println(recetaRecomendada.getItem3().getAlimento().getNombre() + " " + recetaRecomendada.getItem3().getRacion() + "g");
        System.out.println(recetaRecomendada.getItem4().getAlimento().getNombre() + " " + recetaRecomendada.getItem4().getRacion() + "g");
        System.out.println("Calorias de la receta "+calcularCaloriasSelectivo(recetaRecomendada));
        System.out.println("Protes de la receta "+calcularProtes4(recetaRecomendada));
        System.out.println("Grasas de la receta "+calcularGrasas4(recetaRecomendada));
        System.out.println("Carbohidratos de la receta "+calcularCarbs4(recetaRecomendada));        
        
    
    
    
    

      
    }
}
