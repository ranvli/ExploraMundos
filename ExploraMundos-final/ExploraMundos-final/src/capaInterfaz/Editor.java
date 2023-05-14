/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Editor.java
 * Created on 15/03/2009, 06:02:40 PM
 */

package capaInterfaz;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *clase para editar mapas
 * @author Randall Vargas Li
 */
public class Editor extends javax.swing.JFrame{



    int itemEscogido = 0; //el item ke usuario escogio colocar en el pama
    int mMapa[][]; //la matriz del mapa en si
    int mPremios[][]; //matriz de preimos

    //El largo en x, y del mapa
    int xLargo = 0;
    int yLargo = 0;

    //La posicion inicial del avatar
    int xINIavatar = 0;
    int yINIavatar = 0;

    //Valor del premio por defecto
    int valPremio = 1000;

    //vector de jlabels
    JLabel jlNuevo[] = new JLabel[150];
    JLabel jlBlank[] = new JLabel[150];

    //jln es el label en uso
    int jln = -1;
    boolean nuevoItem = false; //colocar nuevo item?
    boolean borro = false; //se borro? para control interno de imagenes

    //Controles LOCK para no chocar
    boolean creandoLabel = false;
    boolean mouseEntered = false;


    String ultX = "";
    String ultY = "";

    //Fset es el fondo a usar
    int fset = 1;
    boolean avatarColocado = false; //ya coloco el avatar pos inicial?


//diferentes fondos y iconos de bombas y piedras y diamantes
    ImageIcon fondo1 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande1.jpg"));
    ImageIcon fondo2 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande2.jpg"));
    ImageIcon fondo3 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande3.jpg"));
    ImageIcon fondo4 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande4.jpg"));
    ImageIcon fondo5 = new ImageIcon(getClass().getResource("/imagenes/bloquegrande5.jpg"));

    ImageIcon iconBomba = new ImageIcon(getClass().getResource("/Imagenes/bomba.png"));
    ImageIcon iconPlata = new ImageIcon(getClass().getResource("/Imagenes/diamante.png"));
    ImageIcon iconPiedra = new ImageIcon(getClass().getResource("/Imagenes/piedra.png"));
    ImageIcon iconBlanco = new ImageIcon("");

    ImageIcon iconAvatar = new ImageIcon(getClass().getResource("/Imagenes/parado-frente.png"));

    //controlador de selector de archivos
    final JFileChooser fc = new JFileChooser();

    //PIUENTE
    capaDatos.AdmDatos datos = new capaDatos.AdmDatos();

    /** Creates new form Editor */
    public Editor() {

        initComponents();
        
    }

    private void actualizaImgPos() {



        if(creandoLabel) return;
        if(mouseEntered) return;
        

        int x = Integer.parseInt(tCoordX.getText());
        int y = Integer.parseInt(tCoordY.getText());

        if (itemEscogido != 0 && itemEscogido != -1 && nuevoItem == true){
            jln = creaItem(itemEscogido);
            //JOptionPane.showMessageDialog(rootPane, "Item creado");
        }

        //Colocar item nuevo creado en x y
        jlNuevo[jln].setLocation((x)*45, (y)*60);
        


    }

    public void borraElementos() {

        for(int i=0; i < 100; i++){
            if (jlNuevo[i] == null) break;
            jlNuevo[i].setVisible(false);
            super.getContentPane().remove(jlNuevo[i]);
        }

    }

    public void cargaElementos() {


//borra todos los labes y lo vuelve a crear para ahorrar espacio en memoria
        for(int i=0; i <= jln; i++){
            jlNuevo[i].setVisible(false);
            jlNuevo[i].setIcon(null);
            super.getContentPane().remove(jlNuevo[i]);
        }

        
        jlNuevo = new JLabel[150];

        jln=-1;


        for(int i = 0; i < xLargo; i++){
            for(int i2 = 0; i2 < yLargo; i2++){
                int vV = mMapa[i][i2];
                if (vV != 0) creaLabel(vV,i,i2);
            }
        }

        //carga avatar if any
        if(avatarColocado){
            creaLabel(4, xINIavatar, yINIavatar);
        }

        //dibujaGrid();
        //jLabel1.update(jLabel1.getGraphics());

    }

