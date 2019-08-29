/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AbstractJasperReports;
import Modelo.Connection;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.NotaMedica;
import Modelo.NotaTratamiento;
import Modelo.Paciente;
import Modelo.Tabla;
import Vista.Mensaje;
import Vista.Psicologo.JFAgregarNotaTratamientoPaciente;
import Vista.Psicologo.JFNotasTratamientoPaciente;
import Vista.Psicologo.JFPacientesPsicologo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ControladorNotaTratamiento {

    JFNotasTratamientoPaciente notasTratamientoPaciente;
    Expediente expediente;
    Empleado empleado;
    String paciente;
    NotaTratamiento notaTratamiento;

    public ControladorNotaTratamiento(JFNotasTratamientoPaciente notasTratamientoPaciente, Expediente expediente, Empleado empleado, String paciente, NotaTratamiento notaTratamiento) {
        this.notasTratamientoPaciente = notasTratamientoPaciente;
        this.expediente = expediente;
        this.empleado = empleado;
        this.paciente = paciente;
        this.notaTratamiento = notaTratamiento;
        tablaNotasDelPaciente();
        notasTratamientoPaciente.labelNumExp.setText("Núm. expediente: " + expediente.getNumExpediente());
        notasTratamientoPaciente.txtPacienteNotas.setText(paciente);
        this.notasTratamientoPaciente.btnAgregarNotaMed1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    JFAgregarNotaTratamientoPaciente tratamientoPaciente = new JFAgregarNotaTratamientoPaciente();
                    tratamientoPaciente.etiquetaAccion.setText("Registrar nota de tratamiento");
                    ControladorAccionesNotaTratamiento controladorAccionesNotaTratamiento = new ControladorAccionesNotaTratamiento(tratamientoPaciente, empleado, expediente, paciente, notaTratamiento);
                    tratamientoPaciente.setVisible(true);
                    notasTratamientoPaciente.dispose();

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorNotaTratamiento.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        this.notasTratamientoPaciente.btnGeneraNotaTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = notasTratamientoPaciente.tablaNotasTratamientoPac.getSelectedRow();
                NotaTratamiento notaTratamiento = new NotaTratamiento();
                
                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                if (fila >= 0) {
                    String fecha = notasTratamientoPaciente.tablaNotasTratamientoPac.getValueAt(fila, 1).toString();
                    int id_nota = (int) notasTratamientoPaciente.tablaNotasTratamientoPac.getValueAt(fila, 0);
                    notaTratamiento = obtenerNotaTratamiento(id_nota);
                    String paciente = notasTratamientoPaciente.txtPacienteNotas.getText();
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("notaTratamiento", id_nota);
                    parametros.put("cedula", empleado.getCedula());
                    parametros.put("terapeuta", emp);
                    parametros.put("fecha_nota", fecha);
                    parametros.put("hora_inicio", notaTratamiento.getHoraInicioNotaTratamiento().toString());
                    parametros.put("hora_fin", notaTratamiento.getHoraFinNotaTratamiento().toString());
                    AbstractJasperReports.createNotaTratamiento(parametros, Connection.getConnection(), "src/Pdf/nota_tratamiento.jasper");
                    AbstractJasperReports.showViewer();
                } else {
                    Mensaje jf = new Mensaje(notasTratamientoPaciente, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una nota de tratamiento para generar el PDF</html>");
                    jf.setVisible(true);
                }
            }
        });

        this.notasTratamientoPaciente.btnCancelarNotaMed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFPacientesPsicologo pacientesPsicologo = new JFPacientesPsicologo();
                ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(pacientesPsicologo, empleado, expediente);
                pacientesPsicologo.setVisible(true);
                notasTratamientoPaciente.dispose();
            }
        });

        this.notasTratamientoPaciente.btnVerNotaMed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFAgregarNotaTratamientoPaciente agregarNotaTratamientoPaciente = new JFAgregarNotaTratamientoPaciente();

                int fila = notasTratamientoPaciente.tablaNotasTratamientoPac.getSelectedRow();
                try {
                    if (fila >= 0) {
                        int id_nota = (int) notasTratamientoPaciente.tablaNotasTratamientoPac.getValueAt(fila, 0);
                        String paciente = notasTratamientoPaciente.txtPacienteNotas.getText();
                        NotaTratamiento notaTratamiento = obtenerNotaTratamiento(id_nota);
                        agregarNotaTratamientoPaciente.etiquetaAccion.setText("Actualizar Nota de Tratamiento");

                        ControladorAccionesNotaTratamiento controladorAccionesNotaTratamiento = new ControladorAccionesNotaTratamiento(agregarNotaTratamientoPaciente, empleado, expediente, paciente, notaTratamiento);

                        agregarNotaTratamientoPaciente.setVisible(true);
                        notasTratamientoPaciente.dispose();
                    } else {
                        Mensaje jf = new Mensaje(notasTratamientoPaciente, true);
                        jf.TituloMsg.setText("Error:");
                        jf.msg.setText("<html>Debe seleccionar una nota de tratamiento para visualizarla/editarla.</html>");
                        jf.setVisible(true);
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorNotaTratamiento.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

    public List getNotasTratamiento(Expediente expediente) {
        List<NotaMedica> lista = new ArrayList<>();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            lista = s.createQuery("from NotaTratamiento where idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "aqui es:cc");
        }
        return lista;
    }

    public NotaTratamiento obtenerNotaTratamiento(int id) {
        NotaTratamiento nm = new NotaTratamiento();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            nm = (NotaTratamiento) s.createQuery("from NotaTratamiento nt where nt.idNotaTratamiento=?")
                    .setParameter(0, id).uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nm;
    }

    public void tablaNotasDelPaciente() {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        notasTratamientoPaciente.tablaNotasTratamientoPac.setRowSorter(elQueOrdena);
        modelo.addColumn("Id");
        modelo.addColumn("Fecha");
        modelo.addColumn("Servicio otorgado");
        modelo.addColumn("Pronostico");

        List<NotaTratamiento> listaNotas = getNotasTratamiento(expediente);
        for (int i = 0; i < listaNotas.size(); i++) {
            Object[] filas = new Object[4];
            filas[0] = listaNotas.get(i).getIdNotaTratamiento();
            filas[1] = listaNotas.get(i).getFechaNotaTratamiento();
            filas[2] = listaNotas.get(i).getServicioOtorgado();
            filas[3] = listaNotas.get(i).getPronosticoTratamiento();
            modelo.addRow(filas);
        }
        notasTratamientoPaciente.tablaNotasTratamientoPac.setModel(modelo);
        t = new Tabla(notasTratamientoPaciente.tablaNotasTratamientoPac);
        t.packColumn(0);
        t.packColumn(1);
        t.packColumn(2);
        t.packColumn(3);
    }

}
