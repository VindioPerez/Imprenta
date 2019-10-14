package imprenta;

import java.util.Date;

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
public class Poster extends Trabajo {
    private int alto;//variable tipo integer que recoge el alton del poster, tiene que ser >0
    private int ancho;//variable tipo integer que recoge el ancho del poster, tiene que ser >0
    private int numCopias;//variable tipo integer que recoge el número de copias de los posters solicitados,tiene que ser >0

    public Poster(){}

    public Poster(int alto, int ancho, int numCopias, int id, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(id, fechaSolicitud, fechaRecogida, relieve);
        this.alto = alto;
        this.ancho = ancho;
        this.numCopias = numCopias;
    }


    

    
    public Poster(Poster p){
        this.alto=p.getAlto();
        this.ancho=p.getAncho();
        this.numCopias=p.getNumCopias();
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    @Override
    public String toString() {
        return "Poster{" + "alto=" + alto + ", ancho=" + ancho + ", numCopias=" + numCopias + '}';
    }
    
    public String data(){
        return getId() + "|" + getFechaSolicitud() +getRelieve() +  "|" + getFechaRecogida() + "|" +this.getAlto()+ "|" + this.getAncho() + "|" + this.numCopias;
    }
    
    
    
}
 