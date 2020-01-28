/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author vindi
 */
public class TrabajoException extends RuntimeException{

    public TrabajoException() {
    }

    public TrabajoException(String message) {
        super(message);
    }
    
    public static boolean comprobarRelieve(String relieve){
        return !relieve.isEmpty();
    
    
    }
    
    public static boolean comprobarId(long id){
        boolean positivo;
        positivo = id > 0;
        return positivo;
    }
    
    public static boolean comprobarClienteVacion(Cliente c){
        boolean vacio;
        vacio = c != null;
        return vacio;
    }
}
