/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

/**
 *
 * @author Alberto
 * @version1
 */
public class Maquina {
    
   protected long id;//identificador
   private Date fechaCompra;//fecha de compra
   private String ubicacion;//localización
   private String imprTipo;//tipo de impresión
   private String imprModo;// modo de impresión 
   private float  volTinta ;//volumen ACTUAL de tinta - Valores [0,capMax]
   private float capMax;//capacidad máxima de tinta - Valor unico nº

   
   
   
   public static Maquina nuevoMaquina() throws ParseException{
    Maquina m = new Maquina();   
           
    Scanner in = new Scanner(System.in);
        char c;
        do {
            System.out.println("Introduzca la ubicación");
            String ubi = in.nextLine();
            m.setUbicacion(ubi);
            System.out.println("Introduzca el tipo de impresión");
            String tipo = in.nextLine();
            m.setImprTipo(tipo);
            System.out.println("Introduzca el modo de impresión");
            String modo = in.nextLine();
            m.setImprModo(modo);
            System.out.println("Introduzca el volumen de tinta");
            float dirf = in.nextFloat();
            m.setVolTinta(dirf);
            System.out.println("Introduzca la capacidad máxima de la máquina");
            float capf = in.nextFloat();
            m.setCapMax(capf);
            System.out.println("Introduzca la fecha de inicio");
            Date fecha = ToolBox.introducirFecha();
            m.setFechaCompra(fecha);
            
            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Ubicación: " + ubi);
            System.out.println("Tipo de Impresión: " + tipo);
            System.out.println("Modo de Impresión: " + modo);
            System.out.println("Volumend de Tinta: " + dirf);
            System.out.println("Capacidad Máxima de Tinta: " + capf);
            System.out.println("Fecha de compra: " + fecha);
            c = in.next().charAt(0);

        } while (c != 's');
        
    
       
     return m;  
   }
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
    public Maquina(long id, Date fechaCompra, String ubicacion, String imprTipo, String imprModo, float volTinta, float capMax) {
        this.fechaCompra = fechaCompra;
        this.ubicacion = ubicacion;
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
        this.fechaCompra = q.getFechaCompra();
        this.ubicacion = q.getUbicacion();
        this.imprTipo = q.getImprTipo();
        this.imprModo = q.getImprModo();
        this.volTinta = q.getVolTinta();
        this.capMax = q.getCapMax();
    }

    public String data() { 
     return id + "|" + fechaCompra + "|" + ubicacion + "|" + imprTipo + "|" + imprModo + "|" + volTinta + "|" + capMax ;
            }
    
    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", fechaCompra=" + fechaCompra + ", loc=" + ubicacion + ", imprTipo=" + imprTipo + ", imprModo=" + imprModo + ", volTinta=" + volTinta + ", capMax=" + capMax + '}';
    }
    
    

    
    
    
}
