package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    public static Connection conectar() {
        Connection con = null;
        try {
            // Cargar y registrar el controlador JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Especificar detalles de la conexión
            String url = "jdbc:sqlserver://DESKTOP-QQQHNB3\\SQLEXPRESS;databaseName=PrimeCinema3;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            // Establecer conexión
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de controlador: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

//    public static void main(String[] args) {
//        Connection con = conectar();
//        if (con != null) {
//            System.out.println("Conexión establecida exitosamente");
//        } else {
//            System.out.println("Falló la conexión");
//        }
//    }
}
