package imprenta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

    //constructor por defecto
    public Libro() {
    }

    //constructores por argumentos
    public Libro(int numPag, String color, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.numPag = numPag;
        this.color = color;
    }
    
    public Libro(long id, int numPag, String color){
        this.id = id;
        this.numPag = numPag;
        this.color = color;
    }

    //constructor de copia
    public Libro(Libro l) {
        this.color = l.getColor();
        this.numPag = l.getNumPag();
    }

    //constructor desde clase padre
    public Libro(Trabajo t) throws TrabajoException {
        super(t);
    }
    
    //getters y setters
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
    
    //getAll, getById, data y toString
    @Override
    public String toString() {
        return "Libro{" + "numPag=" + numPag + ", color=" + color + '}';
    }

    @Override
    public String data() {
        return this.getId() + "|" + this.getNumPag() + "|" + this.getColor();
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
    
    //lectura y escritura
    public static ArrayList<Libro> fromTextFile (String path) {
        ArrayList<Libro> ret = new ArrayList<>();
        File fichero = new File(path);
        FileReader lector = null;
        BufferedReader buffer = null ;
        try {
            try {
                lector = new FileReader(fichero);
                buffer = new BufferedReader(lector);
                String linea;
                while((linea=buffer.readLine())!=null){
                    String[] campos = linea.split("\\|");
                    long id = Long.parseLong(campos[0]);
                    int numPag = Integer.parseInt(campos[1]);
                    String color = campos[2];
                    Libro c = new Libro(id, numPag, color);
                    ret.add(c);                   
                }
            }finally{
                if(buffer!=null)
                    buffer.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException");
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception");
        }
        return ret;
    }
    
    public static ArrayList<Libro> fromBinaryFile (String path) {
        ArrayList<Libro> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Libro c;
                while((c = (Libro)lectorObjeto.readObject())!=null)
                    ret.add(c);
            }finally{
                if(lector!=null)
                    lector.close();
                if(lectorObjeto!=null)
                    lectorObjeto.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException");
        }
        catch(ClassNotFoundException e){
            System.out.println("Se ha producido una ClassNotFoundException");
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception");
        }
        return ret;
    }
    
    public void toTextFile (String path){
        File fichero = new File(path);
        FileWriter escritor = null;
        PrintWriter buffer = null ;
        try {
            try {
                escritor = new FileWriter(fichero);
                buffer = new PrintWriter(escritor);
                buffer.println(this.data());
            }finally{
                if(buffer!=null)
                    buffer.close();
                if(escritor!=null)
                    escritor.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException");
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception");
        }
    }
    
    public void toBinaryFile (String path) {
        FileOutputStream escritor = null;
        ObjectOutputStream escritorObjeto = null;
        try{
            try{
                escritor = new FileOutputStream(path);
                escritorObjeto = new ObjectOutputStream(escritor);
                escritorObjeto.writeObject(this);
            }finally{
                if(escritor!=null)
                    escritor.close();
                if(escritorObjeto!=null)
                    escritorObjeto.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException");
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception");
        }
    }
    
    //metodos propios
    public static Libro nuevoLibro() throws TrabajoException{
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

    public static Libro encargo (Cliente c) throws TrabajoException{
        Libro r;
        Scanner in = new Scanner(System.in);
        boolean check;
        do{
            r = new Libro(Trabajo.encargo(c));
            System.out.println("Introduzca el número de páginas");
            int num = in.nextInt();
            while (num<1){
                System.out.println("Por favor, introduzca un valor válido");
                num = in.nextInt();
            }
            r.setNumPag(num);
            System.out.println("Introduzca el color");
            String col = in.nextLine();
            r.setColor(col);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Recogida: " + r.getFechaRecogida());
            System.out.println("Relieve: " + r.getRelieve());
           System.out.println("Número de páginas: " + num);
            System.out.println("Color: " + col);
            check = ToolBox.leerBoolean();
        } while (!check);            
        
        return r;
    }
    
}
