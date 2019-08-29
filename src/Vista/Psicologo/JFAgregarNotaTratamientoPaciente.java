/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Psicologo;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author YareMtz
 */
public class JFAgregarNotaTratamientoPaciente extends javax.swing.JFrame {

    /**
     * Creates new form JFAgregarNotaTratamientoPaciente
     */
    public JFAgregarNotaTratamientoPaciente() {
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
        etiquetaAccion = new javax.swing.JLabel();
        labelIcon2 = new javax.swing.JLabel();
        btnGuardraNM = new javax.swing.JButton();
        labeNumExp = new javax.swing.JLabel();
        btnCancelarNM = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        fechant = new com.github.lgooddatepicker.components.DatePicker();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        cbServicio = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        horaFin = new lu.tudor.santec.jtimechooser.JTimeChooser();
        horaInicio = new lu.tudor.santec.jtimechooser.JTimeChooser();
        cbPronostico = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtDiagnostico = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

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

        etiquetaAccion.setText("Registrar Nota de Tratamiento");
        etiquetaAccion.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        etiquetaAccion.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.add(etiquetaAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 670, 30));

        labelIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGO.png"))); // NOI18N
        jPanel7.add(labelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, -1, 60));

        btnGuardraNM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-save.png"))); // NOI18N
        btnGuardraNM.setText("Guardar");
        btnGuardraNM.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardraNM.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(btnGuardraNM, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, 140, 40));

        labeNumExp.setText("Núm. Expediente: ####-####");
        labeNumExp.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(labeNumExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, 20));

        btnCancelarNM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-cancel.png"))); // NOI18N
        btnCancelarNM.setText("Cancelar");
        btnCancelarNM.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarNM.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(btnCancelarNM, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 680, 140, 40));

        jLabel18.setText("Hora inicio:");
        jLabel18.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 30));

        fechant.setFont(new java.awt.Font("Heiti TC", 0, 13)); // NOI18N
        jPanel7.add(fechant, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 260, -1));

        txtResumen.setColumns(20);
        txtResumen.setLineWrap(true);
        txtResumen.setRows(2);
        txtResumen.setBorder(null);
        txtResumen.setFont(new java.awt.Font("Heiti TC", 0, 13)); // NOI18N
        txtResumen.setOpaque(false);
        txtResumen.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txtResumen.setSelectionColor(new java.awt.Color(0, 98, 39));
        txtResumen.setToolTipText("");
        jScrollPane6.setViewportView(txtResumen);

        jPanel7.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 830, 340));

        jLabel23.setText("Pronóstico:");
        jLabel23.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, -1, -1));

        cbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consulta por primera vez", "Tratamiento", "Seguimiento", "Tratamiento individual", "Tratamiento grupal", "Tratamiento familiar individual", "Tratamiento familiar grupal" }));
        cbServicio.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        cbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbServicioActionPerformed(evt);
            }
        });
        jPanel7.add(cbServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 380, 30));

        jLabel43.setText("Servicio otorgado");
        jLabel43.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, 30));

        jLabel25.setText("Fecha");
        jLabel25.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, 30));

        jPanel9.setBackground(new java.awt.Color(139, 165, 155));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Chilpancingo, Guerrero, México.");
        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Calle Venustiano Carranza No. 18, Col. 20 de noviembre, C.P. 39096");
        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 20));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 910, 30));

        jLabel26.setText("Hora fin:");
        jLabel26.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 630, -1, 30));

        horaFin.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 80, 30));

        horaInicio.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        horaInicio.setToolTipText("");
        jPanel7.add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 80, 30));

        cbPronostico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Favorable", "Reservado", "Desfavorable" }));
        cbPronostico.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        cbPronostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPronosticoActionPerformed(evt);
            }
        });
        jPanel7.add(cbPronostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 630, 380, 30));

        jLabel20.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel20.setText("próxima sesión");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 205, -1, -1));

        jLabel27.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel27.setText("Indicar la impresión del usuario, objetivo, avances, resumen de la sesión, plan de tratamiento, tema a trabajar en la");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 730, 10));

        txtDiagnostico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiagnostico.setBorder(null);
        txtDiagnostico.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        txtDiagnostico.setToolTipText("");
        jPanel7.add(txtDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 730, 30));

        jLabel19.setText("Diagnóstico:");
        jLabel19.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 904, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbServicioActionPerformed

    private void jPanel7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7FocusLost

    private void cbPronosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPronosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPronosticoActionPerformed

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
            java.util.logging.Logger.getLogger(JFAgregarNotaTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFAgregarNotaTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFAgregarNotaTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFAgregarNotaTratamientoPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFAgregarNotaTratamientoPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarNM;
    public javax.swing.JButton btnGuardraNM;
    public javax.swing.JComboBox<String> cbPronostico;
    public javax.swing.JComboBox<String> cbServicio;
    public javax.swing.JLabel etiquetaAccion;
    public com.github.lgooddatepicker.components.DatePicker fechant;
    public lu.tudor.santec.jtimechooser.JTimeChooser horaFin;
    public lu.tudor.santec.jtimechooser.JTimeChooser horaInicio;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JLabel labeNumExp;
    private javax.swing.JLabel labelIcon2;
    public javax.swing.JTextField txtDiagnostico;
    public javax.swing.JTextArea txtResumen;
    // End of variables declaration//GEN-END:variables
}
