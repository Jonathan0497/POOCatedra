import java.awt.event.*;

import Clases.*;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Wed Sep 13 23:09:56 CST 2023
 */



/**
 * @author Carlos
 */
public class PeliculasForm extends JDialog {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Pelicula ver = new Mantenimiento_Pelicula();
    LlenarLista_pelicula con = new LlenarLista_pelicula();
    public PeliculasForm(Window owner) {
        super(owner);
        initComponents();
        txt_idPeliculas.setEnabled(false);
        this.cmb_generoPeliculas.setModel(con.obt_generoPelicula());

        modelo.addColumn("Id");
        modelo.addColumn("Nombre de la pelicula");
        modelo.addColumn("Descripción");
        modelo.addColumn("Año de lanzamiento");
        modelo.addColumn("Genero");
        modelo.addColumn("Duración");

        this.tablaPeliculas.setModel(modelo);

        try {
            ver.llenarTabla(tablaPeliculas);
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
        if (txt_nomPeliculas.getText().isEmpty() || txt_descripccionPeliculas.getText().isEmpty() || txt_anioInicioPeliculas.getText().equals("") || cmb_generoPeliculas.getSelectedItem().toString().equals("") || txt_duracion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }

        if (!Validaciones_ExpresionesRegulares.validarTexto(txt_nomPeliculas.getText()) ||
                !Validaciones_ExpresionesRegulares.validarTexto(txt_descripccionPeliculas.getText()) ||
                !Validaciones_ExpresionesRegulares.validarFecha(txt_anioInicioPeliculas.getText()) ||
                !Validaciones_ExpresionesRegulares.validarFecha(txt_duracion.getText()))
        {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un texto válido.");
            return;
        }

        Clases.Mantenimiento_Pelicula obj = new Clases.Mantenimiento_Pelicula();
        obj.setNombre_pelicula(txt_nomPeliculas.getText());
        obj.setDescripcion(txt_descripccionPeliculas.getText());
        obj.setAnio_lanzamiento(txt_anioInicioPeliculas.getText());
        obj.setId_genero(cmb_generoPeliculas.getSelectedIndex());
        obj.setDuracion(txt_duracion.getText());

        try {
            if (obj.guardarPelicula()) {
                limpiartabla();
                txt_nomPeliculas.setText("");
                txt_descripccionPeliculas.setText("");
                txt_anioInicioPeliculas.setText("");
                cmb_generoPeliculas.setSelectedIndex(0);
                txt_duracion.setText("");
                txt_idPeliculas.setText("");
                ver.llenarTabla(tablaPeliculas);
                JOptionPane.showMessageDialog(this, "Datos guardados");
                this.tablaPeliculas.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar datos");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage());
        }
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_idPeliculas.getText().equals("") || txt_nomPeliculas.getText().equals("") || txt_descripccionPeliculas.getText().equals("") || txt_anioInicioPeliculas.getText().equals("") || cmb_generoPeliculas.getSelectedItem().toString().equals("") || txt_duracion.getText().equals("")){

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else{
            Clases.Mantenimiento_Pelicula obj = new Clases.Mantenimiento_Pelicula();
            obj.setId_pelicula(Integer.parseInt(txt_idPeliculas.getText()));
            obj.setNombre_pelicula(txt_nomPeliculas.getText());
            obj.setDescripcion(txt_descripccionPeliculas.getText());
            obj.setDescripcion(txt_anioInicioPeliculas.getText());
            obj.setId_genero(cmb_generoPeliculas.getSelectedIndex());
            obj.setDuracion(txt_duracion.getText());

            if(obj.modificarPelicula()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tablaPeliculas);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tablaPeliculas.setModel(modelo);
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
        Clases.Mantenimiento_Pelicula obj = new Clases.Mantenimiento_Pelicula();
        obj.setId_pelicula(Integer.parseInt(txt_idPeliculas.getText()));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(eliminar == 0){
            if(obj.eliminarPelicula()){
                try {
                    limpiartabla();
                    ver.llenarTabla(tablaPeliculas);

                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    this.tablaPeliculas.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar datos: " + ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void tablaPeliculasMouseClicked(MouseEvent e) {
        try {
            int row = tablaPeliculas.getSelectedRow();
            String Table_click = (tablaPeliculas.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM peliculas WHERE id_pelicula='" + Table_click + "' ";
            PreparedStatement ps = cn.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String add1 = rs.getString("id_pelicula");
                txt_idPeliculas.setText(add1);
                String add2 = rs.getString("nombre_pelicula");
                txt_nomPeliculas.setText(add2);
                String add3 = rs.getString("descripcion");
                txt_descripccionPeliculas.setText(add3);
                String add4 = rs.getString("anio_lanzamiento");
                txt_anioInicioPeliculas.setText(add4);
                int add5 = Integer.parseInt(rs.getString("id_genero"));
                cmb_generoPeliculas.setSelectedIndex(add5);
                String add6 = rs.getString("duracion");
                txt_duracion.setText(add6);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        scrollPane1 = new JScrollPane();
        tablaPeliculas = new JTable();
        scrollPane2 = new JScrollPane();
        txt_descripccionPeliculas = new JTextArea();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        txt_nomPeliculas = new JTextField();
        txt_idPeliculas = new JTextField();
        txt_anioInicioPeliculas = new JTextField();
        label6 = new JLabel();
        cmb_generoPeliculas = new JComboBox();
        btn_guardar = new JButton();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();
        label7 = new JLabel();
        txt_duracion = new JTextField();

        //======== this ========
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- tablaPeliculas ----
            tablaPeliculas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tablaPeliculasMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tablaPeliculas);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(txt_descripccionPeliculas);
        }

        //---- label1 ----
        label1.setText("Registro Peliculas");

        //---- label2 ----
        label2.setText("ID:");

        //---- label3 ----
        label3.setText("Nombre de la pelicula:");

        //---- label4 ----
        label4.setText("Descripci\u00f3n:");

        //---- label5 ----
        label5.setText("A\u00f1o de lanzamiento:");

        //---- label6 ----
        label6.setText("G\u00e9nero:");

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

        //---- label7 ----
        label7.setText("Duraci\u00f3n:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(136, 136, 136)
                            .addComponent(btn_guardar)
                            .addGap(66, 66, 66)
                            .addComponent(btn_modificar)
                            .addGap(77, 77, 77)
                            .addComponent(btn_eliminar))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label3)
                                        .addComponent(label2)
                                        .addComponent(label4)
                                        .addComponent(label5))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_nomPeliculas)
                                        .addComponent(txt_idPeliculas)
                                        .addComponent(scrollPane2)
                                        .addComponent(txt_anioInicioPeliculas, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label6)
                                        .addComponent(label7))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmb_generoPeliculas, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                        .addComponent(txt_duracion, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(265, 265, 265)
                            .addComponent(label1)))
                    .addContainerGap(28, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(label1)
                    .addGap(54, 54, 54)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_idPeliculas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6)
                        .addComponent(cmb_generoPeliculas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txt_nomPeliculas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label7)
                        .addComponent(txt_duracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label4)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(17, 17, 17)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(txt_anioInicioPeliculas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_modificar)
                        .addComponent(btn_eliminar)
                        .addComponent(btn_guardar))
                    .addGap(36, 36, 36)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JScrollPane scrollPane1;
    private JTable tablaPeliculas;
    private JScrollPane scrollPane2;
    private JTextArea txt_descripccionPeliculas;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField txt_nomPeliculas;
    private JTextField txt_idPeliculas;
    private JTextField txt_anioInicioPeliculas;
    private JLabel label6;
    private JComboBox cmb_generoPeliculas;
    private JButton btn_guardar;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    private JLabel label7;
    private JTextField txt_duracion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
