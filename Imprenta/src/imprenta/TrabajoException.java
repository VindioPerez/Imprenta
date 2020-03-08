/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author vindi
 */
public class TrabajoException extends Exception{

    public TrabajoException() {
    }

    public TrabajoException(String message) {
        super(message);
    }
    
    public static boolean comprobarRelieve(String relieve){
        return !relieve.isEmpty();
    
    
    }
    
    public static boolean comprobarId(long id){
       
        return id>0;
    }
    
    public static boolean comprobarClienteVacion(Cliente c){
        
        return c != null;
        
    }
    
  
    /*public static boolean validarFecha(String fecha){
       
    *boolean correcta = true;
    *StringTokenizer tokens = new StringTokenizer(fecha,"/");
    *int pos = 0;
    *int dia = 0;
    *int mes = 0;
   w*hile(tokens.hasMoreTokens()){
        Capturamos el Token para Validar
        String token = tokens.nextToken();

        if(token == null || token.trim().length()<=0){
           correcta = false;
        }
        else
        {
          Caracter no númerico 
          if(Character.isLetter(token.charAt(0))){
                correcta = false;
          }
          else if (Integer.parseInt(token) <= 0){
              correcta = false;
          }
          else if (pos > 2){
              correcta = false;
          }
          else if (pos == 0){
              //Validando el DD no puede ser mayor de 31
              dia = Integer.parseInt(token);
              if (dia > 31)
                correcta = false;
          }
           else if (pos == 1){          
              //Validando el MM no puede ser mayor de 12
              mes = Integer.parseInt(token);
              if (mes > 12) {
                correcta = false;
              }
              else {
                //Validando el día en función del mes, solo comprobamos los meses con menos de 31 dias
                switch (mes){                 
                    case 2: // 29 dias
                        if (dia > 29)
                            correcta = false;
                        break;

                    case 4: // 30 dias
                    case 6: // 30 dias
                    case 9: // 30 dias
                    case 11: // 30 dias
                        if (dia > 30)
                            correcta = false;
                        break;

                }
              }
          }
          else if (pos == 2){
              //Validando el MM no puede ser mayor de 99
              if (Integer.parseInt(token) > 100)
                correcta = false;
          }
          pos = pos + 1;
        }
         Imprimir el Token
        System.out.println(token);
    }
   return correcta;
}*/
    
    
    public static boolean comprobarFechaRec(Date fechaRec, Date fechaSol){
        return fechaRec.after(fechaSol);
    }
    
    public static boolean comprobarFechaSol(Date fechaSol, Date fechaRec){
        return fechaSol.before(fechaRec);
    }
}
