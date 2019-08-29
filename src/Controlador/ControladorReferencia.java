/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Domicilio;
import Modelo.Empleado;
import Modelo.Establecimiento;
import Modelo.Expediente;
import Modelo.FichaDeIdentificacion;
import Modelo.NewHibernateUtil;
import Modelo.Paciente;
import Modelo.Referencia;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import Vista.TrabajadorSocial.JFReferencias;
import Vista.TrabajadorSocial.JFRegistrarReferenciaPaciente;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorReferencia {

    JFRegistrarReferenciaPaciente registrarReferenciaPaciente;
    Expediente expediente;
    Paciente paciente;
    Empleado empleado;
    Referencia referencia;

    public ControladorReferencia(JFRegistrarReferenciaPaciente registrarReferenciaPaciente, Expediente expediente, Paciente paciente, Empleado empleado, Referencia referencia) throws ParseException {
        this.registrarReferenciaPaciente = registrarReferenciaPaciente;
        this.expediente = expediente;
        this.paciente = paciente;
        this.empleado = empleado;
        this.referencia = referencia;
        DatePicker dp = new DatePicker();

        llenarCombo(cargaEstablecimientos());
        llenarComboEmpleados(cargaEmpleados());
        cargaDatosEstablecimiento();
validarTyped();
        if (this.registrarReferenciaPaciente.LabelReferencia.getText().equals("Registrar referencia - Paciente nuevo")) {
            this.registrarReferenciaPaciente.horaReferencia.setTime(dp.formatoToDateTimeComplete("08:00:00"));
            this.registrarReferenciaPaciente.dpFechaReferencia.setDateToToday();
            habilitarCampos();
            validarTyped();
            this.registrarReferenciaPaciente.rbM.setSelected(true);
            this.registrarReferenciaPaciente.jRadioButton2.setSelected(true);

            this.registrarReferenciaPaciente.btn_GuardarReferencia.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        if (validaCampos()) {

                            Referencia referencia = obtenerDatosReferencia();

                            if (addInfoNewPaciente(obtenerDomicilio(), obtenerDatosPaciente(), referencia)) {

                                MensajeExitoso msg = new MensajeExitoso(registrarReferenciaPaciente, true);
                                msg.msg.setText("<html>La referencia se ha registrado <br>correctamente.</html>");
                                msg.setVisible(true);

                                JFReferencias jFReferencias = new JFReferencias();
                                ControladorPacientesReferencias controladorPacientesReferencias = new ControladorPacientesReferencias(jFReferencias, empleado, expediente, paciente);
                                jFReferencias.setVisible(true);

                                registrarReferenciaPaciente.dispose();
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorReferencia.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
        }

        if (this.registrarReferenciaPaciente.LabelReferencia.getText().equals("Registrar referencia")) {
            // Registrar referencia a paciente ya registrado
            this.registrarReferenciaPaciente.horaReferencia.setTime(dp.formatoToDateTimeComplete("08:00:00"));
            this.registrarReferenciaPaciente.dpFechaReferencia.setDateToToday();
            this.registrarReferenciaPaciente.rbM.setSelected(true);
            this.registrarReferenciaPaciente.jRadioButton2.setSelected(true);
            this.registrarReferenciaPaciente.btn_GuardarReferencia.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        //Es paciente ya registrado
                        Referencia referencia_nva = obtenerDatosReferencia();

                        referencia_nva.setIdPaciente(referencia.getIdPaciente());
                        
                        if (validaCampos()) {
                            if (addReferencia(referencia_nva)) {
                                MensajeExitoso msg = new MensajeExitoso(registrarReferenciaPaciente, true);
                                msg.msg.setText("<html>La referencia se ha registrado <br>correctamente.</html>");
                                msg.setVisible(true);

                                JFReferencias jFReferencias = new JFReferencias();
                                ControladorPacientesReferencias controladorPacientesReferencias = new ControladorPacientesReferencias(jFReferencias, empleado, expediente, paciente);
                                jFReferencias.setVisible(true);
                                registrarReferenciaPaciente.dispose();

                            }
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorReferencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
        }
        if (this.registrarReferenciaPaciente.LabelReferencia.getText().equals("Referencia")) {
            //Actualizar o ver referencia
            this.registrarReferenciaPaciente.btn_GuardarReferencia.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        if (validaCampos()) {
                            Referencia actualizacion_referencia = new Referencia();
                            actualizacion_referencia = obtenerDatosReferencia();
                            actualizacion_referencia.setIdPaciente(referencia.getIdPaciente());
                            actualizacion_referencia.setIdReferencia(referencia.getIdReferencia());
                            actualizacion_referencia.setIdEstablecimientoRefiere(0);
                            if (updateReferencia(actualizacion_referencia)) {
                                MensajeExitoso msg = new MensajeExitoso(registrarReferenciaPaciente, true);
                                msg.msg.setText("<html>La referencia se ha actualizado <br>correctamente.</html>");
                                msg.setVisible(true);

                                JFReferencias jFReferencias = new JFReferencias();
                                ControladorPacientesReferencias controladorPacientesReferencias = new ControladorPacientesReferencias(jFReferencias, empleado, expediente, paciente);
                                jFReferencias.setVisible(true);
                                registrarReferenciaPaciente.dispose();
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorReferencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        }
        
        this.registrarReferenciaPaciente.txtMotivoEnvio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(registrarReferenciaPaciente.txtMotivoEnvio.getText().length()==100){
                    e.consume();
                }
            }
        });
        
        this.registrarReferenciaPaciente.txtMotivoReferencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(registrarReferenciaPaciente.txtMotivoReferencia.getText().length()==300){
                    e.consume();
                }
            }
        });
        
        this.registrarReferenciaPaciente.txtDiagnostico.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(registrarReferenciaPaciente.txtDiagnostico.getText().length()==100){
                    e.consume();
                }
            }
        });
        
        this.registrarReferenciaPaciente.txtObservaciones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(registrarReferenciaPaciente.txtObservaciones.getText().length()==100){
                    e.consume();
                }
            }
        });

        this.registrarReferenciaPaciente.btnCancelarReferencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFReferencias jFReferencias = new JFReferencias();
                ControladorPacientesReferencias controladorPacientesReferencias = new ControladorPacientesReferencias(jFReferencias, empleado, expediente, paciente);
                jFReferencias.setVisible(true);
                registrarReferenciaPaciente.dispose();
            }
        });

        this.registrarReferenciaPaciente.cbEstablecimiento.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cargaDatosEstablecimiento();
            }
        });
    }

    public void cargaDatosEstablecimiento() {
        String[] separa = registrarReferenciaPaciente.cbEstablecimiento.getSelectedItem().toString().split(":");

        int id = Integer.parseInt(separa[0]);

        List establecimiento = obtenerDatosEstablecimiento(id);
        for (Iterator iterator = establecimiento.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            registrarReferenciaPaciente.txtResponsable.setText(objects[1].toString() + " " + objects[2].toString() + " " + objects[3].toString());
            registrarReferenciaPaciente.txtTelefonoEst.setText(objects[4].toString());
            registrarReferenciaPaciente.txtCalleNumeroEst.setText(objects[5].toString() + ", " + objects[6].toString());
            registrarReferenciaPaciente.txtColoniaEst.setText(objects[7].toString());
            registrarReferenciaPaciente.txtcPEst.setText(objects[8].toString());
            
            registrarReferenciaPaciente.txtEntidadFererativaEst.setText(objects[9].toString());
            
            registrarReferenciaPaciente.txtMunicipioEst.setText(objects[10].toString());

        }
    }

    public boolean addReferencia(Referencia referencia) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            referencia.setIdEstablecimientoRefiere(0);
            int id = (Integer) s.save(referencia);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();

            return true;

        } catch (HibernateException e) {
            s.getTransaction().rollback();
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public void validarTyped() {
        this.registrarReferenciaPaciente.txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (Character.isLetter(c)) || c == '-' || c == '/')) {
                    e.consume();
                }
                if (registrarReferenciaPaciente.txtNumero.getText().length() == 5) {
                    e.consume();
                }
            }
        });

        this.registrarReferenciaPaciente.txtCP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (registrarReferenciaPaciente.txtCP.getText().length() == 5) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
            }
        });
        
        

    }

    public boolean validaCampos() throws ParseException {
        String txtError = "<html>Favor de verificar los datos<br><br>";
        Boolean errores = false;
        DatePicker dp = new DatePicker();
        
        if (registrarReferenciaPaciente.dpFechaReferencia.getDate() == null 
               ) {
            txtError += "- La fecha de la referencia es inválida.<br>";
            errores = true;
        }
        
        if (registrarReferenciaPaciente.pdFechaNac.getDate() == null 
                || registrarReferenciaPaciente.pdFechaNac.getDate().isAfter(LocalDate.now())) {
            txtError += "- La fecha de nacimiento es inválida.<br>";
            errores = true;
        }

        if (registrarReferenciaPaciente.txtMotivoEnvio.getText().isEmpty()) {
            txtError += "- El motivo de envio no puede ser vacío.<br>";
            errores = true;
        }
        
        if (dp.formatoStringToDateTime(registrarReferenciaPaciente.horaReferencia.getFormatedTime()).before(dp.formatoStringToDateTime("08:00:00"))
                || dp.formatoStringToDateTime(registrarReferenciaPaciente.horaReferencia.getFormatedTime()).after(dp.formatoStringToDateTime("20:00:00"))) {
            txtError += "La hora de la cita debe estar <br>en el horario de atención a pacientes.<br>(08:00 am. - 08:00 pm)";
            errores = true;
        }

        if (registrarReferenciaPaciente.txtNombrePaciente.getText().isEmpty() || !registrarReferenciaPaciente.txtNombrePaciente.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- Nombre del paciente inválido.<br>";
            errores = true;
            registrarReferenciaPaciente.txtNombrePaciente.setText("");
        }
        if (registrarReferenciaPaciente.txtApPat.getText().isEmpty() || !(registrarReferenciaPaciente.txtApPat.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido paterno inválido<br>";
            errores = true;
            registrarReferenciaPaciente.txtApPat.setText("");
        }
        if (registrarReferenciaPaciente.txtApMat.getText().isEmpty() || !(registrarReferenciaPaciente.txtApMat.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido materno inválido<br>";
            errores = true;
            registrarReferenciaPaciente.txtApMat.setText("");
        }

        if (!(registrarReferenciaPaciente.txtCalle.getText().isEmpty() || registrarReferenciaPaciente.txtCalle.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9/-]+[ ]*)+"))) {
            txtError += "- El nombre de la calle es inválida.<br>";
            errores = true;
            registrarReferenciaPaciente.txtCalle.setText("");
        }
        if (!(registrarReferenciaPaciente.txtNumero.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚa-zA-Z0-9]{1,4})"))) {
            txtError += "- El número de la dirección es inválido.";
            errores = true;
            registrarReferenciaPaciente.txtNumero.setText("");
        }
        if (!(registrarReferenciaPaciente.txtColonia.getText().isEmpty() || registrarReferenciaPaciente.txtColonia.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- El nombre de la colonia es inválida.<br>";
            errores = true;
            registrarReferenciaPaciente.txtColonia.setText("");
        }
        if (!(registrarReferenciaPaciente.txtCP.getText().isEmpty() || registrarReferenciaPaciente.txtCP.getText().matches("([1-9]{1}[0-9]{4})"))) {
            txtError += "- El código postal es inválido.<br>";
            errores = true;
            registrarReferenciaPaciente.txtCP.setText("");
        }

        if (errores) {
            txtError += "</html>";

            Vista.Mensaje msg = new Mensaje(registrarReferenciaPaciente, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);

            return false;
        }

        return true;
    }
    
    

    public Paciente obtenerDatosPaciente() {
        Paciente paciente = new Paciente();
        try {
            DatePicker dp = new DatePicker();
            
            paciente.setNombre(registrarReferenciaPaciente.txtNombrePaciente.getText());
            paciente.setApellidoPaterno(registrarReferenciaPaciente.txtApPat.getText());
            paciente.setApellidoMaterno(registrarReferenciaPaciente.txtApMat.getText());
            if (registrarReferenciaPaciente.rbM.isSelected()) {
                paciente.setSexo("M");
            } else if (registrarReferenciaPaciente.rbF.isSelected()) {
                paciente.setSexo("F");
            }

            paciente.setFechaNac(dp.formatoDate(registrarReferenciaPaciente.pdFechaNac));
        } catch (Exception e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return paciente;
    }

    public Domicilio obtenerDomicilio() {
        Domicilio dom = new Domicilio();
        dom.setCalle(registrarReferenciaPaciente.txtCalle.getText());
        dom.setNumero(registrarReferenciaPaciente.txtNumero.getText());
        dom.setColonia(registrarReferenciaPaciente.txtColonia.getText());
        dom.setCodigoPostal(registrarReferenciaPaciente.txtCP.getText());
        dom.setDelegacionMunicipio("" + registrarReferenciaPaciente.cbDelegacion.getSelectedItem());
        dom.setEntidadLocalidad("" + registrarReferenciaPaciente.cbEntidadFederativa.getSelectedItem());
        dom.setEstado("Guerrero");
        return dom;
    }

    public boolean updateReferencia(Referencia referencia) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(referencia);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;

        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean addInfoNewPaciente(Domicilio domicilio, Paciente paciente, Referencia referencia) {
        //Primero agrega el domicilio
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(domicilio);

            s.flush();
            s.clear();
            s.close();
            paciente.setIdDomicilio(id);

            return addPaciente(referencia, paciente);

        } catch (HibernateException e) {
            s.getTransaction().rollback();
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean addPaciente(Referencia referencia, Paciente paciente) {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            int id = (Integer) s.save(paciente);

            s.flush();
            s.clear();
            s.close();
            referencia.setIdPaciente(id);
            return addReferencia(referencia);
        } catch (HibernateException e) {
            s.getTransaction().rollback();
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public Referencia obtenerDatosReferencia() throws ParseException {
        DatePicker dp = new DatePicker();
        Referencia referencia = new Referencia();
        referencia.setDiagnosticoClinico(registrarReferenciaPaciente.txtDiagnostico.getText());
        referencia.setFecha(dp.formatoStringToDate(registrarReferenciaPaciente.dpFechaReferencia.getDate().toString()));
        referencia.setHora(dp.formatoStringToDateTime(registrarReferenciaPaciente.horaReferencia.getFormatedTime()));

        String[] empleado = registrarReferenciaPaciente.cbEmpleado.getSelectedItem().toString().split(":");
        int id_empleado = Integer.parseInt(empleado[0].toString());
        referencia.setIdEmpleado(id_empleado);

        String[] separa = registrarReferenciaPaciente.cbEstablecimiento.getSelectedItem().toString().split(":");
        int id = Integer.parseInt(separa[0]);
        referencia.setIdEstablecimientoSerefiere(id);
        referencia.setMotivoDeEnvio(registrarReferenciaPaciente.txtMotivoEnvio.getText());
        referencia.setMotivoDeReferencia(registrarReferenciaPaciente.txtMotivoReferencia.getText());
        referencia.setObservaciones(registrarReferenciaPaciente.txtObservaciones.getText());
        if (registrarReferenciaPaciente.jRadioButton1.isSelected()) {
            referencia.setUrgencia("No");
        } else {
            referencia.setUrgencia("Si");
        }
        return referencia;
    }

    public List<Empleado> cargaEmpleados() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        List<Empleado> lista = new ArrayList<>();
        try {
            s = sf.openSession();
            lista = new ArrayList<>();
            lista = s.createQuery("from Empleado e where e.rol!=5 and e.rol!=6").list();
            s.flush();
            s.clear();
            s.close();

        } catch (Exception e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public void llenarComboEmpleados(List<Empleado> l) {
        for (int i = 0; i < l.size(); i++) {
            registrarReferenciaPaciente.cbEmpleado.addItem(l.get(i).getIdEmpleado() + ":" + l.get(i).getNombre() + " " + l.get(i).getApellidoPaterno() + " " + l.get(i).getApellidoMaterno());
        }
    }

    public List<Establecimiento> cargaEstablecimientos() {

        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        List<Establecimiento> establecimientos = s.createQuery("from Establecimiento")
                .list();
        s.flush();
        s.clear();
        s.close();
        return establecimientos;
    }

    public void llenarCombo(List<Establecimiento> lista) {
        for (int i = 1; i < lista.size(); i++) {
            registrarReferenciaPaciente.cbEstablecimiento.addItem(lista.get(i).getIdEstablecimiento() + ":" + lista.get(i).getNombre());
        }
    }

    public List obtenerDatosEstablecimiento(int id) {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = null;
        List establecimiento = null;
        try {
            session = sessionFactory.openSession();
            establecimiento = session.createSQLQuery("select establecimiento.nombre, establecimiento.`nombre_responsable`, "
                    + "establecimiento.`apellido_paterno_responsable`, establecimiento.`apellido_materno_responsable`, "
                    + "establecimiento.`telefono_establecimiento`, domicilio.calle, domicilio.numero, domicilio.colonia, "
                    + "domicilio.codigo_postal, domicilio.`entidad_localidad`, domicilio.`delegacion_municipio` FROM establecimiento "
                    + "join domicilio on establecimiento.id_domicilio=domicilio.id_domicilio where establecimiento.id_establecimiento=?")
                    .addScalar("nombre").addScalar("nombre_responsable").addScalar("apellido_paterno_responsable").addScalar("apellido_materno_responsable")
                    .addScalar("telefono_establecimiento").addScalar("calle").addScalar("numero").addScalar("colonia").addScalar("codigo_postal")
                    .addScalar("entidad_localidad").addScalar("delegacion_municipio")
                    .setParameter(0, id)
                    .list();
            session.flush();
            session.clear();
            session.close();

        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return establecimiento;
    }

    public void habilitarCampos() {
        this.registrarReferenciaPaciente.txtApMat.setEditable(true);
        this.registrarReferenciaPaciente.txtApPat.setEditable(true);
        this.registrarReferenciaPaciente.txtCP.setEditable(true);
        this.registrarReferenciaPaciente.txtCalle.setEditable(true);
        this.registrarReferenciaPaciente.txtNumero.setEditable(true);
        this.registrarReferenciaPaciente.txtColonia.setEditable(true);
        this.registrarReferenciaPaciente.txtNombrePaciente.setEditable(true);
    }

}