    private int creaItem(int itemEscogido) {



        //crea un nuevo item a colocar
        jln++;

        ImageIcon iconoE = null;
        if (itemEscogido == 1) iconoE = iconBomba;
        if (itemEscogido == 2) iconoE = iconPlata;
        if (itemEscogido == 3) iconoE = iconPiedra;
        if (itemEscogido == 4) iconoE = iconAvatar;

        
        jlNuevo[jln] = new JLabel("");
        jlNuevo[jln].setIcon(iconoE);

        super.getContentPane().add(jlNuevo[jln],0);
        jlNuevo[jln].setBounds(0,0, 45,60);
        jlNuevo[jln].setVisible(true);
        
        nuevoItem = false;
        return jln;


    }

    private void creaLabel(int vV, int i, int i2) {


        creandoLabel = true;

        ImageIcon iconoE = null;
        jln++;


        if (vV == 1) iconoE = iconBomba;
        if (vV == 2) iconoE = iconPlata;
        if (vV == 3) iconoE = iconPiedra;
        if (vV == 4) iconoE = iconAvatar;


        jlNuevo[jln] = new JLabel("");
        jlNuevo[jln].setIcon(iconoE);
        super.getContentPane().add(jlNuevo[jln],0);
        jlNuevo[jln].setBounds(i*45,i2*60, 45,60);
        jlNuevo[jln].setVisible(true);

        jlNuevo[jln].update(jlNuevo[jln].getGraphics());

        creandoLabel = false;



    }



