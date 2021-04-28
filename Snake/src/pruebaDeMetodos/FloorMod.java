/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaDeMetodos;

/**
 *Prueba de metodo Math.floorMod(int x, int y)
 * @author ruben
 */
public class FloorMod {
    public static void main(String args[]) 
    { 
        int a = 1, b = 4; //1%4=0.25
        System.out.println(Math.floorMod(a, b)); 
  
        
        int c = 2, d = 4; //2%4=0.5
        System.out.println(Math.floorMod(c, d)); 
  
        
        int e = 3, f = 4; //3%4=0.75
        System.out.println(Math.floorMod(e, f)); 
  
         
        int g = 4, h = 4; //4%4=1
        System.out.println(Math.floorMod(g, h)); 
        
         int i = 5, j = 4; //1.25
        System.out.println(Math.floorMod(i, j)); 
        
        int k = 8, l = 4; //1.5
        System.out.println(Math.floorMod(k, l));
}
}
