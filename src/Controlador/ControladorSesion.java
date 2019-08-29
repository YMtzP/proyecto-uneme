/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import Modelo.*;
import Vista.Administrador.JFEmpleados;
import Vista.Coordinador.JFEventos;
import Vista.Medico.JFPacientesMedico;
import Vista.Psicologo.JFPacientesPsicologo;
import Vista.Recepcionista.JFCitas;
import Vista.TrabajadorSocial.JFPacientesTrabajadorSocial;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author YareMtz
 */
public class ControladorSesion {

    JFLogin jFLogin;
    Sesion sesion;
    Empleado empleado;

    public ControladorSesion(JFLogin jFLogin, Sesion sesion, Empleado empleado) {
        this.jFLogin = jFLogin;
        this.sesion = sesion;
        this.empleado = empleado;

        this.jFLogin.ingresar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    initLogin();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorSesion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.jFLogin.txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        initLogin();
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorSesion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
    }

    public Sesion verificarSesion(String user, String pass) {
        Sesion sesion = new Sesion();
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sessionFactory.openSession();
        sesion = (Sesion) session.createQuery("from Sesion s where s.usuario like ? and s.contrasenia like ?")
                .setParameter(0, user).setParameter(1, pass)
                .uniqueResult();
        session.flush();
        session.close();
        return sesion;
    }

    public Empleado getEmployee(int idEmpleado) {
        Empleado empleado = new Empleado();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();

        empleado = (Empleado) s.createQuery("from Empleado s where idEmpleado=?")
                .setParameter(0, idEmpleado).uniqueResult();
        s.flush();
        s.close();
        return empleado;
    }

    public void initLogin() throws ParseException {
        String user, pass;
        user = jFLogin.txtUser.getText();
        pass = jFLogin.txtPassword.getText();
        Sesion sesion1 = verificarSesion(user, pass);
        Empleado empleado = new Empleado();
        if (sesion1 != null) {
            if (sesion1.getUsuario().equals(user) && sesion1.getContrasenia().equals(pass)) {
                if (sesion1.getIdEmpleado()==null) {
                    //Es admin
                    //Empleado empleado=new Empleado();
                    Vista.Administrador.JFEmpleados jFEmpleados = new JFEmpleados();
                    ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jFEmpleados, sesion1, empleado);
                    jFLogin.dispose();
                    jFEmpleados.setVisible(true);
                } else {
                    empleado = getEmployee(sesion1.getIdEmpleado());

                    switch (empleado.getRol()) {
                        case ("Psicologo"):
                            Vista.Psicologo.JFPacientesPsicologo jFPacientesPsicologo = new JFPacientesPsicologo();
                            Paciente paciente = new Paciente();
                            Expediente expediente = new Expediente();
                            ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(jFPacientesPsicologo, empleado, expediente);
                            jFPacientesPsicologo.setVisible(true);
                            jFLogin.dispose();
                            break;
                        case ("Trabajador Social"):
                            Vista.TrabajadorSocial.JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
                            FichaDeIdentificacion ficha = new FichaDeIdentificacion();
                            ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
                            jFFichasIdentificacion.NumEmpleado.setText(empleado.getIdEmpleado() + "");
                            jFFichasIdentificacion.NumEmpleado.setVisible(false);
                            jFFichasIdentificacion.setVisible(true);
                            jFLogin.dispose();
                            break;
                        case ("Recepcionista"):
                            Vista.Recepcionista.JFCitas jFCitas = new JFCitas();
                            ControladorAgendaPacientes controladorAgendaPacientes = new ControladorAgendaPacientes(jFCitas, empleado);
                            jFCitas.setVisible(true);
                            jFLogin.dispose();
                            break;
                        case ("Medico"):
                            Vista.Medico.JFPacientesMedico jFPacientesMedico = new JFPacientesMedico();
                            expediente = new Expediente();
                            ControladorPacientesMedico controladorPacientesMedico = new ControladorPacientesMedico(jFPacientesMedico, empleado, expediente);
                            jFPacientesMedico.setVisible(true);
                            jFLogin.dispose();
                            break;
                        case ("Coordinador"):
                            Vista.Coordinador.JFEventos jFEventos = new JFEventos();
                            Extramuro extramuro = new Extramuro();
                            try {
                                ControladorEventos controladorEventos = new ControladorEventos(jFEventos, extramuro);
                                jFEventos.setVisible(true);
                                jFLogin.dispose();
                            } catch (ParseException ex) {
                                Logger.getLogger(ControladorSesion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                }
            } else {
                Mensaje jf = new Mensaje(jFLogin, true);

                jf.msg.setText("<html>Datos incorrectos...<br>Favor de verificar sus datos</html>");
                jf.setVisible(true);
            }

        } else {
            Mensaje jf = new Mensaje(jFLogin, true);

            jf.msg.setText("<html>Datos incorrectos...<br>Favor de verificar sus datos</html>");
            jf.setVisible(true);
        }
    }

}
