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
    private long idCliente;
    private long idTrabajo;

    public String getDesc() {
        return desc;
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

    public Modificacion nuevaModificacion() throws ParseException {
        Modificacion m = new Modificacion();
        Scanner in = new Scanner(System.in);
        ArrayList<OImpresion> operarios = new ArrayList<OImpresion>();

        char c;
        boolean cliente = false;
        boolean operario = false;
        do {


            System.out.println("introduzca la fecha de solicitud");
            ToolBox.introducirFecha();
            System.out.println("introduzca la descripción del cambio:");
            System.out.println("¿El cliente ha aprobado el cambio?");
            cliente = ToolBox.leerBoolean();
            if (!ToolBox.leerBoolean()) {
                System.out.println("Cambio no aceptado");
            } else {
                System.out.println("Cambio aceptado");
            }
            System.out.println("Los operarios de impresión han aprobado el cambio?");
            operario = ToolBox.leerBoolean();
            System.out.println("introduzca la fecha de realización");

        } while (!cliente && !operario);
        return m;
    }

}
