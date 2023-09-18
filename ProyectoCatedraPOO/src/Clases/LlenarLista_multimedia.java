package Clases;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Conexion.conectar;

public class LlenarLista_multimedia {

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

    public DefaultComboBoxModel obt_formato(){

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

    public DefaultComboBoxModel obt_sala(){

        DefaultComboBoxModel jComboBox1 = new DefaultComboBoxModel();
        jComboBox1.addElement("Seleccione una sala");
        ResultSet rs= this.consulta("SELECT CAST(s.numero_sala AS VARCHAR) + ';' + su.nombre AS sala_info FROM salas s INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales;");


        try{
            while(rs.next()){
                jComboBox1.addElement(rs.getString("sala_info"));
            }
            rs.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return jComboBox1;
    }


}
