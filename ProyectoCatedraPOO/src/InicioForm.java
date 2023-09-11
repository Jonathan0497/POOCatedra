import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Mon Sep 11 08:15:49 CST 2023
 */



/**
 * @author jonat
 */
public class InicioForm extends JFrame {
    public InicioForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        btn_genero = new JMenuItem();
        btn_formato = new JMenuItem();
        btn_peliculas = new JMenuItem();
        btn_funciones = new JMenuItem();
        btn_clasificacion = new JMenuItem();
        hSpacer1 = new JPanel(null);
        btn_Usuario = new JMenuItem();
        btn_miUsuario = new JMenuItem();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Cinematograf\u00eda");
                menu1.setIcon(new ImageIcon(getClass().getResource("/icon/camara-de-video.png")));

                //---- btn_genero ----
                btn_genero.setText("Genero");
                menu1.add(btn_genero);

                //---- btn_formato ----
                btn_formato.setText("Formato");
                menu1.add(btn_formato);

                //---- btn_peliculas ----
                btn_peliculas.setText("Peliculas");
                menu1.add(btn_peliculas);

                //---- btn_funciones ----
                btn_funciones.setText("Funciones");
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
            menuBar1.add(btn_Usuario);

            //---- btn_miUsuario ----
            btn_miUsuario.setText("Mi Usuario");
            btn_miUsuario.setIcon(new ImageIcon(getClass().getResource("/icon/usuario_1.png")));
            menuBar1.add(btn_miUsuario);
        }
        setJMenuBar(menuBar1);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 653, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 572, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem btn_genero;
    private JMenuItem btn_formato;
    private JMenuItem btn_peliculas;
    private JMenuItem btn_funciones;
    private JMenuItem btn_clasificacion;
    private JPanel hSpacer1;
    private JMenuItem btn_Usuario;
    private JMenuItem btn_miUsuario;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
