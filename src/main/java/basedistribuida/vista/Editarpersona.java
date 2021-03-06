/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.vista;

import basedistribuida.beans.InformacionPersona;
import basedistribuida.broadcast.BroadcastUtils;
import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Colonia;
import basedistribuida.model.Direccion;
import basedistribuida.model.Estado;
import basedistribuida.model.Municipio;
import basedistribuida.model.Persona;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author iovanny
 */
public class Editarpersona extends javax.swing.JFrame {
private Personas personasFrame;
private InformacionPersona persona;
private Coordinador coordinador;
private List<Estado> listaEstados;
private List<Municipio> listaMunicipios;
private List<Colonia> listaColonias;
private Municipio municipio;
private int id = 0;
private int idDireccion = 0;
    /**
     * Creates new form Agregarpersona
     */
    public Editarpersona(InformacionPersona persona, Personas personasFrame) {
       this.persona=persona;
       this.personasFrame=personasFrame;
        initComponents();
        init();
    }
  private void init() {
        //Cargar estados
        coordinador = new Coordinador();
        listaEstados = coordinador.obtenerEstados();
         
        if (!listaEstados.isEmpty()) {
            ArrayList<String> nombresEstados = new ArrayList<>();
            ArrayList<String> nombresMunicipios = new ArrayList<>();
            ArrayList<String> nombresColonias = new ArrayList<>();
            int indexASeleccionar = -1;
            int i = 0;
            for (Estado e : listaEstados) {
                nombresEstados.add(e.getNombre());
                if (e.getId() == persona.getDireccion().getIdEstado()) {
                    indexASeleccionar =  i;
                    listaMunicipios = coordinador.obtenerMunicipiosByEstado(e.getId());
                    System.out.println(listaMunicipios);
                }
                i++;
            }
            jComboBox1.setModel(new DefaultComboBoxModel(nombresEstados.toArray()));
            jComboBox1.setSelectedIndex(indexASeleccionar);
            i=0;
            indexASeleccionar = -1;
            
            for (Municipio e : listaMunicipios) {
                nombresMunicipios.add(e.getNombre());
                if (e.getId() == persona.getDireccion().getIdMunicipio()) {
                    indexASeleccionar =  i;
                    listaColonias = coordinador.obtenerColoniasByMunicipio(e.getId());
                                        System.out.println(listaColonias);

                }
                i++;
            }
            jComboBox2.setModel(new DefaultComboBoxModel(nombresMunicipios.toArray()));
             jComboBox2.setSelectedIndex(indexASeleccionar);
           
            i=0;
            indexASeleccionar = 0;
            for (Colonia e : listaColonias) {
                nombresColonias.add(e.getNombre());
                if (e.getId() == persona.getDireccion().getIdColonia()) {
                    indexASeleccionar =  i;
                }
                i++;
            }
            jComboBox3.setModel(new DefaultComboBoxModel(nombresColonias.toArray()));
            jComboBox3.setSelectedIndex(indexASeleccionar);
            
            id = persona.getPersona().getId();
            idDireccion = persona.getDireccion().getId();
            tb_nombre.setText(persona.getPersona().getNombre());
            tb_apaterno.setText(persona.getPersona().getApellidoPaterno());
            tb_amaterno.setText(persona.getPersona().getApellidoMaterno());
            tb_fecnac.setText(persona.getPersona().getFechaNacimiento()+"");
            tb_genero.setText(persona.getPersona().getGenero()+"");
            tb_calle.setText(persona.getDireccion().getCalle());
             tb_cp.setText(persona.getDireccion().getCp()+"");
            //Seleccionar estado correcto en combo
           
            
        } else {
            dispose();
            //Mensaje de actualizacion exitosa
            JOptionPane.showMessageDialog(this, "No se pueden editar municipios ya que no existen estados", "Error", JOptionPane.ERROR_MESSAGE);
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
        panelpersona = new javax.swing.JPanel();
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
        pnl_estado = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        pnl_municipio = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        pnl_colonia = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        panelfooter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setTitle("Editar persona");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panelcontenido.setLayout(new javax.swing.BoxLayout(panelcontenido, javax.swing.BoxLayout.LINE_AXIS));

        panelpersona.setLayout(new javax.swing.BoxLayout(panelpersona, javax.swing.BoxLayout.PAGE_AXIS));

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

        pnl_estado.setLayout(new javax.swing.BoxLayout(pnl_estado, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setText("Estado");
        jLabel11.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel11.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel11.setName(""); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_estado.add(jLabel11);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(300, 30));
        jComboBox1.setMinimumSize(new java.awt.Dimension(300, 30));
        jComboBox1.setPreferredSize(new java.awt.Dimension(300, 30));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        pnl_estado.add(jComboBox1);

        paneldatos.add(pnl_estado);

        pnl_municipio.setLayout(new javax.swing.BoxLayout(pnl_municipio, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setText("Municipio");
        jLabel10.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel10.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel10.setName(""); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_municipio.add(jLabel10);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setMaximumSize(new java.awt.Dimension(300, 30));
        jComboBox2.setMinimumSize(new java.awt.Dimension(300, 30));
        jComboBox2.setName(""); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_municipio.add(jComboBox2);

        paneldatos.add(pnl_municipio);

        pnl_colonia.setLayout(new javax.swing.BoxLayout(pnl_colonia, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setText("Colonia");
        jLabel9.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel9.setMinimumSize(new java.awt.Dimension(200, 20));
        jLabel9.setName(""); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 20));
        pnl_colonia.add(jLabel9);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setMaximumSize(new java.awt.Dimension(300, 30));
        jComboBox3.setMinimumSize(new java.awt.Dimension(300, 30));
        jComboBox3.setPreferredSize(new java.awt.Dimension(300, 30));
        pnl_colonia.add(jComboBox3);

        paneldatos.add(pnl_colonia);

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
            .addGap(0, 862, Short.MAX_VALUE)
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
            Persona persona = new Persona();
            Direccion direccion = new Direccion();
            persona.setId(id);
            persona.setNombre(tb_nombre.getText());
            persona.setApellidoPaterno(tb_apaterno.getText());
            persona.setApellidoMaterno(tb_amaterno.getText());
            persona.setGenero(Persona.Genero.valueOf(tb_genero.getText()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                persona.setFechaNacimiento(formatter.parse(tb_fecnac.getText()));
            } catch (ParseException e) {
            }
            direccion.setId(idDireccion);
            direccion.setCalle(tb_calle.getText());
            direccion.setCp(Integer.parseInt(tb_cp.getText()));

            Estado estado = listaEstados.get(jComboBox1.getSelectedIndex());
            direccion.setIdEstado(estado.getId());
            Municipio municipio = listaMunicipios.get(jComboBox2.getSelectedIndex());
            direccion.setIdMunicipio(municipio.getId());
            Colonia colonia = listaColonias.get(jComboBox3.getSelectedIndex());
            direccion.setIdColonia(colonia.getId());
            
             
            coordinador.editarPersona(persona, direccion, estado);
             
            BroadcastUtils.mensajeAServidorRemoto("Operacion");
            
            personasFrame.cargarPersonas();
            dispose();
            //Mensaje de actualizacion exitosa
            JOptionPane.showMessageDialog(this, "Persona editada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Mandar mensaje de que se necesitan datos
            JOptionPane.showMessageDialog(this, "Todos los datos son necesarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
         ArrayList<String> nombresEstados = new ArrayList<>();
            ArrayList<String> nombresMunicipios = new ArrayList<>();
            ArrayList<String> nombresColonias = new ArrayList<>();
        System.out.println(jComboBox1.getSelectedItem());
        for (Estado e : listaEstados) {
            if (e.getNombre() == jComboBox1.getSelectedItem()) {
                listaMunicipios = coordinador.obtenerMunicipiosByEstado(e.getId());
            }

        }
        for (Municipio m : listaMunicipios) {
            nombresMunicipios.add(m.getNombre());
            listaColonias = coordinador.obtenerColoniasByMunicipio(m.getId());

        }
        jComboBox2.setModel(new DefaultComboBoxModel(nombresMunicipios.toArray()));

                    for (Colonia c : listaColonias) {
                nombresColonias.add(c.getNombre());
    
            }
            jComboBox3.setModel(new DefaultComboBoxModel(nombresColonias.toArray()));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel pnl_municipio;
    private javax.swing.JPanel pnl_nombre;
    private javax.swing.JTextField tb_amaterno;
    private javax.swing.JTextField tb_apaterno;
    private javax.swing.JTextField tb_calle;
    private javax.swing.JTextField tb_cp;
    private javax.swing.JTextField tb_fecnac;
    private javax.swing.JTextField tb_genero;
    private javax.swing.JTextField tb_nombre;
    // End of variables declaration//GEN-END:variables
}
