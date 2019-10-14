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
public class OperarioCalidad {
    
    private Operario operario;
    
    public OperarioCalidad() {
    }

    public OperarioCalidad(Operario operario) {
        this.operario = operario;
    }
    
    public OperarioCalidad (OperarioCalidad o) {
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
        return "OperarioCalidad{" + "operario=" + operario + '}';
    }
    
    public String data() {
        return operario.data() ; 
    }
}
