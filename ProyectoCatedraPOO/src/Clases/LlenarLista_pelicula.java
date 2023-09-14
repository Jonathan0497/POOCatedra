package Clases;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Conexion.conectar;

public class LlenarLista_pelicula {
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

    public DefaultComboBoxModel obt_generoPelicula(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una genero");
        ResultSet rs= this.consulta("select * from genero");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("genero"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }

    public DefaultComboBoxModel obt_clasificacionPelicula(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una clasificación");
        ResultSet rs= this.consulta("select * from clasificacion");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("clasificacion"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }

    public DefaultComboBoxModel obt_formatoPelicula(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione un formato");
        ResultSet rs= this.consulta("select * from formato");

        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("formato"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }
}
