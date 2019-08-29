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
import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.NotaRescate;
import Modelo.Paciente;
import Modelo.Tabla;
import Vista.Psicologo.JFPacientesPsicologo;
import Vista.TrabajadorSocial.JFAgregarNotaRescate;
import Vista.TrabajadorSocial.JFNotasRescatePaciente;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorNotaRescate {

    JFNotasRescatePaciente notasRescatePaciente;
    Expediente expediente;
    Empleado empleado;
    String nombre_paciente;
    NotaRescate notaRescate;

    public ControladorNotaRescate(JFNotasRescatePaciente notasRescatePaciente, Expediente expediente, Empleado empleado, String nombre_paciente, NotaRescate notaRescate) {
        this.notasRescatePaciente = notasRescatePaciente;
        this.expediente = expediente;
        this.empleado = empleado;
        this.nombre_paciente = nombre_paciente;
        this.notaRescate = notaRescate;

        cargarFichasPacientes();

        this.notasRescatePaciente.btnCancelarLlamda.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                FichaDeIdentificacion ficha = new FichaDeIdentificacion();
                ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                jFFichasIdentificacion.NumEmpleado.setText(empleado.getIdEmpleado() + "");
                jFFichasIdentificacion.NumEmpleado.setVisible(false);
                jFFichasIdentificacion.setVisible(true);
                notasRescatePaciente.dispose();
            }
        });

        this.notasRescatePaciente.btnAgregarRescate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFAgregarNotaRescate agregarNotaRescate = new JFAgregarNotaRescate();
                try {
                    agregarNotaRescate.label.setText("Agregar llamada de rescate");
                    List cita_last = obtenerUltimaCita(expediente.getIdPaciente());

                    for (Iterator iterator = cita_last.iterator(); iterator.hasNext();) {
                        Object[] objects = (Object[]) iterator.next();
                        notaRescate.setIdUltimaCita(Integer.parseInt(objects[0].toString()));
                        agregarNotaRescate.txtUltimaCita.setText(objects[1].toString());
                    }
                    agregarNotaRescate.txtUltimaCita.setEditable(false);
                    agregarNotaRescate.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                    ControladorAccionesNotaRescate controladorAccionesNotaRescate = new ControladorAccionesNotaRescate(agregarNotaRescate, expediente, empleado, nombre_paciente, notaRescate);
                    agregarNotaRescate.setVisible(true);
                    notasRescatePaciente.dispose();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorNotaRescate.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        this.notasRescatePaciente.btnVerNotaRescate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Actualizar nota
                try {
                    int fila = notasRescatePaciente.tablaLlamadasRescatePac.getSelectedRow();
                    DatePicker dp = new DatePicker();
                    if (fila >= 0) {
                        JFAgregarNotaRescate agregarNotaRescate = new JFAgregarNotaRescate();
                        int id_nota = Integer.parseInt(notasRescatePaciente.tablaLlamadasRescatePac.getValueAt(fila, 3).toString());
                        notaRescate.setIdNotaRescate(id_nota);
                        
                        agregarNotaRescate.label.setText("Nota de rescate");

                        ControladorAccionesNotaRescate controladorAccionesNotaRescate = new ControladorAccionesNotaRescate(agregarNotaRescate, expediente, empleado, nombre_paciente, notaRescate);
                        agregarNotaRescate.setVisible(true);
                        notasRescatePaciente.dispose();
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorNotaRescate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    
    public List obtenerUltimaCita(int id_pac) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List expediente = new ArrayList();
        try {
            s = sf.openSession();
            expediente = s.createSQLQuery("select id_agenda_paciente, fecha from AgendaPaciente where agendapaciente.fecha = (select max(agendapaciente.fecha) as fecha from agendapaciente where agendapaciente.hora_llegada>'07:59:00') and agendapaciente.id_paciente=?")
                    .addScalar("id_agenda_paciente").addScalar("fecha")
                    .setParameter(0, id_pac).list();
            s.flush();
            s.clear();
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + " :ccc aqui hay otro errorssss:c");
        }

        return expediente;

    }

    public List obtenerNotasRescate(int id_exp) {
        List lista = new ArrayList<>();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            lista = s.createSQLQuery("select fecha_llamada, hora_llamada, observaciones, id_nota_rescate from notarescate where id_expediente=?")
                    .addScalar("fecha_llamada").addScalar("hora_llamada").addScalar("observaciones").addScalar("id_nota_rescate")
                    .setParameter(0, id_exp)
                    .list();

            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void cargarFichasPacientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        notasRescatePaciente.tablaLlamadasRescatePac.setRowSorter(elQueOrdena);

        modelo.addColumn(" Fecha ");
        modelo.addColumn(" Hora");
        //modelo.addColumn(" Ultima cita");
        //
        modelo.addColumn(" Observaciones");
        modelo.addColumn("Num. Nota Rescate");

        List pacientes_psic = obtenerNotasRescate(expediente.getIdExpediente());

        int i = 0;
        if (pacientes_psic.size() > 0) {
            for (Iterator iterator = pacientes_psic.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                Object[] filas = new Object[4];

                filas[0] = objects[0];
                filas[1] = objects[1];
                filas[2] = objects[2];
                filas[3] = objects[3];
                modelo.addRow(filas);
            }

            notasRescatePaciente.tablaLlamadasRescatePac.setModel(modelo);
            t = new Tabla(notasRescatePaciente.tablaLlamadasRescatePac);
            t.packColumn(1);
            t.packColumn(0);
            t.packColumn(2);
        }

    }
}
