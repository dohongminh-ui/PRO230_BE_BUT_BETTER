package com.pheobe.main;

import com.pheobe.model.Customer;
import java.awt.Color;

public class AdminSystem extends javax.swing.JFrame {

    private final Customer user;

    public AdminSystem(Customer user) {
        this.user = user;
        initComponents();
        getContentPane().setBackground(new Color(255, 255, 255));
        lbAdmin.setText("Administrator: " + user.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbAdmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");

        lbAdmin.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbAdmin.setForeground(new java.awt.Color(51, 51, 51));
        lbAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAdmin.setText("Administrator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(518, 518, 518)
                .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(Customer user) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSystem(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAdmin;
    // End of variables declaration//GEN-END:variables
} 