/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author VindioPerez
 * @version 1.1
 */
public class OCalidad extends Operario {

    public OCalidad() {
    }

    public OCalidad(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }
    
    public OCalidad(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior, long id) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
    }

    public OCalidad(OCalidad o) {
        super(o);

    }

    public OCalidad(Operario o) {
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

    public static OCalidad nuevoOCalidad() {

        OCalidad oc = new OCalidad(Operario.nuevoOperario());

        return oc;
    }

    public OCalidad getOperarioCalidadById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OCalidad oc = new OCalidad();
        return oc;
    }
}
