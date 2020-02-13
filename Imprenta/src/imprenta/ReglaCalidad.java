/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.util.ArrayList;

/**
 *
 * @author DAW104
 */
public class ReglaCalidad {

    private ArrayList<Prueba> pruebas; //ArrayList que contiene las pruebas de 0 a n 
    private String nombre;//Nombre de la regla
    private long idPolitica;
    private long idRegla;

    public ArrayList<Prueba> getPruebas() {
        return pruebas;
    }
    
    public ReglaCalidad(){};

    public void setPruebas(ArrayList<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public static ReglaCalidad nuevoRegla(){
        ReglaCalidad regla = new ReglaCalidad();
        boolean d;
        boolean c=true;
        do{
        ArrayList<Prueba> pruebas = new ArrayList();
            regla.setPruebas(pruebas);
            
            System.out.println("Quiere Introducir una nueva Prueba? s/n ");
            d = ToolBox.leerBoolean();
            

            while (d) {
                regla.pruebas.add(Prueba.nuevoPrueba());

                System.out.println("Quiere Introducir otra Prueba? s/n ");
                d = ToolBox.leerBoolean();
            }
            // preguntar si los datos son correctos en caso de añadir nuevos atributos
       
        }while(c);
        return regla;
    }

    @Override
    public String toString() {
        return "ReglaCalidad:\n"
                + "ID regla:"+idRegla+"|Nombre:"+nombre+"|ID política:"+idPolitica;
    }
    
    public String data() {
        return "ReglaCalidad:\n"
                + "ID regla:"+this.getIdRegla()+"|Nombre:"+this.getNombre()+"|ID política:"+this.getIdPolitica();
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(long idPolitica) {
        this.idPolitica = idPolitica;
    }

    public long getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(long idRegla) {
        this.idRegla = idRegla;
    }

    public ReglaCalidad(String nombre, long idPolitica, long idRegla) {
        this.nombre = nombre;
        this.idPolitica = idPolitica;
        this.idRegla = idRegla;
    }

    
    

}
