/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Medico;

import Modelo.Boton;
import Modelo.Tabla;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author YareMtz
 */
public class JFPacientesMedico extends javax.swing.JFrame {
    Boton boton =  new Boton();
    Tabla tabla;
    /**
     * Creates new form JFPacientesMedico
     */
    public JFPacientesMedico() {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_Logout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_Pacientes = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPacientesMedico = new JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        btnHistoriaClinica = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnNotasMedicas = new javax.swing.JButton();
        btnPDFHistoriaClinica = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Unidad de Especialidad Médica - Centro de Atención Primaria en Adicciones");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel1FocusLost(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 138, 58));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pacientes");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 950, 30));

        jPanel3.setBackground(new java.awt.Color(0, 98, 39));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Logout.setBackground(new java.awt.Color(0, 109, 75));
        btn_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LogoutMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LogoutMouseEntered(evt);
            }
        });
        btn_Logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-exit.png"))); // NOI18N
        btn_Logout.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 50));

        jLabel7.setText("Cerrar sesión");
        jLabel7.setFont(new java.awt.Font("Champagne & Limousines", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        btn_Logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

        jPanel3.add(btn_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 230, 50));

        btn_Pacientes.setBackground(new java.awt.Color(58, 156, 112));
        btn_Pacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_PacientesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_PacientesMouseEntered(evt);
            }
        });
        btn_Pacientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-conference_background_selected.png"))); // NOI18N
        btn_Pacientes.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 50));

        jLabel13.setText("Pacientes");
        jLabel13.setFont(new java.awt.Font("Champagne & Limousines", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        btn_Pacientes.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

        jPanel3.add(btn_Pacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 230, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 660));

        labelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGO.png"))); // NOI18N
        jPanel1.add(labelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, 60));

        jScrollPane1.setRowHeaderView(null);

        tablaPacientesMedico.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        tablaPacientesMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Exp.", "Paciente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPacientesMedico.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(tablaPacientesMedico);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 570, 270));

        btnHistoriaClinica.setBackground(new java.awt.Color(255, 255, 255));
        btnHistoriaClinica.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnHistoriaClinica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-treatment_plan.png"))); // NOI18N
        btnHistoriaClinica.setText("Historia clínica");
        btnHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaClinicaActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistoriaClinica, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 160, 40));

        jPanel8.setBackground(new java.awt.Color(139, 165, 155));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chilpancingo, Guerrero, México.");
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, 20));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Calle Venustiano Carranza No. 18, Col. 20 de noviembre, C.P. 39096");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, 20));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 630, 950, 30));

        btnNotasMedicas.setBackground(new java.awt.Color(255, 255, 255));
        btnNotasMedicas.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnNotasMedicas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-heart_monitor.png"))); // NOI18N
        btnNotasMedicas.setText("Notas médicas");
        btnNotasMedicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotasMedicasActionPerformed(evt);
            }
        });
        jPanel1.add(btnNotasMedicas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 300, 200, 40));

        btnPDFHistoriaClinica.setBackground(new java.awt.Color(255, 255, 255));
        btnPDFHistoriaClinica.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnPDFHistoriaClinica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-pdf_2.png"))); // NOI18N
        btnPDFHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFHistoriaClinicaActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDFHistoriaClinica, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 260, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseExited
        boton.resetColorMenu(btn_Logout);
    }//GEN-LAST:event_btn_LogoutMouseExited

    private void btn_LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseEntered
        boton.setColorSelectedMenu(btn_Logout);
    }//GEN-LAST:event_btn_LogoutMouseEntered

    private void btn_PacientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PacientesMouseExited
        boton.resetColorMenu(btn_Pacientes);
    }//GEN-LAST:event_btn_PacientesMouseExited

    private void btn_PacientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PacientesMouseEntered
        boton.setColorSelectedMenu(btn_Pacientes);
    }//GEN-LAST:event_btn_PacientesMouseEntered

    private void btnHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistoriaClinicaActionPerformed

    private void jPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1FocusLost

    private void btnNotasMedicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotasMedicasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNotasMedicasActionPerformed

    private void btnPDFHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFHistoriaClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFHistoriaClinicaActionPerformed

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
            java.util.logging.Logger.getLogger(JFPacientesMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPacientesMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPacientesMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPacientesMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPacientesMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnHistoriaClinica;
    public javax.swing.JButton btnNotasMedicas;
    public javax.swing.JButton btnPDFHistoriaClinica;
    public javax.swing.JPanel btn_Logout;
    public javax.swing.JPanel btn_Pacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelIcon;
    public javax.swing.JTable tablaPacientesMedico;
    // End of variables declaration//GEN-END:variables
}
