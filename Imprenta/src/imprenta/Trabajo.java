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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ander
 * @version 1.1
 */
public class Trabajo {

    protected long id; //variable tipo integer con el identificador del trabajo
    protected Date fechaSolicitud;//variable tipo Date donde se recoge la enfecha de entre del trabajo
    protected Date fechaRecogida;//variable tipo Date donde se recoge la enfecha de recogida del trabajo
    protected String relieve; //variable tipo String que indica el tipo de relieve
    private Cliente cliente;//Instancia del Cliente que solicita el trabajo
    protected long idMaquina;//variable tipo long con el id de la máquina que realiza el trabajo
    protected long idOperario;//variable tipo long con el id del operario que se encarga del trabajo
    protected long idCliente;//variable tipo long con el id del cliente que encarga del trabajo

    //constructor por defecto
    public Trabajo() {
    }

    //constructor por argumentos
    public Trabajo(Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {

        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if(!TrabajoException.comprobarFechaSol(fechaSolicitud, fechaRecogida)){
            throw new TrabajoException("La fecha de solicitud no puede ser posterios a la de recogida.");
        }
        else if(!TrabajoException.comprobarFechaRec(fechaRecogida, fechaSolicitud)){
            throw new TrabajoException("La fecha de recogida no puede ser anterior a la de solicitud.");
        }else{
        this.fechaSolicitud = fechaSolicitud;
        this.fechaRecogida = fechaRecogida;
        this.relieve = relieve;
        }
    }
    public Trabajo(long id, long idMaq, long idOpe, long idCli, Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException{
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if(!TrabajoException.comprobarFechaSol(fechaSolicitud, fechaRecogida)){
            throw new TrabajoException("La fecha de solicitud no puede ser posterios a la de recogida.");
        }
        else if(!TrabajoException.comprobarFechaRec(fechaRecogida, fechaSolicitud)){
            throw new TrabajoException("La fecha de recogida no puede ser anterior a la de solicitud.");
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
    public Trabajo(Trabajo t) throws TrabajoException {
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        else if (!TrabajoException.comprobarId(id)) {
            throw new TrabajoException("El id no es valido");
        }else if(!TrabajoException.comprobarFechaRec(fechaRecogida, fechaSolicitud)){
            throw new TrabajoException("La fecha de recogida no es válida.");
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
    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", fechaSolicitud=" + fechaSolicitud + ", fechaRecogida=" + fechaRecogida + '}';
    }

    public String data() {
        return this.getId() + "|" + this.getIdMaquina() + "|" + this.getIdOperario() + "|" + this.getIdCliente() + "|" + this.getFechaSolicitud() + "|" + this.getFechaRecogida() + "|" + this.getRelieve();
    }

    public static Trabajo getTrabajoById(long idTrabajo) throws TrabajoException {
        if (!TrabajoException.comprobarId(idTrabajo)) {
            throw new TrabajoException("El id no es valido");
        }

        for (Trabajo t : BDatos.trabajos) {
            if (t.getId() == idTrabajo) {
                return t;
            }
        }
        return null;
    }
    
    public ArrayList<Trabajo> getAllTrabajo() {
        /*Este método devolverá un arrayList con todos los rotulos existentes*/
        ArrayList<Trabajo> o = new ArrayList<>();
        return o;
    }
    
    //lectura y escritura
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

    public static Trabajo solicitarTrabajo(Cliente c, Operario o) throws TrabajoException {
        Trabajo t = c.crearTrabajo();
        o.confirmar(t);
        return t;
    }

    protected static void modificarTrabajo(Modificacion m) throws TrabajoException {

        Trabajo t = m.getTrabajo();

        Scanner sc = new Scanner(System.in);
        System.out.println("Usted ha solicitado una modificación:");
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
