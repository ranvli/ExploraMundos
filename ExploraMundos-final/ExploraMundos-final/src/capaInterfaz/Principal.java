/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 16/03/2009, 04:11:44 PM
 */
package capaInterfaz;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Form Explora Mundos 1.0
 * Universidad Latina de Costa Rica
 * @author William Medina Romero
 * Carne no. 2008020576
 */
public class Principal extends javax.swing.JFrame {

MotorLogico.AdministradorExploraMundos_Puente administradorMundos;
capaDatos.AdmDatos capaDatos = new capaDatos.AdmDatos();
AgruegeJugador ventanaAgruegeJugador;

File mapaCagado;

final JFileChooser fc = new JFileChooser();



    public Principal() {
        initComponents();
        administradorMundos = new MotorLogico.AdministradorExploraMundos_Puente();
        ventanaAgruegeJugador = new AgruegeJugador(this, true, this);
        TituloRecords1.setVisible(false);
        mJugar.setEnabled(false);
}

    private void cargaRecords(String topten[][]) {


    TituloRecords1.setVisible(true);
    

    top.setText("Nickname jugador :      " + topten[0][0] + "   -   Puntaje obtenido : " + topten[0][1]);
    top1.setText("Nickname jugador :      " + topten[1][0] + "   -   Puntaje obtenido : " + topten[1][1]);
    top2.setText("Nickname jugador :      " + topten[2][0] + "   -   Puntaje obtenido : " + topten[2][1]);
    top3.setText("Nickname jugador :      " + topten[3][0] + "   -   Puntaje obtenido : " + topten[3][1]);
    top4.setText("Nickname jugador :      " + topten[4][0] + "   -   Puntaje obtenido : " + topten[4][1]);
    top5.setText("Nickname jugador :      " + topten[5][0] + "   -   Puntaje obtenido : " + topten[5][1]);
    top6.setText("Nickname jugador :      " + topten[6][0] + "   -   Puntaje obtenido : " + topten[6][1]);
    top7.setText("Nickname jugador :      " + topten[7][0] + "   -   Puntaje obtenido : " + topten[7][1]);
    top8.setText("Nickname jugador :      " + topten[8][0] + "   -   Puntaje obtenido : " + topten[8][1]);
    top9.setText("Nickname jugador :      " + topten[9][0] + "   -   Puntaje obtenido : " + topten[9][1]);




    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TituloRecords1 = new javax.swing.JLabel();
        top = new javax.swing.JLabel();
        top1 = new javax.swing.JLabel();
        top2 = new javax.swing.JLabel();
        top3 = new javax.swing.JLabel();
        top4 = new javax.swing.JLabel();
        top5 = new javax.swing.JLabel();
        top6 = new javax.swing.JLabel();
        top7 = new javax.swing.JLabel();
        top8 = new javax.swing.JLabel();
        top9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mVerManual = new javax.swing.JMenu();
        mJugar = new javax.swing.JMenuItem();
        mCrearMapa = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mCargarDatos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        mSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mAcercaDelPrograma = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menú Principal");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TituloRecords1.setFont(new java.awt.Font("Tahoma", 2, 18));
        TituloRecords1.setForeground(new java.awt.Color(255, 255, 102));
        TituloRecords1.setText("Mejores puntajes de este mundo...");
        getContentPane().add(TituloRecords1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        top.setFont(new java.awt.Font("Tahoma", 2, 18));
        top.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        top1.setFont(new java.awt.Font("Tahoma", 2, 18));
        top1.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        top2.setFont(new java.awt.Font("Tahoma", 2, 18));
        top2.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        top3.setFont(new java.awt.Font("Tahoma", 2, 18));
        top3.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 20));

