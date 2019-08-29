/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.NotaMedica;
import Modelo.Paciente;
import Modelo.RespuestasEncuesta;
import Vista.Medico.JFAgregarNotaMedica;
import Vista.Medico.JFNotasMedicasPaciente;
import Vista.MensajeExitoso;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesNotaMedica {

    JFAgregarNotaMedica agregarNotaMedica;
    Expediente expediente;
    Empleado empleado;
    NotaMedica notaMedica;
    String paciente;

    public ControladorAccionesNotaMedica(JFAgregarNotaMedica agregarNotaMedica, Expediente expediente, Empleado empleado, NotaMedica notaMedica, String paciente) throws ParseException {
        this.agregarNotaMedica = agregarNotaMedica;
        this.expediente = expediente;
        this.empleado = empleado;
        this.paciente = paciente;
        this.notaMedica = notaMedica;

        this.agregarNotaMedica.labeNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());

        if (this.agregarNotaMedica.etiquetaAccion.getText().equals("Registrar Nota Médica")) {
            this.agregarNotaMedica.btnGuardraNM.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    NotaMedica nm = new NotaMedica();
                    try {
                        nm = obtenerDatosFormulario();
                        if (saveNotaMedica(nm)) {

                            MensajeExitoso msg = new MensajeExitoso(agregarNotaMedica, true);
                            msg.msg.setText("<html>La Nota Médica se ha registrado correctamente.</html>");
                            msg.setVisible(true);
                            
                            goBack();

                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaMedica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else if (this.agregarNotaMedica.etiquetaAccion.getText().equals("Actualizar Nota Médica")) {
            
            this.agregarNotaMedica.btnGuardraNM.setText("Actualizar");
            //this.agregarNotaMedica.datePickerFechaNM.setEnabled(false);
            cargarDatosFormulario(notaMedica);

            this.agregarNotaMedica.btnGuardraNM.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    NotaMedica actualizacionNotaMedica;
                    try {
                        actualizacionNotaMedica = obtenerDatosFormulario();
                        actualizacionNotaMedica.setIdNotaMedica(notaMedica.getIdNotaMedica());
                        if (updateNota(actualizacionNotaMedica)) {

                            MensajeExitoso msg = new MensajeExitoso(agregarNotaMedica, true);
                            msg.msg.setText("<html>La Nota Médica se ha actualizado correctamente.</html>");
                            msg.setVisible(true);
                            
                            goBack();
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaMedica.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

        }
        this.agregarNotaMedica.btnCancelarNM.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                goBack();
            }

        });

    }

    public boolean updateNota(NotaMedica nota) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(nota);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (Exception ex) {
            System.out.println("erroorrsss 2" + ex.getMessage());
            if (session.isOpen()) {
                session.getTransaction().rollback();
            }
            return false;
        }
    }

    public NotaMedica obtenerDatosFormulario() throws ParseException {
        DatePicker dp = new DatePicker();
        NotaMedica notaMedica = new NotaMedica();

        notaMedica.setFechaNotaMedica(dp.formatoDate(agregarNotaMedica.datePickerFechaNM));
        notaMedica.setIdEmpleado(empleado.getIdEmpleado());
        notaMedica.setIdExpediente(expediente.getIdExpediente());

        notaMedica.setResumen(agregarNotaMedica.txtResumenNM.getText());

        return notaMedica;
    }

    public boolean saveNotaMedica(NotaMedica nm) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            s.save(nm);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void cargarDatosFormulario(NotaMedica nota) {
        DatePicker dp = new DatePicker();

        agregarNotaMedica.datePickerFechaNM.setDate(dp.formatoStringtoLocalDate(nota.getFechaNotaMedica().toString()));

        agregarNotaMedica.txtResumenNM.setText(nota.getResumen());

    }

    public void goBack() {
        JFNotasMedicasPaciente notasMedicasPaciente = new JFNotasMedicasPaciente();
        ControladorNotaMedicaPaciente controladorNotaMedicaPaciente = new ControladorNotaMedicaPaciente(notasMedicasPaciente, expediente, empleado, paciente);
        notasMedicasPaciente.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
        notasMedicasPaciente.txtPacienteNotasMed.setText(paciente);
        notasMedicasPaciente.setVisible(true);
        agregarNotaMedica.dispose();
    }

}
