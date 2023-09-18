import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

import Clases.LlenasLista_usuario;
import Clases.Mantenimiento_Usuario;
import Clases.Validaciones;
/*
 * Created by JFormDesigner on Tue Sep 12 15:19:18 CST 2023
 */



/**
 * @author jonat
 */
public class usuarioForm extends JDialog {
    LlenasLista_usuario con = new LlenasLista_usuario();
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo =new DefaultTableModel();
    Mantenimiento_Usuario ver = new Mantenimiento_Usuario();
    Validaciones v = new Validaciones();
    public usuarioForm(Window owner) {
        super(owner);
        initComponents();
        v.validarSoloLetras(txt_apellido);
        v.validarSoloLetras(txt_nombre);
        v.validarSoloNumeros(txt_telefono);
        v.validarSoloNumeros(txt_dui);
        v.LongitudDeCaracteres(txt_apellido, 50);
        v.LongitudDeCaracteres(txt_clave, 16);
        v.LongitudDeCaracteres(txt_nombre, 50);
        v.LongitudDeCaracteres(txt_correo, 90);
        v.LongitudDeCaracteres(txt_dui, 9);
        v.LongitudDeCaracteres(txt_telefono, 8);

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Rango");


        this.cmb_tipo.setModel(con.obt_tipoUsuario());

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

    public static boolean validarNumero(String numero, int numeroCaracteres) {
        String regex = "^\\d{"+numeroCaracteres+"}$";
        return Pattern.matches(regex, numero);
    }

    public static boolean validarTexto(String texto) {
        String regex = "^[a-zA-Z\\s]{1,50}$";
        return Pattern.matches(regex, texto);
    }

    public static boolean validarContrasena(String contrasena) {
        String regex = "^[a-zA-Z0-9]+$";
        return Pattern.matches(regex, contrasena);
    }

    public static boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return Pattern.matches(regex, correo);
    }

