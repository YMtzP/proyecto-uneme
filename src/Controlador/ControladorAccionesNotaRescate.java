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
import java.util.Iterator;
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
public class ControladorAccionesNotaRescate {

    JFAgregarNotaRescate jFAgregarNotaRescate;
    Expediente expediente;
    Empleado empleado;
    String nombre_paciente;
    NotaRescate notaRescate;
    private int id;

    public ControladorAccionesNotaRescate(JFAgregarNotaRescate jFAgregarNotaRescate, Expediente expediente, Empleado empleado, String nombre_paciente, NotaRescate notaRescate) throws ParseException {
        this.jFAgregarNotaRescate = jFAgregarNotaRescate;
        this.expediente = expediente;
        this.empleado = empleado;
        this.nombre_paciente = nombre_paciente;
        this.notaRescate = notaRescate;
        DatePicker dp = new DatePicker();

        validaTyped();
        if (this.jFAgregarNotaRescate.label.getText().equals("Agregar llamada de rescate")) {
            this.jFAgregarNotaRescate.dpFecha.setDateToToday();
            this.jFAgregarNotaRescate.timeHra.setTime(dp.formatoStringToDateTime("08:00:00"));
            this.jFAgregarNotaRescate.btnNuevaCita.setEnabled(false);
            this.jFAgregarNotaRescate.btnSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    NotaRescate nr = new NotaRescate();
                    try {
                        nr = obtenerDatosNotaRescate();
                        nr.setIdUltimaCita(notaRescate.getIdUltimaCita());

                        if (validarDatos()) {
                            if (saveNotaRescate(nr)) {

                                notaRescate.setIdNotaRescate(id);
                                notaRescate.setFechaLlamada(nr.getFechaLlamada());
                                notaRescate.setHoraLlamada(nr.getHoraLlamada());
                                notaRescate.setIdEmpleado(nr.getIdEmpleado());
                                notaRescate.setIdExpediente(nr.getIdExpediente());
                                notaRescate.setIdUltimaCita(nr.getIdUltimaCita());
                                notaRescate.setObservaciones(nr.getObservaciones());

                                MensajeExitoso msg = new MensajeExitoso(jFAgregarNotaRescate, true);
                                msg.msg.setText("<html>El registro la nota de rescate se ha realizado con éxito<br>Puede agendar una nueva cita o regresar al menu principal.</html>");
                                msg.setVisible(true);
                                jFAgregarNotaRescate.btnNuevaCita.setEnabled(true);
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaRescate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            this.jFAgregarNotaRescate.btnNuevaCita.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        JFRegistrarCita registrarCita = new JFRegistrarCita();
                        AgendaPaciente agendaPaciente = new AgendaPaciente();
                        Paciente paciente = new Paciente();
                        if (jFAgregarNotaRescate.btnNuevaCita.isEnabled()) {
                            paciente.setIdPaciente(expediente.getIdPaciente());
                            ControladorAccionesCitas controladorAccionesCitas = new ControladorAccionesCitas(registrarCita, paciente, empleado, expediente, agendaPaciente, notaRescate, nombre_paciente);
                            registrarCita.setVisible(true);
                            jFAgregarNotaRescate.dispose();
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaRescate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        } else {
            
            cargarDatosNotasRescate(notaRescate);
            this.jFAgregarNotaRescate.btnNuevaCita.setEnabled(false);
            this.jFAgregarNotaRescate.btnNuevaCita.setVisible(false);
            this.jFAgregarNotaRescate.timeHra.setEnabled(false);
            this.jFAgregarNotaRescate.txtObservaciones.setEditable(false);
            this.jFAgregarNotaRescate.btnSave.setVisible(false);
            this.jFAgregarNotaRescate.btnSave.setEnabled(false);
            
        }

        this.jFAgregarNotaRescate.btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFNotasRescatePaciente notasRescatePaciente = new JFNotasRescatePaciente();
                NotaRescate notaRescate = new NotaRescate();
                notaRescate.setIdEmpleado(empleado.getIdEmpleado());

                notasRescatePaciente.txtPacienteRescate.setText(nombre_paciente);
                notasRescatePaciente.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                ControladorNotaRescate controladorNotaRescate = new ControladorNotaRescate(notasRescatePaciente, expediente, empleado, nombre_paciente, notaRescate);
                notasRescatePaciente.setVisible(true);

                jFAgregarNotaRescate.dispose();
            }
        });

    }

    public NotaRescate obtenerDatosNotaRescate() {
        DatePicker dp = new DatePicker();
        NotaRescate nr = new NotaRescate();
        try {
            nr.setFechaLlamada(dp.formatoStringToDate(jFAgregarNotaRescate.dpFecha.getDate().toString()));
            nr.setHoraLlamada(dp.formatoStringToDateTime(jFAgregarNotaRescate.timeHra.getFormatedTime().toString()));
            nr.setIdEmpleado(empleado.getIdEmpleado());
            nr.setIdExpediente(expediente.getIdExpediente());
            nr.setObservaciones(jFAgregarNotaRescate.txtObservaciones.getText());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorAccionesNotaRescate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nr;
    }

    public boolean saveNotaRescate(NotaRescate nota) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            id = (Integer) s.save(nota);
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

    public boolean validarDatos() throws ParseException {
        String txtError = "<html>Favor de verificar los datos<br>";
        Boolean errores = false;

        if (jFAgregarNotaRescate.dpFecha.getDate().isAfter(LocalDate.now())) {
            txtError += "- La fecha de la llamada no puede ser una fecha posterior <br>a la actual.<br>";
            errores = true;
        }

        DatePicker dp = new DatePicker();

        if (dp.formatoStringToDateTime(jFAgregarNotaRescate.timeHra.getFormatedTime()).before(dp.formatoStringToDateTime("08:00:00"))
                || dp.formatoStringToDateTime(jFAgregarNotaRescate.timeHra.getFormatedTime()).after(dp.formatoStringToDateTime("20:00:00"))) {
            txtError += "- La hora de llamada debe estar <br>en el horario de atención a pacientes.(08:00 am. - 08:00 pm)";
            errores = true;
        }

        if (errores) {
            txtError += "</html>";

            Vista.Mensaje msg = new Mensaje(jFAgregarNotaRescate, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;
    }

    public void validaTyped() {
        this.jFAgregarNotaRescate.txtObservaciones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFAgregarNotaRescate.txtObservaciones.getText().length() == 100) {
                    e.consume();
                }
            }

        });
    }

