/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Juego.java
 *
 * Created on 18/03/2009, 11:10:26 PM
 */

package capaInterfaz;


import MotorLogico.serverConns;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;




/**
 *
 * @author Randall Vargas Li
 */
public class Juego_Server extends javax.swing.JFrame implements Serializable {

    //Todo lo de abajo es parecido a las variables del Editor, solo se comentarian las diferentes

    //maximo tanano en x y y del mapa (en coordenadas enteras)
    public int xLargo;
    public int yLargo;

    //Delay entre animacion y animacion
    int vDelay;
    int hDelay;

    //controla tiempo inactivo
    public int idle = 0;

    ImageIcon fondo1 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande1.jpg"));
    ImageIcon fondo2 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande2.jpg"));
    ImageIcon fondo3 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande3.jpg"));
    ImageIcon fondo4 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande4.jpg"));
    ImageIcon fondo5 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande5.jpg"));

    //Icon uincognita (cajita) y escarbado?
    ImageIcon incognita1 = new ImageIcon(getClass().getResource("/imagenes/incognita1.png"));
    ImageIcon escarbado1 = new ImageIcon(getClass().getResource("/imagenes/escarbado1.png"));

    //posicion actual avatar sin dividir x/45 y/60
    public int posAvatarX[] = new int[8];
    public int posAvatarY[] = new int[8];

    public int numAvatars = 0;

    int xINIavatar = 0;
    int yINIavatar = 0;

    //Variables de juego
    int puntaje[] = new int[8];
    int vidas[] = new int[8];
    public int time[] = new int[8];

    int vidasMapa;
    int timeMapa;
    int turnosMapa;

    public int[][] mMapa;
    public int[][] mPremios;

    int turnos[] = new int[8];
    boolean avatarIniciado = false;

    int posLabels[][];
    JLabel jlNuevo[] = new JLabel[256];
    int jln = -1;

    int mascara;

    int setClearChat[] = new int[8];

    //Coordenadas de control de la ventana del chat
    int paY;
    int paX;


    public JLabel avatars[] = new JLabel[8];
    JLabel imgChat[] = new JLabel[8];
    JTextArea txtChat[] = new JTextArea[8];

    ImageIcon iconBomba = new ImageIcon(getClass().getResource("/Imagenes/bomba.png"));
    ImageIcon iconPlata = new ImageIcon(getClass().getResource("/Imagenes/diamante.png"));
    ImageIcon iconPiedra = new ImageIcon(getClass().getResource("/Imagenes/piedra.png"));
    ImageIcon iconBorde = new ImageIcon(getClass().getResource("/Imagenes/borde.png"));
    ImageIcon iconBlanco = new ImageIcon("");

    public ImageIcon iconAvatarAtras = new ImageIcon(getClass().getResource("/Imagenes/parado-atras.png"));
    public ImageIcon iconAvatarFrente = new ImageIcon(getClass().getResource("/Imagenes/parado-frente.png"));
    public ImageIcon iconAvatarDerecha = new ImageIcon(getClass().getResource("/Imagenes/parado-derecha.png"));
    public ImageIcon iconAvatarIzquierda = new ImageIcon(getClass().getResource("/Imagenes/parado-izquierda.png"));

    ImageIcon chatCentro = new ImageIcon(getClass().getResource("/imagenes/chat-cent.png"));
    ImageIcon chatDer = new ImageIcon(getClass().getResource("/imagenes/chat-Der.png"));
    ImageIcon chatIzq = new ImageIcon(getClass().getResource("/imagenes/chat-Izq.png"));

    //iconos de ventana de chat
    ImageIcon chatCentroAbajo = new ImageIcon(getClass().getResource("/imagenes/chat-cent-abajo.png"));
    ImageIcon chatDerAbajo = new ImageIcon(getClass().getResource("/imagenes/chat-Der-abajo.png"));
    ImageIcon chatIzqAbajo = new ImageIcon(getClass().getResource("/imagenes/chat-Izq-abajo.png"));

    final JFileChooser fc = new JFileChooser();

    //puente puente
    capaDatos.AdmDatos capaDatos = new capaDatos.AdmDatos();
    MotorLogico.serverConns sCon;

    File mapaCagado;

    Control control;

    /** Creates new form Juego */
   
    public Juego_Server(serverConns sCon, File mapaCagado) {
        initComponents();

        for(int i = 0; i < 8; i++){
            imgChat[i] = new JLabel();
            txtChat[i] = new JTextArea();
        }


        this.sCon = sCon;

        this.mapaCagado = mapaCagado;
        if (!(mapaCagado == null)){
            cargaMapa(mapaCagado.toString());
        }


    }

    private Juego_Server() {
        throw new UnsupportedOperationException("Not yet implemented");
    }




    public void bBrincaAbajo(int nAvatar) {


       int xDest = posAvatarX[nAvatar] / 45;
       int yDest = (posAvatarY[nAvatar]+120) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           //aqui piedras
           iniChat(nAvatar, "Hay piedras abajo, si brinco caigo encima y me desmadro!", 5);
           return;
       }


