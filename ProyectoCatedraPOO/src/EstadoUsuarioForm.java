import Clases.Mantenimiento_EstadoPelicula;
import Clases.Mantenimiento_EstadoUsuario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Tue Sep 12 15:00:52 CST 2023
 */



/**
 * @author jonat
 */
public class EstadoUsuarioForm extends JDialog {

    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_EstadoUsuario ver = new Mantenimiento_EstadoUsuario();
    public EstadoUsuarioForm(Window owner) {
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
        if (txt_estado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_EstadoUsuario obj = new Clases.Mantenimiento_EstadoUsuario();
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
        Clases.Mantenimiento_EstadoUsuario obj = new Clases.Mantenimiento_EstadoUsuario();
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

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_id.getText().equals("") ||
                txt_estado.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_EstadoUsuario obj = new Clases.Mantenimiento_EstadoUsuario();
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
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        txt_id = new JTextField();
        txt_estado = new JTextField();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Estado Usuario");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID Estado Usuario");

        //---- label2 ----
        label2.setText("Estado Usuario");

        //======== scrollPane1 ========
        {
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
                            .addGap(219, 219, 219)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(label2))
                                    .addGap(63, 63, 63)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_id, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                        .addComponent(txt_estado, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btn_guardar)
                                    .addGap(49, 49, 49)
                                    .addComponent(btn_eliminar)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                    .addComponent(btn_modificar)))))
                    .addContainerGap(162, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 69, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
                    .addGap(58, 58, 58))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(label3)
                    .addGap(81, 81, 81)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_estado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_modificar))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
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
    private JTextField txt_id;
    private JTextField txt_estado;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
