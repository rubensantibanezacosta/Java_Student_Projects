/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ruben
 */
public class RecetasAlimentos {

    private Item Item1;
    private Item Item2;
    private Item Item3;
    private Item Item4;

    public RecetasAlimentos() {
    }

    public RecetasAlimentos(Item Item1, Item Item2, Item Item3, Item Item4) {
        this.Item1 = Item1;
        this.Item2 = Item2;
        this.Item3 = Item3;
        this.Item4 = Item4;

    }

    public RecetasAlimentos(Item Item1, Item Item2) {
        this.Item1 = Item1;
        this.Item2 = Item2;
    }

    public RecetasAlimentos(Item Item1, Item Item2, Item Item3) {
        this.Item1 = Item1;
        this.Item2 = Item2;
        this.Item3 = Item3;
    }

    public RecetasAlimentos(Item Item1) {
        this.Item1 = Item1;
    }

    public Item getItem1() {
        return Item1;
    }

    public void setItem1(Item Item1) {
        this.Item1 = Item1;
    }

    public Item getItem2() {
        return Item2;
    }

    public void setItem2(Item Item2) {
        this.Item2 = Item2;
    }

    public Item getItem3() {
        return Item3;
    }

    public void setItem3(Item Item3) {
        this.Item3 = Item3;
    }

    public Item getItem4() {
        return Item4;
    }

    public void setItem4(Item Item4) {
        this.Item4 = Item4;
    }

    
    public static RecetasAlimentos algoritmoGeneticoSelectivo(RecetasAlimentos receta, double requisitoCalorico, double requisitoProte, double requisitoGrasa){
        RecetasAlimentos recetaDevuelta=new RecetasAlimentos();
        
        if(receta.getItem4()!=null){
            
           recetaDevuelta= algoritmoGenetico4(receta, requisitoCalorico,requisitoProte,requisitoGrasa);
        }
        if(receta.getItem3()!=null&&receta.getItem4()==null){
             recetaDevuelta= algoritmoGenetico3(receta, requisitoCalorico,requisitoProte,requisitoGrasa);
        }
        return recetaDevuelta;
      
    }
    
