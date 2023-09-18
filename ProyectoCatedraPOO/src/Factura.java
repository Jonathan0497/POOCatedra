import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Mon Sep 18 22:56:04 BST 2023
 */



/**
 * @author Gaming
 */
public class Factura extends JFrame {
    public Factura() {
        initComponents();
    }

    private void btnAcceptMouseClicked(MouseEvent e) {
        InicioForm inicioForm = new InicioForm();
        inicioForm.setVisible(true);
        dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
        label1 = new JLabel();
        btnAccept = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Factura ");

        //---- btnAccept ----
        btnAccept.setText("Aceptar");
        btnAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAcceptMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(174, 174, 174)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnAccept)
                        .addComponent(label1))
                    .addContainerGap(146, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                    .addComponent(btnAccept)
                    .addGap(45, 45, 45))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JButton btnAccept;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
