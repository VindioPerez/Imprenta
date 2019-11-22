/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ander
 * @version 1.0
 */
public class Trabajo {

    protected long id; //variable tipo integer con el identificador del trabajo
    protected Date fechaSolicitud;//variable tipo Date donde se recoge la enfecha de entre del trabajo
    protected Date fechaRecogida;//variable tipo Date donde se recoge la enfecha de recogida del trabajo
    protected String relieve; //variable tipo String que indica el tipo de relieve
    private Cliente cliente;
    protected long idMaquina;
    protected long idOperario;

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
        ListaTrabajo 
        if(t.id == idTrabajo){
            t =
        }else{
            t = null;
        }

    return t ;
    
            }
 
        public Trabajo nuevoTrabajo(){
        Trabajo t = new Trabajo();
        Scanner in = new Scanner (System.in);
        char c;
        do {
           System.out.println("Introduzca el nombre");
           String nom = in.nextLine();
           o.setNombre(nom);
           System.out.println("Introduzca los apellidos");
           String ape = in.nextLine();
           o.setNombre(ape);
           System.out.println("Introduzca el NIF");
           String nnif = in.nextLine();
           o.setNombre(nnif);
           System.out.println("Introduzca el teléfono");
           String tel = in.nextLine();
           o.setNombre(tel);
           System.out.println("Introduzca la dirección");
           String dir = in.nextLine();
           o.setNombre(dir);
           char r;
           do{
           System.out.println("¿El operario es senior? (s/n)");
           System.out.println("Por favor, introduzca un carácter válido");
           r=in.next().charAt(0);
           } while (r!='s' || r!='n');
           if (r == 's'){
               o.setSenior(true);
           } else {
               o.setSenior(false);
           }
           System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
           System.out.println("Nombre: "+nom);
           System.out.println("Apellidos: "+ape);
           System.out.println("NIF: "+nnif);
           System.out.println("Teléfono: "+tel);
           System.out.println("Dirección: "+dir);
           if (r == 's'){
               System.out.println("Senior: si");
           } else {System.out.println("Senior: no");}
           c=in.next().charAt(0);

        } while (c != 's');
        return o;
    }
}
