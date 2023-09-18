package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mantenimiento_Formato {
    private static final Logger LOGGER = Logger.getLogger(Mantenimiento_Formato.class.getName());

    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Integer getId_formato() {
        return id_formato;
    }

    public void setId_formato(Integer id_formato) {
        this.id_formato = id_formato;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    private Integer id_formato;
    private String formato;
    private double precio;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Mantenimiento_Formato() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    public int obtenerUltimoID() {
        int ultimoID = -1;
        try {
            String sql = "SELECT MAX(id_formato) FROM formato";
            PreparedStatement cmd = cn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {
                ultimoID = rs.getInt(1);
            }

            rs.close();
            cmd.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ultimoID;
    }

    public boolean guardarFormato() {
        boolean resp = false;
        try {

            int newID = obtenerUltimoID() + 1;
            String sql = "INSERT INTO formato (id_formato, formato, precio) VALUES(?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setString(2, formato);
            cmd.setDouble(3, precio);

            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al guardar el formato", e);
        }
        return resp;
    }

    public boolean modificarFormato() {
        boolean resp = false;
        try {
            String sql = "UPDATE formato SET formato = ?, precio = ? WHERE id_formato = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, formato);
            cmd.setDouble(2, precio);
            cmd.setInt(3, id_formato);


            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar el formato", e);
        }
        return resp;
    }

    public boolean eliminarFormato() {
        boolean resp = false;
        try {
            String sql = "DELETE FROM formato WHERE id_formato = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id_formato);

            if (!cmd.execute()) {
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar el formato", e);
        }
        return resp;
    }
    public void llenarTabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT * FROM formato");
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
            LOGGER.log(Level.SEVERE, "Error al llenar la tabla", e);
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

