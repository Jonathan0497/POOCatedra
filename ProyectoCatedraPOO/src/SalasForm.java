import java.awt.event.*;
import Clases.LlenarLista_salas;
import Clases.LlenarLista_sucursales;
import Clases.Mantenimiento_Salas;
import Clases.Mantenimiento_Sucursales;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Wed Sep 13 19:40:55 CST 2023
 */



/**
 * @author Carlos
 */
public class SalasForm extends JDialog {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Salas ver = new Mantenimiento_Salas();
    LlenarLista_salas con = new LlenarLista_salas();
    public SalasForm(Window owner) {
        super(owner);
        initComponents();
        this.cmb_sucursal.setModel(con.obt_sucursal());

        modelo.addColumn("Id");
        modelo.addColumn("Número de salas");
        modelo.addColumn("Sucursal");


        this.tabla1.setModel(modelo);

        try {
            ver.llenarTablaSalas(tabla1);
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
        if (txt_NumSalas.getText().isEmpty() || cmb_sucursal.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        Clases.Mantenimiento_Salas obj = new Clases.Mantenimiento_Salas();
        obj.setNumero_sala(Integer.parseInt(txt_NumSalas.getText()));
        obj.setId_sucursales(cmb_sucursal.getSelectedIndex());

        try {
            if (obj.guardarSalas()) {
                limpiartabla();
                txt_NumSalas.setText("");
                cmb_sucursal.setSelectedIndex(0);
                txt_idSalas.setText("");
                ver.llenarTablaSalas(tabla1);
                JOptionPane.showMessageDialog(this, "Datos guardados");
                this.tabla1.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar datos");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage());
        }
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_idSalas.getText().equals("") || txt_NumSalas.getText().equals("") || cmb_sucursal.getSelectedItem().toString().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Salas obj = new Clases.Mantenimiento_Salas();
            obj.setId_sucursales(Integer.parseInt(txt_idSalas.getText()));
            obj.setNumero_sala(Integer.parseInt(txt_NumSalas.getText()));
            obj.setId_sucursales(cmb_sucursal.getSelectedIndex());

            if(obj.modificarSalas()){
                try {
                    limpiartabla();
                    ver.llenarTablaSalas(tabla1);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tabla1.setModel(modelo);
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
        Clases.Mantenimiento_Salas obj = new Clases.Mantenimiento_Salas();
        obj.setId_salas(Integer.parseInt(txt_idSalas.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarSalas()){
                try {
                    limpiartabla();
                    ver.llenarTablaSalas(tabla1);

                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    this.tabla1.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar datos: " + ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void tabla1MouseClicked(MouseEvent e) {
        try {
            int row = tabla1.getSelectedRow();
            String Table_click = (tabla1.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM salas WHERE id_salas='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_salas");
                txt_idSalas.setText(add1);
                String add2 = rs.getString("numero_sala");
                txt_NumSalas.setText(add2);
                int add3 = Integer.parseInt(rs.getString("id_sucursales"));
                cmb_sucursal.setSelectedIndex(add3);


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
        cmb_sucursal = new JComboBox();
        txt_idSalas = new JTextField();
        txt_NumSalas = new JTextField();
        btn_guardar = new JButton();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();
        scrollPane1 = new JScrollPane();
        tabla1 = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Registro Salas");

        //---- label2 ----
        label2.setText("Id:");

        //---- label3 ----
        label3.setText("Numero de salas:");

        //---- label4 ----
        label4.setText("Sucursal:");

        //---- btn_guardar ----
        btn_guardar.setText("Guardar");
        btn_guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_guardarMouseClicked(e);
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

        //---- btn_eliminar ----
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_eliminarMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tabla1 ----
            tabla1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tabla1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tabla1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(227, 227, 227)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label3)
                                        .addComponent(label4)
                                        .addComponent(label2))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_NumSalas, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                        .addComponent(txt_idSalas, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                        .addComponent(cmb_sucursal, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btn_guardar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_modificar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_eliminar))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(147, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(label1)
                    .addGap(85, 85, 85)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_idSalas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txt_NumSalas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(cmb_sucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_modificar)
                        .addComponent(btn_eliminar))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE))
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
    private JComboBox cmb_sucursal;
    private JTextField txt_idSalas;
    private JTextField txt_NumSalas;
    private JButton btn_guardar;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    private JScrollPane scrollPane1;
    private JTable tabla1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
