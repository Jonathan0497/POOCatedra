import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Clases.*;
import javax.swing.table.DefaultTableCellRenderer;
import net.miginfocom.swing.MigLayout;

/*
 * Created by JFormDesigner on Sun Sep 17 17:34:51 BST 2023
 */



/**
 * @author Gaming
 */
public class Seleccion_boletos extends JFrame {

    Mantenimiento_boletos mantenimientoBoletos;

    DefaultTableModel modeloAsientos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Seleccion_boletos(Integer codigo_multimedia) {
        this.mantenimientoBoletos = new Mantenimiento_boletos(codigo_multimedia);

        initComponents();
        iniciarTabla();
        // Asigna el renderizador personalizado, inyectando la clase 'mantenimientoBoletos'
        tblAsientos.setDefaultRenderer(Object.class, new CustomCellRenderer(mantenimientoBoletos));
    }

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
            lblAsiento.setText(""+mantenimientoBoletos.obtenerNumerosDeAsiento());
        }
    }



    private void btn_limpiarSeleccionMouseClicked(MouseEvent e) {
        mantenimientoBoletos.limpiarAsientoSeleccionados(); // Vacía la lista
        lblAsiento.setText("");
        // Notifica a la tabla que debe refrescar su vista
        tblAsientos.repaint();
    }

    private void btnHabilitarButacasMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    public static void main(String[] args) {
        Seleccion_boletos pantallaActual = new Seleccion_boletos(1);
        pantallaActual.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        lblSeleccion_asientos = new JLabel();
        btnHabilitarButacas = new JButton();
        label1 = new JLabel();
        lblAsiento = new JLabel();
        btn_limpiarSeleccion = new JButton();
        scrollPane1 = new JScrollPane();
        tblAsientos = new JTable();
        btnCacelar = new JButton();
        btnFinalizar = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[281,fill]" +
            "[294,fill]" +
            "[fill]" +
            "[647,right]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[123]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- lblSeleccion_asientos ----
        lblSeleccion_asientos.setText("Seleccion de asientos");
        contentPane.add(lblSeleccion_asientos, "cell 1 1");

        //---- btnHabilitarButacas ----
        btnHabilitarButacas.setText("Habilitar todas las butacas");
        btnHabilitarButacas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnHabilitarButacasMouseClicked(e);
            }
        });
        contentPane.add(btnHabilitarButacas, "cell 3 7");

        //---- label1 ----
        label1.setText("Asientos seleccionados:");
        contentPane.add(label1, "cell 0 9");
        contentPane.add(lblAsiento, "cell 3 9");

        //---- btn_limpiarSeleccion ----
        btn_limpiarSeleccion.setText("Limpiar seleccion");
        btn_limpiarSeleccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_limpiarSeleccionMouseClicked(e);
            }
        });
        contentPane.add(btn_limpiarSeleccion, "cell 3 9");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblAsientos);
        }
        contentPane.add(scrollPane1, "cell 0 10 4 1");

        //---- btnCacelar ----
        btnCacelar.setText("Cancelar");
        contentPane.add(btnCacelar, "cell 3 13");

        //---- btnFinalizar ----
        btnFinalizar.setText("Finalizar");
        contentPane.add(btnFinalizar, "cell 3 13");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel lblSeleccion_asientos;
    private JButton btnHabilitarButacas;
    private JLabel label1;
    private JLabel lblAsiento;
    private JButton btn_limpiarSeleccion;
    private JScrollPane scrollPane1;
    private JTable tblAsientos;
    private JButton btnCacelar;
    private JButton btnFinalizar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
