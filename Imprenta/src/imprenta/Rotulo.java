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
public class Rotulo extends Trabajo{
    private String direccion; //variable de tipo String que recoge el lugar de destino del trabajo

    public Rotulo(String direccion, int id, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(id, fechaSolicitud, fechaRecogida, relieve);
        this.direccion = direccion;
    }

    


    public Rotulo(){}
    
    public Rotulo(Rotulo r){
        this.direccion = r.getDireccion();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Rotulo{" + "direccion=" + direccion + '}';
    }
    
    public String data(){
        return getId() + "|" + getFechaSolicitud() + getRelieve()+ "|" + getFechaRecogida() + "|" + direccion;
        
      
        
    }
    
}
