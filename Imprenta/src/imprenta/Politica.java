/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Alberto
 * @version1
 */
public class Politica {

    protected long id;
    private String nom;//nombre de la política
    private Date fechaIni; //fecha de inicio de la politica
    private long idOCalidad1;// primera firma
    private long idOCalidad2;// segunda firma 
    private Lote lote;

    public Politica(String nom, Date fechaIni, long firma1, long firma2, Lote lote) {
        this.nom = nom;
        this.fechaIni = fechaIni;
        this.idOCalidad1 = firma1;
        this.idOCalidad2 = firma2;
        this.lote = lote;
    }

    public Politica() {
    }

    public Politica(Politica p) {
        this.nom = p.getNom();
        this.fechaIni = p.getFechaIni();
        this.idOCalidad1 = p.getIdOCalidad1();
        this.idOCalidad2 = p.getIdOCalidad2();
        this.lote = p.getLote();
    }
    
    public static Politica nuevaPolitica() throws ParseException {
        Politica p = new Politica();
        Scanner in = new Scanner(System.in);
        boolean c;
        do {
            System.out.println("Introduzca el nombre");
            String nom = in.nextLine();
            p.setNom(nom);
            System.out.println("Introduzca la fecha de inicio");
            Date fin = ToolBox.introducirFecha();
            p.setFechaIni(fin);
            System.out.println("Introduzca el id del primer operario");
            long id1 = in.nextLong();
            p.setIdOCalidad1(id1);
            System.out.println("Introduzca el id del segundo operario");
            long id2 = in.nextLong();
            p.setIdOCalidad2(id2);
            System.out.println("Introduzca el lote");
            Lote l = Lote.nuevoLote();
            p.setLote(l);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Nombre: " + nom);
            System.out.println("Fecha de inicio: " + fin);
            System.out.println("Id del primer operario: " + id1);
            System.out.println("Id del segundo operario: " + id2);
            System.out.println("Lote: " + l.toString());
            c = ToolBox.leerBoolean();
        } while (c = false);
        return p;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
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

    public long getIdOCalidad1() {
        return idOCalidad1;
    }

    public void setIdOCalidad1(long idOCalidad1) {
        this.idOCalidad1 = idOCalidad1;
    }

    public long getIdOCalidad2() {
        return idOCalidad2;
    }

    public void setIdOCalidad2(long idOCalidad2) {
        this.idOCalidad2 = idOCalidad2;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
    
    

    public String data() {
        return id + "|" + nom + "|" + fechaIni;
    }

    @Override
    public String toString() {
        return "PoliticaCalidad{" + "nom=" + nom + ", fechaIni=" + fechaIni + '}';
    }
    
    public Politica getPoliticaoById(long id) {
        /*Este método recorrerá un ArrayList con todas las politicas buscando aquella con el id que le introduzcamos, y devolverá esa politica si es que existe o 
        nulo si es que no existe*/
        Politica l = new Politica();
        return l;
    }

    public ArrayList<Politica> getAllPolitica() {
        /*Este método devolverá un arrayList con todas las politicas existentes*/
        ArrayList<Politica> o = new ArrayList<>();
        return o;
    }
}
