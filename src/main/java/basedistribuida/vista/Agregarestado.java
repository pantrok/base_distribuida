/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.vista;

import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Estado;
import javax.swing.JOptionPane;

/**
 *
 * @author iovanny
 */
public class Agregarestado extends javax.swing.JFrame {

    private Coordinador coordinador;
    private Estados estadosFrame;
    /**
     * Creates new form Agregarpersona
     */
    public Agregarestado(Estados estadosFrame) {
        this.estadosFrame = estadosFrame;
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
        paneldatos = new javax.swing.JPanel();
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

        setTitle("Agregar estado");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        paneldatos.setLayout(new javax.swing.BoxLayout(paneldatos, javax.swing.BoxLayout.PAGE_AXIS));

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
            .addGap(0, 355, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panelfooter.add(jPanel1);

        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        panelfooter.add(btn_guardar);

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        panelfooter.add(btn_cancelar);

        getContentPane().add(panelfooter);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonbuscarActionPerformed
        //Validar que existan los dos datos
        if (tb_estado.getText().length() > 0
                && tb_zona.getText().length() > 0) {
            if (Estado.Zona.Centro.name().equals(tb_zona.getText())
                    || Estado.Zona.Sur.name().equals(tb_zona.getText())
                    || Estado.Zona.Norte.name().equals(tb_zona.getText())) {
                coordinador = new Coordinador();
                Estado estado = new Estado();
                estado.setNombre(tb_estado.getText());
                estado.setZona(Estado.Zona.valueOf(tb_zona.getText()));
                coordinador.insertarEstado(estado);
                estadosFrame.cargarEstados();
                dispose();
                //Mensaje de actualizacion exitosa
                JOptionPane.showMessageDialog(this, "Estado insertado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                //Mensaje de que la zona no es conocida
                JOptionPane.showMessageDialog(this, "Zona desconocida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //Mandar mensaje de que se necesitan datos
            JOptionPane.showMessageDialog(this, "Todos los datos son necesarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonbuscarActionPerformed
        dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel paneldatos;
    private javax.swing.JPanel panelfooter;
    private javax.swing.JPanel pnl_estado;
    private javax.swing.JPanel pnl_zona;
    private javax.swing.JTextField tb_estado;
    private javax.swing.JTextField tb_zona;
    // End of variables declaration//GEN-END:variables
}
