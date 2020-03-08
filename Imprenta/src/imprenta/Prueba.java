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
import java.util.Scanner;

/**
 * Modela una Prueba.
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Prueba {

    protected long id; //id de la máquina de tipo long
    private Date fechaR;// tipo date fecha de realización
    private OCalidad operarioC;// Operario de calidad que sigue la prueba
    private long idOperarioC;//clave ajena del Operario de Calidad
    private String desc;//descripción de la prueba
    private String res;// resultados obtenidos
    private String obs;//observaciones
    private Maquina maquina;//máquina que realiza la prueba
    private long idMaquina;//id de la maquina que realiza la prueba
    private long idRegla;//ID de la regla que se aplica

    // Constructor por defecto
    /**
     * Crea una instancia de Calidad con los valores por defecto para los
     * atributos
     */
    public Prueba() {
    }

    // Constructor con argumentos
    /**
     * Crea una instancia de Prueba con los atributos propios de la clase, sin
     * los de de las relaciones ni el id
     *
     *
     * @param fechaR fecha de realización de tipo Date.
     * @param operario objeto de tipo Operario.
     * @param desc descripción de la prueba a realizar de tipo String
     * @param res resultado de la prueba de tipo String
     * @param obs observaciones de la prueba de tipo String
     */
    public Prueba(Date fechaR, OCalidad operario, String desc, String res, String obs) {

        this.fechaR = fechaR;
        this.operarioC = operario;
        this.desc = desc;
        this.res = res;
        this.obs = obs;
    }

    // Constructor de copia
    /**
     * Crea una de Prueba copiando los argumentos de otro objeto de tipo Prueba.
     *
     * @param prueba Prueba de la que se van a copiar los argumentos.
     */
    public Prueba(Prueba prueba) {

        this.fechaR = prueba.getFechaR();
        this.operarioC = prueba.getOperarioC();
        this.desc = prueba.getDesc();
        this.res = prueba.getRes();
        this.obs = prueba.getObs();
        this.maquina = prueba.getMaquina();
    }

