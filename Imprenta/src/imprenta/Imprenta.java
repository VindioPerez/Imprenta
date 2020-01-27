/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
        BDatos.inicializar();
        while(true){
            System.out.println("¿Qué tipo de usuario eres?");
            System.out.println("Si eres un cliente, introduce una C");
            System.out.println("Si eres un operario de impresión, introduce una I");
            System.out.println("Si eres un operario de calidad, introduce una Q");
            System.out.println("Si eres un operario de maquinas, introduce una M");
            System.out.println("Si quieres salir, introduce una S");
            Scanner in = new Scanner(System.in);
            char opcion = in.next().charAt(0);
            switch (opcion){
                case 'c':
                case 'C':
                    System.out.println("¿Qué quiere hacer?");
                    System.out.println("Si quiere registrarse, pulse R");
                    System.out.println("Si quiere encargar un trabajo, pulse E");
                    System.out.println("Si quiere salir, pulse S");
                    char opcionC = in.next().charAt(0);
                    switch (opcionC){
                        case 'r':
                        case 'R':
                            try {
                                Cliente nuevoCliente = Cliente.registrarCliente();
                                BDatos.clientes.add(nuevoCliente);}
                            catch (NumeroInvalidoException numeroinvalido){
                                System.out.println("\"El formato de numero introducido no es correcto\"");}
                            finally {
                                break;}
                        case 'e':
                        case 'E':
                            System.out.println("Introduzca su id de cliente");
                            long idCliente = in.nextLong();
                            Cliente clienteActual = Cliente.getClienteById(idCliente);
                            clienteActual.crearTrabajo();
                            break;
                        default:
                            break;
                    }
                    break;
                case 'i':
                case 'I':
                    break;
                case 'q':
                case 'Q':
                    break;
                case 'm':
                case 'M':
                    break;
                default:
                    break;
            }
            if (opcion=='s'||opcion=='S') break;
        
        
        }
        
    }

}
