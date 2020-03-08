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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Modela la modificacion que crea sobre un {@link Trabajo} un {@link OImpresion], y que tiene que aprobar el {@link Cliente} que ha solicitado el trabajo
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Modificacion implements Serializable {

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
    
    //constructor por defecto
    /**
     * Crea una instancia de Modificacion con los valores por defecto para los atributos
     */
    public Modificacion() {
    }
    
    //constructores con argumentos
    /**
     * Crea una instancia de Modificacion con los atributos propios de la clase menos el id, y con el {@link OImpresion} que la crea
     * @param fecha la fecha de realizacion de la modificacion
     * @param desc la descripcion de la modificacion
     * @param aprob si la modificacion esta o no aprobada por el cliente
     * @param fechaAprob la fecha de aprobacion por el cliente
     * @param operarioI el operario de impresion que crea la modificacion
     */
    public Modificacion(Date fecha, String desc, boolean aprob, Date fechaAprob,OImpresion operarioI) {
        this.operarioI = operarioI;
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fechaAprob;
    }    
    
    /**
     * Crea una instancia de Modificacion con los atributos propios de la clase menos el id, y con el {@link OImpresion} que la crea y el {@link Trabajo} sobre el que se aplica
     * @param fecha la fecha de realizacion de la modificacion
     * @param desc la descripcion de la modificacion
     * @param aprob si la modificacion esta o no aprobada por el cliente
     * @param fechaAprob la fecha de aprobacion por el cliente
     * @param operarioI el operario de impresion que crea la modificacion
     * @param trabajo el trabajo sobre el que se crea la modificacion
     */
    public Modificacion(Date fecha, String desc, boolean aprob, Date fechaAprob,Trabajo trabajo,OImpresion operarioI) {
        this.operarioI = operarioI;
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fechaAprob;
        this.trabajo = trabajo;
    }
    
    /**
     * Crea una instancia de Modificacion con los atributos propios de la clase y todos los atributos id de las relaciones
     * @param id el id de la modificacion
     * @param idTra el id del {@link Trabajo} sobre el que se crea
     * @param idCli el id del {@link Cliente} que pidio el trabajo
     * @param idOpe el id del {@link OImpresion} que crea la modificacion
     * @param fecha la fecha de creacion de la modificacion
     * @param desc la descripcion de la modificacion
     * @param aprob si la modificacion esta aprobada o no
     * @param fApro la fecha de aprobacion de la modificacion
     */
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

    //constructor de copia
    /**
     * Crea una instancia de Modificacion a partir de otra, copiando cada atributo
     * @param m el Modificacion que se va a copiar
     */
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

    //data, toString, getById y getAll
    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>idTrabajo</code>, <code>idCliente</code>, <code>idOImpresion</code>, <code>fecha</code>, <code>desc</code>, <code>aprob</code>, <code>fechaAprob</code>, separados por una barra vertical
     */
    public String data() {
        return this.getId() + "|" + this.getIdTrabajo() + "|" + this.getIdCliente() + "|" + this.getIdOImpresion() + "|" + this.getFecha() + "|" + this.getDesc() + "|" + this.isAprob() + "|" + this.getFechaAprob();
    }

    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Modificacion que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>operarioI</code>, <code>fecha</code>, <code>desc</code>, <code>aprob</code>, <code>fechaAprob</code>, <code>trabajo</code>
     */
    @Override
    public String toString() {
        return "Modificacion{" + "id=" + id + ", operario=" + operarioI + ", fecha=" + fecha + ", desc=" + desc + ", aprob=" + aprob + ", fechaAprob=" + fechaAprob + ", trabajo="+ trabajo.toString()+'}';
    }
    
    /**
     * Recorre el <code>ArrayList</code> de Modificaciones de {@link BDatos} y devuelve la modificacion con el id que se pasa como parametro
     * @param idModificacion el id de la modificacion que se quiere buscar en la base de datos
     * @return la <code>Modificacion</code> con el id coincidente con <code>idModificacion</code>, o nulo si no existe dicha modificacion
     */
    public Modificacion getModificacionById(long idModificacion) {
        for (Modificacion m : BDatos.modificaciones){
            if (m.getId()==idModificacion) return m;
        }
        return null;
        }
    
    /**
     * Devuelve todas las modificaciones registradas en el sistema
     * @return un <code>ArrayList</code> con todas las modificaciones de la base de datos
     */
    public ArrayList<Modificacion> getAllModificacion() {
        /*Este método devolverá un arrayList con todos los libros existentes*/
        ArrayList<Modificacion> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de modificaciones desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todas las modificaciones existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
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
    
    /**
     * Importa un grupo de modificaciones desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todas las modificaciones existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws EOFException al llegar al final del fichero
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
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
    
    /**
     * Exporta los datos de una <code>Modificacion</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Modificacion.data() data
     */
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
    
    /**
     * Exporta una <code>Modificacion</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */    
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
    /**
     * Crea una nueva instancia de la clase <code>Modificacion</code> pidiendo al usuario por pantalla que introduzca los datos
     * @return la <code>Modificacion</code> que se crea con el método
     */
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
