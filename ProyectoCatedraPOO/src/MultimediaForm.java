import java.awt.event.*;
import Clases.LlenarLista_multimedia;
import Clases.LlenarLista_pelicula;
import Clases.Mantenimiento_Multimedia;
import Clases.Mantenimiento_Pelicula;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Thu Sep 14 23:32:19 CST 2023
 */



/**
 * @author jonat
 */
public class MultimediaForm extends JDialog {
    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Multimedia ver = new Mantenimiento_Multimedia();
    LlenarLista_multimedia con = new LlenarLista_multimedia();
    private JSpinner timeSpinner;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public MultimediaForm(Window owner) {
        super(owner);
        initComponents();

        SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setCalendarField(Calendar.MINUTE);

        // Crear un JSpinner con el modelo de fecha
        timeSpinner = new JSpinner(spinnerDateModel);

        // Configurar el editor del JSpinner para mostrar la hora y los minutos
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

        // Añadir el JSpinner al formulario
        timeSpinner.setBounds(132, 200, 74, 31); // Ajuste las coordenadas y el tamaño según sea necesario
        add(timeSpinner);


        this.cmb_pelicula.setModel(con.obt_pelicula());
        this.cmb_formato.setModel(con.obt_formato());
        this.cmb_sala.setModel(con.obt_sala());

        modelo.addColumn("ID");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Finalizacion");
        modelo.addColumn("Sala emision");
        modelo.addColumn("Pelicula");
        modelo.addColumn("Formato");

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
    public int obtenerDuracionPelicula(String nombrePelicula) {
        int duracion = 0;

        try {
            String query = "SELECT duracion FROM peliculas WHERE nombre_pelicula = ?";
            PreparedStatement stmt = cn.conectar().prepareStatement(query);
            stmt.setString(1, nombrePelicula);  // Aquí está la corrección

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                duracion = rs.getInt("duracion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener la duración de la película: " + e.getMessage());
        }

        return duracion;
    }


    private void btn_guardarMouseClicked(MouseEvent e) {
        if (txt_fechaEmision.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }
        String fechaTexto = txt_fechaEmision.getText();
        java.util.Date fecha = null;
        try {
            fecha = formatoFecha.parse(fechaTexto);
        } catch (ParseException e1){
            e1.printStackTrace();
        }

        java.util.Date selectedTime = (java.util.Date) this.timeSpinner.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedTime);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour < 10 || hour > 18 || minute > 59) {
            JOptionPane.showMessageDialog(this, "La hora debe estar entre las 10:00 y las 18:59.");
            return;
        }

        Calendar calendarioFinal = Calendar.getInstance();
        calendarioFinal.setTime(fecha);
        calendarioFinal.set(Calendar.HOUR_OF_DAY, hour);
        calendarioFinal.set(Calendar.MINUTE, minute);

        java.util.Date fechaInicio = calendarioFinal.getTime();

        int seleccionPelicula = cmb_pelicula.getSelectedIndex();

        if (seleccionPelicula == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una pelicula");
            return;
        }
            int duracion = obtenerDuracionPelicula(cmb_pelicula.getSelectedItem().toString());

            calendarioFinal.setTime(fecha);
            calendarioFinal.set(Calendar.HOUR_OF_DAY, hour);
            calendarioFinal.set(Calendar.MINUTE, minute);
            calendarioFinal.add(Calendar.MINUTE, duracion);
            java.util.Date fechaFin = calendarioFinal.getTime();


            Clases.Mantenimiento_Multimedia obj = new Clases.Mantenimiento_Multimedia();
            obj.setHora_inicio(fechaInicio);
            obj.setHora_fin(fechaFin);
            obj.setCodigo_pelicula(cmb_pelicula.getSelectedIndex());
            obj.setCodigo_formato(cmb_formato.getSelectedIndex());
            obj.setCodigo_sala(cmb_sala.getSelectedIndex());

