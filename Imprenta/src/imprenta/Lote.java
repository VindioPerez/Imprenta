/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;

/**
 *
 * @author Alberto
 * @version1
 */
public class Lote {

    private String nom; // nombre del lote
    private char depart; //nombre del departamento asignado Valores[A,B,C]
    private ArrayList<Maquina> maquinas; // array de maquinadas asignadas al lote
    protected long id;//variable identificador
    private Departamento departamento;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getDepart() {
        return depart;
    }

    public void setDepart(char depart) {
        this.depart = depart;
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
    public Lote(String nom, char depart, ArrayList<Maquina> maquinas) {
        this.nom = nom;
        this.depart = depart;
        this.maquinas = maquinas;
    }

    //constructor por defecto
    public Lote() {
    }

    //constructor de copia
    public Lote(Lote l) {
        this.nom = l.getNom();
        this.depart = l.getDepart();
        this.maquinas = l.maquinas;
    }

    public String data() {
        return id + "|" + nom + "|" + depart + "|" + maquinas;

    }

    @Override
    public String toString() {
        return "Lote{" + "nom=" + nom + ", depart=" + depart + ", listaMaq=" + maquinas + ", id=" + id + '}';
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    
}
