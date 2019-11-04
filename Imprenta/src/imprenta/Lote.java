/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author Alberto
 * @version1
 */
public class Lote {

    private String nom; // nombre del lote
    private char depart; //nombre del departamento asignado Valores[A,B,C]
    private Maquina[] listaMaq; // array de maquinadas asignadas al lote
    public long id;//variable identificador
    
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

    public Maquina[] getListaMaq() {
        return listaMaq;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public void setListaMaq(Maquina[] listaMaq) {
        this.listaMaq = listaMaq;
    }

    //constructor con argumentos
    public Lote(String nom, char depart, Maquina[] listaMaq) {
        this.nom = nom;
        this.depart = depart;
        this.listaMaq = listaMaq;
    }

    //constructor por defecto
    public Lote() {
    }

    //constructor de copia
    public Lote(Lote l) {
        this.nom = l.getNom();
        this.depart = l.getDepart();
        this.listaMaq = l.listaMaq;
    }

    public String data() {
        return id + "|" + nom + "|" + depart + "|" + listaMaq;

    }

    @Override
    public String toString() {
        return "Lote{" + "nom=" + nom + ", depart=" + depart + ", listaMaq=" + listaMaq + ", id=" + id + '}';
    }

    

}
