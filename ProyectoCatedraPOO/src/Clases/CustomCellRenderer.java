package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import java.util.ArrayList;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    Mantenimiento_boletos mantenimientoBoletos;

    public CustomCellRenderer(Mantenimiento_boletos mantenimientoBoletosa) {
        this.mantenimientoBoletos =mantenimientoBoletosa;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Aquí puedes aplicar tus condiciones y establecer los colores de fondo y texto según las mismas
        // Por ejemplo, si quieres cambiar el color de fondo de las celdas en la columna 2 (C3), puedes hacerlo así:
        if (mantenimientoBoletos.buscarAsientoPorNumero(value.toString()).getAsientoComprado() == 1) {
            cell.setBackground(Color.RED);
            cell.setForeground(Color.WHITE);
        } else {
            if (hasFocus) {
                // Cambia el color de fondo cuando la celda está seleccionada
                cell.setBackground(Color.ORANGE);
                cell.setForeground(Color.BLACK);
            } else if (mantenimientoBoletos.buscarAsientoSeleccionadoPorNumero(value.toString())) {
                cell.setBackground(Color.cyan);
                cell.setForeground(Color.BLACK);
            }
                else {
                // Establece los colores predeterminados para otras celdas
                cell.setBackground(table.getBackground());
                cell.setForeground(table.getForeground());
            }

        }

        return cell;
    }

}

