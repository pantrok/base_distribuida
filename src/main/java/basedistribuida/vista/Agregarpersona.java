/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.vista;

/**
 *
 * @author iovanny
 */
public class Agregarpersona extends javax.swing.JFrame {

    /**
     * Creates new form Agregarpersona
     */
    public Agregarpersona() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelcontenido = new javax.swing.JPanel();
        panelpersona = new javax.swing.JPanel();
        pnl_id = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tb_id = new javax.swing.JTextField();
        pnl_nombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tb_nombre = new javax.swing.JTextField();
        pnl_apaterno = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tb_apaterno = new javax.swing.JTextField();
        pnl_amaterno = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tb_amaterno = new javax.swing.JTextField();
        pnl_fecnac = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tb_fecnac = new javax.swing.JTextField();
        pnl_genero = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tb_genero = new javax.swing.JTextField();
        paneldatos = new javax.swing.JPanel();
        pnl_calle = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tb_calle = new javax.swing.JTextField();
        pnl_cp = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tb_cp = new javax.swing.JTextField();
        pnl_colonia = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tb_colonia = new javax.swing.JTextField();
        pnl_municipio = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tb_municipio = new javax.swing.JTextField();
        pnl_estado = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tb_estado = new javax.swing.JTextField();
        pnl_zona = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tb_zona = new javax.swing.JTextField();
        panelfooter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setTitle("Agregar persona");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        panelpersona.setLayout(new javax.swing.BoxLayout(panelpersona, javax.swing.BoxLayout.PAGE_AXIS));

