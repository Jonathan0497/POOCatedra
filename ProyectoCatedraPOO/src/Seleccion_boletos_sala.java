import Clases.Asiento;
import Clases.CustomCellRenderer;
import Clases.Mantenimiento_boletos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Mon Sep 18 14:01:54 CST 2023
 */


/**
 * @author jeffersonsolorzano
 */
public class Seleccion_boletos_sala extends JDialog {

    Mantenimiento_boletos mantenimientoBoletos;

    DefaultTableModel modeloAsientos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    private void iniciarTabla(){
        modeloAsientos.addColumn("C1");
        modeloAsientos.addColumn("C2");
        modeloAsientos.addColumn("C3");
        modeloAsientos.addColumn("C4");
        modeloAsientos.addColumn("C5");
        this.tblAsientos.setModel(modeloAsientos);

        try {
            mantenimientoBoletos.llenartabla(tblAsientos);
            tblAsientos.setCellSelectionEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al llenar la tabla: " + e.getMessage());
        }
    }

    private void scrollPane1MouseClicked(MouseEvent e) {

    }

    private void tblAsientosMouseClicked(MouseEvent e) {
        System.out.println("executing");
        int filaSeleccionada = tblAsientos.getSelectedRow();
        int columnaSeleccionada = tblAsientos.getSelectedColumn();

        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
            // Obtener el valor de la celda seleccionada como objeto Asiento
            Object asientoSeleccionado = tblAsientos.getValueAt(filaSeleccionada, columnaSeleccionada);
            Asiento asiento_Seleccionado = mantenimientoBoletos.buscarAsientoPorNumero(asientoSeleccionado.toString());

            if (!mantenimientoBoletos.devolverAsientosSeleccionados().contains(asiento_Seleccionado) && asiento_Seleccionado.getAsientoComprado() != 1) {
                // Si el asiento no está en la lista, agregarlo
                mantenimientoBoletos.agregarButacaSeleccionada(asiento_Seleccionado);
            } else {
                // Si el asiento ya está en la lista, quitarlo
                mantenimientoBoletos.removerAsientoSeleccionado(asiento_Seleccionado);
            }
            // Actualizar lblAsiento con los valores de las butacas seleccionadas
            lblAsientoSeleccionado.setText(""+mantenimientoBoletos.obtenerNumerosDeAsiento());
        }
    }

    private void btn_limpiarSeleccionMouseClicked(MouseEvent e) {
        mantenimientoBoletos.limpiarAsientoSeleccionados(); // Vacía la lista
        lblAsiento.setText("");
        // Notifica a la tabla que debe refrescar su vista
        tblAsientos.repaint();
        lblAsientoSeleccionado.setText("");
    }

    private void btnHabilitarButacasMouseClicked(MouseEvent e) {
        try {
            mantenimientoBoletos.vaciarSala();
            ProcesoExitoso procesoExitoso= new ProcesoExitoso();
            procesoExitoso.setVisible(true);
            dispose();

        } catch (Exception err) {
        err.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro al vaciar sala " + err.getMessage());
    }
    }

    private void btnFinalizarMouseClicked(MouseEvent e) {
        try {
            mantenimientoBoletos.comprarBoletos();
            Factura factura = new Factura(
                    mantenimientoBoletos.obtenerNumerosDeAsiento(),
                    mantenimientoBoletos.obtenerTotal(),
                    mantenimientoBoletos.obtenerVuelto(Double.parseDouble(txt_pago.getText()) ));
            factura.setVisible(true);
            dispose();

        } catch (Exception err) {
            err.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al llenar la tabla: " + err.getMessage());
    }
    }

    public Seleccion_boletos_sala( Integer codigo_multimedia, String formato_pelicula, String edad) {

        this.mantenimientoBoletos = new Mantenimiento_boletos(codigo_multimedia,validarPrecioPelicula(formato_pelicula,edad));
        initComponents();
        iniciarTabla();
        // Asigna el renderizador personalizado, inyectando la clase 'mantenimientoBoletos'
        tblAsientos.setDefaultRenderer(Object.class, new CustomCellRenderer(mantenimientoBoletos));
    }

    public Double validarPrecioPelicula (String formato_pelicula, String edad){
        switch(formato_pelicula){
            case "2D":
                switch (edad){
                    case "3RA":
                        return 3.90;
                    case "ADULTO":
                        return 5.0;
                }
                break;
            case "3D":
                switch (edad){
                    case "3RA":
                        return 5.60;
                    case "ADULTO":
                        return  6.55;
                }
                break;
        }
        return 0.0;
    }

    public Seleccion_boletos_sala() {
    }

    public static void main(String[] args) {
        Seleccion_boletos_sala pantallaActual = new Seleccion_boletos_sala(1,"3D","3RA");
        pantallaActual.setVisible(true);
    }

    private void btnCacelarMouseClicked(MouseEvent e) {
        InicioForm inicioForm = new InicioForm();
        inicioForm.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        lblSeleccion_asientos = new JLabel();
        btnHabilitarButacas = new JButton();
        label1 = new JLabel();
        btn_limpiarSeleccion = new JButton();
        scrollPane1 = new JScrollPane();
        tblAsientos = new JTable();
        btnCacelar = new JButton();
        btnFinalizar = new JButton();
        lblAsiento = new JLabel();
        lblAsientoSeleccionado = new JLabel();
        txt_pago = new JTextField();
        label2 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- lblSeleccion_asientos ----
        lblSeleccion_asientos.setText("Seleccion de asientos");

        //---- btnHabilitarButacas ----
        btnHabilitarButacas.setText("Habilitar todas las butacas");
        btnHabilitarButacas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnHabilitarButacasMouseClicked(e);
                btnHabilitarButacasMouseClicked(e);
            }
        });

        //---- label1 ----
        label1.setText("Asientos seleccionados:");

        //---- btn_limpiarSeleccion ----
        btn_limpiarSeleccion.setText("Limpiar seleccion");
        btn_limpiarSeleccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_limpiarSeleccionMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tblAsientos ----
            tblAsientos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblAsientosMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblAsientos);
        }

        //---- btnCacelar ----
        btnCacelar.setText("Cancelar");
        btnCacelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnCacelarMouseClicked(e);
            }
        });

        //---- btnFinalizar ----
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnFinalizarMouseClicked(e);
            }
        });

        //---- lblAsiento ----
        lblAsiento.setText("[]");

        //---- label2 ----
        label2.setText("Billete con el que pagara:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(270, 270, 270)
                    .addComponent(lblSeleccion_asientos)
                    .addContainerGap(273, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(lblAsientoSeleccionado)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblAsiento, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHabilitarButacas))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 2, Short.MAX_VALUE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label2)
                            .addGap(18, 18, 18)
                            .addComponent(txt_pago, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                            .addComponent(btn_limpiarSeleccion)))
                    .addGap(26, 26, 26))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(492, Short.MAX_VALUE)
                    .addComponent(btnCacelar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnFinalizar)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(lblSeleccion_asientos)
                    .addGap(50, 50, 50)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHabilitarButacas)
                        .addComponent(label1)
                        .addComponent(lblAsiento)
                        .addComponent(lblAsientoSeleccionado))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_limpiarSeleccion)
                        .addComponent(label2)
                        .addComponent(txt_pago, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFinalizar)
                        .addComponent(btnCacelar))
                    .addGap(15, 15, 15))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel lblSeleccion_asientos;
    private JButton btnHabilitarButacas;
    private JLabel label1;
    private JButton btn_limpiarSeleccion;
    private JScrollPane scrollPane1;
    private JTable tblAsientos;
    private JButton btnCacelar;
    private JButton btnFinalizar;
    private JLabel lblAsiento;
    private JLabel lblAsientoSeleccionado;
    private JTextField txt_pago;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
