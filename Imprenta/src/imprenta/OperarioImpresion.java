/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author VindioPerez
 * @version 1.0
 */
public class OperarioImpresion {
    private Operario operario;
    //El unico atributo de la clase es un objeto de la superclase Operario

    public OperarioImpresion() {
    }

    public OperarioImpresion(Operario operario) {
        this.operario = operario;
    }
    
    public OperarioImpresion (OperarioImpresion o) {
        this.operario = o.getOperario();
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    @Override
    public String toString() {
        return "OperarioImpresion{" + "operario=" + operario + '}';
    }
    
    public String data() {
        return operario.data() ; 
    }
}

