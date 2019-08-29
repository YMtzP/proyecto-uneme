/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.TrabajadorSocial;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author YareMtz
 */
public class JFNotasRescatePaciente extends javax.swing.JFrame {

    /**
     * Creates new form JFNotasRescatePaciente
     */
    public JFNotasRescatePaciente() {
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
        btnCancelarLlamda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLlamadasRescatePac = new javax.swing.JTable();
        btnAgregarRescate = new javax.swing.JButton();
        labelNumExp = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtPacienteRescate = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnVerNotaRescate = new javax.swing.JButton();

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

        jLabel3.setText("Llamadas de rescate de paciente");
        jLabel3.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, 30));

        labelIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGO.png"))); // NOI18N
        jPanel7.add(labelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, 60));

        btnCancelarLlamda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-left_round.png"))); // NOI18N
        btnCancelarLlamda.setText("Regresar");
        btnCancelarLlamda.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarLlamda.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(btnCancelarLlamda, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, 140, 40));

        jScrollPane1.setRowHeaderView(null);

        tablaLlamadasRescatePac.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        tablaLlamadasRescatePac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaLlamadasRescatePac.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(tablaLlamadasRescatePac);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 810, 220));

        btnAgregarRescate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-end_call.png"))); // NOI18N
        btnAgregarRescate.setText("Agregar ");
        btnAgregarRescate.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarRescate.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnAgregarRescate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRescateActionPerformed(evt);
            }
        });
        jPanel7.add(btnAgregarRescate, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 130, 40));

        labelNumExp.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        labelNumExp.setText("Núm. Expediente: ####-####");
        jPanel7.add(labelNumExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, 20));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 420, 10));

        txtPacienteRescate.setEditable(false);
        txtPacienteRescate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPacienteRescate.setBorder(null);
        txtPacienteRescate.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        txtPacienteRescate.setToolTipText("");
        jPanel7.add(txtPacienteRescate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 420, 30));

        jLabel18.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel18.setText("Nombre");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 40));

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

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 840, 30));

        btnVerNotaRescate.setBackground(new java.awt.Color(255, 255, 255));
        btnVerNotaRescate.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnVerNotaRescate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-view_file.png"))); // NOI18N
        btnVerNotaRescate.setText("Ver");
        btnVerNotaRescate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaRescateActionPerformed(evt);
            }
        });
        jPanel7.add(btnVerNotaRescate, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 834, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7FocusLost

    private void btnAgregarRescateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRescateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarRescateActionPerformed

    private void btnVerNotaRescateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaRescateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerNotaRescateActionPerformed

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
            java.util.logging.Logger.getLogger(JFNotasRescatePaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFNotasRescatePaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFNotasRescatePaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFNotasRescatePaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFNotasRescatePaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarRescate;
    public javax.swing.JButton btnCancelarLlamda;
    public javax.swing.JButton btnVerNotaRescate;
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
    public javax.swing.JTable tablaLlamadasRescatePac;
    public javax.swing.JTextField txtPacienteRescate;
    // End of variables declaration//GEN-END:variables
}
