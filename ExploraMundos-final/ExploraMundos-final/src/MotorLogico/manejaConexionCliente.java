/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MotorLogico;

import capaInterfaz.Juego_Server;
import capaInterfaz.servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RanVLi
 */
public class manejaConexionCliente extends Thread implements Serializable{

    ObjectOutputStream flujoSalida;
    ObjectInputStream flujoEntrada;
    Socket clienteSocket;

    capaInterfaz.servidor sg;
    capaInterfaz.Juego_Server jserverGUI;

    public ArrayList ordenes = new ArrayList();


    manejaConexionCliente(Socket ssCon, servidor sg, Juego_Server jserverGUI) {

    }

    manejaConexionCliente(Socket sCon, ObjectOutputStream flujoSalida, ObjectInputStream flujoEntrada, servidor sg, Juego_Server jserverGUI) {

        this.sg = sg;
        this.clienteSocket = sCon;
        this.flujoEntrada = flujoEntrada;
        this.flujoSalida = flujoSalida;
        this.jserverGUI = jserverGUI;

    }

    @Override
    public void run() {

        sg.msg("Creando avatar cliente...");
        int nAvatar = jserverGUI.iniAvatarRED();
        sg.msg("Hecho. Número de avatar : " + nAvatar);

       sg.msg("Enviando datos de avatars en juego");
       try {
           for(int i = 0; i < jserverGUI.numAvatars; i++){
                flujoSalida.writeObject("!a");
                int pax = jserverGUI.posAvatarX[i];
                int pay = jserverGUI.posAvatarY[i];
                flujoSalida.writeInt(pax);
                flujoSalida.writeInt(pay);
           }
           flujoSalida.writeObject("avatarsOK");
       } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Error enviando información de avatars : " + ex.getMessage());
       }


        //inicia un kernel para esta conexion
        sg.msg("Iniciando kernel para cliente..");
        kernel_server kserver = new kernel_server(this, nAvatar, jserverGUI);
        kserver.start();

        sg.msg("Kernel nuevo iniciado..");
        sg.msg("Iniciando interfaz cliente servidor.. Escuchando cliente... ");


        while(clienteSocket.isConnected()){
            try {
                String mensaje = (String) flujoEntrada.readObject();
                //procesa orden de cliente
                ordenes.add(mensaje);
                if(kserver.getState() == Thread.State.WAITING) kserver.despierte();
                sg.msg("Orden recibida y procesada : " + mensaje);
                
            }catch (Exception ex) {
                sg.msg("Error en ManejaConexionCliente : " + ex.getMessage());
            }
        }

    }





}
