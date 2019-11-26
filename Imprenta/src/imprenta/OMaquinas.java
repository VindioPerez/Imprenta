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
 * @version 1.1
 */
public class OMaquinas extends Operario {

    private ArrayList<Labor> labores;//variable array que contiene todas las labores del operario de maquinas

    public OMaquinas() {
        super();
    }

    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }

    public OMaquinas(OMaquinas o) {
        super(o);
    }

    public OMaquinas(Operario o) {
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

    public static OMaquinas nuevoOMaquinas() {
        OMaquinas OM = new OMaquinas(Operario.nuevoOperario());
        return OM;

    }

    public ArrayList<Labor> getAllLabores() {
        ArrayList<Labor> listaLabores = new ArrayList<>();
        /*
        Devuelve la lista con todas las labores
        */
        return listaLabores;
    }

        public OMaquinas getOperarioMaquinasById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OMaquinas om = new OMaquinas();
        return om;
    }
    
    
}
