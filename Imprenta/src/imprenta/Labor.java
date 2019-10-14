/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.util.Date;
/**
 *
 * @author Alberto
 * @version 1.0
 */
public class Labor {
    
   private long id;//identificador
   private OperarioMaquinas operario; // operario que hace la labor
   private Date fecha;// fecha de realizacion de la labor
   private String tarea;//en que consiste
   private Maquina maquina;//maquina que lo realiza

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OperarioMaquinas getOperario() {
        return operario;
    }

    public void setOperario(OperarioMaquinas operario) {
        this.operario = operario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    //constructor por defecto
    public Labor() {
    }

    //constructor con argumentos
    public Labor(long id, OperarioMaquinas operario, Date fecha, String tarea, Maquina maquina) {
        this.id = id;
        this.operario = operario;
        this.fecha = fecha;
        this.tarea = tarea;
        this.maquina = maquina;
    }
    
    //constructor de copia
    public Labor(Labor l) {
        this.id = l.getId();
        this.operario = l.getOperario();
        this.fecha = l.getFecha();
        this.tarea = l.getTarea();
        this.maquina = l.getMaquina();
    }

     public String data() { 
     return id + "|" + operario + "|" + fecha + "|" + tarea + "|" + maquina ;
          
            }
    
    @Override
    public String toString() {
        return "Labor{" + "id=" + id + ", operario=" + operario + ", fecha=" + fecha + ", tarea=" + tarea + ", maquina=" + maquina + '}';
    }
    
           
    
    
    
    
}
