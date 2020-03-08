package imprenta;

import java.io.BufferedOutputStream;
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
 * Modela al cliente que se registra en el sistema, solicita trabajos y acepta modificaciones
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Cliente implements Serializable{

    private String nombre; //variable de tipo string que recoge el nombre del cliente
    private String telefono; //variable de tipo string que recoge el número de teléfono del cliente
    protected long id;//variable identificador

    //constructor por defecto
    /**
     * Crea una instancia de Cliente con los valores por defecto para los atributos
     */
    public Cliente() {
    }

    //constructores con argumentos
    /**
     * Crea una instancia de Cliente con los atributos propios de la clase, menos  el id
     * @param nombre el nombre del Cliente
     * @param telefono el telefono del cliente
     * @throws ClienteException si el telefono no tiene el formato correcto o el nombre esta vacio
     */
    public Cliente(String nombre, String telefono) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;
        this.telefono = telefono;}
    }
    
    /**
     * Crea una instancia de Cliente con los atributos propios de la clase
     * @param nombre el nombre del Cliente
     * @param telefono el telefono del cliente
     * @param id el id del cliente
     * @throws ClienteException si el telefono no tiene el formato correcto o el nombre esta vacio
     */
    public Cliente(String nombre, String telefono, long id) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;}
    }
    
    //constructor de copia
    /**
     * Crea una instancia de Cliente a partir de otra, copiando cada atributo
     * @param c el Cliente que se va a copiar
     * @throws ClienteException si el telefono no tiene el formato correcto o el nombre esta vacio
     */
    public Cliente(Cliente c) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = c.getNombre();
        this.telefono = c.getTelefono();}
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ClienteException{
        if (!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;}
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else {
        this.telefono = telefono;}
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //getAll, getById, data y toString
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de cliente que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>nombre</code>, <code>telefono</code>, <code>id</code>
     */
    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", telefono=" + telefono + ", id=" + id + '}';
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>idMaquina</code>, <code>idOperario</code>, <code>idCliente</code>, <code>fechaSolicitud</code>, <code>fechaRecogida</code>, <code>relieve</code>, separados por una barra vertical
     */
    public String data() {
        return this.getId() + "|" + this.getNombre() + "|" + this.getTelefono();
    }
    
    /**
     * Recorre el <code>ArrayList</code> de Clientes de {@link BDatos} y devuelve el cliente con el id que se pasa como parametro
     * @param idCliente el id del cliente que se quiere buscar en la base de datos
     * @return el <code>Cliente</code> con el id coincidente con <code>idCliente</code>, o nulo si no existe dicho cliente
     */
    public static Cliente getClienteById(long idCliente) {
        Cliente temp = null;
        for (Cliente c : BDatos.clientes){
            if (c.getId()==idCliente){
            temp = c;
            }
        }
        return temp;
    }
    
    /**
     * Devuelve todos los clientes registrados en el sistema
     * @return un <code>ArrayList</code> con todos los clientes de la base de datos
     */
    public ArrayList<Cliente> getAllCliente() {
        /*Este método devolverá un arrayList con todos los libros existentes*/
        return BDatos.clientes;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de clientes desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los clientes existentes en el fichero
     * @throws ClienteException si los datos leidos del fichero no permiten construir un <code>Cliente</code>
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Cliente> readClientefromTextFile (String path) {
        ArrayList<Cliente> ret = new ArrayList<>();
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
                    String nombre = campos[1];
                    String telefono = campos[2];
                    Cliente c = new Cliente(nombre, telefono, id);
                    ret.add(c);                   
                }
            }finally{
                if(buffer!=null)
                    buffer.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(ClienteException e){
            System.out.println("Se ha producido una ClienteException");
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
     * Importa un grupo de clientes desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los clientes existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws EOFException al llegar al final del fichero
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
    public static ArrayList<Cliente> readClientefromBinaryFile (String path) {
        ArrayList<Cliente> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Cliente c;
                while((c = (Cliente)lectorObjeto.readObject())!=null){
                    ret.add(c);
                    lector.skip(4);}
            }finally{
                if(lectorObjeto!=null)
                    lectorObjeto.close();
                if(lector!=null)
                    lector.close();
            }
        }
        catch(ClienteException e){
            System.out.println("Se ha producido una ClienteException");
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
     * Exporta los datos de un <code>Cliente</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Cliente.data() data
     */
    public void writeClienteToTextFile (String path){
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
     * Exporta un <code>Cliente</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeClienteToBinaryFile (String path) {
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
     * Crea una nueva instancia de la clase <code>Cliente</code> pidiendo al usuario por pantalla que introduzca los datos
     * @return el <code>Cliente</code> que se crea con el método
     * @throws ClienteException si los datos introducidos no son validos
     */
    public static Cliente nuevoCliente() {
        Cliente c = new Cliente();
        Scanner sc = new Scanner(System.in);
        boolean salir;
        do {
            System.out.println("Introduzca su nombre:");
            String nombre = sc.nextLine();
            c.setNombre(nombre);
            System.out.println("Introduzca su número de teléfono");
            String tlfn = sc.nextLine();
            c.setTelefono(tlfn);
            System.out.println("Son correctos los siguiente datos?(s/n)");
            salir = ToolBox.leerBoolean();
        } while (salir);
        return c;
    }
    
    /**
     * Crea una instancia de la clase {@link Trabajo} pidiendole al cliente que lo llama datos por pantalla, a traves de los metodos <code>encargo</code> de Trabajo y sus subclases. Relacionado con el CU Solicitar trabajo
     * @return el Trabajo instanciado
     * @throws TrabajoException si los datos introducidos no son validos
     * @see Trabajo.encargo(Cliente) Trabajo.encargo
     * @see Libro.encargo(Cliente) Libro.encargo
     * @see Rotulo.encargo(Cliente) Rotulo.encargo
     * @see Poster.encargo(Cliente) Poster.encargo
     */
    public Trabajo crearTrabajo() throws TrabajoException{
        Trabajo t;
        Scanner in = new Scanner(System.in);
        System.out.println("¿Qué tipo de trabajo desea solicitar? (poster=P, libro=L, rótulo=R)");
        char opcion = in.next().charAt(0);
        while(opcion!='p'&&opcion!='P'&&opcion!='r'&&opcion!='R'&&opcion!='l'&&opcion!='L'){
            System.out.println("Por favor, introduzca una opcion válida");
            opcion = in.next().charAt(0);
        }
        switch (opcion){
            case 'p':
            case 'P':
                t = Poster.encargo(this);
                break;
            case 'l':
            case 'L':
                t = Libro.encargo(this);
                break;
            case 'r':
            case 'R':
                t = Rotulo.encargo(this);
                break;
            default:
                t = Trabajo.encargo(this);
                break;
        }
        return t;
    }

    /**
     * Registra a un nuevo Cliente en el sistema
     * @return el Cliente registrado
     * @throws ClienteException si el telefono no tiene el formato correcto o el nombre esta vacio
     */
    public static Cliente registrarCliente() throws ClienteException{
        Cliente c = new Cliente();
        Scanner sc = new Scanner(System.in);
        boolean salir;
        do {
            System.out.println("Introduzca su nombre:");
            String nombre = sc.nextLine();
            c.setNombre(nombre);
            System.out.println("Introduzca su número de teléfono");
            String tlfn = sc.nextLine();
            c.setTelefono(tlfn);
            System.out.println("Son correctos los siguiente datos?(s/n)");
            salir = ToolBox.leerBoolean();
        } while (!salir);
        if (!ClienteException.comprobarTelefono(c.getTelefono())){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(c.getNombre())){
            throw new ClienteException("El nombre no es valido");
        } else {
        c.setId(BDatos.clientes.size()+1);
        return c;}
    }

    /**
     * Muestra al cliente los datos de la {@link Modificacion} y le permite aceptarla o rechazarla, seteando los atributos <code>aprob</code> y <code>fechaA</code> de Modificacion
     * @param m la Modificacion a aceptar
     * @return true si el cliente acepta la modificacion. false si no la acepta
     */
    public boolean aceptarModificacion(Modificacion m){

        System.out.println("Esta es su modificación"+ m.toString());
        System.out.println("Quiére aceptar la modificación?");
        boolean aceptar = ToolBox.leerBoolean();
        if(aceptar){
            m.setAprob(aceptar);
            System.out.println("Introduzca la fecha de aprobación:");
            Date fechaA = ToolBox.introducirFecha();
            m.setFechaAprob(fechaA);
            m.setId(this.id);
        }
        return aceptar;
    }
        
}
