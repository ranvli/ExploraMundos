/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MotorLogico;

/**
 *Esta clase controla variables en la clase juego, como el Idle, y los puntos y el chat
 * @author RanVLi
 */
public class timer extends Thread {

    capaInterfaz.Juego_Server juego;
    public boolean parar = false;
    int nAvatar;

    public timer(capaInterfaz.Juego_Server juego, int nAvatar) {
        this.juego = juego;
        this.nAvatar = nAvatar;
    }


    @Override
    public void run() {



        while(!parar){
            juego.time[nAvatar]-=1;
            juego.actualizaPuntos(nAvatar);
            juego.refrescaTimer(nAvatar);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }

        }

    }



}
