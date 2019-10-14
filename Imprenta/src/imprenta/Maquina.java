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
 * @version1
 */
public class Maquina {
    
   private long id;//identificador
   private Date fechaCompra;//fecha de compra
   private String loc;//localización
   private String imprTipo;//tipo de impresión
   private String imprModo;// modo de impresión 
   private float  volTinta ;//volumen ACTUAL de tinta - Valores [0,capMax]
   private float capMax;//capacidad máxima de tinta - Valor unico nº

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getImprTipo() {
        return imprTipo;
    }

    public void setImprTipo(String imprTipo) {
        this.imprTipo = imprTipo;
    }

    public String getImprModo() {
        return imprModo;
    }

    public void setImprModo(String imprModo) {
        this.imprModo = imprModo;
    }

    public float getVolTinta() {
        return volTinta;
    }

    public void setVolTinta(float volTinta) {
        this.volTinta = volTinta;
    }

    public float getCapMax() {
        return capMax;
    }

    public void setCapMax(float capMax) {
        this.capMax = capMax;
    }

    //constructor con argumentos
    public Maquina(long id, Date fechaCompra, String loc, String imprTipo, String imprModo, float volTinta, float capMax) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.loc = loc;
        this.imprTipo = imprTipo;
        this.imprModo = imprModo;
        this.volTinta = volTinta;
        this.capMax = capMax;
    }
     
    //constructor por defecto
    public Maquina() {
    }
    
     //Constructor de copia
    public Maquina(Maquina q) {
        this.id = q.getId();
        this.fechaCompra = q.getFechaCompra();
        this.loc = q.getLoc();
        this.imprTipo = q.getImprTipo();
        this.imprModo = q.getImprModo();
        this.volTinta = q.getVolTinta();
        this.capMax = q.getCapMax();
    }

    public String data() { 
     return id + "|" + fechaCompra + "|" + loc + "|" + imprTipo + "|" + imprModo + "|" + volTinta + "|" + capMax ;
            }
    
    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", fechaCompra=" + fechaCompra + ", loc=" + loc + ", imprTipo=" + imprTipo + ", imprModo=" + imprModo + ", volTinta=" + volTinta + ", capMax=" + capMax + '}';
    }
    
    
    
    
    
    
}
