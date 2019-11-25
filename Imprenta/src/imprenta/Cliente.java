package imprenta;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ander
 * @version 1.0
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
    
    public static Cliente nuevoCliente(){
        Cliente c = new Cliente();
        Scanner sc = new Scanner(System.in);
        char d; 
        do{
            System.out.println("Introduzca su nombre:");
            String nombre = sc.nextLine();
            c.setNombre(nombre);
            System.out.println("Introduzca su número de teléfono");
            String tlfn = sc.nextLine();
            c.setTelefono(tlfn);
            System.out.println("Son correctos los siguiente datos?");
            d = sc.nextLine().charAt(0);
            
        }while(d == 's');
    
        return c;
    
    
    }

}
