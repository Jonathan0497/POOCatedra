package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Mantenimiento_EstadoPelicula {
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * @return the ps
     */
    public PreparedStatement getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * @return the rsm
     */
    public ResultSetMetaData getRsm() {
        return rsm;
    }

    /**
     * @param rsm the rsm to set
     */
    public void setRsm(ResultSetMetaData rsm) {
        this.rsm = rsm;
    }

    /**
     * @return the dtm
     */
    public DefaultTableModel getDtm() {
        return dtm;
    }
    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }
    private Connection cn;
    private Integer codigo;
    private String nombre;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Mantenimiento_EstadoPelicula() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean guardarProyecto(){
        boolean resp = false;
        try{
            String sql = "INSERT INTO estado_pelicula (estadoPelicula)"
                        + "VALUES(?)";

            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, nombre);

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
            String sql = "UPDATE estado_pelicula SET estadoPelicula = ? WHERE id_estadoPelicula = ?;";
            PreparedStatement cmd = cn.prepareStatement(sql);

            cmd.setString(1, nombre);
            cmd.setInt(2, codigo);

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
            String sql = "DELETE FROM estado_pelicula WHERE id_estadoPelicula = ?;";
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
            ps = cn.prepareStatement("SELECT * FROM estado_pelicula");
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