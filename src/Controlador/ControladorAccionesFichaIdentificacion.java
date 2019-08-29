/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Domicilio;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.NotaMedica;
import Modelo.NotaTratamiento;
import Modelo.Paciente;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import Vista.TrabajadorSocial.JFFichaIdentificacionPaciente;
import Vista.TrabajadorSocial.JFFichaIdentificacionPrimerContacto;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesFichaIdentificacion {

    JFFichaIdentificacionPaciente jFFichaIdentificacionPaciente;
    FichaDeIdentificacion ficha;
    Expediente expediente;

    public ControladorAccionesFichaIdentificacion(JFFichaIdentificacionPaciente jFFichaIdentificacionPaciente, FichaDeIdentificacion ficha, Expediente expediente) throws ParseException {
        this.jFFichaIdentificacionPaciente = jFFichaIdentificacionPaciente;
        this.ficha = ficha;
        this.expediente = expediente;
        validaInputs();
        this.jFFichaIdentificacionPaciente.btnCancelarFichaPac.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(ficha.getIdEmpleado());

                ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                jFFichasIdentificacion.NumEmpleado.setText(ficha.getIdEmpleado() + "");
                jFFichasIdentificacion.NumEmpleado.setVisible(false);
                jFFichasIdentificacion.setVisible(true);
                jFFichaIdentificacionPaciente.dispose();
            }

        });

        if (this.jFFichaIdentificacionPaciente.etiquetaAccion.getText().equals("Registro de ficha de identificación - Datos paciente")) {
            this.jFFichaIdentificacionPaciente.rbMasc.setSelected(true);
            this.jFFichaIdentificacionPaciente.rbLINo.setSelected(true);
            this.jFFichaIdentificacionPaciente.rbMigranteNo.setSelected(true);

            this.jFFichaIdentificacionPaciente.btnSiguiente.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    DatePicker dp = new DatePicker();
                    //Expediente expediente = new Expediente();
                    FichaDeIdentificacion ficha_identi = new FichaDeIdentificacion();
                    Paciente paciente = new Paciente();
                    Domicilio domicilio = new Domicilio();

                    try {
                        expediente.setFechaAperturaExpediente(dp.formatoStringToDate(LocalDate.now().toString()));
                        expediente.setNumExpediente(jFFichaIdentificacionPaciente.txtNum_Expediente.getText());
                        expediente.setIntervencionId(null);
                        if (validaDatosForm(jFFichaIdentificacionPaciente)) {
                            domicilio = getDataAddresForm(jFFichaIdentificacionPaciente);
                            paciente = getDataPacientForm(jFFichaIdentificacionPaciente);
                            ficha_identi = getFichaIdenForm(jFFichaIdentificacionPaciente);
                            ficha_identi.setIdEmpleado(ficha.getIdEmpleado());
                            if (aperturaExpediente(domicilio, paciente, expediente)) {
                                Vista.TrabajadorSocial.JFFichaIdentificacionPrimerContacto jf = new JFFichaIdentificacionPrimerContacto();

                                jf.labelNumExp.setText("Número de expediente: " + expediente.getNumExpediente());
                                jf.etiquetaAccion.setText("Registro de ficha de identificación - Datos del primer contacto del paciente");
                                ControladorDatosPrimerContacto(jf, expediente, ficha_identi);
                                jf.setVisible(true);
                                jFFichaIdentificacionPaciente.dispose();

                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesFichaIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
        } else if (this.jFFichaIdentificacionPaciente.etiquetaAccion.getText().equals("Actualizar Ficha de identificación - Datos paciente")) {
            //Aqui viene lo bueno, la actualizacion D:
            Paciente paciente = new Paciente();
            paciente = getPacient(expediente.getIdPaciente());
            FichaDeIdentificacion ficha_i = getFichaIdentificacion(expediente.getIdFichaIdentificacion());
            Domicilio domicilio = new Domicilio();
            domicilio = getDomicilio(paciente.getIdDomicilio());
            cargarInformacionEnForm(paciente, expediente, ficha_i, domicilio);
            jFFichaIdentificacionPaciente.txtNum_Expediente.setEditable(false);
            this.jFFichaIdentificacionPaciente.btnSiguiente.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    DatePicker dp = new DatePicker();

                    FichaDeIdentificacion ficha_identi = new FichaDeIdentificacion();
                    Paciente paciente = new Paciente();
                    Domicilio domicilio = new Domicilio();
                    try {
                        if (validaDatosForm(jFFichaIdentificacionPaciente)) {
                            domicilio = getDataAddresForm(jFFichaIdentificacionPaciente);
                            paciente = getDataPacientForm(jFFichaIdentificacionPaciente);

                            Paciente p = getPacient(expediente.getIdPaciente());

                            paciente.setIdPaciente(expediente.getIdPaciente());
                            paciente.setIdDomicilio(p.getIdDomicilio());
                            domicilio.setIdDomicilio(p.getIdDomicilio());

                            FichaDeIdentificacion f = new FichaDeIdentificacion();
                            f = getFichaIdentificacion(expediente.getIdFichaIdentificacion());

                            ficha_identi = getFichaIdenForm(jFFichaIdentificacionPaciente);
                            ficha_identi.setIdFichaDeIdentificacion(expediente.getIdFichaIdentificacion());
                            ficha_identi.setIdDomicilioContacto(f.getIdDomicilioContacto());
                            ficha_identi.setIdEmpleado(f.getIdEmpleado());
                            ficha_identi.setNombreContacto(f.getNombreContacto());
                            ficha_identi.setApMaternoContacto(f.getApMaternoContacto());
                            ficha_identi.setApPaternoContacto(f.getApPaternoContacto());
                            ficha_identi.setParentesco(f.getParentesco());
                            ficha_identi.setTelefonoContacto(f.getTelefonoContacto());
                            ficha_identi.setCelularContacto(f.getCelularContacto());

                            //ficha_identi.setIdEmpleado(ficha.getIdEmpleado());
                            if (updatePaciente(paciente) && updateDomicilio(domicilio)) {
                                Vista.TrabajadorSocial.JFFichaIdentificacionPrimerContacto jf = new JFFichaIdentificacionPrimerContacto();

                                jf.labelNumExp.setText("Número de expediente: " + expediente.getNumExpediente());
                                jf.etiquetaAccion.setText("Actualizar ficha de identificación - Datos del primer contacto del paciente");
                                ControladorDatosPrimerContacto(jf, expediente, ficha_identi);
                                jf.setVisible(true);
                                jFFichaIdentificacionPaciente.dispose();

                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorAccionesFichaIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } else {
            jFFichaIdentificacionPaciente.rbMigranteSi.setSelected(true);
            jFFichaIdentificacionPaciente.rbLINo.setSelected(true);
            //Paciente ya existe, debe solo crear expediente y actualizar paciente y dom
            this.jFFichaIdentificacionPaciente.btnSiguiente.addMouseListener(new MouseAdapter() {
                @Override

                public void mousePressed(MouseEvent e) {

                    DatePicker dp = new DatePicker();
                    String[] pac = jFFichaIdentificacionPaciente.etiquetaAccion.getText().split(":");
                    int id_pac = Integer.parseInt(pac[1].toString().replace(" ", ""));
                    int id_dom = obtenerPaciente(id_pac).getIdDomicilio();
                    FichaDeIdentificacion ficha_identi = new FichaDeIdentificacion();
                    Paciente paciente = new Paciente();
                    Domicilio domicilio = new Domicilio();
                    try {
                        expediente.setFechaAperturaExpediente(dp.formatoStringToDate(LocalDate.now().toString()));
                        expediente.setNumExpediente(jFFichaIdentificacionPaciente.txtNum_Expediente.getText());
                        expediente.setIntervencionId(null);
                        expediente.setIdPaciente(id_pac);

                        if (validaDatosForm(jFFichaIdentificacionPaciente)) {
                            domicilio = getDataAddresForm(jFFichaIdentificacionPaciente);
                            paciente = getDataPacientForm(jFFichaIdentificacionPaciente);
                            paciente.setIdPaciente(id_pac);

                            paciente.setIdDomicilio(id_dom);
                            domicilio.setIdDomicilio(id_dom);
                            ficha_identi = getFichaIdenForm(jFFichaIdentificacionPaciente);
                            ficha_identi.setIdEmpleado(ficha.getIdEmpleado());
                            if (updatePaciente(paciente) && updateDomicilio(domicilio) && addExpediente(expediente)) {
                                Vista.TrabajadorSocial.JFFichaIdentificacionPrimerContacto jf = new JFFichaIdentificacionPrimerContacto();
                                jf.labelNumExp.setText("Número de expediente: " + expediente.getNumExpediente());
                                jf.etiquetaAccion.setText("Registro de ficha de identificación - Datos del primer contacto del paciente");
                                ControladorDatosPrimerContacto(jf, expediente, ficha_identi);
                                jf.setVisible(true);
                                jFFichaIdentificacionPaciente.dispose();
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesFichaIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

    }

    public boolean updateDomicilio(Domicilio domicilio) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(domicilio);
            //session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen()) {
                session.getTransaction().rollback();
            }
            System.out.println("erroorrsss 1" + ex.getMessage());
            return false;
        }
    }

    public boolean updateFicha(FichaDeIdentificacion ficha) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(ficha);
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

    public boolean updatePaciente(Paciente paciente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(paciente);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (Exception ex) {
            if (session.isOpen()) {
                session.getTransaction().rollback();
            }
            System.out.println("erroorrsss" + ex.getMessage());
            return false;
        }
    }

    //Metodo que carga la información de la primer pantalla (datos del paciente) en el fomurlario
    public void cargarInformacionEnForm(Paciente paciente, Expediente expediente, FichaDeIdentificacion ficha, Domicilio domicilio) throws ParseException {
        //Datos expediente
        jFFichaIdentificacionPaciente.txtNum_Expediente.setText(expediente.getNumExpediente());

        //Datos del paciente
        jFFichaIdentificacionPaciente.txtNombrePaciente.setText(paciente.getNombre());
        jFFichaIdentificacionPaciente.txtApMa.setText(paciente.getApellidoMaterno());
        jFFichaIdentificacionPaciente.txtApPat.setText(paciente.getApellidoPaterno());
        if (paciente.getSexo().equals("M")) {
            jFFichaIdentificacionPaciente.rbMasc.setSelected(true);
        } else {
            jFFichaIdentificacionPaciente.rbFemenino.setSelected(true);
        }
        DatePicker dp = new DatePicker();

        jFFichaIdentificacionPaciente.datePickerFechaNac.setText(dp.StringToString(paciente.getFechaNac()) + "");

        jFFichaIdentificacionPaciente.cbEdoCivil.setSelectedItem(paciente.getEstadoCivil());

        jFFichaIdentificacionPaciente.cbMunicipioLugarNacimiento.setSelectedItem(paciente.getMunicipioNacimiento());

        //jFFichaIdentificacionPaciente.txtMunicipioNac.setText(paciente.getMunicipioNacimiento());
        jFFichaIdentificacionPaciente.txtEstadoNac.setText(paciente.getEstadoNacimiento());
        jFFichaIdentificacionPaciente.txtRFC.setText(paciente.getRfc());
        jFFichaIdentificacionPaciente.txtCurp.setText(paciente.getCurp());
        jFFichaIdentificacionPaciente.txtTelefono.setText(paciente.getTelefono());
        jFFichaIdentificacionPaciente.txtCelular.setText(paciente.getCelular());

        jFFichaIdentificacionPaciente.cbOcupacion.setSelectedItem(paciente.getOcupacion());

        //Domicilio del paciente
        jFFichaIdentificacionPaciente.txtCalle.setText(domicilio.getCalle());
        jFFichaIdentificacionPaciente.txtNumero.setText(domicilio.getNumero());
        jFFichaIdentificacionPaciente.txtColonia.setText(domicilio.getColonia());
        jFFichaIdentificacionPaciente.txtCP.setText(domicilio.getCodigoPostal());

        jFFichaIdentificacionPaciente.cbMunicipioDomicilioActual.setSelectedItem(domicilio.getDelegacionMunicipio());
        jFFichaIdentificacionPaciente.cbEntidadLocalidadDomicilioActual.setSelectedItem(domicilio.getEntidadLocalidad());

        jFFichaIdentificacionPaciente.txtEstadoDom.setText(domicilio.getEstado());

        //Ficha de identificacion
        jFFichaIdentificacionPaciente.cbReferidoPor.setSelectedItem(ficha.getReferidoPor());
        jFFichaIdentificacionPaciente.cbReligion.setSelectedItem(ficha.getReligion());
        jFFichaIdentificacionPaciente.cbGrupoSanguineo.setSelectedItem(ficha.getGrupoSanguineo());

        if (ficha.getHablaLenguaIndigena().equals("Si")) {
            jFFichaIdentificacionPaciente.rbLISi.setSelected(true);
        } else {
            jFFichaIdentificacionPaciente.rbLINo.setSelected(true);
        }
        jFFichaIdentificacionPaciente.txtLenguaIndigena.setText(ficha.getLenguaIndigena());
        if (ficha.getMigrante().equals("Si")) {
            jFFichaIdentificacionPaciente.rbMigranteSi.setSelected(true);
        }
        if (ficha.getMigrante().equals("No")) {
            jFFichaIdentificacionPaciente.rbMigranteNo.setSelected(true);
        }

        jFFichaIdentificacionPaciente.cbDerechohabiencia.setSelectedItem(ficha.getDerechohabiencia());

        jFFichaIdentificacionPaciente.txtNumDerechohabiencia.setText(ficha.getNumeroDerechohabiencia());
        jFFichaIdentificacionPaciente.txtMotivoConsulta.setText(ficha.getMotivoConsulta());

        jFFichaIdentificacionPaciente.cbEscolaridad.setSelectedItem(ficha.getEscolaridad());

    }

    public void cargarInformacionEnFormPrimercontacto(JFFichaIdentificacionPrimerContacto jf, FichaDeIdentificacion ficha, Domicilio domicilio) {
        jf.txtNombreContacto.setText(ficha.getNombreContacto());
        jf.txtApPatContacto.setText(ficha.getApPaternoContacto());
        jf.txtApMaContacto.setText(ficha.getApMaternoContacto());
        jf.txtCelularContacto.setText(ficha.getCelularContacto());
        jf.txtTelefonoContacto.setText(ficha.getTelefonoContacto());
        jf.cbParentesco.setSelectedItem(ficha.getParentesco());

        jf.txtCP.setText(domicilio.getCodigoPostal());
        jf.txtCalle.setText(domicilio.getCalle());
        jf.txtNumero.setText(domicilio.getNumero());
        jf.txtColonia.setText(domicilio.getColonia());

        jf.cbMunicipio.setSelectedItem(domicilio.getDelegacionMunicipio());

        jf.cbEntidadLocalidadDomicilio.setSelectedItem(domicilio.getEntidadLocalidad());
        jf.txtEstado.setText(domicilio.getEstado());

    }
//Obtener datos del paciente    

    public Domicilio getDomicilio(int id) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        Domicilio domicilio = new Domicilio();
        try {
            s = sf.openSession();
            domicilio = (Domicilio) s.createQuery("from Domicilio d where d.idDomicilio=?")
                    .setParameter(0, id).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return domicilio;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Hubo error aqui" + e.getMessage());
            return domicilio;
        }
    }

    public FichaDeIdentificacion getFichaIdentificacion(int id_ficha) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        FichaDeIdentificacion ficha = new FichaDeIdentificacion();
        try {
            s = sf.openSession();
            ficha = (FichaDeIdentificacion) s.createQuery("from FichaDeIdentificacion f where f.idFichaDeIdentificacion=?")
                    .setParameter(0, id_ficha).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return ficha;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Hubo error aqui");
            return ficha;
        }
    }

    public Paciente getPacient(int id_paciente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        Paciente paciente = new Paciente();
        try {
            s = sf.openSession();
            paciente = (Paciente) s.createQuery("from Paciente p where p.idPaciente=?")
                    .setParameter(0, id_paciente).uniqueResult();
            s.flush();
            s.clear();
            s.close();
            return paciente;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Hubo error aqui");
            return paciente;
        }
    }

    public void ControladorDatosPrimerContacto(JFFichaIdentificacionPrimerContacto fj, Expediente exp, FichaDeIdentificacion ficha) {
        validarInputsForm2(fj);
        if (fj.etiquetaAccion.getText().equals("Registro de ficha de identificación - Datos del primer contacto del paciente")) {
            //Cargar los psicologos y medicos
            fj.cbMedico = llenarComboEmpleado(fj.cbMedico, "Psicologo");
            fj.cbPsicologo = llenarComboEmpleado(fj.cbPsicologo, "Medico");

            fj.btn_GuardarFicha.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ManejadorEncuestas me = new ManejadorEncuestas();
                    Domicilio domicilio = getDatosDomicilioPrimerContactoForm(fj);
                    int id_psc = obtenerEmpleado(fj.cbMedico);
                    int id_med = obtenerEmpleado(fj.cbPsicologo);
                    Empleado psicologo = new Empleado();
                    Empleado medico = new Empleado();
                    psicologo.setIdEmpleado(id_psc);
                    medico.setIdEmpleado(id_med);

                    Empleado empleado = new Empleado();

                    if (validaDatosFormPrimerContacto(fj)) {
                        if (domicilio != null && getDatosPrimerContactoForm(fj, ficha)) { //Verifica que se haya obtenido correctamente los datos

                            addFichaIdentificacion(domicilio, ficha, exp);

                            if (me.crearEncuesta(medico, exp, 1) && me.crearEncuesta(psicologo, exp, 3)) {//Linea que verifica que se haya realizado la apertura de la nota de tratamiento y psicologica para cada empleado (psicologo y medic)

                                MensajeExitoso msg = new MensajeExitoso(jFFichaIdentificacionPaciente, true);
                                msg.msg.setText("<html>    El registro del paciente <br>se ha realizado correctamente.</html>");
                                msg.setVisible(true);
                                fj.dispose();

                                Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();

                                empleado.setIdEmpleado(ficha.getIdEmpleado());

                                ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                                jFFichasIdentificacion.setVisible(true);
                            }

                        }
                    }

                }

            });

            fj.btnCancelarFicha.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                    Empleado empleado = new Empleado();
                    empleado.setIdEmpleado(ficha.getIdEmpleado());
                    ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                    jFFichasIdentificacion.NumEmpleado.setText(ficha.getIdEmpleado() + "");
                    jFFichasIdentificacion.NumEmpleado.setVisible(false);
                    jFFichasIdentificacion.setVisible(true);
                    fj.dispose();
                }

            });
        }
        if (fj.etiquetaAccion.getText().equals("Actualizar ficha de identificación - Datos del primer contacto del paciente")) {
            fj.cbMedico.setVisible(false);
            fj.cbPsicologo.setVisible(false);
            fj.panelEmpleados.setVisible(false);

            //Actualizar datos del primer contacto
            Domicilio domicilio = new Domicilio();
            domicilio = getDomicilio(ficha.getIdDomicilioContacto());
            cargarInformacionEnFormPrimercontacto(fj, ficha, domicilio);

            fj.btn_GuardarFicha.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (validaDatosFormPrimerContacto(fj)) {
                        Domicilio dom = getDatosDomicilioPrimerContactoForm(fj);

                        Domicilio domicilio = getDomicilio(ficha.getIdDomicilioContacto());

                        dom.setIdDomicilio(domicilio.getIdDomicilio());
                        getDatosPrimerContactoForm(fj, ficha);

                        if (updateDomicilio(dom) && updateFicha(ficha)) {
                            MensajeExitoso msg = new MensajeExitoso(jFFichaIdentificacionPaciente, true);
                            msg.msg.setText("<html>La actualización de la ficha de identificación se ha realizado correctamente.</html>");
                            msg.setVisible(true);
                            fj.dispose();

                            Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(ficha.getIdEmpleado());
                            ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                            jFFichasIdentificacion.NumEmpleado.setText(ficha.getIdEmpleado() + "");
                            jFFichasIdentificacion.NumEmpleado.setVisible(false);
                            jFFichasIdentificacion.setVisible(true);
                            fj.dispose();
                        }

                    }
                }

            });

            fj.btnCancelarFicha.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                    Empleado empleado = new Empleado();
                    empleado.setIdEmpleado(ficha.getIdEmpleado());
                    ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                    jFFichasIdentificacion.NumEmpleado.setText(ficha.getIdEmpleado() + "");
                    jFFichasIdentificacion.NumEmpleado.setVisible(false);
                    jFFichasIdentificacion.setVisible(true);
                    fj.dispose();
                }

            });
        }

    }

    //Apertura de nota de tratamiento de paciente para el psicologo asignado
    public boolean addNewNotaTratamiento(NotaTratamiento notaTratamiento) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            s.save(notaTratamiento);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            return true;
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage() + "-- Aqui hay errorsito:C");
            return false;
        }
    }

    //Apertura de nota medica de paciente para el psicologo asignado
    public boolean addNewNotaMedica(NotaMedica notaMedica) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;

        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            s.save(notaMedica);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            return true;
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage() + "-- Aqui hay errorsito:C");
            return false;
        }
    }

    //Seleccion de psicologo y medico
    //Cargar combobox con el nombre y id de los empleados psicologos y medicos
    public JComboBox llenarComboEmpleado(JComboBox cb, String rol) {
        List<Empleado> listaEmpleados = getInfoEmployees(rol);
        String emp;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            emp = listaEmpleados.get(i).getIdEmpleado() + ": " + listaEmpleados.get(i).getNombre() + " " + listaEmpleados.get(i).getApellidoPaterno() + " " + listaEmpleados.get(i).getApellidoMaterno();
            cb.addItem(emp);
        }
        return cb;
    }

    //Metodo para obtener los empleados segun el rol de la base de datos
    public List getInfoEmployees(String rol) {
        //Empleado empleado = new Empleado();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        List<Empleado> empleados = s.createQuery("from Empleado where rol=?")
                .setParameter(0, rol)
                .list();
        s.flush();
        s.clear();
        s.close();
        return empleados;
    }

    //Metodo para obtener el empleado (psicologo o medico) del combobox
    public int obtenerEmpleado(JComboBox cb) {
        String empleado = cb.getSelectedItem() + "";
        String[] datos_psi = empleado.split(":");
        return Integer.parseInt(datos_psi[0]);
    }

    // ------------------------------------------------------------------------------------------------
    //Metodo para Agregar el domicilio del primer contacto del paciente
    public boolean addFichaIdentificacion(Domicilio domicilio, FichaDeIdentificacion ficha, Expediente exp) {
        //Primero agrega el domicilio
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(domicilio);

            //s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            //System.out.println(ficha.getMotivoConsulta());
            ficha.setIdDomicilioContacto(id);
            //System.out.println("Aqui pasa 5 - "+ficha.getIdDomicilioContacto());
            return addPrimerContacto(ficha, exp);
        } catch (HibernateException e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    //Metodo para agregar la informacion del primer contacto a la ficha de identificacion
    public boolean addPrimerContacto(FichaDeIdentificacion ficha, Expediente exp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            int id = (Integer) s.save(ficha);
            s.flush();
            s.clear();
            s.close();
            //System.out.println("Aqui pasa 4 - " + ficha.getNombreContacto());
            exp.setIdFichaIdentificacion(id);
            return addFichaAtExpediente(exp);
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return false;
        }
    }

    //------------------>> --- Metodos para obtener datos de los formularios:
    //Obtiene los datos del primer contacto del paciente del formulario
    public boolean getDatosPrimerContactoForm(JFFichaIdentificacionPrimerContacto jf, FichaDeIdentificacion ficha) {
        try {
            ficha.setNombreContacto(jf.txtNombreContacto.getText());
            ficha.setApPaternoContacto(jf.txtApPatContacto.getText());
            ficha.setApMaternoContacto(jf.txtApMaContacto.getText());
            ficha.setCelularContacto(jf.txtCelularContacto.getText());
            ficha.setParentesco("" + jf.cbParentesco.getSelectedItem());
            ficha.setTelefonoContacto(jf.txtTelefonoContacto.getText());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //Obtiene la direccion del primer contacto del paciente del formulario
    public Domicilio getDatosDomicilioPrimerContactoForm(JFFichaIdentificacionPrimerContacto jf) {
        Domicilio domicilio = new Domicilio();
        try {
            domicilio.setCalle(jf.txtCalle.getText());
            domicilio.setNumero(jf.txtNumero.getText());
            domicilio.setColonia(jf.txtColonia.getText());
            domicilio.setCodigoPostal(jf.txtCP.getText());
            domicilio.setDelegacionMunicipio("" + jf.cbMunicipio.getSelectedItem() + "");
            domicilio.setEntidadLocalidad("" + jf.cbEntidadLocalidadDomicilio.getSelectedItem());
            domicilio.setEstado(jf.txtEstado.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return domicilio;
    }

    //Obtiene los datos del domicilio del paciente del formulario
    public Paciente getDataPacientForm(JFFichaIdentificacionPaciente form) {
        Paciente paciente = new Paciente();
        try {

            //Datos paciente
            paciente.setNombre(form.txtNombrePaciente.getText());
            paciente.setApellidoPaterno(form.txtApPat.getText());
            paciente.setApellidoMaterno(form.txtApMa.getText());
            paciente.setCelular(form.txtCelular.getText());
            if (form.rbFemenino.isSelected()) {
                paciente.setSexo("F");
            } else {
                paciente.setSexo("M");
            }
            Modelo.DatePicker dp = new DatePicker();
            Date fecha_nac = dp.formatoDate(form.datePickerFechaNac);
            paciente.setFechaNac(fecha_nac);
            paciente.setCurp(form.txtCurp.getText());

            paciente.setEstadoCivil(form.cbEdoCivil.getSelectedItem() + "");

            paciente.setEstadoNacimiento(form.txtEstadoNac.getText());
            paciente.setMunicipioNacimiento("" + form.cbMunicipioLugarNacimiento.getSelectedItem());
            paciente.setRfc(form.txtRFC.getText());
            paciente.setTelefono(form.txtTelefono.getText());
            paciente.setOcupacion("" + form.cbOcupacion.getSelectedItem());
        } catch (Exception e) {
            System.out.println("Error al obtener datos del formulario" + e.getMessage());
        }
        return paciente;
    }

    //Obtiene los datos del paciente del formulario
    public Domicilio getDataAddresForm(JFFichaIdentificacionPaciente form) {
        Domicilio domicilio = new Domicilio();
        try {
            domicilio.setCalle(form.txtCalle.getText());
            domicilio.setNumero(form.txtNumero.getText());
            domicilio.setColonia(form.txtColonia.getText());
            domicilio.setCodigoPostal(form.txtCP.getText());
            domicilio.setDelegacionMunicipio("" + form.cbMunicipioDomicilioActual.getSelectedItem());
            domicilio.setEntidadLocalidad("" + form.cbEntidadLocalidadDomicilioActual.getSelectedItem());
            domicilio.setEstado(form.txtEstadoDom.getText());
        } catch (Exception e) {
            System.out.println("Error al obtener datos del formulario.\n" + e.getMessage());
        }
        return domicilio;
    }

    //Obtiene los datos de la ficha de identificacion del paciente del formulario
    public FichaDeIdentificacion getFichaIdenForm(JFFichaIdentificacionPaciente form) {
        FichaDeIdentificacion ficha = new FichaDeIdentificacion();
        try {
            //datos de la primera parte de la ficha de identificacion 
            ficha.setEscolaridad("" + form.cbEscolaridad.getSelectedItem());
            ficha.setReferidoPor(form.cbReferidoPor.getSelectedItem() + "");
            ficha.setReligion("" + form.cbReligion.getSelectedItem());
            ficha.setGrupoSanguineo("" + form.cbGrupoSanguineo.getSelectedItem());

            if (form.rbLISi.isSelected()) {
                ficha.setHablaLenguaIndigena("Si");
            } else {
                ficha.setHablaLenguaIndigena("No");
            }

            ficha.setLenguaIndigena(form.txtLenguaIndigena.getText());

            if (form.rbMigranteSi.isSelected()) {
                ficha.setMigrante("Si");
            } else {
                ficha.setMigrante("No");
            }

            ficha.setDerechohabiencia("" + form.cbDerechohabiencia.getSelectedItem());
            ficha.setNumeroDerechohabiencia(form.txtNumDerechohabiencia.getText());
            ficha.setMotivoConsulta(form.txtMotivoConsulta.getText());

            ficha.setIdEmpleado(null);

        } catch (Exception e) {
            System.out.println("Error al obtener datos del formulario\n" + e.getMessage());
        }
        return ficha;
    }

    //Apertura de expediente
    //Metodo para aperturar y guardar el expediente, comenzando por el guardado del domicilio del paciente (Domicilio)
    public boolean aperturaExpediente(Domicilio domicilio, Paciente paciente, Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            //s.save(domicilio);
            int id = (Integer) s.save(domicilio);
            //System.out.println("Id domicilio: " + id);
            //s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            paciente.setIdDomicilio(id);
            //System.out.println("Aqui pasa 3");
            return addPaciente(paciente, expediente);
        } catch (HibernateException e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    //Metodo para agregar los datos del paciente (Paciente)
    public boolean addPaciente(Paciente paciente, Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(paciente);

            //s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            //System.out.println("Aqui pasa 2");
            expediente.setIdPaciente(id);
            return addExpediente(expediente);
        } catch (HibernateException e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Metodo para agregar los datos del paciente de su ficha (Ficha de identificacion) 
    public boolean addFichaPaciente(FichaDeIdentificacion ficha, Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(ficha);

            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            expediente.setIdFichaIdentificacion(id);

            return addFichaAtExpediente(expediente);
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Metodo para actualizar el id de la ficha de identificacion del expediente.
    public boolean addFichaAtExpediente(Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            //Apertura de expediente
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            s.update(expediente);

            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            //System.out.println("Aqui pasa 1");
            return true;
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Metodo que guarda el paciente
    public boolean addExpediente(Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            //Apertura de expediente
            expediente.setPruebaRealizada("ASSIT");
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(expediente);

            //s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            //System.out.println("Aqui pasa 10");
            return true;
        } catch (Exception e) {
            if (s.isOpen()) {
                s.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Validar
    public boolean validaDatosFormPrimerContacto(JFFichaIdentificacionPrimerContacto jf) {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;

//Campos obligatorios
        if (jf.txtNombreContacto.getText().isEmpty() || !jf.txtNombreContacto.getText().matches("([àèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- Nombre del contacto es inválido.<br>";
            errores = true;
            jf.txtNombreContacto.setText("");
        }
        if (jf.txtApPatContacto.getText().isEmpty() || !(jf.txtApPatContacto.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- Apellido paterno inválido<br>";
            errores = true;
            jf.txtApPatContacto.setText("");
        }
        if (jf.txtApMaContacto.getText().isEmpty() || !(jf.txtApMaContacto.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- Apellido materno inválido<br>";
            errores = true;
            jf.txtApMaContacto.setText("");
        }

        if (!jf.txtTelefonoContacto.getText().matches("[0-9]{7,15}")) {
            txtError += "- El número de telefono es inválido.<br>";
            errores = true;
            jf.txtTelefonoContacto.setText("");
        }
        if (!jf.txtCelularContacto.getText().matches("[0-9]{7,15}")) {
            txtError += "- El número de celular es inválido.<br>";
            errores = true;
            jf.txtCelularContacto.setText("");
        }

        if (!jf.txtCalle.getText().matches("([àèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- El nombre de la calle es inválida.<br>";
            errores = true;
            jf.txtCalle.setText("");
        }
        if (!(jf.txtNumero.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚa-zA-Z0-9]{1,4})"))) {
            txtError += "- El número de la dirección es inválido.";
            errores = true;
            jf.txtNumero.setText("");
        }
        if (!jf.txtEstado.getText().matches("([àèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- El nombre del estado es inválido.<br>";
            errores = true;
            jf.txtEstado.setText("");
        }
        if (!(jf.txtColonia.getText().isEmpty() || jf.txtColonia.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- El nombre de la colonia es inválida.<br>";
            errores = true;
            jf.txtColonia.setText("");
        }
        if (!(jf.txtCP.getText().isEmpty() || jf.txtCP.getText().matches("([1-9]{1}[0-9]{4})"))) {
            txtError += "- El código postal es inválido.<br>";
            errores = true;
            jf.txtCP.setText("");
        }

        if (errores) {
            txtError += "</html>";

            Vista.Mensaje msg = new Mensaje(jf, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;

    }

    public boolean validaDatosForm(JFFichaIdentificacionPaciente jf) {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;

//Campos obligatorios
        if (jf.txtNombrePaciente.getText().isEmpty() || !jf.txtNombrePaciente.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- Nombre del paciente inválido.<br>";
            errores = true;
            jf.txtNombrePaciente.setText("");
        }
        if (jf.txtApPat.getText().isEmpty() || !(jf.txtApPat.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido paterno inválido<br>";
            errores = true;
            jf.txtApPat.setText("");
        }
        if (jf.txtApMa.getText().isEmpty() || !(jf.txtApMa.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido materno inválido<br>";
            errores = true;
            jf.txtApMa.setText("");
        }

        if (jf.txtNum_Expediente.getText().isEmpty() || !jf.txtNum_Expediente.getText().matches("([0-9]{4}[-][0-9]{4})")) {
            txtError += "- Número de expediente inválido.(E.J.####-####)<br>";
            errores = true;
            jf.txtNum_Expediente.setText("");
        }
        if (jf.etiquetaAccion.getText().equals("Registro de ficha de identificación - Datos paciente")) {
            if (verificarNumExp(jf.txtNum_Expediente.getText()) != null) {
                txtError += "- El número de expediente ya se encuentra registrado.<br>";
                errores = true;
                jf.txtNum_Expediente.setText("");
            }
        }

        if (jf.txtEstadoNac.getText().isEmpty() || !jf.txtEstadoNac.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- Estado de nacimiento inválido.<br>";
            errores = true;
            jf.txtEstadoNac.setText("");
        }
        //Valida fecha de nacimiento
        //Date fecha = new Date();

        if (jf.datePickerFechaNac.getDate() == null || jf.datePickerFechaNac.getDate().isAfter(LocalDate.now())) {
            txtError += "- Fecha de nacimiento inválida.<br>";
            errores = true;
            jf.datePickerFechaNac.setText("");
        }
        if (jf.txtMotivoConsulta.getText().isEmpty()) {
            txtError += "- Motivo de la consulta inválido.<br>";
            errores = true;
            jf.txtMotivoConsulta.setText("");
        }

        if (!jf.txtCalle.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9/-]+[ ]*)+")) {
            txtError += "- El nombre de la calle es inválida.<br>";
            errores = true;
            jf.txtCalle.setText("");
        }
        if (!(jf.txtNumero.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚa-zA-Z0-9]{1,4})"))) {
            txtError += "- El número de la dirección es inválido.";
            errores = true;
            jf.txtNumero.setText("");
        }
        if (!jf.txtEstadoDom.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- El nombre del estado es inválido.<br>";
            errores = true;
            jf.txtEstadoDom.setText("");
        }

        if (jf.txtTelefono.getText().isEmpty() && !jf.txtTelefono.getText().matches("[0-9]{7,15}")) {
            txtError += "- El número de telefono es inválido.<br>";
            errores = true;
            jf.txtTelefono.setText("");
        }

        //Campos no obligatorios
        if (!(jf.txtColonia.getText().isEmpty() || jf.txtColonia.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- El nombre de la colonia es inválida.<br>";
            errores = true;
            jf.txtColonia.setText("");
        }
        if (!(jf.txtCP.getText().isEmpty() || jf.txtCP.getText().matches("([1-9]{1}[0-9]{4})"))) {
            txtError += "- El código postal es inválido.<br>";
            errores = true;
            jf.txtCP.setText("");
        }

        if (!jf.txtCelular.getText().isEmpty() && !jf.txtCelular.getText().matches("[0-9]{7,15}")) {
            txtError += "- El número de celular es inválido.<br>";
            errores = true;
            jf.txtCelular.setText("");
        }

        //Campos no obligatorios
        if (!jf.txtNumDerechohabiencia.getText().isEmpty() && !jf.txtNumDerechohabiencia.getText().matches("([0-9]+[- ]*)+")) {
            txtError += "- Núm. de derechohabiencia inválido.<br>";
            errores = true;
            jf.txtNumDerechohabiencia.setText("");
        }

        if (!jf.txtLenguaIndigena.getText().isEmpty() && !jf.txtLenguaIndigena.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+")) {
            txtError += "- Lengua indígena inválida.<br>";
            errores = true;
            jf.txtLenguaIndigena.setText("");
        }

        if (!jf.txtRFC.getText().isEmpty() && !jf.txtRFC.getText().matches("[ñÑa-zA-Z]{4}[0-9]{6}[ñÑa-zA-Z0-9]{3}")) {
            txtError += "- RFC inválido.(Debe tener de 13 digitos).<br>";
            errores = true;
            jf.txtRFC.setText("");
        }

        if (!jf.txtCurp.getText().isEmpty() && !jf.txtCurp.getText().matches("([Ñña-zA-Z]{4}[0-9]{6}[ñÑa-zA-Z0-9]{8})")) {
            txtError += "- CURP inválida.<br>";
            errores = true;
            jf.txtCurp.setText("");
        }

        if (errores) {
            txtError += "</html>";

            Vista.Mensaje msg = new Mensaje(jf, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }
        return true;

    }

    public void validaInputs() {
        this.jFFichaIdentificacionPaciente.txtNumDerechohabiencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtNumDerechohabiencia.getText().length() == 15) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == '-')) {
                    e.consume();
                }
            }
        });
        this.jFFichaIdentificacionPaciente.txtCP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtCP.getText().length() == 5) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });

        this.jFFichaIdentificacionPaciente.txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtNumero.getText().length() == 5) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (Character.isLetter(c)) || c == '-' || c == '/')) {
                    e.consume();
                }
            }
        });

        this.jFFichaIdentificacionPaciente.txtRFC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtRFC.getText().length() == 13) {
                    e.consume();
                }
            }
        });

        this.jFFichaIdentificacionPaciente.txtCurp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtCurp.getText().length() == 18) {
                    e.consume();
                }
            }
        });

        this.jFFichaIdentificacionPaciente.txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtTelefono.getText().length() == 15) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });
        this.jFFichaIdentificacionPaciente.txtCelular.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFFichaIdentificacionPaciente.txtCelular.getText().length() == 15) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });
    }

    public void validarInputsForm2(JFFichaIdentificacionPrimerContacto fj) {
        fj.txtCP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (fj.txtCP.getText().length() == 5) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });

        fj.txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (fj.txtNumero.getText().length() == 4) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        fj.txtTelefonoContacto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (fj.txtTelefonoContacto.getText().length() == 15) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });
        fj.txtCelularContacto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (fj.txtCelularContacto.getText().length() == 15) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });
    }

    public Expediente verificarNumExp(String nexp) {
        Expediente exp = new Expediente();
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sessionFactory.openSession();
        exp = (Expediente) session.createQuery("from Expediente e where e.numExpediente like ?")
                .setParameter(0, nexp)
                .uniqueResult();
        session.flush();
        session.close();
        return exp;
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

}
