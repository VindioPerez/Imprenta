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

    private Date fechaini;// fecha de inicio de la labor
    private Date fechafin;// fecha de finalización de la labor
    private String tarea;//en que consiste
    private Maquina maquina;//maquina que lo realiza
    private ArrayList<OMaquinas> operariosM; // operarios que hacen la labor
    private char estado;// estado de la labor

    public static Labor nuevoLabor() throws ParseException {
        Labor lab = new Labor();

        Scanner in = new Scanner(System.in);
        boolean c;
        boolean d;
        do {

            System.out.println("Introduzca la tarea");
            String tarea = in.nextLine();
            lab.setTarea(tarea);
            System.out.println("Introduzca la fecha de realizacion");
            Date fecha = ToolBox.introducirFecha();
            lab.setFechaini(fecha);
            System.out.println("Introduzca la fecha de realizacion");
            Date fechafin = ToolBox.introducirFecha();
            lab.setFechafin(fechafin);
            System.out.println("Introduzca los datos de la Máquina asignada");
            Maquina maq = Maquina.nuevoMaquina();
            lab.setMaquina(maq);
            System.out.println("Quiere Introducir un nuevo Operario de Maquinas? s/n ");
            d = ToolBox.leerBoolean();
            char a = 0;
            lab.setEstado(a);

            while (d) {
                ArrayList<OMaquinas> ops = new ArrayList<OMaquinas>();
                lab.operariosM.add(OMaquinas.nuevoOMaquinas());
                lab.setOperariosM(ops);
                System.out.println("Quiere Introducir otro Operario? s/n ");
                d = ToolBox.leerBoolean();
            }

            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Tarea: " + tarea);
            System.out.println("Fecha de realización: " + fecha);

            c = ToolBox.leerBoolean();

        } while (!c);
        Maquina.noDisponible(lab.getMaquina());
        return lab;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
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

    public ArrayList<OMaquinas> getOperariosM() {
        return operariosM;
    }

    public void setOperariosM(ArrayList<OMaquinas> operariosM) {
        this.operariosM = operariosM;
    }

    //constructor por defecto
    public Labor() {
    }

    //constructor con argumentos
    public Labor(long id, Date fecha, String tarea, Maquina maquina) {
        this.id = id;

        this.fechaini = fecha;
        this.tarea = tarea;
        this.maquina = maquina;
    }

    //constructor con argumentos ampliado 
    public Labor(long id, Date fechaini, Date fechafin, String tarea, Maquina maquina, ArrayList<OMaquinas> operariosM, char estado) {
        this.id = id;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.tarea = tarea;
        this.maquina = maquina;
        this.operariosM = operariosM;
        this.estado = estado;
    }

    //constructor de copia
    public Labor(Labor l) {
        this.id = l.getId();

        this.fechaini = l.getFechaini();
        this.tarea = l.getTarea();
        this.maquina = l.getMaquina();
    }

    public String data() {
        return id + "|" + fechaini + "|" + fechafin + "|" + tarea + "|" + maquina;

    }

    @Override
    public String toString() {
        return "Labor{" + "id=" + id + ", fecha ini=" + fechaini + ", fecha fin =" + fechafin + ", tarea=" + tarea + ", maquina=" + maquina + '}';
    }

    public static void realizarLabor(Labor l) throws LaborException {
        Scanner in = new Scanner(System.in);
        System.out.println("El estado actual de la labor es el siguiente: ");
        char estado;
        boolean  b=false;
        estado = l.getEstado();
        if (estado != 'p' && estado != 'P' && estado != '0' && estado != 'f' && estado != 'F') {
            throw new LaborException(" El Estado no tiene un valor  válido");
        } else {
            switch (estado) {
                case 0:
                    System.out.println("La labor no se ha comenzado (0)");
                    break;
                case 'p':
                case 'P':
                    System.out.println("En pausa ('p'||'P') ");
                    break;
                case 'r':
                case 'R':
                    System.out.println(" En realización('p'||'P') ");
                    break;
                case 'f':
                case 'F':
                    System.out.println("Finalizado ('f'||'F') ");
                    break;
            }
        }
        System.out.println("¿Desea cambiar el estado de la labor (si/no)? ");
        String ans = in.nextLine();
        if (!"si".equals(ans) & !"Si".equals(ans) & !"SI".equals(ans) & !"sI".equals(ans) & !"NO".equals(ans)
                & !"no".equals(ans) & !"nO".equals(ans) & !"nO".equals(ans)) {
            if (!"NO".equals(ans) & !"no".equals(ans) & !"nO".equals(ans) & !"nO".equals(ans)) {
                do{
                  
                System.out.println("Introduzca el nuevo estado de la labor, las opciones validas son las siguientes: (0: sin comenzar,"
                        + "p: en pausa, r: en realización, f: finalizado ");
                
                char nuevoestado =in.next().charAt(0);
                 
                System.out.println("El estado actual de la labor es el siguiente: ");
                switch (estado) {
                case 0:
                    System.out.println("La labor no se ha comenzado (0)");
                    break;
                case 'p':
                case 'P':
                    System.out.println("En pausa ('p'||'P') ");
                    break;
                case 'r':
                case 'R':
                    System.out.println(" En realización('p'||'P') ");
                    break;
                case 'f':
                case 'F':
                    System.out.println("Finalizado ('f'||'F') ");
                    break;
                }
              System.out.println("¿Está de acuerdo con lo mostrado? (si/no");      
              String verif = in.nextLine();
              if (!"si".equals(verif) & !"Si".equals(verif) & !"SI".equals(verif) & !"sI".equals(verif) & !"NO".equals(verif)
                & !"no".equals(verif) & !"nO".equals(verif) & !"nO".equals(verif)) {
            if (!"NO".equals(verif) & !"no".equals(verif) & !"nO".equals(verif) & !"nO".equals(verif)) {
                b=true;
               System.out.println("El cambio se ha realizado satisfactioriamente"); 
            }
            } else { throw new LaborException("no ha dado una respuesta válida");}
                
                if(estado != 'p' && estado != 'P' && estado != '0' && estado != 'f' && estado != 'F'){
                 
                  
                }else {throw new LaborException("El nuevo valor de estado no es válido");}
                 l.estado= nuevoestado; } while(b=false);
            }

        } else { throw new LaborException("no ha dado una respuesta válida");}
        
    }
    
     public static Labor getLaborById(long idLabor) {
            
            Labor temp = null;
            for (Labor l : BDatos.labores){
                if (l.getId()==idLabor){
                temp = l;
                }
            }
            return temp;
            }
    }
