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
public class Departamento {

    private char letraDep;//Los departamentos se identifican con un carácter, valores posible: 'A', 'B' o 'C'
    protected long id;// variable identificador
    private ArrayList<Operario> operarios;//lista de operarios del Departamento

    public Departamento() {
    }

    public Departamento(char letraDep) {
        this.letraDep = letraDep;
    }

    public Departamento(Departamento d) {
        this.letraDep = d.getLetraDep();
    }

    public char getLetraDep() {
        return letraDep;
    }

    public void setLetraDep(char letraDep) {
        this.letraDep = letraDep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Departamento{" + "letraDep=" + letraDep + ", id=" + id + '}';
    }

    public String data() {
        return id + "|" + Character.toString(letraDep);
    }

    public ArrayList<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(ArrayList<Operario> operarios) {
        this.operarios = operarios;
    }

    public static Departamento nuevoDepartamento() {
        Departamento dep = new Departamento();
        Scanner in = new Scanner(System.in);
        char c;
        char d;
        do {
            System.out.println("Introduzca la letra del departamento");
            char l = 0;
            l = in.next().charAt(l);
            dep.setLetraDep(l);

            System.out.println("Quiere Introducir un nuevo Operario? s/n ");
            d = in.next().charAt(0);
            ArrayList<Operario> operarios = new ArrayList<>();
            while (d == 's') {
                          
            
            Operario o = Operario.nuevoOperario();
            operarios.add(o);
            
                System.out.println("Quiere Introducir otro Operario? s/n ");

                d = in.next().charAt(0);
            }

            do {

                System.out.println("¿Son correctos estos datos? (introduzca una v si lo son)");
                System.out.println("letraDep: " + l);
                System.out.println("Operarios: " );

                c = in.next().charAt(0);

            } while (c != 'v' || c != 'V');
            return dep;
        }
    

    }
    


