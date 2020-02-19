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
 *
 * @author Alberto
 * @version1
 */
public class Lote {

    private String nom; // nombre del lote
    private ArrayList<Maquina> maquinas; // array de maquinadas asignadas al lote
    protected long id;//variable identificador
    private Departamento departamento;
    private long idDepartamento;

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

    //constructor con argumentos
    public Lote(String nom, ArrayList<Maquina> maquinas) {
        this.nom = nom;
        this.maquinas = maquinas;
    }

    //constructor por defecto
    public Lote() {
    }

    //constructor de copia
    public Lote(Lote l) {
        this.nom = l.getNom();
        this.maquinas = l.maquinas;
    }

    public Lote(String nom, long id, Departamento departamento, long idDepartamento) {
        this.nom = nom;
        this.id = id;
        this.departamento = departamento;
        this.idDepartamento = idDepartamento;
    }
    
    
    //lectura y escritura
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
                    Lote l = new Lote( nom, id,  dep, idDep);

                    lot.add (l);
                }
            } finally {
                if (buffer != null) {
                    buffer.close();
                }
                if (lector != null) {
                    lector.close();
                }
            }
        } catch (ClienteException e) {
            System.out.println("Se ha producido una ClienteException");
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido una FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Se ha producido una IOException");
        } catch (Exception e) {
            System.out.println("Se ha producido una Exception");
        }
        return lot;
    }

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
        } catch (LaborException l) {
            System.out.println("Se ha producido una LaborException");
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
    public String data() {
        return id + "|" + nom + "|" + maquinas + "|" + idDepartamento + "|" + departamento;

    }

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