       AnimBrincaAbajo(nAvatar);




    }

    public void bBrincaArriba(int nAvatar) {



       int xDest = posAvatarX[nAvatar] / 45;
       int yDest = (posAvatarY[nAvatar]-120) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           iniChat(nAvatar, "Hay piedras arriba, si brinco me descalabro!", 5);
           return;
       }


       AnimBrincaArriba(nAvatar);






    }

    public void bBrincaDerecha(int nAvatar) {



       int xDest = (posAvatarX[nAvatar]+90) / 45;
       int yDest = (posAvatarY[nAvatar]) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           iniChat(nAvatar, "Hay piedras en el destino a la derecha, si brinco les caigo encima y me quiebro toda!", 5);
           return;
       }


       AnimBrincaDerecha(nAvatar);






    }

    public void bBrincaIzquierda(int nAvatar) {




       int xDest = (posAvatarX[nAvatar]-90) / 45;
       int yDest = (posAvatarY[nAvatar]) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           iniChat(nAvatar, "Hay piedras a la izquierda, ha intentado otra ruta?", 5);
           return;
       }

       AnimBrincaIzquierda(nAvatar);



    }

    public void bCaminaAbajo(int nAvatar) {



        //calcula el objeto en posicion futura
       int xDest = posAvatarX[nAvatar] / 45;
       int yDest = (posAvatarY[nAvatar]+60) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           avatars[nAvatar].setIcon(iconAvatarFrente);
           iniChat(nAvatar, "Hay una piedra abajo!", 5);
           return;
       }

       


       AnimCaminaAbajo(nAvatar);

   

    }

    public void bCaminaDerecha(int nAvatar) {



       int xDest = (posAvatarX[nAvatar]+45) / 45;
       int yDest = (posAvatarY[nAvatar]) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           avatars[nAvatar].setIcon(iconAvatarDerecha);
           iniChat(nAvatar, "Me quiebro toda!", 5);
           return;
       }


       AnimCaminaDerecha(nAvatar);



    }

    public void bCaminaIzquierda(int nAvatar) {



       int xDest = (posAvatarX[nAvatar]-45) / 45;
       int yDest = (posAvatarY[nAvatar]) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           avatars[nAvatar].setIcon(iconAvatarIzquierda);
           iniChat(nAvatar, "Hay piedras a la izquierda, no puedo pasarles por encima!", 5);
           return;
       }

       AnimCaminaIzquierda(nAvatar);




    }




    public void bEscabar(int nAvatar) {



       new capaDatos.tocaWav("escarba.wav").start();

       int x = posAvatarX[nAvatar] / 45;
       int y = posAvatarY[nAvatar] / 60;

       int objetoEnDestino = chequeaObjetoPos(x, y);
       int labelDest = posLabels[x][y];

       //nada
       if (objetoEnDestino == 0){
         seSalvo(nAvatar); //animacion escarba
         creaLabel(5, x, y); //icon escarbad
       }

       //bomba
       if (objetoEnDestino == 1){
         explota(labelDest, nAvatar);
         mMapa[x][y] = 0;
       }

       //diamante
       if (objetoEnDestino == 2){
         agarraDiam(labelDest, nAvatar);
         mMapa[x][y] = 0;
       }


       //Si no quedan diamantes entonces decirle que lo hizo bien y si quiere seguir.. Aunque solo bombas quedarian...
       if(chequeaElementosRemaining(2) == 0){
           //int resp = JOptionPane.showConfirmDialog(rootPane, "Has explotado los recursos minerales de este mundo en su totalidad!!\n\nFelicidades!! Ahora eres todo un ser humano! Has explotado este mundo y agotaste\nsus recursos por completo dejando así el mundo inservible!\n\nCOMO BUEN SER HUMANO!!\n\nQuieres seguir explotando este mundo?");
           //x = exploto todo
           enviaMensajeAtodos("x" + String.valueOf(nAvatar));
           finalizaJuego(nAvatar);
       }

       if(chequeaElementosRemaining(0) == 0){
           //JOptionPane.showMessageDialog(rootPane, "Has explorado este mundo en su totalidad!");
           //X mays Exploto todo termina
           enviaMensajeAtodos("X" + String.valueOf(nAvatar));
           finalizaJuego(nAvatar);
       }


    }

    public void bcaminaArriba(int nAvatar) {



       int xDest = posAvatarX[nAvatar] / 45;
       int yDest = (posAvatarY[nAvatar]-60) / 60;

       int objetoEnDestino = chequeaObjetoPos(xDest, yDest);

       if (objetoEnDestino == 3){
           avatars[nAvatar].setIcon(iconAvatarAtras);
           iniChat(nAvatar, "Hay piedras arriba, no puedo pasarles por encima!", 5);
           return;
       }


       AnimCaminaArriba(nAvatar);




    }

    private void AnimBrincaAbajo(int nAvatar) {

    BufferedImage avatar = cargaImg("teleport.png");

    new capaDatos.tocaWav("teleport.WAV").start();

    int lin = 0;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);



    for (int i=0; i < 32; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 8) {
            lin+= 64;
            iCuadro = 0;
        }
        espere(vDelay-1);

        avatars[nAvatar].setLocation(posAvatarX[nAvatar],posAvatarY[nAvatar] + i*4);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i*4, "bd");
        espere(1);
        
        //refrescaElementos();
    }

    posAvatarY[nAvatar]+= 120;

    avatars[nAvatar].setIcon(iconAvatarFrente);



    }


