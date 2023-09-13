import Clases.Mantenimiento_EstadoUsuario;
import Clases.Mantenimiento_Formato;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Tue Sep 12 23:09:52 CST 2023
 */



/**
 * @author jonat
 */
public class Formato extends JDialog {

    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Formato ver = new Mantenimiento_Formato();
    public Formato(Window owner) {
        super(owner);
        initComponents();

        modelo.addColumn("id_formato");
        modelo.addColumn("formato");
        modelo.addColumn("Precio");

        this.tablaUsuario.setModel(modelo);

        try {
            ver.llenarTabla(tablaUsuario);
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

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_idFormato.getText().equals("") || txt_formato.getText().equals("") || txt_precio.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Formato obj = new Clases.Mantenimiento_Formato();
            obj.setId_formato(Integer.parseInt(txt_idFormato.getText()));
            obj.setFormato(txt_formato.getText());
            obj.setPrecio(Double.parseDouble(txt_precio.getText()));

            if(obj.modificarFormato()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tablaUsuario);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tablaUsuario.setModel(modelo);
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
        Clases.Mantenimiento_Formato obj = new Clases.Mantenimiento_Formato();
        obj.setId_formato(Integer.parseInt(txt_idFormato.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarFormato()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tablaUsuario);

                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    this.tablaUsuario.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar datos: " + ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void btn_guardarMouseClicked(MouseEvent e) {
        if (txt_formato.getText().isEmpty() || txt_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_Formato obj = new Clases.Mantenimiento_Formato();
        obj.setFormato(txt_formato.getText());
        obj.setPrecio(Double.parseDouble(txt_precio.getText()));

        try {
            if (obj.guardarFormato()) {
                limpiartabla();
                txt_formato.setText("");
                txt_precio.setText("");
                txt_idFormato.setText("");
                ver.llenarTabla(tablaUsuario);
                JOptionPane.showMessageDialog(this, "Datos guardados");
                this.tablaUsuario.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar datos");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage());
        }
    }

    private void scrollPane1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tablaUsuarioMouseClicked(MouseEvent e) {
        try {
            int row = tablaUsuario.getSelectedRow();
            String Table_click = (tablaUsuario.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM formato WHERE id_formato='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_formato");
                txt_idFormato.setText(add1);
                String add2 = rs.getString("formato");
                txt_formato.setText(add2);
                String add3 = rs.getString("precio");
                txt_precio.setText(add3);


            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        txt_idFormato = new JTextField();
        txt_formato = new JTextField();
        txt_precio = new JTextField();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();
        btn_guardar = new JButton();
        label4 = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        tablaUsuario = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("ID Formato");

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

        //---- label4 ----
        label4.setText("Precio");

        //---- label2 ----
        label2.setText("Formato");

        //======== scrollPane1 ========
        {
            scrollPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scrollPane1MouseClicked(e);
                }
            });

            //---- tablaUsuario ----
            tablaUsuario.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tablaUsuarioMouseClicked(e);
                    scrollPane1MouseClicked(e);
                    tablaUsuarioMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tablaUsuario);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(155, 155, 155)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label1)
                                        .addComponent(label4)
                                        .addComponent(label2))
                                    .addGap(43, 43, 43))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btn_guardar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txt_formato, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btn_eliminar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_modificar))
                                .addComponent(txt_precio, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_idFormato, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(111, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txt_idFormato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_formato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(txt_precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_modificar)
                        .addComponent(btn_guardar))
                    .addGap(48, 48, 48)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(103, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JTextField txt_idFormato;
    private JTextField txt_formato;
    private JTextField txt_precio;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    private JButton btn_guardar;
    private JLabel label4;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTable tablaUsuario;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
