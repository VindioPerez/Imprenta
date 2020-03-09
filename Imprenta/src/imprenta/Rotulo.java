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
 * Modela el poster, una de las subclases de {@link Trabajo}
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Rotulo extends Trabajo implements Serializable {

    private String centroComercial; //variable de tipo String que recoge el lugar de destino del trabajo
    
    //constructor por defecto
    /**
     * Crea una instancia de Rotulo con los valores por defecto para los atributos
     */
    public Rotulo() {
    }

    //constructor por argumentos
    /**
     * Crea una instancia de Rotulo con los atributos propios de la clase y los de la superclase {@link Trabajo}, sin los de de las relaciones ni el id
     * @param centroComercial el centro comercial en el que se instalara el rotulo
     * @param fechaSolicitud la fecha de solicitud del trabajo
     * @param fechaRecogida la fecha de recogida del trabajo
     * @param relieve el relieve del trabajo
     */
    public Rotulo(String centroComercial, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.centroComercial = centroComercial;
    }
    
    /**
     * Crea una instancia de Poster con los atributos propios de la clase
     * @param id el id del poster
     * @param centroComercial el centro comercial en el que se instalara el rotulo
     */
    public Rotulo(long id, String centroComercial){
        this.id = id;
        this.centroComercial = centroComercial;
    }
    
    //constructor de copia
    /**
     * Crea una instancia de Rotulo a partir de otra, copiando cada atributo
     * @param r el Rotulo que se va a copiar
     */
    public Rotulo(Rotulo r) {
        this.centroComercial = r.getCentroComercial();
    }
    
    //constructor desde clase padre
    /**
     * Crea una instancia de Rotulo a partir de otra de la superclase {@link Trabajo}
     * @param t el Trabajo a partir del cual se va a generar el Rotulo
     * @throws TrabajoException si los datos del trabajo introducido no son validos
     */
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
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Poster que llama al metodo
     * @return un <code>String</code> con el atributo centroComercial del objeto
     */
    @Override
    public String toString() {
        return "Rotulo{" + "direccion=" + centroComercial + '}';
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>centroComercial</code>
     */
    @Override
    public String data() {
        return getId() + "|" + this.getCentroComercial();
    }
    
    /**
     * Recorre el <code>ArrayList</code> de rotulos de {@link BDatos} y devuelve el rotulo con el id que se pasa como parametro
     * @param id el id del rotulo que se quiere buscar en la base de datos
     * @return el <code>Rotulo</code> con el id coincidente con <code>idRotulo</code>, o nulo si no existe dicho rotulo
     */
    public Rotulo getRotuloById(long id) {
        /*Este método recorrerá un ArrayList con todos los rotulos buscando aquel con el id que le introduzcamos, y devolverá ese rotulo si es que existe o 
        nulo si es que no existe*/
        Rotulo l = new Rotulo();
        return l;
    }

    /**
     * Devuelve todos los rotulos registrados en el sistema
     * @return un <code>ArrayList</code> con todos los rotulos de la base de datos
     */
    public ArrayList<Rotulo> getAllRotulo() {
        /*Este método devolverá un arrayList con todos los rotulos existentes*/
        ArrayList<Rotulo> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de rotulos desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los rotulos existentes en el fichero
     */
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
    
    /**
     * Importa un grupo de rotulos desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los rotulos existentes en el fichero
     */
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
    
    /**
     * Exporta los datos de un <code>Rotulo</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @see Rotulo#data() data
     */
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
    
    /**
     * Exporta un <code>Rotulo</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     */
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
    /**
     * Crea una nueva instancia de la clase <code>Rotulo</code> pidiendo al usuario por pantalla que introduzca los datos, y llamando al metodo nuevoTrabajo de {@link Trabajo} para completar los datos de la superclase
     * @return el <code>Rotulo</code> que se crea con el método
     * @see Trabajo#nuevoTrabajo() Trabajo.nuevoTrabajo
     * @throws TrabajoException si los datos introducidos no son validos
     */
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
    
    /**
     * Crea un <code>Rotulo</code> nuevo pidiendo datos por pantalla, y llamando al metodo encargo de {@link Trabajo} para completar los datos de la superclase. Relacionado con el caso de uso Solicitar trabajo
     * @param c el <code>Cliente</code> que solicita el trabajo
     * @return el <code>Rotulo</code> que se crea con el método
     * @throws TrabajoException si los datos introducidos no son validos
     * @see Trabajo#encargo(Cliente) Trabajo.encargo
     * @see Cliente#crearTrabajo() Cliente.crearTrabajo
     */
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
