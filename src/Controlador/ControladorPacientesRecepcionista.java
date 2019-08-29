/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AgendaPaciente;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.Intervencion;
import Modelo.NewHibernateUtil;
import Modelo.Paciente;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.JFLogin;
import Vista.Mensaje;
import Vista.Recepcionista.JFCitas;
import Vista.Recepcionista.JFPacientes;
import Vista.Recepcionista.JFRegistrarCita;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorPacientesRecepcionista {

    JFPacientes jFPacientes;
    Expediente expediente;
    Paciente paciente;
    Empleado empleado;

    public ControladorPacientesRecepcionista(JFPacientes jFPacientes, Expediente expediente, Paciente paciente, Empleado empleado) {
        this.jFPacientes = jFPacientes;
        this.expediente = expediente;
        this.paciente = paciente;
        this.empleado = empleado;
        cargarPacientes();

        this.jFPacientes.btnRegistrarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AgendaPaciente agendaPaciente = new AgendaPaciente();
                JFRegistrarCita jfRegistrarCita = new JFRegistrarCita();

                int fila = jFPacientes.tablaPacientes.getSelectedRow();
                if (fila >= 0) {
                    try {
                        expediente.setNumExpediente(jFPacientes.tablaPacientes.getValueAt(fila, 1).toString());
                        jfRegistrarCita.txtNombrePAciente.setText(jFPacientes.tablaPacientes.getValueAt(fila, 2).toString());

                        List lista = obtenerInfoIntervencion(expediente.getNumExpediente());

                        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
                            Object[] objects = (Object[]) iterator.next();
                            jfRegistrarCita.txtIntervencion.setText(objects[0].toString()+" "+objects[1].toString());
                            
                        }

                        
                        paciente.setIdPaciente(Integer.parseInt(jFPacientes.tablaPacientes.getValueAt(fila, 0).toString()));

                        ControladorAccionesCitas controladorAccionesCitas = new ControladorAccionesCitas(jfRegistrarCita, paciente, expediente, agendaPaciente, empleado);
                        jfRegistrarCita.setVisible(true);
                        jFPacientes.dispose();
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorPacientesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Mensaje jf = new Mensaje(jFPacientes, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un paciente para registrar una cita.</html>");
                    jf.setVisible(true);
                }
            }

        });

        this.jFPacientes.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFLogin.setVisible(true);
                jFPacientes.dispose();
            }
        });

        this.jFPacientes.btn_Citas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Recepcionista.JFCitas jFCitas = new JFCitas();
                try {
                    ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(jFCitas, empleado);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorPacientesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
                }
                jFCitas.setVisible(true);
                jFPacientes.dispose();
            }
        });

    }

    public List obtenerPacientes() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        List pacientes = null;
        try {
            s = sf.openSession();
            pacientes = s.createSQLQuery("select expediente.`num_expediente`, paciente.id_paciente, paciente.nombre as paciente, paciente.apellido_paterno, "
                    + "paciente.apellido_materno from paciente join expediente on "
                    + "paciente.id_paciente = expediente.id_paciente ")
                    .addScalar("num_expediente").addScalar("id_paciente").addScalar("paciente").addScalar("apellido_paterno")
                    .addScalar("apellido_materno")
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException ex) {
            Logger.getLogger(ControladorPacientesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacientes;
    }

    public List obtenerInfoIntervencion(String num_exp) {
        List intervencion = new ArrayList();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            intervencion = s.createSQLQuery("select intervencion.nombre, intervencion.siglas from intervencion join expediente on intervencion.id=expediente.intervencion_id where expediente.num_expediente=?")
                    .addScalar("nombre").addScalar("siglas").setParameter(0, num_exp)
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(ControladorPacientesRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intervencion;
    }

    public void cargarPacientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        jFPacientes.tablaPacientes.setRowSorter(elQueOrdena);
        modelo.addColumn("Id paciente");
        modelo.addColumn("Núm. Expediente");
        modelo.addColumn("Paciente");

        List pacientes_recepcionista = obtenerPacientes();

        if (pacientes_recepcionista.size() > 0) {
            int i = 0;

            for (Iterator iterator = pacientes_recepcionista.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                Object[] filas = new Object[3];
                filas[0] = objects[1];
                filas[1] = objects[0];
                filas[2] = objects[2] + " " + objects[3] + " " + objects[4];

                modelo.addRow(filas);
            }

            jFPacientes.tablaPacientes.setModel(modelo);
            t = new Tabla(jFPacientes.tablaPacientes);
            t.packColumn(1);
            t.packColumn(2);
        }

    }

}
