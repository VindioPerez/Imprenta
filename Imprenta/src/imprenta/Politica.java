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
public class Politica {
    
    protected long id;
    private String nom ;//nombre de la política
    private Date fechaIni ; //fecha de inicio de la politica
    private long idOCalidad1  ;// primera firma
    private long idOCalidad2  ;// segunda firma 
    private Lote lote;
    
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





    public Politica(String nom, Date fechaIni, OCalidad firma1, OCalidad firma2) {
        this.nom = nom;
        this.fechaIni = fechaIni;
    }

    //constructor por defecto
    public Politica() {
    }
    //constructor de copia
    public Politica(Politica p) {
        this.nom = p.getNom();
        this.fechaIni = p.getFechaIni();

            
    }
    
    public String data() { 
     return id + "|" + nom + "|" + fechaIni;
            }
    
    @Override
    public String toString() {
        return "PoliticaCalidad{" + "nom=" + nom + ", fechaIni=" + fechaIni  + '}';
    }
}