    private void dibujaGrid() {


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

    



    private void espere(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tCoordX = new javax.swing.JTextField();
        tCoordY = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        it1 = new javax.swing.JLabel();
        it2 = new javax.swing.JLabel();
        it3 = new javax.swing.JLabel();
        it4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPremio = new javax.swing.JTextField();
        enmascaramiento = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tvidas = new javax.swing.JTextField();
        tSecs = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hDelay = new javax.swing.JTextField();
        vDelay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tLimJug = new javax.swing.JTextField();
        segMov = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NuevoMapa = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        CargaMapa = new javax.swing.JMenuItem();
        SalvaMapa = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        Salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial", 0, 10));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Posición"));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11));
        jLabel5.setText("Y");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11));
        jLabel4.setText("X");

        tCoordX.setText("000");
        tCoordX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCoordXActionPerformed(evt);
            }
        });

        tCoordY.setText("000");
        tCoordY.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tCoordYCaretUpdate(evt);
            }
        });
        tCoordY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCoordYActionPerformed(evt);
            }
        });
        tCoordY.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tCoordYInputMethodTextChanged(evt);
            }
        });
        tCoordY.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tCoordYPropertyChange(evt);
            }
        });
        tCoordY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCoordYKeyTyped(evt);
            }
        });
        tCoordY.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                tCoordYVetoableChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCoordX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCoordY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tCoordX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCoordY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Objetos"));

        it1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bomba.png"))); // NOI18N
        it1.setToolTipText("Bomba");
        it1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                it1MouseClicked(evt);
            }
        });

        it2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/diamante.png"))); // NOI18N
        it2.setToolTipText("Dinero");
        it2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                it2MouseClicked(evt);
            }
        });

        it3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/piedra.png"))); // NOI18N
        it3.setToolTipText("Obstáculo");
        it3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                it3MouseClicked(evt);
            }
        });

        it4.setFont(new java.awt.Font("Arial", 0, 11));
        it4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        it4.setText("Borrar");
        it4.setToolTipText("Obstáculo");
        it4.setPreferredSize(new java.awt.Dimension(45, 60));
        it4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                it4MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/parado-frente.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(it1)
            .addComponent(it2)
            .addComponent(it3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(it4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(it1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(it2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(it3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(it4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 10));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Editor"));

        jButton2.setFont(new java.awt.Font("Arial", 0, 11));
        jButton2.setText("Crear mapa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel3.setText("Dimensiones");

        jTextField3.setText("14");

        jTextField4.setText("9");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mapa", jPanel3);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel6.setText("Valor premio");

        jPremio.setText("1000");
        jPremio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPremioKeyReleased(evt);
            }
        });

        enmascaramiento.setFont(new java.awt.Font("Arial", 0, 10));
        enmascaramiento.setSelected(true);
        enmascaramiento.setText("Enmascarar premios y bombas con cajitas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPremio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enmascaramiento)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPremio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enmascaramiento))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Premios & Bombas", jPanel2);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande1.jpg"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setIconTextGap(1);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande2.jpg"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande3.jpg"))); // NOI18N
        jLabel13.setText("Fondo 3");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande5.jpg"))); // NOI18N
        jLabel14.setText("Fondo 5");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquegrande4.jpg"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fondo Mundo", jPanel6);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración"));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel7.setText("Vidas iniciales");

        tvidas.setText("5");

        tSecs.setText("300");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel8.setText("Tiempo en segundos límite");

        hDelay.setText("44");

        vDelay.setText("40");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel9.setText("Delay animación avatar vertical y horizontal");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel10.setText("Límite de jugadas");

        tLimJug.setText("30");

        segMov.setText("2");
        segMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segMovActionPerformed(evt);
            }
        });
        segMov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                segMovKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel16.setText("Segundos entre c/movida");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tvidas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tSecs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(segMov)
                    .addComponent(tLimJug, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tvidas, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tSecs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tLimJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(vDelay, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(segMov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Configuración", jPanel5);

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

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 10));

        NuevoMapa.setText("Nuevo Mapa");
        NuevoMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoMapaActionPerformed(evt);
            }
        });
        jMenu1.add(NuevoMapa);
        jMenu1.add(jSeparator2);

        CargaMapa.setFont(new java.awt.Font("Arial", 0, 10));
        CargaMapa.setActionCommand("Cargar Mapa");
        CargaMapa.setLabel("Cargar Mapa");
        CargaMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargaMapaActionPerformed(evt);
            }
        });
        jMenu1.add(CargaMapa);

        SalvaMapa.setFont(new java.awt.Font("Arial", 0, 10));
        SalvaMapa.setLabel("Salvar Mapa");
        SalvaMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvaMapaActionPerformed(evt);
            }
        });
        jMenu1.add(SalvaMapa);
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


   

    private void jLabel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseMoved

        //Si hay movimiento del mouse entonces colocar de acuerdo a las coordenadas los itemes

        if (itemEscogido == 4 && avatarColocado) return;
        if (mouseEntered) return;
        if (creandoLabel) return;

        //informa x y y en text boxes
        tCoordX.setText(String.valueOf(evt.getX()/45));
        tCoordY.setText(String.valueOf(evt.getY()/60));

        if (!ultX.equals(tCoordX.getText()) | !ultY.equals(tCoordY.getText())){
            if (itemEscogido != -1 && itemEscogido != 0) actualizaImgPos();
            if (Integer.parseInt(tCoordX.getText())+1 > xLargo){
                tCoordX.setText("Fuera");
            }
            if (Integer.parseInt(tCoordY.getText())+1 > yLargo){
                tCoordY.setText("Fuera");
            }
        }

        dibujaGrid();

        ultX = tCoordX.getText();
        ultY = tCoordY.getText();


        

    }//GEN-LAST:event_jLabel1MouseMoved

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


        jPremio.setVisible(false);
        jLabel6.setVisible(false);

        

    }//GEN-LAST:event_formWindowOpened

    private void it1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_it1MouseClicked


        //Si hay un clic entonces seleccionar objeto

        jPremio.setVisible(false);
        jLabel6.setVisible(false);


        it1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        it2.setBorder(null);
        it3.setBorder(null);
        it4.setBorder(null);

        jLabel2.setBorder(null);

//        if (borro){
//          if (itemEscogido == 0) nuevoItem = false;
//          if (itemEscogido != 0) nuevoItem = true;
//        }
//
//        if (!borro){
//            borro = false;
//            nuevoItem = true;
//        }
        itemEscogido = 1;

    }//GEN-LAST:event_it1MouseClicked

    private void it2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_it2MouseClicked

        jPremio.setVisible(true);
        jLabel6.setVisible(true);


        it1.setBorder(null);
        it2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        it3.setBorder(null);
        it4.setBorder(null);

        jLabel2.setBorder(null);


//        if (borro){
//          if (itemEscogido == 0) nuevoItem = false;
//          if (itemEscogido != 0) nuevoItem = true;
//        }
//
//        if (!borro){
//            borro = false;
//            nuevoItem = true;
//        }
        itemEscogido = 2;


    }//GEN-LAST:event_it2MouseClicked

    private void it3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_it3MouseClicked

        jPremio.setVisible(false);
        jLabel6.setVisible(false);


        it1.setBorder(null);
        it2.setBorder(null);
        it3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        it4.setBorder(null);

        jLabel2.setBorder(null);

