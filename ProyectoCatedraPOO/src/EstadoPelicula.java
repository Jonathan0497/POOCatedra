import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import Clases.Conexion;
import Clases.Mantenimiento_EstadoPelicula;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Wed Sep 06 20:37:02 CST 2023
 */



/**
 * @author jonat
 */
public class EstadoPelicula extends JFrame {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_EstadoPelicula ver = new Mantenimiento_EstadoPelicula();
    public EstadoPelicula() {
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
        if (txt_estado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_EstadoPelicula obj = new Clases.Mantenimiento_EstadoPelicula();
        obj.setNombre(txt_estado.getText());

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
        Clases.Mantenimiento_EstadoPelicula obj = new Clases.Mantenimiento_EstadoPelicula();
        obj.setCodigo(Integer.parseInt(txt_id.getText()));
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

    private void tablaMouseClicked(MouseEvent e) {
        try {
            int row = tabla.getSelectedRow();
            String Table_click = (tabla.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM estado_pelicula WHERE id_estadoPelicula='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_estadoPelicula");
                txt_id.setText(add1);
                String add2 = rs.getString("estadoPelicula");
                txt_estado.setText(add2);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_id.getText().equals("") ||
                txt_estado.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_EstadoPelicula obj = new Clases.Mantenimiento_EstadoPelicula();
            obj.setCodigo(Integer.parseInt(txt_id.getText()));
            obj.setNombre(txt_estado.getText());

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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        label2 = new JLabel();
        txt_id = new JTextField();
        txt_estado = new JTextField();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("ID Estado");

        //---- label2 ----
        label2.setText("Estado");

        //---- label3 ----
        label3.setText("Estado Pelicula");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

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
                            .addGap(230, 230, 230)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(167, 167, 167)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(label2))
                            .addGap(76, 76, 76)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_id)
                                .addComponent(txt_estado, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(159, 159, 159)
                            .addComponent(btn_guardar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_eliminar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_modificar)))
                    .addContainerGap(182, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 34, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(label3)
                    .addGap(58, 58, 58)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_estado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_guardar)
                        .addComponent(btn_modificar))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoPelicula().setVisible(true);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JLabel label2;
    private JTextField txt_id;
    private JTextField txt_estado;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
