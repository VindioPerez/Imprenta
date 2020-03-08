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
 * Modela el Departamento donde trabajan los {@link Operario} y los {@link Lote}.
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Departamento {

    private char letraDep;//Los departamentos se identifican con un carácter, valores posible: 'A', 'B' o 'C'
    protected long id;// variable identificador
    private ArrayList<Operario> operarios;//lista de operarios del Departamento

    // Constructor por defecto
    public Departamento() {
    }

    // Constructor con argumentos
    /**
     * Crea una instancia de Departamento con los siguientes atributos
     *
     * @param letraDep letra del Departamento
     */
    public Departamento(char letraDep) {
        this.letraDep = letraDep;
    }

    // Constructor con argumentos
    /**
     * Crea una instancia de Departamento con los siguientes atributos
     *
     * @param letraDep letra del Departamento
     * @param id id del departamento
     */
    public Departamento(char letraDep, long id) {
        this.letraDep = letraDep;
        this.id = id;
    }

    //constructor de copia
    /**
     * Crea una instancia de Departamento a partir de otra, copiando cada
     * atributo
     *
     * @param d el Departamento que se va a copiar
     */
    public Departamento(Departamento d) {
        this.letraDep = d.getLetraDep();
    }

    // getters y setters 
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

    public ArrayList<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(ArrayList<Operario> operarios) {
        this.operarios = operarios;
    }

    //getAll, getById, data y toString
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de departamento
     * que llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>idMaquina</code>, <code>idOperario</code>,
     * <code>idCliente</code>, <code>fechaSolicitud</code>,
     * <code>fechaRecogida</code>, <code>relieve</code>
     */
    @Override
    public String toString() {
        return "Departamento{" + "letraDep=" + letraDep + ", id=" + id + '}';
    }

    // El orden de los campos es el siguiente: id-letraDep
    /**
     * Devuelve un <code>String</code> con los atributos formateados para
     * exportar a un fichero de texto
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>letraDep</code>,  separados por una barra vertical
     */
    public String data() {
        return id + "|" + Character.toString(letraDep);
    }

    /**
     * Recorre el <code>ArrayList</code> de Departamentos de {@link BDatos} y
     * devuelve el departamento con el id que se pasa como parametro
     *
     * @param idDepartamento el id del departamento que se quiere buscar en la base
     * de datos
     * @return el <code>Departamento</code> con el id coincidente con
     * <code>idDepartamento</code>, o nulo si no existe dicho departamento
     */
    public static Departamento getDepartamentoById(long idDepartamento) {
        for (Departamento d : BDatos.departamentos) {
            if (d.getId() == idDepartamento) {
                return d;
            }
        }
        return null;
    }

    /**
     * Devuelve todos los departamentos registrados en el sistema
     *
     * @return un <code>ArrayList</code> con todos los departamentos de la base
     * de datos
     */
    public ArrayList<Departamento> getAllDepartamento() {
        /*Este método devolverá un arrayList con todos los departamentos existentes*/
        return BDatos.departamentos;
    }

    //lectura y escritura
    /**
     * Importa un grupo de departamentos desde un fichero de texto
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los departamentos existentes
     * en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con
     * la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
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
                    Departamento dep = new Departamento(letraDep, id);
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

    /**
     * Importa un grupo de departamentos desde un fichero binario
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los departamento existentes
     * en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con
     * la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el
     * objeto
     */
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

    /**
     * Exporta los datos de un <code>Departamento</code> a un fichero de texto,
     * a traves del metodo <code>Data</code> introduciendo al final un retorno
     * de carro
     *
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la
     * ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Departamento.data() data
     */
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

    /**
     * Exporta un <code>Departamento</code> a un fichero binario
     *
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con
     * la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
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

    /**
     * Crea una nueva instancia de la clase <code>Departamento</code> pidiendo
     * al usuario por pantalla que introduzca los datos
     *
     * @return el <code>Departamento</code> que se crea con el método
     */
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
        } while (c = false);
        return dep;
    }

}