//
//        if (borro){
//          if (itemEscogido == 0) nuevoItem = false;
//          if (itemEscogido != 0) nuevoItem = true;
//        }
//
//        if (!borro){
//            borro = false;
//            nuevoItem = true;
//        }
        itemEscogido = 3;

    }//GEN-LAST:event_it3MouseClicked

    private void it4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_it4MouseClicked


        jPremio.setVisible(false);
        jLabel6.setVisible(false);


        it1.setBorder(null);
        it2.setBorder(null);
        it3.setBorder(null);
        it4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBorder(null);

        //nuevoItem=false;
        itemEscogido = 0;

    }//GEN-LAST:event_it4MouseClicked

    private void tCoordYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCoordYActionPerformed

        


                
       


}//GEN-LAST:event_tCoordYActionPerformed

    private void tCoordYPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tCoordYPropertyChange


        


}//GEN-LAST:event_tCoordYPropertyChange

    private void tCoordYInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tCoordYInputMethodTextChanged

                        


        
}//GEN-LAST:event_tCoordYInputMethodTextChanged

    private void tCoordYVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_tCoordYVetoableChange


        


}//GEN-LAST:event_tCoordYVetoableChange

    private void tCoordYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCoordYKeyTyped

 

        // TODO add your handling code here:
}//GEN-LAST:event_tCoordYKeyTyped

    private void tCoordYCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tCoordYCaretUpdate

        
               
}//GEN-LAST:event_tCoordYCaretUpdate



    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked


//si se hace clic en el jlabel1 ke es la arena
        //entonces colocar el objeto correspondiente seleccionadp

        if (itemEscogido == -1 || itemEscogido == -2 || (tCoordX.getText().equals("Fuera") || tCoordY.getText().equals("Fuera")) ) return;

        int x = Integer.parseInt(tCoordX.getText());
        int y = Integer.parseInt(tCoordY.getText());

        if(mMapa[x][y] != 0 && itemEscogido != 0){
            JOptionPane.showMessageDialog(null, "Ya esta ocupada esta casilla.");
            return;
        }


        if(itemEscogido != 4){
            mMapa[x][y] = itemEscogido;
            if(itemEscogido == 2) mPremios[x][y] = valPremio; //salva valor del premio en matriz de premios si esta escogido
        }

        if (itemEscogido == 4){
            jLabel2.setEnabled(false);
            xINIavatar = x;
            yINIavatar = y;
            avatarColocado = true;
            cargaElementos();
            return;
        }

        //se borro elemento de la matriz, volver a cargar matriz para e desaparezca el elem. borrao
        if(itemEscogido == 0){
            if (x == xINIavatar && y == yINIavatar){
                avatarColocado = false;
                jLabel2.setEnabled(true);
            }
            borro = true;
            cargaElementos();
            dibujaGrid();
            return;
        }

        //Si no era elemento 0 (borrar) entonces colocar label y activar bandera para generar otro item a mano
        jlNuevo[jln].setLocation((x)*45, (y)*60);
        nuevoItem = true;



    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered



nuevoItem = true;


