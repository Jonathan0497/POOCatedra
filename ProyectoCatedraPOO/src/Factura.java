import Clases.Asiento;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.ArrayList;
/*
 * Created by JFormDesigner on Mon Sep 18 22:56:04 BST 2023
 */



/**
 * @author Gaming
 */
public class Factura extends JFrame {


    public Factura( ArrayList<String>  butacasSeleccionadas, Double totalPagar, Double vuelto) {
        initComponents();
        lbl_asientos.setText("" +butacasSeleccionadas);
        lbl_total.setText("" + totalPagar);
        lbl_vuelto.setText("" + vuelto);

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
        label2 = new JLabel();
        label3 = new JLabel();
        lbl_asientos = new JLabel();
        lbl_total = new JLabel();
        label4 = new JLabel();
        lbl_vuelto = new JLabel();

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

        //---- label2 ----
        label2.setText("Sus asientos seleccionados son:");

        //---- label3 ----
        label3.setText("Su total es:");

        //---- label4 ----
        label4.setText("Vuelto:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(278, 278, 278)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addComponent(btnAccept))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_total))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addGap(194, 194, 194)
                                    .addComponent(lbl_asientos))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_vuelto)
                                    .addGap(27, 27, 27)))))
                    .addContainerGap(105, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(label1)
                    .addGap(87, 87, 87)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(lbl_asientos))
                    .addGap(55, 55, 55)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(lbl_total))
                    .addGap(36, 36, 36)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(lbl_vuelto))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                    .addComponent(btnAccept)
                    .addGap(97, 97, 97))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Jonathan Mendoza
    private JLabel label1;
    private JButton btnAccept;
    private JLabel label2;
    private JLabel label3;
    private JLabel lbl_asientos;
    private JLabel lbl_total;
    private JLabel label4;
    private JLabel lbl_vuelto;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
