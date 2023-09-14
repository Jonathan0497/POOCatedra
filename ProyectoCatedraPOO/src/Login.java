/*
 * Created by JFormDesigner on Thu Sep 14 00:12:48 CST 2023
 */

import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author jonat
 */
public class Login extends JFrame {
    Clases.Conexion cn = new Clases.Conexion();
    public Login() {
        initComponents();
    }

    private void btn_ingresarMouseClicked(MouseEvent e) {
        if (txt_contra.getText().isEmpty() || txt_dui.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Campos vacíos");
        } else {
            String ContraBD, ContraSy, duiBD, duiSy;
            ContraSy = txt_contra.getText();
            duiSy = txt_dui.getText();
            try {
                String sql = "SELECT dui, clave FROM usuario WHERE dui =" + duiSy;
                PreparedStatement ps = cn.conectar().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    duiBD = rs.getString("dui");
                    ContraBD = rs.getString("clave");

                    if (duiSy.equals(duiBD) || ContraSy.equals(ContraBD)){
                        InicioForm ini = new InicioForm();
                        ini.setVisible(true);
                        dispose();
                    }
                }

            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        Login log = new Login();
        log.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        label2 = new JLabel();
        txt_dui = new JTextField();
        label3 = new JLabel();
        txt_contra = new JTextField();
        btn_ingresar = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("PrimeCinema");

        //---- label2 ----
        label2.setText("DUI:");

        //---- label3 ----
        label3.setText("Contrase\u00f1a");

        //---- btn_ingresar ----
        btn_ingresar.setText("Ingresar");
        btn_ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_ingresarMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(label2)
                            .addGap(64, 64, 64)
                            .addComponent(txt_dui, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(72, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 56, Short.MAX_VALUE)
                    .addComponent(label3)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txt_contra, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_ingresar))
                    .addContainerGap(72, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_dui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_contra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addGap(46, 46, 46)
                    .addComponent(btn_ingresar)
                    .addContainerGap(61, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JLabel label2;
    private JTextField txt_dui;
    private JLabel label3;
    private JTextField txt_contra;
    private JButton btn_ingresar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
