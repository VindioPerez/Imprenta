/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author VindioPerez
 * @version 1.0
 */
public class Departamento {

    private char letraDep;//Los departamentos se identifican con un carácter, valores posible: 'A', 'B' o 'C'
    protected long id;// variable identificador
    private ArrayList<Operario> operarios;//lista de operarios del Departamento

    public Departamento() {
    }

    public Departamento(char letraDep) {
        this.letraDep = letraDep;
    }
    
    public Departamento(char letraDep, long id) {
        this.letraDep = letraDep;
        this.id = id;
    }

    public Departamento(Departamento d) {
        this.letraDep = d.getLetraDep();
    }

    public char getLetraDep() {
        return letraDep;
    }

    public void setLetraDep(char letraDep) {
        this.letraDep = letraDep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Departamento{" + "letraDep=" + letraDep + ", id=" + id + '}';
    }
    
    // El orden de los campos es el siguiente: id-letraDep
    public String data() {
        return id + "|" + Character.toString(letraDep);
    }

    public ArrayList<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(ArrayList<Operario> operarios) {
        this.operarios = operarios;
    }
    
    public static ArrayList<Departamento> readDepartamentoFromTextFile(String path) {
        ArrayList<Departamento> ret = new ArrayList<>();
        File fichero = new File(path);
        FileReader lector = null;
        BufferedReader buffer = null;
        try {
            try {
                lector = new FileReader(fichero);
                buffer = new BufferedReader(lector);
                String linea;
                while ((linea = buffer.readLine()) != null) {
                    String[] campos = linea.split("\\|");
                    char letraDep = campos[1].charAt(0);
                    long id = Long.parseLong(campos[0]);
                    Departamento  dep = new Departamento( letraDep, id);
                    ret.add(dep);
                }
            } finally {
                if (buffer != null) {
                    buffer.close();
                }
                if (lector != null) {
                    lector.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException");
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception");
        }
        return ret;
    }

    public static ArrayList<Departamento> readDepartamentoFromBinaryFile(String path) {
        ArrayList<Departamento> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Departamento dep;
                while ((dep = (Departamento) lectorObjeto.readObject()) != null) {
                    ret.add(dep);
                }
            } finally {
                if (lectorObjeto != null) {
                    lectorObjeto.close();
                }
                if (lector != null) {
                    lector.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido una ClassNotFoundException");
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception");
        }
        return ret;
    }

    public void writeDepartamentoToTextFile(String path) {
        File fichero = new File(path);
        FileWriter escritor = null;
        PrintWriter buffer = null;
        try {
            try {
                escritor = new FileWriter(fichero);
                buffer = new PrintWriter(escritor);
                buffer.println(this.data() + "\r\n");
            } finally {
                if (buffer != null) {
                    buffer.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException");
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception");
        }
    }

    public void writeDepartamentoToBinaryFile(String path) {
        try {
            FileOutputStream fichero = new FileOutputStream(path, true);
            try (ObjectOutputStream escritor = new ObjectOutputStream(fichero)) {
                escritor.writeObject(this);
                escritor.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception" + e.getMessage());
        }
    }
   
    public static Departamento nuevoDepartamento() {
        Departamento dep = new Departamento();
        Scanner in = new Scanner(System.in);
        Boolean c;
        char d;
        do {
            System.out.println("Introduzca la letra del departamento");
            char l = 0;
            l = in.next().charAt(l);
            dep.setLetraDep(l);
            
            ArrayList<Operario> os = new ArrayList();
            dep.setOperarios(os);
            
            System.out.println("Quiere Introducir un nuevo Operario? s/n ");
            d = in.next().charAt(0);
            

            while (d == 's') {
                dep.operarios.add(Operario.nuevoOperario());

                System.out.println("Quiere Introducir otro Operario? s/n ");
                d = in.next().charAt(0);
            }

            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("letraDep: " + l);
            

            c = ToolBox.leerBoolean();
        } while (c= false);
        return dep;
    }

}
