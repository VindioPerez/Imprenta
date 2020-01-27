/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author VindioPerez
 * @version 1.1
 */
public class OImpresion extends Operario {

    public OImpresion() {
        super();
    }

    public OImpresion(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }
    
    public OImpresion(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior, long id) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
    }

    public OImpresion(OImpresion o) {
        super(o);
    }

    public OImpresion(Operario o) {
        super(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String data() {
        return super.data();
    }

    public static OImpresion nuevoOImpresion() {
        OImpresion OI = new OImpresion(Operario.nuevoOperario());

        return OI;
    }
            public OImpresion getOperarioImpresionById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OImpresion oi = new OImpresion();
        return oi;
    }

            
}
