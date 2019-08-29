/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Domicilio;
import Modelo.Empleado;
import Modelo.Establecimiento;
import Modelo.NewHibernateUtil;
import Modelo.Sesion;
import Modelo.Tabla;
import Vista.Administrador.JFAgregarEmpleado;
import Vista.Administrador.JFEmpleados;
import Vista.Administrador.JFEstablecimientos;
import Vista.JFLogin;
import Vista.Mensaje;
import Vista.MensajeExitoso;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ControladorEmpleado {

    JFEmpleados jFEmpleados = new JFEmpleados();
    Sesion sesion;
    Empleado empleado;

    public ControladorEmpleado(JFEmpleados jFEmpleados, Sesion sesion, Empleado empleado) {
        this.jFEmpleados = jFEmpleados;
        this.empleado = empleado;
        this.sesion = sesion;
        this.jFEmpleados.tablaEmpleados = tablaEmpleados(this.jFEmpleados.tablaEmpleados);

        this.jFEmpleados.btn_AgregarEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Administrador.JFAgregarEmpleado jFAgregarEmpleado = new JFAgregarEmpleado();
                Empleado emp = new Empleado();
                jFAgregarEmpleado.labelAccion.setText("Registrar Empleado");
                ControladorAccionesEmpleado controladorAgregarEmpleado = new ControladorAccionesEmpleado(jFAgregarEmpleado, empleado, sesion);
                jFEmpleados.dispose();
                jFAgregarEmpleado.setVisible(true);
            }
        });

        this.jFEmpleados.btn_Responsable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFEmpleados.tablaEmpleados.getSelectedRow();
                int id_empleado;
                if (fila != -1) {
                    id_empleado = Integer.parseInt(jFEmpleados.tablaEmpleados.getValueAt(fila, 0).toString());
                    if (actualizarResponsable(id_empleado)) {
                        MensajeExitoso msg = new MensajeExitoso(jFEmpleados, true);
                        msg.msg.setText("<html>La asignación del responsable de la UNEME - CAPA se ha realizado correctamente.</html>");
                        msg.setVisible(true);
                    } else {
                        Mensaje msg = new Mensaje(jFEmpleados, true);
                        msg.msg.setText("<html>Ocurrio un error al actualizar al responsable de la UNEME - CAPA<br>Verifique que el trabajador se encuentre activo.</html>");
                        msg.setVisible(true);
                    }
                }
            }
        });

        this.jFEmpleados.btn_Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFEmpleados.tablaEmpleados.getSelectedRow();

                if (fila >= 0) {
                    Vista.Administrador.JFAgregarEmpleado jFAgregarEmpleado = new JFAgregarEmpleado();

                    int id_empleado = Integer.parseInt(jFEmpleados.tablaEmpleados.getValueAt(jFEmpleados.tablaEmpleados.getSelectedRow(), 0) + "");

                    jFAgregarEmpleado.labelAccion.setText("Actualizar Empleado - " + jFEmpleados.tablaEmpleados
                            .getValueAt(jFEmpleados.tablaEmpleados.getSelectedRow(), 1) + "");
                    empleado.setIdEmpleado(id_empleado);

                    Sesion sesion = getUserEmployee(id_empleado);

                    ControladorAccionesEmpleado controladorAgregarEmpleado = new ControladorAccionesEmpleado(jFAgregarEmpleado, empleado, sesion);
                    jFEmpleados.dispose();
                    jFAgregarEmpleado.setVisible(true);
                } else {
                    Mensaje jf = new Mensaje(jFEmpleados, true);

                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un registro para modificar los datos del empleado</html>");

                    jf.setVisible(true);

                }

            }

        });

        this.jFEmpleados.btn_Establecimientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFEstablecimientos jFEstablecimientos = new JFEstablecimientos();
                Establecimiento establecimiento = new Establecimiento();
                Domicilio domicilio = new Domicilio();
                ControladorEstablecimiento controladorEstablecimiento = new ControladorEstablecimiento(jFEstablecimientos, establecimiento, domicilio);

                jFEmpleados.dispose();

                jFEstablecimientos.setVisible(true);
            }
        });

        this.jFEmpleados.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFEmpleados.dispose();
                jFLogin.setVisible(true);
            }

        });
    }

    public JTable tablaEmpleados(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(elQueOrdena);
        Tabla t;
        modelo.addColumn(" I D ");
        modelo.addColumn(" N O M B R E ");
        modelo.addColumn(" C É D U L A ");
        modelo.addColumn(" P U E S T O ");
        modelo.addColumn("E S T A D O");

        List<Empleado> listaEmpleados = getInfoEmployees();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Object[] filas = new Object[5];
            filas[0] = listaEmpleados.get(i).getIdEmpleado();
            filas[1] = listaEmpleados.get(i).getNombre() + " " + listaEmpleados.get(i).getApellidoPaterno() + " " + listaEmpleados.get(i).getApellidoMaterno();
            filas[2] = listaEmpleados.get(i).getCedula();
            filas[3] = listaEmpleados.get(i).getRol();
            filas[4] = listaEmpleados.get(i).getStatusEmpleado();
            modelo.addRow(filas);
        }

        tabla.setModel(modelo);
        t = new Tabla(tabla);
        t.packColumn(1);
        t.packColumn(2);
        t.packColumn(3);
        return tabla;
    }

    public void addEmployee(Empleado empleado) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sessionFactory.openSession();
        empleado.setStatusEmpleado("Activo");
        Transaction transaction = session.beginTransaction();
        session.save(empleado);
        session.flush();
        session.clear();
        session.close();
    }

    public List getInfoEmployees() {
        //Empleado empleado = new Empleado();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        List<Empleado> empleados = s.createQuery("from Empleado")
                .list();
        s.flush();
        s.clear();
        s.close();
        return empleados;
    }

    public boolean actualizarResponsable(int id) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        s = sf.openSession();
        Empleado empleado = new Empleado();

        try {
            s.beginTransaction();
            empleado = (Empleado) s.createQuery("from Empleado e where e.statusEmpleado=1 and e.idEmpleado=?").setParameter(0, id).uniqueResult();

            Establecimiento est = (Establecimiento) s.load(Establecimiento.class, 0);

            if (empleado != null) {
                est.setNombreResponsable(empleado.getNombre());
                est.setApellidoPaternoResponsable(empleado.getApellidoPaterno());
                est.setApellidoMaternoResponsable(empleado.getApellidoMaterno());
                s.update(est);
                s.getTransaction().commit();
                s.flush();
                s.clear();
                s.close();
                return true;
            } else {
                s.flush();
                s.clear();
                s.close();
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public Sesion getUserEmployee(int id) {
        Sesion sesion = new Sesion();
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sessionFactory.openSession();
            sesion = (Sesion) session.createQuery("from Sesion s where s.idEmpleado=?")
                    .setParameter(0, id)
                    .uniqueResult();
            session.flush();
            session.clear();
            session.close();
            return sesion;
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return sesion;
    }

    public boolean updateEmployee(int id_empleado) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            String hql = "UPDATE Empleado e SET e.statusEmpleado=2 WHERE e.idEmpleado=?";
            int result = session.createQuery(hql)
                    .setParameter(0, id_empleado)
                    .executeUpdate();
//
//            
//            session.beginTransaction();
//            session.update(empleado);
//            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
