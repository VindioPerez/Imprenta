/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author DAW104
 */
public class ReglaCalidad {
  
    
private ArrayList<Prueba> pruebas; //ArrayList que contiene las pruebas de 0 a n 

    public ArrayList<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(ArrayList<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public static ReglaCalidad nuevoRegla() throws ParseException{
        ReglaCalidad regla = new ReglaCalidad();
        boolean d;
        boolean c=true;
        do{
        ArrayList<Prueba> pruebas = new ArrayList();
            regla.setPruebas(pruebas);
            
            System.out.println("Quiere Introducir una nueva Prueba? s/n ");
            d = ToolBox.leerBoolean();
            

            while (d) {
                regla.pruebas.add(Prueba.nuevoPrueba());

                System.out.println("Quiere Introducir otra Prueba? s/n ");
                d = ToolBox.leerBoolean();
            }
            // preguntar si los datos son correctos en caso de añadir nuevos atributos
       
        }while(c);
        return regla;
    }

    @Override
    public String toString() {
        return "ReglaCalidad{" + "pruebas=" + pruebas + '}';
    }
    
    public String data() {
        return pruebas.toString();
    }


}
