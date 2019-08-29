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
import Modelo.Paciente;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.JFLogin;
import Vista.Mensaje;
import Vista.Recepcionista.JFCitas;
import Vista.Recepcionista.JFPacientes;
import Vista.Recepcionista.JFRegistrarCita;
import Vista.Recepcionista.JFRegistrarHoras;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
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
public class ControladorAgendaPacientes {

    JFCitas citas;
    Empleado empleado;

    public ControladorAgendaPacientes(JFCitas citas, Empleado empleado) throws ParseException {
        this.citas = citas;
        this.empleado = empleado;
        DatePicker dp = new DatePicker();
        this.citas.datePickerCitas.setDateToToday();
        cargarPacientes(LocalDate.now().toString());
        this.citas.labelFechaSeleccionada.setText(dp.formatoFechaLetra(citas.datePickerCitas));

        this.citas.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                citas.dispose();
                jFLogin.setVisible(true);
            }
        });
        this.citas.btn_Pacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFPacientes jfPaciente = new JFPacientes();
                Expediente expediente = new Expediente();
                Paciente paciente = new Paciente();
                ControladorPacientesRecepcionista controladorPacientesRecepcionista = new ControladorPacientesRecepcionista(jfPaciente, expediente, paciente, empleado);
                jfPaciente.setVisible(true);
                citas.dispose();
            }

        });

        this.citas.btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DatePicker dp = new DatePicker();
                try {

                    if (dp.formatoDate(citas.datePickerCitas).before(dp.formatoStringToDate(LocalDate.now().toString()))) {
                        citas.btnActualizar.setEnabled(false);
                        citas.btnRegistrarHras.setEnabled(false);
                    }
                    if(dp.formatoDate(citas.datePickerCitas).after(dp.formatoStringToDate(LocalDate.now().toString()))) {
                        citas.btnRegistrarHras.setEnabled(false);
                        citas.btnActualizar.setEnabled(true);
                    }
                    if(dp.formatoDate(citas.datePickerCitas).equals(dp.formatoStringToDate(LocalDate.now().toString()))) {
                        citas.btnActualizar.setEnabled(true);
                        citas.btnRegistrarHras.setEnabled(true);
                        
                    }
                    String fecha_seleccionada = citas.datePickerCitas.getDate().toString();
                    cargarPacientes(fecha_seleccionada);
                    citas.labelFechaSeleccionada.setText(dp.formatoFechaLetra(citas.datePickerCitas));
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAgendaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.citas.btnRegistrarHras.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (citas.btnActualizar.isEnabled() && citas.btnRegistrarHras.isEnabled()) {
                    JFRegistrarHoras jFRegistrarHoras = new JFRegistrarHoras();
                    AgendaPaciente agendaPaciente = new AgendaPaciente();
                    int fila = citas.tablaCitas.getSelectedRow();
                    if (fila >= 0) {
                        String paciente = citas.tablaCitas.getValueAt(fila, 2).toString();
                        String intervencion = citas.tablaCitas.getValueAt(fila, 4).toString();
                        agendaPaciente.setIdAgendaPaciente(Integer.parseInt(citas.tablaCitas.getValueAt(fila, 0).toString()));
                        ControladorRegistroHoras controladorRegistroHoras = new ControladorRegistroHoras(jFRegistrarHoras, agendaPaciente, empleado, intervencion, paciente);
                        jFRegistrarHoras.setVisible(true);
                        citas.dispose();
                    }else{
                        Mensaje msg = new Mensaje(citas, true);
                        msg.msg.setText("<html>Debe selecionar una cita para registrar la hora de llegada/entrada/salida.</htlm>");
                        msg.setVisible(true);
                    }
                }

            }
        });

        this.citas.btnActualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (citas.btnActualizar.isEnabled()) {
                    JFRegistrarCita cita = new JFRegistrarCita();
                    AgendaPaciente agendaPaciente = new AgendaPaciente();
                    int fila = citas.tablaCitas.getSelectedRow();
                    if (fila >= 0) {

                        Expediente expediente = new Expediente();
                        String intervencion = citas.tablaCitas.getValueAt(fila, 4).toString();
                        String nombre_paciente = citas.tablaCitas.getValueAt(fila, 2).toString();
                        agendaPaciente.setIdAgendaPaciente(Integer.parseInt(citas.tablaCitas.getValueAt(fila, 0).toString()));
                        cita.etiquetaAccion.setText("Actualizar cita");
                        try {
                            ControladorAccionesCitas controladorAccionesCitas = new ControladorAccionesCitas(cita, empleado, expediente, agendaPaciente, intervencion, nombre_paciente);
                        } catch (ParseException ex) {
                            Logger.getLogger(ControladorAgendaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        cita.setVisible(true);
                        citas.dispose();
                    } else {
                        Mensaje msg = new Mensaje(citas, true);
                        msg.msg.setText("<html>Debe selecionar una cita para modificarla.</htlm>");
                        msg.setVisible(true);
                    }
                }

            }
        });

    }

    public void cargarPacientes(String fecha) {
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        citas.tablaCitas.setRowSorter(elQueOrdena);
        Tabla t;
        modelo.addColumn("Id");
        modelo.addColumn("Hora");
        modelo.addColumn("Paciente");
        modelo.addColumn("Num. Expediente");
        modelo.addColumn("Intervencion");
        modelo.addColumn("Observacion");
        modelo.addColumn("Hora de llegada");
        modelo.addColumn("Hora de entrada");
        modelo.addColumn("Hora de salida");
        modelo.addColumn("Tipo");

        List pacientes_recepcionista = obtenerCitasPacientes(fecha);

        if (pacientes_recepcionista.size() > 0) {
            int i = 0;

            for (Iterator iterator = pacientes_recepcionista.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                Object[] filas = new Object[10];
                filas[0] = objects[0];
                filas[1] = objects[1];
                filas[2] = objects[2] + " " + objects[3] + " " + objects[4];
                filas[3] = objects[5];
                filas[4] = objects[6];
                filas[5] = objects[7];
                filas[6] = objects[8];
                filas[7] = objects[9];
                filas[8] = objects[10];
                filas[9] = objects[11];
                modelo.addRow(filas);
            }

            citas.tablaCitas.setModel(modelo);
            t = new Tabla(citas.tablaCitas);
            t.packColumn(0);
            t.packColumn(1);
            t.packColumn(2);
            t.packColumn(3);
            t.packColumn(4);
            t.packColumn(5);
            t.packColumn(6);
            t.packColumn(7);
            t.packColumn(8);
            t.packColumn(9);

        }

    }

    public List obtenerCitasPacientes(String fecha) {
        List citas = new ArrayList();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            citas = s.createSQLQuery("select agendapaciente.id_agenda_paciente, agendapaciente.hora, paciente.nombre, paciente.apellido_paterno, paciente.apellido_materno, "
                    + "paciente.`celular`, expediente.num_expediente, intervencion.nombre as intervencion, agendapaciente.observacion, "
                    + "agendapaciente.`hora_llegada`, agendapaciente.`hora_entrada`, agendapaciente.`hora_salida`, agendapaciente.`tipo` "
                    + "from expediente join intervencion on expediente.intervencion_id=intervencion.`id` join paciente on "
                    + "expediente.id_paciente=paciente.id_paciente join agendapaciente on agendapaciente.`id_paciente`=paciente.id_paciente where agendapaciente.fecha=? order by agendapaciente.hora")
                    .addScalar("id_agenda_paciente").addScalar("hora").addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno").addScalar("num_expediente")
                    .addScalar("intervencion").addScalar("observacion").addScalar("hora_llegada").addScalar("hora_entrada").addScalar("hora_salida")
                    .addScalar("tipo").setParameter(0, fecha)
                    .list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return citas;
    }

}
