/*
 * Control.java
 *
 * Created on March 20, 2009, 9:37 PM
 */

package capaInterfaz;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author  Jorge Gonzalez
 */


public class Control extends javax.swing.JFrame {

    /** Creates new form Control
     Este metodo recibe ordenes del usuario
     * Es una interfaz desde el mundo real al juego..
     * Va cargando las ordenes del usuario en una arraylist con chars que luego son interpretados por el kernel
     */
    
    ImageIcon iconControl = new ImageIcon(getClass().getResource("/Imagenes/control.png"));
    //public ArrayList ordenes = new ArrayList();
    char orden;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    
//    Juego juego;
//    MotorLogico.kernelBombeta kernel;

    boolean iniPrimera = false;
    boolean juegoIniciado = false;

    public Control() {
        
        
    }

    Control(File mapaCagado) {
        initComponents();

//        juego = new Juego(this, mapaCagado);
//        juego.setVisible(true);
//        kernel = new MotorLogico.kernelBombeta(this, juego);
    }
    
    public void leerLista()
    {

//        String cadenaOrdenes = "";
//
//        for (int i = 0; i < ordenes.size(); i++)
//        {
//
//            cadenaOrdenes+= ordenes.get(i).toString().charAt(0);
//
//        }
//
//        jOrdenesEnFila.setText(cadenaOrdenes);
//
//        jOrdenesEnFila.repaint();
//
//        if(kernel.getState() == Thread.State.WAITING) kernel.despierte();

        

    
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lUp = new javax.swing.JLabel();
        lLeft = new javax.swing.JLabel();
        lRigth = new javax.swing.JLabel();
        lDown = new javax.swing.JLabel();
        lStart = new javax.swing.JLabel();
        lB = new javax.swing.JLabel();
        lA = new javax.swing.JLabel();
        lControl = new javax.swing.JLabel();
        jOrdenesEnFila = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control");
        setForeground(java.awt.Color.lightGray);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/indicontrol.png"))); // NOI18N
        lUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lUpMouseEntered(evt);
            }
        });
        lUp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lUpMouseMoved(evt);
            }
        });

        lLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lLeftMouseClicked(evt);
            }
        });
        lLeft.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lLeftMouseMoved(evt);
            }
        });

        lRigth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lRigthMouseClicked(evt);
            }
        });
        lRigth.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lRigthMouseMoved(evt);
            }
        });

        lDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lDownMouseClicked(evt);
            }
        });
        lDown.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lDownMouseMoved(evt);
            }
        });

        lStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lStartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lStartMouseEntered(evt);
            }
        });
        lStart.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lStartMouseMoved(evt);
            }
        });

        lB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lBMouseClicked(evt);
            }
        });
        lB.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lBMouseMoved(evt);
            }
        });

        lA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAMouseClicked(evt);
            }
        });
        lA.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lAMouseMoved(evt);
            }
        });

        lControl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/control.jpg"))); // NOI18N
        lControl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lControlMouseEntered(evt);
            }
        });
        lControl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lControlMouseMoved(evt);
            }
        });

        jOrdenesEnFila.setFont(new java.awt.Font("Arial", 0, 12));
        jOrdenesEnFila.setText("...");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel1.setText("Ordenes pendientes de procesar ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(lB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(lStart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(lA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(lRigth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lControl)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jOrdenesEnFila, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lA, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lRigth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lControl, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jOrdenesEnFila))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void lUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lUpMouseClicked
// TODO add your handling code here:
    //JOptionPane.showMessageDialog(null, "Click up");
    orden='u';//u para la orden de arriba

    ordenes(orden);


    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();

    
    leerLista();
//GEN-LAST:event_lUpMouseClicked
}

private void lLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lLeftMouseClicked
// TODO add your handling code here:
    //JOptionPane.showMessageDialog(null, "Click left");
    orden='l';//l para la orden de izquierda
    ordenes(orden);

    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();

    leerLista();
}//GEN-LAST:event_lLeftMouseClicked

private void lRigthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lRigthMouseClicked
// TODO add your handling code here:
    //JOptionPane.showMessageDialog(null, "Click rigth");
    orden='r';//r para la orden de derecha
    ordenes(orden);

    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();


    leerLista();

}//GEN-LAST:event_lRigthMouseClicked

private void lDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lDownMouseClicked
// TODO add your handling code here:
    //JOptionPane.showMessageDialog(null, "Click down");

    orden='d';//d para la orden de abajo
    ordenes(orden);

    
    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();



    leerLista();

}//GEN-LAST:event_lDownMouseClicked

