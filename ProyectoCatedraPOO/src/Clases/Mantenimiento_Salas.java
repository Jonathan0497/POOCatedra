package Clases;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mantenimiento_Salas {
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

    public Integer getId_salas() {
        return id_salas;
    }

    public void setId_salas(Integer id_salas) {
        this.id_salas = id_salas;
    }

    public Integer getNumero_sala() {
        return numero_sala;
    }

    public void setNumero_sala(Integer numero_sala) {
        this.numero_sala = numero_sala;
    }

    public Integer getId_sucursales() {
        return id_sucursales;
    }

    public void setId_sucursales(Integer id_sucursales) {
        this.id_sucursales = id_sucursales;
    }

    private static final Logger LOGGER = Logger.getLogger(Mantenimiento_Salas.class.getName());
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    private DefaultTableModel dtm;
    private Integer id_salas;
    private Integer numero_sala;
    private Integer id_sucursales;

    public Mantenimiento_Salas() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int obtenerUltimoID() {
        int ultimoID = -1;
        try {
            String sql = "SELECT MAX(id_salas) FROM salas";
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

    public boolean guardarSalas() {
        boolean resp = false;
        try {

            int newID = obtenerUltimoID() + 1;
            String sql = "INSERT INTO salas (id_salas, numero_sala, id_sucursales) VALUES(?,?,?)" +
                    "DECLARE @fila CHAR(1), @columna INT, @asiento_num INT, @max_asiento INT;\n" +
                    "\n" +
                    "-- Obtén el número máximo de asientos ya presentes en la sala 3\n" +
                    "SELECT @max_asiento = ISNULL(MAX(id_asiento), 0) FROM asientos WHERE id_salas = 3;\n" +
                    "\n" +
                    "SET @asiento_num = @max_asiento + 1;\n" +
                    "SET @fila = CHAR(65 + (@max_asiento / 5)); -- 65 es el código ASCII para 'A'\n" +
                    "\n" +
                    "WHILE @asiento_num <= (@max_asiento + 40)\n" +
                    "BEGIN\n" +
                    "    SET @columna = (@asiento_num - 1) % 5 + 1;\n" +
                    "\n" +
                    "    IF @asiento_num % 5 = 1 AND @asiento_num != (@max_asiento + 1)\n" +
                    "    BEGIN\n" +
                    "        SET @fila = CHAR(ASCII(@fila) + 1);\n" +
                    "    END\n" +
                    "\n" +
                    "    INSERT INTO asientos (numero_asiento, id_salas)\n" +
                    "    VALUES (@fila + CAST(@columna AS VARCHAR), ?);\n" +
                    "\n" +
                    "    SET @asiento_num = @asiento_num + 1;\n" +
                    "END;\n";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, newID);
            cmd.setInt(2, numero_sala);
            cmd.setInt(3,id_sucursales);
            cmd.setInt(4,newID);

            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la sala", e);
        }
        return resp;
    }

    public boolean modificarSalas() {
        boolean resp = false;
        try {
            String sql = "UPDATE salas SET numero_sala = ?, id_sucursales = ? WHERE id_salas = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, numero_sala);
            cmd.setInt(2, id_sucursales);
            cmd.setInt(3,id_salas);

            if (!cmd.execute()) {
                resp = true;
            }

            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la sala", e);
        }
        return resp;
    }
    public boolean eliminarSalas() {
        boolean resp = false;
        try {
            String sql = "DELETE FROM salas WHERE id_salas = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id_salas);

            if (!cmd.execute()) {
                resp = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la sala", e);
        }
        return resp;
    }

    public void llenarTablaSalas(javax.swing.JTable tabla) throws Exception {
        try {
            Conexion con = new Conexion(); // Crear un nuevo objeto Conexion
            cn = con.conectar(); // Obtener una nueva conexión
            ps = cn.prepareStatement("SELECT s.id_salas, s.numero_sala, su.nombre AS nombre_sucursal\n" +
                                    "FROM salas s\n" +
                                    "INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales;");
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
