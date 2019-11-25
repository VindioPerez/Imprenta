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
 * @author Alberto
 * @version1
 */
public class Lote {

    private String nom; // nombre del lote
    private ArrayList<Maquina> maquinas; // array de maquinadas asignadas al lote
    protected long id;//variable identificador
    private Departamento departamento;

    public static Lote nuevoLote() throws ParseException {
        Lote l = new Lote();

        Scanner in = new Scanner(System.in);
        Boolean c;
        char d;
        do {
            System.out.println("Introduzca el departamento");
           Departamento dep =  Departamento.nuevoDepartamento();
            l.setDepartamento(dep);
            System.out.println("Introduzca el nombre del lote");
            String nom = in.nextLine();
            l.setNom(nom);
            
            
            System.out.println("Quiere Introducir una nueva Maquina? s/n ");
            d = in.next().charAt(0);

            while (d == 's') {
                l.maquinas.add(Maquina.nuevoMaquina());

                System.out.println("Quiere Introducir otra Maquina? s/n ");
                d = in.next().charAt(0);
            }

            System.out.println("¿Son correctos estos datos? (introduzca una v si lo son)");
            System.out.println("Nombre Lote: " +nom );

            c = ToolBox.leerBoolean();

        } while (c=false);

        return l;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   

    public long getId() {
        return id;
    }

    /*
    public void setId(long id) {
        this.id = id;
    }
     */
    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(ArrayList<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    //constructor con argumentos
    public Lote(String nom, ArrayList<Maquina> maquinas) {
        this.nom = nom;
        this.maquinas = maquinas;
    }

    //constructor por defecto
    public Lote() {
    }

    //constructor de copia
    public Lote(Lote l) {
        this.nom = l.getNom();
        this.maquinas = l.maquinas;
    }

    public String data() {
        return id + "|" + nom +  "|" + maquinas;

    }

    @Override
    public String toString() {
        return "Lote{" + "nom=" + nom +  ", listaMaq=" + maquinas + ", id=" + id + '}';
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
