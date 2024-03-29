/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Psicologo;

import java.awt.Image;
import javax.swing.*;
/**
 *
 * @author YareMtz
 */
public class JFNotasTratamientoPaciente extends javax.swing.JFrame {

    /**
     * Creates new form JFNotasTratamientoPaciente
     */
    public JFNotasTratamientoPaciente() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        Image icon = new ImageIcon(getClass().getResource("/imagenes/solo-unemecapa.png")).getImage();
        setIconImage(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelIcon2 = new javax.swing.JLabel();
        btnCancelarNotaMed = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotasTratamientoPac = new JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        btnVerNotaMed = new javax.swing.JButton();
        labelNumExp = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtPacienteNotas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnAgregarNotaMed1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnGeneraNotaTratamiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Unidad de Especialidad Médica - Centro de Atención Primaria en Adicciones");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel7FocusLost(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 138, 58));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Notas de tratamiento del paciente");
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, 30));

        labelIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGO.png"))); // NOI18N
        jPanel7.add(labelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, 60));

        btnCancelarNotaMed.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarNotaMed.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnCancelarNotaMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-home.png"))); // NOI18N
        btnCancelarNotaMed.setToolTipText("Regresar al menu principal");
        jPanel7.add(btnCancelarNotaMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, 40, 40));

        jScrollPane1.setRowHeaderView(null);

        tablaNotasTratamientoPac.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        tablaNotasTratamientoPac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Servicio otorgado", "Tema siguiente sesión", "Diagnostico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaNotasTratamientoPac.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(tablaNotasTratamientoPac);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 810, 220));

        btnVerNotaMed.setBackground(new java.awt.Color(255, 255, 255));
        btnVerNotaMed.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnVerNotaMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-view_file.png"))); // NOI18N
        btnVerNotaMed.setText("Ver");
        btnVerNotaMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaMedActionPerformed(evt);
            }
        });
        jPanel7.add(btnVerNotaMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 80, 40));

        labelNumExp.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        labelNumExp.setText("Núm. Expediente: ####-####");
        jPanel7.add(labelNumExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, 20));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 420, 10));

        txtPacienteNotas.setEditable(false);
        txtPacienteNotas.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        txtPacienteNotas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPacienteNotas.setToolTipText("");
        txtPacienteNotas.setBorder(null);
        jPanel7.add(txtPacienteNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 420, 30));

        jLabel18.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel18.setText("Nombre");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        btnAgregarNotaMed1.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarNotaMed1.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnAgregarNotaMed1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-plus.png"))); // NOI18N
        btnAgregarNotaMed1.setText("Agregar ");
        btnAgregarNotaMed1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNotaMed1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnAgregarNotaMed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 130, 40));

        jPanel9.setBackground(new java.awt.Color(139, 165, 155));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Chilpancingo, Guerrero, México.");
        jPanel9.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, 20));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Calle Venustiano Carranza No. 18, Col. 20 de noviembre, C.P. 39096");
        jPanel9.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 20));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 840, 30));

        btnGeneraNotaTratamiento.setBackground(new java.awt.Color(255, 255, 255));
        btnGeneraNotaTratamiento.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnGeneraNotaTratamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-pdf_2.png"))); // NOI18N
        btnGeneraNotaTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneraNotaTratamientoActionPerformed(evt);
            }
        });
        jPanel7.add(btnGeneraNotaTratamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 50, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerNotaMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaMedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerNotaMedActionPerformed

    private void btnAgregarNotaMed1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNotaMed1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarNotaMed1ActionPerformed

    private void jPanel7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7FocusLost

    private void btnGeneraNotaTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneraNotaTratamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGeneraNotaTratamientoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFNotasTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFNotasTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFNotasTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFNotasTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFNotasTratamientoPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarNotaMed1;
    public javax.swing.JButton btnCancelarNotaMed;
    public javax.swing.JButton btnGeneraNotaTratamiento;
    public javax.swing.JButton btnVerNotaMed;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel labelIcon2;
    public javax.swing.JLabel labelNumExp;
    public javax.swing.JTable tablaNotasTratamientoPac;
    public javax.swing.JTextField txtPacienteNotas;
    // End of variables declaration//GEN-END:variables
}