    private void btn_guardarMouseClicked(MouseEvent e) {
        if (txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty() || txt_clave.getText().isEmpty()
                || txt_dui.getText().isEmpty() || txt_telefono.getText().isEmpty() || cmb_tipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        if (!validarNumero(txt_telefono.getText(), 8) || !validarNumero(txt_dui.getText(), 9) || !validarTexto(txt_nombre.getText()) || !validarTexto(txt_apellido.getText())
        || !validarContrasena(txt_clave.getText()) || !validarCorreo(txt_correo.getText())){
            JOptionPane.showMessageDialog(this, "El formato ingresado en algun dato es incorrecto");
            return;
        }


        Clases.Mantenimiento_Usuario obj = new Clases.Mantenimiento_Usuario();
        obj.setNombre(txt_nombre.getText());
        obj.setApellido(txt_apellido.getText());
        obj.setContra(txt_clave.getText());
        obj.setDui(txt_dui.getText());
        obj.setCorreo(txt_correo.getText());
        obj.setTelefono(Integer.parseInt(txt_telefono.getText()));
        obj.setTipo(cmb_tipo.getSelectedIndex());

        try {
            if (obj.guardarProyecto()) {
                txt_nombre.setText("");
                txt_apellido.setText("");
                txt_clave.setText("");
                txt_dui.setText("");
                txt_correo.setText("");
                txt_telefono.setText("");
                cmb_tipo.setSelectedIndex(0);
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
        Clases.Mantenimiento_Usuario obj = new Clases.Mantenimiento_Usuario();
        obj.setCodigo(Integer.parseInt(txt_id.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarProyecto()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    txt_nombre.setText("");
                    txt_apellido.setText("");
                    txt_clave.setText("");
                    txt_dui.setText("");
                    txt_correo.setText("");
                    txt_telefono.setText("");
                    cmb_tipo.setSelectedIndex(0);
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
        if (txt_id.getText().isEmpty() || txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty() || txt_dui.getText().isEmpty()
                || txt_telefono.getText().isEmpty() || cmb_tipo.getSelectedItem().toString().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Usuario obj = new Clases.Mantenimiento_Usuario();
            obj.setCodigo(Integer.parseInt(txt_id.getText()));
            obj.setNombre(txt_nombre.getText());
            obj.setApellido(txt_apellido.getText());
            obj.setContra(txt_clave.getText());
            obj.setDui(txt_dui.getText());
            obj.setCorreo(txt_correo.getText());
            obj.setTelefono(Integer.parseInt(txt_telefono.getText()));
            obj.setTipo(cmb_tipo.getSelectedIndex());

            if(obj.modificarProyecto()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);
                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    txt_nombre.setText("");
                    txt_apellido.setText("");
                    txt_clave.setText("");
                    txt_dui.setText("");
                    txt_correo.setText("");
                    txt_telefono.setText("");
                    cmb_tipo.setSelectedIndex(0);
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
            String sql = "SELECT * FROM usuario WHERE id_estadoUsuario='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_estadoUsuario");
                txt_id.setText(add1);
                String add2 = rs.getString("nombre");
                txt_nombre.setText(add2);
                String add3 = rs.getString("apellido");
                txt_apellido.setText(add3);
                String add4 = rs.getString("telefono");
                txt_telefono.setText(add4);
                String add5 = rs.getString("dui");
                txt_dui.setText(add5);
                String add6 = rs.getString("correo");
                txt_correo.setText(add6);
                int add7 = Integer.parseInt(rs.getString("id_tipoUsuario"));
                cmb_tipo.setSelectedIndex(add7);

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
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        txt_id = new JTextField();
        txt_nombre = new JTextField();
        txt_apellido = new JTextField();
        txt_dui = new JTextField();
        txt_telefono = new JTextField();
        cmb_tipo = new JComboBox();
        btn_guardar = new JButton();
        btn_eliminar = new JButton();
        btn_modificar = new JButton();
        label8 = new JLabel();
        txt_clave = new JTextField();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        label9 = new JLabel();
        txt_correo = new JTextField();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Registro Usuarios");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID Usuario:");

        //---- label2 ----
        label2.setText("Nombre:");

        //---- label4 ----
        label4.setText("Apellido:");

        //---- label5 ----
        label5.setText("DUI:");

        //---- label6 ----
        label6.setText("Telefono:");

        //---- label7 ----
        label7.setText("Rango Usuario:");

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

        //---- label8 ----
        label8.setText("Contrase\u00f1a");

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

        //---- label9 ----
        label9.setText("Correo:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(label2)
                                        .addComponent(label4)
                                        .addComponent(label8))
                                    .addGap(36, 36, 36)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_clave, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                                .addComponent(txt_nombre, GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_id, GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_apellido, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                    .addGap(23, 23, 23)
                                                    .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label6)
                                                        .addComponent(label7)
                                                        .addComponent(label5)))
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(label9)))
                                            .addGap(26, 26, 26)
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_telefono)
                                                .addComponent(cmb_tipo)
                                                .addComponent(txt_dui, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                                .addComponent(txt_correo, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(btn_guardar)
                                            .addGap(79, 79, 79)
                                            .addComponent(btn_eliminar)
                                            .addGap(68, 68, 68)
                                            .addComponent(btn_modificar))))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(label3)
                    .addGap(46, 46, 46)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(label6)
                        .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(label7)
                        .addComponent(txt_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_tipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(txt_apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5)
                        .addComponent(txt_dui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label8)
                        .addComponent(txt_clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label9)
                        .addComponent(txt_correo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_modificar)
                        .addComponent(btn_eliminar))
                    .addGap(33, 33, 33)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
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
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField txt_id;
    private JTextField txt_nombre;
    private JTextField txt_apellido;
    private JTextField txt_dui;
    private JTextField txt_telefono;
    private JComboBox cmb_tipo;
    private JButton btn_guardar;
    private JButton btn_eliminar;
    private JButton btn_modificar;
    private JLabel label8;
    private JTextField txt_clave;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JLabel label9;
    private JTextField txt_correo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
