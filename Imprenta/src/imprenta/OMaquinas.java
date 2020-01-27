/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;
import java.util.Scanner;

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

    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior, long id) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
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

    public static OMaquinas getOperarioMaquinasById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OMaquinas om = new OMaquinas();
        return om;
    }

    public boolean rellenarMaquina(long idMaquina) {
        Scanner sc = new Scanner(System.in);
        boolean rellenada;
        Maquina m = Maquina.getMaquinaById(idMaquina);
        m.setDisponible(false);
        System.out.println("La máquina" + m.getId());
        System.out.println("Localizada en:" + m.getUbicacion());
        System.out.println("Tiene un volumen de tinta:" + m.getVolTinta());
        System.out.println("Su volúmen máximo de tinta es:" + m.getCapMax());
        System.out.println("Desea rellenar la máquina?");
        boolean decision = ToolBox.leerBoolean();
        if (m.getVolTinta() < 0.0 && m.getVolTinta() < m.getCapMax()) {
            if (decision) {
                System.out.println("Por favor introduzca el volumen de tinta a rellenar");
                float vol = sc.nextFloat();
                m.setVolTinta(vol);
                rellenada = true;
            } else {
                System.out.println("La máquina se va a quedar igual");
                rellenada = false;

            }
        } else {
            System.out.println("El volumen de la máquina no es correcto");
            rellenada = false;
        }
        
        System.out.println("El resultado de la máquina es:"+m.toString());
        
        return rellenada;
    }

}