    public static RecetasAlimentos algoritmoGenetico4(RecetasAlimentos receta, double requisitoCalorico, double requisitoProte, double requisitoGrasa) {
        double racion1, racion2, racion3, racion4;

        //GENERAR POBLACION, RACIONES RANDOM
        RecetasAlimentos[] recetasPoblacion = new RecetasAlimentos[20];
        for (int i = 0; i < recetasPoblacion.length; i++) {
            racion1 = receta.getItem1().getAlimento().getDosis() * Math.random() * 2000;
            racion2 = receta.getItem2().getAlimento().getDosis() * Math.random() * 2000;
            racion3 = receta.getItem3().getAlimento().getDosis() * Math.random() * 2000;
            racion4 = receta.getItem4().getAlimento().getDosis() * Math.random() * 2000;
            Item itemA = new Item(receta.getItem1().getAlimento(), racion1);
            Item itemB = new Item(receta.getItem2().getAlimento(), racion2);
            Item itemC = new Item(receta.getItem3().getAlimento(), racion3);
            Item itemD = new Item(receta.getItem4().getAlimento(), racion4);
            recetasPoblacion[i] = new RecetasAlimentos(itemA, itemB, itemC, itemD);

        }

        //PUNTUAR POBLACION
        double[] puntuacion = new double[20];
        for (int i = 0; i < puntuacion.length; i++) {
            //calcular totales para comparar con requisitos
            double caloriasTotales = calcularCalorias4(recetasPoblacion[i]);
            double protesTotales = calcularProtes4(recetasPoblacion[i]);
            double grasasTotales = calcularGrasas4(recetasPoblacion[i]);
            //puntuar    
            double puntuacionA = Math.abs(caloriasTotales - requisitoCalorico);
            double puntuacionB = Math.abs(protesTotales - requisitoProte);
            double puntuacionC = Math.abs(grasasTotales - requisitoGrasa);
            puntuacion[i] = puntuacionA + puntuacionB + (puntuacionC / 2);

        }
        //buscamos las 2 puntuaciones mas altas, y guardamos su ubicacion
        int primero = 0;
        int segundo = 0;
        double puntuacionMax1 = Double.MAX_VALUE;
        double puntuacionMax2 = Double.MAX_VALUE;
        for (int i = 0; i < puntuacion.length; i++) {
            if (puntuacion[i] < puntuacionMax1) {
                primero = i;
                puntuacionMax1 = puntuacion[i];

            }
        }
        for (int i = 0; i < puntuacion.length - 1; i++) {
            if (puntuacion[i] < puntuacionMax2 && puntuacion[i] > puntuacion[primero]) {
                segundo = i;
                puntuacionMax2 = puntuacion[i];
            }
        }
        //pasamos las 2 recetas mas aptas a un nuevo array

        RecetasAlimentos[] recetasNuevo = new RecetasAlimentos[20];
        recetasNuevo[0] = new RecetasAlimentos(recetasPoblacion[primero].getItem1(), recetasPoblacion[primero].getItem2(), recetasPoblacion[primero].getItem3(), recetasPoblacion[primero].getItem4());
        recetasNuevo[1] = new RecetasAlimentos(recetasPoblacion[segundo].getItem1(), recetasPoblacion[segundo].getItem2(), recetasPoblacion[segundo].getItem3(), recetasPoblacion[segundo].getItem4());

        //generamos dos recetas nuevas a partir de mezclar las 2 recetas mas aptas
        recetasNuevo[2] = new RecetasAlimentos(recetasPoblacion[primero].getItem1(), recetasPoblacion[segundo].getItem2(), recetasPoblacion[primero].getItem3(), recetasPoblacion[segundo].getItem4());
        recetasNuevo[3] = new RecetasAlimentos(recetasPoblacion[segundo].getItem1(), recetasPoblacion[primero].getItem2(), recetasPoblacion[segundo].getItem3(), recetasPoblacion[primero].getItem4());

        //generamos recetas aleatorias hasta completar el array, a partir de aqui iniciamos el bucle
        for (int x = 0; x < 10000; x++) {

            for (int i = 4; i < recetasNuevo.length; i++) {
                racion1 = receta.getItem1().getAlimento().getDosis() * Math.random() * 2000;
                racion2 = receta.getItem2().getAlimento().getDosis() * Math.random() * 2000;
                racion3 = receta.getItem3().getAlimento().getDosis() * Math.random() * 2000;
                racion4 = receta.getItem4().getAlimento().getDosis() * Math.random() * 2000;
                Item itemA = new Item(receta.getItem1().getAlimento(), racion1);
                Item itemB = new Item(receta.getItem2().getAlimento(), racion2);
                Item itemC = new Item(receta.getItem3().getAlimento(), racion3);
                Item itemD = new Item(receta.getItem4().getAlimento(), racion4);
                recetasNuevo[i] = new RecetasAlimentos(itemA, itemB, itemC, itemD);

            }
            //PUNTUAR POBLACION EVOLUCIONADA

            for (int i = 0; i < puntuacion.length; i++) {
                //calcular totales para comparar con requisitos
                double caloriasTotales = calcularCalorias4(recetasNuevo[i]);
                double protesTotales = calcularProtes4(recetasNuevo[i]);
                double grasasTotales = calcularGrasas4(recetasNuevo[i]);
                //puntuar    
                double puntuacionA = Math.abs(caloriasTotales - requisitoCalorico);
                double puntuacionB = Math.abs(protesTotales - requisitoProte);
                double puntuacionC = Math.abs(grasasTotales - requisitoGrasa);
                puntuacion[i] = puntuacionA + puntuacionB + (puntuacionC / 2);

            }

            //buscamos las 2 puntuaciones mas altas, y guardamos su ubicacion
            primero = 0;
            segundo = 0;
            puntuacionMax1 = Double.MAX_VALUE;
            puntuacionMax2 = Double.MAX_VALUE;
            for (int i = 0; i < puntuacion.length; i++) {
                if (puntuacion[i] < puntuacionMax1) {
                    primero = i;
                    puntuacionMax1 = puntuacion[i];

                }
            }
            for (int i = 0; i < puntuacion.length - 1; i++) {
                if (puntuacion[i] < puntuacionMax2 && puntuacion[i] > puntuacion[primero]) {
                    segundo = i;
                    puntuacionMax2 = puntuacion[i];
                }
            }
            //pasamos las 2 recetas mas aptas a un nuevo array

            recetasNuevo[0] = new RecetasAlimentos(recetasNuevo[primero].getItem1(), recetasNuevo[primero].getItem2(), recetasNuevo[primero].getItem3(), recetasNuevo[primero].getItem4());
            recetasNuevo[1] = new RecetasAlimentos(recetasNuevo[segundo].getItem1(), recetasNuevo[segundo].getItem2(), recetasNuevo[segundo].getItem3(), recetasNuevo[segundo].getItem4());

            //generamos dos recetas nuevas a partir de mezclar las 2 recetas mas aptas
            recetasNuevo[2] = new RecetasAlimentos(recetasNuevo[primero].getItem1(), recetasNuevo[segundo].getItem2(), recetasNuevo[primero].getItem3(), recetasNuevo[segundo].getItem4());
            recetasNuevo[3] = new RecetasAlimentos(recetasNuevo[segundo].getItem1(), recetasNuevo[primero].getItem2(), recetasNuevo[segundo].getItem3(), recetasNuevo[primero].getItem4());

        }

        return recetasNuevo[0];
    }
    
