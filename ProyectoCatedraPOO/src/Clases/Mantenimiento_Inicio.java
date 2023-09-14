package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Mantenimiento_Inicio {
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Integer getCodigo_sucursal() {
        return codigo_sucursal;
    }

    public void setCodigo_sucursal(Integer codigo_sucursal) {
        this.codigo_sucursal = codigo_sucursal;
    }

    public Integer getCodigo_pelicula() {
        return codigo_pelicula;
    }

    public void setCodigo_pelicula(Integer codigo_pelicula) {
        this.codigo_pelicula = codigo_pelicula;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSetMetaData getRsm() {
        return rsm;
    }

    public void setRsm(ResultSetMetaData rsm) {
        this.rsm = rsm;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    private Integer codigo_sucursal;
    private Integer codigo_pelicula;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Mantenimiento_Inicio() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public void llenartabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión

            String consulta = "SELECT m.horaInicio, f.formato, s.numero_sala, p.descripcion AS sinopsis\n" +
                    "FROM multimedia m\n" +
                    "INNER JOIN peliculas p ON m.id_pelicula = p.id_pelicula\n" +
                    "INNER JOIN formato f ON m.id_formato = f.id_formato\n" +
                    "INNER JOIN salas s ON m.id_salas = s.id_salas\n" +
                    "WHERE s.id_sucursales = ?\n" +  // Usamos ? como marcador de posición
                    "AND p.id_pelicula = ?;";  // Usamos ? como marcador de posición

            ps = cn.prepareStatement(consulta);

            // Asignamos los valores de los parámetros
            ps.setInt(2, getCodigo_pelicula());
            ps.setInt(1, getCodigo_sucursal());

            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> datos = new ArrayList<>();
            while (rs.next()) {
                Object[] fila = new Object[rsm.getColumnCount()];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                datos.add(fila);
            }
            dtm = (DefaultTableModel) tabla.getModel();
            for (Object[] fila : datos) {
                dtm.addRow(fila);
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
    }
}
