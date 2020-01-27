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
public class ClienteException extends RuntimeException{

    public ClienteException() {
    }

    public ClienteException(String message) {
        super(message);
    }
    
    public static boolean comprobarTelefono(String telefono){
        if (telefono.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) return true;
        else return false;
    }
    
    public static boolean comprobarNombre(String nombre){
        if (!nombre.isEmpty()) return true;
        else return false;
    }
    
}
