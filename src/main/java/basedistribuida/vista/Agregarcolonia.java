/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.vista;

import basedistribuida.broadcast.BroadcastUtils;
import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Colonia;
import basedistribuida.model.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author iovanny
 */
public class Agregarcolonia extends javax.swing.JFrame {
    private Colonias coloniasFrame;
    private Coordinador coordinador;
    private List<Municipio> listaMunicipios;
    /**
     * Creates new form Agregarpersona
     */
    public Agregarcolonia(Colonias coloniasframe) {
        initComponents();
        init();
    }
        private void init() {
        //Llenar combo con los estados, si no hay estado decir que no se pueden agregar municipios
        coordinador = new Coordinador();
        listaMunicipios = coordinador.obtenerMunicipios();
        if (!listaMunicipios.isEmpty()) {
            ArrayList<String> nombresMunicipios = new ArrayList<>();
            for (Municipio e : listaMunicipios) {
                nombresMunicipios.add(e.getNombre());
            }
            jComboBox1.setModel(new DefaultComboBoxModel(nombresMunicipios.toArray()));
        } else {
            dispose();
            //Mensaje de actualizacion exitosa
            JOptionPane.showMessageDialog(this, "No se pueden agregar municipios ya que no existen estados", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        panelcolonia = new javax.swing.JPanel();
        pnl_id = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tb_id = new javax.swing.JTextField();
        pnl_nombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tb_nombre = new javax.swing.JTextField();
        pnl_municipio = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        panelfooter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setTitle("Agregar colonia");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        panelcolonia.setLayout(new javax.swing.BoxLayout(panelcolonia, javax.swing.BoxLayout.PAGE_AXIS));

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

        panelcolonia.add(pnl_id);

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

        panelcolonia.add(pnl_nombre);

        pnl_municipio.setLayout(new javax.swing.BoxLayout(pnl_municipio, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Municipio");
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel3.setName(""); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_municipio.add(jLabel3);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setAlignmentX(0.0F);
        jComboBox1.setMaximumSize(new java.awt.Dimension(300, 30));
        jComboBox1.setMinimumSize(new java.awt.Dimension(300, 30));
        jComboBox1.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_municipio.add(jComboBox1);

        panelcolonia.add(pnl_municipio);

        panelcontenido.add(panelcolonia);

        getContentPane().add(panelcontenido);

        panelfooter.setMaximumSize(new java.awt.Dimension(32767, 35));
        panelfooter.setMinimumSize(new java.awt.Dimension(480, 35));
        panelfooter.setPreferredSize(new java.awt.Dimension(480, 35));
        panelfooter.setLayout(new javax.swing.BoxLayout(panelfooter, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panelfooter.add(jPanel1);

        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        panelfooter.add(btn_guardar);

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        panelfooter.add(btn_cancelar);

        getContentPane().add(panelfooter);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
                if (tb_nombre.getText().length() > 0) {
            coordinador = new Coordinador();
            Colonia colonia = new Colonia();
            colonia.setNombre(tb_nombre.getText());
            Municipio municipio = listaMunicipios.get(jComboBox1.getSelectedIndex());
            colonia.setIdMunicipio(municipio.getId()); //Obtener estado seleccionado del combo
            coordinador.insertarColonia(colonia);
            BroadcastUtils.mensajeAServidorRemoto("Operacion");
            
            coloniasFrame.cargarColonias();
            dispose();
            //Mensaje de actualizacion exitosa
            JOptionPane.showMessageDialog(this, "Colonia insertada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Mandar mensaje de que se necesitan datos
            JOptionPane.showMessageDialog(this, "Todos los datos son necesarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelcolonia;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel panelfooter;
    private javax.swing.JPanel pnl_id;
    private javax.swing.JPanel pnl_municipio;
    private javax.swing.JPanel pnl_nombre;
    private javax.swing.JTextField tb_id;
    private javax.swing.JTextField tb_nombre;
    // End of variables declaration//GEN-END:variables
}