private void lStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lStartMouseClicked

////
////    //JOptionPane.showMessageDialog(null, "Click iniAvatar");
////
//////    orden='s';//s para la orden de comenzar o crear el avatar
//////    ordenes.add(orden);
//////    leerLista();
////
////    //inicia avatar en Juego
    
//    if (!iniPrimera){
//        iniPrimera = true;
//        juegoIniciado = true;
//        juego.iniAvatar();
//        kernel.start();
//        JOptionPane.showMessageDialog(rootPane, "El juego ha sido iniciado por primera vez.");
//        return;
//    }
//
//
//    if (juegoIniciado){
//        //kernel.parar = true;
//        juegoIniciado = false;
//        kernel.parar = true;
//        JOptionPane.showMessageDialog(rootPane, "El juego ha sido pausado.");
//    }
//    else{
//        //inicia kernel de lectura de buffer
//        kernel = new MotorLogico.kernelBombeta(this, juego);
//        kernel.start();
//        juegoIniciado = true;
//        JOptionPane.showMessageDialog(rootPane, "El juego ha sido re-iniciado.");
//    }

    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();


}//GEN-LAST:event_lStartMouseClicked

private void lBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lBMouseClicked
// TODO add your handling code here:
    //JOptionPane.showMessageDialog(null, "Click B");
    orden='b';//b para la orden de brincar
    ordenes(orden);

    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();

    leerLista();

}//GEN-LAST:event_lBMouseClicked

private void lAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAMouseClicked
// TODO add your handling code here:
   //JOptionPane.showMessageDialog(null, "Click A");
    orden='a';//a para la orden de escarbar
    ordenes(orden);
    
    
    //sonido boton
    new capaDatos.tocaWav("boton.wav").start();

    leerLista();

    
}//GEN-LAST:event_lAMouseClicked

private void lControlMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lControlMouseMoved
    // TODO add your handling code here:
}//GEN-LAST:event_lControlMouseMoved

private void lUpMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lUpMouseMoved


    setNadaDemas();
    lUp.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol.png")));





}//GEN-LAST:event_lUpMouseMoved

private void lControlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lControlMouseEntered

       

}//GEN-LAST:event_lControlMouseEntered

private void lUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lUpMouseEntered


        
}//GEN-LAST:event_lUpMouseEntered

private void lStartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lStartMouseEntered
    // TODO add your handling code here:
}//GEN-LAST:event_lStartMouseEntered

private void lLeftMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lLeftMouseMoved


    setNadaDemas();
    lLeft.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol.png")));

}//GEN-LAST:event_lLeftMouseMoved

private void lRigthMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lRigthMouseMoved


    setNadaDemas();
    lRigth.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol.png")));

}//GEN-LAST:event_lRigthMouseMoved

private void lDownMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lDownMouseMoved

    setNadaDemas();
    lDown.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol.png")));


}//GEN-LAST:event_lDownMouseMoved

private void lStartMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lStartMouseMoved


    setNadaDemas();
    lStart.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol.png")));


}//GEN-LAST:event_lStartMouseMoved

private void lBMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lBMouseMoved

    setNadaDemas();
    lB.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol-BG.png")));


}//GEN-LAST:event_lBMouseMoved

private void lAMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAMouseMoved


    setNadaDemas();
    lA.setIcon(new ImageIcon(getClass().getResource("/Imagenes/indicontrol-BG.png")));

}//GEN-LAST:event_lAMouseMoved

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed


   

}//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Control().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jOrdenesEnFila;
    private javax.swing.JLabel lA;
    private javax.swing.JLabel lB;
    private javax.swing.JLabel lControl;
    private javax.swing.JLabel lDown;
    private javax.swing.JLabel lLeft;
    private javax.swing.JLabel lRigth;
    private javax.swing.JLabel lStart;
    private javax.swing.JLabel lUp;
    // End of variables declaration//GEN-END:variables

    private void ordenes(char orden) {
        try {
            oos.writeObject(orden);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en control remoto : "+ ex.getMessage());
        }


    }
    // End of variables declaration

    private void setNadaDemas() {


        lUp.setIcon(null);
        lDown.setIcon(null);
        lRigth.setIcon(null);
        lLeft.setIcon(null);
        lStart.setIcon(null);
        lA.setIcon(null);
        lB.setIcon(null);


    }

}
