/*
 * Created by JFormDesigner on Sun Sep 10 22:47:01 CST 2023
 */

import Clases.Mantenimiento_Categoria;
import Clases.Validaciones_ExpresionesRegulares;
import Clases.Mantenimiento_EstadoPelicula;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author jonat
 */
public class CategoriaForm extends JDialog {

    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Categoria ver = new Mantenimiento_Categoria();
    public CategoriaForm(Window owner) {
        super(owner);
        initComponents();
        txt_idCategoria.setEnabled(false);
        modelo.addColumn("id_categoria");
        modelo.addColumn("Categoria");

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
        if (txt_categoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        if (!Validaciones_ExpresionesRegulares.validarTexto(txt_categoria.getText())) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un texto válido.");
            return;
        }

        Clases.Mantenimiento_Categoria obj = new Clases.Mantenimiento_Categoria();
        obj.setNombre(txt_categoria.getText());


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
        Clases.Mantenimiento_Categoria obj = new Clases.Mantenimiento_Categoria();
        obj.setCodigo(Integer.parseInt(txt_idCategoria.getText()));
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
        if (txt_idCategoria.getText().equals("") || txt_categoria.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        if (!Validaciones_ExpresionesRegulares.validarTexto(txt_categoria.getText())) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un texto válido.");
            return;
        }

        Clases.Mantenimiento_Categoria obj = new Clases.Mantenimiento_Categoria();
        obj.setCodigo(Integer.parseInt(txt_idCategoria.getText()));
        obj.setNombre(txt_categoria.getText());

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

    private void tablaMouseClicked(MouseEvent e) {
        try {
            int row = tabla.getSelectedRow();
            String Table_click = (tabla.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM genero WHERE id_genero='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_genero");
                txt_idCategoria.setText(add1);
                String add2 = rs.getString("genero");
                txt_categoria.setText(add2);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        txt_idCategoria = new JTextField();
        txt_categoria = new JTextField();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Categoria Pelicula");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label4 ----
        label4.setText("ID Categoria");

        //---- label5 ----
        label5.setText("Categoria");

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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(148, 148, 148)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label4)
                                        .addComponent(label5))
                                    .addGap(79, 79, 79)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_idCategoria, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(txt_categoria, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btn_guardar)
                                    .addGap(36, 36, 36)
                                    .addComponent(btn_eliminar)
                                    .addGap(29, 29, 29)
                                    .addComponent(btn_modificar)))))
                    .addContainerGap(182, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
                    .addGap(58, 58, 58))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(label3)
                    .addGap(93, 93, 93)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(txt_idCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(txt_categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_modificar))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField txt_idCategoria;
    private JTextField txt_categoria;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
