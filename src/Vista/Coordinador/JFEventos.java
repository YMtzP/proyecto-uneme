/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Coordinador;

import Modelo.Boton;
import Modelo.Tabla;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author YareMtz
 */
public class JFEventos extends javax.swing.JFrame {
    Boton btn = new Boton();
    Tabla tabla;
    /**
     * Creates new form JFEventos
     */
    public JFEventos() {
        initComponents();
        Image icon = new ImageIcon(getClass().getResource("/imagenes/solo-unemecapa.png")).getImage();
        setIconImage(icon);
        setLocationRelativeTo(null);
        setResizable(false);
        
        tabla = new Tabla(tablaEventos);
        tabla.packColumn(0);
        tabla.packColumn(1);
        tabla.packColumn(2);
        tabla.packColumn(3);
        tabla.packColumn(4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTimeChooserDemo1 = new lu.tudor.santec.jtimechooser.demo.JTimeChooserDemo();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_Eventos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_Logout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEventos = new JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        datePickerEventos = new com.github.lgooddatepicker.components.DatePicker();
        btnAgregarEvento = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelFechaSeleccionada = new javax.swing.JLabel();
        btnBuscarEventos = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Eventos");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 950, 30));

        jPanel3.setBackground(new java.awt.Color(0, 98, 39));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Eventos.setBackground(new java.awt.Color(58, 156, 112));
        btn_Eventos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-tear_off_calendar.png"))); // NOI18N
        btn_Eventos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 40, 50));

        jLabel5.setFont(new java.awt.Font("Champagne & Limousines", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Eventos");
        btn_Eventos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, 50));

        jPanel3.add(btn_Eventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 142, 230, 49));

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
        btn_Logout.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 40, 50));

        jLabel7.setFont(new java.awt.Font("Champagne & Limousines", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cerrar sesión");
        btn_Logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, 50));

        jPanel3.add(btn_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 230, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 660));

        labelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LOGO.png"))); // NOI18N
        jPanel1.add(labelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, 60));

        jScrollPane1.setRowHeaderView(null);

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"2", "8:00 AM", "Mitos y realidades de las drogas", "Esc. Sec. Tecnica #81", "Gabriela Guevara Hernandez"}
            },
            new String [] {
                "ID", "Hora", "Actividad", "Lugar", "Responsables"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEventos.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        tablaEventos.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jScrollPane1.setViewportView(tablaEventos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 850, 320));
        jPanel1.add(datePickerEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 30));

        btnAgregarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-calendar_plus.png"))); // NOI18N
        btnAgregarEvento.setText("Agregar evento");
        btnAgregarEvento.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarEvento.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        jPanel1.add(btnAgregarEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 170, -1));

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

        labelFechaSeleccionada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechaSeleccionada.setText("Fecha: ");
        labelFechaSeleccionada.setFont(new java.awt.Font("Heiti TC", 0, 18)); // NOI18N
        jPanel1.add(labelFechaSeleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 200, 950, 20));

        btnBuscarEventos.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarEventos.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btnBuscarEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-search.png"))); // NOI18N
        btnBuscarEventos.setText("Buscar");
        btnBuscarEventos.setToolTipText("Busca los eventos de la fecha seleccionada");
        jPanel1.add(btnBuscarEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 100, -1));

        btn_update.setBackground(new java.awt.Color(255, 255, 255));
        btn_update.setFont(new java.awt.Font("Heiti TC", 0, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/icons8-edit_file.png"))); // NOI18N
        btn_update.setText("Editar evento");
        jPanel1.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 140, 170, -1));

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

    private void jPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1FocusLost

    private void btn_LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseEntered
        btn.setColorSelectedMenu(btn_Logout);
    }//GEN-LAST:event_btn_LogoutMouseEntered

    private void btn_LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseExited
        btn.resetColorMenu(btn_Logout);
    }//GEN-LAST:event_btn_LogoutMouseExited

    void setColorSelected(JPanel panel){
        panel.setBackground(new Color(58,156,112));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(0,109,75));
    }
    
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
            java.util.logging.Logger.getLogger(JFEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFEventos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarEvento;
    public javax.swing.JButton btnBuscarEventos;
    public javax.swing.JPanel btn_Eventos;
    public javax.swing.JPanel btn_Logout;
    public javax.swing.JButton btn_update;
    public com.github.lgooddatepicker.components.DatePicker datePickerEventos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private lu.tudor.santec.jtimechooser.demo.JTimeChooserDemo jTimeChooserDemo1;
    public javax.swing.JLabel labelFechaSeleccionada;
    private javax.swing.JLabel labelIcon;
    public javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables
}
