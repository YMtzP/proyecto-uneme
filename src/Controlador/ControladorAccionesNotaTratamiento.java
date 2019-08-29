/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.NotaMedica;
import Modelo.NotaTratamiento;
import Modelo.Paciente;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import Vista.Psicologo.JFAgregarNotaTratamientoPaciente;
import Vista.Psicologo.JFNotasTratamientoPaciente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesNotaTratamiento {

    JFAgregarNotaTratamientoPaciente notaTratamientoPaciente;
    Empleado empleado;
    Expediente expediente;
    NotaTratamiento notaTratamiento;
    String paciente;

    public ControladorAccionesNotaTratamiento(JFAgregarNotaTratamientoPaciente notaTratamientoPaciente, Empleado empleado, Expediente expediente, String paciente, NotaTratamiento notaTratamiento) throws ParseException {
        this.notaTratamientoPaciente = notaTratamientoPaciente;
        this.empleado = empleado;
        this.expediente = expediente;
        this.paciente = paciente;

        this.notaTratamiento = notaTratamiento;

        this.notaTratamientoPaciente.labeNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
        DatePicker dp = new DatePicker();
        if (this.notaTratamientoPaciente.etiquetaAccion.getText().equals("Registrar nota de tratamiento")) {
            notaTratamientoPaciente.fechant.setDateToToday();
            notaTratamientoPaciente.horaInicio.setTime(dp.formatoStringToDateTime("08:00:00"));
            notaTratamientoPaciente.horaFin.setTime(dp.formatoStringToDateTime("09:30:00"));
            this.notaTratamientoPaciente.btnGuardraNM.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        NotaTratamiento notaTratamiento = obtenerDatosFormulario();
                        notaTratamiento.setIdEmpleado(empleado.getIdEmpleado());
                        notaTratamiento.setIdExpediente(expediente.getIdExpediente());
                        if (validarDatos()) {
                            if (saveNota(notaTratamiento)) {
                                MensajeExitoso msg = new MensajeExitoso(notaTratamientoPaciente, true);
                                msg.msg.setText("<html>La Nota de Tratamiento se ha <br>registrado correctamente.</html>");
                                msg.setVisible(true);
                                goBack();
                            }
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaTratamiento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } else {
            cargarDatosFormulario(notaTratamiento);
            this.notaTratamientoPaciente.btnGuardraNM.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    NotaTratamiento actualizacionNotaTratamiento;
                    try {
                        actualizacionNotaTratamiento = obtenerDatosFormulario();
                        actualizacionNotaTratamiento.setIdEmpleado(empleado.getIdEmpleado());
                        actualizacionNotaTratamiento.setIdExpediente(expediente.getIdExpediente());
                        if (validarDatos()) {
                            if (updateNota(actualizacionNotaTratamiento)) {
                                MensajeExitoso msg = new MensajeExitoso(notaTratamientoPaciente, true);
                                msg.msg.setText("<html>La Nota de Tratamiento se ha <br>actualizado correctamente.</html>");
                                msg.setVisible(true);
                                goBack();

                            }
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesNotaTratamiento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
        }

        this.notaTratamientoPaciente.btnCancelarNM.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                goBack();
            }
        });

    }

    public void goBack() {
        JFNotasTratamientoPaciente jf = new JFNotasTratamientoPaciente();
        ControladorNotaTratamiento controladorNotaTratamiento = new ControladorNotaTratamiento(jf, expediente, empleado, paciente, notaTratamiento);
        jf.txtPacienteNotas.setText(paciente);
        jf.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
        jf.setVisible(true);
        notaTratamientoPaciente.dispose();
    }

    public boolean updateNota(NotaTratamiento nota) {
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

    public NotaTratamiento obtenerDatosFormulario() throws ParseException {
        DatePicker dp = new DatePicker();
        notaTratamiento.setFechaNotaTratamiento(dp.formatoDate(notaTratamientoPaciente.fechant));
        
        notaTratamiento.setHoraFinNotaTratamiento(dp.formatoToDateTimeComplete(notaTratamientoPaciente.horaFin.getFormatedTime()));
        notaTratamiento.setHoraInicioNotaTratamiento(dp.formatoToDateTimeComplete(notaTratamientoPaciente.horaInicio.getFormatedTime()));
        notaTratamiento.setResumenSesion(notaTratamientoPaciente.txtResumen.getText());
        notaTratamiento.setPronosticoTratamiento(notaTratamientoPaciente.cbPronostico.getSelectedItem() + "");
        notaTratamiento.setDiagnostico(notaTratamientoPaciente.txtDiagnostico.getText());
        notaTratamiento.setServicioOtorgado("" + notaTratamientoPaciente.cbServicio.getSelectedItem());

        return notaTratamiento;
    }

    public boolean saveNota(NotaTratamiento nt) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            s.save(nt);
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

    public void cargarDatosFormulario(NotaTratamiento nota) {
        DatePicker dp = new DatePicker();
        notaTratamientoPaciente.txtResumen.setText(nota.getResumenSesion());
        notaTratamientoPaciente.cbPronostico.setSelectedItem(nota.getPronosticoTratamiento());

        if (!nota.getFechaNotaTratamiento().equals("")) {
            notaTratamientoPaciente.fechant.setDate(dp.formatoStringtoLocalDate(nota.getFechaNotaTratamiento().toString()));
        }

        if (!nota.getHoraFinNotaTratamiento().equals("") || nota.getHoraFinNotaTratamiento() != null) {
            notaTratamientoPaciente.horaFin.setTime(nota.getHoraFinNotaTratamiento());
        }
        if (!nota.getHoraInicioNotaTratamiento().equals("") || nota.getHoraInicioNotaTratamiento() != null) {
            notaTratamientoPaciente.horaInicio.setTime(nota.getHoraInicioNotaTratamiento());
        }
        notaTratamientoPaciente.txtDiagnostico.setText(nota.getDiagnostico());
        notaTratamientoPaciente.cbServicio.setSelectedItem(nota.getServicioOtorgado());
    }

    public boolean validarDatos() throws ParseException {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;
        if (notaTratamientoPaciente.fechant.getDate().isAfter(LocalDate.now())) {
            txtError += "- La fecha no debe ser mayor a la actual<br>";
            errores = true;
            notaTratamientoPaciente.fechant.setDate(LocalDate.now());
        }
        DatePicker dp = new DatePicker();
        Date hora = dp.formatoStringToDateTime(notaTratamientoPaciente.horaFin.getFormatedTime());
        if(hora.before(dp.formatoStringToDateTime("08:00:00"))||hora.after(dp.formatoStringToDateTime("20:00:00"))){
            txtError += "- La hora fin debe estar en el rango de 8:00 hrs-22:00 hrs<br>";
            errores = true;
        }
        
        hora = dp.formatoStringToDateTime(notaTratamientoPaciente.horaInicio.getFormatedTime());
        if(hora.before(dp.formatoStringToDateTime("08:00:00"))|| hora.after(dp.formatoStringToDateTime("20:00:00)"))){
            txtError += "- La hora de inicio debe estar en el rango de 8:00 hrs-22:00 hrs<br>";
            errores = true;
        }
        
        if (errores) {
            txtError += "<html>";
            Vista.Mensaje msg = new Mensaje(notaTratamientoPaciente, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;
    }

}
