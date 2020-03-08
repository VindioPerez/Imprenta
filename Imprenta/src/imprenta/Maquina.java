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
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

/**
 * Modela la maquina (impresora) que realiza un {@link Trabajo}
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Maquina {

    protected long id;//identificador
    private Date fechaCompra;//fecha de compra
    private String ubicacion;//localización
    private String imprTipo;//tipo de impresión
    private String imprModo;// modo de impresión 
    private float volTinta;//volumen ACTUAL de tinta - Valores [0,capMax]
    private float capMax;//capacidad máxima de tinta - Valor unico nº
    private boolean disponible = true;

    /**
     * Crea una nueva instancia de la clase <code>Maquina</code> pidiendo al
     * usuario por pantalla que introduzca los datos
     *
     * @return el <code>Maquina</code> que se crea con el método
     */
    public static Maquina nuevoMaquina() {
        Maquina m = new Maquina();

        Scanner in = new Scanner(System.in);
        Boolean c;
        do {
            System.out.println("Introduzca la ubicación");
            String ubi = in.nextLine();
            m.setUbicacion(ubi);
            System.out.println("Introduzca el tipo de impresión");
            String tipo = in.nextLine();
            m.setImprTipo(tipo);
            System.out.println("Introduzca el modo de impresión");
            String modo = in.nextLine();
            m.setImprModo(modo);
            System.out.println("Introduzca el volumen de tinta");
            float dirf = in.nextFloat();
            m.setVolTinta(dirf);
            System.out.println("Introduzca la capacidad máxima de la máquina");
            float capf = in.nextFloat();
            m.setCapMax(capf);
            System.out.println("Introduzca la fecha de inicio");
            Date fecha = ToolBox.introducirFecha();
            m.setFechaCompra(fecha);

            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Ubicación: " + ubi);
            System.out.println("Tipo de Impresión: " + tipo);
            System.out.println("Modo de Impresión: " + modo);
            System.out.println("Volumend de Tinta: " + dirf);
            System.out.println("Capacidad Máxima de Tinta: " + capf);
            System.out.println("Fecha de compra: " + fecha);
            c = ToolBox.leerBoolean();

        } while (c = false);

        return m;
    }
    // getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImprTipo() {
        return imprTipo;
    }

    public void setImprTipo(String imprTipo) {
        this.imprTipo = imprTipo;
    }

    public String getImprModo() {
        return imprModo;
    }

    public void setImprModo(String imprModo) {
        this.imprModo = imprModo;
    }

    public float getVolTinta() {
        return volTinta;
    }

    public void setVolTinta(float volTinta) {
        this.volTinta = volTinta;
    }

    public float getCapMax() {
        return capMax;
    }

    public void setCapMax(float capMax) {
        this.capMax = capMax;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    //constructor con argumentos
    /**
     * Crea una instancia de Maquina con
     *
     * @param id el id de la Maquina
     * @param fechaCompra la fecha en la que se compra la maquina
     * @param ubicacion la ubicacion en la que se encuentra la Maquina
     * @param imprTipo el tipo de impresion de la maquina
     * @param imprModo el modo de impresion de la maquina
     * @param volTinta el volumen de tinta de la maquina
     * @param capMax la capacidad maxima de tinta de la maquina
     */
    public Maquina(long id, Date fechaCompra, String ubicacion, String imprTipo, String imprModo, float volTinta, float capMax) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.ubicacion = ubicacion;
        this.imprTipo = imprTipo;
        this.imprModo = imprModo;
        this.volTinta = volTinta;
        this.capMax = capMax;
    }

    //constructor por defecto
    /**
     * Crea una instancia de Maquina con los valores por defecto para los
     * atributos
     */
    public Maquina() {
    }

    //Constructor de copia
    /**
     * Realiza una copia a partir de una Maquina existente
     *
     * @param q la maquina que se va a copiar
     */
    public Maquina(Maquina q) {
        this.fechaCompra = q.getFechaCompra();
        this.ubicacion = q.getUbicacion();
        this.imprTipo = q.getImprTipo();
        this.imprModo = q.getImprModo();
        this.volTinta = q.getVolTinta();
        this.capMax = q.getCapMax();
    }

    // metodos de lectura y escritura
    /**
     * Importa un grupo de maquinas desde un archivo de texto
     *
     * @param path la ruta del archivo a importar
     * @return un <code>ArrayList</code> con todas las maquinas existentes en el
     * fichero
     * construir un <code>Maquina</code>
     * @throws FileNotFoundException si no se puede acceder al fichero con la
     * ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Maquina> readMaquinaFromTextFile(String path) {
        ArrayList<Maquina> ret = new ArrayList<>();
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
                    Date fechaCompra = new Date(campos[1]);
                    String ubicacion = campos[2];
                    String imprTipo = campos[3];
                    String imprModo = campos[4];
                    float volTinta = Float.parseFloat(campos[5]);
                    float capMax = Float.parseFloat(campos[6]);

                    Maquina maq = new Maquina(id, fechaCompra, ubicacion, imprTipo, imprModo, volTinta, capMax);
                    ret.add(maq);
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
     * Importa un grupo de maquinas desde un fichero binario
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos las maquinas existentes en el
     * fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la
     * ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el
     * objeto
     */
    public static ArrayList<Maquina> readMaquinaFromBinaryFile(String path) {
        ArrayList<Maquina> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Maquina maq;
                while ((maq = (Maquina) lectorObjeto.readObject()) != null) {
                    ret.add(maq);
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
     * Exporta los datos de una <code>Maquina</code> a un fichero de texto, a
     * traves del metodo <code>Data</code> introduciendo al final un retorno de
     * carro
     *
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la
     * ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Maquina.data() data
     */
    public void writeMaquinaToTextFile(String path) {
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
     * Exporta un <code>Maquina</code> a un fichero binario
     *
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la
     * ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeMaquinaToBinaryFile(String path) {
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

    //getAll, getById, data y toString
    /**
     * Devuelve un <code>String</code> con los atributos formateados para
     * exportar a un fichero de texto
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>fechaCompra</code>, <code>ubicacion</code>,
     * <code>imprTipo</code>, <code> imprModo </code>, <code> volTinta</code>, <code>capMax</code>,  separados por una barra vertical
     */
    // El orden de los campos será el siguiente: id-fechaCompra-ubicacion-imprTipo-imprModo-volTinta-capMax
    public String data() {
        return id + "|" + fechaCompra + "|" + ubicacion + "|" + imprTipo + "|" + imprModo + "|" + volTinta + "|" + capMax;
    }

    /**
     * Devuelve un <code>String</code> con los datos de la instancia de maquina
     * que llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>fechaCompra</code>, <code>ubicacion</code>,
     * <code>imprTipo</code>, <code> imprModo </code>, <code> volTinta</code>, <code>capMax
     * </code>, separados por una barra vertical
     */
    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", fechaCompra=" + fechaCompra + ", loc=" + ubicacion + ", imprTipo=" + imprTipo + ", imprModo=" + imprModo + ", volTinta=" + volTinta + ", capMax=" + capMax + '}';
    }

    /**
     * Cambia el estado de una maquina a no disponible
     *
     * @param m la maquina cuyo estado se va a modificar
     */
    public static void noDisponible(Maquina m) {
        m.setDisponible(false);
    }

    /**
     *
     * @param idMaquina el id de la maquina que se quiere buscar en la base de
     * datos
     * @return
     */
    public static Maquina getMaquinaById(long idMaquina) {

        Maquina temp = null;
        for (Maquina m : BDatos.maquinas) {
            if (m.getId() == idMaquina) {
                temp = m;
            }
        }
        return temp;
    }

}
