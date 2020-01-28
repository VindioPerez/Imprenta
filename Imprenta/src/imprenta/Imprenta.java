/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

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
    public static void main(String[] args){
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
                            while (clienteActual == null) {
                                System.out.println("Por favor, introduzca un id válido");
                                idCliente = in.nextLong();
                                clienteActual = Cliente.getClienteById(idCliente);
                            }

                            try{
                            Trabajo t = clienteActual.crearTrabajo();

                            
                            t.setId(BDatos.trabajos.size() + 1);
                            
                            BDatos.trabajos.add(t);
                            break;
                            }catch(TrabajoException tr){
                               
                            }

                        case 'x':
                        case 'X':
                            System.out.println("Introduzca su id de cliente");
                            long idClienteMod = in.nextLong();
                            Cliente clienteMod = Cliente.getClienteById(idClienteMod);

                            System.out.println("");
                            BDatos.trabajos.stream().filter((tr) -> (tr.getCliente() == clienteMod)).forEach((Trabajo tr) -> {
                                BDatos.modificaciones.stream().filter((m) -> (m.getTrabajo() == tr)).forEach((m) -> {
                                    clienteMod.aceptarModificacion(m);
                });
            });
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
                            try{
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
                            }catch(TrabajoException tr){}

                    }

                    break;
                case 'q':
                case 'Q':
                    break;

                case 'm':
                case 'M':
                    System.out.println("Para rellenar una máquina pulse R:");
                    System.out.println("Si quierer realizar una labor, pulse L");
                    char opcionM = in.next().charAt(0);
                    switch (opcionM) {
                        case 'l':
                        case 'L':
                            System.out.println("Introduzca su Id de usuario:");
                            long idUsuarioMq = in.nextLong();
                            OMaquinas omq = OMaquinas.getOperarioMaquinasById(idUsuarioMq);
                            System.out.println("Bienvenido" + omq.getNombre());
                            System.out.println("Introduzca el id de la labor a realizar:");
                            long idLabor = in.nextLong();
                            Labor l = Labor.getLaborById(idLabor);
                            System.out.println("La labor a realizar es :" + l);
                            Labor.realizarLabor(l);
                            break;
                        case 'r':
                        case 'R':
                            System.out.println("Introduzca su Id de usuario:");
                            long idUsuarioM = in.nextLong();
                            OMaquinas om = OMaquinas.getOperarioMaquinasById(idUsuarioM);
                            System.out.println("Bienvenido" + om.getNombre());
                            System.out.println("Introduzca el id de la maquina a rellenar:");
                            long idMaquina = in.nextLong();
                            Maquina m = Maquina.getMaquinaById(idMaquina);
                            System.out.println("La maquina a rellenar es:" + m);
                            om.rellenarMaquina(idMaquina);
                    }

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
