package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mantenimiento_Sucursales {
    private static final Logger LOGGER = Logger.getLogger(Mantenimiento_Sucursales.class.getName());
    private Connection cn;
    private Integer id_sucursales;
    private String nombre;
    private Integer telefono;
    private String direccion;
    private Integer id_usuario;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Integer getId_sucursales() {
        return id_sucursales;
    }

    public void setId_sucursales(Integer id_sucursales) {
        this.id_sucursales = id_sucursales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
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

    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Mantenimiento_Sucursales() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int obtenerUltimoID() {
        int ultimoID = -1;
        try {
            String sql = "SELECT MAX(id_sucursales) FROM sucursales";
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
            String sql = "INSERT INTO sucursales (id_sucursales, nombre, telefono, direccion, id_usuario) VALUES(?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setString(2, nombre);
            cmd.setDouble(3, telefono);
            cmd.setString(4,direccion);
            cmd.setInt(5,id_usuario);

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
            String sql = "UPDATE sucursales SET nombre = ?, telefono = ?, direccion = ?, id_usuario = ? WHERE id_sucursales = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(2, nombre);
            cmd.setDouble(3, telefono);
            cmd.setString(4,direccion);
            cmd.setInt(5,id_usuario);
            cmd.setInt(3, id_sucursales);


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
            String sql = "DELETE FROM sucursales WHERE id_sucursales = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id_sucursales);

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
            ps = cn.prepareStatement("SELECT s.id_sucursales, s.nombre, s.telefono, s.direccion, u.nombre\n" +
                                        "FROM sucursales s\n" +
                                        "INNER JOIN usuario u ON s.id_usuario = u.id_usuario;");
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
