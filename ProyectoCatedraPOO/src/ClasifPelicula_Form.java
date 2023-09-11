import Clases.Mantenimiento_Categoria;
import Clases.Mantenimiento_Clasificacion;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Mon Sep 11 08:33:41 CST 2023
 */



/**
 * @author jonat
 */
public class ClasifPelicula_Form extends JDialog {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Clasificacion ver = new Mantenimiento_Clasificacion();
    public ClasifPelicula_Form(Window owner) {
        super(owner);
        initComponents();
        modelo.addColumn("id_clasificacion");
        modelo.addColumn("Clasificacion");

        this.tabla.setModel(modelo);

        try {
            ver.ShowTable(tabla);
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
        if (txt_clasificacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_Clasificacion obj = new Clases.Mantenimiento_Clasificacion();
        obj.setNombre(txt_clasificacion.getText());

        try {
            if (obj.guardarProyecto()) {
                limpiartabla();
                ver.ShowTable(tabla);
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
        Clases.Mantenimiento_Clasificacion obj = new Clases.Mantenimiento_Clasificacion();
        obj.setCodigo(Integer.parseInt(txt_idClasificacion.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarProyecto()){
                try {
                    limpiartabla();
                    ver.ShowTable(tabla);

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
        if (txt_idClasificacion.getText().equals("") ||
                txt_clasificacion.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Clasificacion obj = new Clases.Mantenimiento_Clasificacion();
            obj.setCodigo(Integer.parseInt(txt_idClasificacion.getText()));
            obj.setNombre(txt_clasificacion.getText());

            if(obj.modificarProyecto()){
                try {
                    limpiartabla();
                    ver.ShowTable(tabla);

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
            String sql = "SELECT * FROM clasificacion WHERE id_clasificacion='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_clasificacion");
                txt_idClasificacion.setText(add1);
                String add2 = rs.getString("clasificacion");
                txt_clasificacion.setText(add2);

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
        txt_idClasificacion = new JTextField();
        txt_clasificacion = new JTextField();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Clasificaci\u00f3n de Peliculas");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID Clasificacion");

        //---- label2 ----
        label2.setText("Clasificacion");

        //---- btn_guardar ----
        btn_guardar.setText("Guardar");
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(174, Short.MAX_VALUE)
                    .addComponent(label3)
                    .addGap(169, 169, 169))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btn_guardar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                            .addComponent(btn_eliminar)
                            .addGap(52, 52, 52)
                            .addComponent(btn_modificar)
                            .addGap(183, 183, 183))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(label2))
                            .addGap(61, 61, 61)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_idClasificacion, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addComponent(txt_clasificacion, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addContainerGap(207, Short.MAX_VALUE))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(label3)
                    .addGap(78, 78, 78)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(txt_idClasificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_clasificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_modificar))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
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
    private JTextField txt_idClasificacion;
    private JTextField txt_clasificacion;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    private JScrollPane scrollPane1;
    private JTable tabla;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
