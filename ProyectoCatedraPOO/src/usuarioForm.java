import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import Clases.LlenasLista_usuario;
/*
 * Created by JFormDesigner on Tue Sep 12 15:19:18 CST 2023
 */



/**
 * @author jonat
 */
public class usuarioForm extends JDialog {
    LlenasLista_usuario con = new LlenasLista_usuario();
    public usuarioForm(Window owner) {
        super(owner);
        initComponents();
        this.cmb_tipo.setModel(con.obt_tipoUsuario());

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label3 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        txt_id = new JTextField();
        txt_nombre = new JTextField();
        txt_apellido = new JTextField();
        txt_dui = new JTextField();
        txt_telefono = new JTextField();
        cmb_tipo = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label8 = new JLabel();
        textField6 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label3 ----
        label3.setText("Registro Usuarios");
        label3.setFont(new Font("Inter", Font.PLAIN, 26));

        //---- label1 ----
        label1.setText("ID Usuario:");

        //---- label2 ----
        label2.setText("Nombre:");

        //---- label4 ----
        label4.setText("Apellido:");

        //---- label5 ----
        label5.setText("DUI:");

        //---- label6 ----
        label6.setText("Telefono:");

        //---- label7 ----
        label7.setText("Rango Usuario:");

        //---- button1 ----
        button1.setText("Agregar");

        //---- button2 ----
        button2.setText("Eliminar");

        //---- button3 ----
        button3.setText("Modificar");

        //---- label8 ----
        label8.setText("Contrase\u00f1a");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(label2)
                                        .addComponent(label4)
                                        .addComponent(label8))
                                    .addGap(36, 36, 36)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(textField6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                                .addComponent(txt_nombre, GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_id, GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_apellido, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                            .addGap(23, 23, 23)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label6)
                                                .addComponent(label7)
                                                .addComponent(label5))
                                            .addGap(26, 26, 26)
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_telefono)
                                                .addComponent(cmb_tipo)
                                                .addComponent(txt_dui, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(button1)
                                            .addGap(79, 79, 79)
                                            .addComponent(button2)
                                            .addGap(68, 68, 68)
                                            .addComponent(button3))))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(label3)
                    .addGap(46, 46, 46)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(label6)
                        .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(label7)
                        .addComponent(txt_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_tipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(txt_apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5)
                        .addComponent(txt_dui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label8)
                        .addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button3)
                        .addComponent(button2))
                    .addGap(33, 33, 33)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField txt_id;
    private JTextField txt_nombre;
    private JTextField txt_apellido;
    private JTextField txt_dui;
    private JTextField txt_telefono;
    private JComboBox cmb_tipo;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label8;
    private JTextField textField6;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
