import Clases.LlenarLista_inicio;
import Clases.LlenarLista_sucursales;
import Clases.Mantenimiento_Inicio;
import Clases.Mantenimiento_Sucursales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Mon Sep 11 08:15:49 CST 2023
 */



/**
 * @author jonat
 */
public class InicioForm extends JFrame {

    Clases.Conexion cn = new Clases.Conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    Mantenimiento_Inicio ver = new Mantenimiento_Inicio();
    LlenarLista_inicio con = new LlenarLista_inicio();
    public InicioForm() {
        initComponents();

        this.cmb_pelicula.setModel(con.obt_pelicula());
        this.cmb_sucursal.setModel(con.obt_sucursal());

        modelo.addColumn("HoraInicio");
        modelo.addColumn("Formato");
        modelo.addColumn("N° sala");
        modelo.addColumn("sinopsis");

        this.tabla.setModel(modelo);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioForm().setVisible(true);
            }
        });
    }

    private void btn_Usuario(ActionEvent e) {
        usuarioForm c = new usuarioForm(this);
        c.setVisible(true);
    }

    private void btn_UsuarioMouseClicked(MouseEvent e) {

    }

    private void btn_genero(ActionEvent e) {
        CategoriaForm c = new CategoriaForm(this);
        c.setVisible(true);
    }

    private void btn_formato(ActionEvent e) {
        Formato c = new Formato(this);
        c.setVisible(true);
    }

    private void btn_funciones(ActionEvent e) {
        // TODO add your code here
    }

    private void btn_buscarMouseClicked(MouseEvent e) {
        int indiceSucursal = cmb_sucursal.getSelectedIndex();
        int indicePelicula = cmb_pelicula.getSelectedIndex();

        if (indiceSucursal == 0 || indicePelicula == 0){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una película y sucursal válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Clases.Mantenimiento_Inicio obj = new Clases.Mantenimiento_Inicio();
                obj.setCodigo_pelicula(cmb_pelicula.getSelectedIndex());
                obj.setCodigo_sucursal(cmb_sucursal.getSelectedIndex());

                ver.llenartabla(tabla);
            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        hSpacer2 = new JPanel(null);
        menu1 = new JMenu();
        btn_genero = new JMenuItem();
        btn_formato = new JMenuItem();
        btn_peliculas = new JMenuItem();
        btn_funciones = new JMenuItem();
        btn_clasificacion = new JMenuItem();
        hSpacer1 = new JPanel(null);
        btn_Usuario = new JMenuItem();
        btn_miUsuario = new JMenuItem();
        label1 = new JLabel();
        label2 = new JLabel();
        cmb_sucursal = new JComboBox();
        cmb_pelicula = new JComboBox();
        btn_buscar = new JButton();
        scrollPane1 = new JScrollPane();
        tabla = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Cinema");
                menu2.setIcon(new ImageIcon(getClass().getResource("/icon/entrada-de-cine.png")));

                //---- menuItem1 ----
                menuItem1.setText("Sucursales");
                menu2.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Salas");
                menu2.add(menuItem2);
            }
            menuBar1.add(menu2);
            menuBar1.add(hSpacer2);

            //======== menu1 ========
            {
                menu1.setText("Cinematograf\u00eda");
                menu1.setIcon(new ImageIcon(getClass().getResource("/icon/camara-de-video.png")));

                //---- btn_genero ----
                btn_genero.setText("Genero");
                btn_genero.addActionListener(e -> btn_genero(e));
                menu1.add(btn_genero);

                //---- btn_formato ----
                btn_formato.setText("Formato");
                btn_formato.addActionListener(e -> btn_formato(e));
                menu1.add(btn_formato);

                //---- btn_peliculas ----
                btn_peliculas.setText("Peliculas");
                menu1.add(btn_peliculas);

                //---- btn_funciones ----
                btn_funciones.setText("Funciones");
                btn_funciones.addActionListener(e -> btn_funciones(e));
                menu1.add(btn_funciones);

                //---- btn_clasificacion ----
                btn_clasificacion.setText("Clasificaci\u00f3n");
                menu1.add(btn_clasificacion);
            }
            menuBar1.add(menu1);
            menuBar1.add(hSpacer1);

            //---- btn_Usuario ----
            btn_Usuario.setText("Usuarios");
            btn_Usuario.setIcon(new ImageIcon(getClass().getResource("/icon/editar.png")));
            btn_Usuario.addActionListener(e -> btn_Usuario(e));
            btn_Usuario.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btn_UsuarioMouseClicked(e);
                }
            });
            menuBar1.add(btn_Usuario);

            //---- btn_miUsuario ----
            btn_miUsuario.setText("Mi Usuario");
            btn_miUsuario.setIcon(new ImageIcon(getClass().getResource("/icon/usuario_1.png")));
            menuBar1.add(btn_miUsuario);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("Sucursal");
        label1.setFont(new Font("Inter", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("Pelicula");
        label2.setFont(new Font("Inter", Font.PLAIN, 16));

        //---- btn_buscar ----
        btn_buscar.setText("Buscar");
        btn_buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_buscarMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tabla);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(cmb_sucursal, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2)
                        .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                    .addGap(160, 160, 160))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(246, 246, 246)
                            .addComponent(btn_buscar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_sucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btn_buscar)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel hSpacer2;
    private JMenu menu1;
    private JMenuItem btn_genero;
    private JMenuItem btn_formato;
    private JMenuItem btn_peliculas;
    private JMenuItem btn_funciones;
    private JMenuItem btn_clasificacion;
    private JPanel hSpacer1;
    private JMenuItem btn_Usuario;
    private JMenuItem btn_miUsuario;
    private JLabel label1;
    private JLabel label2;
    private JComboBox cmb_sucursal;
    private JComboBox cmb_pelicula;
    private JButton btn_buscar;
    private JScrollPane scrollPane1;
    private JTable tabla;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
