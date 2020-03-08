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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Modela el trabajo que solicita un {@link Cliente}. Tiene 3 clases hijas, {@link Libro}, {@link Rotulo} y {@link Poster}
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Trabajo implements Serializable {

    protected long id; //variable tipo integer con el identificador del trabajo
    protected Date fechaSolicitud;//variable tipo Date donde se recoge la enfecha de entre del trabajo
    protected Date fechaRecogida;//variable tipo Date donde se recoge la enfecha de recogida del trabajo
    protected String relieve; //variable tipo String que indica el tipo de relieve
    private Cliente cliente;//Instancia del Cliente que solicita el trabajo
    protected long idMaquina;//variable tipo long con el id de la máquina que realiza el trabajo
    protected long idOperario;//variable tipo long con el id del operario que se encarga del trabajo
    protected long idCliente;//variable tipo long con el id del cliente que encarga del trabajo

    //constructor por defecto
    /**
     * Crea una instancia de Trabajo con los valores por defecto para los atributos
     */
    public Trabajo() {
    }

    //constructor por argumentos
    /**
     * Crea una instancia de Trabajo con los atributos propios de la clase, sin los de de las relaciones ni el id
     * @param fechaSolicitud la fecha de solicitud que se le va a setear al trabajo
     * @param fechaRecogida la fecha de recogida que se le va a setear al trabajo
     * @param relieve el relieve en el que se imprimira el trabajo
     * @throws TrabajoException si la fecha de solicitud es posterior a la de recogida
     */
    public Trabajo(Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {

        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if(!TrabajoException.comprobarFechaSol(fechaSolicitud, fechaRecogida)){
            throw new TrabajoException("La fecha de solicitud no puede ser posterior a la de recogida.");
        }
        else{
        this.fechaSolicitud = fechaSolicitud;
        this.fechaRecogida = fechaRecogida;
        this.relieve = relieve;
        }
    }
    
    /**
     * Crea una instancia de Trabajo con todos los atributos
     * @param id el id del trabajo
     * @param idMaq el id de la {@link Maquina} que imprime el trabajo
     * @param idOpe el id del {@link Operario} que registra el trabajo
     * @param idCli el id del {@link Cliente} que solicita el trabajo
     * @param fechaSolicitud la fecha de solicitud que se le va a setear al trabajo
     * @param fechaRecogida la fecha de recogida que se le va a setear al trabajo
     * @param relieve el relieve en el que se imprimira el trabajo
     * @throws TrabajoException si la fecha de solicitud es posterior a la de recogida
     */
    public Trabajo(long id, long idMaq, long idOpe, long idCli, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException{
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if(!TrabajoException.comprobarFechaSol(fechaSolicitud, fechaRecogida)){
            throw new TrabajoException("La fecha de solicitud no puede ser posterios a la de recogida.");
        }else{
            this.id = id;
            this.idMaquina = idMaq;
            this.idOperario = idOpe;
            this.idCliente = idCli;
            this.fechaSolicitud = fechaSolicitud;
            this.fechaRecogida = fechaRecogida;
            this.relieve = relieve;
        }
    }
    
    //constructor de copia
    /**
     * Crea una instancia de Trabajo a partir de otra, copiando cada atributo
     * @param t el Trabajo que se va a copiar
     * @throws TrabajoException si la fecha de solicitud es posterior a la de recogida
     */
    public Trabajo(Trabajo t) throws TrabajoException {
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if (!TrabajoException.comprobarId(id)) {
            throw new TrabajoException("El id no es valido");
        }else if(!TrabajoException.comprobarFechaSol(fechaSolicitud, fechaRecogida)){
            throw new TrabajoException("La fecha de solicitud no es válida.");
        }else{
        this.id = t.getId();
        this.fechaRecogida = t.fechaRecogida;
        this.fechaSolicitud = t.fechaSolicitud;
        this.relieve = t.relieve;
        }
    }
   
    //getters y setters
    public String getRelieve() {
        return relieve;
    }

    public void setRelieve(String relieve) throws TrabajoException {
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        this.relieve = relieve;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(Date fechaRecogida) {

        this.fechaRecogida = fechaRecogida;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) throws TrabajoException {
        if (!TrabajoException.comprobarId(id)) {
            throw new TrabajoException("El id no es valido");
        }
        this.id = id;
    }

    public long getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(long idOperario) throws TrabajoException {
        if (!TrabajoException.comprobarId(idOperario)) {
            throw new TrabajoException("El id no es valido");
        }
        this.idOperario = idOperario;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }
    
    public long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(long idMaquina) throws TrabajoException {
        if (!TrabajoException.comprobarId(idMaquina)) {
            throw new TrabajoException("El id no es valido");
        }

        this.idMaquina = idMaquina;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) throws TrabajoException {
        if (!TrabajoException.comprobarClienteVacion(cliente)) {
            throw new TrabajoException("El cliente no puede estar vacío");
        }

        this.cliente = cliente;
    }
    
    //getAll, getById, data y toString
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de trabajo que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>fechaSolicitud</code>, <code>fechaRecogida</code>, <code>relieve</code>
     */
    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", fechaSolicitud=" + fechaSolicitud + ", fechaRecogida=" + fechaRecogida + ", relieve=" + relieve + '}';
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>idMaquina</code>, <code>idOperario</code>, <code>idCliente</code>, <code>fechaSolicitud</code>, <code>fechaRecogida</code>, <code>relieve</code>, separados por una barra vertical
     */
    public String data() {
        return this.getId() + "|" + this.getIdMaquina() + "|" + this.getIdOperario() + "|" + this.getIdCliente() + "|" + this.getFechaSolicitud() + "|" + this.getFechaRecogida() + "|" + this.getRelieve();
    }

    /**
     * Recorre el <code>ArrayList</code> de Trabajos de {@link BDatos} y devuelve el trabajo con el id que se pasa como parametro
     * @param idTrabajo el id del trabajo que se quiere buscar en la base de datos
     * @return el <code>Trabajo</code> con el id coincidente con <code>idTrabajo</code>, o nulo si no existe dicho trabajo
     */
    public static Trabajo getTrabajoById(long idTrabajo) {
        for (Trabajo t : BDatos.trabajos) {
            if (t.getId() == idTrabajo) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * Devuelve todos los trabajos registrados en el sistema
     * @return un <code>ArrayList</code> con todos los trabajos de la base de datos
     */
    public ArrayList<Trabajo> getAllTrabajo() {
        /*Este método devolverá un arrayList con todos los rotulos existentes*/
        return BDatos.trabajos;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de trabajos desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los trabajos existentes en el fichero
     * @throws TrabajoException si los datos leidos del fichero no permiten construir un <code>Trabajo</code>
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Trabajo> readTrabajoFromTextFile (String path) {
        ArrayList<Trabajo> ret = new ArrayList<>();
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
                    long idMaq = Long.parseLong(campos[1]);
                    long idOpe = Long.parseLong(campos[2]);
                    long idCli = Long.parseLong(campos[3]);
                    Date fSol = df.parse(campos[4]);
                    Date fRec = df.parse(campos[5]);
                    String relieve = campos[6];
                    Trabajo c = new Trabajo(id, idMaq, idOpe, idCli, fSol, fRec, relieve);
                    ret.add(c);                   
                }
            }finally{
                if(buffer!=null)
                    buffer.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(TrabajoException e){
            System.out.println("Se ha producido una TrabajoException");
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
     * Importa un grupo de trabajos desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los trabajos existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws EOFException al llegar al final del fichero
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
    public static ArrayList<Trabajo> readTrabajoFromBinaryFile (String path) {
        ArrayList<Trabajo> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Trabajo c;
                while((c = (Trabajo)lectorObjeto.readObject())!=null){
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
     * Exporta los datos de un <code>Trabajo</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Trabajo.data() data
     */
    public void writeTrabajoToTextFile (String path){
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
     * Exporta un <code>Trabajo</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeTrabajoToBinaryFile (String path) {
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
     * Crea una nueva instancia de la clase <code>Trabajo</code> pidiendo al usuario por pantalla que introduzca los datos
     * @return el <code>Trabajo</code> que se crea con el método
     * @throws TrabajoException si los datos introducidos no son validos
     */
    public static Trabajo nuevoTrabajo() throws TrabajoException {
        Trabajo t = new Trabajo();
        Scanner in = new Scanner(System.in);
        boolean salir;
        do {

            System.out.println("Introduzca la fecha de solicitud del trabajo");
            Date fechaSol = ToolBox.introducirFecha();
            System.out.println("Introduzca la fecha de recogida");
            Date fechaRec = ToolBox.introducirFecha();
            try {
                if (!TrabajoException.comprobarFechaRec(fechaRec, fechaSol)) {
                    throw new TrabajoException("La fecha de Recogida es anterior a la de solicitud");
                }
            } catch (TrabajoException ex) {
                System.out.println("Esto es un error por fechas" + ex.getMessage());

            }

            System.out.println("Introduzca el relieve en el que desea su trabajo");
            String relieve = in.nextLine();
            try {
                if (!TrabajoException.comprobarRelieve(relieve)) {
                    throw new TrabajoException("El relieve introducido no es valido.");
                }
                t.setRelieve(relieve);
            } catch (TrabajoException ex) {
                System.out.println("Error por relieve" + ex.getMessage());
            }

            System.out.println("Introduzca un cliente");
            Cliente cli = Cliente.nuevoCliente();
            try {
                if (!TrabajoException.comprobarClienteVacion(cli)) {
                    throw new TrabajoException("El cliente no puede estar vacío");

                }
            } catch (TrabajoException ex) {
                System.out.println("Error por cliente" + ex.getMessage());
            }

            System.out.println("Introduzca el id de la maquina:");

            long idmaq = Maquina.nuevoMaquina().getId();
            try {
                if (!TrabajoException.comprobarId(idmaq)) {
                    throw new TrabajoException("El id introducido no es válido.");
                }
            } catch (TrabajoException ex) {
                System.out.println("Error por id" + ex.getMessage());
            }
            System.out.println("Introduzca el id del operario");

            long idope = OImpresion.nuevoOImpresion().getId();
            try {
                if (!TrabajoException.comprobarId(idope)) {
                    throw new TrabajoException("El id introducido no es válido");
                }
            } catch (TrabajoException ex) {
                System.out.println("Error por id" + ex);
            }

            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Solicitud " + fechaSol);
            System.out.println("Fecha Recogida: " + fechaRec);
            System.out.println("Cliente: " + cli.getNombre());
            System.out.println("id maquina: " + idmaq);
            System.out.println("id operario: " + idope);
            salir = ToolBox.leerBoolean();
        } while (!salir);
        return t;
    }

    /**
     * Crea un <code>Trabajo</code> nuevo pidiendo datos por pantalla. Relacionado con el caso de uso Solicitar trabajo
     * @param c el <code>Cliente</code> que solicita el trabajo
     * @return el <code>Trabajo</code> que se crea con el método
     * @throws TrabajoException si los datos introducidos no son validos
     * @see Libro.encargo(Cliente) Libro.encargo
     * @see Rotulo.encargo(Cliente) Rotulo.encargo
     * @see Poster.encargo(Cliente) Poster.encargo
     * @see Cliente.crearTrabajo() Cliente.crearTrabajo
     */
    public static Trabajo encargo(Cliente c) throws TrabajoException {
        Trabajo t = new Trabajo();
        Scanner in = new Scanner(System.in);
        System.out.println("Introduzca la fecha de solicitud");
        Date fechaSol = ToolBox.introducirFecha();
        System.out.println("Introduzca la fecha de recogida");
        Date fechaRec = ToolBox.introducirFecha();
        if(!TrabajoException.comprobarFechaSol(fechaSol, fechaRec)){
            throw new TrabajoException("La fecha de solicitud no puede ser posterios a la de recogida.");
        }else{
        t.setFechaSolicitud(fechaSol);
        }
        if(!TrabajoException.comprobarFechaRec(fechaRec, fechaSol)){
            throw new TrabajoException("La fecha de recogida no puede ser anterior a la de solicitud.");
        }else{
        t.setFechaRecogida(fechaRec);
        }
        System.out.println("Introduzca el relieve en el que desea su trabajo");
        String relieve = in.nextLine();
        if(!TrabajoException.comprobarRelieve(relieve)){
            throw new TrabajoException("El releive no es corrcto");
        }else{
        t.setRelieve(relieve);
        }if(!TrabajoException.comprobarClienteVacion(c)){
            throw new TrabajoException("El cliente no puede estar vacío.");
        }else{
        t.setCliente(c);
        }
        System.out.println("¿Son correctos estos datos? (s/n)");
        System.out.println("Fecha Solicitud: " + fechaSol);
        System.out.println("Fecha Recogida: " + fechaRec);
        System.out.println("Relieve: " + relieve);
        
        return t;
    }

    /**
     * Crea un <code>Trabajo</code> nuevo pidiendo datos por pantalla, requiriendo tanto un operario como un cliente. 
     * Creado para contemplar la posibilidad de ejecutar el programa en una tienda fisica en la que esten presentes tanto el operario como el cliente
     * @param c el <code>Cliente</code> que solicita el trabajo
     * @param o el <code>Operario</code> que registra el trabajo
     * @return el <code>Trabajo</code> que se crea con el método
     * @throws TrabajoException si los datos introducidos no son validos
     * @see Cliente.crearTrabajo() Cliente.crearTrabajo
     * @see Operario.confirmar(Trabajo) Operario.confirmar
     */
    public static Trabajo solicitarTrabajo(Cliente c, Operario o) throws TrabajoException {
        Trabajo t = c.crearTrabajo();
        o.confirmar(t);
        return t;
    }

    /**
     * Modifica los datos de un <code>Trabajo</code> de acuerdo a una <code>Modificacion</code>
     * @param m la <code>Modificacion</code> de la que se extraen los nuevos datos
     * @throws TrabajoException si los datos nuevos del trabajo no son correctos
     */
    protected static void modificarTrabajo(Modificacion m) throws TrabajoException {

        Trabajo t = m.getTrabajo();

        Scanner sc = new Scanner(System.in);
        System.out.println("Usted ha solicitado una modificación:");
        System.out.println(m.getDesc());
        if (t instanceof Libro) {
            System.out.println("Introduzca la nueva cantidad de páginas que quieres:");
            int pag = sc.nextInt();
            if (pag < 1) {
                throw new TrabajoException("El número de páginas no puede ser 0");
            }
            ((Libro) t).setNumPag(pag);

            System.out.println("Introduzca un nuevo color para cambiar:");
            String color = sc.nextLine();
            if (color.isEmpty()) {
                throw new TrabajoException("El color no puede ser vacío");

            }
            ((Libro) t).setColor(color);
            System.out.println("La modificación ha quedado así:");
            ((Libro) t).toString();

        }
        if (t instanceof Poster) {
            System.out.println("Introduzca el nuevo ancho del Poster:");
            double ancho = sc.nextDouble();
            if (ancho <= 0.0) {
                throw new TrabajoException("El ancho no puede ser negativo");
            }
            ((Poster) t).setAncho(ancho);
            System.out.println("Introduzca el nuevo alto del Poster");

            double alto = sc.nextDouble();
            if (alto <= 0.0) {
                throw new TrabajoException("El alto no puede ser negativo");
            }
            ((Poster) t).setAlto(alto);
            System.out.println("Introduzca el nuevo número de copias:");
            int copia = sc.nextInt();
            if (copia < 1) {
                throw new TrabajoException("El número de copias no puede ser 0");
            }
            ((Poster) t).setNumCopias(copia);
            System.out.println("Su trabajo ha quedado asi:");
            ((Poster) t).toString();

        }
        if (t instanceof Rotulo) {
            System.out.println("Introduzca la nueva dirección donde enviar el rótulo:");
            String direccion = sc.nextLine();
            if (direccion.isEmpty()) {
                throw new TrabajoException("La dirección no puede estar vacia");
            }
            ((Rotulo) t).setCentroComercial(direccion);
            System.out.println("Su trabajo ha quedado asi:");
            ((Rotulo) t).toString();

        }

    }

    

}
