/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AbstractJasperReports;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.NotaTratamiento;
import Modelo.Paciente;
import Modelo.RespuestasEncuesta;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.JFLogin;
import Vista.Mensaje;
import Vista.Psicologo.JFEntrevistaExploratoria;
import Vista.Psicologo.JFFormatoEgreso;
import Vista.Psicologo.JFNotasTratamientoPaciente;
import Vista.Psicologo.JFPacientesPsicologo;
import Vista.Psicologo.JFPrueba;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorPacientesPsicologo {

    JFPacientesPsicologo jFPacientesPsicologo;
    Empleado empleado;
    Expediente expediente;

    public ControladorPacientesPsicologo(JFPacientesPsicologo jFPacientesPsicologo, Empleado empleado, Expediente expediente) {
        this.jFPacientesPsicologo = jFPacientesPsicologo;
        this.empleado = empleado;
        this.expediente = expediente;
        cargarFichasPacientes(jFPacientesPsicologo);

        this.jFPacientesPsicologo.btn_Logout1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFPacientesPsicologo.dispose();
                jFLogin.setVisible(true);
            }
        });

        this.jFPacientesPsicologo.btn_Prueba.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AbstractJasperReports.prueba("src/Pdf/prueba_assit.jasper");
                AbstractJasperReports.showViewer();
            }
        });

        this.jFPacientesPsicologo.btn_Tamizaje.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                AbstractJasperReports.tamizaje("src/Pdf/hoja_tamizaje.jasper");
                AbstractJasperReports.showViewer();
            }
        });

        this.jFPacientesPsicologo.btnPDFFormatoEgreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ManejadorEncuestas me = new ManejadorEncuestas();

                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                if (fila >= 0) {
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    List<RespuestasEncuesta> lista = me.obtenerRespuestasFormatoEgreso(expediente);
                    Map<String, Object> parametros = me.cargarRespuestas(lista);
                    parametros.put("num_exp", num_exp);
                    parametros.put("emp", emp);
                    parametros.put("fecha_act", LocalDate.now().toString());
                    AbstractJasperReports.createConsentimientoInformado(parametros, getConnection(), "src/Pdf/formato_egreso.jasper");
                    AbstractJasperReports.showViewer();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una paciente para generar el PDF del formato de egreso.</html>");
                    jf.setVisible(true);
                }
            }
        });

        this.jFPacientesPsicologo.btnPDFEntrevista.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ManejadorEncuestas me = new ManejadorEncuestas();

                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                if (fila >= 0) {
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    List<RespuestasEncuesta> lista = me.obtenerRespuestasEntrevistaExploratoria(expediente);
                    Map<String, Object> parametros = me.cargarRespuestas(lista);
                    parametros.put("paciente", num_exp);
                    parametros.put("terapeuta", emp);
                    parametros.put("cedula", empleado.getCedula());
                    parametros.put("fecha_act", LocalDate.now().toString());
                    AbstractJasperReports.createConsentimientoInformado(parametros, getConnection(), "src/Pdf/entrevista_exploratoria.jasper");
                    AbstractJasperReports.showViewer();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una paciente para generar el PDF de la entrevista exploratoria.</html>");
                    jf.setVisible(true);
                }
            }
        });

        this.jFPacientesPsicologo.btnConsentimientoInf.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Map<String, Object> parametros = new HashMap<>();
                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    parametros.put("paciente-ci", num_exp);
                    parametros.put("terapeuta", emp);
                    AbstractJasperReports.createConsentimientoInformado(parametros, getConnection(), "src/Pdf/consentimiento-informado.jasper");
                    AbstractJasperReports.showViewer();
                }
            }
        });

        this.jFPacientesPsicologo.btnCartaEgreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Map<String, Object> parametros = new HashMap<>();
                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                Calendar fecha = Calendar.getInstance();
                if (fila >= 0) {
                    int anio = fecha.get(Calendar.YEAR);
                    int mes = fecha.get(Calendar.MONTH) + 1;
                    int dia = fecha.get(Calendar.DAY_OF_MONTH);

                    String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = obtenerDatosPaciente(num_exp);
                    String sesiones = "0";
                    if (expediente.getNombreFamiliarRepresentanteLegal() != null && expediente.getIntervencionId() != null && expediente.getNumSesiones() != null) {
                        Long num_sesiones = obtieneNumSesiones(expediente.getIdPaciente());
                        if (num_sesiones != null) {
                            sesiones = num_sesiones.toString();
                        }
                        parametros.put("sesiones", sesiones);
                        parametros.put("paciente-ci", num_exp);
                        parametros.put("terapeuta", emp);
                        parametros.put("anio", anio + "");
                        parametros.put("mes", mes + "");
                        parametros.put("dia", dia + "");
                        AbstractJasperReports.createCartaEgresoVoluntario(parametros, getConnection(), "src/Pdf/carta_egreso_voluntario.jasper");
                        AbstractJasperReports.showViewer();
                    } else {
                        Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                        jf.TituloMsg.setText("Error:");
                        jf.msg.setText("<html>El paciente debe tener registrada la prueba realizada, la intervención requerida y un número de sesiones registradas.</html>");
                        jf.setVisible(true);
                    }

                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una paciente para generar la carta de egreso voluntario.</html>");
                    jf.setVisible(true);
                }
            }
        });

        this.jFPacientesPsicologo.btnEntrevistaExploratoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    JFEntrevistaExploratoria jFEntrevistaExploratoria = new JFEntrevistaExploratoria();
                    jFEntrevistaExploratoria.etiquetaExp.setText("Entrevista exploratoria - Núm. Expediente: " + num_exp);
                    try {
                        ControladorEntrevistaExploratoria controladorEntrevistaExploratoria = new ControladorEntrevistaExploratoria(jFEntrevistaExploratoria, expediente, empleado);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorPacientesPsicologo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jFEntrevistaExploratoria.setVisible(true);
                    jFPacientesPsicologo.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una paciente para realizar la entrevista exploratoria.</html>");
                    jf.setVisible(true);
                }

            }

        });

        this.jFPacientesPsicologo.btnRegistroPrueba.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    JFPrueba jFPrueba = new JFPrueba();
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);

                    jFPrueba.txtPAciente.setText(jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 1).toString());
                    jFPrueba.labeNumExp.setText("Número de expediente: " + num_exp);
                    //jFPrueba.rbAssit.setSelected(true);
                    //jFPrueba.rbpiba.setSelected(true);
                    ControladorPrueba controladorPrueba = new ControladorPrueba(jFPrueba, expediente, empleado);
                    jFPrueba.setVisible(true);
                    jFPacientesPsicologo.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un paciente para registrar la prueba realizada.</html>");
                    jf.setVisible(true);
                }

            }

        });

        this.jFPacientesPsicologo.btnFormatoEgreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);

                    JFFormatoEgreso jformatoEgreso = new JFFormatoEgreso();
                    ControladorFormatoEgreso formatoEgreso = new ControladorFormatoEgreso(jformatoEgreso, expediente, empleado);

                    jformatoEgreso.setVisible(true);
                    jFPacientesPsicologo.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un paciente para realizar el formato de egreso.</html>");
                    jf.setVisible(true);
                }
            }

        });

        this.jFPacientesPsicologo.btnNotaTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int fila = jFPacientesPsicologo.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    NotaTratamiento notaTratamiento = new NotaTratamiento();
                    num_exp = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    String paciente = jFPacientesPsicologo.tablaPacientes.getValueAt(fila, 1).toString();
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    Vista.Psicologo.JFNotasTratamientoPaciente jFNotasTratamientoPaciente = new JFNotasTratamientoPaciente();
                    jFNotasTratamientoPaciente.txtPacienteNotas.setText(num_exp);
                    ControladorNotaTratamiento controladorNotaTratamiento = new ControladorNotaTratamiento(jFNotasTratamientoPaciente, expediente, empleado, paciente, notaTratamiento);
                    jFNotasTratamientoPaciente.setVisible(true);
                    jFPacientesPsicologo.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesPsicologo, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un paciente para ver sus notas de tratamiento.</html>");
                    jf.setVisible(true);
                }
            }

        });

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

    //Consulta para obtener la lista de los pacientes del psicologo
    public List getPacientesPsicologo(int id) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List pacientes = null;
        try {
            s = sf.openSession();

            pacientes = s.createSQLQuery("select expediente.num_expediente, ANY_VALUE(paciente.nombre), "
                    + "ANY_VALUE(paciente.apellido_paterno), ANY_VALUE(paciente.apellido_materno) from expediente "
                    + "join paciente on expediente.id_paciente = paciente.id_paciente join respuestasencuesta on "
                    + "expediente.`id_expediente`=respuestasencuesta.`id_expediente` where respuestasencuesta.`id_empleado`=?"
                    + " group by expediente.num_expediente;")
                    .addScalar("num_expediente")
                    .addScalar("ANY_VALUE(paciente.nombre)")
                    .addScalar("ANY_VALUE(paciente.apellido_paterno)")
                    .addScalar("ANY_VALUE(paciente.apellido_materno)")
                    .setParameter(0, id)
                    .list();

            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Error aqui:c");
        }
        return pacientes;
    }

    //MEtodo que sirve para cargar los datos de los pacientes en la tabla
    public void cargarFichasPacientes(JFPacientesPsicologo jf) {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        jf.tablaPacientes.setRowSorter(elQueOrdena);

        modelo.addColumn(" N U M . E X P E D I E N T E ");
        modelo.addColumn(" N O M B R E   P A C I E N T E ");

        List pacientes_psic = getPacientesPsicologo(empleado.getIdEmpleado());
        System.out.println(empleado.getIdEmpleado());
        int i = 0;
        if (pacientes_psic.size() > 0) {
            for (Iterator iterator = pacientes_psic.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                Object[] filas = new Object[3];

                filas[0] = objects[0] + "             ";
                filas[1] = objects[1] + " " + objects[2] + " " + objects[3] + "                ";
                modelo.addRow(filas);
            }

            jf.tablaPacientes.setModel(modelo);
            t = new Tabla(jf.tablaPacientes);
            t.packColumn(1);
            t.packColumn(0);
        }
    }

    public Long obtieneNumSesiones(int id_pac) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Long num_sesiones = null;
        try {
            s = sf.openSession();
            num_sesiones = (Long) s.createQuery("select count(*) from AgendaPaciente e where e.idPaciente=? and e.horaEntrada!=null")
                    .setParameter(0, id_pac).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return num_sesiones;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " :ccc aqui hay otro errorssss:c");
        }
        return num_sesiones;
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "%TargetHxC");
            //System.out.println("Connection succesful");

        } catch (SQLException sqle) {
            System.out.println("Error: getConnection()->SQLException" + sqle.getMessage());
            JOptionPane.showMessageDialog(null, "Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            cn = null;
        } catch (Exception e) {
            System.out.println("Error: getConnection()->Exception" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            cn = null;
        }
        return cn;
    }
}
