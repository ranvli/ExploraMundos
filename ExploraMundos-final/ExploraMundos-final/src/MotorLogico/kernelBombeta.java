///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package MotorLogico;
//import capaInterfaz.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * Este programa no tiene ningún segmento copiado de internet
// * todo el código fué investigado y siguiendo ejemplos similares a códigos actuales de libros e internet.
// *
// * @author Randall Vargas Li
// * Universidad Internacional de las Americas
// * Carné 98011220
// */
//
//
///**
// * Este metodo es el kernel, lee las ordenes del arraylist en clase control y se las pasa
// * a Clase Juego para ser ejecutadas..
// *
// * @param
// */
//public class kernelBombeta extends Thread {
//
//    Control control;
//    Juego juego;
//    int idle=0;
//    public boolean parar = false;
//
//    public kernelBombeta(Control aThis, Juego juego) {
//        control = aThis;
//        this.juego = juego;
//    }
//
//    public synchronized void despierte(){
//
//        notify();
//
//
//    }
//
//    @Override
//    public synchronized void run() {
//
//      char siguienteOrden = 'x';
//
//      //inicia el timer del juego para controlar varias varas como el tiempo, la ventanita del chat, el idle, etc..
////      timer tiempo = new timer(juego);
////      tiempo.start();
////
//      while(!parar){
//
//        //lee siuiente orden del arraylist en Control
//        if (control.ordenes.size() == 0){
//            siguienteOrden = 'x'; //si no hay entonces esperar a ke haya
//                try {
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(kernelBombeta.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }
//        else {
//            Object so = control.ordenes.get(0);
//            siguienteOrden = (char) so.toString().charAt(0);
//            control.ordenes.remove(0);
//            control.leerLista();
//        }
//
//        //indicador de inactividad reseteado a cero
//        juego.idle = 0;
//
//          //procesa orden
//          switch(siguienteOrden){
//            case 'u':
//                //verificar fuera de rango para caminar
//                if (((juego.posAvatarY - 60) / 60 < 0)){
//                    new capaDatos.tocaWav("fuerarango.wav").start();
//                    juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                    siguienteOrden =  'x';
//                } else juego.bcaminaArriba();
//                break;
//            case 'd':
//                //verificar fuera de rango para caminar
//                if (((juego.posAvatarY + 60) / 60 >= juego.yLargo)){
//                    new capaDatos.tocaWav("fuerarango.wav").start();
//                    juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                    siguienteOrden =  'x';
//                }
//                else {
//                    juego.bCaminaAbajo();
//                }
//                break;
//            case 'l':
//               //verificar fuera de rango para caminar
//                if (((juego.posAvatarX - 45) / 45 < 0)){
//                    new capaDatos.tocaWav("fuerarango.wav").start();
//                    juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                    siguienteOrden =  'x';
//                } else juego.bCaminaIzquierda();
//                break;
//            case 'r':
//                //verificar fuera de rango para caminar
//                if (((juego.posAvatarX + 45) / 45 >= juego.xLargo)){
//                    new capaDatos.tocaWav("fuerarango.wav").start();
//                    siguienteOrden =  'x';
//                } else juego.bCaminaDerecha();
//                break;
//            case 'a':
//                juego.bEscabar();
//                break;
//            case 'b':
//                if (juego.avatars[1].getIcon().equals(juego.iconAvatarAtras)){
//                    //verificar fuera de rango para brincar
//                    if (((juego.posAvatarY - 120) / 60 < 0)){
//                        new capaDatos.tocaWav("fuerarango.wav").start();
//                        juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                        siguienteOrden =  'x';
//                    } else juego.bBrincaArriba();
//                }
//                if (juego.avatars[1].getIcon().equals(juego.iconAvatarDerecha)){
//                    if (((juego.posAvatarX + 90) / 45 >= juego.xLargo)){
//                    new capaDatos.tocaWav("fuerarango.wav").start();
//                    juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                    siguienteOrden =  'x';
//                    } else juego.bBrincaDerecha();
//                }
//                if (juego.avatars[1].getIcon().equals(juego.iconAvatarFrente)){
//                    if (((juego.posAvatarY + 120) / 60 >= juego.yLargo)){
//                        new capaDatos.tocaWav("fuerarango.wav").start();
//                        juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                        siguienteOrden =  'x';
//                    } else juego.bBrincaAbajo();
//                }
//                if (juego.avatars[1].getIcon().equals(juego.iconAvatarIzquierda)){
//                    //verificar fuera de rango para caminar
//                    if (((juego.posAvatarX - 90) / 45 < 0)){
//                        new capaDatos.tocaWav("fuerarango.wav").start();
//                        juego.iniChat("El destino esta fuera de rango, si prosigo se le cae el programa con un error IndexOutOfBoundsException.. Je JE!", 5);
//                        siguienteOrden =  'x';
//                    } else juego.bBrincaIzquierda();
//                }
//                break;
//        } //end select
//
//        if (siguienteOrden != 'x') juego.actualizaTurnos(); //si llega a este punto y la orden es todavia != x entonces la orden fue ejecutada con exito y rebaja turno
//
//    }//Wend
//
//      //tiempo.parar=true;
//
//
//  }
//
//    private void espere(int i) {
//
//        //metodo espere es un sleep en realidad
//        Thread.yield();
//
//        try {
//            Thread.sleep(i);
//        } catch (InterruptedException ex) {
//
//        }
//
//
//    }
//
//
//
//
//
//}
