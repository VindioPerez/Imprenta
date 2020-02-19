package imprenta;

import java.io.BufferedReader;
import java.io.EOFException;
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

    //constructor por defecto
    public Poster() {
    }

    //constructor por argumentos
    public Poster(double alto, double ancho, int numCopias, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }
    
    public Poster(long id, double alto, double ancho, int numCopias){
        this.id = id;
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }

    //constructor de copia
    public Poster(Poster p) {
        this.alto = p.getAlto();
        this.ancho = p.getAncho();
        this.numCopias = p.getNumCopias();
    }

    //constructor desde clase padre
    public Poster(Trabajo t) throws TrabajoException {
        super(t);
    }
    
    //getters y setters
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
    
    //getAll, getById, data y toString
    @Override
    public String toString() {
        return "Poster{" + "alto=" + alto + ", ancho=" + ancho + ", numCopias=" + numCopias + '}';
    }

    @Override
    public String data() {
        return this.getId() + "|" + this.getAlto() + "|" + this.getAncho() + "|" + this.getNumCopias();
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
    
    //lectura y escritura
    public static ArrayList<Poster> readPosterFromTextFile (String path) {
        ArrayList<Poster> ret = new ArrayList<>();
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
                    double alto = Double.parseDouble(campos[1]);
                    double ancho = Double.parseDouble(campos[2]);
                    int numCopias = Integer.parseInt(campos[3]);
                    Poster c = new Poster(id, alto, ancho, numCopias);
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
            System.out.println("Se ha producido una FileNotFoundException"+e.getMessage());
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException"+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception"+e.getMessage());
        }
        return ret;
    }
    
    public static ArrayList<Poster> readPosterFromBinaryFile (String path) {
        ArrayList<Poster> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Poster c;
                while((c = (Poster)lectorObjeto.readObject())!=null){
                    ret.add(c);
                    lector.skip(4);}
            }finally{
                if(lectorObjeto!=null)
                    lectorObjeto.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException"+e.getMessage());
        }
        catch(EOFException e){
            System.out.println("Final de fichero");
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException: "+e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println("Se ha producido una ClassNotFoundException"+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception"+e.getMessage());
        }
        return ret;
    }
    
    public void writePosterToTextFile (String path){
        File fichero = new File(path);
        FileWriter escritor = null;
        PrintWriter buffer = null ;
        try {
            try {
                escritor = new FileWriter(fichero, true);
                buffer = new PrintWriter(escritor);
                buffer.print(this.data()+"\r\n");
            }finally{
                if(buffer!=null)
                    buffer.close();
                if(escritor!=null)
                    escritor.close();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException"+e.getMessage());
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException"+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception"+e.getMessage());
        }
    }
    
    public void writePosterToBinaryFile (String path) {
        try{
            FileOutputStream fichero = new FileOutputStream(path, true);
            ObjectOutputStream escritor = new ObjectOutputStream(fichero);
            escritor.writeObject(this);
            escritor.flush();
            escritor.close();
        }       
        catch(FileNotFoundException e){
            System.out.println("Se ha producido una FileNotFoundException"+e.getMessage());
        }
        catch(IOException e){
            System.out.println("Se ha producido una IOException"+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Se ha producido una Exception"+e.getMessage());
        }
    }

    //metodos propios
    public static Poster nuevoPoster() throws TrabajoException{
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
    
    public static Poster encargo (Cliente c) throws TrabajoException {
        Poster r;
        Scanner in = new Scanner(System.in);
        boolean check;
        do{
            r = new Poster(Trabajo.encargo(c));
            System.out.println("Introduzca el alto en cm");
            double alt = in.nextDouble();
            while (alt<10){
                System.out.println("Por favor, introduzca un valor válido");
                alt = in.nextDouble();
            }
            r.setAlto(alt);
            System.out.println("Introduzca el ancho en cm");
            double anc = in.nextDouble();
            while (anc<10){
                System.out.println("Por favor, introduzca un valor válido");
                anc = in.nextDouble();
            }
            r.setAncho(anc);
            System.out.println("Introduzca el número de copias");
            int num = in.nextInt();
            r.setNumCopias(num);
            while (num<1){
                System.out.println("Por favor, introduzca un valor válido");
                num = in.nextInt();
            }
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
