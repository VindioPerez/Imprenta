/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.util.ArrayList;
/**
 *
 * @author VindioPerez
 * @version 1.0
 */
public class OMaquinas extends Operario{
    
    private ArrayList<Labor> labores;
    
    public OMaquinas() {
        super();
    }

    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }
    
    public OMaquinas (OMaquinas o) {
       super(o);
    }
    
    public OMaquinas (Operario o){
        super(o);
        
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    public String data() {
        return super.data() ; 
    }
 
    public static OMaquinas nuevoOMaquinas(){
        OMaquinas OM = new OMaquinas(Operario.nuevoOperario());
        return OM;
        
    }
}
  
