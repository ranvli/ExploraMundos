/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package capaDatos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author William Medina Romero
 * Universidad Latina de Costa Rica
 * carne no. 2208020576
 */
public class AdmDatos implements Serializable{


    public String[][] cargaTops(String name){


        try{
            FileInputStream atops = new FileInputStream(name+".top");
            ObjectInputStream dtops = new ObjectInputStream(atops);

            String matrizTops[][] = (String[][]) dtops.readObject();
            dtops.close();
            return matrizTops;

        }
        catch(FileNotFoundException fnf){
            String fnfo[][] = {{"fnf","fnf"}}; //devuelve codigo para ser interpretado por la clase
            return fnfo;
        }
        catch(Exception e){
            return null;
        }

    }

//metodo para salvar archivo de matrices de top tens
    public void salvaTops(String path, Object tops){

        try{
            FileOutputStream atops = new FileOutputStream(path+".top");
            ObjectOutputStream dtops = new ObjectOutputStream(atops);

            dtops.writeObject(tops);
            dtops.close();

        }catch(Exception e){}

    }

//carga matrices del mapa
    //Recibe 1 para retornar matriz del mapa
    //2 para retornar matriz de premios
    //3 para retornar los settings del mapa
    
    public boolean salvaArchivo(String arch,  int[][] mM, int[][] mTops, int[][] settings){


        try{

            FileOutputStream archivo = new FileOutputStream(arch);
            ObjectOutputStream datos = new ObjectOutputStream(archivo);

            datos.writeObject(mM);
            datos.writeObject(mTops);
            datos.writeObject(settings);
            datos.close();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error desconocido : " + e.getMessage());
        }

        return false;
        
        
    }
    public int[][] cargaMapa(String name, int ret) {


    try{
        FileInputStream archivo = new FileInputStream(name);
        ObjectInputStream datos = new ObjectInputStream(archivo);

        
        
        

        if (ret == 1){
            int matrizMapa[][] = (int[][]) datos.readObject();
            datos.close();
            return matrizMapa;
        }
        if (ret == 2){
            int matrizMapa[][] = (int[][]) datos.readObject();
            int matrizPremios[][] = (int[][]) datos.readObject();
            datos.close();
            return matrizPremios;
        }
        if (ret == 3){
            int matrizMapa[][] = (int[][]) datos.readObject();
            int matrizPremios[][] = (int[][]) datos.readObject();
            int settings[][] = (int[][]) datos.readObject();
            datos.close();
            return settings;
        }
        else return null;

    }


    catch (FileNotFoundException fnf){
        JOptionPane.showMessageDialog(null, "Error! Archivo " + name +  " no se encuentra");
        return null;
    }
    catch(IOException io){
        //JOptionPane.showMessageDialog(null, "Error de E/S : " + io.getMessage());
        int [][] rtmp = {{-1,-1}};
        return rtmp;
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error desconocido : " + e.getMessage());
        return null;
    }






    }
    
    public boolean chequeaCompatibilidad(String name) {

        
    try{
        
        FileInputStream archivo = new FileInputStream(name);
        ObjectInputStream datos = new ObjectInputStream(archivo);
        
        //Leer el primer bloque de datos para ver si es el mapa

            int matrizMapa[][] = (int[][]) datos.readObject();
            if(matrizMapa.length > 14 || matrizMapa[0].length > 9){
                JOptionPane.showMessageDialog(null, "Las dimensiones del mundo no son soportadas por ExploraMundos v1.0. Las dimensiones aceptadas son un maximo de x=14 y=9");
                return false;
            }
            for(int i = 0; i < matrizMapa.length; i++){
                for (int i2=0; i2 < matrizMapa[0].length; i2++){
                    int vCheck = matrizMapa[i][i2];
                    if (vCheck < 0 || vCheck > 3){
                         JOptionPane.showMessageDialog(null, "Hay enteros en la matriz del mapa que no se reconocen.");
                         return false;
                    }
                }
                return true;
            }
        

        datos.close();
        
        }

        catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null, "Error! Archivo " + name +  " no se encuentra");
        }

        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error chequeando compatibilidad : " + e.getMessage());
        }
    
        return false;
    
    
    }



        public void salvaMapa(String path, String name, Object mMapa, Object mPremios, Object settings) {


        String top[][] = { {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"} ,{"","0"}, {"","0"} };


        try{
            FileOutputStream aTops = new FileOutputStream(path +".top");
            ObjectOutputStream dTops = new ObjectOutputStream(aTops);

            dTops.flush();
            dTops.writeObject(top);
            dTops.close();

        }catch(Exception e){}

        try{
            FileOutputStream archivo = new FileOutputStream(path);
            ObjectOutputStream datos = new ObjectOutputStream(archivo);

            //resetea top ten
            
            datos.flush();
            datos.writeObject(mMapa);
            datos.writeObject(mPremios);
            datos.writeObject(settings);


            datos.close();
            JOptionPane.showMessageDialog(null, "Mapa ha sido salvado.");
        }

    catch(FileNotFoundException fnf){
        JOptionPane.showMessageDialog(null, "Archivo de datos no se encuentra.");
    }

    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"Error desconocido : " + e);
    }



    }



}