        top4.setFont(new java.awt.Font("Tahoma", 2, 18));
        top4.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        top5.setFont(new java.awt.Font("Tahoma", 2, 18));
        top5.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        top6.setFont(new java.awt.Font("Tahoma", 2, 18));
        top6.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        top7.setFont(new java.awt.Font("Tahoma", 2, 18));
        top7.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        top8.setFont(new java.awt.Font("Tahoma", 2, 18));
        top8.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        top9.setFont(new java.awt.Font("Tahoma", 2, 18));
        top9.setForeground(new java.awt.Color(255, 255, 102));
        getContentPane().add(top9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/explora mundos 1.0.JPG"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mVerManual.setText("Archivo");
        mVerManual.setFont(new java.awt.Font("Arial", 0, 11));

        mJugar.setFont(new java.awt.Font("Arial", 0, 11));
        mJugar.setText("Jugar");
        mJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mJugarActionPerformed(evt);
            }
        });
        mVerManual.add(mJugar);

        mCrearMapa.setFont(new java.awt.Font("Arial", 0, 11));
        mCrearMapa.setText("Crear/Editar Mapa");
        mCrearMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCrearMapaActionPerformed(evt);
            }
        });
        mVerManual.add(mCrearMapa);

        jMenu3.setText("Administrador de Datos");

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 11));
        jMenuItem1.setText("Chequear compatibilidad de mapa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        mCargarDatos.setFont(new java.awt.Font("Arial", 0, 11));
        mCargarDatos.setText("Cargar mapa");
        mCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCargarDatosActionPerformed(evt);
            }
        });
        jMenu3.add(mCargarDatos);

        mVerManual.add(jMenu3);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jMenuItem2.setText("Ver manual de Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mVerManual.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jMenuItem3.setText("Ayuda interactiva");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mVerManual.add(jMenuItem3);
        mVerManual.add(jSeparator1);

        mSalir.setFont(new java.awt.Font("Arial", 0, 11));
        mSalir.setText("Salir");
        mSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSalirActionPerformed(evt);
            }
        });
        mVerManual.add(mSalir);

        jMenuBar1.add(mVerManual);

        jMenu2.setText("Acerca de");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 11));

        mAcercaDelPrograma.setFont(new java.awt.Font("Arial", 0, 11));
        mAcercaDelPrograma.setText("Acerca del Programa");
        mAcercaDelPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAcercaDelProgramaActionPerformed(evt);
            }
        });
        jMenu2.add(mAcercaDelPrograma);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSalirActionPerformed

    System.exit(0);//Termina de ejecutar el sistema....

    }//GEN-LAST:event_mSalirActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        
        String Aplicacion1 = "C:/Archivos de programa/Microsoft Office/Office12/WINWORD.exe ExploraMundos.doc";
        try {
            Process proceso = Runtime.getRuntime().exec(Aplicacion1);
        } catch (Throwable ex) {
            String Aplicacion2 = "C:/Program Files/Microsoft Office/Office12/WINWORD.exe ExploraMundos.doc";
            String Aplicacion3 = "C:/Program Files/Microsoft Office/Office11/WINWORD.exe ExploraMundos.doc";
            try {
                try{
                Process proceso = Runtime.getRuntime().exec(Aplicacion2);}
                catch(Throwable t)
                {
                Process proceso = Runtime.getRuntime().exec(Aplicacion3);
                }
            }catch (Throwable ext) {
//                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ext);
                JOptionPane.showMessageDialog(null, "Lo sentimos a ocurrido un error fuera de nuestro alcance\n" +
                        "Se pudo haber originado debido a alguna de las siguientes situaciones:\n\n" + "1. El programa de Microsoft Oficce 2007 no esta instalado correctamente\n" +
                        "2. Este programa esta alterado o corrupto y no ha podido ubicar el archivo del manual\n" +
                        "3. Esta version del programa no esta diseñado para un ambiente distinto de Microsoft Windows XP", "Error del Sistema", JOptionPane.ERROR_MESSAGE);

            }



        }
//
//try {
//Runtime run = Runtime.getRuntime();
//String path = new String("C:/Explora Mundos.doc");
//Process pro = run.exec("Notepad2.exe");//Abre o Corre el Porgrama a utilizar
//
//}
//
//catch (Exception err) {
//
//System.out.print("Error ..." +err);
//}




    }//GEN-LAST:event_jMenuItem2ActionPerformed

