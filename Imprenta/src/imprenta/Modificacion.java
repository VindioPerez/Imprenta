/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alberto
 * @version1
 */
public class Modificacion {

    protected long id;// idenitficador 
    private ArrayList<OImpresion> operarios; //operarios que realizan la modificación
    private Date fecha; //fecha de realización
    private String desc; //descripción del cambio
    private boolean aprob;//aprobacion del cliente
    private Date fechaAprob;//fecha limite aprobación del cliente
    private long idCliente;//variable con el id de cliente que solicita la modificación
    private long idTrabajo;// variable con el id del trabajo a modificar

    public String getDesc() {
        return desc;
    }

    public boolean isAprob() {
        return aprob;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getAprob() {
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
    Maquina maquina;//maquina que la realiza

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getdesc() {
        return desc;
    }

    public void setdesc(String desc) {
        this.desc = desc;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    //constructor con argumentos
    public Modificacion(long id, Date fecha, String desc, boolean aprob, Date fechaAprob, Maquina maquina) {
        this.operarios = new ArrayList<OImpresion>();
        this.fecha = fecha;
        this.desc = desc;
        this.aprob = aprob;
        this.fechaAprob = fechaAprob;
        this.maquina = maquina;
    }

    public ArrayList<OImpresion> getOperarios() {
        return operarios;
    }

    public void setOperarios(ArrayList<OImpresion> operarios) {
        this.operarios = operarios;
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

    //constructor por defecto
    public Modificacion() {
    }

    //constructor de copia
    public Modificacion(Modificacion m) {
        this.operarios = new ArrayList<OImpresion>();
        this.fecha = m.getFecha();
        this.desc = m.getDesc();
        this.aprob = m.getAprob();
        this.fechaAprob = m.getFechaAprob();
        this.maquina = m.getMaquina();
    }

    public String data() {
        return id + "|" + operarios + "|" + fecha + "|" + desc + "|" + aprob + "|" + fechaAprob + "|" + maquina;
    }

    @Override
    public String toString() {
        return "Modificacion{" + "id=" + id + ", operario=" + operarios + ", fecha=" + fecha + ", desc=" + desc + ", aprob=" + aprob + ", fechaAprob=" + fechaAprob + ", maquina=" + maquina + '}';
    }

    public static Modificacion nuevaModificacion() throws ParseException {
        Modificacion m = new Modificacion();
        Scanner in = new Scanner(System.in);
        ArrayList<OImpresion> operarios = new ArrayList<OImpresion>();

        boolean salir;
        boolean cliente;
        boolean operario;
        do {

            System.out.println("introduzca la fecha de Realización:");
            Date fechaRea = ToolBox.introducirFecha();
            System.out.println("introduzca la descripción del cambio:");
            String cambio = in.nextLine();
            m.setDesc(cambio);
            boolean confirmarOperario;
            do {

                System.out.println("Quierés añadir un Operario de impresión?");
                confirmarOperario = ToolBox.leerBoolean();
                if (confirmarOperario) {
                    operarios.add(OImpresion.nuevoOImpresion());
                }

            } while (!confirmarOperario);

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

        public Modificacion getModificacionById(long idModificacion) {

        Modificacion t = new Modificacion();
        /*
        Este método se encarga de recorrer un arraylist con las modificaciones, si el 
        id de parametro coincide con el del modificacion se devuelve esa modificacion sino
        se devuelve null
         */

        return t;
        }
}