//        if (creandoLabel){
//            return;
//        }
//
//        if(itemEscogido == -2){
//            return;
//        }
//
//        mouseEntered = true;
//
//
//        if (jln != -1 & itemEscogido != 0){
//
//           ImageIcon iconoE = null;
//           if (itemEscogido == 1) iconoE = iconBomba;
//           if (itemEscogido == 2) iconoE = iconPlata;
//           if (itemEscogido == 3) iconoE = iconPiedra;
//           if (itemEscogido == 4) iconoE = iconAvatar;
//
//
//
//            jlNuevo[jln].setVisible(true);
//            jlNuevo[jln].setIcon(iconoE);
//
//            nuevoItem = !nuevoItem;
//
//
//        }
//
//        if (jln == -1) nuevoItem=true;
//
//        mouseEntered=false;
        
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited


        //no dejar el item creado en el aire
        if(itemEscogido == 4 && avatarColocado) return;

        if (itemEscogido != -2 && itemEscogido != -1 && itemEscogido != 0){

            jlNuevo[jln].setVisible(false);
            nuevoItem = !nuevoItem;

        }



    }//GEN-LAST:event_jLabel1MouseExited

    private void tCoordXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCoordXActionPerformed








}//GEN-LAST:event_tCoordXActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked



        jPremio.setVisible(false);
        jLabel6.setVisible(false);


        it1.setBorder(null);
        it2.setBorder(null);
        it3.setBorder(null);
        it4.setBorder(null);

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); //avatar

        if (borro){
          if (itemEscogido == 0) nuevoItem = false;
          if (itemEscogido != 0) nuevoItem = true;
        }

        if (!borro){
            borro = false;
            nuevoItem = true;
        }

        itemEscogido = 4;


    }//GEN-LAST:event_jLabel2MouseClicked

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed


        this.setVisible(false);


    }//GEN-LAST:event_SalirActionPerformed



    private void CargaMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargaMapaActionPerformed


        //cargar mapa en el editor

        int returnVal = fc.showOpenDialog(this);


        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            //verificar compatibilidad del archivo con EM 1.0
            boolean compat = datos.chequeaCompatibilidad(file.toString());
            if (!compat){
                JOptionPane.showMessageDialog(rootPane, "El archivo no es compatible con ExploraMundos v.1.0");
                return;
            }


            int matrizMapa[][] = datos.cargaMapa(file.getPath(),1);
            int matrizPremios[][] = datos.cargaMapa(file.getPath(),2);

            //Si retorna -1 entonces asumir valores por defecto
            //esto en el caso de que el mapa no haya sido creado en EM 1.0
            if (matrizPremios[0][0] == -1){
                JOptionPane.showMessageDialog(rootPane, "No se encuentra matriz de premios, asumiendo todos los premios de 1000");
                int mp[][] = new int[matrizMapa.length][matrizMapa[0].length];
                for(int i = 0; i < matrizMapa.length; i++){
                    for (int i2 = 0; i2 < matrizMapa[0].length; i2++){
                        mp[i][i2] = 1000;
                    }
                }
            }


            //carga settings
            int settings[][] = datos.cargaMapa(file.getPath(),3);
            if (settings[0][0] == -1){ //si retorna error entonces carga valores por defecto por si mapa no es oriundo de EM 1.0
                JOptionPane.showMessageDialog(rootPane, "No se encuentra configuración del mundo, asumiendo valores por defecto.");
                tvidas.setText("5");
                tSecs.setText("300");
                vDelay.setText("40");
                hDelay.setText("44");
                tLimJug.setText("50");
                setFondo(1);
                enmascaramiento.setSelected(true);
                //el avatar no es colocado.
            }
            else{
                tvidas.setText(String.valueOf(settings[0][0]));
                tSecs.setText(String.valueOf(settings[0][1]));
                tLimJug.setText(String.valueOf(settings[0][2]));
                fset = settings[0][3];
                setFondo(fset);
                vDelay.setText(String.valueOf(settings[0][4]));
                hDelay.setText(String.valueOf(settings[0][5]));
                int enmasc = settings[0][6];
                if (enmasc == 1) enmascaramiento.setSelected(true);
                if (enmasc == 0) enmascaramiento.setSelected(false);
                xINIavatar = settings[0][7];
                yINIavatar = settings[1][7];
                avatarColocado = true;
                segMov.setText("") ;
                //actualiza fondo
            }

            itemEscogido = -2;

            //aplica settings al mundo

            jTextField3.setText(String.valueOf(matrizMapa.length));
            jTextField4.setText(String.valueOf(matrizMapa[0].length));
            xLargo = matrizMapa.length;
            yLargo = matrizMapa[0].length;
            nuevoMapa(xLargo, yLargo);

            borraElementos();

            mMapa = matrizMapa;
            mPremios = matrizPremios;

            cargaElementos();
            dibujaGrid();

            it1.setBorder(null);
            it2.setBorder(null);
            it3.setBorder(null);
            it4.setBorder(null);


        } else {
            JOptionPane.showMessageDialog(rootPane, "Error, null retornado.");
            //cancelado
        }



    }//GEN-LAST:event_CargaMapaActionPerformed

    private void SalvaMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvaMapaActionPerformed

        //metodo salva mundo

        if(mMapa == null){
            JOptionPane.showMessageDialog(rootPane, "No hay mapa activo. No se puede salvar.");
            return;
        }

        int settings[][] = new int[2][8];

        
        //erifica errores de entrada
        try {
            int vidas = Integer.parseInt(tvidas.getText());
            int tJuego = Integer.parseInt(tSecs.getText());
            int tLim = Integer.parseInt(tLimJug.getText());
            int vD = Integer.parseInt(vDelay.getText());
            int hD = Integer.parseInt(hDelay.getText());

            //enmascaramiento 1=true
            int enmasc = 0;
            if (enmascaramiento.isSelected()) enmasc = 1;


            settings[0][0] = vidas; //num de vidas
            settings[0][1] = tJuego; //tiempo del juego
            settings[0][2] = tLim; //limite de jugadas
            settings[0][3] = fset; //fondo escogido (1-5)
            settings[0][4] = vD; //delay vert
            settings[0][5] = hD; //delay horiz
            settings[0][6] = enmasc;
            settings[0][7] = xINIavatar;
            settings[1][7] = yINIavatar;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Los valores de la configuración del mundo deben ser enteros. No se puede salvar.");
            return;
        }



        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            datos.salvaMapa(file.getPath(), file.getName(), mMapa, mPremios, settings);
        } else {
            //cancelado
        }



    }//GEN-LAST:event_SalvaMapaActionPerformed

    private void jPremioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPremioKeyReleased

        try {
            valPremio = Integer.parseInt(jPremio.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Debe digitar solo números enteros en el valor del premio.");
            valPremio = 1000;
            jPremio.setText("1000");
        }

    }//GEN-LAST:event_jPremioKeyReleased

    private void NuevoMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoMapaActionPerformed



        crearMapa cm = new crearMapa(this, true);
        cm.setVisible(true);


    }//GEN-LAST:event_NuevoMapaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try{
            xLargo = Integer.parseInt(jTextField3.getText());
            yLargo = Integer.parseInt(jTextField4.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Las dimensiones deben ser números enteros.");
            return;
        }

        if( xLargo < 5 || xLargo >14 || yLargo < 5 || yLargo >9){
            JOptionPane.showMessageDialog(rootPane, "Las dimensiones deben estar entre el rango 5x5 y 14x9.");
            return;
        }

        borraElementos();
        nuevoMapa(xLargo, yLargo);
        cargaElementos();



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        fset = 1;
        jLabel1.setIcon(fondo1);



    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked


        fset = 2;
        jLabel1.setIcon(fondo2);




    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked


        fset = 3;
        jLabel1.setIcon(fondo3);



    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked


        fset = 5;
        jLabel1.setIcon(fondo5);


    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked


        fset = 4;
        jLabel1.setIcon(fondo4);


    }//GEN-LAST:event_jLabel15MouseClicked

    private void segMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segMovActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_segMovActionPerformed

    private void segMovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segMovKeyReleased


        try{
            String vd = String.valueOf( (Integer.parseInt(segMov.getText()) *1000)  /60 );
            String hd = String.valueOf( (Integer.parseInt(segMov.getText()) *1000)  /45 );
            vDelay.setText(vd);
            hDelay.setText(hd);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Debe digitar solo numeros y enteros.");
            return;
        }
        
        

}//GEN-LAST:event_segMovKeyReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CargaMapa;
    private javax.swing.JMenuItem NuevoMapa;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem SalvaMapa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox enmascaramiento;
    private javax.swing.JTextField hDelay;
    private javax.swing.JLabel it1;
    private javax.swing.JLabel it2;
    private javax.swing.JLabel it3;
    private javax.swing.JLabel it4;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jPremio;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField segMov;
    private javax.swing.JTextField tCoordX;
    private javax.swing.JTextField tCoordY;
    private javax.swing.JTextField tLimJug;
    private javax.swing.JTextField tSecs;
    private javax.swing.JTextField tvidas;
    private javax.swing.JTextField vDelay;
    // End of variables declaration//GEN-END:variables

    
    public void nuevoMapa(int xLargo, int yLargo) {


        mMapa = new int[xLargo][yLargo];
        mPremios = new int[xLargo][yLargo];

        jln = -1;
        nuevoItem = true;

        jLabel1.setEnabled(true);
        jLabel1.repaint();

        avatarColocado = false;
        jLabel2.setEnabled(true);
        itemEscogido = 0;

        dibujaGrid();



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



}
