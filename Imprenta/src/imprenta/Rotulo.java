/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ander
 * @version 1.0
 */
public class Rotulo extends Trabajo {

    private String centroComercial; //variable de tipo String que recoge el lugar de destino del trabajo

    public Rotulo(String centroComercial, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.centroComercial = centroComercial;
    }

    public Rotulo() {
    }

    public Rotulo(Rotulo r) {
        this.centroComercial = r.getCentroComercial();
    }
    
    public Rotulo(Trabajo t) {
        super(t);
    }
    
    public static Rotulo nuevoRotulo() throws ParseException{
        Rotulo r = new Rotulo(Trabajo.nuevoTrabajo());
        Scanner in = new Scanner(System.in);
        char c;
        do {
            System.out.println("Introduzca el centro comercial");
            String cen = in.nextLine();
            r.setCentroComercial(cen);
            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Centro comercial: " + cen);
            c = in.next().charAt(0);
        } while (c != 's');
        return r;
    }

    public String getCentroComercial() {
        return centroComercial;
    }

    public void setCentroComercial(String centroComercial) {
        this.centroComercial = centroComercial;
    }

    @Override
    public String toString() {
        return "Rotulo{" + "direccion=" + centroComercial + '}';
    }

    public String data() {
        return getId() + "|" + getFechaSolicitud() + getRelieve() + "|" + getFechaRecogida() + "|" + centroComercial;

    }
    
    public Rotulo getRotuloById(long id) {
        /*Este método recorrerá un ArrayList con todos los rotulos buscando aquel con el id que le introduzcamos, y devolverá ese rotulo si es que existe o 
        nulo si es que no existe*/
        Rotulo l = new Rotulo();
        return l;
    }

    public ArrayList<Rotulo> getAllRotulo() {
        /*Este método devolverá un arrayList con todos los rotulos existentes*/
        ArrayList<Rotulo> o = new ArrayList<>();
        return o;
    }

}
