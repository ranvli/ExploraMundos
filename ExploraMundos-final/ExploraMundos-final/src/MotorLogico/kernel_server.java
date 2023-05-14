/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MotorLogico;
import capaInterfaz.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Este programa no tiene ningún segmento copiado de internet
 * todo el código fué investigado y siguiendo ejemplos similares a códigos actuales de libros e internet.
 *
 * @author Randall Vargas Li
 * Universidad Internacional de las Americas
 * Carné 98011220
 */


/**
 * Este metodo es el kernel, lee las ordenes del arraylist en clase control y se las pasa
 * a Clase Juego para ser ejecutadas..
 *
 * @param
 */
public class kernel_server extends Thread implements Serializable {

    manejaConexionCliente control;
    //Juego juego;
    capaInterfaz.Juego_Server juego;

    int idle=0;
    int nAvatar;
    public boolean parar = false;

    

//
//    public kernel_server(Control aThis, Juego juego) {
//        control = aThis;
//        this.juego = juego;
//    }




    kernel_server(manejaConexionCliente control, int nAvatar, Juego_Server jserverGUI) {

        this.control = control;
        this.nAvatar = nAvatar;
        juego = jserverGUI;

    }

    public synchronized void despierte(){
        
        notify();
        
        
    }

    @Override
    public synchronized void run() {

      char siguienteOrden = 'x';

      //inicia el timer del juego para controlar varias varas como el tiempo, la ventanita del chat, el idle, etc..
      timer tiempo = new timer(juego, nAvatar);
      tiempo.start();
      
      while(!parar){
          
        //lee siuiente orden del arraylist en Control
        if (control.ordenes.size() == 0){
            siguienteOrden = 'x'; //si no hay entonces esperar a ke haya
                try {
                    wait();
                } catch (InterruptedException ex) {
                    
                }
        }
        else {
            Object so = control.ordenes.get(0);
            siguienteOrden = (char) so.toString().charAt(0);
            control.ordenes.remove(0);
            //control.leerLista();
        }

        //indicador de inactividad reseteado a cero
        juego.idle = 0;

          //procesa orden
          switch(siguienteOrden){
            case 'u':
                //verificar fuera de rango para caminar
                if (((juego.posAvatarY[nAvatar] - 60) / 60 < 0)){
                    new capaDatos.tocaWav("fuerarango.wav").start();
                    juego.iniChat(nAvatar,"El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                    siguienteOrden =  'x';
                } else {
                    juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                    juego.bcaminaArriba(nAvatar);
                }
                break;
            case 'd':
                //verificar fuera de rango para caminar
                if (((juego.posAvatarY[nAvatar] + 60) / 60 >= juego.yLargo)){
                    new capaDatos.tocaWav("fuerarango.wav").start();
                    juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                    siguienteOrden =  'x';
                } 
                else {
                    juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                    juego.bCaminaAbajo(nAvatar);
                }
                break;
            case 'l':
               //verificar fuera de rango para caminar
                if (((juego.posAvatarX[nAvatar] - 45) / 45 < 0)){
                    new capaDatos.tocaWav("fuerarango.wav").start();
                    juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                    siguienteOrden =  'x';
                } else {
                    juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                    juego.bCaminaIzquierda(nAvatar);
                }
                break;
            case 'r':
                //verificar fuera de rango para caminar
                if (((juego.posAvatarX[nAvatar] + 45) / 45 >= juego.xLargo)){
                    new capaDatos.tocaWav("fuerarango.wav").start();
                    siguienteOrden =  'x';
                } else {
                    juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                    juego.bCaminaDerecha(nAvatar);
                }
                break;
            case 'a':
                juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                juego.bEscabar(nAvatar);
                break;
            case 'b':
                if (juego.avatars[nAvatar].getIcon().equals(juego.iconAvatarAtras)){
                    //verificar fuera de rango para brincar
                    if (((juego.posAvatarY[nAvatar] - 120) / 60 < 0)){
                        new capaDatos.tocaWav("fuerarango.wav").start();
                        juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                        siguienteOrden =  'x';
                    } else{
                        juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                        juego.bBrincaArriba(nAvatar);
                    }
                }
                if (juego.avatars[nAvatar].getIcon().equals(juego.iconAvatarDerecha)){
                    if (((juego.posAvatarX[nAvatar] + 90) / 45 >= juego.xLargo)){
                    new capaDatos.tocaWav("fuerarango.wav").start();
                    juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                    siguienteOrden =  'x';
                    } else{
                        juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                        juego.bBrincaDerecha(nAvatar);
                    }
                }
                if (juego.avatars[nAvatar].getIcon().equals(juego.iconAvatarFrente)){
                    if (((juego.posAvatarY[nAvatar] + 120) / 60 >= juego.yLargo)){
                        new capaDatos.tocaWav("fuerarango.wav").start();
                        juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                        siguienteOrden =  'x';
                    } else {
                        juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                        juego.bBrincaAbajo(nAvatar);
                    }
                }
                if (juego.avatars[nAvatar].getIcon().equals(juego.iconAvatarIzquierda)){
                    //verificar fuera de rango para caminar
                    if (((juego.posAvatarX[nAvatar] - 90) / 45 < 0)){
                        new capaDatos.tocaWav("fuerarango.wav").start();
                        juego.iniChat(nAvatar, "El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
                        siguienteOrden =  'x';
                    } else{
                        juego.enviaMensajeAtodos(siguienteOrden + String.valueOf(nAvatar));
                        juego.bBrincaIzquierda(nAvatar);
                    }
                }
                break;
        } //end select

        if (siguienteOrden != 'x') juego.actualizaTurnos(nAvatar); //si llega a este punto y la orden es todavia != x entonces la orden fue ejecutada con exito y rebaja turno

    }//Wend

      tiempo.parar=true;
      

  }

    private void espere(int i) {

        //metodo espere es un sleep en realidad
        Thread.yield();
        
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {

        }


    }





}
