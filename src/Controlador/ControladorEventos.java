/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.Coordinador.JFAgregarEvento;

import Vista.Coordinador.JFEventos;
import Vista.JFLogin;
import Vista.Mensaje;
import com.github.lgooddatepicker.components.DatePicker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author YareMtz
 */
public class ControladorEventos {

    JFEventos jFEventos;
    Extramuro extramuro;
    ExtramuroEmpleado extramuroEmpleado;

    public ControladorEventos(JFEventos jFEventos, Extramuro extramuro) throws ParseException {
        this.jFEventos = jFEventos;
        this.extramuro = extramuro;
        DatePicker fecha = new DatePicker();

        if (this.jFEventos.datePickerEventos != null) {
            fecha = this.jFEventos.datePickerEventos;
        }

        fecha.setDate(LocalDate.now());

        this.jFEventos.labelFechaSeleccionada.setText("Fecha: " + formatoFechaLetra(fecha));

        this.jFEventos.tablaEventos = tableEvents(this.jFEventos.tablaEventos, fecha);

        this.jFEventos.btnBuscarEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    DatePicker fecha = jFEventos.datePickerEventos;
                    jFEventos.labelFechaSeleccionada.setText("Fecha: " + formatoFechaLetra(fecha));
                    jFEventos.tablaEventos = tableEvents(jFEventos.tablaEventos, fecha);
                    if(fecha.getDate().isBefore(LocalDate.now())){
                        jFEventos.btn_update.setText("Ver");
                    }else{
                        jFEventos.btn_update.setText("Editar");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.jFEventos.btnAgregarEvento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFAgregarEvento jFAgregarEvento = new JFAgregarEvento();
                jFAgregarEvento.btn_GuardarEvento.setText("Guardar");
                try {
                    ControladorAccionesEvento controladorAgregarEvento = new ControladorAccionesEvento(jFAgregarEvento, extramuro, jFEventos);
                    jFAgregarEvento.setVisible(true);
                    jFEventos.dispose();
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        this.jFEventos.btn_update.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = jFEventos.tablaEventos.getSelectedRow();
                try {
                    if (fila >= 0) {
                        
                        extramuro.setIdExtramuro(Integer.parseInt(jFEventos.tablaEventos.getValueAt(fila, 0).toString()));
                        JFAgregarEvento agregarEvento = new JFAgregarEvento();
                        if(jFEventos.btn_update.getText().equals("Editar")){
                            agregarEvento.btn_GuardarEvento.setText("Actualizar");
                            agregarEvento.etiquetaAccion.setText("Editar evento");
                        }else{
                            agregarEvento.btn_GuardarEvento.setVisible(false);
                            agregarEvento.btn_GuardarEvento.setEnabled(false);
                            agregarEvento.listaempleados.setEnabled(false);
                            agregarEvento.pdFechaEvento.setEnabled(false);
                            agregarEvento.tp_HoraEvento.setEnabled(false);
                            agregarEvento.txtActividad.setEnabled(false);
                            agregarEvento.txtLugar.setEnabled(false);
                        }
                        agregarEvento.btn_GuardarEvento.setText("Actualizar");
                        agregarEvento.etiquetaAccion.setText("Editar evento");
                        ControladorAccionesEvento controladorAgregarEvento = new ControladorAccionesEvento(agregarEvento, extramuro, jFEventos);

                        agregarEvento.setVisible(true);
                        jFEventos.dispose();
                    }else{
                        Mensaje msg = new Mensaje(jFEventos, true);
                        msg.msg.setText("<html>Debe selecionar un evento para<br> editarlo.</htlm>");
                        msg.setVisible(true);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.jFEventos.btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Sesion sesion = new Sesion();
                Empleado empleado = new Empleado();
                JFLogin jFLogin = new JFLogin();
                ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
                jFEventos.dispose();
                jFLogin.setVisible(true);
            }

        });


    }

    public String formatoFechaLetra(DatePicker dp) throws ParseException {
        String f = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        f = formateador.format(formatoDate(dp));
        return f;
    }

    public Date formatoDate(DatePicker dp) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dp.getDate().toString());
        return date;
    }

    public List getAllEvents(DatePicker fecha) throws ParseException {
        List<Extramuro> eventos = null;

        try {

            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session s;
            s = sf.openSession();

            eventos = s.createQuery("from Extramuro where fecha=?")
                    .setParameter(0, formatoDate(fecha)).list();

            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }

        return eventos;
    }

    public List getIdEmployeeEvent(int idEvento) {
        List<Empleado> listaEmpleados = null;
        try {
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session s;
            s = sf.openSession();
            listaEmpleados = s.createQuery("from ExtramuroEmpleado where idExtramuro=?")
                    .setParameter(0, idEvento).list();
            s.flush();
            s.clear();
            s.close();
            return listaEmpleados;
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaEmpleados;
    }

    public ArrayList getEmployee(List<ExtramuroEmpleado> idsEmp) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session s;
            s = sf.openSession();
            Empleado empleado = new Empleado();
            for (int i = 0; i < idsEmp.size(); i++) {
                empleado = (Empleado) s.createQuery("from Empleado where idEmpleado=?")
                        .setParameter(0, idsEmp.get(i).getIdEmpleado()).uniqueResult();

                String emp = empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno();

                lista.add(emp);
            }
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    public JTable tableEvents(JTable table, DatePicker fecha) throws ParseException {
        DefaultTableModel model = new DefaultTableModel();
        Tabla tabla;
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
        table.setRowSorter(elQueOrdena);
        JTable tbl = table;
        model.addColumn("I D");
        model.addColumn("H O R A");
        model.addColumn("A C T I V I D A D");
        model.addColumn("L U G A R");
        model.addColumn("R E S P O N S A B L E S");

        //Obtiene los eventos de la fecha seleccionada
        List<Extramuro> listEvents = getAllEvents(fecha);

        for (int i = 0; i < listEvents.size(); i++) {
            Object[] filas = new Object[5];
            filas[0] = listEvents.get(i).getIdExtramuro();
            filas[1] = listEvents.get(i).getHora();
            filas[2] = listEvents.get(i).getActividad();
            filas[3] = listEvents.get(i).getLugar();

            //Obtiene los id de los empleados responsables del evento
            List<ExtramuroEmpleado> listExtEmp = getIdEmployeeEvent(listEvents.get(i).getIdExtramuro());
            String responsables = "";

            //Obtiene los nombres de los responsables del evento
            ArrayList listaResponsables = getEmployee(listExtEmp);
            //Concatena todos los nombres de los responsables
            for (int n = 0; n < listaResponsables.size(); n++) {
                responsables += "<html> ▸ " + listaResponsables.get(n) + "<br>";
            }
            responsables += "</html>";

            filas[4] = responsables;

            model.addRow(filas);
        }
        tbl.setRowHeight(150);

        tbl.setModel(model);

        tabla = new Tabla(tbl);
        tabla.packColumn(2);
        tabla.packColumn(3);
        tabla.packColumn(4);

        return tbl;
    }

}
