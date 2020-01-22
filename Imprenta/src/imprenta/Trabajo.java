/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
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

    public Trabajo() {
    }

    public Trabajo(Date fechaSolicitud, Date fechaRecogida, String relieve) {

        this.fechaSolicitud = fechaSolicitud;
        this.fechaRecogida = fechaRecogida;
        this.relieve = relieve;
    }

    public String getRelieve() {
        return relieve;
    }

    public void setRelieve(String relieve) {
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

    public Trabajo(Trabajo t) {
        this.id = t.getId();
        this.fechaRecogida = t.fechaRecogida;
        this.fechaSolicitud = t.fechaSolicitud;
    }

    public long getId() {
        return id;
    }

    /*
    public void setId(int id) {
        this.id = id;
    }
     */
    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", fechaSolicitud=" + fechaSolicitud + ", fechaRecogida=" + fechaRecogida + '}';
    }

    public String data() {
        return id + "|" + fechaSolicitud + "|" + fechaRecogida;

    }

    public Cliente getClienteById() {

        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Trabajo getTrabajoById(long idTrabajo) {

        Trabajo t = new Trabajo();
        /*
        Este método se encarga de recorrer un arraylist con los trabajos, si el 
        id de parametro coincide con el del trabajo se devuelve ese trabajo sino
        se devuelve null
         */

        return t;

    }

    public static Trabajo nuevoTrabajo() throws ParseException {
        Trabajo t = new Trabajo();
        Scanner in = new Scanner(System.in);
        boolean salir;
        do {

            System.out.println("Introduzca la fecha de solicitud del trabajo");
            Date fechaSol = ToolBox.introducirFecha();
            System.out.println("Introduzca la fecha de recogida");
            Date fechaRec = ToolBox.introducirFecha();
            System.out.println("Introduzca el relieve en el que desea su trabajo");
            String relieve = in.nextLine();
            t.setRelieve(relieve);
            System.out.println("Introduzca un cliente");
            Cliente cli = Cliente.nuevoCliente();
            System.out.println("Introduzca el id de la maquina:");
            long idmaq = Maquina.nuevoMaquina().getId();
            System.out.println("Introduzca el id del operario");
            long idope = OImpresion.nuevoOImpresion().getId();

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
    
    public static Trabajo encargo (Cliente c) throws ParseException{
        Trabajo t = new Trabajo();
        Scanner in = new Scanner(System.in);
        boolean salir;
            System.out.println("Introduzca la fecha de recogida");
            Date fechaRec = ToolBox.introducirFecha();
            t.setFechaRecogida(fechaRec);
            System.out.println("Introduzca el relieve en el que desea su trabajo");
            String relieve = in.nextLine();
            t.setRelieve(relieve);
            t.setCliente(c);

            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Recogida: " + fechaRec);
            System.out.println("Relieve: " + relieve);
        return t;
    }
    
}