     public static RecetasAlimentos algoritmoGenetico3(RecetasAlimentos receta, double requisitoCalorico, double requisitoProte, double requisitoGrasa) {
        double racion1, racion2, racion3;

        //GENERAR POBLACION, RACIONES RANDOM
        RecetasAlimentos[] recetasPoblacion = new RecetasAlimentos[20];
        for (int i = 0; i < recetasPoblacion.length; i++) {
            racion1 = receta.getItem1().getAlimento().getDosis() * Math.random() * 2000;
            racion2 = receta.getItem2().getAlimento().getDosis() * Math.random() * 2000;
            racion3 = receta.getItem3().getAlimento().getDosis() * Math.random() * 2000;
            
            Item itemA = new Item(receta.getItem1().getAlimento(), racion1);
            Item itemB = new Item(receta.getItem2().getAlimento(), racion2);
            Item itemC = new Item(receta.getItem3().getAlimento(), racion3);
         
            recetasPoblacion[i] = new RecetasAlimentos(itemA, itemB, itemC);

        }

        //PUNTUAR POBLACION
        double[] puntuacion = new double[20];
        for (int i = 0; i < puntuacion.length; i++) {
            //calcular totales para comparar con requisitos
            double caloriasTotales = calcularCalorias3(recetasPoblacion[i]);
            double protesTotales = calcularProtes3(recetasPoblacion[i]);
            double grasasTotales = calcularGrasas3(recetasPoblacion[i]);
            //puntuar    
            double puntuacionA = Math.abs(caloriasTotales - requisitoCalorico);
            double puntuacionB = Math.abs(protesTotales - requisitoProte);
            double puntuacionC = Math.abs(grasasTotales - requisitoGrasa);
            puntuacion[i] = puntuacionA + puntuacionB + (puntuacionC / 2);

        }
        //buscamos las 2 puntuaciones mas altas, y guardamos su ubicacion
        int primero = 0;
        int segundo = 0;
        double puntuacionMax1 = Double.MAX_VALUE;
        double puntuacionMax2 = Double.MAX_VALUE;
        for (int i = 0; i < puntuacion.length; i++) {
            if (puntuacion[i] < puntuacionMax1) {
                primero = i;
                puntuacionMax1 = puntuacion[i];

            }
        }
        for (int i = 0; i < puntuacion.length - 1; i++) {
            if (puntuacion[i] < puntuacionMax2 && puntuacion[i] > puntuacion[primero]) {
                segundo = i;
                puntuacionMax2 = puntuacion[i];
            }
        }
        //pasamos las 2 recetas mas aptas a un nuevo array

        RecetasAlimentos[] recetasNuevo = new RecetasAlimentos[20];
        recetasNuevo[0] = new RecetasAlimentos(recetasPoblacion[primero].getItem1(), recetasPoblacion[primero].getItem2(), recetasPoblacion[primero].getItem3());
        recetasNuevo[1] = new RecetasAlimentos(recetasPoblacion[segundo].getItem1(), recetasPoblacion[segundo].getItem2(), recetasPoblacion[segundo].getItem3());

        //generamos dos recetas nuevas a partir de mezclar las 2 recetas mas aptas
        recetasNuevo[2] = new RecetasAlimentos(recetasPoblacion[primero].getItem1(), recetasPoblacion[segundo].getItem2(), recetasPoblacion[primero].getItem3());
        recetasNuevo[3] = new RecetasAlimentos(recetasPoblacion[segundo].getItem1(), recetasPoblacion[primero].getItem2(), recetasPoblacion[segundo].getItem3());

        //generamos recetas aleatorias hasta completar el array, a partir de aqui iniciamos el bucle
        for (int x = 0; x < 5000; x++) {

            for (int i = 4; i < recetasNuevo.length; i++) {
                racion1 = receta.getItem1().getAlimento().getDosis() * Math.random() * 2000;
                racion2 = receta.getItem2().getAlimento().getDosis() * Math.random() * 2000;
                racion3 = receta.getItem3().getAlimento().getDosis() * Math.random() * 2000;
               
                Item itemA = new Item(receta.getItem1().getAlimento(), racion1);
                Item itemB = new Item(receta.getItem2().getAlimento(), racion2);
                Item itemC = new Item(receta.getItem3().getAlimento(), racion3);
                
                recetasNuevo[i] = new RecetasAlimentos(itemA, itemB, itemC);

            }
            //PUNTUAR POBLACION EVOLUCIONADA

            for (int i = 0; i < puntuacion.length; i++) {
                //calcular totales para comparar con requisitos
                double caloriasTotales = calcularCalorias3(recetasNuevo[i]);
                double protesTotales = calcularProtes3(recetasNuevo[i]);
                double grasasTotales = calcularGrasas3(recetasNuevo[i]);
                //puntuar    
                double puntuacionA = Math.abs(caloriasTotales - requisitoCalorico);
                double puntuacionB = Math.abs(protesTotales - requisitoProte);
                double puntuacionC = Math.abs(grasasTotales - requisitoGrasa);
                puntuacion[i] = puntuacionA + puntuacionB + (puntuacionC / 2);

            }

            //buscamos las 2 puntuaciones mas altas, y guardamos su ubicacion
            primero = 0;
            segundo = 0;
            puntuacionMax1 = Double.MAX_VALUE;
            puntuacionMax2 = Double.MAX_VALUE;
            for (int i = 0; i < puntuacion.length; i++) {
                if (puntuacion[i] < puntuacionMax1) {
                    primero = i;
                    puntuacionMax1 = puntuacion[i];

                }
            }
            for (int i = 0; i < puntuacion.length - 1; i++) {
                if (puntuacion[i] < puntuacionMax2 && puntuacion[i] > puntuacion[primero]) {
                    segundo = i;
                    puntuacionMax2 = puntuacion[i];
                }
            }
            //pasamos las 2 recetas mas aptas a un nuevo array

            recetasNuevo[0] = new RecetasAlimentos(recetasNuevo[primero].getItem1(), recetasNuevo[primero].getItem2(), recetasNuevo[primero].getItem3());
            recetasNuevo[1] = new RecetasAlimentos(recetasNuevo[segundo].getItem1(), recetasNuevo[segundo].getItem2(), recetasNuevo[segundo].getItem3());

            //generamos dos recetas nuevas a partir de mezclar las 2 recetas mas aptas
            recetasNuevo[2] = new RecetasAlimentos(recetasNuevo[primero].getItem1(), recetasNuevo[segundo].getItem2(), recetasNuevo[primero].getItem3());
            recetasNuevo[3] = new RecetasAlimentos(recetasNuevo[segundo].getItem1(), recetasNuevo[primero].getItem2(), recetasNuevo[segundo].getItem3());

        }

        return recetasNuevo[0];
    }

