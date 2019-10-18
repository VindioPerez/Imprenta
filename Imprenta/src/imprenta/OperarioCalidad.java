/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author VindioPerez
 * @version 1.0
 */
public class OperarioCalidad extends Operario {
    
    
    public OperarioCalidad() {
    }

    public OperarioCalidad(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }


    
    public OperarioCalidad (OperarioCalidad o) {
        super(o);
  
    }

    

    @Override
    public String toString() {
        return super.toString();
    }
    
    public String data() {
        return super.data(); 
    }
}
