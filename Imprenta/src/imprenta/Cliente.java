package imprenta;

import java.text.ParseException;
import java.util.Date;
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
    
    public Cliente(String nombre, String telefono, long id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
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

    
        public static Cliente getClienteById(long idCliente) {
            for (Cliente c : BDatos.clientes){
                if (c.getId()==idCliente) return c;
            }
            return null;
        }
        
        public Trabajo crearTrabajo() throws ParseException {
            Trabajo t;
            Scanner in = new Scanner(System.in);
            System.out.println("¿Qué tipo de trabajo desea solicitar? (poster=P, libro=L, rótulo=R)");
            char opcion = in.next().charAt(0);
            while(opcion!='p'&&opcion!='P'&&opcion!='r'&&opcion!='R'&&opcion!='l'&&opcion!='L'){
                System.out.println("Por favor, introduzca una opcion válida");
                opcion = in.next().charAt(0);
            }
            switch (opcion){
                case 'p':
                case 'P':
                    t = Poster.encargo(this);
                    break;
                case 'l':
                case 'L':
                    t = Libro.encargo(this);
                    break;
                case 'r':
                case 'R':
                    t = Rotulo.encargo(this);
                    break;
                default:
                    t = Trabajo.encargo(this);
                    break;
            }
            return t;
        }
        
        public static Cliente registrarCliente() throws NumeroInvalidoException{
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
            if(!c.getTelefono().matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")){
                    throw new NumeroInvalidoException ();
                } else {
            c.setId(BDatos.clientes.size()+1);
            return c;}
        }
        
        
        public  Modificacion solicitarModificación(Trabajo t) throws ParseException{
            Modificacion m = new Modificacion();
            Scanner sc = new Scanner (System.in);
            System.out.println("introduce los datos a modificar:");
            System.out.println("introduce la fecha de realización");
            Date fechaRea = ToolBox.introducirFecha();
            System.out.println("introduzca una descripción de la modificación");
            String descripcion = sc.nextLine();
            System.out.println("Su modificación es la siguiente" + descripcion);
            System.out.println("Es correcta su modificación?");
            boolean aceptar = ToolBox.leerBoolean();
            if (aceptar){
                m.setFecha(fechaRea);
                m.setDesc(descripcion);
                m.setAprob(aceptar);
                System.out.println("Introduzca la fecha de hoy para aprobar el trabajo:");
                Date fechaApro = ToolBox.introducirFecha();
                m.setFechaAprob(fechaApro);
                m.setIdTrabajo(t.getId());//id del trabajo
                m.setIdCliente(this.id);//id del cliente
            }
            
            return m;
        }
        
}
