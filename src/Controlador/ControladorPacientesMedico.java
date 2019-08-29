/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorPacientesPsicologo.getConnection;
import Modelo.AbstractJasperReports;
import Modelo.Empleado;
import Modelo.Encuesta;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.Pregunta;
import Modelo.RespuestasEncuesta;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.JFLogin;
import Vista.Medico.*;
import Vista.Medico.JFPacientesMedico;
import Vista.Mensaje;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;
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
public class ControladorPacientesMedico {

    JFPacientesMedico jFPacientesMedico;
    Empleado empleado;
    Expediente expediente;

    public ControladorPacientesMedico(JFPacientesMedico jFPacientesMedico, Empleado empleado, Expediente expediente) {
        this.jFPacientesMedico = jFPacientesMedico;
        this.empleado = empleado;
        this.expediente = expediente;

        cargarPacientesMedico(jFPacientesMedico);

        this.jFPacientesMedico.btnHistoriaClinica.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFPacientesMedico.tablaPacientesMedico.getSelectedRow();
                if (fila >= 0) { //se selecciono un paciente
                    String num_exp = jFPacientesMedico.tablaPacientesMedico.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    //RespuestasEncuesta rp = new RespuestasEncuesta();
                    //rp.setIdEmpleado(empleado.getIdEmpleado());
                    JFHistoriaClinica jFHistoriaClinica = new JFHistoriaClinica();
                    try {
                        jFHistoriaClinica.etqExpediente.setText("Historia clínica - Expediente: "+expediente.getNumExpediente());
                        ControladorHistoriaClinica controllerHistoriaClinica = new ControladorHistoriaClinica(jFHistoriaClinica, expediente, empleado);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorPacientesMedico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jFHistoriaClinica.setVisible(true);
                    jFPacientesMedico.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesMedico, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un registro para abrir su historia clinica</html>");
                    jf.setVisible(true);
                }
            }
        });

        this.jFPacientesMedico.btnPDFHistoriaClinica.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int fila = jFPacientesMedico.tablaPacientesMedico.getSelectedRow();
                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();
                if (fila >= 0) { //se selecciono un paciente
                    ManejadorEncuestas me = new ManejadorEncuestas();
                    String num_exp = jFPacientesMedico.tablaPacientesMedico.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    String paciente = jFPacientesMedico.tablaPacientesMedico.getValueAt(fila, 1).toString();
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    List<RespuestasEncuesta> lista = me.obtieneRespuestasEncuestaHistoriaClinica(expediente);
                    Map<String, Object> parametros = me.cargarRespuestas(lista);
                    parametros.put("num_exp", num_exp);
                    parametros.put("medico", emp);
                    parametros.put("fecha", LocalDate.now().toString());
                    AbstractJasperReports.createConsentimientoInformado(parametros, getConnection(), "src/Pdf/historia_clinica.jasper");
                    AbstractJasperReports.showViewer();

                } else {
                    Mensaje jf = new Mensaje(jFPacientesMedico, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un registro para generar el PDF de su historia clinica</html>");
                    jf.setVisible(true);
                }

            }
        });

        this.jFPacientesMedico.btnNotasMedicas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFPacientesMedico.tablaPacientesMedico.getSelectedRow();
                if (fila >= 0) { //se selecciono un paciente
                    String num_exp = jFPacientesMedico.tablaPacientesMedico.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    String paciente = jFPacientesMedico.tablaPacientesMedico.getValueAt(fila, 1).toString();
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosPaciente(num_exp);
                    JFNotasMedicasPaciente notasMedicasPaciente = new JFNotasMedicasPaciente();
                    ControladorNotaMedicaPaciente controladorNotaMedicaPaciente = new ControladorNotaMedicaPaciente(notasMedicasPaciente, expediente, empleado, paciente);
                    notasMedicasPaciente.labelNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                    notasMedicasPaciente.txtPacienteNotasMed.setText(paciente);
                    notasMedicasPaciente.setVisible(true);

                    jFPacientesMedico.dispose();
                } else {
                    Mensaje jf = new Mensaje(jFPacientesMedico, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un registro para abrir sus notas médicas</html>");
                    jf.setVisible(true);
                }
            }

        });

        this.jFPacientesMedico.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFLogin.setVisible(true);
                jFPacientesMedico.dispose();
            }
        });

    }

    //Método que sirve para cargar los datos de los pacientes en la tabla
    public void cargarPacientesMedico(JFPacientesMedico jf) {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        jf.tablaPacientesMedico.setRowSorter(elQueOrdena);
        modelo.addColumn(" N U M . E X P E D I E N T E ");
        modelo.addColumn(" N O M B R E   P A C I E N T E ");

        List expedientesFichas = getExpedientesDelMedico(empleado.getIdEmpleado());

        if (expedientesFichas.size() > 0) {
            int i = 0;

            for (Iterator iterator = expedientesFichas.iterator(); iterator.hasNext();) {
                Object[] objects = (Object[]) iterator.next();
                Object[] filas = new Object[3];

                filas[0] = objects[0] + "             ";
                filas[1] = objects[1] + " " + objects[2] + " " + objects[3] + "                ";
                modelo.addRow(filas);
            }

            jf.tablaPacientesMedico.setModel(modelo);
            t = new Tabla(jf.tablaPacientesMedico);
            t.packColumn(1);
            t.packColumn(0);
        }

    }

    //Consulta para obtener la lista de los pacientes del medico
    public List getExpedientesDelMedico(int id) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List pacientes = null;
        try {
            s = sf.openSession();

            pacientes = s.createSQLQuery("select expediente.num_expediente, ANY_VALUE(paciente.nombre), "
                    + "ANY_VALUE(paciente.apellido_paterno), ANY_VALUE(paciente.apellido_materno) from expediente join paciente "
                    + "on expediente.id_paciente = paciente.id_paciente join respuestasencuesta on "
                    + "expediente.`id_expediente`=respuestasencuesta.`id_expediente` where respuestasencuesta.`id_empleado`=? "
                    + "group by expediente.num_expediente;")
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

}
