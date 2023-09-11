<<<<<<< Updated upstream
import java.awt.*;
=======
import java.awt.event.*;
>>>>>>> Stashed changes
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Thu Sep 07 20:17:51 CST 2023
 */



/**
 * @author jonat
 */
public class Inicio extends JFrame {
    public Inicio() {
        initComponents();
    }

    private void btn_categoria(ActionEvent e) {
        CategoriaForm c = new CategoriaForm(this);
        c.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        menuBar1 = new JMenuBar();
        btn_inicio = new JMenuItem();
        menuBar2 = new JMenuBar();
        btn_cinematografia = new JMenu();
        btn_categoria = new JMenuItem();
        btn_formato = new JMenuItem();
        btn_peliculas = new JMenuItem();
        btn_funcion = new JMenuItem();
        btn_usuario = new JMenuItem();
        btn_miUsuario = new JMenuItem();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //---- btn_inicio ----
            btn_inicio.setText("Inicio");
            btn_inicio.setIcon(new ImageIcon(getClass().getResource("/icon/casa.png")));
            menuBar1.add(btn_inicio);

            //======== menuBar2 ========
            {

                //======== btn_cinematografia ========
                {
                    btn_cinematografia.setText("Cinematografia");
                    btn_cinematografia.setIcon(new ImageIcon(getClass().getResource("/icon/camara-de-video.png")));

                    //---- btn_categoria ----
<<<<<<< Updated upstream
                    btn_categoria.setText("categorias");
                    btn_categoria.setPreferredSize(new Dimension(125, 19));
=======
                    btn_categoria.setText("Categorias");
                    btn_categoria.addActionListener(e -> btn_categoria(e));
>>>>>>> Stashed changes
                    btn_cinematografia.add(btn_categoria);

                    //---- btn_formato ----
                    btn_formato.setText("Formato");
                    btn_cinematografia.add(btn_formato);

                    //---- btn_peliculas ----
                    btn_peliculas.setText("Peliculas");
                    btn_cinematografia.add(btn_peliculas);

                    //---- btn_funcion ----
                    btn_funcion.setText("Funci\u00f3n");
                    btn_cinematografia.add(btn_funcion);
                }
                menuBar2.add(btn_cinematografia);

                //---- btn_usuario ----
                btn_usuario.setText("Usuario");
                btn_usuario.setIcon(new ImageIcon(getClass().getResource("/icon/editar.png")));
                menuBar2.add(btn_usuario);
            }
            menuBar1.add(menuBar2);

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
                .addGap(0, 583, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 516, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JMenuBar menuBar1;
    private JMenuItem btn_inicio;
    private JMenuBar menuBar2;
    private JMenu btn_cinematografia;
    private JMenuItem btn_categoria;
    private JMenuItem btn_formato;
    private JMenuItem btn_peliculas;
    private JMenuItem btn_funcion;
    private JMenuItem btn_usuario;
    private JMenuItem btn_miUsuario;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
