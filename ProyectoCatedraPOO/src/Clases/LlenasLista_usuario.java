package Clases;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Conexion.conectar;

public class LlenasLista_usuario {
    public ResultSet consulta(String sql){

        Connection cn = conectar();
        ResultSet rs = null;
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch(Exception e){
            System.err.println("Error de consulta"+e.getMessage());
        }
        return rs;
    }

    public DefaultComboBoxModel obt_tipoUsuario(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione un rango");
        ResultSet rs= this.consulta("select * from tipo_usuario");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("tipoUsuario"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }
}
