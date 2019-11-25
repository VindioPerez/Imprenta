package imprenta;

import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
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
public class ToolBox {
    

    public static Date introducirFecha() throws ParseException{
    
        System.out.println("Introduzca la fecha en format dd/mm/aaaa");
        Scanner sc = new Scanner(System.in);
        String fechaTexto = sc.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = df.parse(fechaTexto);
        
        return fecha;
    }
    
    public static boolean leerBoolean(){
        boolean check = false;
        char r;
        Scanner in = new Scanner(System.in);
        r = in.next().charAt(0);
        while(r !='s'&& r!= 'S' && r!='N' && r !='n'){
            System.out.println("Por favor, introduzca un carácter válido (s/n)");
            r = in.next().charAt(0);
        }
        if((r == 's') || (r == 'S')){
            check = true;
            }else if((r == 'n') || (r == 'N')){
            check = false;
        
        }
        return check;
        
    }
}
