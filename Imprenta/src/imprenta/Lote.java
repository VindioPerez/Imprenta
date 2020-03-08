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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Modela los lotes en los que se agrupan las {@link Maquina}.
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Lote {

    private String nom; // nombre del lote
    private ArrayList<Maquina> maquinas; // array de maquinadas asignadas al lote
    protected long id;//variable identificador
    private Departamento departamento;
    private long idDepartamento;

    /**
     * Crea una nueva instancia de la clase <code>Lote</code> pidiendo al
     * usuario por pantalla que introduzca los datos
     *
     * @return el <code>Lote</code> que se crea con el método
     */
    public static Lote nuevoLote() throws ParseException {
        Lote l = new Lote();

        Scanner in = new Scanner(System.in);
        Boolean c;
        char d;
        do {
            System.out.println("Introduzca el departamento");
            Departamento dep = Departamento.nuevoDepartamento();
            l.setDepartamento(dep);
            System.out.println("Introduzca el nombre del lote");
            String nom = in.nextLine();
            l.setNom(nom);

            System.out.println("Quiere Introducir una nueva Maquina? s/n ");
            d = in.next().charAt(0);

            while (d == 's') {
                l.maquinas.add(Maquina.nuevoMaquina());

                System.out.println("Quiere Introducir otra Maquina? s/n ");
                d = in.next().charAt(0);
            }

            System.out.println("¿Son correctos estos datos? (introduzca una v si lo son)");
            System.out.println("Nombre Lote: " + nom);

            c = ToolBox.leerBoolean();

        } while (c = false);

        return l;
    }

    // getters y setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public long getId() {
        return id;
    }

    /*
    public void setId(long id) {
        this.id = id;
    }
     */
    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(ArrayList<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    // constructor con argumentos completo
    /**
     * Crea una instancia de Lote con todos los atributos
     *
     * @param nom nombre del lote
     * @param maquinas la coleccion de maquinas asignadas al departamento
     * @param id id del lote
     * @param departamento departamento al que esta asignado el lote
     * @param idDepartamento id del departamento al que esta asignado el lote
     */
    public Lote(String nom, ArrayList<Maquina> maquinas, long id, Departamento departamento, long idDepartamento) {
        this.nom = nom;
        this.maquinas = maquinas;
        this.id = id;
        this.departamento = departamento;
        this.idDepartamento = idDepartamento;
    }

    //constructor con argumentos
    /**
     * Crea una instancia de Lote con los siguientes atributos:
     *
     * @param nom nombre del lote
     * @param maquinas la coleccion de maquinas asignadas al departamento
     */
    public Lote(String nom, ArrayList<Maquina> maquinas) {
        this.nom = nom;
        this.maquinas = maquinas;
    }

    //constructor por defecto
    /**
     * Crea una instancia de lote con los valores por defecto para los atributos
     */
    public Lote() {
    }

    //constructor de copia
    /**
     * Crea una copia de un Lote ya existente
     *
     * @param l el lote que se va a copiar
     */
    public Lote(Lote l) {
        this.nom = l.getNom();
        this.maquinas = l.maquinas;
    }

    //Vonstructor con argumentos
    /**
     * Crea una instancia de Lote con los siguientes atributos:
     *
     * @param nom nombre del lote
     * @param id id del lote
     * @param departamento departamento al que esta asignado el lote
     * @param idDepartamento id del departamento al que esta asignado el lote
     */
    public Lote(String nom, long id, Departamento departamento, long idDepartamento) {
        this.nom = nom;
        this.id = id;
        this.departamento = departamento;
        this.idDepartamento = idDepartamento;
    }

    //lectura y escritura
    /**
     * Importa un grupo de lotes desde un fichero de texto
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los lotes existentes en el
     * fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta
     * especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<Lote> readLoteFromTextFile(String path) {
        ArrayList<Lote> lot = new ArrayList<>();
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

                    String nom = campos[0];
                    long id = Long.parseLong(campos[1]);
                    Departamento dep = new Departamento();
                    //Falta parsear los elementos de departamento
                    long idDep = Long.parseLong(campos[2]);
                    Lote l = new Lote(nom, id, dep, idDep);

                    lot.add(l);
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
        return lot;
    }

    /**
     * Importa un grupo de lotes desde un fichero binario
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los lotes existentes en el
     * fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta
     * especificada
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el
     * objeto
     */
    public static ArrayList<Lote> readLoteFromBinaryFile(String path) {
        ArrayList<Lote> lot = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Lote l;
                while ((l = (Lote) lectorObjeto.readObject()) != null) {
                    lot.add(l);
                }
            } finally {
                if (lectorObjeto != null) {
                    lectorObjeto.close();
                }
                if (lector != null) {
                    lector.close();
                }
            }
        } catch (FileNotFoundException l) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException l) {
            System.out.println("Se ha producido una IOException");
        } catch (ClassNotFoundException l) {
            System.out.println("Se ha producido una ClassNotFoundException");
        } catch (Exception l) {
            System.out.println("Se ha producido una Exception");
        }
        return lot;
    }

    /**
     * Exporta los datos de un <code>Lote</code> a un fichero de texto, a traves
     * del metodo <code>Data</code> introduciendo al final un retorno de carro
     *
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta
     * especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see Lote.data() data
     */
    public void writeLoteToTextFile(String path) {
        File fichero = new File(path);
        FileWriter escritor = null;
        PrintWriter buffer = null;
        try {
            try {
                escritor = new FileWriter(fichero);
                buffer = new PrintWriter(escritor);
                buffer.println(this.data());
            } finally {
                if (buffer != null) {
                    buffer.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            }
        } catch (FileNotFoundException l) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException l) {
            System.out.println("Se ha producido una IOException");
        } catch (Exception l) {
            System.out.println("Se ha producido una Exception");
        }
    }

    /**
     * Exporta un <code>Lote</code> a un fichero binario
     *
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta
     * especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeLoteToBinaryFile(String path) {
        FileOutputStream escritor = null;
        ObjectOutputStream escritorObjeto = null;
        try {
            try {
                escritor = new FileOutputStream(path);
                escritorObjeto = new ObjectOutputStream(escritor);
                escritorObjeto.writeObject(this);
            } finally {
                if (escritorObjeto != null) {
                    escritorObjeto.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            }
        } catch (FileNotFoundException l) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException l) {
            System.out.println("Se ha producido una IOException");
        } catch (Exception l) {
            System.out.println("Se ha producido una Exception");
        }
    }

    // el orden de los campos es el siguiente: id-nom-maquinas-idDepartamento-Departamento
    /**
     * Devuelve un <code>String</code> con los atributos formateados para
     * exportar a un fichero de texto
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nom</code>, <code>maquinas</code>,
     * <code>idDepartamento</code>, <code>Departamento</code>, separados por una
     * barra vertical
     */
    public String data() {
        return id + "|" + nom + "|" + maquinas + "|" + idDepartamento + "|" + departamento;

    }

    /**
     * Devuelve un <code>String</code> con los datos de la instancia de lote que
     * llama al metodo
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nom</code>, <code>maquinas</code>,
     * <code>idDepartamento</code>, <code>Departamento</code>
     */
    @Override
    public String toString() {
        return "Lote{" + "nom=" + nom + ", listaMaq=" + maquinas + ", id=" + id + '}';
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