    public static double calcularCalorias4(RecetasAlimentos receta) {
        double caloriasItem1 = (receta.getItem1().getAlimento().getCalorias() * receta.getItem1().getRacion());
        double caloriasItem2 = (receta.getItem2().getAlimento().getCalorias() * receta.getItem2().getRacion());
        double caloriasItem3 = (receta.getItem3().getAlimento().getCalorias() * receta.getItem3().getRacion());
        double caloriasItem4 = (receta.getItem4().getAlimento().getCalorias() * receta.getItem4().getRacion());
        return caloriasItem1 + caloriasItem2 + caloriasItem3 + caloriasItem4;
    }

    public static double calcularProtes4(RecetasAlimentos receta) {
        double protesItem1 = (receta.getItem1().getAlimento().getProtes() * receta.getItem1().getRacion());
        double protesItem2 = (receta.getItem2().getAlimento().getProtes() * receta.getItem2().getRacion());
        double protesItem3 = (receta.getItem3().getAlimento().getProtes() * receta.getItem3().getRacion());
        double protesItem4 = (receta.getItem4().getAlimento().getProtes() * receta.getItem4().getRacion());
        return protesItem1 + protesItem2 + protesItem3 + protesItem4;
    }

    public static double calcularGrasas4(RecetasAlimentos receta) {
        double grasasItem1 = (receta.getItem1().getAlimento().getGrasas() * receta.getItem1().getRacion());
        double grasasItem2 = (receta.getItem2().getAlimento().getGrasas() * receta.getItem2().getRacion());
        double grasasItem3 = (receta.getItem3().getAlimento().getGrasas() * receta.getItem3().getRacion());
        double grasasItem4 = (receta.getItem4().getAlimento().getGrasas() * receta.getItem4().getRacion());
        return grasasItem1 + grasasItem2 + grasasItem3 + grasasItem4;
    }

