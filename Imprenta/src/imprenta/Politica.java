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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Modela una Politica.
 *
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class Politica {

    protected long id;//id de la política
    private String nom;//nombre de la política
    private Date fechaIni; //fecha de inicio de la politica
    private long idOCalidad1;// primera firma
    private long idOCalidad2;// segunda firma 
    private Lote lote;//Lote al que se aplica la política
    private long idLote;//ID del lote al que se aplica la política

    //constructoras
    /**
     * Crea una instancia de Politica con los atributos propios de la clase, sin
     * los de de las relaciones ni el id
     *
     * @param nom nombre de la politica de tipo String
     * @param fechaIni fecha de inicio de la politica de tipo Date
     * @param firma1 firma de uno de los operarios de calidad de tipo long
     * @param firma2 firma del segundo operario de calidad de tipo long
     * @param lote instanci de lote de tipo Lote
     */
    public Politica(String nom, Date fechaIni, long firma1, long firma2, Lote lote) {
        this.nom = nom;
        this.fechaIni = fechaIni;
        this.idOCalidad1 = firma1;
        this.idOCalidad2 = firma2;
        this.lote = lote;
    }

    /**
     * Crea una instancia de Politica con los valores por defecto para los
     * atributos
     */
    public Politica() {
    }

    /**
     * Crea una instancia de Politica a partir de los atributos de Politica.
     *
     * @param p Argumento de tipo Politica
     */
    public Politica(Politica p) {
        this.nom = p.getNom();
        this.fechaIni = p.getFechaIni();
        this.idOCalidad1 = p.getIdOCalidad1();
        this.idOCalidad2 = p.getIdOCalidad2();
        this.lote = p.getLote();
    }

    /**
     * Crea una instancia de Politica relacionando a los operarios de Calidad
     * {@link OCalidad} y el {@link Lote} mediante sus ids.
     *
     * @param id id de la politica de tipo long
     * @param nom nombre de la politica de tipo String
     * @param fechaIni fecha de inicializacion de tipo Date
     * @param idOCalidad1 id del operario de calidad 1 que firma la politica de
     * tipo long
     * @param idOCalidad2 id del operario de calidad 2 que firma la politica de
     * tipo long
     * @param idLote id del lote al que se aplica la politica de tipo long.
     */
    public Politica(long id, String nom, Date fechaIni, long idOCalidad1, long idOCalidad2, long idLote) {
        this.id = id;
        this.nom = nom;
        this.fechaIni = fechaIni;
        this.idOCalidad1 = idOCalidad1;
        this.idOCalidad2 = idOCalidad2;
        this.idLote = idLote;
    }

    /**
     * Crea una instancia de Politica relacionando a los operarios de Calidad
     * {@link OCalidad} y el {@link Lote} mediante sus ids.
     *
     * @param id id de la politica de tipo long
     * @param nom nombre de la politica de tipo String
     * @param idOCalidad1 id del operario de calidad 1 que firma la politica de
     * tipo long
     * @param idOCalidad2 id del operario de calidad 2 que firma la politica de
     * tipo long
     * @param idLote id del lote al que se aplica la politica de tipo long.
     */
    public Politica(long id, String nom, long idOCalidad1, long idOCalidad2, long idLote) {
        this.id = id;
        this.nom = nom;
        this.idOCalidad1 = idOCalidad1;
        this.idOCalidad2 = idOCalidad2;
        this.idLote = idLote;
    }

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public long getIdOCalidad1() {
        return idOCalidad1;
    }

    public void setIdOCalidad1(long idOCalidad1) {
        this.idOCalidad1 = idOCalidad1;
    }

    public long getIdOCalidad2() {
        return idOCalidad2;
    }

    public void setIdOCalidad2(long idOCalidad2) {
        this.idOCalidad2 = idOCalidad2;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Politica getPoliticaoById(long id) {
        /*Este método recorrerá un ArrayList con todas las politicas buscando aquella con el id que le introduzcamos, y devolverá esa politica si es que existe o 
    nulo si es que no existe*/
        Politica l = new Politica();
        return l;
    }

    public ArrayList<Politica> getAllPolitica() {
        /*Este método devolverá un arrayList con todas las politicas existentes*/
        ArrayList<Politica> o = new ArrayList<>();
        return o;
    }

    public long getIdLote() {
        return idLote;
    }

    public void setIdLote(long idLote) {
        this.idLote = idLote;
    }

    //Metodos de Data y toString
    /**
     * Devuelve un <code>String</code> con los datos de la instancia de
     * {@link Politica} que llama al metodo.
     *
     * @return un <code>String</code> con los atributos del objeto en este
     * orden: <code>id</code>, <code>nombre</code>, <code>fecha inicio</code>,
     * <code>id operario 1</code>,<code>id operario 2</code>,<code>id del lote</code>
     */
    public String data() {
        return this.getId() + this.getNom() + this.getFechaIni() + this.getIdOCalidad1() + this.getIdOCalidad2() + this.getIdLote();
    }

    @Override
    public String toString() {
        return "PoliticaCalidad:\n"
                + "id=" + id + "Nombre=" + nom + "|Fecha de inicio=" + fechaIni + "|id Operario 1=" + idOCalidad1 + "|id Operario 2=" + idOCalidad2 + "|id Lote=" + idLote;
    }

    //Método para crear políticas
    /**
     * Crea una Politica introduciendo los atributos por teclado.
     *
     * @return una instancia de Politica.
     * @throws ParseException cuando el formato de fecha no es correcto.
     */
    public static Politica nuevaPolitica() throws ParseException {
        Politica p = new Politica();
        Scanner in = new Scanner(System.in);
        boolean c;
        do {
            System.out.println("Introduzca el nombre");
            String nom = in.nextLine();
            p.setNom(nom);
            System.out.println("Introduzca la fecha de inicio");
            Date fin = ToolBox.introducirFecha();
            p.setFechaIni(fin);
            System.out.println("Introduzca el id del primer operario");
            long id1 = in.nextLong();
            p.setIdOCalidad1(id1);
            System.out.println("Introduzca el id del segundo operario");
            long id2 = in.nextLong();
            p.setIdOCalidad2(id2);
            System.out.println("Introduzca el lote");
            Lote l = Lote.nuevoLote();
            p.setLote(l);
            System.out.println("¿Son correctos estos datos? (s/n)");
            System.out.println("Nombre: " + nom);
            System.out.println("Fecha de inicio: " + fin);
            System.out.println("Id del primer operario: " + id1);
            System.out.println("Id del segundo operario: " + id2);
            System.out.println("Lote: " + l.toString());
            c = ToolBox.leerBoolean();
        } while (!c);
        return p;
    }

    //lectura y escritura
    /**
     * Importa un grupo de políticas {@link Politica} desde un fichero de texto
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos las politicas
     * {@link Politica} existentes en el fichero
     */
    public static ArrayList<Politica> fromTextFile(String path) {
        ArrayList<Politica> ret = new ArrayList<>();
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
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = formatter.parse(campos[2]);
                    long idOpe1 = Long.parseLong(campos[3]);
                    long idOpe2 = Long.parseLong(campos[4]);
                    long idLote = Long.parseLong(campos[5]);
                    Politica p = new Politica(id, nombre, fecha, idOpe1, idOpe2, idLote);
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
            System.out.println("FileNotFoundException encontrado");
        } catch (IOException e) {
            System.out.println("IOExceotion encontrado");
        } catch (Exception e) {
            System.out.println("Exception encontrada");
        }
        return ret;
    }

    /**
     * Importa un grupo de politicas {@link Politica} desde un fichero binario.
     *
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos las politicas
     * {@link Politica} existentes en el fichero.
     */
    public static ArrayList<Politica> fromBinaryFile(String path) {
        ArrayList<Politica> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                Politica p;
                while ((p = (Politica) lectorObjeto.readObject()) != null) {
                    ret.add(p);
                }
            } finally {
                if (lector != null) {
                    lector.close();
                }
                if (lectorObjeto != null) {
                    lectorObjeto.close();
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
     * Exporta los datos de una <code>Politica</code> a un fichero de texto, a
     * traves del metodo <code>Data</code> introduciendo al final un retorno de
     * carro.
     *
     * @param path la ruta del fichero al que exportar los datos del objeto de
     * tipo String.
     * @see Politica.data() data
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
     * Exporta una <code>Politica</code> a un fichero binario.
     *
     * @param path la ruta del fichero al que exportar de tipo String.
     */
    public void toBinaryFile(String path) {
        FileOutputStream escritor = null;
        ObjectOutputStream escritorObjeto = null;
        try {
            try {
                escritor = new FileOutputStream(path);
                escritorObjeto = new ObjectOutputStream(escritor);
                escritorObjeto.writeObject(this);
            } finally {
                if (escritor != null) {
                    escritor.close();
                }
                if (escritorObjeto != null) {
                    escritorObjeto.close();
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
}
