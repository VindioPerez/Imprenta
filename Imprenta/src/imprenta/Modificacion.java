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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alberto
 * @version1
 */
public class Modificacion {

    protected long id;//idenitficador 
    private OImpresion operarioI;//operarios que realizan la modificación
    private Date fecha;//fecha de realización
    private String desc;//descripción del cambio
    private boolean aprob;//aprobacion del cliente
    private Date fechaAprob;//fecha limite aprobación del cliente
    private long idCliente;//variable con el id de cliente que solicita la modificación
    private long idTrabajo;//variable con el id del trabajo a modificar
    private long idOImpresion;//variable con el id del operario de impresion que crea la modificacion
    private Trabajo trabajo;//trabajo sobre el que se crea la modificacion
    
    
    
    //constructores con argumentos
    public Modificacion(long id, Date fecha, String desc, boolean aprob, Date fechaAprob, Maquina maquina,OImpresion operarioI) {
        this.operarioI = operarioI;
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fechaAprob;
    }    
    
    public Modificacion(long id, Date fecha, String desc, boolean aprob, Date fechaAprob, Maquina maquina,Trabajo trabajo,OImpresion operarioI) {
        this.operarioI = operarioI;
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fechaAprob;
        this.trabajo = trabajo;
    }
    
    public Modificacion(long id, long idTra, long idCli, long idOpe, Date fecha, String desc, boolean aprob, Date fApro){
        this.id = id;
        this.idCliente = idCli;
        this.idOImpresion = idOpe;
        this.idTrabajo = idTra;
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fApro;
    }
    
    //constructor por defecto
    public Modificacion() {
    }

    //constructor de copia
    public Modificacion(Modificacion m) {
        this.operarioI = m.getOperarioI();
        this.fecha = m.getFecha();
        this.desc = m.getDesc();
        this.aprob = m.isAprob();
        this.fechaAprob = m.getFechaAprob();
    }

    //getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OImpresion getOperarioI() {
        return operarioI;
    }

    public void setOperarioI(OImpresion operarioI) {
        this.operarioI = operarioI;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isAprob() {
        return aprob;
    }

    public void setAprob(boolean aprob) {
        this.aprob = aprob;
    }

    public Date getFechaAprob() {
        return fechaAprob;
    }

    public void setFechaAprob(Date fechaAprob) {
        this.fechaAprob = fechaAprob;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public long getIdOImpresion() {
        return idOImpresion;
    }

    public void setIdOImpresion(long idOImpresion) {
        this.idOImpresion = idOImpresion;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    //data y toString
    public String data() {
        return this.getId() + "|" + this.getIdTrabajo() + "|" + this.getIdCliente() + "|" + this.getIdOImpresion() + "|" + this.getFecha() + "|" + this.getDesc() + "|" + this.isAprob() + "|" + this.getFechaAprob();
    }

    @Override
    public String toString() {
        return "Modificacion{" + "id=" + id + ", operario=" + operarioI + ", fecha=" + fecha + ", desc=" + desc + ", aprob=" + aprob + ", fechaAprob=" + fechaAprob + ", trabajo="+ trabajo.toString()+'}';
    }
    
    public Modificacion getModificacionById(long idModificacion) {

        Modificacion t = new Modificacion();
        /*
        Este método se encarga de recorrer un arraylist con las modificaciones, si el 
        id de parametro coincide con el del modificacion se devuelve esa modificacion sino
        se devuelve null
         */

        return t;
        }
    
    public ArrayList<Modificacion> getAllModificacion() {
        /*Este método devolverá un arrayList con todos los libros existentes*/
        ArrayList<Modificacion> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
    public static ArrayList<Modificacion> readModificacionFromTextFile (String path) {
        ArrayList<Modificacion> ret = new ArrayList<>();
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
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    long id = Long.parseLong(campos[0]);
                    long idTra = Long.parseLong(campos[1]);
                    long idCli = Long.parseLong(campos[2]);
                    long idOpe = Long.parseLong(campos[3]);
                    Date fecha = df.parse(campos[4]);
                    String desc = campos[5];
                    boolean aprob = Boolean.parseBoolean(campos[6]);
                    Date fApro = df.parse(campos[7]);
                    Modificacion c = new Modificacion(id, idTra, idCli, idOpe, fecha, desc, aprob, fApro);
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
    
    public static ArrayList<Modificacion> readModificacionFromBinaryFile (String path) {
        ArrayList<Modificacion> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Modificacion c;
                while((c = (Modificacion)lectorObjeto.readObject())!=null){
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
    
    public void writeModificacionToTextFile (String path){
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
    
    public void writeModificacionToBinaryFile (String path) {
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
    public static Modificacion nuevaModificacion(){
        Modificacion m = new Modificacion();
        Scanner in = new Scanner(System.in);
        OImpresion operarioI;

        boolean salir;
        boolean cliente;
        boolean operario;
        do {

            System.out.println("introduzca la fecha de Realización:");
            Date fechaRea = ToolBox.introducirFecha();
            System.out.println("introduzca la descripción del cambio:");
            String cambio = in.nextLine();
            m.setDesc(cambio);
            
          

            System.out.println("¿El cliente ha aprobado el cambio?");
            cliente = ToolBox.leerBoolean();
            if (!cliente) {
                System.out.println("Cambio no aceptado");
            } else {
                System.out.println("Cambio aceptado");
            }
            System.out.println("Los operarios de impresión han aprobado el cambio?");
            operario = ToolBox.leerBoolean();
            if (!operario) {
                System.out.println("Cambio no aceptado");
            } else {
                System.out.println("Cambio aceptado");
            }
            System.out.println("introduzca la fecha de aprobación del cliente:");
            Date fechaApr = ToolBox.introducirFecha();

            System.out.println("Son correctos los cambios?(s/n)");
            System.out.println("Fecha Realización " + fechaRea);
            System.out.println("Fecha Aprobación: " + fechaApr);
            System.out.println("Solicitud aprobada por el cliente" + cliente);
            System.out.println("Solicitud aprobada por los operarios" + operario);
            System.out.println("Descripción: " + cambio);

            salir = ToolBox.leerBoolean();

        } while (!salir);
        return m;
    }    
}
