/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author YareMtz
 */
public class MensajeExitoso extends javax.swing.JDialog {

    /**
     * Creates new form MensajeExitoso
     */
    public MensajeExitoso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        labelAccion5 = new javax.swing.JLabel();
        TituloMsg = new javax.swing.JLabel();
        btnAceptar2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        labelAccion6 = new javax.swing.JLabel();
        msg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel8FocusLost(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelAccion5.setFont(new java.awt.Font("Heiti TC", 0, 16)); // NOI18N
        labelAccion5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-ok.png"))); // NOI18N
        jPanel8.add(labelAccion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 80, 80));

        TituloMsg.setFont(new java.awt.Font("Heiti TC", 0, 16)); // NOI18N
        TituloMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloMsg.setText("Operación exitosa");
        jPanel8.add(TituloMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 310, 30));

        btnAceptar2.setBackground(new java.awt.Color(69, 167, 94));
        btnAceptar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAceptar2MousePressed(evt);
            }
        });
        btnAceptar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAceptar2KeyPressed(evt);
            }
        });
        btnAceptar2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 138, 58));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnAceptar2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 310, 10));

        labelAccion6.setFont(new java.awt.Font("Heiti TC", 0, 16)); // NOI18N
        labelAccion6.setForeground(new java.awt.Color(255, 255, 255));
        labelAccion6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAccion6.setText("Aceptar");
        btnAceptar2.add(labelAccion6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 40));

        jPanel8.add(btnAceptar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 210, 40));

        msg.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msg.setText("El registro se ha realizado");
        msg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                msgKeyPressed(evt);
            }
        });
        jPanel8.add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 230, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptar2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptar2MousePressed
        this.dispose();
    }//GEN-LAST:event_btnAceptar2MousePressed

    private void btnAceptar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptar2KeyPressed

    }//GEN-LAST:event_btnAceptar2KeyPressed

    private void msgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgKeyPressed

    }//GEN-LAST:event_msgKeyPressed

    private void jPanel8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8FocusLost

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
            java.util.logging.Logger.getLogger(MensajeExitoso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MensajeExitoso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MensajeExitoso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MensajeExitoso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MensajeExitoso dialog = new MensajeExitoso(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TituloMsg;
    public javax.swing.JPanel btnAceptar2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel8;
    public javax.swing.JLabel labelAccion5;
    public javax.swing.JLabel labelAccion6;
    public javax.swing.JLabel msg;
    // End of variables declaration//GEN-END:variables
}
