/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author VindioPerez
 * @version 1.0
 */
public class Operario {

    protected String nombre;
    //El nombre del operario, se define con un String alfabetico
    protected String apellidos;
    //Los apellidos, se definen con un String alfabetico
    protected String nif;
    //El NIF, se define con un String alfanumerico de 8 numeros y una letra
    protected String telefono;
    //El numero de telefono, se define con un String de 9 numeros empezado por 6, 7 o 9
    protected String direccion;
    //La direccion, se define con un String alfanumerico
    protected boolean senior;
    //La categoria del empleado, solo puede o ser senior o no serlo
    protected long id;
    //variable identificador 
    protected ArrayList<Departamento> departamentos;

    public Operario() {
    }

    public Operario(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.telefono = telefono;
        this.direccion = direccion;
        this.senior = senior;
    }

    public Operario(Operario o) {
        this.nombre = o.getNombre();
        this.apellidos = o.getApellidos();
        this.nif = o.getNif();
        this.telefono = o.getTelefono();
        this.direccion = o.getDireccion();
        this.senior = o.isSenior();
    }

    public static Operario nuevoOperario() {
        Operario o = new Operario();
        Scanner in = new Scanner(System.in);
        char c;
        do {
            System.out.println("Introduzca el nombre");
            String nom = in.nextLine();
            o.setNombre(nom);
            System.out.println("Introduzca los apellidos");
            String ape = in.nextLine();
            o.setNombre(ape);
            System.out.println("Introduzca el NIF");
            String nnif = in.nextLine();
            o.setNombre(nnif);
            System.out.println("Introduzca el teléfono");
            String tel = in.nextLine();
            o.setNombre(tel);
            System.out.println("Introduzca la dirección");
            String dir = in.nextLine();
            o.setNombre(dir);
            ArrayList<Departamento> os = new ArrayList();
            o.setDepartamentos(os);
            System.out.println("¿Quiere introducir un departamento? (s/n)");
            char p;
            p = in.next().charAt(0);
            while (p != 's' && p != 'n') {
                System.out.println("Por favor, introduzca un caracter válido");
                p = in.next().charAt(0);
            } 
            while (p == 's') {
                o.departamentos.add(Departamento.nuevoDepartamento());
                System.out.println("¿Quiere introducir otro departamento? (s/n)");
                p = in.next().charAt(0);
            }
            System.out.println("¿El operario es senior? (s/n)");
            char r;
            r = in.next().charAt(0);
            while (r != 's' && r != 'n') {
                System.out.println("Por favor, introduzca un carácter válido");
                System.out.println("¿El operario es senior? (s/n)");
                r = in.next().charAt(0);}
            if (r == 's') {
                o.setSenior(true);
            } else {
                o.setSenior(false);
            }
            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Nombre: " + nom);
            System.out.println("Apellidos: " + ape);
            System.out.println("NIF: " + nnif);
            System.out.println("Teléfono: " + tel);
            System.out.println("Dirección: " + dir);
            if (r == 's') {
                System.out.println("Senior: si");
            } else {
                System.out.println("Senior: no");
            }
            c = in.next().charAt(0);

        } while (c != 's');
        return o;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    @Override
    public String toString() {
        return "Operario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", nif=" + nif + ", telefono=" + telefono + ", direccion=" + direccion + ", senior=" + senior + ", id=" + id + '}';
    }

    public String data() {
        return id + "|" + nombre + "|" + apellidos + "|" + nif + "|" + telefono + "|" + direccion + "|" + senior;
    }

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Operario getOperarioById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        Operario o = new Operario();
        return o;
    }

    public ArrayList<Operario> getAllOperario() {
        /*Este método devolverá un arrayList con todos los operarios existentes*/
        ArrayList<Operario> o = new ArrayList<>();
        return o;
    }

}
