package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Mantenimiento_Usuario {
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    private Connection cn;
    private Integer codigo;
    private String nombre;
    private String apellido;
    private String dui;
    private String contra;
    private String correo;
    private Integer telefono;
    private Integer estado;
    private Integer tipo;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Mantenimiento_Usuario() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int obtenerUltimoID() {
        int ultimoID = -1;
        try {
            String sql = "SELECT MAX(id_usuario) FROM usuario";
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

    public boolean guardarProyecto(){
        boolean resp = false;
        try{
            int newID = obtenerUltimoID() + 1;
            int id_estado = 1;

            String sql = "INSERT INTO usuario (id_usuario, nombre, apellido, clave, dui, correo, telefono, id_estadoUsuario, id_tipoUsuario)"
                    + "VALUES(?, ?, ? , ?, ?, ?, ?, ?, ?)";

            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setString(2, nombre);
            cmd.setString(3, apellido);
            cmd.setString(4, contra);
            cmd.setString(5, dui);
            cmd.setString(6, correo);
            cmd.setInt(7, telefono);
            cmd.setInt(8, id_estado);
            cmd.setInt(9, tipo);

            if(!cmd.execute()){
                resp = true;
            }

            cmd.close();
            cn.close(); // Cerrar la conexión (ahora es seguro hacerlo porque obtendrás una nueva conexión la próxima vez)
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean modificarProyecto(){
        boolean resp = false;
        try {
            String sql = "UPDATE usuario SET nombre = ?, apellido = ?, dui = ?, correo = ?, telefono = ?, id_tipoUsuario = ? WHERE id_usuario = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, nombre);
            cmd.setString(2, apellido);
            cmd.setString(3, dui);
            cmd.setString(4, correo);
            cmd.setInt(5, telefono);
            cmd.setInt(6, tipo);
            cmd.setInt(7, codigo);

            if(!cmd.execute()){
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return resp;
    }

    public boolean eliminarProyecto(){
        boolean resp = false;
        try{
            String sql = "UPDATE usuario SET id_estadoUsuario = 2 WHERE id_usuario = ?";
            PreparedStatement cmd = cn.prepareStatement (sql);

            cmd.setInt(1, codigo);

            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            cn.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return resp;
    }

    public void llenartabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT u.id_usuario, u.nombre, u.apellido, u.correo, u.telefono, tu.tipoUsuario FROM usuario u INNER JOIN tipo_usuario tu ON u.id_tipoUsuario = tu.id_tipoUsuario;");
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
