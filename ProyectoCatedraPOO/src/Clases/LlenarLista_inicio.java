package Clases;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Conexion.conectar;

public class LlenarLista_inicio {
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

    public DefaultComboBoxModel obt_pelicula(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una pelicula");
        ResultSet rs= this.consulta("select * from peliculas");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("nombre_pelicula"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }

    public DefaultComboBoxModel obt_sucursal(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una sucursales");
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
