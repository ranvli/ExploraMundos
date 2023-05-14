/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MotorLogico;

import java.util.ArrayList;

/**
 *
 * @author William Medina Romero
 * Universidad Latina de Costa Rica
 * Carne no. 2008020576
 */
public class ExploraMundos {

    private ArrayList listJugadores;

    public ArrayList getListJugadores() {
        return listJugadores;
    }

    public ExploraMundos() {
        listJugadores = new ArrayList();
    }

    public void registrarJugador(String nombre) {
        Jugador nuevo = new Jugador(nombre);
        listJugadores.add(nuevo);
    }

    public String obtenerListaJugadores() {
        String h = "";

        for (int i = 0; i < listJugadores.size(); i++) {
            Jugador tmp = (Jugador) listJugadores.get(i);
            h += tmp.toString() + "\n";
        }
        return h;
    }

    public Jugador buscaJugadorPorNombre(String nomJugador) {
        for (int i = 0; i < listJugadores.size(); i++) {
            Jugador tmp = (Jugador) listJugadores.get(i);
            if (tmp.nombre.equals(nomJugador)) {
                return tmp;
            }
        }
        return null;
    }

    /*
     * Este metodo retorna una matriz creada con numeros aleatorios del 1 al 3
     * 1: significa que la celda contiene una bomba
     * 2: significa que la celda contiene un premio o dinero
     * 3: significa que la celda contiene una un obstaculo
     *
     * @PARAM double cantBombas       se refiere al PORCENTAJE de bombas en el mundo
     *        double cantPremios      se refiere al PORCENTAJE de premios en el mundo
     *        double cantObstaculos   se refiere al PORCENTAJE de obtaculos en el mundo
     *        int tamañoX             se refiere al tamaño x de la matriz del mundo
     *        int tamañoY             se refiere al tamaño y de la matriz del mundo
     *
     * @RETURN matriz --> int [][]
     *
     *
     */
    public int[][] crearMatrizAleatoria(double cantBombas, double cantPremios, double cantObstaculos, int tamañoX, int tamañoY) {
        int matriz[][] = new int[tamañoX][tamañoY];

        int areaDelMundo = tamañoX * tamañoY;
        double areaDeBombas = (cantBombas / areaDelMundo) * 100;


        for (int i = 0; i < areaDeBombas; i++)//mientras i sea menor que el area de las BOMBAS que se quieren ingresar
        {

            int rX = (int) Math.random() * tamañoX;
            int rY = (int) Math.random() * tamañoY;

            if (matriz[rX][rY] == 0)//Si la localizacion especificada se encuentra un 0
                                   //quiere decir que no hay nada en esta celda y por lo tanto
                                  //podemos introducir una bomba
            {
               matriz[rX][rY] = 1;
            }
            else
             {
               i--;//si la localizacion ya tiene algo introducido entonces le resto 1 a la i
               //para que vuelva a hacer la subrutina hasta que que encuentra una localizacion libre
             }
        }


        double areaPremio = (cantPremios/areaDelMundo) * 100;

        for (int i = 0; i < areaPremio; i++)
        {
                  int rX = (int) Math.random() * tamañoX;
            int rY = (int) Math.random() * tamañoY;

            if (matriz[rX][rY] == 0)
            {
               matriz[rX][rY] = 2;
            }
            else
             {
               i--;
            }

            
        }

       double areaObstaculo = (cantObstaculos/areaDelMundo) * 100;

        for (int i = 0; i < areaPremio; i++)
        {
                  int rX = (int) Math.random() * tamañoX;
            int rY = (int) Math.random() * tamañoY;

            if (matriz[rX][rY] == 0)
            {
               matriz[rX][rY] = 3;
            }
            else
             {
               i--;
            }


        }


       return matriz;

        }




    }
