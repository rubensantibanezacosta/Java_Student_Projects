/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Alimento;
import model.Item;
import model.RecetasAlimentos;
import static model.RecetasAlimentos.calcularCalorias3;
import static model.RecetasAlimentos.calcularCalorias4;
import static model.RecetasAlimentos.calcularCaloriasSelectivo;
import static model.RecetasAlimentos.calcularCarbs3;
import static model.RecetasAlimentos.calcularCarbs4;
import static model.RecetasAlimentos.calcularGrasas3;
import static model.RecetasAlimentos.calcularGrasas4;
import static model.RecetasAlimentos.calcularProtes3;
import static model.RecetasAlimentos.calcularProtes4;

/**
 *
 * @author ruben
 */
public class test3items {
    public static void main(String[] args) {
        
        
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
        
          RecetasAlimentos receta = new RecetasAlimentos(item1, item2, item3);
        RecetasAlimentos recetaRecomendada2 = new RecetasAlimentos();
        recetaRecomendada2 = RecetasAlimentos.algoritmoGeneticoSelectivo(receta, calorias, protes, grasas);
        
        System.out.println(recetaRecomendada2.getItem1().getAlimento().getNombre() + " " + recetaRecomendada2.getItem1().getRacion() + "g");
        System.out.println(recetaRecomendada2.getItem2().getAlimento().getNombre() + " " + recetaRecomendada2.getItem2().getRacion() + "g");
        System.out.println(recetaRecomendada2.getItem3().getAlimento().getNombre() + " " + recetaRecomendada2.getItem3().getRacion() + "g");
       
        System.out.println("Calorias de la receta "+calcularCaloriasSelectivo(recetaRecomendada2));
        System.out.println("Protes de la receta "+calcularProtes3(recetaRecomendada2));
        System.out.println("Grasas de la receta "+calcularGrasas3(recetaRecomendada2));
        System.out.println("Carbohidratos de la receta "+calcularCarbs3(recetaRecomendada2));        
        
    }
    
}
