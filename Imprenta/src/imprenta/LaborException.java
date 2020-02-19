/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alberto
 */
public class LaborException extends RuntimeException{
    
     public LaborException() {
    }

    public LaborException(String message) {
        super(message);
    }
    
    
    public static boolean comprobarFechaini(Date fechaini){
          return fechaini!=null;
    }
    public static boolean comprobarFechafin(Date fechafin){
        return fechafin!=null;
    }
    public static boolean comprobarTarea(String tarea){
    return !tarea.isEmpty();
    }
    public static boolean comprobarMaquina(Maquina maquina){
        return maquina!=null;
    }
    public static boolean comprobarOperariosM(ArrayList<OMaquinas> operariosM){
      return operariosM !=null;  
    }
    public static boolean comprobarIdMaquina (long idMaquina) {
        return idMaquina>0;
    }
}
