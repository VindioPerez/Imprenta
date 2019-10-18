package imprenta;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DAW108
 */
public class Libro extends Trabajo {

    private int numPag;//variable tipo int que recoge el número de páginas que tiene el libro;
    private String color; //variable tipo String que recoge el color del libro

    public Libro() {
    }

    public Libro(int numPag, String color, int id, Date fechaSolicitud, Date fechaRecogida, String relieve) {
        super(id, fechaSolicitud, fechaRecogida, relieve);
        this.numPag = numPag;
        this.color = color;
    }

    public Libro(Libro l) {
        this.color = l.getColor();
        this.numPag = l.getNumPag();
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Libro{" + "numPag=" + numPag + ", color=" + color + '}';
    }

    public String data() {

        return getId() + "|" + getFechaSolicitud() + "|" + getRelieve() + getFechaRecogida() + "|" + color + "|" + numPag;
    }

}
