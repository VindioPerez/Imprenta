/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;
import java.util.Date;
/**
 *
 * @author Alberto
 * @version1
 */
public class PoliticaCalidad {
    
    private long id;
    private String nom ;//nombre de la política
    private Date fechaIni ; //fecha de inicio de la politica
    private OperarioCalidad firma1 ;// primera firma
    private OperarioCalidad firma2 ;// segunda firma 

    public String getNom() {
        return nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
     
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public OperarioCalidad getFirma1() {
        return firma1;
    }

    public void setFirma1(OperarioCalidad firma1) {
        this.firma1 = firma1;
    }

    public OperarioCalidad getFirma2() {
        return firma2;
    }

    public void setFirma2(OperarioCalidad firma2) {
        this.firma2 = firma2;
    }



    public PoliticaCalidad(String nom, Date fechaIni, OperarioCalidad firma1, OperarioCalidad firma2) {
        this.nom = nom;
        this.fechaIni = fechaIni;
        this.firma1 = firma1;
        this.firma2 = firma2;
    }

    //constructor por defecto
    public PoliticaCalidad() {
    }
    //constructor de copia
    public PoliticaCalidad(PoliticaCalidad p) {
        this.nom = p.getNom();
        this.fechaIni = p.getFechaIni();
        this.firma1 = p.getFirma1();
        this.firma2 = p.getFirma2();    
            
    }
    
    public String data() { 
     return id + "|" + nom + "|" + fechaIni + "|" + firma1 +"|" + firma2 ;
            }
    
    @Override
    public String toString() {
        return "PoliticaCalidad{" + "nom=" + nom + ", fechaIni=" + fechaIni + ", firma1=" + firma1 + ", firma2=" + firma2 + '}';
    }
}








