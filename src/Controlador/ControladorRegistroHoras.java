/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AgendaPaciente;
import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.NewHibernateUtil;
import Vista.MensajeExitoso;
import Vista.Recepcionista.JFCitas;
import Vista.Recepcionista.JFRegistrarHoras;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorRegistroHoras {

    JFRegistrarHoras jFRegistrarHoras;
    AgendaPaciente agendaPaciente;
    Empleado empleado;
    String intervencion;
    String paciente;

    public ControladorRegistroHoras(JFRegistrarHoras jFRegistrarHoras, AgendaPaciente agendaPaciente, Empleado empleado, String intervencion, String paciente) {
        this.jFRegistrarHoras = jFRegistrarHoras;
        this.agendaPaciente = agendaPaciente;
        this.empleado = empleado;
        this.intervencion = intervencion;
        this.paciente = paciente;
        DatePicker dp = new DatePicker();
        int id = agendaPaciente.getIdAgendaPaciente();
        agendaPaciente = obtenerDatosCita(id);
        
        jFRegistrarHoras.txtIntervencion.setText(intervencion);
        jFRegistrarHoras.dpFechaCita.setDate(dp.formatoStringtoLocalDate(agendaPaciente.getFecha().toString()));
        jFRegistrarHoras.dpFechaCita.setEnabled(false);
        

        if (agendaPaciente.getHoraEntrada() != null) {
            jFRegistrarHoras.hraEntrada.setTime(agendaPaciente.getHoraEntrada());
        }

        if (agendaPaciente.getHoraLlegada() != null) {
            jFRegistrarHoras.hraLlegada.setTime(agendaPaciente.getHoraLlegada());
        }

        if (agendaPaciente.getHoraSalida() != null) {
            jFRegistrarHoras.hraSalida.setTime(agendaPaciente.getHoraSalida());
        }

        jFRegistrarHoras.txtNombrePAciente.setText(paciente);

        this.jFRegistrarHoras.btn_GuardarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DatePicker dp = new DatePicker();
                AgendaPaciente ag = new AgendaPaciente();

                ag = obtenerDatosCita(id);
                try {

                    ag.setHoraLlegada(dp.formatoToDateTimeComplete(jFRegistrarHoras.hraLlegada.getFormatedTime()));
                    ag.setHoraEntrada(dp.formatoToDateTimeComplete(jFRegistrarHoras.hraEntrada.getFormatedTime()));
                    ag.setHoraSalida(dp.formatoToDateTimeComplete(jFRegistrarHoras.hraSalida.getFormatedTime()));
                    if (updateHoras(ag)) {
                        MensajeExitoso msg = new MensajeExitoso(jFRegistrarHoras, true);
                        msg.msg.setText("<html>     La actualización de la hora de <br<llegada/entrada/salidase ha <br>actualizado con éxito.</html>");
                        msg.setVisible(true);
                        Vista.Recepcionista.JFCitas jFCitas = new JFCitas();
                        ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(jFCitas, empleado);
                        jFCitas.setVisible(true);
                        jFRegistrarHoras.dispose();
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorRegistroHoras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.jFRegistrarHoras.btnCancelarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Recepcionista.JFCitas jFCitas = new JFCitas();
                try {
                    ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(jFCitas, empleado);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorRegistroHoras.class.getName()).log(Level.SEVERE, null, ex);
                }
                jFCitas.setVisible(true);
                jFRegistrarHoras.dispose();
            }

        });

    }

    public boolean updateHoras(AgendaPaciente ag) {
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
}
