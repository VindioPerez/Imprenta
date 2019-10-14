/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.Date;

/**
 *
 * @author Ander
 * @version 1.0
 */
public class Trabajo {
    private int id; //variable tipo integer con el identificador del trabajo
    private Date fechaSolicitud;//variable tipo Date donde se recoge la enfecha de entre del trabajo
    private Date fechaRecogida;//variable tipo Date donde se recoge la enfecha de recogida del trabajo
    private String relieve; //variable tipo String que indica el tipo de relieve
            

    public Trabajo(){}

    public Trabajo(int id, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        this.id = id;
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
            
            


    public Trabajo(Trabajo t){
        this.id = t.getId();
        this.fechaRecogida=t.fechaRecogida;
        this.fechaSolicitud=t.fechaSolicitud;
    }
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trabajo{" + "id=" + id + ", fechaSolicitud=" + fechaSolicitud + ", fechaRecogida=" + fechaRecogida + '}';
    }
    
    public String data(){
        return id + "|" + fechaSolicitud + "|" + fechaRecogida;
                
    }
    
}
