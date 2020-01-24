/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;

/**
 *
 * @author DAW107
 */
public class BDatos {
    
    public static ArrayList<Cliente> clientes;
    public static ArrayList<Departamento> departamentos;
    public static ArrayList<Labor> labores;
    public static ArrayList<Libro> libros;
    public static ArrayList<Lote> lotes;
    public static ArrayList<Maquina> maquinas;
    public static ArrayList<Modificacion> modificaciones;
    public static ArrayList<OCalidad> osCalidad;
    public static ArrayList<OImpresion> osImpresion;
    public static ArrayList<OMaquinas> osMaquinas;
    public static ArrayList<Operario> operarios;
    public static ArrayList<Politica> politicas;
    public static ArrayList<Poster> posters;
    public static ArrayList<Prueba> pruebas;
    public static ArrayList<ReglaCalidad> reglas;
    public static ArrayList<Rotulo> rotulos;
    public static ArrayList<Trabajo> trabajos;
    
    public static void inicializar(){
        Cliente c1 = new Cliente("Pedro Ruiz", "634789231");
    }
    
}
