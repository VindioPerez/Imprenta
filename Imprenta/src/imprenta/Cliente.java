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

    public Cliente(String nombre, String telefono) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;
        this.telefono = telefono;}
    }
    
    public Cliente(String nombre, String telefono, long id) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;}
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ClienteException{
        if (!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = nombre;}
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else {
        this.telefono = telefono;}
    }

    public Cliente(Cliente c) throws ClienteException{
        if (!ClienteException.comprobarTelefono(telefono)){
            throw new ClienteException("El telefono no es valido");
        } else if(!ClienteException.comprobarNombre(nombre)){
            throw new ClienteException("El nombre no es valido");
        } else {
        this.nombre = c.getNombre();
        this.telefono = c.getTelefono();}
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
        return this.getId() + "|" + this.getNombre() + "|" + this.getTelefono();
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
            
            Cliente temp = null;
            for (Cliente c : BDatos.clientes){
                if (c.getId()==idCliente){
                temp = c;
                }
            }
            return temp;
            }
        
        
        public Trabajo crearTrabajo() throws TrabajoException{
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
        
        public static Cliente registrarCliente() throws ClienteException{
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
            } while (!salir);
            if (!ClienteException.comprobarTelefono(c.getTelefono())){
                throw new ClienteException("El telefono no es valido");
            } else if(!ClienteException.comprobarNombre(c.getNombre())){
                throw new ClienteException("El nombre no es valido");
            } else {
            c.setId(BDatos.clientes.size()+1);
            return c;}
        }
        
        
        
        
        
         public boolean aceptarModificacion(Modificacion m){
             
            System.out.println("Esta es su modificación"+ m.toString());
            System.out.println("Quiére aceptar la modificación?");
            boolean aceptar = ToolBox.leerBoolean();
            if(aceptar){
                m.setAprob(aceptar);
                System.out.println("Introduzca la fecha de aprobación:");
                Date fechaA = ToolBox.introducirFecha();
                m.setFechaAprob(fechaA);
                m.setId(this.id);
            }
            return aceptar;
     }
        
}
