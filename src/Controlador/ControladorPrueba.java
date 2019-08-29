/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.Paciente;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import Vista.Psicologo.JFPacientesPsicologo;
import Vista.Psicologo.JFPrueba;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorPrueba {

    JFPrueba jFPrueba;
    Expediente expediente;
    Empleado empleado;

    public ControladorPrueba(JFPrueba jFPrueba, Expediente expediente, Empleado empleado) {
        this.jFPrueba = jFPrueba;
        this.expediente = expediente;
        this.empleado = empleado;

        Expediente e = obtenerDatosPaciente(expediente.getNumExpediente());

        cargarDatosFormulario(e);

        this.jFPrueba.txtnumsesiones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFPrueba.txtnumsesiones.getText().length() == 2) {
                    e.consume();
                }
            }

        });

        this.jFPrueba.btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Psicologo.JFPacientesPsicologo jFPacientesPsicologo = new JFPacientesPsicologo();
                Paciente paciente = new Paciente();
                ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(jFPacientesPsicologo, empleado, expediente);
                jFPacientesPsicologo.setVisible(true);
                jFPrueba.dispose();
            }

        });
        this.jFPrueba.btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Expediente exp = new Expediente();
                exp = obtenerDatosPaciente(expediente.getNumExpediente());
                obtenerDatosFormulario(exp);
                if (validarDatos()) {
                    if (updateExpediente(exp)) {
                        MensajeExitoso msg = new MensajeExitoso(jFPrueba, true);
                        msg.msg.setText("<html>    El registro de la prueba <br>se ha realizado correctamente.</html>");
                        msg.setVisible(true);

                        Vista.Psicologo.JFPacientesPsicologo jFPacientesPsicologo = new JFPacientesPsicologo();
                        Paciente paciente = new Paciente();
                        ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(jFPacientesPsicologo, empleado, expediente);
                        jFPacientesPsicologo.setVisible(true);
                        jFPrueba.dispose();
                    }
                }
            }

        });
    }

    public boolean updateExpediente(Expediente exp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(exp);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (Exception ex) {
            System.out.println("erroorrsss" + ex.getMessage());
            return false;
        }
    }

    //----------------------->Obtencion de datos del formulario<--------------------//
    public void obtenerDatosFormulario(Expediente exp) {
        if (this.jFPrueba.txtnumsesiones.getText().isEmpty()) {
            exp.setNumSesiones(null);
        } else {
            exp.setNumSesiones(Integer.parseInt(this.jFPrueba.txtnumsesiones.getText()));
        }

        if (this.jFPrueba.rbAssit.isSelected()) {
            exp.setPruebaRealizada("ASSIT");
        }
        if (this.jFPrueba.rbPosit.isSelected()) {
            exp.setPruebaRealizada("POSIT");
        }

        if (this.jFPrueba.rbpiba.isSelected()) {
            exp.setIntervencionId(1);
        }
        if (this.jFPrueba.rbibbp.isSelected()) {
            exp.setIntervencionId(2);
        }
        if (this.jFPrueba.rbibum.isSelected()) {
            exp.setIntervencionId(3);
        }
        if (this.jFPrueba.rbtbuc.isSelected()) {
            exp.setIntervencionId(4);
        }
        if (this.jFPrueba.rbibmf.isSelected()) {
            exp.setIntervencionId(5);
        }
        if (this.jFPrueba.rbpsc.isSelected()) {
            exp.setIntervencionId(6);
        }
        if (this.jFPrueba.rbppr.isSelected()) {
            exp.setIntervencionId(7);
        }
        if (this.jFPrueba.rbpibfamiliaresusuarios.isSelected()) {
            exp.setIntervencionId(8);
        }
        if (this.jFPrueba.rbpibadolescentesnoconsu.isSelected()) {
            exp.setIntervencionId(9);
        }
        if (this.jFPrueba.rbPropar.isSelected()) {
            exp.setIntervencionId(10);
        }
        if (this.jFPrueba.rbconsejobreve.isSelected()) {
            exp.setIntervencionId(11);
        }
        if (this.jFPrueba.rbintervencionbreve.isSelected()) {
            exp.setIntervencionId(12);
        }

        exp.setNombreFamiliarRepresentanteLegal(this.jFPrueba.txtResponsable.getText());

    }

    public void cargarDatosFormulario(Expediente exp) {

        if (exp.getNumSesiones() != null) {
            this.jFPrueba.txtnumsesiones.setText(exp.getNumSesiones() + "");
        } else {
            this.jFPrueba.txtnumsesiones.setText("");
        }

        if (exp.getPruebaRealizada() == null) {
            this.jFPrueba.rbAssit.setSelected(false);
            this.jFPrueba.rbPosit.setSelected(false);
        } else if (exp.getPruebaRealizada().equals("ASSIT")) {
            this.jFPrueba.rbAssit.setSelected(true);
        } else if (exp.getPruebaRealizada().equals("POSIT")) {
            this.jFPrueba.rbPosit.setSelected(true);
        }
        if (exp.getIntervencionId() != null) {

            if (exp.getIntervencionId() == 1) {
                this.jFPrueba.rbpiba.setSelected(true);
            } else if (exp.getIntervencionId() == 2) {
                this.jFPrueba.rbibbp.setSelected(true);
            } else if (exp.getIntervencionId() == 3) {
                this.jFPrueba.rbibum.setSelected(true);
            } else if (exp.getIntervencionId() == 4) {
                this.jFPrueba.rbtbuc.setSelected(true);
            } else if (exp.getIntervencionId() == 5) {
                this.jFPrueba.rbibmf.setSelected(true);
            } else if (exp.getIntervencionId() == 6) {
                this.jFPrueba.rbpsc.setSelected(true);
            } else if (exp.getIntervencionId() == 7) {
                this.jFPrueba.rbppr.setSelected(true);
            } else if (exp.getIntervencionId() == 8) {
                this.jFPrueba.rbpibfamiliaresusuarios.setSelected(true);
            } else if (exp.getIntervencionId() == 9) {
                this.jFPrueba.rbpibadolescentesnoconsu.setSelected(true);
            } else if (exp.getIntervencionId() == 10) {
                this.jFPrueba.rbPropar.setSelected(true);
            } else if (exp.getIntervencionId() == 11) {
                this.jFPrueba.rbconsejobreve.setSelected(true);
            } else if (exp.getIntervencionId() == 12) {
                this.jFPrueba.rbintervencionbreve.setSelected(true);
            }
        }else{
            this.jFPrueba.rbpiba.setSelected(true);
        }

        this.jFPrueba.txtResponsable.setText(exp.getNombreFamiliarRepresentanteLegal());

    }

    public Expediente obtenerDatosPaciente(String num_exp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Expediente expediente = new Expediente();
        try {
            s = sf.openSession();
            expediente = (Expediente) s.createQuery("from Expediente e where e.numExpediente=?")
                    .setParameter(0, num_exp).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return expediente;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " :ccc aqui hay otro errorssss:c");
        }
        return expediente;
    }

    public boolean validarDatos() {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;

        if (jFPrueba.txtResponsable.getText().isEmpty() || !jFPrueba.txtResponsable.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+")) {
            txtError += "- Nombre del responsable inválido<br>";
            errores = true;
            jFPrueba.txtResponsable.setText("");
        }
        
        if (jFPrueba.txtnumsesiones.getText().isEmpty()) {
            txtError += "- El número de sesiones no puede estar vacío<br>";
            errores = true;
        }
        if (errores) {
            txtError += "";

            Vista.Mensaje msg = new Mensaje(jFPrueba, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;
    }
}
