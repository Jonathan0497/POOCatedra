package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mantenimiento_Multimedia {
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
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

    public Integer getCodigo_sala() {
        return codigo_sala;
    }

    public void setCodigo_sala(Integer codigo_sala) {
        this.codigo_sala = codigo_sala;
    }

    public Integer getCodigo_pelicula() {
        return codigo_pelicula;
    }

    public void setCodigo_pelicula(Integer codigo_pelicula) {
        this.codigo_pelicula = codigo_pelicula;
    }

    public Integer getCodigo_formato() {
        return codigo_formato;
    }

    public void setCodigo_formato(Integer codigo_formato) {
        this.codigo_formato = codigo_formato;
    }

    private static final Logger LOGGER = Logger.getLogger(Mantenimiento_Multimedia.class.getName());
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    private Integer codigo;

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
    }

    private java.util.Date hora_inicio;
    private java.util.Date hora_fin;
    private Integer codigo_sala;
    private Integer codigo_pelicula;
    private Integer codigo_formato;

    public Mantenimiento_Multimedia(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int obtenerUltimoID() {
        int ultimoID = -1;
        try {
            String sql = "SELECT MAX(id_multimedia) FROM multimedia";
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
    public boolean guardarPelicula() {
        boolean resp = false;
        try {

            int newID = obtenerUltimoID() + 1;

            String sql = "INSERT INTO multimedia (id_multimedia, horaInicio, horaFin, id_salas, id_pelicula, id_formato) VALUES(?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setTimestamp(2, new java.sql.Timestamp(hora_inicio.getTime()));
            cmd.setTimestamp(3, new java.sql.Timestamp(hora_fin.getTime()));
            cmd.setInt(4, codigo_sala);
            cmd.setInt(5,codigo_pelicula);
            cmd.setInt(6,codigo_formato);

            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la pelicula", e);
        }
        return resp;
    }

    public boolean modificarPelicula() {
        boolean resp = false;
        try {
            String sql = "UPDATE multimedia SET horaInicio = ?, horaFin = ?, id_salas = ?, id_pelicula = ?, id_formato = ? WHERE id_multimedia = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setTimestamp(2, new java.sql.Timestamp(hora_inicio.getTime()));
            cmd.setTimestamp(3, new java.sql.Timestamp(hora_fin.getTime()));
            cmd.setInt(3, codigo_sala);
            cmd.setInt(4,codigo_pelicula);
            cmd.setInt(5,codigo_formato);
            cmd.setInt(6, codigo);


            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la pelicula", e);
        }
        return resp;
    }

    public boolean eliminarPelicula() {
        boolean resp = false;
        try {
            String sql = "DELETE FROM multimedia WHERE id_multimedia = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, codigo);

            if (!cmd.execute()) {
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la pelicula", e);
        }
        return resp;
    }

    public void llenartabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT * FROM multimedia");
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
