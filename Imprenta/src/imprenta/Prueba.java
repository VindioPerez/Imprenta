/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author DAW104
 */
public class Prueba {
 
    
    protected long id;
    private Date fechaR;
    private OCalidad operarioC;
    private String desc;
    private String res;
    private String obs;
    private Maquina maquina;
    
    // Constructor por defecto
    public Prueba() {
    }

    
    // Constructor con argumentos
    public Prueba( Date fechaR, OCalidad operario, String desc, String res, String obs) {
        
        this.fechaR = fechaR;
        this.operarioC = operario;
        this.desc = desc;
        this.res = res;
        this.obs = obs;
    }
    
    // Constructor de copia
    public Prueba(Prueba prueba) {
        
        this.fechaR = prueba.getFechaR();
        this.operarioC = prueba.getOperarioC();
        this.desc = prueba.getDesc();
        this.res = prueba.getRes();
        this.obs = prueba.getObs();
        this.maquina = prueba.getMaquina();
    }

    

    
    public static Prueba nuevoPrueba() throws ParseException{
    Prueba prueba = new Prueba();
    Scanner in = new Scanner(System.in);
    boolean c;
        
        do {
            
           
             
            System.out.println("Introduzca la descripción");
            String desc = in.nextLine();
            prueba.setDesc(desc);
            System.out.println("Introduzca el resultado");
            String res = in.nextLine();
            prueba.setRes(res);
            System.out.println("Introduzca la observación");
            String obs = in.nextLine();
            prueba.setObs(obs);
            System.out.println("Introduzca la fecha de realización");
            Date fecha = ToolBox.introducirFecha();
            prueba.setFechaR(fecha);
            System.out.println("Introduzca los datos del Operario de Calidad asignado");
            OCalidad ope =  OCalidad.nuevoOCalidad();
            prueba.setOperarioC(ope);
            System.out.println("Introduzca los datos de la Máquina asignada");
            Maquina maq =  Maquina.nuevoMaquina();
            prueba.setMaquina(maq);

           
            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Descripción: " +desc);
            System.out.println("Resultados: " +res);
            System.out.println("Observaciones: " +obs);
            System.out.println("Fecha de realización: " + fecha);

            c = ToolBox.leerBoolean();

        } while (!c);
       
       
            
    return prueba;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }
    
    public Date getFechaR() {
        return fechaR;
    }

    public void setFechaR(Date fechaR) {
        this.fechaR = fechaR;
    }

    public OCalidad getOperarioC() {
        return operarioC;
    }

    public void setOperarioC(OCalidad operario) {
        this.operarioC = operario;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public String toString() {
        return "Prueba{" + "id=" + id + ", fechaR=" + fechaR + ", operarioC=" + operarioC + ", desc=" + desc + ", res=" + res + ", obs=" + obs + ", maquina=" + maquina + '}';
    }
    
    public String data() {
        return id + "|" + fechaR + "|" + operarioC + "|" + desc + "|" + res + "|" + obs + "|" + maquina;
    }
    
    
    
    
    
    
    
}
