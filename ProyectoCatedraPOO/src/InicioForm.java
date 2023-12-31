import Clases.LlenarLista_inicio;
import Clases.LlenarLista_sucursales;
import Clases.Mantenimiento_Inicio;

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
        cmb_edad.addItem("ADULTO");
        cmb_edad.addItem("3RA");

        modelo.addColumn("ID Multimedia");
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
        MultimediaForm c = new MultimediaForm(this);
        c.setVisible(true);
    }

    private void btn_buscarMouseClicked(MouseEvent e) {
        int indiceSucursal = cmb_sucursal.getSelectedIndex();
        int indicePelicula = cmb_pelicula.getSelectedIndex();

        if (indiceSucursal == 0 || indicePelicula == 0){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una película y sucursal válida. ", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                System.out.println(indicePelicula);
                System.out.println(indiceSucursal);
                Clases.Mantenimiento_Inicio obj = new Clases.Mantenimiento_Inicio();

                // Establece los valores para codigo_sucursal y codigo_pelicula
                obj.setCodigo_sucursal(indiceSucursal);
                obj.setCodigo_pelicula(indicePelicula);

                // Llama al método llenartabla
                obj.llenartabla(tabla);
            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void btn_sucursales(ActionEvent e) {
        SucursalesForm c = new SucursalesForm(this);
        c.setVisible(true);
    }

    private void btn_salas(ActionEvent e) {
        SalasForm c = new SalasForm(this);
        c.setVisible(true);
    }

    private void btn_peliculas(ActionEvent e) {
        PeliculasForm c = new PeliculasForm(this);
        c.setVisible(true);
    }

    private void tablaMouseClicked(MouseEvent e) {
        int filaSeleccionada = tabla.getSelectedRow();
        int columnaSeleccionada = tabla.getSelectedColumn();

        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
            // Obtener el valor de la celda seleccionada como objeto Asiento
            Object valorCelda = tabla.getModel().getValueAt(filaSeleccionada, 0); // 0 representa la primera columna
            Object formatoPelicula = tabla.getModel().getValueAt(filaSeleccionada, 2);
            String idFuncion_String = String.valueOf(valorCelda);
            Integer id_funcion = Integer.valueOf(idFuncion_String);

            Seleccion_boletos_sala seleccionBoletos = new Seleccion_boletos_sala(id_funcion,formatoPelicula.toString(),cmb_edad.getSelectedItem().toString());
            seleccionBoletos.setVisible(true);
            dispose();
        }

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        btn_sucursales = new JMenuItem();
        btn_salas = new JMenuItem();
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
        label3 = new JLabel();
        cmb_edad = new JComboBox();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Cinema");
                menu2.setIcon(new ImageIcon(getClass().getResource("/icon/entrada-de-cine.png")));

                //---- btn_sucursales ----
                btn_sucursales.setText("Sucursales");
                btn_sucursales.addActionListener(e -> btn_sucursales(e));
                menu2.add(btn_sucursales);

                //---- btn_salas ----
                btn_salas.setText("Salas");
                btn_salas.addActionListener(e -> btn_salas(e));
                menu2.add(btn_salas);
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
                btn_peliculas.addActionListener(e -> btn_peliculas(e));
                menu1.add(btn_peliculas);

                //---- btn_funciones ----
                btn_funciones.setText("Funciones");
                btn_funciones.addActionListener(e -> {
			btn_funciones(e);
			btn_funciones(e);
		});
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

            //---- tabla ----
            tabla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tablaMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tabla);
        }

        //---- label3 ----
        label3.setText("Edad:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(cmb_sucursal, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(btn_buscar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
                            .addGap(72, 72, 72)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                            .addGap(60, 60, 60)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label3)
                                .addComponent(cmb_edad, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(cmb_sucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(label3))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_pelicula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_edad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                    .addComponent(btn_buscar)
                    .addGap(18, 18, 18)
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
    private JMenuItem btn_sucursales;
    private JMenuItem btn_salas;
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
    private JLabel label3;
    private JComboBox cmb_edad;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
