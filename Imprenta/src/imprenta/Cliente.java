package imprenta;

import java.text.ParseException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ander
 * @version 1.1
 */
public class Cliente {

    private String nombre; //variable de tipo string que recoge el nombre del cliente
    private String telefono; //variable de tipo string que recoge el número de teléfono del cliente
    protected long id;//variable identificador

    public Cliente() {
    }

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente(Cliente c) {
        this.nombre = c.getNombre();
        this.telefono = c.getTelefono();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", telefono=" + telefono + ", id=" + id + '}';
    }

    public String data() {

        return id + "|" + nombre + "|" + telefono;
    }

    public static Cliente nuevoCliente() {
        Cliente c = new Cliente();
        Scanner sc = new Scanner(System.in);
        boolean salir;
        do {
            System.out.println("Introduzca su nombre:");
            String nombre = sc.nextLine();
            c.setNombre(nombre);
            System.out.println("Introduzca su número de teléfono");
            String tlfn = sc.nextLine();
            c.setTelefono(tlfn);
            System.out.println("Son correctos los siguiente datos?(s/n)");
            salir = ToolBox.leerBoolean();
        } while (salir);

        return c;

    }

    
        public Cliente getClienteById(long idCliente) {

        Cliente c = new Cliente();
        /*
        Este método se encarga de recorrer un arraylist con los cliente, si el 
        id de parametro coincide con el del cliente se devuelve ese cliente sino
        se devuelve null
         */

        return c;

        }
        
        public static Poster encargo (Cliente c) throws ParseException {
        Poster r;
        Scanner in = new Scanner(System.in);
        boolean check;
        do{
            r = new Poster(Trabajo.encargo(c));
            System.out.println("Introduzca el alto en cm");
            double alt = in.nextDouble();
            r.setAlto(alt);
            System.out.println("Introduzca el ancho en cm");
            double anc = in.nextDouble();
            r.setAncho(anc);
            System.out.println("Introduzca el número de copias");
            int num = in.nextInt();
            r.setNumCopias(num);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Fecha Recogida: " + r.getFechaRecogida());
            System.out.println("Relieve: " + r.getRelieve());
            System.out.println("Alto (cm): " + alt);
            System.out.println("Ancho (cm): " + anc);
            System.out.println("Número de copias: " + num);
            check = ToolBox.leerBoolean();
        } while (!check);            
        
        return r;
    }
}