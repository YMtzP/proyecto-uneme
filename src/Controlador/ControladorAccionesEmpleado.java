/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Modelo.NewHibernateUtil;
import Vista.Administrador.JFAgregarEmpleado;
import Vista.Administrador.JFEmpleados;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.javafx.tk.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

import org.hibernate.*;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesEmpleado {
    
    JFAgregarEmpleado jFAgregarEmpleado;
    Empleado emp;
    Sesion sesion;
    
    public ControladorAccionesEmpleado(JFAgregarEmpleado jFAgregarEmpleado, Empleado emp, Sesion sesion) {
        this.jFAgregarEmpleado = jFAgregarEmpleado;
        this.emp = emp;
        
        this.jFAgregarEmpleado.btnCancelarRegistroEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFEmpleados jFEmpleados = new JFEmpleados();
                ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jFEmpleados, sesion, emp);
                jFAgregarEmpleado.dispose();
                jFEmpleados.setVisible(true);
                
            }
            
        });
        
        this.jFAgregarEmpleado.txtCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jFAgregarEmpleado.txtCedula.getText().length() == 10) {
                    e.consume();
                }
                
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || Character.isLetter(c))) {
                    e.consume();
                }
                
            }
        });
        
        if (this.jFAgregarEmpleado.labelAccion.getText().equals("Registrar Empleado")) {
            this.jFAgregarEmpleado.btn_GuardarEmpleado.setText("Guardar");
            this.jFAgregarEmpleado.btn_GuardarEmpleado.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // Datos empleado
                    String user, pass;
                    Empleado emp;
                    int tipo = 0;
                    if (jFAgregarEmpleado.CBPuesto.getSelectedIndex() != 4) {
                        tipo = 1;
                    }
                    if (validarDatosEmpleado(jFAgregarEmpleado) && validarUsuario(jFAgregarEmpleado, tipo)) {
                        
                        user = jFAgregarEmpleado.txtUsuario.getText();
                        pass = jFAgregarEmpleado.txtContrasenia.getText();
                        emp = getDataFormEmployee();
                        if (addEmployee(emp, user, pass)) {
                            
                            MensajeExitoso msg = new MensajeExitoso(jFAgregarEmpleado, true);
                            msg.msg.setText("<html>El registro del empleado se ha registrado con éxito.</html>");
                            msg.setVisible(true);
                            
                            JFEmpleados jFEmpleados = new JFEmpleados();
                            ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jFEmpleados, sesion, emp);
                            jFAgregarEmpleado.dispose();
                            jFEmpleados.setVisible(true);
                        }
                    }
                }
            });
        } else {
            this.jFAgregarEmpleado.btn_GuardarEmpleado.setText("Actualizar");
            //Se obtienen los datos del registro a modificar
            Empleado empleado_old = getInfoEmployee(emp.getIdEmpleado());
            Sesion s = getInfoUser(empleado_old.getIdEmpleado());
            loadData(empleado_old, s);
            this.jFAgregarEmpleado.btn_GuardarEmpleado.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        
                        if (validarDatosEmpleado(jFAgregarEmpleado)) {
                            //Actualiza empleado;

                            Empleado empleado = getDataFormEmployee();
                            empleado.setIdEmpleado(emp.getIdEmpleado());
                            
                            if (updateEmployee(empleado)) {
                                
                                if (empleado_old.getRol().equals("Promotor")) {
                                    if (!empleado.getRol().equals("Promotor")) {
                                        Sesion s = getDataFormUser();
                                        newUser(empleado, s);
                                        
                                    }
                                } else {
                                    //Actualizar usuario?
                                    if (!empleado.getRol().equals("Promotor")) {
                                        if (!jFAgregarEmpleado.txtContrasenia.getText().isEmpty() || !jFAgregarEmpleado.txtRepetirContrasenia.getText().isEmpty()) {
                                            
                                            Sesion ses = new Sesion();
                                            
                                            if (validarUsuario(jFAgregarEmpleado, 1)) {
                                                
                                                ses = getDataFormUser();
                                                ses.setIdEmpleado(emp.getIdEmpleado());
                                                ses.setIdSesion(s.getIdSesion());
                                                
                                                if (updateUser(ses)) {
                                                    
                                                    MensajeExitoso msg = new MensajeExitoso(jFAgregarEmpleado, true);
                                                    msg.msg.setText("<html>La actualización del empleado se ha registrado con éxito.</html>");
                                                    msg.setVisible(true);
                                                    
                                                    JFEmpleados jFEmpleados = new JFEmpleados();
                                                    ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jFEmpleados, sesion, emp);
                                                    jFAgregarEmpleado.dispose();
                                                    jFEmpleados.setVisible(true);
                                                }
                                            }
                                        } else {
                                            if (validarUsuario(jFAgregarEmpleado, 2)) {
                                                s.setUsuario(jFAgregarEmpleado.txtUsuario.getText());
                                                if (updateUser(s)) {
                                                    MensajeExitoso msg = new MensajeExitoso(jFAgregarEmpleado, true);
                                                    msg.msg.setText("<html>La actualización del empleado se ha registrado con éxito.</html>");
                                                    msg.setVisible(true);
                                                    
                                                    JFEmpleados jFEmpleados = new JFEmpleados();
                                                    ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jFEmpleados, sesion, emp);
                                                    jFAgregarEmpleado.dispose();
                                                    jFEmpleados.setVisible(true);
                                                }
                                            }
                                        }
                                    }
                                }
                                
                            }
                        }
                        
                    } catch (org.hibernate.exception.ConstraintViolationException exception) {
                        Logger.getLogger(ControladorAccionesEmpleado.class.getName()).log(Level.SEVERE, null, exception);
                    } catch (MySQLIntegrityConstraintViolationException ex) {
                        Logger.getLogger(ControladorAccionesEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            });
            
        }
        
        this.jFAgregarEmpleado.CBPuesto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (jFAgregarEmpleado.CBPuesto.getSelectedIndex() == 5) {
                    jFAgregarEmpleado.txtUsuario.setText("");
                    jFAgregarEmpleado.txtContrasenia.setText("");
                    jFAgregarEmpleado.txtRepetirContrasenia.setText("");
                    jFAgregarEmpleado.txtUsuario.setEditable(false);
                    jFAgregarEmpleado.txtUsuario.setEnabled(false);
                    jFAgregarEmpleado.txtContrasenia.setEditable(false);
                    jFAgregarEmpleado.txtContrasenia.setEnabled(false);
                    jFAgregarEmpleado.txtRepetirContrasenia.setEditable(false);
                    jFAgregarEmpleado.txtRepetirContrasenia.setEnabled(false);
                    //jFAgregarEmpleado.btn_GuardarEmpleado.setEnabled(false);
                } else {
                    jFAgregarEmpleado.txtUsuario.requestFocus();
                    jFAgregarEmpleado.txtUsuario.setEditable(true);
                    jFAgregarEmpleado.txtUsuario.setEnabled(true);
                    jFAgregarEmpleado.txtContrasenia.setEditable(true);
                    jFAgregarEmpleado.txtContrasenia.setEnabled(true);
                    jFAgregarEmpleado.txtRepetirContrasenia.setEditable(true);
                    jFAgregarEmpleado.txtRepetirContrasenia.setEnabled(true);
                    //jFAgregarEmpleado.btn_GuardarEmpleado.setEnabled(true);

                }
            }
        });
        
    }
    
    public boolean addEmployee(Empleado empleado, String user, String pass) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            if (empleado.getRol().equals("Promotor")) {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.save(empleado);
                session.getTransaction().commit();
                session.flush();
                session.clear();
                session.close();
                return true;
            } else {
                return addtUserFromEmployee(empleado, user, pass);
            }
            
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            
            Mensaje msg = new Mensaje(jFAgregarEmpleado, true);
            msg.msg.setText("<html>La cédula del empleado se encuentra registrada en otro empleado. Favor de verificarla</html>");
            msg.setVisible(true);
            
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw new RuntimeException(ex);
        }
        return false;
        
    }
    
    public boolean newUser(Empleado emp, Sesion sesion) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            sesion.setIdEmpleado(emp.getIdEmpleado());
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(sesion);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            
            Mensaje msg = new Mensaje(jFAgregarEmpleado, true);
            msg.msg.setText("<html>El nombre de usuario ya se encuentra registrado.<br>Favor de escoger otro</html>");
            msg.setVisible(true);
            
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw new RuntimeException(ex);
        }
        return false;
    }
    
    public boolean addtUserFromEmployee(Empleado empleado, String user, String pass) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction;
        try {
            
            Sesion sesion = new Sesion(user, pass);
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(sesion);
            //session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            int id = (Integer) session.save(empleado);
            //session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            
            sesion.setIdEmpleado(id);
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(sesion);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            
            Mensaje msg = new Mensaje(jFAgregarEmpleado, true);
            
            msg.msg.setText("<html>El nombre de usuario ya se encuentra registrado.<br>Favor de escoger otro</html>");
            jFAgregarEmpleado.txtUsuario.setText("");
            msg.setVisible(true);
            
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw new RuntimeException(ex);
        }
        return false;
    }
    
    public Empleado getInfoEmployee(int id) {
        Empleado empleado = new Empleado();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        empleado = (Empleado) s.createQuery("from Empleado where idEmpleado=?")
                .setParameter(0, id).uniqueResult();
        s.clear();
        s.close();
        return empleado;
    }
    
    public Sesion getInfoUser(int id) {
        Sesion sesion = new Sesion();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        sesion = (Sesion) s.createQuery("from Sesion where idEmpleado=?")
                .setParameter(0, id).uniqueResult();
        return sesion;
    }
    
    public boolean updateEmployee(Empleado emp) throws com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException {
        
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(emp);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            
            return true;
            
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            
            Mensaje msg = new Mensaje(jFAgregarEmpleado, true);
            msg.msg.setText("<html>La cédula del empleado se encuentra registrada en otro empleado. Favor de verificarla</html>");
            msg.setVisible(true);
            
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw new RuntimeException(ex);
        }
        return false;
    }
    
    public boolean updateUser(Sesion sesion) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            /*System.out.println(sesion.getUsuario());
            System.out.println(sesion.getIdSesion());
            System.out.println(sesion.getIdEmpleado());*/
            session.update(sesion);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (org.hibernate.exception.ConstraintViolationException cve) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            
            Mensaje msg = new Mensaje(jFAgregarEmpleado, true);
            
            msg.msg.setText("<html>El nombre de usuario ya se encuentra registrado.<br>Favor de escoger otro</html>");
            jFAgregarEmpleado.txtUsuario.setText("");
            msg.setVisible(true);
            
        } catch (RuntimeException ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw ex;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                System.out.println("Fallo el rollback");
            }
            throw new RuntimeException(ex);
        }
        return false;
    }
    
    public void loadData(Empleado empleado, Sesion sesion) {
        this.jFAgregarEmpleado.txtNombre.setText(empleado.getNombre());
        this.jFAgregarEmpleado.txtApPaterno.setText(empleado.getApellidoPaterno());
        this.jFAgregarEmpleado.txtApMaterno.setText(empleado.getApellidoMaterno());
        if (!empleado.getCedula().isEmpty()) {
            this.jFAgregarEmpleado.txtCedula.setText(empleado.getCedula());
        }
        
        switch (empleado.getRol()) {
            case ("Psicologo"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(0);
                break;
            case ("Medico"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(1);
                break;
            case ("Trabajador Social"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(2);
                break;
            case ("Recepcionista"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(3);
                break;
            case ("Coordinador"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(4);
                break;
            case ("Promotor"):
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(5);
                break;
            default:
                this.jFAgregarEmpleado.CBPuesto.setSelectedIndex(0);
                break;
        }
        
        if (empleado.getTurno().equals("vespertino")) {
            this.jFAgregarEmpleado.CBTurno.setSelectedIndex(1);
        } else {
            this.jFAgregarEmpleado.CBTurno.setSelectedIndex(0);
        }
        
        if (!empleado.getRol().equals("Promotor")) {
            
            this.jFAgregarEmpleado.txtUsuario.setText(sesion.getUsuario());
        }
        
    }
    
    public Empleado getDataFormEmployee() {
        // Datos empleado
        String nombre, ap_pat, ap_mat, cedula, puesto, turno;
        
        nombre = jFAgregarEmpleado.txtNombre.getText();
        ap_pat = jFAgregarEmpleado.txtApPaterno.getText();
        ap_mat = jFAgregarEmpleado.txtApMaterno.getText();
        cedula = jFAgregarEmpleado.txtCedula.getText();
        puesto = jFAgregarEmpleado.CBPuesto.getSelectedItem() + "";
        turno = jFAgregarEmpleado.CBTurno.getSelectedItem() + "";
        Empleado emp = new Empleado(nombre, ap_pat, ap_mat, cedula, puesto, "Activo", turno);
        return emp;
    }
    
    public Sesion getDataFormUser() {
        // Datos usuario
        String user, pass, confirmPass;
        
        user = jFAgregarEmpleado.txtUsuario.getText();
        pass = jFAgregarEmpleado.txtContrasenia.getText();
        confirmPass = jFAgregarEmpleado.txtRepetirContrasenia.getText();

        //Valida los datos del usuario, campos vacios, biene scritos
        if (validarDatosEmpleado(jFAgregarEmpleado)) {
            //Valida contraseñas iguales
            if (!pass.equals(confirmPass)) {
                return null;
            } else {
                //Datos correctos, actualizacion
                Sesion sesion = new Sesion(user, pass);
                return sesion;
            }
        } else {
            return null;
        }
        
    }
    
    public boolean validarDatosEmpleado(JFAgregarEmpleado jf) {
        
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;

        //Validacion de campos obligatorios
        if (!(jf.txtNombre.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Nombre inválido<br>";
            errores = true;
            jf.txtNombre.setText("");
            // jf.btn_GuardarEmpleado.setEnabled(false);
        }
        if (!(jf.txtApPaterno.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido paterno inválido<br>";
            errores = true;
            jf.txtApPaterno.setText("");
            //   jf.btn_GuardarEmpleado.setEnabled(false);
        }
        if (!(jf.txtApMaterno.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z]+[ ]*)+"))) {
            txtError += "- Apellido materno inválido<br>";
            errores = true;
            jf.txtApMaterno.setText("");
            //jf.btn_GuardarEmpleado.setEnabled(false);
        }

        //Campos no obligatorios
        if (!jf.txtCedula.getText().isEmpty() && !jf.txtCedula.getText().matches("[ñÑÀÁÉÈÌÍÓÒÙÚa-zA-Z0-9]{10}")) {
            txtError += "- Cédula inválida<br>";
            errores = true;
            jf.txtCedula.setText("");
        }
        if (errores) {
            txtError += "";
            
            Vista.Mensaje msg = new Mensaje(jf, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);
            
            return false;
        }
        return true;
        
    }
    
    public boolean validarUsuario(JFAgregarEmpleado jf, int tipo) {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;
        //Verifica que no sea un promotor
        if (jf.txtUsuario.getText().isEmpty() || !jf.txtUsuario.getText().matches("([a-zA-Z0-9]+)")) {
            if (tipo == 2 || tipo == 1) {
                
                txtError += "- Usuario inválido (Debe tener solo letras y números)<br>";
                errores = true;
                //    jf.btn_GuardarEmpleado.setEnabled(false);
                jf.txtUsuario.setText("");
            }
        }
        if (jf.txtContrasenia.getText().isEmpty() || !jf.txtContrasenia.getText().equals(jf.txtRepetirContrasenia.getText())) {
            if (tipo == 1) {
                
                txtError += "- Las contraseñas no coinciden<br>";
                errores = true;
                
                jf.txtContrasenia.setText("");
                jf.txtRepetirContrasenia.setText("");
            }
            
        }
        
        if (errores) {
            txtError += "<html>";
            
            Vista.Mensaje msg = new Mensaje(jf, true);
            msg.msg.setText(txtError);
            msg.setVisible(true);
            
            return false;
        }
        return true;
    }
}
