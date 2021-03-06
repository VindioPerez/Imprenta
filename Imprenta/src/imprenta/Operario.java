/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Modela el tipo de operario que realiza una diversa tarea. Tiene 3 clases
 * hijas, {@link OCaldiad}, {@link OMaquinas} y {@link OImpresion}
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
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
    //variable identificador del operario
    protected ArrayList<Departamento> departamentos;

    //constructor por defecto
    /**
     * Crea una instancia de Operario con los valores por defecto para los
     * atributos
     */
    public Operario() {
    }

    //constructor por argumentos
    /**
     * Crea una instancia de Operario con los atributos propios de la clase, sin
     * los de de las relaciones ni el id
     *
     * @param nombre
     * @param apellidos
     * @param nif
     * @param telefono
     * @param direccion
     * @param senior
     */
    public Operario(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.telefono = telefono;
        this.direccion = direccion;
        this.senior = senior;
    }

    //constructor de copia
    /**
     * Crea una instancia de Operario a partir de otra, copiando cada atributo
     *
     * @param o el Opearario que se va a copiar
     */
    public Operario(Operario o) {
        this.nombre = o.getNombre();
        this.apellidos = o.getApellidos();
        this.nif = o.getNif();
        this.telefono = o.getTelefono();
        this.direccion = o.getDireccion();
        this.senior = o.isSenior();
    }

    /**
     *Crea una instancia de Operario asignando los atributos por teclado.
     * @return devuelve un nuevo objeto de Operario
     */

    public static Operario nuevoOperario() {
        Operario o = new Operario();
        Scanner in = new Scanner(System.in);
        boolean c;
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
            System.out.println("¿Quiere introducir un departamento? (s/n)");
            boolean p;
            p = ToolBox.leerBoolean();
            while (p) {
                o.departamentos.add(Departamento.nuevoDepartamento());
                System.out.println("¿Quiere introducir otro departamento? (s/n)");
                p = ToolBox.leerBoolean();
            }
            o.setDepartamentos(os);
            System.out.println("¿El operario es senior? (s/n)");
            boolean r;
            r = ToolBox.leerBoolean();
            if (r) {
                o.setSenior(true);
            } else {
                o.setSenior(false);
            }
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Nombre: " + nom);
            System.out.println("Apellidos: " + ape);
            System.out.println("NIF: " + nnif);
            System.out.println("Teléfono: " + tel);
            System.out.println("Dirección: " + dir);
            if (r) {
                System.out.println("Senior: si");
            } else {
                System.out.println("Senior: no");
            }
            c = ToolBox.leerBoolean();

        } while (!c);
        return o;
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

    public long getId() {
        return id;
    }

    //getById,getAll,data y toString
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de operario
     * que llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nombre</code>, <code>apellidos</code>,
     * <code>nif</code>,<code>telefono</code>,<code>dirección</code> y
     * <code>senior</code>,
     */
    @Override
    public String toString() {
        return "Operario:\n"
                + "|id=" + id + "|nombre=" + nombre + "| apellidos=" + apellidos + "| nif=" + nif + "| telefono=" + telefono + "| direccion=" + direccion + "| senior=" + senior + '}';
    }

    public String data() {
        return "Operario:\n"
                + "ID:" + this.getId() + "|Nombre:" + this.getNombre() + "|Apellidos_" + this.getApellidos() + "|NIF:" + this.getNif() + "|Teléfono:" + this.getTelefono() + "|Dirección:" + this.getDireccion() + "|Senior:" + this.isSenior();
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

    /**
     * Asigna un id al trabajo perteneciente al Operario que lo confirma. 
     * @param t Trabajo que se pasa para saber si es confirmado. 
     * @throws TrabajoException cuando uno de los argumentos de Trabajo es incorrecto. 
     */
    public void confirmar(Trabajo t) throws TrabajoException {
        t.setIdOperario(this.getId());
    }
}