    public static double calcularCarbs4(RecetasAlimentos receta) {
        double carbsItem1 = (receta.getItem1().getAlimento().getCarbs() * receta.getItem1().getRacion());
        double carbsItem2 = (receta.getItem2().getAlimento().getCarbs() * receta.getItem2().getRacion());
        double carbsItem3 = (receta.getItem3().getAlimento().getCarbs() * receta.getItem3().getRacion());
        double carbsItem4 = (receta.getItem4().getAlimento().getCarbs() * receta.getItem4().getRacion());
        return carbsItem1 + carbsItem2 + carbsItem3 + carbsItem4;
    }
    
       public static double calcularCalorias3(RecetasAlimentos receta) {
        double caloriasItem1 = (receta.getItem1().getAlimento().getCalorias() * receta.getItem1().getRacion());
        double caloriasItem2 = (receta.getItem2().getAlimento().getCalorias() * receta.getItem2().getRacion());
        double caloriasItem3 = (receta.getItem3().getAlimento().getCalorias() * receta.getItem3().getRacion());
      
        return caloriasItem1 + caloriasItem2 + caloriasItem3;
    }

    public static double calcularProtes3(RecetasAlimentos receta) {
        double protesItem1 = (receta.getItem1().getAlimento().getProtes() * receta.getItem1().getRacion());
        double protesItem2 = (receta.getItem2().getAlimento().getProtes() * receta.getItem2().getRacion());
        double protesItem3 = (receta.getItem3().getAlimento().getProtes() * receta.getItem3().getRacion());
 
        return protesItem1 + protesItem2 + protesItem3;
    }

    public static double calcularGrasas3(RecetasAlimentos receta) {
        double grasasItem1 = (receta.getItem1().getAlimento().getGrasas() * receta.getItem1().getRacion());
        double grasasItem2 = (receta.getItem2().getAlimento().getGrasas() * receta.getItem2().getRacion());
        double grasasItem3 = (receta.getItem3().getAlimento().getGrasas() * receta.getItem3().getRacion());
  
        return grasasItem1 + grasasItem2 + grasasItem3;
    }

    public static double calcularCarbs3(RecetasAlimentos receta) {
        double carbsItem1 = (receta.getItem1().getAlimento().getCarbs() * receta.getItem1().getRacion());
        double carbsItem2 = (receta.getItem2().getAlimento().getCarbs() * receta.getItem2().getRacion());
        double carbsItem3 = (receta.getItem3().getAlimento().getCarbs() * receta.getItem3().getRacion());
     
        return carbsItem1 + carbsItem2 + carbsItem3;
    }
    
     public static double calcularCaloriasSelectivo(RecetasAlimentos receta){
        double caloriasTotales=0;
        
        if(receta.getItem4()!=null){
            
           caloriasTotales= calcularCalorias4(receta);
        }
        if(receta.getItem3()!=null&&receta.getItem4()==null){
             caloriasTotales= calcularCalorias3(receta);
        }
        return caloriasTotales;
      
    }
}
