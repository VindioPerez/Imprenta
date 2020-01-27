/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public Trabajo(Date fechaSolicitud, Date fechaRecogida, String relieve) throws TrabajoException {

        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        this.fechaSolicitud = fechaSolicitud;
        this.fechaRecogida = fechaRecogida;
        this.relieve = relieve;
    }

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

    public Trabajo(Trabajo t) throws TrabajoException {
        if (!TrabajoException.comprobarRelieve(relieve)) {
            throw new TrabajoException("El relieve no es válido");
        }
        if (!TrabajoException.comprobarId(id)) {
            throw new TrabajoException("El id no es valido");
        }
        this.id = t.getId();
        this.fechaRecogida = t.fechaRecogida;
        this.fechaSolicitud = t.fechaSolicitud;
        this.relieve = t.relieve;
    }

    public long getId() {
        return id;
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

    public void setCliente(Cliente cliente) throws TrabajoException {
        if (!TrabajoException.comprobarClienteVacion(cliente)) {
            throw new TrabajoException("El cliente no puede estar vacío");
        }

        this.cliente = cliente;
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

    public static Trabajo nuevoTrabajo() {
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

    public static Trabajo encargo(Cliente c) throws TrabajoException {
        Trabajo t = new Trabajo();
        Scanner in = new Scanner(System.in);
        System.out.println("Introduzca la fecha de recogida");
        Date fechaRec = ToolBox.introducirFecha();
        while (fechaRec.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) {
            System.out.println("Por favor, introduzca una fecha válida");
            fechaRec = ToolBox.introducirFecha();
        }
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

    public static Trabajo solicitarTrabajo(Cliente c, Operario o) {
        Trabajo t = c.crearTrabajo();
        o.confirmar(t);
        return t;
    }

    protected static void modificarTrabajo(Modificacion m) {

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

    public void setId(long id) throws TrabajoException {
        if (!TrabajoException.comprobarId(id)) {
            throw new TrabajoException("El id no es valido");
        }
        this.id = id;
    }

}
