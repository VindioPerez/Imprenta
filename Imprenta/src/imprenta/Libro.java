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
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Modela el libro, una de las subclases de {@link Trabajo}
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Libro extends Trabajo implements Serializable {

    private int numPag;//variable tipo int que recoge el número de páginas que tiene el libro;
    private String color; //variable tipo String que recoge el color del libro

    //constructor por defecto
    /**
     * Crea una instancia de Libro con los valores por defecto para los atributos
     */
    public Libro() {
    }

    //constructores por argumentos
    /**
     * Crea una instancia de Libro con los atributos propios de la clase y los de la superclase {@link Trabajo}, sin los de de las relaciones ni el id
     * @param numPag el numero de paginas del libro
     * @param color el color del libro
     * @param fechaSolicitud la fecha de solicitud, heredada de <code>Trabajo</code>
     * @param fechaRecogida la fecha de recogida, heredada de <code>Trabajo</code>
     * @param relieve el relieve, heredado de <code>Trabajo</code>
     * @throws TrabajoException si los datos introducidos no son correctos
     */
    public Libro(int numPag, String color, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.numPag = numPag;
        this.color = color;
    }
    
    /**
     * Crea una instancia de Libro con los atributos propios de la clase, sin el id
     * @param id el id del libro
     * @param numPag el numero de paginas del libro
     * @param color el color del libro
     */
    public Libro(long id, int numPag, String color){
        this.id = id;
        this.numPag = numPag;
        this.color = color;
    }

    //constructor de copia
    /**
     * Crea una instancia de Trabajo a partir de otra, copiando cada atributo
     * @param l el Libro que se va a copiar
     */
    public Libro(Libro l) {
        this.color = l.getColor();
        this.numPag = l.getNumPag();
    }

    //constructor desde clase padre
    /**
     * Crea una instancia de Libro a partir de otra de la superclase {@link Trabajo}
     * @param t el Trabajo a partir del cual se va a generar el Libro
     * @throws TrabajoException TrabajoException si los datos del trabajo introducido no son validos
     */
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
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Libro que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>numPag</code>, <code>color</code>
     */
    @Override
    public String toString() {
        return "Libro{" + "numPag=" + numPag + ", color=" + color + '}';
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>numPag</code>, <code>color</code>
     */
    @Override
    public String data() {
        return this.getId() + "|" + this.getNumPag() + "|" + this.getColor();
    }
    
    /**
     * Recorre el <code>ArrayList</code> de Libros de {@link BDatos} y devuelve el libro con el id que se pasa como parametro
     * @param idLibro el id del libro que se quiere buscar en la base de datos
     * @return el <code>Libro</code> con el id coincidente con <code>idLibro</code>, o nulo si no existe dicho libro
     * @throws LibroException si el id introducido no es valido
     */
    public Libro getLibroById(long id) {
        /*Este método recorrerá un ArrayList con todos los libros buscando aquel con el id que le introduzcamos, y devolverá ese libro si es que existe o 
        nulo si es que no existe*/
        Libro l = new Libro();
        return l;
    }

    /**
     * Devuelve todos los libros registrados en el sistema
     * @return un <code>ArrayList</code> con todos los libros de la base de datos
     */
    public ArrayList<Libro> getAllLibro() {
        /*Este método devolverá un arrayList con todos los libros existentes*/
        ArrayList<Libro> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de libros desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los libros existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Libro> readLibroFromTextFile (String path) {
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
     * Importa un grupo de libros desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los libros existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws EOFException al llegar al final del fichero
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
    public static ArrayList<Libro> readLibroFromBinaryFile (String path) {
        ArrayList<Libro> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try{
            try{
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Libro c;
                while((c = (Libro)lectorObjeto.readObject())!=null){
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
     * Exporta los datos de un <code>Libro</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Libro.data() data
     */
    public void writeLibroToTextFile (String path){
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
     * Exporta un <code>Libro</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeLibroToBinaryFile (String path) {
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
     * Crea una nueva instancia de la clase <code>Libro</code> pidiendo al usuario por pantalla que introduzca los datos, y llamando al metodo nuevoTrabajo de {@link Trabajo} para completar los datos de la superclase
     * @return el <code>Libro</code> que se crea con el método
     * @see Trabajo.nuevoTrabajo() Trabajo.nuevoTrabajo
     */
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

    /**
     * Crea un <code>Libro</code> nuevo pidiendo datos por pantalla, y llamando al metodo encargo de {@link Trabajo} para completar los datos de la superclase. Relacionado con el caso de uso Solicitar trabajo
     * @param c el <code>Cliente</code> que solicita el trabajo
     * @return el <code>Libro</code> que se crea con el método
     * @see Trabajo.encargo(Cliente) Trabajo.encargo
     * @see Cliente.crearTrabajo() Cliente.crearTrabajo
     */
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