private void AnimBrincaArriba(int nAvatar) {

    BufferedImage avatar = cargaImg("teleport.png");

    new capaDatos.tocaWav("teleport.WAV").start();

    int lin = 0;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);

    for (int i=0; i < 32; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 8) {
            lin+= 64;
            iCuadro = 0;
        }
        espere(vDelay-1);
        //jLabel1.update(jLabel1.getGraphics());
        avatars[nAvatar].setLocation(posAvatarX[nAvatar],posAvatarY[nAvatar] - i*4);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i*4, "bu");
        espere(1);
        //refrescaElementos();
    }

    avatars[nAvatar].setIcon(iconAvatarAtras);
    
    posAvatarY[nAvatar]-= 120;



    }
private void AnimBrincaDerecha(int nAvatar) {

    BufferedImage avatar = cargaImg("teleport.png");

    new capaDatos.tocaWav("teleport.WAV").start();

    int lin = 0;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);

    for (int i=0; i < 32; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 8) {
            lin+= 64;
            iCuadro = 0;
        }
        espere(vDelay-1);
        //jLabel1.update(jLabel1.getGraphics());
        avatars[nAvatar].setLocation(posAvatarX[nAvatar] + i*3 ,posAvatarY[nAvatar]);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i*3, "br");
        espere(1);
        //refrescaElementos();
    }

    avatars[nAvatar].setIcon(iconAvatarDerecha);

    posAvatarX[nAvatar]+= 90;


    }

