/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Modela las labores que se realizan en las  {@link Maquina} por {@link OMaquinas}. 
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Labor {

    protected long id;//identificador

    private Date fechaini;// fecha de inicio de la labor
    private Date fechafin;// fecha de finalización de la labor
    private String tarea;//en que consiste
    private Maquina maquina;//maquina que lo realiza
    private ArrayList<OMaquinas> operariosM; // operarios que hacen la labor
    char estado;// estado de la labor
    private long idMaquina;

    /**
     * Crea una nueva instancia de la clase <code>Labor</code> pidiendo al usuario por pantalla que introduzca los datos
     * @return el <code>Trabajo</code> que se crea con el método
     */
    public static Labor nuevoLabor() throws LaborException {
        Labor lab = new Labor();

        Scanner in = new Scanner(System.in);
        boolean c;
        boolean d;
        do {

            System.out.println("Introduzca la tarea");
            String tarea = in.nextLine();
            try {
                if (!TrabajoException.comprobarRelieve(tarea)) {
                    throw new LaborException("La Tarea introducida no es valida.");
                }            lab.setTarea(tarea);
                        } catch (LaborException ex) {
                System.out.println("Error por tarea" + ex.getMessage());
            }
            System.out.println("Introduzca la fecha de inicio");
            Date fecha = ToolBox.introducirFecha();
            lab.setFechaini(fecha);
            System.out.println("Introduzca la fecha de realizacion");
            Date fechafin = ToolBox.introducirFecha();
            lab.setFechafin(fechafin);
            System.out.println("Introduzca los datos de la Máquina asignada");          
            Maquina maq = Maquina.nuevoMaquina();
                        try {
                if (!LaborException.comprobarMaquina(maq)) {
                    throw new LaborException("La maquina no puede estar vacía");

                }             lab.setMaquina(maq);
            } catch (LaborException ex) {
                System.out.println("Error por maquina" + ex.getMessage());
            }
            System.out.println("Quiere Introducir un nuevo Operario de Maquinas? s/n ");
            d = ToolBox.leerBoolean();
            char a = 0;
            lab.setEstado(a);

            while (d) {
                ArrayList<OMaquinas> ops = new ArrayList<OMaquinas>();
                lab.operariosM.add(OMaquinas.nuevoOMaquinas());
                lab.setOperariosM(ops);
                System.out.println("Quiere Introducir otro Operario? s/n ");
                d = ToolBox.leerBoolean();
            }

            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Tarea: " + tarea);
            System.out.println("Fecha de realización: " + fecha);

            c = ToolBox.leerBoolean();

        } while (!c);
        Maquina.noDisponible(lab.getMaquina());
        return lab;

    }

    // getters y setters
    public long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(long idMaquina) throws LaborException {
        if (!LaborException.comprobarIdMaquina(idMaquina)) {
            throw new LaborException("El idMaquina no es valido");
        } else {
            this.idMaquina = idMaquina;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) throws LaborException {
        if (!LaborException.comprobarFechaini(fechaini)) {
            throw new LaborException("La Fechaini no es valida");
        } else {
            this.fechaini = fechaini;
        }
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) throws LaborException {
        if (!LaborException.comprobarFechafin(fechafin)) {
            throw new LaborException("La Fechafin no es valida");
        } else {
            this.fechafin = fechafin;
        }
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) throws LaborException {
        if (!LaborException.comprobarTarea(tarea)) {
            throw new LaborException("La Tarea no es valida");
        } else {
            this.tarea = tarea;
        }
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) throws LaborException {
        if (!LaborException.comprobarMaquina(maquina)) {
            throw new LaborException("La Maquina no es valida");
        } else {
            this.maquina = maquina;
        }
    }

    public ArrayList<OMaquinas> getOperariosM() {
        return operariosM;
    }

    public void setOperariosM(ArrayList<OMaquinas> operariosM) throws LaborException {
        if (!LaborException.comprobarOperariosM(operariosM)) {
            throw new LaborException("El Array de operariosM no es valido");
        } else {
            this.operariosM = operariosM;
        }
    }

    //constructor por defecto
    /**
     * Crea una instancia de Labor con los valores por defecto para los atributos
     */    
    public Labor() {
    }

    //constructor con argumentos
    /**
     * Crea una instancia de Labor  con  los siguientes  atributos propios de la clase :
     * @param fechaini la fecha de inicio de la labor
     * @param tarea la descripcion de la tarea que se va a realizar
     * @param maquina la maquina sobre la que se realiza la labor
     * @throws LaborException  si alguno de los atributos no es valido
     */
    public Labor(Date fechaini, String tarea, Maquina maquina) throws LaborException {
        if (!LaborException.comprobarFechaini(fechaini)) {
            throw new LaborException("La Fechaini no es valida");
        } else if (!LaborException.comprobarMaquina(maquina)) {
            throw new LaborException("La Maquina no es valida");
        } else if (!LaborException.comprobarTarea(tarea)) {
            throw new LaborException("La Tarea no es valida");
        } else {
            this.fechaini = fechaini;
            this.tarea = tarea;
            this.maquina = maquina;
        }
    }

    //constructor con argumentos ampliado 
    /**
     * Crea una instancia de Labor  con  los  siguientes atributos propios de la clase :
     * @param fechaini la fecha de inicio de la labor
     * @param fechafin la fecha de fin de la labor
     * @param tarea la descripcion de la tarea que se va a realizar
     * @param maquina la maquina sobre la que se realiza la labor
     * @param operariosM los operarios que van a realizar la labor
     * @param estado el estado de la labor
     * @throws LaborException  si alguno de los atributos no es valido
     */
    public Labor(Date fechaini, Date fechafin, String tarea, Maquina maquina, ArrayList<OMaquinas> operariosM, char estado) throws LaborException {

        if (!LaborException.comprobarFechaini(fechaini)) {
            throw new LaborException("La Fechaini no es valida");
        } else if (!LaborException.comprobarMaquina(maquina)) {
            throw new LaborException("La Maquina no es valida");
        } else if (!LaborException.comprobarOperariosM(operariosM)) {
            throw new LaborException("El Array de operariosM no es valido");
        } else if (!LaborException.comprobarFechafin(fechafin)) {
            throw new LaborException("La Fechafin no es valida");
        } else if (!LaborException.comprobarTarea(tarea)) {
            throw new LaborException("La Tarea no es valida");
        } else {
            this.fechaini = fechaini;
            this.fechafin = fechafin;
            this.tarea = tarea;
            this.maquina = maquina;
            this.operariosM = operariosM;
            this.estado = estado;
        }
    }

    /**
     * Crea una instancia de Labor  con todos los atributos propios de la clase 
     * @param fechaini la fecha de inicio de la labor
     * @param fechafin la fecha de fin de la labor
     * @param tarea la descripcion de la tarea que se va a realizar
     * @param maquina la maquina sobre la que se realiza la labor
     * @param operariosM los operarios que van a realizar la labor
     * @param estado el estado de la labor
     * @param idMaquina el id de la maquina sobre la que se realiza la labor
     * @throws LaborException  si alguno de los atributos no es valido
     */
    public Labor(Date fechaini, Date fechafin, String tarea, Maquina maquina, ArrayList<OMaquinas> operariosM, char estado, long idMaquina) throws LaborException {
        if (!LaborException.comprobarMaquina(maquina)) {
            throw new LaborException("La Maquina no es valida");
        } else if (!LaborException.comprobarFechaini(fechaini)) {
            throw new LaborException("La Fechaini no es valida");
        } else if (!LaborException.comprobarOperariosM(operariosM)) {
            throw new LaborException("El Array de operariosM no es valido");
        } else if (!LaborException.comprobarFechafin(fechafin)) {
            throw new LaborException("La Fechafin no es valida");
        } else if (!LaborException.comprobarIdMaquina(idMaquina)) {
            throw new LaborException("El idMaquina no es valido");
        } else if (!LaborException.comprobarTarea(tarea)) {
            throw new LaborException("La Tarea no es valida");
        } else {
            this.fechaini = fechaini;
            this.fechafin = fechafin;
            this.tarea = tarea;
            this.maquina = maquina;
            this.operariosM = operariosM;
            this.estado = estado;
            this.idMaquina = idMaquina;
        }
    }

    //constructor de copia
        /**
     * Crea una instancia de Labor a partir de otra, copiando cada atributo
     * @param l la Labor que se va a copiar
     * @throws LaborException si alguno de los atributos no es valido
     */
    public Labor(Labor l) throws LaborException {

        if (!LaborException.comprobarMaquina(maquina)) {
            throw new LaborException("La Maquina no es valida");
        } else if (!LaborException.comprobarFechaini(fechaini)) {
            throw new LaborException("La Fechaini no es valida");
        } else if (!LaborException.comprobarTarea(tarea)) {
            throw new LaborException("La Tarea no es valida");
        } else {
            this.fechaini = l.getFechaini();
            this.tarea = l.getTarea();
            this.maquina = l.getMaquina();
        }
    }

    // El orden de los campos es el siguiente: id- fechaini-fechafin-tarea-maquina-operariosM-idMaquina-estado-maquina;
    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>fechaini</code>, <code>fechafin</code>, <code>tarea</code>, <code>maquina</code>, <code>operariosM</code>, <code>idMaquina</code>,<code>estado</code>, <code>maquina</code>, separados por una barra vertical
     */
    public String data() {
        return id + "|" + fechaini + "|" + fechafin + "|" + tarea + "|" + maquina + "|" + operariosM + "|" + idMaquina + "|" + estado + "|" + maquina ;

    }
    
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de labor que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>fechaini</code>, <code>fechafin</code>, <code>tarea</code>, <code>maquina</code>, <code>operariosM</code>, <code>idMaquina</code>,<code>estado</code>, <code>maquina</code>, separados por una barra vertical
     */
    @Override
    public String toString() {
        return "Labor{" + "id=" + id + ", fecha ini=" + fechaini + ", fecha fin =" + fechafin + ", tarea=" + tarea + ", maquina=" + maquina + '}';
    }

   

    //lectura y escritura
    /**
     * Importa un grupo de labores desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos las labores existentes en el fichero
     * @throws LaborException si los datos leidos del fichero no permiten construir un <code>Labor</code>
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Labor> readLaborFromTextFile(String path) {
        ArrayList<Labor> lab = new ArrayList<>();
        File fichero = new File(path);
        FileReader lector = null;
        BufferedReader buffer = null;
        try {
            try {
                lector = new FileReader(fichero);
                buffer = new BufferedReader(lector);
                String linea;
                while ((linea = buffer.readLine()) != null) {
                    String[] campos = linea.split("\\|");
                    long id = Long.parseLong(campos[0]);
                    String nombre = campos[1];
                    String telefono = campos[2];
                    Date fechafin;
                    Date fechaini;
                    String tarea;
                    Maquina maquina;
                    ArrayList<OMaquinas> operariosM;
                    char estado;
                    long idMaquina;
               //     Labor l = new Labor(fechafin, fechaini, idMaquina, estado, operariosM,tarea, maquina);
      

   // lab.add (l);
}
}finally{
                if(buffer!=null)
                    buffer.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(LaborException l){
            System.out.println("Se ha producido una LaborException");
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
        return lab;
    }
 
    /**
     * Importa un grupo de labores desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos las labores existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
    public static ArrayList<Labor> readLaborFromBinaryFile (String path) {
        ArrayList<Labor> lab = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Labor l;
                while((l = (Labor)lectorObjeto.readObject())!=null)
                    lab.add(l);
            }finally{
                if(lectorObjeto!=null)
                    lectorObjeto.close();
                if(lector!=null)
                    lector.close();
            }
        }

        catch(FileNotFoundException l){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException l){
            System.out.println("Se ha producido una IOException");
        }
        catch(ClassNotFoundException l){
            System.out.println("Se ha producido una ClassNotFoundException");
        }
        catch(Exception l){
            System.out.println("Se ha producido una Exception");
        }
        return lab;
    }
    
        /**
     * Exporta los datos de una <code>Labor</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Labor.data() data
     */
    public void writeLaborToTextFile (String path){
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
        catch(FileNotFoundException l){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException l){
            System.out.println("Se ha producido una IOException");
        }
        catch(Exception l){
            System.out.println("Se ha producido una Exception");
        }
    }
    
    /**
     * Exporta una <code>Labor</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */   
    public void writeLaborToBinaryFile (String path) {
        FileOutputStream escritor = null;
        ObjectOutputStream escritorObjeto = null;
        try{
            try{
                escritor = new FileOutputStream(path);
                escritorObjeto = new ObjectOutputStream(escritor);
                escritorObjeto.writeObject(this);
            }finally{                
                if(escritorObjeto!=null)
                    escritorObjeto.close();
                if(escritor!=null)
                    escritor.close();
            }
        }
        catch(FileNotFoundException l){
            System.out.println("Se ha producido una FileNotFoundException");
        }
        catch(IOException l){
            System.out.println("Se ha producido una IOException");
        }
        catch(Exception l){
            System.out.println("Se ha producido una Exception");
        }
    }
    
    /**
     * Recorre el <code>ArrayList</code> de Labores de {@link BDatos} y devuelve la labor  con el id que se pasa como parametro
     * @param idLabor el id de la labor que se quiere buscar en la base de datos
     * @return el <code>Labor</code> con el id coincidente con <code>idLabor</code>, o nulo si no existe dicha labor
     */   
    public static Labor getLaborById(long idLabor) {

        Labor temp = null;
        for (Labor l : BDatos.labores) {
            if (l.getId() == idLabor) {
                temp = l;
            }
        }
        return temp;
    }
}
