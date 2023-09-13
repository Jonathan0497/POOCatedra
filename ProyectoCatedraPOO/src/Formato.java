import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Tue Sep 12 23:09:52 CST 2023
 */



/**
 * @author jonat
 */
public class Formato extends JDialog {
    public Formato(Window owner) {
        super(owner);
        initComponents();
    }

    private void btn_modificarMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_eliminarMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_guardarMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void scrollPane1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tablaUsuarioMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        txt_idFormato = new JTextField();
        txt_formato = new JTextField();
        txt_precio = new JTextField();
        btn_modificar = new JButton();
        btn_eliminar = new JButton();
        btn_guardar = new JButton();
        label4 = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        tablaUsuario = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("ID Formato");

        //---- btn_modificar ----
        btn_modificar.setText("Modificar");
        btn_modificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_modificarMouseClicked(e);
            }
        });

        //---- btn_eliminar ----
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_eliminarMouseClicked(e);
            }
        });

        //---- btn_guardar ----
        btn_guardar.setText("Guardar");
        btn_guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_guardarMouseClicked(e);
            }
        });

        //---- label4 ----
        label4.setText("Precio");

        //---- label2 ----
        label2.setText("Formato");

        //======== scrollPane1 ========
        {
            scrollPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scrollPane1MouseClicked(e);
                }
            });

            //---- tablaUsuario ----
            tablaUsuario.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tablaUsuarioMouseClicked(e);
                    scrollPane1MouseClicked(e);
                    tablaUsuarioMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tablaUsuario);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(label1)
                                .addGap(76, 76, 76)
                                .addComponent(txt_idFormato, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(label2)
                                .addGap(92, 92, 92)
                                .addComponent(txt_formato, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(label4)
                                .addGap(106, 106, 106)
                                .addComponent(txt_precio, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(btn_guardar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_eliminar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_modificar))
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 653, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(label1))
                            .addComponent(txt_idFormato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(label2))
                            .addComponent(txt_formato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(label4))
                            .addComponent(txt_precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(btn_guardar)
                            .addComponent(btn_eliminar)
                            .addComponent(btn_modificar))
                        .addGap(37, 37, 37)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 594, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JTextField txt_idFormato;
    private JTextField txt_formato;
    private JTextField txt_precio;
    private JButton btn_modificar;
    private JButton btn_eliminar;
    private JButton btn_guardar;
    private JLabel label4;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTable tablaUsuario;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
