
package basedistribuida.vista;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


 

public class Personas extends javax.swing.JFrame {
 
 
File file = null;
Image img = null;

    public Personas() {
        initComponents();
        try {
            file = new File(System.getProperty("user.dir") + "/archivos/312408.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            botonVer.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/agregar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            btnagregar.setIcon(new ImageIcon(img));            
            file = new File(System.getProperty("user.dir") + "/archivos/editar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            botoneditar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/eliminar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            botoneliminar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/buscar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            botonbuscar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/actualizar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            btnactualizar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/salir.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            btnsalir.setIcon(new ImageIcon(img));            
        } catch (Exception ex) {
            System.out.println(ex);
        }
      }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelcontenido = new javax.swing.JPanel();
        panelizquierdo = new javax.swing.JPanel();
        btnactualizar = new javax.swing.JButton();
        botonVer = new javax.swing.JButton();
        botonbuscar = new javax.swing.JButton();
        btnagregar = new javax.swing.JButton();
        botoneditar = new javax.swing.JButton();
        botoneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        paneltabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Personas");
        setBackground(java.awt.Color.white);
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelcontenido.setBackground(new java.awt.Color(255, 255, 255));
        panelcontenido.setMaximumSize(new java.awt.Dimension(1000, 2000));
        panelcontenido.setMinimumSize(new java.awt.Dimension(1000, 600));
        panelcontenido.setPreferredSize(new java.awt.Dimension(1000, 600));
        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        panelizquierdo.setBackground(java.awt.Color.white);
        panelizquierdo.setMaximumSize(new java.awt.Dimension(150, 2000));
        panelizquierdo.setMinimumSize(new java.awt.Dimension(150, 600));
        panelizquierdo.setPreferredSize(new java.awt.Dimension(150, 600));
        panelizquierdo.setLayout(new javax.swing.BoxLayout(panelizquierdo, javax.swing.BoxLayout.PAGE_AXIS));

        btnactualizar.setText("Actualizar");
        btnactualizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnactualizar.setMaximumSize(new java.awt.Dimension(150, 35));
        btnactualizar.setMinimumSize(new java.awt.Dimension(150, 35));
        btnactualizar.setPreferredSize(new java.awt.Dimension(150, 35));
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        panelizquierdo.add(btnactualizar);

        botonVer.setText("Ver");
        botonVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botonVer.setMaximumSize(new java.awt.Dimension(150, 35));
        botonVer.setMinimumSize(new java.awt.Dimension(150, 35));
        botonVer.setPreferredSize(new java.awt.Dimension(150, 35));
        botonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerActionPerformed(evt);
            }
        });
        panelizquierdo.add(botonVer);

        botonbuscar.setText("Buscar");
        botonbuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botonbuscar.setMaximumSize(new java.awt.Dimension(150, 35));
        botonbuscar.setMinimumSize(new java.awt.Dimension(150, 35));
        botonbuscar.setPreferredSize(new java.awt.Dimension(150, 35));
        botonbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonbuscarActionPerformed(evt);
            }
        });
        panelizquierdo.add(botonbuscar);

        btnagregar.setText("Agregar");
        btnagregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnagregar.setMaximumSize(new java.awt.Dimension(150, 35));
        btnagregar.setMinimumSize(new java.awt.Dimension(150, 35));
        btnagregar.setPreferredSize(new java.awt.Dimension(150, 35));
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        panelizquierdo.add(btnagregar);

        botoneditar.setText("Editar");
        botoneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botoneditar.setMaximumSize(new java.awt.Dimension(150, 35));
        botoneditar.setMinimumSize(new java.awt.Dimension(150, 35));
        botoneditar.setPreferredSize(new java.awt.Dimension(150, 35));
        botoneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoneditarActionPerformed(evt);
            }
        });
        panelizquierdo.add(botoneditar);

        botoneliminar.setText("Eliminar");
        botoneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botoneliminar.setMaximumSize(new java.awt.Dimension(150, 35));
        botoneliminar.setMinimumSize(new java.awt.Dimension(150, 35));
        botoneliminar.setPreferredSize(new java.awt.Dimension(150, 35));
        botoneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoneliminarActionPerformed(evt);
            }
        });
        panelizquierdo.add(botoneliminar);

        btnsalir.setText("Salir");
        btnsalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnsalir.setMaximumSize(new java.awt.Dimension(150, 35));
        btnsalir.setMinimumSize(new java.awt.Dimension(150, 35));
        btnsalir.setPreferredSize(new java.awt.Dimension(150, 35));
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        panelizquierdo.add(btnsalir);

        panelcontenido.add(panelizquierdo);

        paneltabla.setBackground(new java.awt.Color(255, 255, 255));
        paneltabla.setMaximumSize(new java.awt.Dimension(850, 900));
        paneltabla.setMinimumSize(new java.awt.Dimension(850, 600));
        paneltabla.setPreferredSize(new java.awt.Dimension(850, 400));
        paneltabla.setLayout(new javax.swing.BoxLayout(paneltabla, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane2.setBackground(java.awt.Color.white);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(850, 600));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(850, 600));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(850, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "A. Paterno", "A. Materno", "Fecha de Nacimiento", "Genero"
            }
        ));
        jTable1.setMaximumSize(new java.awt.Dimension(850, 600));
        jTable1.setMinimumSize(new java.awt.Dimension(850, 600));
        jTable1.setName(""); // NOI18N
        jTable1.setPreferredSize(new java.awt.Dimension(850, 600));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        paneltabla.add(jScrollPane2);

        panelcontenido.add(paneltabla);

        getContentPane().add(panelcontenido);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerActionPerformed
Verpersona verpersona = new Verpersona();
verpersona.setVisible(true);
    }//GEN-LAST:event_botonVerActionPerformed

    private void botoneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneditarActionPerformed
Editarpersona editarpersona = new Editarpersona();
editarpersona.setVisible(true);
    }//GEN-LAST:event_botoneditarActionPerformed

    private void botoneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneliminarActionPerformed
        
    }//GEN-LAST:event_botoneliminarActionPerformed

    private void botonbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonbuscarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
Agregarpersona agregarpersona = new Agregarpersona();
agregarpersona.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnagregarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Personas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
 Personas principal = new Personas();
 principal.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonVer;
    private javax.swing.JButton botonbuscar;
    private javax.swing.JButton botoneditar;
    private javax.swing.JButton botoneliminar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel panelizquierdo;
    private javax.swing.JPanel paneltabla;
    // End of variables declaration//GEN-END:variables
 
}