private void mAcercaDelProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAcercaDelProgramaActionPerformed


    AcercaDelPrograma nuevo = new AcercaDelPrograma(this, true, this);
    nuevo.setVisible(true);


}//GEN-LAST:event_mAcercaDelProgramaActionPerformed

private void mCrearMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCrearMapaActionPerformed

    Editor e = new Editor();
    e.setVisible(true);
    
    

}//GEN-LAST:event_mCrearMapaActionPerformed

private void mJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mJugarActionPerformed


    Control j = new Control(mapaCagado);
    j.setVisible(true);

}//GEN-LAST:event_mJugarActionPerformed

private void mCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCargarDatosActionPerformed


    int returnVal = fc.showOpenDialog(this);

    String topten[][] = null;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            boolean compat = capaDatos.chequeaCompatibilidad(file.toString());
            if(!compat){
                JOptionPane.showMessageDialog(rootPane, "Archivo no es compatible con ExploraMundos v1.0");
                return;
            }
            TituloRecords1.setText("Los mejores puntajes del mundo " + file.getName());
            topten = capaDatos.cargaTops(file.getPath());
            if (topten[0][0].equals("fnf")){
                JOptionPane.showMessageDialog(rootPane, "Archivo de toptens no se encuentra, creando uno en blanco.");
                String topNF[][] = { {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"}, {"","0"} ,{"","0"}, {"","0"} };
                topten = topNF;
                capaDatos.salvaTops(file.toString(), topNF);
            }
            if (topten == null){
                mapaCagado = null;
                JOptionPane.showMessageDialog(rootPane, "Error abriendo archivo de mapa.");
                return;
            }
            mapaCagado = file;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Apertura cancelada.");
            mapaCagado = null;
            mJugar.setEnabled(false);
            return;
            //cancelado
        }


    cargaRecords(topten);


    mJugar.setEnabled(true);

}//GEN-LAST:event_mCargarDatosActionPerformed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated


    
    refrescaRecords();


}//GEN-LAST:event_formWindowActivated

public void refrescaRecords(){
    
    if (mapaCagado != null){
        String topten[][] = capaDatos.cargaTops(mapaCagado.toString());
        cargaRecords(topten);
    }

    
}

private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus


    refrescaRecords();

}//GEN-LAST:event_formWindowGainedFocus

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed



        int returnVal = fc.showOpenDialog(this);

        boolean esCompatible;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            esCompatible = capaDatos.chequeaCompatibilidad(file.getPath());
            if (esCompatible == true){
                JOptionPane.showMessageDialog(rootPane, "El archivo es compatible con Explora Mundos v1.0");
                return;
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "El archivo NO es compatible con Explora Mundos v1.0");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Apertura cancelada.");
            mapaCagado = null;
            mJugar.setEnabled(false);
            return;
            //cancelado
        }



}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed


    String userDir = System.getProperty("user.dir");

    String manualInt = "C:/Program Files/Internet Explorer/iexplore.exe " + userDir + "/manual-EM.swf";
        try {
            Process proceso = Runtime.getRuntime().exec(manualInt);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error abriendo shockwave file.." + ex.getMessage());
        }

    
   


}//GEN-LAST:event_jMenuItem3ActionPerformed

private void espere(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
        }


    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new capaDatos.tocaWav("signosvitales-tourniquet.wav",1).start();
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TituloRecords1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem mAcercaDelPrograma;
    private javax.swing.JMenuItem mCargarDatos;
    private javax.swing.JMenuItem mCrearMapa;
    private javax.swing.JMenuItem mJugar;
    private javax.swing.JMenuItem mSalir;
    private javax.swing.JMenu mVerManual;
    private javax.swing.JLabel top;
    private javax.swing.JLabel top1;
    private javax.swing.JLabel top2;
    private javax.swing.JLabel top3;
    private javax.swing.JLabel top4;
    private javax.swing.JLabel top5;
    private javax.swing.JLabel top6;
    private javax.swing.JLabel top7;
    private javax.swing.JLabel top8;
    private javax.swing.JLabel top9;
    // End of variables declaration//GEN-END:variables
}
