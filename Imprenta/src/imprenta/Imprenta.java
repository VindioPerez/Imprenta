/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;

/**
 *
 * @author DAW108
 * @version 1.1
 */
public class Imprenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Cliente a = Cliente.nuevoCliente();
        Departamento b = Departamento.nuevoDepartamento();
        Labor c = Labor.nuevoLabor();
        Libro d = Libro.nuevoLibro();
        Lote e = Lote.nuevoLote();
        Maquina f = Maquina.nuevoMaquina();
        Modificacion g = Modificacion.nuevaModificacion();
        OCalidad h = OCalidad.nuevoOCalidad();
        OImpresion i = OImpresion.nuevoOImpresion();
        OMaquinas j = OMaquinas.nuevoOMaquinas();
        Operario k = Operario.nuevoOperario();
        Politica l = Politica.nuevaPolitica();
        Poster m = Poster.nuevoPoster();
        Rotulo n = Rotulo.nuevoRotulo();
        Trabajo o = Trabajo.nuevoTrabajo();
        
    }

}
