/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Domicilio;
import Modelo.Establecimiento;
import Modelo.NewHibernateUtil;
import Vista.Administrador.JFAgregarEstablecimiento;
import Vista.Administrador.JFEstablecimientos;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ControladorAccionesEstablecimiento {

    JFAgregarEstablecimiento jFAgregarEstablecimiento;
    Establecimiento establecimiento;
    Domicilio domicilio;

    public ControladorAccionesEstablecimiento(JFAgregarEstablecimiento jFAgregarEstablecimiento, Establecimiento establecimiento, Domicilio domicilio) {
        this.jFAgregarEstablecimiento = jFAgregarEstablecimiento;
        this.establecimiento = establecimiento;
        this.domicilio = domicilio;

        if (this.jFAgregarEstablecimiento.labelAccion.getText().equals("Agregar Establecimiento")) {
            this.jFAgregarEstablecimiento.btnCancelarRegistroEstablecimiento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JFEstablecimientos jFEstablecimientos = new JFEstablecimientos();
                    ControladorEstablecimiento controladorEstablecimiento = new ControladorEstablecimiento(jFEstablecimientos, establecimiento, domicilio);
                    jFAgregarEstablecimiento.dispose();
                    jFEstablecimientos.setVisible(true);
                }

            });

            this.jFAgregarEstablecimiento.btn_GuardarEstablecimiento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    if (validaEstablecimiento(jFAgregarEstablecimiento)) {
                        Domicilio domicilio = getDataAddressForm();
                        Establecimiento est = getDataEstablishmetnForm();

                        if (addAddresFromEstablishment(domicilio, est)) {
                            MensajeExitoso msg = new MensajeExitoso(jFAgregarEstablecimiento, true);
                            msg.msg.setText("<html>El registro del establecimiento se <br>ha realizado correctamente.</html>");
                            msg.setVisible(true);

                            Vista.Administrador.JFEstablecimientos jFEstablecimientos = new JFEstablecimientos();
                            ControladorEstablecimiento controlador = new ControladorEstablecimiento(jFEstablecimientos, establecimiento, domicilio);
                            jFAgregarEstablecimiento.dispose();
                            jFEstablecimientos.setVisible(true);

                        }
                    }

                }

            });
        } else {
            this.jFAgregarEstablecimiento.btn_GuardarEstablecimiento.setText("Actualizar");
            //obtener informacion del establecimiento

            Establecimiento est = new Establecimiento();
            est = getInfoEstablishment(establecimiento.getIdEstablecimiento());

            int id_est = est.getIdEstablecimiento();

            int id_dom = est.getIdDomicilio();

            Domicilio dom = getAddresFromEstablishment(est.getIdDomicilio());
            this.jFAgregarEstablecimiento.txtNombreEstablecimiento.setText(est.getNombre());
            this.jFAgregarEstablecimiento.txtNombreResp.setText(est.getNombreResponsable());
            this.jFAgregarEstablecimiento.txtApPatResp.setText(est.getApellidoPaternoResponsable());
            this.jFAgregarEstablecimiento.txtApMaternoResp.setText(est.getApellidoMaternoResponsable());
            this.jFAgregarEstablecimiento.txtTelefonoEst.setText(est.getTelefonoEstablecimiento());
            this.jFAgregarEstablecimiento.txtCalle.setText(dom.getCalle());
            this.jFAgregarEstablecimiento.txtNumero.setText(dom.getNumero());
            this.jFAgregarEstablecimiento.txtColonia.setText(dom.getColonia());
            this.jFAgregarEstablecimiento.txtCP.setText(dom.getCodigoPostal());
            
            this.jFAgregarEstablecimiento.cbMunicipioDomicilioActual.setSelectedItem(dom.getDelegacionMunicipio());
            this.jFAgregarEstablecimiento.cbEntidadLocalidadDomicilioActual.setSelectedItem(dom.getEntidadLocalidad());
            this.jFAgregarEstablecimiento.txtEstado.setText(dom.getEstado());

            this.jFAgregarEstablecimiento.btn_GuardarEstablecimiento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (validaEstablecimiento(jFAgregarEstablecimiento)) {
                        Domicilio d = getDataAddressForm();
                        Establecimiento establecimiento1 = getDataEstablishmetnForm();

                        establecimiento1.setIdEstablecimiento(id_est);
                        establecimiento1.setIdDomicilio(id_dom);
                        d.setIdDomicilio(id_dom);

                        if (updateEstablishment(establecimiento1) && updateAddress(d)) {
                            MensajeExitoso msg = new MensajeExitoso(jFAgregarEstablecimiento, true);
                            msg.msg.setText("<html>La actualización del establecimiento <br>se ha realizado correctamente.</html>");
                            msg.setVisible(true);

                            Vista.Administrador.JFEstablecimientos jFEstablecimientos = new JFEstablecimientos();
                            ControladorEstablecimiento controlador = new ControladorEstablecimiento(jFEstablecimientos, establecimiento, domicilio);
                            jFEstablecimientos.setVisible(true);
                            jFAgregarEstablecimiento.dispose();

                        } else {
                            System.out.println("Error en la actualización");
                        }

                    }

                }

            });

            this.jFAgregarEstablecimiento.btnCancelarRegistroEstablecimiento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JFEstablecimientos jf = new JFEstablecimientos();

                    ControladorEstablecimiento controladorEstablecimiento = new ControladorEstablecimiento(jf, establecimiento, domicilio);
                    jf.setVisible(true);
                    jFAgregarEstablecimiento.dispose();
                }

            });
        }

        this.jFAgregarEstablecimiento.txtTelefonoEst.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFAgregarEstablecimiento.txtTelefonoEst.getText().length() == 15) {
                    e.consume();
                }
            }
        });
        
        this.jFAgregarEstablecimiento.txtNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (Character.isLetter(c))||c == '-' ||c == '/' )) {
                    e.consume();
                }
                if(jFAgregarEstablecimiento.txtNumero.getText().length() == 4){
                    e.consume();
                }
            }
        });

    }

    public boolean addAddresFromEstablishment(Domicilio domicilio, Establecimiento establecimiento) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();

            //s.save(domicilio);
            int id = (Integer) s.save(domicilio);

            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            establecimiento.setIdDomicilio(id);
            return addEstablishment(establecimiento);
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean addEstablishment(Establecimiento establecimiento) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction t = s.beginTransaction();
            s.save(establecimiento);
            s.getTransaction().commit();
            s.flush();
            s.clear();
            s.close();
            return true;
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public Establecimiento getInfoEstablishment(int id) {
        Establecimiento establecimiento = new Establecimiento();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            establecimiento = (Establecimiento) s.createQuery("from Establecimiento where idEstablecimiento=?")
                    .setParameter(0, id)
                    .uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }

        return establecimiento;
    }

    public Domicilio getAddresFromEstablishment(int id) {
        Domicilio domicilio = new Domicilio();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        domicilio = (Domicilio) s.get(Domicilio.class, id);
        s.flush();
        s.clear();
        s.close();
        return domicilio;
    }

    public boolean updateEstablishment(Establecimiento est) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(est);
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

    public boolean updateAddress(Domicilio dom) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(dom);
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

    public boolean validaEstablecimiento(JFAgregarEstablecimiento jf) {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;
// Datos del establecimiento        
//Campos obligatorios
        if (jf.txtNombreEstablecimiento.getText().isEmpty() ){
                //|| !jf.txtNombreEstablecimiento.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9.]+[ -]*)+")) {
            txtError += "- Nombre del establecimiento inválido.<br>";
            errores = true;
            jf.txtNombreEstablecimiento.setText("");
        }
        if (!jf.txtNombreResp.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+")) {
            txtError += "- Nombre del responsable inválido.<br>";
            errores = true;
            jf.txtNombreResp.setText("");
        }
        if (!jf.txtApPatResp.getText().isEmpty()&&!(jf.txtApPatResp.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido paterno del responsable inválido.<br>";
            errores = true;
            jf.txtApPatResp.setText("");
        }
        if (!jf.txtApMaternoResp.getText().isEmpty()&&!(jf.txtApMaternoResp.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido materno del responsable inválido.<br>";
            errores = true;
            jf.txtApMaternoResp.setText("");
        }

        //Campos opcionales
        if (!jf.txtTelefonoEst.getText().isEmpty() && !jf.txtTelefonoEst.getText().matches("[0-9]{7,15}")) {
            txtError += "- El número de teléfono es inválido.<br>";
            errores = true;
            jf.txtTelefonoEst.setText("");
        }

        //Datos domicilio del establecimiento
        //Campos obligatorios
        /*if (!jf.txtCalle.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- El nombre de la calle es inválida.<br>";
            errores = true;
            jf.txtCalle.setText("");
        }*/
        /*if (!(jf.txtNumero.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚa-zA-Z0-9/-]{1,4})"))) {
            txtError += "- El número de la dirección es inválido.";
            errores = true;
            jf.txtNumero.setText("");
        }*/

        if (!jf.txtEstado.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+")) {
            txtError += "- El nombre del estado es inválido.<br>";
            errores = true;
            jf.txtEstado.setText("");
        }

        //Campos no obligatorios
        if ((!jf.txtColonia.getText().isEmpty() && !jf.txtColonia.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9]+[ ]*)+"))) {
            txtError += "- El nombre de la colonia es inválida.<br>";
            errores = true;
            jf.txtColonia.setText("");
        }
        if ((!jf.txtCP.getText().isEmpty() && !jf.txtCP.getText().matches("([1-9]{1}[0-9]{4})"))) {
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

    public Establecimiento getDataEstablishmetnForm() {
        //Establecimiento
        String nombre, resp, resp_appat, resp_apmat, telefono;

        //Datos establecimiento
        nombre = jFAgregarEstablecimiento.txtNombreEstablecimiento.getText();
        resp = jFAgregarEstablecimiento.txtNombreResp.getText();
        resp_appat = jFAgregarEstablecimiento.txtApPatResp.getText();
        resp_apmat = jFAgregarEstablecimiento.txtApMaternoResp.getText();
        telefono = jFAgregarEstablecimiento.txtTelefonoEst.getText();
        Establecimiento est = new Establecimiento(nombre, resp, resp_appat, resp_apmat, telefono);
        return est;
    }

    public Domicilio getDataAddressForm() {
        //Domicilio
        String calle, numero, colonia, codigo_p, municipio, entidad, estado;
        //Datos domicilio del establecimiento
        calle = jFAgregarEstablecimiento.txtCalle.getText();
        numero = jFAgregarEstablecimiento.txtNumero.getText();
        colonia = jFAgregarEstablecimiento.txtColonia.getText();
        codigo_p = jFAgregarEstablecimiento.txtCP.getText();
        municipio = "" + jFAgregarEstablecimiento.cbMunicipioDomicilioActual.getSelectedItem();
        entidad = "" + jFAgregarEstablecimiento.cbEntidadLocalidadDomicilioActual.getSelectedItem();
        estado = jFAgregarEstablecimiento.txtEstado.getText();
        Domicilio domicilio = new Domicilio(calle, numero, colonia, codigo_p, municipio, entidad, estado);
        return domicilio;
    }
}
