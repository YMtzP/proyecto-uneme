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
import Vista.Administrador.JFAgregarEstablecimiento;
import Vista.Administrador.JFEmpleados;
import Vista.Administrador.JFEstablecimientos;
import Vista.JFLogin;
import Vista.Mensaje;


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

/**
 *
 * @author YareMtz
 */
public class ControladorEstablecimiento {

    JFEstablecimientos jFEstablecimientos;
    Establecimiento establecimiento;
    Domicilio domicilio;

    public ControladorEstablecimiento(JFEstablecimientos jFEstablecimientos, Establecimiento establecimiento, Domicilio domicilio) {
        this.jFEstablecimientos = jFEstablecimientos;
        this.establecimiento = establecimiento;

        this.jFEstablecimientos.tablaEstablecimientos = tableEstablishments(this.jFEstablecimientos.tablaEstablecimientos);

        this.jFEstablecimientos.btnAgregarEstablecimiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Administrador.JFAgregarEstablecimiento jFAgregarEstablecimiento = new JFAgregarEstablecimiento();
                jFAgregarEstablecimiento.labelAccion.setText("Agregar Establecimiento");
                ControladorAccionesEstablecimiento controladorAgregarEstablecimiento = new ControladorAccionesEstablecimiento(jFAgregarEstablecimiento, establecimiento, domicilio);
                jFEstablecimientos.dispose();
                jFAgregarEstablecimiento.setVisible(true);
            }
        });

        this.jFEstablecimientos.btn_Empleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFEmpleados jf = new JFEmpleados();
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                ControladorEmpleado controladorEmpleado = new ControladorEmpleado(jf, sesion, empleado);
                
                jFEstablecimientos.dispose();
                jFEstablecimientos.setVisible(false);
                jf.setVisible(true);

            }

        });

        this.jFEstablecimientos.btnAct.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int fila = jFEstablecimientos.tablaEstablecimientos.getSelectedRow();
                if (fila >= 0) {
                    Vista.Administrador.JFAgregarEstablecimiento jFAgregarEstablecimiento = new JFAgregarEstablecimiento();
                    int id_establecimiento = Integer.parseInt(jFEstablecimientos.tablaEstablecimientos.getValueAt(jFEstablecimientos.tablaEstablecimientos.getSelectedRow(), 0) + "");
                    //Establecimiento est = new Establecimiento();
                    jFAgregarEstablecimiento.labelAccion.setText("Actualizar información del establecimiento");
                    establecimiento.setIdEstablecimiento(id_establecimiento);

                    domicilio.setIdDomicilio(establecimiento.getIdDomicilio());

                    ControladorAccionesEstablecimiento controladorActualizarEstablecimiento = new ControladorAccionesEstablecimiento(jFAgregarEstablecimiento, establecimiento, domicilio);
                    jFEstablecimientos.dispose();
                    jFAgregarEstablecimiento.setVisible(true);
                } else {
                    Vista.Mensaje jf = new Mensaje(jFEstablecimientos, true);

                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar un registro para modificar los datos del extablecimiento</html>");
                    
                    jf.setVisible(true);


                }

            }
        });

        this.jFEstablecimientos.tablaEstablecimientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = jFEstablecimientos.tablaEstablecimientos;
                int fila = table.rowAtPoint(e.getPoint());
                int columna = table.columnAtPoint(e.getPoint());
                if ((fila > -1) && (columna > -1)) {
                    fila = table.getSelectedRow();
                }
            }

        });
        this.jFEstablecimientos.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFEstablecimientos.dispose();
                jFLogin.setVisible(true);

            }

        });
    }

    public JTable tableEstablishments(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        Tabla tabla;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
        table.setRowSorter(elQueOrdena);
        JTable tbl = table;
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Responsable");
        model.addColumn("Telefono");
        model.addColumn("Domicilio");

        List<Establecimiento> listEstablishments = getInfoEstablishments();
        for (int i = 0; i < listEstablishments.size(); i++) {
            Object[] filas = new Object[5];
            filas[0] = listEstablishments.get(i).getIdEstablecimiento();
            filas[1] = listEstablishments.get(i).getNombre();
            filas[2] = listEstablishments.get(i).getNombreResponsable() + " " + listEstablishments.get(i).getApellidoPaternoResponsable()
                    + " " + listEstablishments.get(i).getApellidoMaternoResponsable();
            filas[3] = listEstablishments.get(i).getTelefonoEstablecimiento();

            Domicilio domicilio = getAddresFromEstablishment(listEstablishments.get(i).getIdDomicilio());

            filas[4] = domicilio.getCalle() + " #" + domicilio.getNumero() + " Col. " + domicilio.getColonia() + " C.P. "
                    + domicilio.getCodigoPostal() + " " + domicilio.getDelegacionMunicipio() + ", " + domicilio.getEntidadLocalidad()
                    + " " + domicilio.getEstado();
            model.addRow(filas);
        }
        tbl.setModel(model);
        tabla = new Tabla(tbl);
        tabla.packColumn(0);
        tabla.packColumn(1);
        tabla.packColumn(2);
        tabla.packColumn(3);
        tabla.packColumn(4);
        return tbl;
    }

    public List getInfoEstablishments() {
        Establecimiento establecimiento = new Establecimiento();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        List<Establecimiento> establecimientos = null;
        try {
            s = sf.openSession();
            establecimientos = s.createQuery("from Establecimiento")
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);

        }

        return establecimientos;
    }

    public Domicilio getAddresFromEstablishment(int id) {

        Domicilio domicilio = new Domicilio();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            domicilio = (Domicilio) s.get(Domicilio.class, id);
            s.flush();
            s.close();
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            domicilio = null;
        }

        return domicilio;
    }
    
    


}