private void AnimBrincaIzquierda(int nAvatar) {

    BufferedImage avatar = cargaImg("teleport.png");

    new capaDatos.tocaWav("teleport.WAV").start();

    int lin = 0;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);
    
    for (int i=0; i < 32; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 8) {
            lin+= 64;
            iCuadro = 0;
        }
        espere(vDelay-1);
        //jLabel1.update(jLabel1.getGraphics());
        avatars[nAvatar].setLocation(posAvatarX[nAvatar] - i*3 ,posAvatarY[nAvatar]);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i*3, "bl");
        espere(1);
        //refrescaElementos();
    }

    avatars[nAvatar].setIcon(iconAvatarIzquierda);
    
    posAvatarX[nAvatar]-= 90;


    }

    private void actualizaChat(int nAvatar, int i, String direccion) {


        

        if(direccion.equals("d") || direccion.equals("bd")){
            imgChat[nAvatar-1].setLocation(posAvatarX[nAvatar] +paX, (posAvatarY[nAvatar]+i) +paY);
            txtChat[nAvatar-1].setLocation(posAvatarX[nAvatar] + (paX+40), (posAvatarY[nAvatar]+i) +(paY +80));
        }

        if(direccion.equals("u") || direccion.equals("bu") ){
            imgChat[nAvatar-1].setLocation(posAvatarX[nAvatar] +paX, (posAvatarY[nAvatar]-i) +paY);
            txtChat[nAvatar-1].setLocation(posAvatarX[nAvatar] + (paX+40), (posAvatarY[nAvatar]-i) +(paY +80));
        }

        if(direccion.equals("r") || direccion.equals("br") ){
            imgChat[nAvatar-1].setLocation((posAvatarX[nAvatar] +paX)+i, (posAvatarY[nAvatar]) +paY);
            txtChat[nAvatar-1].setLocation((posAvatarX[nAvatar] + (paX+40))+i, (posAvatarY[nAvatar]) +(paY +80));
        }

        if(direccion.equals("l") || direccion.equals("bl")){
            imgChat[nAvatar-1].setLocation((posAvatarX[nAvatar] +paX)-i, (posAvatarY[nAvatar]) +paY);
            txtChat[nAvatar-1].setLocation((posAvatarX[nAvatar] + (paX+40))-i, (posAvatarY[nAvatar]) +(paY +80));
        }




    }

    private int chequeaElementosRemaining(int elemento) {

        int bombas = 0;
        int diamantes = 0;
        int total = 0;

        for (int i = 0; i < mMapa.length; i++){
            for(int i2 = 0; i2 < mMapa[0].length; i2++){
                if (mMapa[i][i2] == 1) bombas++;
                if (mMapa[i][i2] == 2) diamantes++;
                total++;
            }
        }

        if(elemento == 0) return total;
        if(elemento == 1) return bombas;
        if(elemento == 2) return diamantes;
        else return -1;


    }

    private int chequeaObjetoPos(int x, int y) {


        return mMapa[x][y];




    }

    private void chequeaRecords(int nAvatar) {

        String records[][] = capaDatos.cargaTops(mapaCagado.toString());

        //chequea si el puntaje es mayor ke el menor de los tops
        int minRecord = Integer.parseInt(records[9][1]);
        if(puntaje[nAvatar] > minRecord){
            //String nick = JOptionPane.showInputDialog(rootPane, "Has establecido un record en este mundo inmundo!");
            String nick = String.valueOf(nAvatar);
            records[9][0] = nick;
            records[9][1] = String.valueOf(puntaje[nAvatar]);
            capaDatos.salvaTops(mapaCagado.toString(), ordenarPorRecord(records));
        }


    }

    public void enviaMensajeAtodos(String string) {


        for(int c = 0; c < sCon.conexiones.size(); c++){
           ObjectOutputStream oos = (ObjectOutputStream) sCon.conexiones.get(c);
           try {
               oos.writeObject(string);
           } catch (IOException ex) {
               Logger.getLogger(Juego_Server.class.getName()).log(Level.SEVERE, null, ex);
           }
        }



    }

    private void finalizaJuego(int nAvatar) {


        //limpia las órdenes pendientes de ejecutar
//        control.ordenes.clear();
//        control.leerLista();

//        if (control.kernel.parar == false) control.kernel.parar = true;
//        if (control.juegoIniciado == true) control.juegoIniciado = false;
//        //JOptionPane.showMessageDialog(this, "El juego ha sido detenido.");
        avatars[nAvatar].setVisible(false);
        getContentPane().remove(avatars[nAvatar]);
        
        chequeaRecords(nAvatar);

        //this.setVisible(false);
        //control.setVisible(false);


    }

    public void iniChat(int nAvatar, String texto, int tiempo) {

        //valores defecto
        paY = -256;
        paX = -114;

        int chatAvatar = nAvatar -1;


        //agregar a contenedor y set visible y el icon por defecto
        //imgChat = new JLabel();
        //txtChat = new JTextArea();

        getContentPane().add(imgChat[chatAvatar],0);
        imgChat[chatAvatar].setIcon(chatCentro);
        imgChat[chatAvatar].setVisible(true);

        //Si se sale en x o y entonces reacomodarlo
        if(posAvatarY[nAvatar] + paY < 0){
            paY = 48;
            imgChat[chatAvatar].setIcon(chatCentroAbajo);
        }
        if(posAvatarX[nAvatar] + paX < 0){
            paX = -40;
            imgChat[chatAvatar].setIcon(chatDer);
            if (paY == 48) imgChat[chatAvatar].setIcon(chatDerAbajo);
        }

        //colocar la imagen de chat
        imgChat[chatAvatar].setBounds(posAvatarX[nAvatar] + paX, posAvatarY[nAvatar] + paY, 256, 256);

        //Agregar al contenedor el texto de chat
        getContentPane().add(txtChat[chatAvatar],0);
//
//        String l1 = texto + "                                                                                                                                                      ";
//        String l2 = texto + "                                                                                                                                                      ";
//        String l3 = texto + "                                                                                                                                                      ";
//        String l4 = texto + "                                                                                                                                                      ";
//        String l5 = texto + "                                                                                                                                                      ";
//
//        //dividir el texto en 5 lineas de 25 caracteres
//
//        String txt = l1.substring(0,25) + "\n" + l2.substring(25,50) + "\n" + l3.substring(50,75) + "\n" + l4.substring(75,100) + "\n" + l5.substring(100, 125);

        txtChat[chatAvatar].setLineWrap(true);
        txtChat[chatAvatar].setText(texto);

        //setear transparencia (opaque false) y color de texto y colocarlo
        txtChat[chatAvatar].setVisible(true);
        txtChat[chatAvatar].setForeground(Color.ORANGE);
        txtChat[chatAvatar].setOpaque(false);
        txtChat[chatAvatar].setBounds(posAvatarX[nAvatar] + (paX+40), posAvatarY[nAvatar] + (paY+80), 160,120);

        setClearChat[nAvatar] = time[nAvatar] - tiempo;




    }

    private void iniciarAvatar() {

        //inicia avatar[1] ke es parado al frente
        avatars[numAvatars] = new JLabel();
        getContentPane().add(avatars[numAvatars],0);
        avatars[numAvatars].setIcon(iconAvatarFrente);
        avatars[numAvatars].setBounds(xINIavatar, yINIavatar, 45, 60);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tJugador = new javax.swing.JTextField();
        tPuntaje = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tVidas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tTurnos = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        Salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande1.jpg"))); // NOI18N
        jLabel1.setEnabled(false);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel1MouseMoved(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel2.setText("Tiempo remanente segs.");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel3.setText("Puntaje");

        tJugador.setEditable(false);
        tJugador.setText("300");
        tJugador.setAutoscrolls(false);
        tJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tJugadorActionPerformed(evt);
            }
        });
        tJugador.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tJugadorInputMethodTextChanged(evt);
            }
        });

        tPuntaje.setEditable(false);
        tPuntaje.setAutoscrolls(false);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel4.setText("Vidas remanentes");

        tVidas.setEditable(false);
        tVidas.setText("5");
        tVidas.setAutoscrolls(false);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel5.setText("Movidas remanentes");

        tTurnos.setEditable(false);
        tTurnos.setText("30");
        tTurnos.setAutoscrolls(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        jTextField1.setText("Hola");
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 0, 10));
        jButton1.setText("Diga");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
        );

        jMenuBar1.setFont(new java.awt.Font("Arial", 0, 10));

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 11));
        jMenu1.add(jSeparator1);

        Salir.setFont(new java.awt.Font("Arial", 0, 10));
        Salir.setLabel("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tVidas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(tJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, 0, 640, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tVidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked




    }//GEN-LAST:event_jLabel1MouseClicked

    public void agarraDiam(int labelDest, int nAvatar){


        new capaDatos.tocaWav("diama.wav").start();
        
        BufferedImage avatar = cargaImg("agarra-diam.png");

        int lin = 10;
        int iCuadro = 0;

        jlNuevo[labelDest].setIcon(iconBlanco);

        for (int i=0; i < 28; i++){
            Graphics2D g2 = (Graphics2D) jlNuevo[labelDest].getGraphics();
            g2.drawImage(avatar, 0,0,45,60, 19+(iCuadro*64), lin,     51+(iCuadro*64),31+lin,  this);
            iCuadro++;
            if(iCuadro == 8) {
                lin+= 68 ;
                iCuadro = 0;
            }
            espere(100);
            jLabel1.update(jLabel1.getGraphics());
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            refrescaElementos();
    }



        //cambiar label a escarbado
        jlNuevo[labelDest].setIcon(escarbado1);

        ////remover label descubierto
        //jlNuevo[labelDest].setVisible(false);
        //getContentPane().remove(jlNuevo[labelDest]);

        //actualiza puntaje
        puntaje[nAvatar]+= mPremios[posAvatarX[nAvatar]/45][posAvatarY[nAvatar]/60];
        tPuntaje.setText(String.valueOf(puntaje));

        int encontradoPremioDe = mPremios[posAvatarX[nAvatar]/45][posAvatarY[nAvatar]/60];
        iniChat(nAvatar, "Plata! Que dicha! Estaba limpio! Encontré : " + encontradoPremioDe, 10);
        
        
    }

    public void explota(int labelDest, int nAvatar){
        
        

        BufferedImage avatar = cargaImg("bomb_anim.png");

        int lin = 12;
        int iCuadro = 0;


        //muestra bomba
        jlNuevo[labelDest].setVisible(true);


        //Sonido mecha
        new capaDatos.tocaWav("mecha.wav").start();
        
        for (int i=0; i < 14; i++){
            Graphics2D g2 = (Graphics2D) jlNuevo[labelDest].getGraphics();
            g2.drawImage(avatar, 0,0,45,60, 19+(iCuadro*64), lin,     51+(iCuadro*64),31+lin,  this);
            iCuadro++;
            if(iCuadro == 4) {
                lin+= 68 ;
                iCuadro = 0;
            }
            if (i == 5) new capaDatos.tocaWav("explota1.wav").start();
            espere(250);
            jLabel1.update(jLabel1.getGraphics());
            refrescaElementos();
    }

//        control.ordenes.clear();
//        control.leerLista();

        //cambiar label a escarbado
        jlNuevo[labelDest].setIcon(escarbado1);

        
        posAvatarX[nAvatar] = xINIavatar;
        posAvatarY[nAvatar] = yINIavatar;
        avatars[nAvatar].setLocation(xINIavatar, yINIavatar);
        
        //baja vidasw
        vidas[nAvatar]--;
        //tVidas.setText(String.valueOf(vidas));

        

        iniChat(nAvatar, "Ay! Caramba!! Eso dolió", 5);

        if (vidas[nAvatar] < 0){
            //JOptionPane.showMessageDialog(rootPane, "Se le agotaron las vidas! ja ja!");
            //v = vidas out
            enviaMensajeAtodos("v" + String.valueOf(nAvatar));
            finalizaJuego(nAvatar);
            iniChat(nAvatar, "El jugador " + nAvatar + " ha muerto el mongolo.", 5);
        }


        
        

        
    }


    private void AnimCaminaAbajo(int nAvatar){


    BufferedImage avatar = cargaImg("ha.png");

    int lin = 0;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);

    for (int i=0; i < 60; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 15 && lin == 0) {
            lin = 64;
            iCuadro = 0;
        }
        if(iCuadro == 4 && lin == 64) {
            lin = 0;
            iCuadro = 0;
        }
        if (i % 10 == 0) new capaDatos.tocaWav("paso.wav").start(); //sonido pasos

        espere(vDelay-1);
        avatars[nAvatar].setLocation(posAvatarX[nAvatar],posAvatarY[nAvatar] + i);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);

        actualizaChat(nAvatar, i, "d");
        espere(1);

    }

    avatars[nAvatar].setIcon(iconAvatarFrente);
    
    posAvatarY[nAvatar]+= 60;



    }


    public void AnimCaminaArriba(int nAvatar){


    BufferedImage avatar = cargaImg("ha.png");

    int lin = 7 *64;
    int iCuadro = 0;

    avatars[nAvatar].setIcon(iconBlanco);

    for (int i=0; i < 60; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 15 && lin == 7 *64) {
            lin = 8 *64;
            iCuadro = 0;
        }
        if(iCuadro == 4 && lin == 8 *64) {
            lin = 7 *64;
            iCuadro = 0;
        }
        if (i % 10 == 0) new capaDatos.tocaWav("paso.wav").start(); //sonido pasos
        espere(vDelay-1);
        avatars[nAvatar].setLocation(posAvatarX[nAvatar],posAvatarY[nAvatar] - i);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i, "u");
        espere(1);
    }

    avatars[nAvatar].setIcon(iconAvatarAtras);
    
    posAvatarY[nAvatar]-= 60;




    }


    public void AnimCaminaDerecha(int nAvatar){


    BufferedImage avatar = cargaImg("ha.png");

    int lin = 3*64;
    int iCuadro = 8;

    avatars[nAvatar].setIcon(iconBlanco);

    for (int i=0; i < 45; i++){
        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 15 && lin == 3*64) {
            lin = 4*64;
            iCuadro = 0;
        }
        if(iCuadro == 11 && lin == 4*64) {
            lin = 3*64;
            iCuadro = 8;
        }
        if (i % 10 == 0) new capaDatos.tocaWav("paso.wav").start(); //sonido pasos
        espere(hDelay-1);
        avatars[nAvatar].setLocation(posAvatarX[nAvatar] +i,posAvatarY[nAvatar]);
        g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        actualizaChat(nAvatar, i, "r");
        espere(1);
    }

    avatars[nAvatar].setIcon(iconAvatarDerecha);
    
    posAvatarX[nAvatar]+= 45;

    }


    public void AnimCaminaIzquierda(int nAvatar){


    BufferedImage avatar = cargaImg("ha.png");

    int lin = 10*64;
    int iCuadro = 8;

    avatars[nAvatar].setIcon(iconBlanco);
  

    for (int i=0; i < 45; i++){

        Graphics2D g2 = (Graphics2D) avatars[nAvatar].getGraphics();
        g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
        iCuadro++;
        if(iCuadro == 15 && lin == 10*64) {
            lin = 11*64;
            iCuadro = 0;
        }
        if(iCuadro == 11 && lin == 11*64) {
            lin = 10*64;
            iCuadro = 8;
        }

       if (i % 10 == 0) new capaDatos.tocaWav("paso.wav").start(); //sonido pasos
       espere(hDelay-1);

       avatars[nAvatar].setLocation(posAvatarX[nAvatar] -i,posAvatarY[nAvatar]);

       g2 = (Graphics2D) avatars[nAvatar].getGraphics();
       g2.drawImage(avatar, 0,0,45,60, 15+(iCuadro*64),lin,57+(iCuadro*64),63+lin, this);
       actualizaChat(nAvatar, i, "l");
       espere(1);
        
    }

    avatars[nAvatar].setIcon(iconAvatarIzquierda);
    
    posAvatarX[nAvatar]-= 45;

    }
    
    
    private void espere(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
        }


    }

    public static BufferedImage cargaImg(String ref) {
        BufferedImage bimg = null;
        try {

            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered


    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited


    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseMoved


    }//GEN-LAST:event_jLabel1MouseMoved

    private void creaLabel(int vV, int i, int i2) {

        ImageIcon iconoE = null;
        jln++;

        if (vV == 1) iconoE = iconBomba;
        if (vV == 2) iconoE = iconPlata;
        if (vV == 3) iconoE = iconPiedra;
        if (vV == 4) iconoE = iconBorde;
        if (vV == 5) iconoE = escarbado1;

        jlNuevo[jln] = new JLabel("");
        jlNuevo[jln].setIcon(iconoE);

        //Si es un objeto ordinario entonces el Zorder = 0 si no Zorder = 1 porke la senal de
        //que fue escarbado aparecería encima del avatar y no sobre el mapa.
        if(vV == 5) getContentPane().add(jlNuevo[jln],5);
        if(vV != 5) getContentPane().add(jlNuevo[jln],0);

        //getContentPane().add(jlNuevo[jln],1);

        //crear label
        jlNuevo[jln].setBounds(i*45,i2*60, 45,60);
        if (vV != 4) posLabels[i][i2] = jln; //Guarda el numero de label en esa posicion para posterior uso..

        //si son bombas o premios entonces esconderlos o enmascararlos
        if (mascara == 0) if (vV == 1 || vV == 2) jlNuevo[jln].setVisible(false);
        if (mascara == 1) if (vV == 1 || vV == 2) jlNuevo[jln].setIcon(incognita1);

        jlNuevo[jln].update(jlNuevo[jln].getGraphics());



    }

    private void cargaMapa(String name){


        boolean compat = capaDatos.chequeaCompatibilidad(name);
        if (!compat){
            JOptionPane.showMessageDialog(rootPane, "El archivo no es compatible con ExploraMundos v.1.0");
            return;
        }

        int matrizMapa[][] = capaDatos.cargaMapa(name, 1);
        
        xLargo = matrizMapa.length;
        yLargo = matrizMapa[0].length;

        posLabels = new int[xLargo][yLargo];
        nuevoMapa(xLargo, yLargo);

        borraElementos();

        mMapa = matrizMapa;

        //carga matriz premios
        mPremios = capaDatos.cargaMapa(name, 2);

        if (mPremios[0][0] == -1){
            JOptionPane.showMessageDialog(rootPane, "No se encuentra matriz de premios, asumiendo todos los premios de 1000");
            int mp[][] = new int[matrizMapa.length][matrizMapa[0].length];
            for(int i = 0; i < matrizMapa.length; i++){
                for (int i2 = 0; i2 < matrizMapa[0].length; i2++){
                    mp[i][i2] = 1000;
                }
            }
            mPremios = mp;
        }


        //carga settings
        int settings[][] = capaDatos.cargaMapa(mapaCagado.toString(),3);

        if (settings[0][0] == -1){
            JOptionPane.showMessageDialog(rootPane, "No se encuentra configuración del mundo, asumiendo valores por defecto.");
//            tVidas.setText("5");
//            vidas = 5;
//            time = 300;
//            turnos = 50;
//            setFondo(1);
//            vDelay = 40;
//            hDelay = 44;
//            mascara = 1;
        }
        else{
            vidasMapa = settings[0][0];
            //tVidas.setText(String.valueOf(vidas));
            timeMapa = settings[0][1];
            turnosMapa = settings[0][2];
            setFondo(settings[0][3]);
            vDelay = settings[0][4];
            hDelay = settings[0][5];
            mascara = settings[0][6];
            xINIavatar = settings[0][7] * 45;
            yINIavatar = settings[1][7] * 60;
            if (vDelay == 0) vDelay = 1;
            if (hDelay == 0) hDelay = 1;
            tTurnos.setText(String.valueOf(turnos));

        }

        //posAvatarX[nAvatar] = xINIavatar;
        //posAvatarY[nAvatar] = yINIavatar;
        
        cargaElementos();
        
        //crear borde
        for(int i = 0; i < yLargo; i++){
            creaLabel(4, xLargo, i);
        }
        for(int i = 0; i <= xLargo; i++){
            creaLabel(4, i, yLargo);
        }

        //carga settings
        

        
}



    private void setFondo(int i) {

        switch(i){
            case 1:
                jLabel1.setIcon(fondo1);
                break;
            case 2:
                jLabel1.setIcon(fondo2);
                break;
            case 3:
                jLabel1.setIcon(fondo3);
                break;
            case 4:
                jLabel1.setIcon(fondo4);
                break;
            case 5:
                jLabel1.setIcon(fondo5);
                break;

        }

    }




    private void borraElementos() {

        for(int i=0; i < 100; i++){
            if (jlNuevo[i] == null) break;
            jlNuevo[i].setVisible(false);
            super.getContentPane().remove(jlNuevo[i]);
        }

    }

    private void cargaElementos() {


        //borra los elementos existentes de un mapa anterior (si hay)
        for(int i=0; i < jln; i++){
            jlNuevo[i].setVisible(false);
            getContentPane().remove(jlNuevo[i]);
        }

        jln=-1;

        for(int i = 0; i < xLargo; i++){
            for(int i2 = 0; i2 < yLargo; i2++){
                int vV = mMapa[i][i2];
                if (vV != 0) creaLabel(vV,i,i2);
            }
        }

        //dibujaGrid();
        //jLabel1.update(jLabel1.getGraphics());

    }



        private void nuevoMapa(int xLargo, int yLargo) {


        mMapa = new int[xLargo][yLargo];

        jln = -1;

        jLabel1.setEnabled(true);
        jLabel1.repaint();






    }
    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed


        //finalizaJuego();






    }//GEN-LAST:event_SalirActionPerformed

    private void tJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tJugadorActionPerformed

        

    }//GEN-LAST:event_tJugadorActionPerformed

    public void actualizaTurnos(int nAvatar){


        turnos[nAvatar]--;
        //tTurnos.setText(String.valueOf(turnos));
        if (turnos[nAvatar] < 0) {
            //JOptionPane.showMessageDialog(rootPane, "Se le agotaron las movidas! JAAAH!");
            //envia T mayus ke es turnos out
            enviaMensajeAtodos("T" + String.valueOf(nAvatar));
            finalizaJuego(nAvatar);
        }


    }
    public void actualizaPuntos(int nAvatar){
        
        
        puntaje[nAvatar]--; //baja un punto por cada segundo ke pase..
        //Aqui hay ke enviarlo a cliente correspondiente
        //tPuntaje.setText(String.valueOf(puntaje));
        if (time[nAvatar] < 0) {
            //JOptionPane.showMessageDialog(rootPane, "Se le agotó el tiempo! ja!");
            //envia t + nAvatar .. t = timeout
            enviaMensajeAtodos("t" + String.valueOf(nAvatar));
            finalizaJuego(nAvatar);
        }

        
    }

    private void tJugadorInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tJugadorInputMethodTextChanged





    }//GEN-LAST:event_tJugadorInputMethodTextChanged

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

    
        if(evt.getKeyCode() == 10){
            //iniChat(jTextField1.getText(), 5);
            enviaMensajeAtodos("<Servidor>" + jTextField1.getText());
            jTextField1.setText("");
        }


    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        //sCon.
        //iniChat(jTextField1.getText(), 5);

        enviaMensajeAtodos("<Servidor>" + jTextField1.getText());
        jTextField1.setText("");



    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void dibujaGrid() {

//en este frame Juego solo para debug se implemento este metodo

        int x = xLargo;
        int y = yLargo;

        //repinta la arena para repintar las coordenadas (si nuevas)
        //jLabel1.update(jLabel1.getGraphics());

        //crea la red de diseño
        for(int i = 1; i < x+1; i++){
            jLabel1.getGraphics().drawLine(i*45, 0, i*45, 60*y);
        }

        for(int i = 1; i < y+1; i++){
            jLabel1.getGraphics().drawLine(0, i*60, 45*x, i*60);
        }

    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego_Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField tJugador;
    private javax.swing.JTextField tPuntaje;
    private javax.swing.JTextField tTurnos;
    private javax.swing.JTextField tVidas;
    // End of variables declaration//GEN-END:variables

    public void refrescaTimer(int nAvatar){

        //nAvatar--;
        //refresca tiempo
        //tJugador.setText(String.valueOf(time));

        //aqui enviar tiempo al cliente

        //Refresca chat, ver si quitar..
        if(time[nAvatar] == setClearChat[nAvatar]){
            imgChat[nAvatar-1].setVisible(false);
            txtChat[nAvatar-1].setVisible(false);
        }

        //Esto se elimina de la version RED por RAZONES OBVIAS PORFAOVR!!!
        //ver tiempo idle..
//            idle++;
//            if(idle == 10){
//                iniChat("Emm.. Esperando órdenes..", 3);
//            }
//            if(idle == 15){
//                iniChat("Emm.. Aló!! hay alguien ahi????..", 5);
//            }
//            if(idle == 25){
//                iniChat("En serio.. Si no hace nada, voy a tener que terminar el programa..", 5);
//            }
//            if(idle == 35){
//                iniChat("No es broma!!", 5);
//            }
//            if(idle == 42){
//                iniChat("SE LO ADVERTI!! CHAO!", 2);
//            }
//            if(idle == 45){
//                System.exit(0);
//            }





    }
    public void refrescaElementos() {

        for (int i = 0; i <= jln; i++){
            if(jlNuevo[i].isVisible()) jlNuevo[i].update(jlNuevo[i].getGraphics() );
            //if(jlNuevo[i].getIcon().equals(iconPiedra)) jlNuevo[i].update(jlNuevo[i].getGraphics());
        }


        for (int i = 1; i <= numAvatars; i++){
            avatars[i].update(avatars[i].getGraphics());
        }
        
        //dibujaGrid(); //quitar luego

    }

    private String[][] ordenarPorRecord(String[][] records) {


        int maxVal = 0;

        for (int i=0; i < 10; i++){
            int mvm = Integer.parseInt(records[i][1]);
            if (mvm > maxVal) maxVal = mvm;
        }

        String topOrdenado[][] =  { {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"} ,{"","0"}, {"","0"} };

        int row = 0;

        for (int i=maxVal; i > 0; i--){
            for (int i2=0; i2 < 10; i2++){
                int mvm = Integer.parseInt(records[i2][1]);
                if (mvm == i) {
                    topOrdenado[row][0] = records[i2][0];
                    topOrdenado[row][1] = records[i2][1];
                    row++;
                }
            }
        }

        return topOrdenado;


    }

    private void seSalvo(int nAvatar) {

        Icon orig = avatars[nAvatar].getIcon();

        for(int v = 1; v < 4; v++){
            avatars[nAvatar].setIcon(iconAvatarAtras);
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            espere(100);
            avatars[nAvatar].setIcon(iconAvatarDerecha);
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            espere(100);
            avatars[nAvatar].setIcon(iconAvatarFrente);
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            espere(100);
            avatars[nAvatar].setIcon(iconAvatarIzquierda);
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            espere(100);
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            avatars[nAvatar].update(avatars[nAvatar].getGraphics());
            espere(100);
            jLabel1.update(jLabel1.getGraphics());
            refrescaElementos();
        }

        avatars[nAvatar].setIcon(orig);
        avatars[nAvatar].update(avatars[nAvatar].getGraphics());



    }


    public int iniAvatarRED() {

        //busca la primera posición desocupada en el mapa sin obstaculos
//        for(int i = 0; i < mMapa.length; i++){
//            for(int i2 = 0; i2 < mMapa[0].length; i2++){
//                if(chequeaObjetoPos(i, i2) != 3){
//                    numAvatars++;
//                    xINIavatar = i;
//                    yINIavatar = i2;
//                    break;
//                }
//            }
//        }

        numAvatars++;

        //iniciar avatar
        iniciarAvatar();
        //avatarIniciado = true;

        posAvatarX[numAvatars] = xINIavatar;
        posAvatarY[numAvatars] = yINIavatar;

        avatars[numAvatars].setLocation(xINIavatar, yINIavatar);
        
        vidas[numAvatars] = vidasMapa;
        time[numAvatars] = timeMapa;
        turnos[numAvatars] = turnosMapa;

        //avatars[nAvatar].setDoubleBuffered(true);

        jTextField1.setEnabled(true);
        jButton1.setEnabled(true);

        return numAvatars;


    }

}
