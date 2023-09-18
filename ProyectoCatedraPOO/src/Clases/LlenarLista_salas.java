package Clases;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Conexion.conectar;

public class LlenarLista_salas {
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

    public DefaultComboBoxModel obt_sucursal(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una sucursal");
        ResultSet rs= this.consulta("select * from sucursales");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("nombre"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }
}
