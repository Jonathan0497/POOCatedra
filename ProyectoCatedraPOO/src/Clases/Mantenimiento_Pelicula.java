package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Mantenimiento_Pelicula {
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

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getNombre_pelicula() {
        return nombre_pelicula;
    }

    public void setNombre_pelicula(String nombre_pelicula) {
        this.nombre_pelicula = nombre_pelicula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public void setAnio_lanzamiento(String anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public Integer getId_clasificacion() {
        return id_clasificacion;
    }

    public void setId_clasificacion(Integer id_clasificacion) {
        this.id_clasificacion = id_clasificacion;
    }

    public Integer getId_formato() {
        return id_formato;
    }

    public void setId_formato(Integer id_formato) {
        this.id_formato = id_formato;
    }

    public Integer getId_estadoPelicula() {
        return id_estadoPelicula;
    }

    public void setId_estadoPelicula(Integer id_estadoPelicula) {
        this.id_estadoPelicula = id_estadoPelicula;
    }

    private static final Logger LOGGER = Logger.getLogger(Mantenimiento_Sucursales.class.getName());
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;
    private Integer id_pelicula;
    private String nombre_pelicula;
    private String descripcion;
    private String anio_lanzamiento;
    private Integer id_genero;
    private Integer id_clasificacion;
    private Integer id_formato;
    private Integer id_estadoPelicula;
    public Mantenimiento_Pelicula() {
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

    public boolean guardarPelicula() {
        boolean resp = false;
        try {

            int newID = obtenerUltimoID() + 1;
            int id_estadoPeliculas = 1;

            String sql = "INSERT INTO peliculas (id_pelicula, nombre_pelicula, descripcion, anio_lanzamiento, id_genero, id_clasificacion, id_formato, id_estadoPelicula) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setString(2, nombre_pelicula);
            cmd.setString(3, descripcion);
            cmd.setString(4, anio_lanzamiento);
            cmd.setInt(5,id_genero);
            cmd.setInt(6,id_clasificacion);
            cmd.setInt(7,id_formato);
            cmd.setInt(8,id_estadoPeliculas);

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
            String sql = "UPDATE peliculas SET nombre_pelicula = ?, descripcion = ?, anio_lanzamiento = ?, id_genero = ?, id_clasificacion = ?, id_formato = ? WHERE id_pelicula = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nombre_pelicula);
            cmd.setString(2,descripcion);
            cmd.setString(3,anio_lanzamiento);
            cmd.setInt(4, id_genero);
            cmd.setInt(5, id_clasificacion);
            cmd.setInt(6, id_formato);


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
            String sql = "DELETE FROM peliculas WHERE id_pelicula = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id_pelicula);

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

    public void llenarTabla(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT p.id_pelicula, p.nombre_pelicula, p.descripcion, p.anio_lanzamiento, g.genero, c.clasificacion, f.formato\n" +
                                        "FROM peliculas p\n" +
                                        "INNER JOIN genero g ON p.id_genero = g.id_genero\n" +
                                        "INNER JOIN clasificacion c ON p.id_clasificacion = c.id_clasificacion\n" +
                                        "INNER JOIN formato f ON p.id_formato = f.id_formato;");
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
