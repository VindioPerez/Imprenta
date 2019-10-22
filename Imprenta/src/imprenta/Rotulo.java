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
public class Rotulo extends Trabajo {

    private String centroComercial; //variable de tipo String que recoge el lugar de destino del trabajo

    public Rotulo(String centroComercial, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(fechaSolicitud, fechaRecogida, relieve);
        this.centroComercial = centroComercial;
    }

    public Rotulo() {
    }

    public Rotulo(Rotulo r) {
        this.centroComercial = r.getCentroComercial();
    }

    public String getCentroComercial() {
        return centroComercial;
    }

    public void setCentroComercial(String centroComercial) {
        this.centroComercial = centroComercial;
    }

    @Override
    public String toString() {
        return "Rotulo{" + "direccion=" + centroComercial + '}';
    }

    public String data() {
        return getId() + "|" + getFechaSolicitud() + getRelieve() + "|" + getFechaRecogida() + "|" + centroComercial;

    }

}
