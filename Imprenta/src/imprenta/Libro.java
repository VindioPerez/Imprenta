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
 * @author DAW108
 * @version 1.1
 */
public class Libro extends Trabajo {

    private int numPag;//variable tipo int que recoge el número de páginas que tiene el libro;
    private String color; //variable tipo String que recoge el color del libro

    public Libro() {
    }

    public Libro(int numPag, String color, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.numPag = numPag;
        this.color = color;
    }

    public Libro(Libro l) {
        this.color = l.getColor();
        this.numPag = l.getNumPag();
    }

    public Libro(Trabajo t) {
        super(t);
    }

    public static Libro nuevoLibro() throws ParseException{
        Libro l = new Libro(Trabajo.nuevoTrabajo());
        Scanner in = new Scanner(System.in);
        boolean c;
        do {
            System.out.println("Introduzca el número de páginas");
            int num = in.nextInt();
            l.setNumPag(num);
            System.out.println("Introduzca el color");
            String col = in.nextLine();
            l.setColor(col);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Número de páginas: " + num);
            System.out.println("Color: " + col);
            c = ToolBox.leerBoolean();
        } while (!c);
        return l;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Libro{" + "numPag=" + numPag + ", color=" + color + '}';
    }

    public String data() {

        return getId() + "|" + getFechaSolicitud() + "|" + getRelieve() + getFechaRecogida() + "|" + color + "|" + numPag;
    }
    
    public Libro getLibroById(long id) {
        /*Este método recorrerá un ArrayList con todos los libros buscando aquel con el id que le introduzcamos, y devolverá ese libro si es que existe o 
        nulo si es que no existe*/
        Libro l = new Libro();
        return l;
    }

    public ArrayList<Libro> getAllLibro() {
        /*Este método devolverá un arrayList con todos los libros existentes*/
        ArrayList<Libro> o = new ArrayList<>();
        return o;
    }

}
