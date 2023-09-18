package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Mantenimiento_boletos {

    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Integer getCodigo_multimedia() {
        return codigo_multimedia;
    }

    public void setCodigo_multimedia(Integer codigo_multimedia) {
        this.codigo_multimedia = codigo_multimedia;
    }

    private Integer codigo_multimedia;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    ArrayList<Asiento> asientos_ui_tablas = new ArrayList<>(8);
    ArrayList<Asiento> asientos_almacenados = new ArrayList<>();
    private ArrayList<Asiento> butacasSeleccionadas = new ArrayList<>();


    public Mantenimiento_boletos(Integer codigo_multimedia) {
        this.codigo_multimedia = codigo_multimedia;

        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public void agregarButacaSeleccionada(Asiento asiento){
        butacasSeleccionadas.add(asiento);
    }


    public  void limpiarAsientoSeleccionados(){
        butacasSeleccionadas.clear();
    }
    public void removerAsientoSeleccionado(Asiento asiento){
        butacasSeleccionadas.remove(asiento);
    }

    public  ArrayList<Asiento> devolverAsientosSeleccionados(){
        return butacasSeleccionadas;
    }

    public ArrayList<String> obtenerNumerosDeAsiento() {
        ArrayList<String> numerosDeAsiento = new ArrayList<>();

        for (Asiento asiento : devolverAsientosSeleccionados()) {
            String numeroAsiento = asiento.getNumero_asiento();
            numerosDeAsiento.add(numeroAsiento);
        }

        return numerosDeAsiento;
    }

    public Asiento buscarAsientoPorNumero( String numeroAsientoBuscado) {

        for (Asiento asiento : asientos_almacenados) {
            if (asiento.getNumero_asiento().equals(numeroAsientoBuscado)) {
                return asiento; // Devolver el asiento encontrado
            }
        }
        return null; // Devolver null si no se encontró ningún asiento con ese número
    }

    public boolean buscarAsientoSeleccionadoPorNumero( String numeroAsientoBuscado) {

        for (Asiento asiento : butacasSeleccionadas) {
            if (asiento.getNumero_asiento().equals(numeroAsientoBuscado)) {
                return true; // Devolver el asiento encontrado
            }
        }
        return false;
    }

    public ArrayList<Asiento> llenartabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT \n" +
                    "    m.id_multimedia,\n" +
                    "    a.id_asiento,\n" +
                    "    a.numero_asiento,\n" +
                    "    CASE \n" +
                    "        WHEN t.id_asiento IS NOT NULL THEN 1\n" +
                    "        ELSE 0\n" +
                    "    END AS AsientoComprado\n" +
                    "FROM \n" +
                    "    multimedia m\n" +
                    "LEFT JOIN \n" +
                    "    salas s ON m.id_salas = s.id_salas\n" +
                    "LEFT JOIN \n" +
                    "    asientos a ON s.id_salas = a.id_salas\n" +
                    "LEFT JOIN \n" +
                    "    ticket t ON a.id_asiento = t.id_asiento AND m.id_multimedia = t.id_multimedia\n" +
                    "where m.id_multimedia = ?");
            ps.setInt(1, codigo_multimedia);

            rs = ps.executeQuery();
            rsm = rs.getMetaData();


            // Limpiar la tabla antes de llenarla
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            dtm.setRowCount(0);

            int columnCount = 0;

            while (rs.next()) {
                int id_asiento = rs.getInt("id_asiento");
                String numero_asiento = rs.getString("numero_asiento");
                int asientoComprado = rs.getInt("AsientoComprado");

                Asiento asiento = new Asiento(id_asiento, numero_asiento, asientoComprado);
                asientos_ui_tablas.add(asiento);
                asientos_almacenados.add(asiento);

                if (asientos_ui_tablas.size() == 8) {
                    // Agregar un conjunto completo de 8 registros a la tabla
                    Object[] rowData = new Object[8];
                    for (int i = 0; i < 8; i++) {
                        rowData[i] = asientos_ui_tablas.get(i).getNumero_asiento();
                    }
                    dtm.addRow(rowData);
                    asientos_ui_tablas.clear();
                    columnCount++;

                    if (columnCount >= 5) {
                        // Si llegamos a 5 columnas, salir del bucle
                        break;
                    }
                }
            }

            // Agregar cualquier conjunto incompleto de registros restantes
            if (!asientos_ui_tablas.isEmpty()) {
                Object[] rowData = new Object[asientos_ui_tablas.size()];
                for (int i = 0; i < asientos_ui_tablas.size(); i++) {
                    rowData[i] = asientos_ui_tablas.get(i).getNumero_asiento();
                }
                dtm.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al llenar la tabla: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null && !cn.isClosed()) {
                cn.close();
            }
        }

        return asientos_almacenados;
    }

}
