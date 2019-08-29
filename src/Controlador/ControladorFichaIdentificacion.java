/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorPacientesPsicologo.getConnection;
import Modelo.AbstractJasperReports;
import Modelo.Connection;
import Modelo.DatePicker;
import Modelo.Domicilio;
import Modelo.Empleado;
import Modelo.Expediente;

import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.NotaRescate;
import Modelo.NotaTratamiento;
import Modelo.Paciente;
import Modelo.RespuestasEncuesta;
import Modelo.Sesion;

import Modelo.Tabla;
import Vista.JFLogin;
import Vista.Mensaje;
import Vista.TrabajadorSocial.*;
import Vista.TrabajadorSocial.JFFichaIdentificacionPaciente;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ControladorFichaIdentificacion {

    JFPacientesTrabajadorSocial jFFichasIdentificacion;
    FichaDeIdentificacion ficha;
    Empleado empleado_main;

    public ControladorFichaIdentificacion(JFPacientesTrabajadorSocial jFFichasIdentificacion, FichaDeIdentificacion ficha, Empleado empleado) {
        this.jFFichasIdentificacion = jFFichasIdentificacion;
        this.ficha = ficha;
        this.empleado_main = empleado;
        llenarCombo(getPacientes());
        // System.out.println(empleado_main.getIdEmpleado());
        cargarFichasPacientes(jFFichasIdentificacion);

        this.jFFichasIdentificacion.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFFichasIdentificacion.dispose();

                jFLogin.setVisible(true);
            }
        });

        this.jFFichasIdentificacion.btn_Referencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFReferencias jReferencias = new JFReferencias();
                Expediente expediente = new Expediente();
                Paciente paciente = new Paciente();
                ControladorPacientesReferencias controladorPacientesReferencias = new ControladorPacientesReferencias(jReferencias, empleado_main, expediente, paciente);
                jReferencias.setVisible(true);
                jFFichasIdentificacion.dispose();
            }
        });

        this.jFFichasIdentificacion.btnNuevoPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFFichaIdentificacionPaciente jsAgregarFicha = new JFFichaIdentificacionPaciente();
                ficha.setIdEmpleado(empleado_main.getIdEmpleado());
                Expediente expediente = new Expediente();
                try {
                    ControladorAccionesFichaIdentificacion controladorAccionesFichaIdentificacion = new ControladorAccionesFichaIdentificacion(jsAgregarFicha, ficha, expediente);

                    jsAgregarFicha.setVisible(true);

                    jFFichasIdentificacion.dispose();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        this.jFFichasIdentificacion.btnEstudioSocialSocioeconomico.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                String num_exp;
                if (fila >= 0) {
                    num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosExpedientePaciente(num_exp);
                    ficha.setIdEmpleado(empleado_main.getIdEmpleado());
                    jFFichasIdentificacion.dispose();
                    JFEstudioSocialSocioEconomico jFEstudioSocialSocioEconomico = new JFEstudioSocialSocioEconomico();
                    ControladorEstudioSocialSocioEconomico controllerEstudioSocialSocioEconomico = new ControladorEstudioSocialSocioEconomico(jFEstudioSocialSocioEconomico, empleado_main, expediente, ficha);
                    jFEstudioSocialSocioEconomico.setVisible(true);
                } else {
                    Mensaje msg = new Mensaje(jFFichasIdentificacion, true);
                    msg.msg.setText("<html>Debe selecionar un paciente para abrir su estudio social socioeconómico.</htlm>");
                    msg.setVisible(true);
                }
            }

        });

        this.jFFichasIdentificacion.btnNotasRescate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFNotasRescatePaciente notasRescatePaciente = new JFNotasRescatePaciente();
                NotaRescate notaRescate = new NotaRescate();
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                if (fila >= 0) {
                    String num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosExpedientePaciente(num_exp);
                    notaRescate.setIdEmpleado(empleado_main.getIdEmpleado());

                    String nombre_paciente = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 1).toString();
                    notasRescatePaciente.txtPacienteRescate.setText(nombre_paciente);
                    notasRescatePaciente.labelNumExp.setText("Núm. Expediente: " + num_exp);
                    ControladorNotaRescate controladorNotaRescate = new ControladorNotaRescate(notasRescatePaciente, expediente, empleado_main, nombre_paciente, notaRescate);
                    notasRescatePaciente.setVisible(true);
                    jFFichasIdentificacion.dispose();
                } else {
                    Mensaje msg = new Mensaje(jFFichasIdentificacion, true);
                    msg.msg.setText("<html>Debe selecionar un paciente para abrir sus notas de rescate.</htlm>");
                    msg.setVisible(true);
                }

            }
        });

        this.jFFichasIdentificacion.btnPdfEstSocSoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                String ts = empleado_main.getNombre() + " " + empleado_main.getApellidoPaterno() + " " + empleado_main.getNombre();
                if (fila >= 0) {
                    String num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosExpedientePaciente(num_exp);
                    String nombre_paciente = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 1).toString();
                    ManejadorEncuestas me = new ManejadorEncuestas();
                    List<RespuestasEncuesta> lista = me.obtenerRespuestasEncuestaEstudioSocialSocioeconomico(expediente);
                    Map<String, Object> parametros = me.cargarRespuestas(lista);
                    parametros.put("trabajadorsocial", ts);
                    parametros.put("cedula", empleado_main.getCedula());
                    parametros.put("fecha_act", LocalDate.now().toString());
                    parametros.put("paciente", num_exp);
                    AbstractJasperReports.createEstudio(parametros, getConnection(), "src/Pdf/estudio_social.jasper");
                    AbstractJasperReports.showViewer();
                } else {
                    Mensaje msg = new Mensaje(jFFichasIdentificacion, true);
                    msg.msg.setText("<html>Debe selecionar un paciente para abrir su estudio social socioeconómico.</htlm>");
                    msg.setVisible(true);
                }
            }
        });
        
        /*this.jFFichasIdentificacion.btnNotasPDF.addMouseListener(new MouseAdapter() {
            @Override
            /// Pendiente
            public void mousePressed(MouseEvent e) {
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                String ts = empleado_main.getNombre() + " " + empleado_main.getApellidoPaterno() + " " + empleado_main.getNombre();
                if (fila >= 0) {
                    String num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    Expediente expediente = new Expediente();
                    expediente = obtenerDatosExpedientePaciente(num_exp);
                    Paciente paciente = obtenerDatosPaciente(expediente.getIdPaciente());
                    Domicilio dom = obtenerDomicilioPac(paciente.getIdDomicilio());
                    String nombre_paciente = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 1).toString();
                    ManejadorEncuestas me = new ManejadorEncuestas();
                    List<RespuestasEncuesta> lista = me.obtenerRespuestasEncuestaEstudioSocialSocioeconomico(expediente);
                    Map<String, Object> parametros = me.cargarRespuestas(lista);
                    parametros.put("trabajadorsocial", ts);
                    parametros.put("psicologo", obtenerPsicologo(expediente.getIdExpediente()));
                    parametros.put("fecha_nota", LocalDate.now().toString());
                    parametros.put("exp", expediente.getIdExpediente());
                    parametros.put("usuario",nombre_paciente);
                    parametros.put("telefono_ubi", paciente.getTelefono());
                    parametros.put("num_exp", num_exp);
                    String direccion = dom.getCalle()+" "+dom.getNumero()+" "+dom.getColonia()+" "+dom.getCodigoPostal();
                    parametros.put("direccion", direccion);
                    parametros.put("municipio", dom.getDelegacionMunicipio());
                    parametros.put("entidad", dom.getEntidadLocalidad());
                    
                    AbstractJasperReports.createNotaMedica(parametros, Connection.getConnection(), "src/Pdf/notarescate.jasper");
                    AbstractJasperReports.showViewer();
                } else {
                    Mensaje msg = new Mensaje(jFFichasIdentificacion, true);
                    msg.msg.setText("<html>Debe selecionar un paciente para generar las notas de rescate.</htlm>");
                    msg.setVisible(true);
                }
            }
        });*/

        this.jFFichasIdentificacion.btnPdfFicha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Map<String, Object> parametros = new HashMap<>();
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                String num_exp;

                if (fila >= 0) {
                    num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");

                    parametros.put("fecha_actual", LocalDate.now().toString());
                    parametros.put("paciente", new String(num_exp));

                    AbstractJasperReports.createFichaIdentificacion(parametros, Connection.getConnection(), "src/Pdf/ficha_identificacion.jasper");

                    
                    AbstractJasperReports.showViewer();
                }
            }
        });

        this.jFFichasIdentificacion.btnFichaIdentificacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFFichasIdentificacion.tablaPacientes.getSelectedRow();
                String num_exp;
                Expediente expediente = new Expediente();
                JFFichaIdentificacionPaciente jsAgregarFicha = new JFFichaIdentificacionPaciente();
                if (fila >= 0) {
                    num_exp = jFFichasIdentificacion.tablaPacientes.getValueAt(fila, 0).toString().replaceAll(" ", "");
                    expediente = obtenerDatosExpedientePaciente(num_exp);
                    ficha.setIdEmpleado(empleado_main.getIdEmpleado());

                    jsAgregarFicha.etiquetaAccion.setText("Actualizar Ficha de identificación - Datos paciente");

                } else {
                    //Esta seleccionado un paciente del combobox

                    String[] pac = jFFichasIdentificacion.cbPaciente.getSelectedItem().toString().split(":");
                    int id_paciente = Integer.parseInt(pac[0].toString());
                    Paciente paciente = obtenerPaciente(id_paciente);
                    Domicilio domicilio = obtenerDomicilioPac(paciente.getIdDomicilio());
                    jsAgregarFicha.txtNombrePaciente.setText(paciente.getNombre());
                    jsAgregarFicha.txtApPat.setText(paciente.getApellidoPaterno());
                    jsAgregarFicha.txtApMa.setText(paciente.getApellidoMaterno());
                    
                    if (paciente.getSexo().equals("F")) {
                        jsAgregarFicha.rbFemenino.setSelected(true);
                    } else if (paciente.getSexo().equals("M")) {
                        jsAgregarFicha.rbMasc.setSelected(true);
                    }
                    DatePicker dp = new DatePicker();
                    jsAgregarFicha.datePickerFechaNac.setDate(dp.formatoStringtoLocalDate(paciente.getFechaNac().toString()));
                    jsAgregarFicha.cbMunicipioDomicilioActual.setSelectedItem(domicilio.getDelegacionMunicipio());
                    jsAgregarFicha.cbEntidadLocalidadDomicilioActual.setSelectedItem(domicilio.getEntidadLocalidad());
                    jsAgregarFicha.txtCalle.setText(domicilio.getCalle());
                    jsAgregarFicha.txtNumero.setText(domicilio.getNumero());
                    jsAgregarFicha.txtColonia.setText(domicilio.getColonia());
                    jsAgregarFicha.txtCP.setText(domicilio.getCodigoPostal());
                    
                    jsAgregarFicha.etiquetaAccion.setText("Registro de ficha de identificación - Número de paciente: "+id_paciente);
                }
                try {
                    ControladorAccionesFichaIdentificacion controladorAccionesFichaIdentificacion = new ControladorAccionesFichaIdentificacion(jsAgregarFicha, ficha, expediente);
                    jsAgregarFicha.setVisible(true);
                    jFFichasIdentificacion.dispose();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    public Expediente obtenerDatosExpedientePaciente(String num_exp) {
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
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return expediente;
    }
    
    public Paciente obtenerDatosPaciente(int id) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Paciente paciente = new Paciente();
        try {
            s = sf.openSession();
            paciente = (Paciente) s.createQuery("from Paciente e where e.idPaciente=?")
                    .setParameter(0, id).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return paciente;
        } catch (Exception e) {
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
            return paciente;
        }
        
    }
    
    public String obtenerPsicologo(int id_exp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        RespuestasEncuesta info_rp = new RespuestasEncuesta();
        try {
            s = sf.openSession();
            info_rp = (RespuestasEncuesta) s.createQuery("from RespuestasEncuesta rp where rp.idExpediente=? and rp.idPregunta=269")
                    .setParameter(0, id_exp).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return obtenerNomPsicologo(info_rp.getIdEmpleado());
        } catch (Exception e) {
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
            return "";
        }
    }
    
    public String obtenerNomPsicologo(int id_emp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Empleado empleado = new Empleado();
        String psc="";
        try {
            s = sf.openSession();
            empleado = (Empleado) s.createQuery("from Empleado e where e.idEmpleado=?")
                    .setParameter(0, id_emp).uniqueResult();
            psc = empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno();
            s.flush();
            s.clear();
            s.close();
            return psc;
        } catch (Exception e) {
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return psc;
    }

    //Metodo que sirve para cargar los datos de los pacientes en la tabla
    public void cargarFichasPacientes(JFPacientesTrabajadorSocial jf) {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        jf.tablaPacientes.setRowSorter(elQueOrdena);
        modelo.addColumn(" N U M . E X P E D I E N T E ");
        modelo.addColumn(" N O M B R E   P A C I E N T E ");

        List expedientesFichas = expedientesFichas = getExpedientesFichas();

        int i = 0;

        for (Iterator iterator = expedientesFichas.iterator(); iterator.hasNext();) {
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

    //Consulta para obtener la lista de los pacientes del trabajador social
    public List getExpedientesFichas() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List pacientes = null;
        try {
            s = sf.openSession();

            pacientes = s.createSQLQuery("select expediente.num_expediente, paciente.nombre,  paciente.apellido_paterno, paciente.apellido_materno"
                    + " from paciente join expediente on "
                    + "paciente.id_paciente=expediente.id_paciente join fichadeidentificacion on "
                    + "fichadeidentificacion.id_ficha_de_identificacion=expediente.id_ficha_identificacion "
                    + "where fichadeidentificacion.id_empleado=?")
                    .addScalar("num_expediente").addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno")
                    .setParameter(0, empleado_main.getIdEmpleado())
                    .list();

            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Error aqui:c");
        }
        return pacientes;
    }

    public Paciente obtenerPaciente(int id_pac) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Paciente paciente = new Paciente();
        try {
            s = sf.openSession();
            paciente = (Paciente) s.createQuery("from Paciente p where p.idPaciente=?")
                    .setParameter(0, id_pac).uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return paciente;
    }
    
    public Domicilio obtenerDomicilioPac(int id_dom) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Domicilio domicilio = new Domicilio();
        try {
            s = sf.openSession();
            domicilio = (Domicilio) s.createQuery("from Domicilio dom where dom.idDomicilio=?")
                    .setParameter(0, id_dom).uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorFichaIdentificacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return domicilio;
    }

    public List getPacientes() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List allPacient = null;
        try {
            s = sf.openSession();
            allPacient = s.createSQLQuery("select p.id_paciente, p.nombre, p.apellido_paterno, p.apellido_materno from Paciente as p left join Expediente as e on p.id_paciente=e.id_paciente where e.id_paciente is null")
                    .addScalar("id_paciente").addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno").list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, e);
        }
        return allPacient;
    }

    public void llenarCombo(List lista) {
        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            jFFichasIdentificacion.cbPaciente.addItem(objects[0] + ":" + objects[1] + " " + objects[2] + " " + objects[3]);
        }
    }

}