    public List obtenerInfoNotaRescate(int nota) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List nota_rescate = new ArrayList();
        try {
            s = sf.openSession();
            nota_rescate = s.createSQLQuery("select uneme_capa.NotaRescate.fecha_llamada, uneme_capa.NotaRescate.observaciones," +
"uneme_capa.`Expediente`.`num_expediente`, uneme_capa.ult_cita.fecha as ultima_cita, uneme_capa.NotaRescate.hora_llamada " +
" from uneme_capa.`NotaRescate` join uneme_capa.`Expediente` on uneme_capa.`NotaRescate`.id_expediente = uneme_capa.`Expediente`.id_expediente " +
"join uneme_capa.`AgendaPaciente` as ult_cita on ult_cita.id_agenda_paciente = uneme_capa.`NotaRescate`.id_ultima_cita where notarescate.id_nota_rescate=?")
                    .addScalar("fecha_llamada").addScalar("observaciones").addScalar("num_expediente")
                    .addScalar("ultima_cita").addScalar("hora_llamada")
                    .setParameter(0, nota).list();
            s.flush();
            s.clear();
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + " :ccc aqui hay otro errorssss:c");
        }

        return nota_rescate;

    }
    
    public void cargarDatosNotasRescate(NotaRescate notarescate) throws ParseException{
        DatePicker dp = new DatePicker();
        List nota = obtenerInfoNotaRescate(notarescate.getIdNotaRescate());
        for (Iterator iterator = nota.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            jFAgregarNotaRescate.dpFecha.setDate(dp.formatoStringtoLocalDate(objects[0].toString()));
            jFAgregarNotaRescate.timeHra.setTime(dp.formatoStringToDateTime(objects[4].toString()));
            jFAgregarNotaRescate.txtObservaciones.setText(objects[1].toString());
            jFAgregarNotaRescate.txtUltimaCita.setText(objects[3].toString());
            jFAgregarNotaRescate.labelNumExp.setText("Núm. Expediente: "+objects[2].toString());
            
        }
    }
}
