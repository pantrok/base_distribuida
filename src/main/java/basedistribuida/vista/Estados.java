package basedistribuida.vista;

import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Estado;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class Estados extends javax.swing.JFrame {

    private File file = null;
    private Image img = null;
    private Coordinador coordinador;
    private List<Estado> listaEstados;

    public Estados() {
        initComponents();
        cargarEstados();
        try {
            file = new File(System.getProperty("user.dir") + "/archivos/agregar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            btnagregar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/editar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            botoneditar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/eliminar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            botoneliminar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/buscar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            //botonbuscar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/actualizar.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
            //btnactualizar.setIcon(new ImageIcon(img));
            file = new File(System.getProperty("user.dir") + "/archivos/salir.png");
            img = ImageIO.read(file).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
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
        btnagregar = new javax.swing.JButton();
        botoneditar = new javax.swing.JButton();
        botoneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        paneltabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Estados");
        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(700, 600));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelcontenido.setBackground(new java.awt.Color(255, 255, 255));
        panelcontenido.setMaximumSize(new java.awt.Dimension(700, 2000));
        panelcontenido.setMinimumSize(new java.awt.Dimension(700, 600));
        panelcontenido.setPreferredSize(new java.awt.Dimension(700, 600));
        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        panelizquierdo.setBackground(java.awt.Color.white);
        panelizquierdo.setMaximumSize(new java.awt.Dimension(150, 2000));
        panelizquierdo.setMinimumSize(new java.awt.Dimension(150, 600));
        panelizquierdo.setPreferredSize(new java.awt.Dimension(150, 600));
        panelizquierdo.setLayout(new javax.swing.BoxLayout(panelizquierdo, javax.swing.BoxLayout.PAGE_AXIS));

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
        paneltabla.setMaximumSize(new java.awt.Dimension(550, 900));
        paneltabla.setMinimumSize(new java.awt.Dimension(550, 600));
        paneltabla.setPreferredSize(new java.awt.Dimension(550, 400));
        paneltabla.setLayout(new javax.swing.BoxLayout(paneltabla, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane2.setBackground(java.awt.Color.white);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(550, 600));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(550, 600));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(550, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Nombre", "Zona"
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

    public void cargarEstados() {
        coordinador = new Coordinador();
        listaEstados = coordinador.obtenerEstados();
        //Actualizamos modelo jTable
        actualizarJTableModel();
    }

    private void actualizarJTableModel() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        for (int i = 0; i < listaEstados.size(); i++) {
            String[] data = new String[3];
            data[0] = listaEstados.get(i).getId() + "";
            data[1] = listaEstados.get(i).getNombre();
            data[2] = listaEstados.get(i).getZona().name(); //Validar que el dato sea nulo
            tableModel.addRow(data);
        }
        jTable1.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void botoneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneditarActionPerformed
        int row = jTable1.getSelectedRow();
        Estado estado = listaEstados.get(row);
        System.out.println("row " + row + " estado " + listaEstados.get(row));
        if (estado != null) {
            Editarestado editarestado = new Editarestado(estado, this);
            editarestado.setVisible(true);
        }
    }//GEN-LAST:event_botoneditarActionPerformed

    private void botoneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoneliminarActionPerformed
        //Obtener index de fila seleccionada, checar primero que lista estados tenga info
        int row = jTable1.getSelectedRow();
        //System.out.println("row " + row + " estado " + listaEstados.get(row));
        //Obtener objeto estado con el index y eliminarlo
        Estado estado = listaEstados.get(row);
        if (estado != null) {
            coordinador = new Coordinador();
            coordinador.borrarEstado(estado);
            //Obtener estados una vez mas
            listaEstados = coordinador.obtenerEstados();
            //Actualizar modelo jTable
            actualizarJTableModel();
        }
    }//GEN-LAST:event_botoneliminarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        Agregarestado agregarestado = new Agregarestado(this);
        agregarestado.setVisible(true);
    }//GEN-LAST:event_btnagregarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoneditar;
    private javax.swing.JButton botoneliminar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelcontenido;
    private javax.swing.JPanel panelizquierdo;
    private javax.swing.JPanel paneltabla;
    // End of variables declaration//GEN-END:variables

}
