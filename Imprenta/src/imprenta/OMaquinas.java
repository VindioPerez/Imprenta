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
 *
 * @author VindioPerez
 * @version 1.1
 */
public class OMaquinas extends Operario {

    private ArrayList<Labor> labores;//variable array que contiene todas las labores del operario de maquinas

    public OMaquinas() {
        super();
    }

    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
    }

    public OMaquinas(String nombre, String apellidos, String nif, String telefono, String direccion, boolean senior, long id) {
        super(nombre, apellidos, nif, telefono, direccion, senior);
        this.id = id;
    }

    public OMaquinas(OMaquinas o) {
        super(o);
    }

    public OMaquinas(Operario o) {
        super(o);

    }

    public ArrayList<Labor> getLabores() {
        return labores;
    }

    public void setLabores(ArrayList<Labor> labores) {
        this.labores = labores;
    }

    @Override
    public String toString() {
        return super.toString() + "labores=" + labores;
    }

    @Override
    public String data() {
        return super.data() + "|" + labores;

    }

    public static OMaquinas nuevoOMaquinas() {
        OMaquinas OM = new OMaquinas(Operario.nuevoOperario());
        return OM;

    }

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
    
    public static OMaquinas getOperarioMaquinasById(long id) {
        /*Este método recorrerá un ArrayList con todos los operarios buscando aquel con el id que le introduzcamos, y devolverá ese operario si es que existe o 
        nulo si es que no existe*/
        OMaquinas om = new OMaquinas();
        return om;
    }

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

}
