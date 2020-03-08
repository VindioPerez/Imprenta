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

/**
 * Modela un {@link Operario} de tipo Operario de Calidad
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class OCalidad extends Operario {

    //constructores
    //constructor por defecto
    /**
     * Crea una instancia de Operario Calidad con los valores por defecto para
     * los atributos
     */
    public OCalidad() {
    }

    /**
     * Crea una instancia de Operario de Calidad con los atributos propios de la
     * clase, sin los de de las relaciones ni el id
     *
     * @param nombre
     * @param apellidos
     * @param nif
     * @param telefono
     * @param direccion
     * @param senior
     */
    public OCalidad(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }

    /**
     * Crea una instancia de Operario de Calidad creando la relación con
     * {@link Politica} mediante el atributo id.
     *
     * @param id
     * @param nombre
     * @param apellidos
     * @param nif
     * @param telefono
     * @param direccion
     * @param senior
     */
    public OCalidad(long id, String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
    }

    /**
     * Crea una instancia de Operario de Calidad a partir de los atributos de
     * Operario de Calidad.
     *
     * @param o el Operario de Calidad que se va a copiar
     */
    public OCalidad(OCalidad o) {
        super(o);

    }

    /**
     * Crea una instancia de Operario de Calidad a partir de los atributos de
     * Operario.
     *
     * @param o el Operario que se va a copiar
     */
    public OCalidad(Operario o) {
        super(o);
    }

    //toString y Data
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Operario
     * de Calidad que llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nombre</code>, <code>apellidos</code>,
     * <code>nif</code>,<code>telefono</code>,<code>dirección</code> y
     * <code>senior</code>,
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String data() {
        return super.data();
    }

    //getter
    public OCalidad getOperarioCalidadById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OCalidad oc = new OCalidad();
        return oc;
    }

    //metodo propio
    public static OCalidad nuevoOCalidad() {

        OCalidad oc = new OCalidad(Operario.nuevoOperario());

        return oc;
    }

    //lectura y escritura
    /**
     * Importa un grupo de operarios de calidad desde un fichero de texto
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los Operarios de calidad
     * existentes en el fichero
     */
    public static ArrayList<OCalidad> fromTextFile(String path) {
        ArrayList<OCalidad> ret = new ArrayList<>();
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
                    String apellido = campos[2];
                    String nif = campos[3];
                    String telefono = campos[4];
                    String direccion = campos[5];
                    Boolean senior = Boolean.parseBoolean(campos[6]);

                    OCalidad oc = new OCalidad(id, nombre, apellido, nif, telefono, direccion, senior);
                    ret.add(oc);
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

    /**
     * Importa un grupo de Operarios de Calidad desde un fichero binario.
     *
     * @param path la ruta del fichero a importar.
     * @return un <code>ArrayList</code> con todos los Operarios de Calidad
     * existentes en el fichero
     */
    public static ArrayList<OCalidad> fromBinaryFile(String path) {
        ArrayList<OCalidad> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                OCalidad oc;
                while ((oc = (OCalidad) lectorObjeto.readObject()) != null) {
                    ret.add(oc);
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

    /**
     * Exporta los datos de un <code>OCalidad</code> a un fichero de texto, a
     * traves del metodo <code>Data</code> introduciendo al final un retorno de
     * carro
     *
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @see OCalidad.data() data
     */
    public void toTextFile(String path) {
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

    /**
     * Exporta un <code>Operario de Calidad</code> a un fichero binario.
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
