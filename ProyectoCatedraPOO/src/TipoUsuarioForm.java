import java.awt.event.*;
import Clases.Mantenimiento_EstadoPelicula;
import Clases.Mantenimiento_TipoUsuario;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Tue Sep 12 14:25:23 CST 2023
 */



/**
 * @author jonat
 */
public class TipoUsuarioForm extends JDialog {

    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_TipoUsuario ver = new Mantenimiento_TipoUsuario();
    public TipoUsuarioForm(Window owner) {
        super(owner);
        initComponents();

        modelo.addColumn("id_estado");
        modelo.addColumn("Estado");

        this.tabla.setModel(modelo);

        try {
            ver.llenartabla(tabla);
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
        if (txt_rango.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_TipoUsuario obj = new Clases.Mantenimiento_TipoUsuario();
        obj.setNombre(txt_rango.getText());

        try {
            if (obj.guardarProyecto()) {
                limpiartabla();
                ver.llenartabla(tabla);
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

    private void btn_eliminarMouseClicked(MouseEvent e) {
        Clases.Mantenimiento_TipoUsuario obj = new Clases.Mantenimiento_TipoUsuario();
        obj.setCodigo(Integer.parseInt(txt_idRango.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarProyecto()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);

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

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_idRango.getText().equals("") ||
                txt_rango.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_EstadoPelicula obj = new Clases.Mantenimiento_EstadoPelicula();
            obj.setCodigo(Integer.parseInt(txt_idRango.getText()));
            obj.setNombre(txt_rango.getText());

            if(obj.modificarProyecto()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);

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

    private void tablaMouseClicked(MouseEvent e) {
        try {
            int row = tabla.getSelectedRow();
            String Table_click = (tabla.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM tipo_usuario WHERE id_tipoUsuario='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_tipoUsuario");
                txt_idRango.setText(add1);
                String add2 = rs.getString("tipoUsuario");
                txt_rango.setText(add2);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        txt_idRango = new JTextField();
        txt_rango = new JTextField();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Rango Usuario");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID Rango");

        //---- label2 ----
        label2.setText("Rango");

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

        //---- btn_guardar ----
        btn_guardar.setText("Agregar");
        btn_guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_guardarMouseClicked(e);
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

        //---- btn_modificar ----
        btn_modificar.setText("Modificar");
        btn_modificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_modificarMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(214, 214, 214)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(153, 153, 153)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(label1))
                            .addGap(54, 54, 54)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_idRango, GroupLayout.Alignment.LEADING)
                                .addComponent(txt_rango, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(139, 139, 139)
                            .addComponent(btn_guardar)
                            .addGap(42, 42, 42)
                            .addComponent(btn_eliminar)
                            .addGap(42, 42, 42)
                            .addComponent(btn_modificar)))
                    .addContainerGap(83, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(label3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(txt_idRango, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(48, 48, 48)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_rango, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_modificar))
                    .addGap(30, 30, 30)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label3;
    private JLabel label1;
    private JLabel label2;
    private JTextField txt_idRango;
    private JTextField txt_rango;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
