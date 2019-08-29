/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorPacientesPsicologo.getConnection;
import Modelo.AbstractJasperReports;
import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.Paciente;
import Modelo.Referencia;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.JFLogin;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
import Vista.TrabajadorSocial.JFReferencias;
import Vista.TrabajadorSocial.JFRegistrarReferenciaPaciente;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class ControladorPacientesReferencias {

    JFReferencias jFReferencias;
    Empleado empleado;
    Expediente expediente;
    Paciente paciente;

    public ControladorPacientesReferencias(JFReferencias jFReferencias, Empleado empleado, Expediente expediente, Paciente paciente) {
        this.jFReferencias = jFReferencias;
        this.empleado = empleado;
        this.expediente = expediente;
        this.paciente = paciente;
        cargarFichasPacientes(0, 0);
        llenarCombo(getPacientes());
        this.jFReferencias.cbPaciente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (jFReferencias.cbPaciente.getSelectedIndex() == 0) {
                    cargarFichasPacientes(0, 0);
                } else {
                    String[] paciente = jFReferencias.cbPaciente.getSelectedItem().toString().split(":");
                    int id = Integer.parseInt(paciente[0].toString());
                    cargarFichasPacientes(1, id);
                }

            }
        });

        this.jFReferencias.btnAgregarReferencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DatePicker dp = new DatePicker();
                Referencia referencia = new Referencia();
                JFRegistrarReferenciaPaciente registrarReferenciaPaciente;
                int id_referencia;
                List paciente = new ArrayList();
                Paciente obj_paciente = new Paciente();
                try {
                    int id_paciente;
                    int fila = jFReferencias.tablaPacientesReferencias.getSelectedRow();

                    //Se va a visualizar/editar la referencia
                    if (fila >= 0) { //Se selecciono una referencia
                        registrarReferenciaPaciente = new JFRegistrarReferenciaPaciente();
                        id_paciente = Integer.parseInt(jFReferencias.tablaPacientesReferencias.getValueAt(fila, 5).toString());
                        obj_paciente.setIdPaciente(id_paciente);
                        id_referencia = Integer.parseInt(jFReferencias.tablaPacientesReferencias.getValueAt(fila, 0).toString());
                        referencia.setIdReferencia(id_referencia);
                        referencia.setIdPaciente(id_paciente);
                        paciente = getInfoReferenciaPaciente(id_referencia);
                        for (Iterator iterator = paciente.iterator(); iterator.hasNext();) {
                            Object[] objects = (Object[]) iterator.next();
                            registrarReferenciaPaciente.txtNombrePaciente.setText(objects[0].toString());
                            registrarReferenciaPaciente.txtApPat.setText(objects[1].toString());
                            registrarReferenciaPaciente.txtApMat.setText(objects[2].toString());
                            if (objects[3].toString().equals("M")) {
                                registrarReferenciaPaciente.rbM.setSelected(true);
                            } else if (objects[3].toString().equals("F")) {
                                registrarReferenciaPaciente.rbF.setSelected(true);
                            }
                            registrarReferenciaPaciente.txtEdad.setText(objects[4].toString());
                            registrarReferenciaPaciente.pdFechaNac.setDate(dp.formatoStringtoLocalDate(objects[5].toString()));
                            registrarReferenciaPaciente.txtCalle.setText(objects[6].toString());
                            registrarReferenciaPaciente.txtNumero.setText(objects[7].toString());
                            registrarReferenciaPaciente.txtColonia.setText(objects[8].toString());
                            registrarReferenciaPaciente.txtCP.setText(objects[9].toString());

                            registrarReferenciaPaciente.cbEntidadFederativa.setSelectedItem(objects[10]);
                            registrarReferenciaPaciente.cbDelegacion.setSelectedItem(objects[11]);

                            registrarReferenciaPaciente.dpFechaReferencia.setDate(dp.formatoStringtoLocalDate(objects[12].toString()));
                            registrarReferenciaPaciente.horaReferencia.setTime(dp.formatoStringToDateTime(objects[13].toString()));
                            registrarReferenciaPaciente.txtDiagnostico.setText(objects[14].toString());

                            String empleado = objects[15] + ":" + objects[21] + " " + objects[22] + " " + objects[23];
                            registrarReferenciaPaciente.cbEmpleado.setSelectedItem(empleado);

                            String est = objects[16] + ":" + objects[24];
                            registrarReferenciaPaciente.cbEstablecimiento.setSelectedItem(est);

                            registrarReferenciaPaciente.txtMotivoEnvio.setText(objects[17].toString());
                            registrarReferenciaPaciente.txtMotivoReferencia.setText(objects[18].toString());
                            registrarReferenciaPaciente.txtObservaciones.setText(objects[19].toString());
                            if (objects[20].toString().equals("Si")) {
                                registrarReferenciaPaciente.jRadioButton2.setSelected(true);
                            } else {
                                registrarReferenciaPaciente.jRadioButton1.setSelected(true);
                            }
                        }
                        deshabilitarDatosPaciente(registrarReferenciaPaciente);
                        deshabilitarDatosEstablecimiento(registrarReferenciaPaciente);

                        registrarReferenciaPaciente.cbEmpleado.setEnabled(false);
                        //Podra editarse la fecha y hora de la referencia?
                        registrarReferenciaPaciente.horaReferencia.setEnabled(false);
                        registrarReferenciaPaciente.dpFechaReferencia.setEnabled(false);
                        registrarReferenciaPaciente.LabelReferencia.setText("Referencia");
                        ControladorReferencia controladorReferencia = new ControladorReferencia(registrarReferenciaPaciente, expediente, obj_paciente, empleado, referencia);
                        registrarReferenciaPaciente.setVisible(true);
                        jFReferencias.dispose();
                    }
                    // Se va a registrar una referencia a un paciente que ya existe
                    if (jFReferencias.cbPaciente.getSelectedIndex() != 0 && fila == -1) {
                        registrarReferenciaPaciente = new JFRegistrarReferenciaPaciente();
                        registrarReferenciaPaciente.LabelReferencia.setText("Registrar referencia");
                        //Agregar nueva referencia al paciente existente
                        String[] info_pac = jFReferencias.cbPaciente.getSelectedItem().toString().split(":");
                        id_paciente = Integer.parseInt(info_pac[0]);

                        paciente = getInfoPacienteParaReferenciar(id_paciente);
                        for (Iterator iterator = paciente.iterator(); iterator.hasNext();) {
                            Object[] objects = (Object[]) iterator.next();
                            registrarReferenciaPaciente.txtNombrePaciente.setText(objects[0].toString());
                            registrarReferenciaPaciente.txtApPat.setText(objects[1].toString());
                            registrarReferenciaPaciente.txtApMat.setText(objects[2].toString());
                            if (objects[3].toString().equals("M")) {
                                registrarReferenciaPaciente.rbM.setSelected(true);
                            } else if (objects[3].toString().equals("F")) {
                                registrarReferenciaPaciente.rbF.setSelected(true);
                            }
                            registrarReferenciaPaciente.txtEdad.setText(objects[4].toString());
                            registrarReferenciaPaciente.pdFechaNac.setDate(dp.formatoStringtoLocalDate(objects[5].toString()));
                            registrarReferenciaPaciente.txtCalle.setText(objects[6].toString());
                            registrarReferenciaPaciente.txtNumero.setText(objects[7].toString());
                            registrarReferenciaPaciente.txtColonia.setText(objects[8].toString());
                            registrarReferenciaPaciente.txtCP.setText(objects[9].toString());
                            
                            registrarReferenciaPaciente.cbEntidadFederativa.setSelectedItem(objects[10]);
                            registrarReferenciaPaciente.cbDelegacion.setSelectedItem(objects[11]);
                            deshabilitarDatosPaciente(registrarReferenciaPaciente);
                            referencia.setIdPaciente(id_paciente);
                            ControladorReferencia controladorReferencia = new ControladorReferencia(registrarReferenciaPaciente, expediente, obj_paciente, empleado, referencia);
                            registrarReferenciaPaciente.setVisible(true);
                            jFReferencias.dispose();
                        }
                    }
                    //Se va a registrar una referencia a un nuevo paciente
                    if (fila == -1 && jFReferencias.cbPaciente.getSelectedIndex() == 0) {
                        registrarReferenciaPaciente = new JFRegistrarReferenciaPaciente();
                        registrarReferenciaPaciente.LabelReferencia.setText("Registrar referencia - Paciente nuevo");

                        ControladorReferencia controladorReferencia = new ControladorReferencia(registrarReferenciaPaciente, expediente, obj_paciente, empleado, referencia);
                        registrarReferenciaPaciente.setVisible(true);
                        jFReferencias.dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.jFReferencias.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFLogin.setVisible(true);
                jFReferencias.dispose();
            }
        });
        this.jFReferencias.btn_FichasIdentificacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                FichaDeIdentificacion ficha = new FichaDeIdentificacion();
                ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                jFFichasIdentificacion.NumEmpleado.setText(empleado.getIdEmpleado() + "");
                jFFichasIdentificacion.NumEmpleado.setVisible(false);
                jFFichasIdentificacion.setVisible(true);
                jFReferencias.dispose();
            }
        });

        this.jFReferencias.btnPDFReferencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int id_paciente;
                int fila = jFReferencias.tablaPacientesReferencias.getSelectedRow();
                if (fila >= 0) { 
                    id_paciente = Integer.parseInt(jFReferencias.tablaPacientesReferencias.getValueAt(fila, 5).toString());
                    int id_referencia = Integer.parseInt(jFReferencias.tablaPacientesReferencias.getValueAt(fila, 0).toString());
                    String exp =" ";
                    if(obtenerNumeroExp(id_paciente)!=null){
                        exp = obtenerNumeroExp(id_paciente).getNumExpediente();
                    }
                    
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("id_referencia", id_referencia);
                    parametros.put("exp", exp);
                    parametros.put("fecha", LocalDate.now().toString());
                    parametros.put("hora", LocalTime.now().toString());
                    AbstractJasperReports.createEstudio(parametros, getConnection(), "src/Pdf/Referencia.jasper");
                    AbstractJasperReports.showViewer();
                }
            }
        });
    }

    public void cargarFichasPacientes(int tipo, int id_paciente) {
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla t;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        jFReferencias.tablaPacientesReferencias.setRowSorter(elQueOrdena);

        modelo.addColumn(" Id. Referencia");
        modelo.addColumn(" Fecha referencia");
        modelo.addColumn(" Nombre");
        modelo.addColumn(" Apellido paterno");
        modelo.addColumn(" Apellido materno");
        modelo.addColumn(" Id. Paciente ");

        List referencias = new ArrayList();

        //Todas las referencias
        if (tipo == 0) {
            referencias = getReferenciasTodas();
        } else if (tipo == 1) { //Un paciente en especifico
            referencias = getReferenciasPaciente(id_paciente);
        }

        for (Iterator iterator = referencias.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            Object[] filas = new Object[6];

            filas[0] = objects[0];
            filas[1] = objects[1];

            filas[2] = objects[3];
            filas[3] = objects[4];
            filas[4] = objects[5];
            filas[5] = objects[2];
            modelo.addRow(filas);
        }

        jFReferencias.tablaPacientesReferencias.setModel(modelo);
        t = new Tabla(jFReferencias.tablaPacientesReferencias);
        t.packColumn(1);
        t.packColumn(3);
        t.packColumn(4);

    }

    public List getReferenciasTodas() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List todas_referencias = new ArrayList();
        try {
            s = sf.openSession();
            todas_referencias = s.createSQLQuery("select referencia.id_referencia, referencia.fecha, paciente.id_paciente, paciente.nombre,  paciente.apellido_paterno, paciente.apellido_materno from paciente join referencia on paciente.id_paciente = referencia.id_paciente;")
                    .addScalar("id_referencia").addScalar("fecha").addScalar("id_paciente").addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno")
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, e);
        }
        return todas_referencias;
    }

    public Expediente obtenerNumeroExp(int id){
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        Expediente exp = new Expediente();
        try {
            s = sf.openSession();
            exp =(Expediente) s.createQuery("from Expediente where idPaciente=?").setParameter(0, id).uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, e);
        }
        return exp;
    }
    
    public List<Paciente> getPacientes() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List<Paciente> allPacient = new ArrayList<>();
        try {
            s = sf.openSession();
            allPacient = s.createQuery("from Paciente").list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, e);
        }
        return allPacient;
    }

    public List getReferenciasPaciente(int paciente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List todas_referencias = new ArrayList();
        try {
            s = sf.openSession();
            todas_referencias = s.createSQLQuery("select referencia.id_referencia, referencia.fecha, paciente.id_paciente, paciente.nombre, "
                    + "paciente.apellido_paterno, paciente.apellido_materno from paciente join referencia on paciente.id_paciente = referencia.id_paciente "
                    + "where paciente.id_paciente=?;")
                    .addScalar("id_referencia").addScalar("fecha").addScalar("id_paciente").addScalar("nombre").addScalar("apellido_paterno")
                    .addScalar("apellido_materno").setParameter(0, paciente)
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorPacientesReferencias.class.getName()).log(Level.SEVERE, null, e);
        }
        return todas_referencias;
    }

    public List getInfoPacienteParaReferenciar(int id_paciente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List paciente = null;
        try {
            s = sf.openSession();
            paciente = s.createSQLQuery("select paciente.nombre, paciente.apellido_paterno, paciente.apellido_materno, paciente.sexo, "
                    + "DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(paciente.`fecha_nac`)), '%Y')+0 AS edad, paciente.fecha_nac, domicilio.`calle`, "
                    + "domicilio.numero, domicilio.colonia, domicilio.codigo_postal, domicilio.`entidad_localidad`, domicilio.`delegacion_municipio` "
                    + "FROM paciente join domicilio on paciente.id_domicilio=domicilio.id_domicilio where paciente.id_paciente=?;")
                    .addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno").addScalar("sexo").addScalar("edad").addScalar("fecha_nac")
                    .addScalar("calle").addScalar("numero").addScalar("colonia").addScalar("codigo_postal").addScalar("entidad_localidad")
                    .addScalar("delegacion_municipio").setParameter(0, id_paciente)
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return paciente;
    }

    public List getInfoReferenciaPaciente(int id_referencia) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List paciente = null;
        try {
            s = sf.openSession();
            paciente = s.createSQLQuery("select paciente.nombre, paciente.apellido_paterno, paciente.apellido_materno, paciente.sexo, "
                    + "DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(paciente.`fecha_nac`)), '%Y')+0 AS edad, paciente.fecha_nac, domicilio.`calle`, "
                    + "domicilio.numero, domicilio.colonia, domicilio.codigo_postal, domicilio.`entidad_localidad`, domicilio.`delegacion_municipio`, referencia.`fecha`, "
                    + "referencia.hora, referencia.`diagnostico_clinico`, referencia.`id_empleado`, referencia.`id_establecimiento_serefiere`, "
                    + "referencia.`motivo_de_envio`, referencia.`motivo_de_referencia`, referencia.`observaciones`, referencia.`urgencia`, "
                    + " empleado.nombre as nom_emp, empleado.apellido_paterno as ap_emp, empleado.apellido_materno as am_emp, establecimiento.nombre as est FROM "
                    + "paciente join domicilio on paciente.id_domicilio=domicilio.id_domicilio join referencia on referencia.id_paciente=paciente.id_paciente "
                    + " join empleado on empleado.id_empleado = referencia.id_empleado join establecimiento on establecimiento.id_establecimiento = "
                    + "referencia.id_establecimiento_serefiere where referencia.id_referencia=?")
                    .addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno").addScalar("sexo").addScalar("edad").addScalar("fecha_nac")
                    .addScalar("calle").addScalar("numero").addScalar("colonia").addScalar("codigo_postal").addScalar("entidad_localidad")
                    .addScalar("delegacion_municipio").addScalar("fecha").addScalar("hora").addScalar("diagnostico_clinico").addScalar("id_empleado")
                    .addScalar("id_establecimiento_serefiere").addScalar("motivo_de_envio").addScalar("motivo_de_referencia").addScalar("observaciones")
                    .addScalar("urgencia").addScalar("nom_emp").addScalar("ap_emp").addScalar("am_emp").addScalar("est")
                    .setParameter(0, id_referencia)
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return paciente;
    }

    public void llenarCombo(List<Paciente> lista) {
        for (int i = 0; i < lista.size(); i++) {
            jFReferencias.cbPaciente.addItem(lista.get(i).getIdPaciente() + ":" + lista.get(i).getNombre() + " " + lista.get(i).getApellidoPaterno() + " " + lista.get(i).getApellidoMaterno());
        }
    }

    public void deshabilitarDatosPaciente(JFRegistrarReferenciaPaciente registrarReferenciaPaciente) {
        registrarReferenciaPaciente.txtApMat.setEditable(false);
        registrarReferenciaPaciente.txtApPat.setEditable(false);
        registrarReferenciaPaciente.txtCP.setEditable(false);
        registrarReferenciaPaciente.txtCalle.setEditable(false);
        registrarReferenciaPaciente.txtColonia.setEditable(false);
        registrarReferenciaPaciente.txtEdad.setEditable(false);
        registrarReferenciaPaciente.txtNombrePaciente.setEditable(false);
        registrarReferenciaPaciente.txtNumero.setEditable(false);
        registrarReferenciaPaciente.rbF.setEnabled(false);
        registrarReferenciaPaciente.rbM.setEnabled(false);
        registrarReferenciaPaciente.cbDelegacion.setEnabled(false);
        registrarReferenciaPaciente.cbEntidadFederativa.setEnabled(false);
        registrarReferenciaPaciente.pdFechaNac.setEnabled(false);
    }

    public void deshabilitarDatosEstablecimiento(JFRegistrarReferenciaPaciente registrarReferenciaPaciente) {
        registrarReferenciaPaciente.txtCalleNumeroEst.setEditable(false);
        registrarReferenciaPaciente.txtColoniaEst.setEditable(false);
        registrarReferenciaPaciente.txtEntidadFererativaEst.setEditable(false);
        registrarReferenciaPaciente.txtMunicipioEst.setEditable(false);
        registrarReferenciaPaciente.txtTelefonoEst.setEditable(false);
        registrarReferenciaPaciente.txtcPEst.setEditable(false);
        registrarReferenciaPaciente.txtResponsable.setEditable(false);

        registrarReferenciaPaciente.cbEstablecimiento.setEnabled(false);
    }
}
