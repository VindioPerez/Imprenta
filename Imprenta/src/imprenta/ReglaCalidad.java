/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.io.BufferedReader;
import java.io.EOFException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Modela una regla de calidad de tipo <code>ReglaCalidad</code>.
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class ReglaCalidad {

    private long idRegla;//id de la regla a la que se aplica
    private ArrayList<Prueba> pruebas; //ArrayList que contiene las pruebas de 0 a n 
    private String nombre;//Nombre de la regla
    private long idPolitica;//id de la politica que se aplica

    //constructores
    /**
     * Crea una instancia de <code>ReglaCalidad</code> con los valores por
     * defecto para los atributos
     */
    public ReglaCalidad() {
    }

    /**
     * Crea una instancia de <code>ReglaCalidad</code> relacionandolo con la
     * clase {@link Politica} mediante su id.
     *
     * @param idRegla id de la regla de tipo long.
     * @param nombre nombre de la regla de tipo long.
     * @param idPolitica id de la politica que se le aplica de tipo long.
     */
    public ReglaCalidad(long idRegla, String nombre, long idPolitica) {
        this.idRegla = idRegla;
        this.nombre = nombre;
        this.idPolitica = idPolitica;

    }

    //getters and setters
    public void setPruebas(ArrayList<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public ArrayList<Prueba> getPruebas() {
        return pruebas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(long idPolitica) {
        this.idPolitica = idPolitica;
    }

    public long getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(long idRegla) {
        this.idRegla = idRegla;
    }

    //tostring and data
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de regla de
     * calidad <code>ReglaCalidad</code> que llama al metodo.
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nombre</code> y  <code>id politica</code>
     */
    @Override
    public String toString() {
        return "ReglaCalidad:\n"
                + "ID regla:" + idRegla + "|Nombre:" + nombre + "|ID política:" + idPolitica;
    }

    public String data() {
        return "ReglaCalidad:\n"
                + "ID regla:" + this.getIdRegla() + "|Nombre:" + this.getNombre() + "|ID política:" + this.getIdPolitica();
    }

    //metodos propios
    public static ReglaCalidad nuevoRegla() {
        ReglaCalidad regla = new ReglaCalidad();
        boolean d;
        boolean c = true;
        do {
            ArrayList<Prueba> pruebas = new ArrayList();
            regla.setPruebas(pruebas);

            System.out.println("Quiere Introducir una nueva Prueba? s/n ");
            d = ToolBox.leerBoolean();

            while (d) {
                regla.pruebas.add(Prueba.nuevoPrueba());

                System.out.println("Quiere Introducir otra Prueba? s/n ");
                d = ToolBox.leerBoolean();
            }
            // preguntar si los datos son correctos en caso de añadir nuevos atributos

        } while (c);
        return regla;
    }

    //lectura y escritura 
    /**
     * Importa un grupo de reglas de calidad <code>ReglaCalidad</code> desde un
     * fichero de texto.
     *
     * @param path la ruta del fichero a importar de tipo String.
     * @return un <code>ArrayList</code> con todos las Reglas de calidad
     * existentes en el fichero.
     */
    public static ArrayList<ReglaCalidad> FromTextFile(String path) {
        ArrayList<ReglaCalidad> ret = new ArrayList<>();
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
                    long id = Long.parseLong(campos[0]);
                    String nombre = campos[1];
                    long idPolitica = Long.parseLong(campos[2]);
                    ReglaCalidad r = new ReglaCalidad(id, nombre, idPolitica);
                    ret.add(r);
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
            System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception" + e.getMessage());
        }
        return ret;
    }

    /**
     * Importa un grupo de Reglas de calidad desde un fichero binario.
     *
     * @param path la ruta del fichero a importar de tipo String. 
     * @return un <code>ArrayList</code> con todas las Reglas de calidad
     * existentes en el fichero. 
     */
    public static ArrayList<ReglaCalidad> FromBinaryFile(String path) {
        ArrayList<ReglaCalidad> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                ReglaCalidad r;
                while ((r = (ReglaCalidad) lectorObjeto.readObject()) != null) {
                    ret.add(r);
                    lector.skip(4);
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
            System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("Final de fichero");
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido una ClassNotFoundException" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception" + e.getMessage());
        }
        return ret;
    }

    /**
     * Exporta los datos de un <code>Politica</code> a un fichero de texto, a
     * traves del metodo <code>Data</code> introduciendo al final un retorno de
     * carro.
     *
     * @param path la ruta del fichero al que exportar los datos del objeto de tipo String. 
     * @see ReglaCalidad.data() data
     */
    
    public void toTextFile(String path) {
        File fichero = new File(path);
        FileWriter escritor = null;
        PrintWriter buffer = null;
        try {
            try {
                escritor = new FileWriter(fichero, true);
                buffer = new PrintWriter(escritor);
                buffer.print(this.data() + "\r\n");
            } finally {
                if (buffer != null) {
                    buffer.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception" + e.getMessage());
        }
    }

        /**
     * Exporta una <code>ReglaCalidad</code> a un fichero binario.
     *
     * @param path la ruta del fichero al que exportar de tipo String.
     */
    
    public void toBinaryFile(String path) {
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
}
