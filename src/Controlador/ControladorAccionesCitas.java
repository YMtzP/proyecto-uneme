/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AgendaPaciente;
import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.NotaRescate;
import Modelo.Paciente;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import Vista.Recepcionista.JFCitas;
import Vista.Recepcionista.JFRegistrarCita;
import Vista.TrabajadorSocial.JFAgregarNotaRescate;
import Vista.TrabajadorSocial.JFNotasRescatePaciente;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesCitas {

    JFRegistrarCita jFRegistrarCita;
    Paciente paciente;
    Empleado empleado;
    Expediente expediente;
    AgendaPaciente agendaPaciente;
    int id_nva_cita_ts;

    //Registro de citas por el recepcionista
    public ControladorAccionesCitas(JFRegistrarCita jFRegistrarCita, Paciente paciente, Expediente expediente, AgendaPaciente agendaPaciente, Empleado empleado) throws ParseException {
        this.jFRegistrarCita = jFRegistrarCita;
        this.paciente = paciente;
        this.expediente = expediente;
        this.agendaPaciente = agendaPaciente;
        this.empleado = empleado;
        DatePicker dp = new DatePicker();
        jFRegistrarCita.dpFechaCita.setDateToToday();
        jFRegistrarCita.tcHora.setTime(dp.formatoStringToDateTime("08:00:00"));

        validaTyped();

        this.jFRegistrarCita.btnCancelarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFCitas citas = new JFCitas();
                try {
                    ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(citas, empleado);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAccionesCitas.class.getName()).log(Level.SEVERE, null, ex);
                }
                citas.setVisible(true);
                jFRegistrarCita.dispose();
            }
        });

        if (jFRegistrarCita.etiquetaAccion.getText().equals("Registrar cita")) {
            this.jFRegistrarCita.btn_GuardarCita.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        AgendaPaciente ag = new AgendaPaciente();

                        if (validarDatos()) {
                            ag = obtenerDatos();
                            ag.setIdPaciente(paciente.getIdPaciente());
                            if (saveCita(ag)) {
                                MensajeExitoso msg = new MensajeExitoso(jFRegistrarCita, true);
                                msg.msg.setText("<html>El registro la cita del paciente <br>se ha registrado con éxito.</html>");
                                msg.setVisible(true);

                                JFCitas citas = new JFCitas();
                                ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(citas, empleado);
                                citas.setVisible(true);
                                jFRegistrarCita.dispose();
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesCitas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

    }

    //Actualizar citas por recepcionista
    public ControladorAccionesCitas(JFRegistrarCita jFRegistrarCita, Empleado empleado, Expediente expediente, AgendaPaciente agendaPaciente, String intervencion, String paciente) throws ParseException {
        this.jFRegistrarCita = jFRegistrarCita;
        this.empleado = empleado;
        this.expediente = expediente;
        this.agendaPaciente = agendaPaciente;
        this.paciente = new Paciente();
        int id_cita = agendaPaciente.getIdAgendaPaciente();
        DatePicker dp = new DatePicker();
        agendaPaciente = obtenerDatosCita(id_cita);
        int id_paciente = agendaPaciente.getIdPaciente();

        this.jFRegistrarCita.txtIntervencion.setText(intervencion);
        this.jFRegistrarCita.txtNombrePAciente.setText(paciente);
        this.jFRegistrarCita.txtObservaciones.setText(agendaPaciente.getObservacion());
        this.jFRegistrarCita.cbTipoCita.setSelectedItem(agendaPaciente.getTipo());
        this.jFRegistrarCita.dpFechaCita.setDate(dp.formatoStringtoLocalDate(agendaPaciente.getFecha().toString()));

        this.jFRegistrarCita.tcHora.setTime(dp.formatoStringToDateTime(agendaPaciente.getHora().toString()));
        if (agendaPaciente.getTipo().equals("consulta_medica")) {
            this.jFRegistrarCita.cbTipoCita.setSelectedIndex(1);
        }
        if (agendaPaciente.getTipo().equals("consulta_psicologia")) {
            this.jFRegistrarCita.cbTipoCita.setSelectedIndex(0);
        }
        validaTyped();

        this.jFRegistrarCita.btn_GuardarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AgendaPaciente ag = new AgendaPaciente();
                try {
                    if (validarDatos()) {
                        ag = obtenerDatos();
                        ag.setIdAgendaPaciente(id_cita);
                        ag.setIdPaciente(id_paciente);

                        if (updateCita(ag)) {
                            MensajeExitoso msg = new MensajeExitoso(jFRegistrarCita, true);
                            msg.msg.setText("<html>La actualización de la cita del paciente se ha registrado con éxito.</html>");
                            msg.setVisible(true);

                            JFCitas citas = new JFCitas();
                            ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(citas, empleado);
                            citas.setVisible(true);
                            jFRegistrarCita.dispose();
                        }
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAccionesCitas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        this.jFRegistrarCita.btnCancelarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFCitas citas = new JFCitas();
                try {
                    ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(citas, empleado);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAccionesCitas.class.getName()).log(Level.SEVERE, null, ex);
                }
                citas.setVisible(true);
                jFRegistrarCita.dispose();
            }
        });

    }

    //Trabajador social
    public ControladorAccionesCitas(JFRegistrarCita jFRegistrarCita, Paciente paciente, Empleado empleado, Expediente expediente, AgendaPaciente agendaPaciente, NotaRescate notaRescate, String nombre_paciente) throws ParseException {
        this.jFRegistrarCita = jFRegistrarCita;
        this.paciente = paciente;
        this.empleado = empleado;
        this.expediente = expediente;
        this.agendaPaciente = agendaPaciente;
        NotaRescate nr = notaRescate;
        DatePicker dp = new DatePicker();
        this.jFRegistrarCita.txtIntervencion.setVisible(false);
        this.jFRegistrarCita.separador.setVisible(false);
        this.jFRegistrarCita.labelinter.setVisible(false);
        this.jFRegistrarCita.txtNombrePAciente.setText(nombre_paciente);

        jFRegistrarCita.dpFechaCita.setDateToToday();
        jFRegistrarCita.tcHora.setTime(dp.formatoStringToDateTime("08:00:00"));
        validaTyped();
        this.jFRegistrarCita.btn_GuardarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    AgendaPaciente ag = new AgendaPaciente();
                    if (validarDatos()) {
                        ag = obtenerDatos();
                        ag.setIdPaciente(paciente.getIdPaciente());

                        if (saveCita(ag)) {

                            nr.setIdProximaCita(id_nva_cita_ts);
                            if (updateNotaRescate(nr)) {
                                MensajeExitoso msg = new MensajeExitoso(jFRegistrarCita, true);
                                msg.msg.setText("<html>El registro la cita del paciente <br>se ha registrado con éxito.</html>");
                                msg.setVisible(true);

                                JFNotasRescatePaciente notasRescatePaciente = new JFNotasRescatePaciente();

                                notasRescatePaciente.txtPacienteRescate.setText(nombre_paciente);
                                notasRescatePaciente.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                                ControladorNotaRescate controladorNotaRescate = new ControladorNotaRescate(notasRescatePaciente, expediente, empleado, nombre_paciente, notaRescate);
                                notasRescatePaciente.setVisible(true);
                                jFRegistrarCita.dispose();
                            }

                            /*JFAgregarNotaRescate agregarNotaRescate = new JFAgregarNotaRescate();

                            List cita_last = obtenerUltimaCita(expediente.getIdPaciente());

                            for (Iterator iterator = cita_last.iterator(); iterator.hasNext();) {
                                Object[] objects = (Object[]) iterator.next();
                                agregarNotaRescate.txtUltimaCita.setText(objects[0].toString());
                            }
                            agregarNotaRescate.txtUltimaCita.setEditable(false);
                            agregarNotaRescate.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                            ControladorAccionesNotaRescate controladorAccionesNotaRescate = new ControladorAccionesNotaRescate(agregarNotaRescate, expediente, empleado, nombre_paciente, notaRescate);
                            agregarNotaRescate.setVisible(true);
                            jFRegistrarCita.dispose();*/
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAccionesCitas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.jFRegistrarCita.btnCancelarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                JFNotasRescatePaciente notasRescatePaciente = new JFNotasRescatePaciente();
                NotaRescate notaRescate = new NotaRescate();

                String num_exp = expediente.getNumExpediente();
                notaRescate.setIdEmpleado(empleado.getIdEmpleado());

                String nombre_paciente = jFRegistrarCita.txtNombrePAciente.getText();
                notasRescatePaciente.txtPacienteRescate.setText(nombre_paciente);
                notasRescatePaciente.labelNumExp.setText("Núm. Expediente: " + num_exp);
                ControladorNotaRescate controladorNotaRescate = new ControladorNotaRescate(notasRescatePaciente, expediente, empleado, nombre_paciente, notaRescate);
                notasRescatePaciente.setVisible(true);
                jFRegistrarCita.dispose();

            }
        });

    }

    public boolean updateCita(AgendaPaciente ag) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(ag);
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

    public boolean updateNotaRescate(NotaRescate nr) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();

            session.update(nr);
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

    public AgendaPaciente obtenerDatos() throws ParseException {
        DatePicker dp = new DatePicker();
        AgendaPaciente agendaPaciente = new AgendaPaciente();
        agendaPaciente.setFecha(dp.formatoDate(jFRegistrarCita.dpFechaCita));
        agendaPaciente.setHora(dp.formatoToDateTimeComplete(jFRegistrarCita.tcHora.getFormatedTime()));
        agendaPaciente.setObservacion(jFRegistrarCita.txtObservaciones.getText());

        if (jFRegistrarCita.cbTipoCita.getSelectedIndex() == 0) {
            agendaPaciente.setTipo("consulta_psicologia");
        }
        if (jFRegistrarCita.cbTipoCita.getSelectedIndex() == 1) {
            agendaPaciente.setTipo("consulta_medica");
        }

        return agendaPaciente;
    }

    public boolean validarDatos() throws ParseException {
        String txtError = "<html>Favor de verificar los datos<br><br>";
        Boolean errores = false;
        if (jFRegistrarCita.dpFechaCita.getDate().isBefore(LocalDate.now())) {
            txtError += "- La fecha de la cita no puede <br> ser una fecha anterior a la actual.<br>";
            errores = true;
        }

        DatePicker dp = new DatePicker();

        if (dp.formatoStringToDateTime(jFRegistrarCita.tcHora.getFormatedTime()).before(dp.formatoStringToDateTime("08:00:00"))
                || dp.formatoStringToDateTime(jFRegistrarCita.tcHora.getFormatedTime()).after(dp.formatoStringToDateTime("20:00:00"))) {
            txtError += "La hora de la cita debe estar <br>en el horario de atención a pacientes.<br>(08:00 am. - 08:00 pm)";
            errores = true;
        }

        if (errores) {
            txtError += "</html>";

            Vista.Mensaje msg = new Mensaje(jFRegistrarCita, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;
    }

    public boolean saveCita(AgendaPaciente cita) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            id_nva_cita_ts = (Integer) s.save(cita);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage() + " aqui:c");
            return false;
        }
    }

    public AgendaPaciente obtenerDatosCita(int id) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        AgendaPaciente ag = new AgendaPaciente();
        Session session;
        session = sessionFactory.openSession();
        ag = (AgendaPaciente) session.createQuery("from AgendaPaciente ap where ap.idAgendaPaciente=?")
                .setParameter(0, id)
                .uniqueResult();

        session.flush();
        session.close();
        return ag;
    }

    public List obtenerUltimaCita(int id_pac) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List expediente = new ArrayList();
        try {
            s = sf.openSession();
            expediente = s.createSQLQuery("select fecha, observacion from AgendaPaciente where agendapaciente.fecha = (select max(agendapaciente.fecha) from agendapaciente) and agendapaciente.id_paciente=?")
                    .addScalar("fecha").addScalar("observacion")
                    .setParameter(0, id_pac).list();
            s.flush();
            s.clear();
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + " :ccc aqui hay otro errorssss:c");
        }

        return expediente;

    }

    public void validaTyped() {
        this.jFRegistrarCita.txtObservaciones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFRegistrarCita.txtObservaciones.getText().length() == 100) {
                    e.consume();
                }
            }

        });
    }
}