            try {
                if (obj.guardarPelicula()) {
                    limpiartabla();

                    txt_fechaEmision.setText("");
                    cmb_sala.setSelectedIndex(0);
                    cmb_formato.setSelectedIndex(0);
                    cmb_pelicula.setSelectedIndex(0);

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
        if (txt_id.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Campos vacíos");
        }
        else {
            Clases.Mantenimiento_Multimedia obj = new Clases.Mantenimiento_Multimedia();
            obj.setCodigo(Integer.parseInt(txt_id.getText()));


            if(obj.modificarPelicula()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tabla.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al modificar datos: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar datos");
            }
        }
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        if (txt_id.getText().equals("") || txt_fechaEmision.getText().equals("") || cmb_sala.getSelectedItem().toString().equals("") || cmb_pelicula.getSelectedItem().toString().equals("") || cmb_formato.getSelectedItem().toString().equals("")) {

            JOptionPane.showMessageDialog(this, "Campos vacíos");
            return;
        }
        String fechaTexto = txt_fechaEmision.getText();
        java.util.Date fecha = null;
        try {
            fecha = formatoFecha.parse(fechaTexto);
        } catch (ParseException e1){
            e1.printStackTrace();
        }

        java.util.Date selectedTime = (java.util.Date) this.timeSpinner.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedTime);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour < 10 || hour > 18 || minute > 59) {
            JOptionPane.showMessageDialog(this, "La hora debe estar entre las 10:00 y las 18:59.");
            return;
        }

        Calendar calendarioFinal = Calendar.getInstance();
        calendarioFinal.setTime(fecha);
        calendarioFinal.set(Calendar.HOUR_OF_DAY, hour);
        calendarioFinal.set(Calendar.MINUTE, minute);

        java.util.Date fechaInicio = calendarioFinal.getTime();

        int seleccionPelicula = cmb_pelicula.getSelectedIndex();

        if (seleccionPelicula == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una pelicula");
            return;
        }
        int duracion = obtenerDuracionPelicula(cmb_pelicula.getSelectedItem().toString()) + 30;

        calendarioFinal.setTime(fecha);
        calendarioFinal.set(Calendar.HOUR_OF_DAY, hour);
        calendarioFinal.set(Calendar.MINUTE, minute);
        calendarioFinal.add(Calendar.MINUTE, duracion);
        java.util.Date fechaFin = calendarioFinal.getTime();

            Clases.Mantenimiento_Multimedia obj = new Clases.Mantenimiento_Multimedia();
            obj.setCodigo(Integer.parseInt(txt_id.getText()));
            obj.setHora_inicio(fechaInicio);
            obj.setHora_fin(fechaFin);
            obj.setCodigo_pelicula(cmb_pelicula.getSelectedIndex());
            obj.setCodigo_formato(cmb_formato.getSelectedIndex());
            obj.setCodigo_sala(cmb_sala.getSelectedIndex());

            if(obj.modificarPelicula()){
                try {
                    limpiartabla();
                    ver.llenartabla(tabla);

                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    this.tabla.setModel(modelo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al modificar datos: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar datos");
            }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        txt_id = new JTextField();
        cmb_sala = new JComboBox();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        cmb_formato = new JComboBox();
        cmb_pelicula = new JComboBox();
        label7 = new JLabel();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();
        txt_fechaEmision = new JTextField();
        btn_guardar = new JButton();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Registro  Funciones");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID");

        //---- label2 ----
        label2.setText("Fecha Emision:");

        //---- txt_id ----
        txt_id.setEnabled(false);

        //---- label4 ----
        label4.setText("Sala:");

        //---- label5 ----
        label5.setText("Pelicula:");

        //---- label6 ----
        label6.setText("Formato:");

        //---- label7 ----
        label7.setText("Hora:");

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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(187, 187, 187)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label2)
                                        .addComponent(label1))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_id, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(txt_fechaEmision, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                    .addGap(55, 55, 55)
                                    .addComponent(label4))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(317, 317, 317)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label5)
                                                .addComponent(label6)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label7)
                                            .addGap(68, 68, 68)
                                            .addComponent(btn_guardar)
                                            .addGap(54, 54, 54)
                                            .addComponent(btn_eliminar)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cmb_sala, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmb_formato, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addComponent(btn_modificar)))))
                            .addGap(12, 12, 12)))
                    .addContainerGap(57, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(label3)
                    .addGap(50, 50, 50)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5)
                        .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(16, 16, 16)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txt_fechaEmision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6)
                        .addComponent(cmb_formato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(cmb_sala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label7))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar)
                        .addComponent(btn_modificar)
                        .addComponent(btn_eliminar))
                    .addGap(7, 7, 7)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16))
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
    private JComboBox cmb_sala;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JComboBox cmb_formato;
    private JComboBox cmb_pelicula;
    private JLabel label7;
    private JScrollPane scrollPane1;
    private JTable tabla;
    private JTextField txt_fechaEmision;
    private JButton btn_guardar;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
