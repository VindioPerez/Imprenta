/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ander
 * @version 1.1
 */
public class Rotulo extends Trabajo implements Serializable {

    private String centroComercial; //variable de tipo String que recoge el lugar de destino del trabajo

    //constructor por argumentos
    public Rotulo(String centroComercial, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.centroComercial = centroComercial;
    }
    
    public Rotulo(long id, String centroComercial){
        this.id = id;
        this.centroComercial = centroComercial;
    }

    //constructor por defecto
    public Rotulo() {
    }

    //constructor de copia
    public Rotulo(Rotulo r) {
        this.centroComercial = r.getCentroComercial();
    }
    
    //constructor desde clase padre
    public Rotulo(Trabajo t) throws TrabajoException {
        super(t);
    }
    
    //getters y setters
    public String getCentroComercial() {
        return centroComercial;
    }

    public void setCentroComercial(String centroComercial) {
        this.centroComercial = centroComercial;
    }
    
    //getAll, getById, data y toString
    @Override
    public String toString() {
        return "Rotulo{" + "direccion=" + centroComercial + '}';
    }

    @Override
    public String data() {
        return getId() + "|" + this.getCentroComercial();
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
    
    //lectura y escritura
    public static ArrayList<Rotulo> readRotuloFromTextFile (String path) {
        ArrayList<Rotulo> ret = new ArrayList<>();
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
                    String centroComercial = campos[1];
                    Rotulo c = new Rotulo(id, centroComercial);
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
    
    public static ArrayList<Rotulo> readRotuloFromBinaryFile (String path) {
        ArrayList<Rotulo> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Rotulo c;
                while((c = (Rotulo)lectorObjeto.readObject())!=null){
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
    
    public void writeRotuloToTextFile (String path){
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
    
    public void writeRotuloToBinaryFile (String path) {
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
    public static Rotulo nuevoRotulo() throws ParseException, TrabajoException{
        Rotulo r = new Rotulo(Trabajo.nuevoTrabajo());
        Scanner in = new Scanner(System.in);
        boolean c;
        do {
            System.out.println("Introduzca el centro comercial");
            String cen = in.nextLine();
            r.setCentroComercial(cen);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Centro comercial: " + cen);
            c = ToolBox.leerBoolean();
        } while (!c);
        return r;
    }
    
    public static Rotulo encargo (Cliente c) throws TrabajoException{
        Rotulo r;
        Scanner in = new Scanner(System.in);
        boolean check;
        do{
            r = new Rotulo(Trabajo.encargo(c));
            System.out.println("Introduzca el centro comercial");
            String cen = in.nextLine();
            r.setCentroComercial(cen);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Recogida: " + r.getFechaRecogida());
            System.out.println("Relieve: " + r.getRelieve());
            System.out.println("Centro comercial: " + cen);
            check = ToolBox.leerBoolean();
        } while (!check);            
        
        return r;
    }

}
