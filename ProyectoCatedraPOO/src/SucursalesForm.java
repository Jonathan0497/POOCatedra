import java.awt.event.*;

import Clases.LlenarLista_sucursales;
import Clases.LlenasLista_usuario;
import Clases.Mantenimiento_Formato;
import Clases.Mantenimiento_Sucursales;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Wed Sep 13 17:42:14 CST 2023
 */



/**
 * @author Carlos
 */
public class SucursalesForm extends JDialog {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Sucursales ver = new Mantenimiento_Sucursales();
    LlenarLista_sucursales con = new LlenarLista_sucursales();
    public SucursalesForm(Window owner) {
        super(owner);
        initComponents();
        this.cmb_gerenteSucursales.setModel(con.obt_tipoGerente());

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Gerente");

        this.tabla.setModel(modelo);

        try {
            ver.llenarTabla(tabla);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al llenar la tabla: " + e.getMessage());
        }
    }


    void limpiartabla(){
        int filas=modelo.getRowCount();
        for(int i=0; i<filas;i++){
            modelo.removeRow(0);
        }
    }

    private void btn_guardarMouseClicked(MouseEvent e) {
        if (txt_nombreSucursales.getText().isEmpty() || txt_telefonoSucursales.getText().isEmpty() || txt_direccionSucursales.getText().equals("") || cmb_gerenteSucursales.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_Sucursales obj = new Clases.Mantenimiento_Sucursales();
        obj.setNombre(txt_nombreSucursales.getText());
        obj.setTelefono(Integer.parseInt(txt_telefonoSucursales.getText()));
        obj.setDireccion(txt_direccionSucursales.getText());
        obj.setId_usuario(cmb_gerenteSucursales.getSelectedIndex());

        try {
            if (obj.guardarFormato()) {
                limpiartabla();
                txt_nombreSucursales.setText("");
                txt_telefonoSucursales.setText("");
                txt_direccionSucursales.setText("");
                cmb_gerenteSucursales.setSelectedIndex(0);
                txt_idSucursales.setText("");
                ver.llenarTabla(tabla);
                JOptionPane.showMessageDialog(this, "Datos guardados");
                this.tabla.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar datos");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage());
        }
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_idSucursales.getText().equals("") || txt_nombreSucursales.getText().equals("") || txt_telefonoSucursales.getText().equals("") || txt_direccionSucursales.getText().equals("") || cmb_gerenteSucursales.getSelectedItem().toString().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Sucursales obj = new Clases.Mantenimiento_Sucursales();
            obj.setId_sucursales(Integer.parseInt(txt_idSucursales.getText()));
            obj.setNombre(txt_nombreSucursales.getText());
            obj.setTelefono(Integer.parseInt(txt_telefonoSucursales.getText()));
            obj.setDireccion(txt_direccionSucursales.getText());
            obj.setId_usuario(cmb_gerenteSucursales.getSelectedIndex());

            if(obj.modificarFormato()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tabla);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tabla.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al modificar datos: " + ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error al modificar datos");
            }

        }
    }

    private void btn_eliminarMouseClicked(MouseEvent e) {
        Clases.Mantenimiento_Sucursales obj = new Clases.Mantenimiento_Sucursales();
        obj.setId_sucursales(Integer.parseInt(txt_idSucursales.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarFormato()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tabla);

                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    this.tabla.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar datos: " + ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void tablaMouseClicked(MouseEvent e) {
        try {
            int row = tabla.getSelectedRow();
            String Table_click = (tabla.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM sucursales WHERE id_sucursales='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_sucursales");
                txt_idSucursales.setText(add1);
                String add2 = rs.getString("nombre");
                txt_nombreSucursales.setText(add2);
                String add3 = rs.getString("telefono");
                txt_telefonoSucursales.setText(add3);
                String add4 = rs.getString("direccion");
                txt_direccionSucursales.setText(add4);
                int add5 = Integer.parseInt(rs.getString("id_usuario"));
                cmb_gerenteSucursales.setSelectedIndex(add5);


            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        txt_telefonoSucursales = new JTextField();
        txt_nombreSucursales = new JTextField();
        txt_idSucursales = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        txt_direccionSucursales = new JTextField();
        cmb_gerenteSucursales = new JComboBox();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();
        btn_guardar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Registro Sucursales");

        //---- label2 ----
        label2.setText("Id:");

        //---- label3 ----
        label3.setText("Nombre:");

        //---- label4 ----
        label4.setText("Tel\u00e9fono:");

        //---- label5 ----
        label5.setText("Direcci\u00f3n:");

        //---- label6 ----
        label6.setText("Gerente:");

        //======== scrollPane1 ========
        {

            //---- tabla ----
            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tablaMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tabla);
        }

        //---- btn_modificar ----
        btn_modificar.setText("Modificar");
        btn_modificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_modificarMouseClicked(e);
            }
        });

        //---- btn_eliminar ----
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_eliminarMouseClicked(e);
            }
        });

        //---- btn_guardar ----
        btn_guardar.setText("Guardar");
        btn_guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_guardarMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label4)
                                .addComponent(label3)
                                .addComponent(label2))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_idSucursales)
                                .addComponent(txt_nombreSucursales)
                                .addComponent(txt_telefonoSucursales, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label5)
                                .addComponent(label6))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_direccionSucursales)
                                .addComponent(cmb_gerenteSucursales, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(257, 257, 257)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(109, 109, 109)
                            .addComponent(btn_guardar)
                            .addGap(82, 82, 82)
                            .addComponent(btn_modificar)
                            .addGap(72, 72, 72)
                            .addComponent(btn_eliminar)))
                    .addContainerGap(74, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(label1)
                    .addGap(59, 59, 59)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label2)
                            .addGap(18, 18, 18)
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(label4))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_idSucursales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5)
                                .addComponent(txt_direccionSucursales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_nombreSucursales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label6)
                                .addComponent(cmb_gerenteSucursales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(txt_telefonoSucursales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_guardar)
                        .addComponent(btn_modificar))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txt_telefonoSucursales;
    private JTextField txt_nombreSucursales;
    private JTextField txt_idSucursales;
    private JLabel label5;
    private JLabel label6;
    private JTextField txt_direccionSucursales;
    private JComboBox cmb_gerenteSucursales;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    private JButton btn_guardar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
