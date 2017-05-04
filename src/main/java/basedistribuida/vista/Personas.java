
package basedistribuida.vista;

import basedistribuida.beans.InformacionPersona;
import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Persona;
import basedistribuida.model.Colonia;
import basedistribuida.model.Estado;
import basedistribuida.model.Municipio;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


 

public class Personas extends javax.swing.JFrame {
 
 
File file = null;
Image img = null;
private List<InformacionPersona> listaPersonas;
private Coordinador coordinador;

    public Personas() {
        initComponents();
        cargarPersonas();
        try {
            file = new File(System.getProperty("user.dir") + "/archivos/312408.png");
            img = ImageIO.read(file).getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
            //botonVer.setIcon(new ImageIcon(img));
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
            //botonbuscar.setIcon(new ImageIcon(img));
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
        btnagregar = new javax.swing.JButton();
        botoneditar = new javax.swing.JButton();
        botoneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Filtar por Estado");
        jLabel1.setMaximumSize(new java.awt.Dimension(150, 35));
        jLabel1.setMinimumSize(new java.awt.Dimension(150, 35));
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setAlignmentX(0.0F);
        jComboBox1.setMaximumSize(new java.awt.Dimension(150, 35));
        jComboBox1.setMinimumSize(new java.awt.Dimension(150, 35));
        jComboBox1.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jComboBox1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Filtar por Municipio");
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 35));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 35));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jLabel2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setAlignmentX(0.0F);
        jComboBox2.setMaximumSize(new java.awt.Dimension(150, 35));
        jComboBox2.setMinimumSize(new java.awt.Dimension(150, 35));
        jComboBox2.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jComboBox2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Filtar por Colonia");
        jLabel3.setMaximumSize(new java.awt.Dimension(150, 35));
        jLabel3.setMinimumSize(new java.awt.Dimension(150, 35));
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jLabel3);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setAlignmentX(0.0F);
        jComboBox3.setMaximumSize(new java.awt.Dimension(150, 35));
        jComboBox3.setMinimumSize(new java.awt.Dimension(150, 35));
        jComboBox3.setPreferredSize(new java.awt.Dimension(150, 35));
        panelizquierdo.add(jComboBox3);

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
public void cargarPersonas() {
        coordinador = new Coordinador();
        listaPersonas = coordinador.obtenerPersonas();
        //Actualizamos modelo jTable
        actualizarJTableModel();
    }

    private void actualizarJTableModel() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        for (int i = 0; i < listaPersonas.size(); i++) {
            String[] data = new String[6];
            data[0] = listaPersonas.get(i).getPersona().getId()+ "";
            data[1] = listaPersonas.get(i).getPersona().getNombre();
            data[2] = listaPersonas.get(i).getPersona().getApellidoPaterno();
            data[3] = listaPersonas.get(i).getPersona().getApellidoMaterno();
            data[4] = listaPersonas.get(i).getPersona().getFechaNacimiento()+"";
            data[5] = listaPersonas.get(i).getPersona().getGenero().name();             
            tableModel.addRow(data);
        }
        jTable1.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }
    private void botoneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneditarActionPerformed
 int row = jTable1.getSelectedRow();
        InformacionPersona persona = listaPersonas.get(row);
        System.out.println("row " + row + " estado " + listaPersonas.get(row));
        if (persona != null) {
            Editarpersona editarpersona = new Editarpersona(persona, this);
            editarpersona.setVisible(true);
        }
    }//GEN-LAST:event_botoneditarActionPerformed

    private void botoneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneliminarActionPerformed
     int row = jTable1.getSelectedRow();
        InformacionPersona persona = listaPersonas.get(row);
         if (persona != null) {
            coordinador = new Coordinador();
            Estado estado = coordinador.obtenerEstadoById(persona.getDireccion().getIdEstado());
            coordinador.borrarInformacionPersona(persona.getPersona(),persona.getDireccion(),estado);
            //Obtener estados una vez mas
            listaPersonas = coordinador.obtenerPersonas();
            //Actualizar modelo jTable
            actualizarJTableModel(); }           
    }//GEN-LAST:event_botoneliminarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
Agregarpersona agregarpersona = new Agregarpersona(this);
agregarpersona.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnagregarActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoneditar;
    private javax.swing.JButton botoneliminar;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel panelizquierdo;
    private javax.swing.JPanel paneltabla;
    // End of variables declaration//GEN-END:variables
 
}
