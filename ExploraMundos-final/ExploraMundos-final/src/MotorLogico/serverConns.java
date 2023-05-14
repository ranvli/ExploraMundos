/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MotorLogico;

import capaInterfaz.Juego_Server;
import capaInterfaz.servidor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RanVLi
 */




public class serverConns implements Serializable{

    ServerSocket sSocket;
    int puerto = 19380;

    File mapaCagado;

    capaInterfaz.servidor sg;
    capaInterfaz.Juego_Server jserverGUI;

    capaDatos.AdmDatos cd;

    public ArrayList conexiones = new ArrayList();


    public serverConns(servidor aThis) {

    }

    public serverConns(servidor aThis, File mapaCagado) {

        this.sg =  aThis;
        this.mapaCagado = mapaCagado;
        iniciaServidor(mapaCagado);
        cd = new capaDatos.AdmDatos();

    }

    public void iniciaServidor(File mapaCagado) {

        msg("Iniciando juego GUI server");
        jserverGUI = new capaInterfaz.Juego_Server(this, mapaCagado);
        jserverGUI.setVisible(true);

        msg("Servidor juego GUI iniciado..");

        msg("Iniciando servidor de conexiones de juego..");
        msg("Intentando conectar socket en puerto " + puerto);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    msg("Nuevo socketServer ha sido creado..");
                    sSocket = new ServerSocket(puerto);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el servidor creando serversocket : " + ex.getMessage());
                }
                try {
                    while(true){
                        msg("\n**************\nNUEVO SOCKET CREADO: Servidor escuchando en puerto : " + puerto +"\n**************\n");
                        Socket ssCon = sSocket.accept();
                        procesaConexion(ssCon, sg, jserverGUI);
                    }
                } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el servidor : " + ex.getMessage());
                }
            }


        });
        
        t.start();
        msg("Servidor de conexiones iniciado..");





    }



    private void msg(String string) {
        sg.msg(string);
    }
    
    
    private void procesaConexion(Socket ssCon, servidor sg, Juego_Server jserverGUI) {

        msg("******************* Conexión entrante ****************");
        msg("IP   : " + ssCon.getInetAddress());
        msg("Host : " + ssCon.getInetAddress().getHostName());
        msg("Creando flujos de datos");

        ObjectOutputStream flujoSalida = null;
        ObjectInputStream flujoEntrada = null;

        try {
            flujoSalida = new ObjectOutputStream(ssCon.getOutputStream());
            flujoSalida.flush();
            flujoEntrada = new ObjectInputStream(ssCon.getInputStream());

            msg("Enviando datos de mapa en juego");
            //envia a cliente las especificaciones del mapa
            // envia matriz mapa, tops y luego settings
            flujoSalida.writeObject(jserverGUI.mMapa);
            flujoSalida.writeObject(jserverGUI.mPremios);
            flujoSalida.writeObject(cd.cargaMapa(mapaCagado.getPath(), 3)); //aqui si carga settings
            //crea avatars existentes jugando actualmente

        } catch (IOException ex) {
             sg.msg("Error creando flujos de datos : " + ex.getMessage());
        }



        conexiones.add(flujoSalida);
        new manejaConexionCliente(ssCon, flujoSalida, flujoEntrada, sg, jserverGUI).start();



    }
    
   
   


}