//constructores
    /**
     * Crea una instancia de Prueba creando la relación con {@link Regla} y
     * {@link Maquina} mediante el atributo id.
     *
     * @param id id de la prueba de tipo Long
     * @param fechaR fecha de realizacíon de tipo Date
     * @param idOperarioC id del operario de calidad que realiza la prueba de
     * tipo Long
     * @param desc descripcion de la prueba de tipo String
     * @param res resultado de la prueba de tipo String
     * @param obs observaciones de la prueba de tipo String
     * @param idMaquina id de la maquina que realiza la preuba de tipo Long
     * @param idRegla id de la regla que se le aplica de tipo Long
     */
    public Prueba(long id, Date fechaR, long idOperarioC, String desc, String res, String obs, long idMaquina, long idRegla) {
        this.id = id;
        this.fechaR = fechaR;
        this.idOperarioC = idOperarioC;
        this.desc = desc;
        this.res = res;
        this.obs = obs;
        this.idMaquina = idMaquina;
        this.idRegla = idRegla;
    }

    /**
     * Crea una instancia de Prueba creando la relación con {@link Regla} y
     * {@link Maquina} sin id.
     *
     * @param id
     * @param fechaR
     * @param idOperarioC
     * @param desc
     * @param res
     * @param obs
     * @param maquina
     */
    public Prueba(long id, Date fechaR, long idOperarioC, String desc, String res, String obs, Maquina maquina) {
        this.id = id;
        this.fechaR = fechaR;
        this.idOperarioC = idOperarioC;
        this.desc = desc;
        this.res = res;
        this.obs = obs;
        this.maquina = maquina;
    }

    //getters and setters
    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Date getFechaR() {
        return fechaR;
    }

    public void setFechaR(Date fechaR) {
        this.fechaR = fechaR;
    }

    public OCalidad getOperarioC() {
        return operarioC;
    }

    public void setOperarioC(OCalidad operario) {
        this.operarioC = operario;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdOperarioC() {
        return idOperarioC;
    }

    public void setIdOperarioC(long idOperarioC) {
        this.idOperarioC = idOperarioC;
    }

    public long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public long getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(long idRegla) {
        this.idRegla = idRegla;
    }

    //data y tostring
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de Prueba
     * que llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id de la prueba</code>, <code>id de la regla</code>,
     * <code>id de la maquina</code>,
     * <code>fecha de Realizacion</code>,<code>id del operario</code>,<code>descripcion</code>,<code>observación</code>
     * y <code>resultado</code>,
     */
    @Override
    public String toString() {
        return "Prueba:\n"
                + "id de la prueba=" + id + "| id de la regla=" + idRegla + "| id de la máquina=" + idMaquina + "| fecha de realización=" + fechaR + "| id Operario=" + idOperarioC + "| descripció=" + desc + "| resultado=" + res + "| observaciones=" + obs + '}';
    }

    public String data() {
        return "Prueba:\n"
                + "id de la prueba:" + this.getId() + "id de máquina:" + this.getIdMaquina() + "id de regla:" + this.getIdRegla() + "| fecha de realización:" + this.getFechaR() + "|" + "|id de Operario:" + this.getIdOperarioC() + "| descripción:" + this.getDesc() + "| resultados:" + this.getRes() + "| observaciones:" + this.getObs();
    }

    //metodos propios
    /**
     * Crea una instancia de Prueba introduciendo los atributos por teclado
     *
     * @return devuelve un objeto de tipo Prueba.
     */
    public static Prueba nuevoPrueba() {
        Prueba prueba = new Prueba();
        Scanner in = new Scanner(System.in);
        boolean c;

        do {

            System.out.println("Introduzca la descripción");
            String desc = in.nextLine();
            prueba.setDesc(desc);
            System.out.println("Introduzca el resultado");
            String res = in.nextLine();
            prueba.setRes(res);
            System.out.println("Introduzca la observación");
            String obs = in.nextLine();
            prueba.setObs(obs);
            System.out.println("Introduzca la fecha de realización");
            Date fecha = ToolBox.introducirFecha();
            prueba.setFechaR(fecha);
            System.out.println("Introduzca los datos del Operario de Calidad asignado");
            OCalidad ope = OCalidad.nuevoOCalidad();
            prueba.setOperarioC(ope);
            System.out.println("Introduzca los datos de la Máquina asignada");
            Maquina maq = Maquina.nuevoMaquina();
            prueba.setMaquina(maq);

            System.out.println("¿Son correctos estos datos? (introduzca una s si lo son)");
            System.out.println("Descripción: " + desc);
            System.out.println("Resultados: " + res);
            System.out.println("Observaciones: " + obs);
            System.out.println("Fecha de realización: " + fecha);

            c = ToolBox.leerBoolean();

        } while (!c);

        return prueba;
    }

    /**
     * Importa un grupo de Prueba desde un fichero de texto
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los Pruebas de calidad
     * existentes en el fichero
     */
    public static ArrayList<Prueba> readPruebaFromTextFile(String path) {
        ArrayList<Prueba> ret = new ArrayList<>();
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
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    long id = Long.parseLong(campos[0]);
                    Date fecha = df.parse(campos[1]);
                    long idOperario = Long.parseLong(campos[1]);
                    String desc = campos[3];
                    String res = campos[4];
                    String obs = campos[5];
                    long idMaq = Long.parseLong(campos[6]);
                    long idReg = Long.parseLong(campos[7]);
                    Prueba p = new Prueba(id, fecha, idOperario, desc, res, obs, idMaq, idReg);
                    ret.add(p);
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
     * Importa un grupo de Prueba desde un fichero binario
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los s de Calidad existentes
     * en el fichero
     */
    public static ArrayList<Prueba> FromBinaryFile(String path) {
        ArrayList<Prueba> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Prueba c;
                while ((c = (Prueba) lectorObjeto.readObject()) != null) {
                    ret.add(c);
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
     * Exporta los datos de un <code>Prueba</code> a un fichero de texto, a
     * traves del metodo <code>Data</code> introduciendo al final un retorno de
     * carro. 
     *
     * @param path la ruta del fichero al que exportar los datos del objeto. 
     * @see Prueba.data() data
     */
    public void ToTextFile(String path) {
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
     * Exporta un <code>OCalidad</code> a un fichero binario
     *
     * @param path la ruta del fichero al que exportar
     */
    public void ToBinaryFile(String path) {
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
