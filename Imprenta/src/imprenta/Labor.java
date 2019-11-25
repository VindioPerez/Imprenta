/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Alberto
 * @version1
 */
public class Labor {

    protected long id;//identificador
    
    private Date fecha;// fecha de realizacion de la labor
    private String tarea;//en que consiste
    private Maquina maquina;//maquina que lo realiza
    private ArrayList<OMaquinas> operariosM; // operarios que hacen la labor
    
    
    
    public static Labor nuevoLabor() throws ParseException{
      Labor lab= new Labor();  
    
     Scanner in = new Scanner(System.in);
        char c;
        char d;
        do {
            System.out.println("Introduzca el departamento");
           
             
            System.out.println("Introduzca la tarea");
            String tarea = in.nextLine();
            lab.setTarea(tarea);
            System.out.println("Introduzca la fecha de realizacion");
            Date fecha = ToolBox.introducirFecha();
            lab.setFecha(fecha);
            Maquina maq =  Maquina.nuevoMaquina();
            lab.setMaquina(maq);
            System.out.println("Quiere Introducir un nuevo Operario de Maquinas? s/n ");
            d = in.next().charAt(0);
            

            while (d == 's') {
                lab.operariosM.add(OMaquinas.nuevoOMaquinas());

                System.out.println("Quiere Introducir otro Operario? s/n ");
                d = in.next().charAt(0);
            }

            System.out.println("¿Son correctos estos datos? (introduzca una v si lo son)");
            System.out.println("Tarea: " +tarea );
            System.out.println("Fecha de realización: " + fecha);

            c = in.next().charAt(0);

        } while (c != 'v' || c != 'V');
        return lab;
    }
    
    
    
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
    public Labor(long id,  Date fecha, String tarea, Maquina maquina) {
        this.id = id;
      
        this.fecha = fecha;
        this.tarea = tarea;
        this.maquina = maquina;
    }

    //constructor de copia
    public Labor(Labor l) {
        this.id = l.getId();
        
        this.fecha = l.getFecha();
        this.tarea = l.getTarea();
        this.maquina = l.getMaquina();
    }

    public String data() {
        return id +  "|" + fecha + "|" + tarea + "|" + maquina;

    }

    @Override
    public String toString() {
        return "Labor{" + "id=" + id +  ", fecha=" + fecha + ", tarea=" + tarea + ", maquina=" + maquina + '}';
    }

}
