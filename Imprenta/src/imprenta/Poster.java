package imprenta;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ander
 * @version 1.1
 */
public class Poster extends Trabajo {

    private double alto;//variable tipo integer que recoge el alton del poster
    private double ancho;//variable tipo integer que recoge el ancho del poster
    private int numCopias;//variable tipo integer que recoge el número de copias de los posters solicitados

    public Poster() {
    }

    public Poster(double alto, double ancho, int numCopias, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }

    public Poster(Poster p) {
        this.alto = p.getAlto();
        this.ancho = p.getAncho();
        this.numCopias = p.getNumCopias();
    }

    public Poster(Trabajo t) {
        super(t);
    }

    public static Poster nuevoPoster() throws ParseException{
        Poster p = new Poster(Trabajo.nuevoTrabajo());
        Scanner in = new Scanner(System.in);
        boolean c;
        do {
            System.out.println("Introduzca el alto en cm");
            double alt = in.nextDouble();
            p.setAlto(alt);
            System.out.println("Introduzca el ancho en cm");
            double anc = in.nextDouble();
            p.setAncho(anc);
            System.out.println("Introduzca el número de copias");
            int num = in.nextInt();
            p.setNumCopias(num);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Alto (cm): " + alt);
            System.out.println("Ancho (cm): " + anc);
            System.out.println("Número de copias: " + num);
            c = ToolBox.leerBoolean();
        } while (!c);
        return p;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    @Override
    public String toString() {
        return "Poster{" + "alto=" + alto + ", ancho=" + ancho + ", numCopias=" + numCopias + '}';
    }

    @Override
    public String data() {
        return getId() + "|" + getFechaSolicitud() + getRelieve() + "|" + getFechaRecogida() + "|" + this.getAlto() + "|" + this.getAncho() + "|" + this.numCopias;
    }
    
    public Poster getPosterById(long id) {
        /*Este método recorrerá un ArrayList con todos los posters buscando aquel con el id que le introduzcamos, y devolverá ese poster si es que existe o 
        nulo si es que no existe*/
        Poster l = new Poster();
        return l;
    }

    public ArrayList<Poster> getAllPoster() {
        /*Este método devolverá un arrayList con todos los posters existentes*/
        ArrayList<Poster> o = new ArrayList<>();
        return o;
    }
    
    public static Poster encargo (Cliente c) {
        Poster r;
        Scanner in = new Scanner(System.in);
        boolean check;
        do{
            r = new Poster(Trabajo.encargo(c));
            System.out.println("Introduzca el alto en cm");
            double alt = in.nextDouble();
            r.setAlto(alt);
            System.out.println("Introduzca el ancho en cm");
            double anc = in.nextDouble();
            r.setAncho(anc);
            System.out.println("Introduzca el número de copias");
            int num = in.nextInt();
            r.setNumCopias(num);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Recogida: " + r.getFechaRecogida());
            System.out.println("Relieve: " + r.getRelieve());
            System.out.println("Alto (cm): " + alt);
            System.out.println("Ancho (cm): " + anc);
            System.out.println("Número de copias: " + num);
            check = ToolBox.leerBoolean();
        } while (!check);            
        
        return r;
    }

    
    
}
