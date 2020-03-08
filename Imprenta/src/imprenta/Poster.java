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
public class Poster extends Trabajo implements Serializable {

    private double alto;//variable tipo integer que recoge el alton del poster
    private double ancho;//variable tipo integer que recoge el ancho del poster
    private int numCopias;//variable tipo integer que recoge el número de copias de los posters solicitados

    //constructor por defecto
    /**
     * Crea una instancia de Poster con los valores por defecto para los atributos
     */
    public Poster() {
    }

    //constructor por argumentos
    /**
     * Crea una instancia de Poster con los atributos propios de la clase y los de la superclase {@link Trabajo}, sin los de de las relaciones ni el id
     * @param alto el alto del poster en cm
     * @param ancho el ancho del poster en cm
     * @param numCopias el numero de copias del poster
     * @param fechaSolicitud la fecha de solicitud del trabajo
     * @param fechaRecogida la fecha de recogida del trabajo
     * @param relieve el relieve del trabajo
     */
    public Poster(double alto, double ancho, int numCopias, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }
    
    /**
     * Crea una instancia de Poster con los atributos propios de la clase, sin el id
     * @param id el id del poster
     * @param alto el alto del poster en cm
     * @param ancho el ancho del poster en cm
     * @param numCopias el numero de copias del poster
     */
    public Poster(long id, double alto, double ancho, int numCopias){
        this.id = id;
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }

    //constructor de copia
    /**
     * Crea una instancia de Poster a partir de otra, copiando cada atributo
     * @param p el Poster que se va a copiar
     */
    public Poster(Poster p) {
        this.alto = p.getAlto();
        this.ancho = p.getAncho();
        this.numCopias = p.getNumCopias();
    }

    //constructor desde clase padre
    /**
     * Crea una instancia de Poster a partir de otra de la superclase {@link Trabajo}
     * @param t el Trabajo a partir del cual se va a generar el Poster
     * @throws TrabajoException si los datos del trabajo introducido no son validos
     */
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
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Poster que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>alto</code>, <code>ancho</code>, <code>numCopias</code>
     */
    @Override
    public String toString() {
        return "Poster{" + "alto=" + alto + ", ancho=" + ancho + ", numCopias=" + numCopias + '}';
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>id</code>, <code>alto</code>, <code>ancho</code>, <code>numCopias</code>
     */
    @Override
    public String data() {
        return this.getId() + "|" + this.getAlto() + "|" + this.getAncho() + "|" + this.getNumCopias();
    }
    
    /**
     * Recorre el <code>ArrayList</code> de posters de {@link BDatos} y devuelve el poster con el id que se pasa como parametro
     * @param idPoster el id del poster que se quiere buscar en la base de datos
     * @return el <code>Poster</code> con el id coincidente con <code>idPoster</code>, o nulo si no existe dicho poster
     * @throws PosterException si el id introducido no es valido
     */
    public Poster getPosterById(long id) {
        /*Este método recorrerá un ArrayList con todos los posters buscando aquel con el id que le introduzcamos, y devolverá ese poster si es que existe o 
        nulo si es que no existe*/
        Poster l = new Poster();
        return l;
    }

    /**
     * Devuelve todos los posters registrados en el sistema
     * @return un <code>ArrayList</code> con todos los posters de la base de datos
     */
    public ArrayList<Poster> getAllPoster() {
        /*Este método devolverá un arrayList con todos los posters existentes*/
        ArrayList<Poster> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
    /**
     * Importa un grupo de posters desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los posters existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
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
    
    /**
     * Importa un grupo de posters desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los posters existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws EOFException al llegar al final del fichero
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
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
    
    /**
     * Exporta los datos de un <code>Poster</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Poster.data() data
     */
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
    
    /**
     * Exporta un <code>Poster</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
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
    /**
     * Crea una nueva instancia de la clase <code>Poster</code> pidiendo al usuario por pantalla que introduzca los datos, y llamando al metodo nuevoTrabajo de {@link Trabajo} para completar los datos de la superclase
     * @return el <code>Poster</code> que se crea con el método
     * @see Trabajo.nuevoTrabajo() Trabajo.nuevoTrabajo
     */
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
    
    /**
     * Crea un <code>Poster</code> nuevo pidiendo datos por pantalla, y llamando al metodo encargo de {@link Trabajo} para completar los datos de la superclase. Relacionado con el caso de uso Solicitar trabajo
     * @param c el <code>Cliente</code> que solicita el trabajo
     * @return el <code>Poster</code> que se crea con el método
     * @see Trabajo.encargo(Cliente) Trabajo.encargo
     * @see Cliente.crearTrabajo() Cliente.crearTrabajo
     */
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
