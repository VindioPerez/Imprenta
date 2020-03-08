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

/**
 * Modela el trabajo que solicita un {@link Cliente}. Tiene 3 clases hijas, {@link Libro}, {@link Rotulo} y {@link Poster}
 * @author Alberto
 * @author Ander
 * @author Vindio
 * @version 1.5
 */
public class OMaquinas extends Operario {

    private ArrayList<Labor> labores;//variable array que contiene todas las labores del operario de maquinas

    //constructor por defecto 
    /**
     * {@inheritDoc}
     */
    public OMaquinas() {
        super();
    }

    // constructor con argumentos
    /**
     * Crea una instancia de OMaquinas con  los atributos propios de la clase
     * {@inheritDoc}
     * @param nombre el nombre del operario 
     * @param apellidos los apellidos del operario
     * @param nif el dni del operario
     * @param telefono el numero de telefono del operario
     * @param direccion ñlla direccion ( domicilio) del operario
     * @param senior si es senior o no el operario
     */
    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }

    //constructor con argumentos completo
    /**
     * Crea una instancia de OMaquinas con  los atributos propios de la clase
     * {@inheritDoc}
     * @param nombre el nombre del operario 
     * @param apellidos los apellidos del operario
     * @param nif el dni del operario
     * @param telefono el numero de telefono del operario
     * @param direccion ñlla direccion ( domicilio) del operario
     * @param senior si es senior o no el operario
     * @param id  el id del operaio
     */
    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior, long id) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
    }


    /**
     * {@inheritDoc}
     * @param o Operario de maquinas a copiar 
     */
    public OMaquinas(OMaquinas o) {
        super(o);
    }

    /**
     * {@inheritDoc}
     * @param o operario a copiar
     */
    public OMaquinas(Operario o) {
        super(o);

    }

    // getters y setters
    public ArrayList<Labor> getLabores() {
        return labores;
    }

    public void setLabores(ArrayList<Labor> labores) {
        this.labores = labores;
    }

    /**
     * {@inheritDoc }
     * Devuelve un <code>String</code> con los datos de la instancia de OMaquinas que llama al metodo
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>super</code>, <code>labores</code> 
     */
    @Override
    public String toString() {
        return super.toString() + "labores=" + labores;
    }

    /**
     * Devuelve un <code>String</code> con los atributos formateados para exportar a un fichero de texto
     * @return un <code>String</code> con los atributos del objeto en este orden: <code>super</code>, <code>labores</code>, separados por una barra vertical
     */
    @Override
    public String data() {
        return super.data() + "|" + labores;

    }

    /**
     * Crea una nueva instancia de la clase <code>OMaquinas</code> pidiendo al usuario por pantalla que introduzca los datos
     * @return el <code>OMaquinas</code> que se crea con el método
     */
    public static OMaquinas nuevoOMaquinas() {
        OMaquinas OM = new OMaquinas(Operario.nuevoOperario());
        return OM;

    }

        /**
     * Importa un grupo de OMaquinas desde un fichero de texto
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los operarios de maquinas existentes en el fichero
     * @throws TrabajoException si los datos leidos del fichero no permiten construir un <code>OMaquina</code>
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public static ArrayList<OMaquinas> readOMaquinasFromTextFile(String path) {
        ArrayList<OMaquinas> ret = new ArrayList<>();
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

                    OMaquinas om = new OMaquinas(nombre, apellido, nif, telefono, direccion, senior, id);
                    ret.add(om);
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
     * Importa un grupo de OMaquinas desde un fichero binario
     * @param path la ruta del fichero a importar
     * @return un <code>ArrayList</code> con todos los operarios de maquinas existentes en el fichero
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @throws ClassNotFoundException si no se encuentra la clase al leer el objeto
     */
    public static ArrayList<OMaquinas> readOMaquinasFromBinaryFile(String path) {
        ArrayList<OMaquinas> ret = new ArrayList<>();
        FileInputStream lector = null;
        ObjectInputStream lectorObjeto = null;
        try {
            try {
                lector = new FileInputStream(path);
                lectorObjeto = new ObjectInputStream(lector);
                OMaquinas om;
                while ((om = (OMaquinas) lectorObjeto.readObject()) != null) {
                    ret.add(om);
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
     * Exporta los datos de un <code>OMaquinas</code> a un fichero de texto, a traves del metodo <code>Data</code> introduciendo al final un retorno de carro
     * @param path la ruta del fichero al que exportar los datos del objeto
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     * @see OMaquinas.data() data
     */
    public void writeOMaquinasToTextFile(String path) {
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
     * Exporta un <code>OMaquinas</code> a un fichero binario
     * @param path la ruta del fichero al que exportar
     * @throws FileNotFoundException si no se puede acceder al fichero con la ruta especificada
     * @throws IOException si hay un problema de entrada/salida
     */
    public void writeOMaquinasToBinaryFile(String path) {
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
     * Recorre el <code>ArrayList</code> de OMaquinas de {@link BDatos} y devuelve el operario de maquinas con el id que se pasa como parametro
     * @param id el id del OMaquinas que se quiere buscar en la base de datos
     * @return el <code>OMaquinas</code> con el id coincidente con <code>id</code>, o nulo si no existe dicho OMaquinas
     */
    public static OMaquinas getOperarioMaquinasById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OMaquinas om = new OMaquinas();
        return om;
    }

    /**
     * Rellena una {@link Maquina} cambiando su volumen de tinta
     * @param idMaquina la maquina sobre la que se va a realizar el rellenado
     * @return el boolean que confirma que se ha rellenado
     */
    public boolean rellenarMaquina(long idMaquina) {
        Scanner sc = new Scanner(System.in);
        boolean rellenada;
        Maquina m = Maquina.getMaquinaById(idMaquina);
        m.setDisponible(false);
        System.out.println("La máquina" + m.getId());
        System.out.println("Localizada en:" + m.getUbicacion());
        System.out.println("Tiene un volumen de tinta:" + m.getVolTinta());
        System.out.println("Su volúmen máximo de tinta es:" + m.getCapMax());
        System.out.println("Desea rellenar la máquina?");
        boolean decision = ToolBox.leerBoolean();
        if (m.getVolTinta() < 0.0 && m.getVolTinta() < m.getCapMax()) {
            if (decision) {
                System.out.println("Por favor introduzca el volumen de tinta a rellenar");
                float vol = sc.nextFloat();
                m.setVolTinta(vol);
                rellenada = true;
            } else {
                System.out.println("La máquina se va a quedar igual");
                rellenada = false;

            }
        } else {
            System.out.println("El volumen de la máquina no es correcto");
            rellenada = false;
        }

        System.out.println("El resultado de la máquina es:" + m.toString());

        return rellenada;
    }
    
    /**
     * Realiza una labor modificando su estado acual
     * @param l la labor que se va a realizar
     * @return boolean que informa de la realizacion de la labor
     * @throws LaborException sio alguno de los atributos no es valido
     */
     public boolean realizarLabor(Labor l) throws LaborException {
        Scanner in = new Scanner(System.in);
        System.out.println("El estado actual de la labor es el siguiente: ");
        char estado;
        boolean realizada=false ;
        boolean b = false;
        estado = l.getEstado();
        if (estado != 'p' && estado != 'P' && estado != '0' && estado != 'f' && estado != 'F') {
            throw new LaborException(" El Estado no tiene un valor  válido");
        } else {
            switch (estado) {
                case 0:
                    System.out.println("La labor no se ha comenzado (0)");
                    break;
                case 'p':
                case 'P':
                    System.out.println("En pausa ('p'||'P') ");
                    break;
                case 'r':
                case 'R':
                    System.out.println(" En realización('p'||'P') ");
                    break;
                case 'f':
                case 'F':
                    System.out.println("Finalizado ('f'||'F') ");
                    break;
            }
        }
        System.out.println("¿Desea cambiar el estado de la labor (s/n)? ");
        boolean ans =  ToolBox.leerBoolean();
        if (ans=true && estado!='f'&& estado!='F' ) {
                do {

                    System.out.println("Introduzca el nuevo estado de la labor, las opciones validas son las siguientes: (0: sin comenzar,"
                            + "p: en pausa, r: en realización, f: finalizado ");

                    char nuevoestado = in.next().charAt(0);

                    System.out.println("El estado actual de la labor es el siguiente: ");
                    switch (estado) {
                        case 0:
                            System.out.println("La labor no se ha comenzado (0)");
                            break;
                        case 'p':
                        case 'P':
                            System.out.println("En pausa ('p'||'P') ");
                            break;
                        case 'r':
                        case 'R':
                            System.out.println(" En realización('p'||'P') ");
                            break;
                        case 'f':
                        case 'F':
                            System.out.println("Finalizado ('f'||'F') ");
                            break;
                    }
                    System.out.println("¿Está de acuerdo con lo mostrado? (s/n");
                    boolean verif  = ToolBox.leerBoolean();
                        if (!(verif=true)) {
                        } else {
                            b = true;
                            System.out.println("El cambio se ha realizado satisfactioriamente");
                        }
                   

                    if (estado != 'p' && estado != 'P' && estado != '0' && estado != 'f' && estado != 'F') {

                    } else {
                        throw new LaborException("El nuevo valor de estado no es válido");
                    }
                    l.estado = nuevoestado;
                    realizada=true;
                } while (b = false);
            }
        return realizada;
    }
}
