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
    public static void main(String[] args) {
        // TODO code application logic here
        BDatos.inicializar();
        while (true) {
            System.out.println("¿Qué tipo de usuario eres?");
            System.out.println("Si eres un cliente, introduce una C");
            System.out.println("Si eres un operario de impresión, introduce una I");
            System.out.println("Si eres un operario de calidad, introduce una Q");
            System.out.println("Si eres un operario de maquinas, introduce una M");
            System.out.println("Si quieres salir, introduce una S");
            Scanner in = new Scanner(System.in);
            char opcion = in.next().charAt(0);
            switch (opcion) {
                case 'c':
                case 'C':
                    System.out.println("¿Qué quiere hacer?");
                    System.out.println("Si quiere registrarse, pulse R");
                    System.out.println("Si quiere encargar un trabajo, pulse E");
                    System.out.println("Si quiere salir, pulse S");
                    System.out.println("Si quieres revisar el trabajo, pulse X");
                    char opcionC = in.next().charAt(0);
                    switch (opcionC) {
                        case 'r':
                        case 'R':
                            try {
                                Cliente nuevoCliente = Cliente.registrarCliente();
                                BDatos.clientes.add(nuevoCliente);
                            } catch (ClienteException clienteinvalido) {
                                System.out.println("El cliente no es válido");
                            } finally {
                                break;
                            }
                        case 'e':
                        case 'E':
                            System.out.println("Introduzca su id de cliente");
                            long idCliente = in.nextLong();
                            Cliente clienteActual = Cliente.getClienteById(idCliente);
                            while (clienteActual==null){
                                System.out.println("Por favor, introduzca un id válido");
                                idCliente = in.nextLong();
                                clienteActual = Cliente.getClienteById(idCliente);
                            }

                            Trabajo t = clienteActual.crearTrabajo();

                            t.setId(BDatos.trabajos.size() + 1);
                            BDatos.trabajos.add(t);
                            break;

                        case 'x':
                        case 'X':
                            System.out.println("Introduzca su id de cliente");
                            long idClienteMod = in.nextLong();
                            Cliente clienteMod = Cliente.getClienteById(idClienteMod);

                            System.out.println("");
                            for (Trabajo tr : BDatos.trabajos) {
                                if (tr.getCliente() == clienteMod) {
                                    for (Modificacion m : BDatos.modificaciones) {
                                        if (m.getTrabajo() == tr) {
                                            clienteMod.aceptarModificacion(m);

                                        }

                                    }

                                }

                            }
                        default:
                            break;

                    }
                    break;
                case 'i':
                case 'I':
                    System.out.println("Si quierer realizar una modificación, pulse M");
                    char opcionI = in.next().charAt(0);
                    switch (opcionI) {
                        case 'm':
                        case 'M':
                            System.out.println("Introduzca su ID de usuario:");
                            long idUsuario = in.nextLong();
                            OImpresion operarioI = OImpresion.getOperarioImpresionById(idUsuario);
                            System.out.println("Introduzca el ID del trabajo a modificar");
                            long idTrabajo = in.nextLong();
                            Trabajo t = Trabajo.getTrabajoById(idTrabajo);
                            Modificacion m = operarioI.realizarModificacion(t);
                            System.out.println("El cliente ha aceptado la modificación?");
                            Cliente c = t.getCliente();
                            boolean aceptado = c.aceptarModificacion(m);
                            if (aceptado) {
                                System.out.println("La modificación ha sido aceptada por el cliente");
                            } else {
                                System.out.println("La modificación no ha sido aceptada por el cliente");
                            }

                    }

                    break;
                case 'q':
                case 'Q':
                    break;

                default:
                    break;
            }
            if (opcion == 's' || opcion == 'S') {
                break;
            }

        }

    }

}