        pnl_id.setLayout(new javax.swing.BoxLayout(pnl_id, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("id");
        jLabel1.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel1.setName(""); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_id.add(jLabel1);

        tb_id.setText(" ");
        tb_id.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_id.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_id.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_id.add(tb_id);

        panelpersona.add(pnl_id);

        pnl_nombre.setLayout(new javax.swing.BoxLayout(pnl_nombre, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Nombre");
        jLabel2.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_nombre.add(jLabel2);

        tb_nombre.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_nombre.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_nombre.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_nombre.add(tb_nombre);

        panelpersona.add(pnl_nombre);

        pnl_apaterno.setLayout(new javax.swing.BoxLayout(pnl_apaterno, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Apellido paterno");
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel3.setName(""); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_apaterno.add(jLabel3);

        tb_apaterno.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_apaterno.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_apaterno.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_apaterno.add(tb_apaterno);

        panelpersona.add(pnl_apaterno);

        pnl_amaterno.setLayout(new javax.swing.BoxLayout(pnl_amaterno, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText("Apellido materno");
        jLabel4.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel4.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel4.setName(""); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_amaterno.add(jLabel4);

        tb_amaterno.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_amaterno.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_amaterno.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_amaterno.add(tb_amaterno);

        panelpersona.add(pnl_amaterno);

        pnl_fecnac.setLayout(new javax.swing.BoxLayout(pnl_fecnac, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setText("Fecha de nacimiento");
        jLabel5.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel5.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel5.setName(""); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_fecnac.add(jLabel5);

        tb_fecnac.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_fecnac.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_fecnac.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_fecnac.add(tb_fecnac);

        panelpersona.add(pnl_fecnac);

        pnl_genero.setLayout(new javax.swing.BoxLayout(pnl_genero, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setText("Genero");
        jLabel6.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel6.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel6.setName(""); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_genero.add(jLabel6);

        tb_genero.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_genero.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_genero.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_genero.add(tb_genero);

        panelpersona.add(pnl_genero);

        panelcontenido.add(panelpersona);

        paneldatos.setLayout(new javax.swing.BoxLayout(paneldatos, javax.swing.BoxLayout.PAGE_AXIS));

        pnl_calle.setLayout(new javax.swing.BoxLayout(pnl_calle, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setText("Calle");
        jLabel7.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel7.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel7.setName(""); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_calle.add(jLabel7);

        tb_calle.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_calle.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_calle.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_calle.add(tb_calle);

        paneldatos.add(pnl_calle);

        pnl_cp.setLayout(new javax.swing.BoxLayout(pnl_cp, javax.swing.BoxLayout.LINE_AXIS));

        jLabel8.setText("C.P.");
        jLabel8.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel8.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel8.setName(""); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_cp.add(jLabel8);

        tb_cp.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_cp.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_cp.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_cp.add(tb_cp);

        paneldatos.add(pnl_cp);

        pnl_colonia.setLayout(new javax.swing.BoxLayout(pnl_colonia, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setText("Colonia");
        jLabel9.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel9.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel9.setName(""); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_colonia.add(jLabel9);

        tb_colonia.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_colonia.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_colonia.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_colonia.add(tb_colonia);

        paneldatos.add(pnl_colonia);

        pnl_municipio.setLayout(new javax.swing.BoxLayout(pnl_municipio, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setText("Municipio");
        jLabel10.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel10.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel10.setName(""); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_municipio.add(jLabel10);

        tb_municipio.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_municipio.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_municipio.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_municipio.add(tb_municipio);

        paneldatos.add(pnl_municipio);

        pnl_estado.setLayout(new javax.swing.BoxLayout(pnl_estado, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setText("Estado");
        jLabel11.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel11.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel11.setName(""); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_estado.add(jLabel11);

        tb_estado.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_estado.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_estado.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_estado.add(tb_estado);

        paneldatos.add(pnl_estado);

        pnl_zona.setLayout(new javax.swing.BoxLayout(pnl_zona, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setText("Zona");
        jLabel12.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel12.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel12.setName(""); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_zona.add(jLabel12);

        tb_zona.setMaximumSize(new java.awt.Dimension(300, 30));
        tb_zona.setMinimumSize(new java.awt.Dimension(300, 30));
        tb_zona.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_zona.add(tb_zona);

        paneldatos.add(pnl_zona);

        panelcontenido.add(paneldatos);

        getContentPane().add(panelcontenido);

        panelfooter.setMaximumSize(new java.awt.Dimension(32767, 35));
        panelfooter.setMinimumSize(new java.awt.Dimension(480, 35));
        panelfooter.setPreferredSize(new java.awt.Dimension(480, 35));
        panelfooter.setLayout(new javax.swing.BoxLayout(panelfooter, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelfooter.add(jPanel1);

        btn_guardar.setText("Guardar");
        panelfooter.add(btn_guardar);

        btn_cancelar.setText("Cancelar");
        panelfooter.add(btn_cancelar);

        getContentPane().add(panelfooter);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Agregarpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregarpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregarpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregarpersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregarpersona().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel paneldatos;
    private javax.swing.JPanel panelfooter;
    private javax.swing.JPanel panelpersona;
    private javax.swing.JPanel pnl_amaterno;
    private javax.swing.JPanel pnl_apaterno;
    private javax.swing.JPanel pnl_calle;
    private javax.swing.JPanel pnl_colonia;
    private javax.swing.JPanel pnl_cp;
    private javax.swing.JPanel pnl_estado;
    private javax.swing.JPanel pnl_fecnac;
    private javax.swing.JPanel pnl_genero;
    private javax.swing.JPanel pnl_id;
    private javax.swing.JPanel pnl_municipio;
    private javax.swing.JPanel pnl_nombre;
    private javax.swing.JPanel pnl_zona;
    private javax.swing.JTextField tb_amaterno;
    private javax.swing.JTextField tb_apaterno;
    private javax.swing.JTextField tb_calle;
    private javax.swing.JTextField tb_colonia;
    private javax.swing.JTextField tb_cp;
    private javax.swing.JTextField tb_estado;
    private javax.swing.JTextField tb_fecnac;
    private javax.swing.JTextField tb_genero;
    private javax.swing.JTextField tb_id;
    private javax.swing.JTextField tb_municipio;
    private javax.swing.JTextField tb_nombre;
    private javax.swing.JTextField tb_zona;
    // End of variables declaration//GEN-END:variables
}
