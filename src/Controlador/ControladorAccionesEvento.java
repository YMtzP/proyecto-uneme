/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Extramuro;
import Modelo.ExtramuroEmpleado;
import Modelo.NewHibernateUtil;
import Vista.Coordinador.JFAgregarEvento;
import Vista.Coordinador.JFEventos;
import Vista.Mensaje;
import Vista.MensajeExitoso;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.hibernate.*;

/**
 *
 * @author YareMtz
 */
public class ControladorAccionesEvento {

    JFAgregarEvento jFAgregarEvento;
    JFEventos jFEventos;
    Extramuro extramuro;

    public ControladorAccionesEvento(JFAgregarEvento jFAgregarEvento, Extramuro extramuro, JFEventos jFEventos) throws ParseException {
        this.jFAgregarEvento = jFAgregarEvento;
        this.extramuro = extramuro;
        this.jFEventos = jFEventos;
        DatePicker dp = new DatePicker();
        listaEmpleados(jFAgregarEvento);

        if (this.jFAgregarEvento.etiquetaAccion.getText().equals("Editar evento")) {
            int id_evento = extramuro.getIdExtramuro();
            Extramuro evento = getEvent(id_evento);

            // Obtengo empleados responsables del evento
            cargarEmpleadosResponsablesALista(jFAgregarEvento.listaempleados, id_evento);
            jFAgregarEvento.listaempleados.setEnabled(false);

            this.jFAgregarEvento.pdFechaEvento.setText(dp.StringToString(evento.getFecha()) + "");
            this.jFAgregarEvento.tp_HoraEvento.setText(evento.getHora().toString());
            this.jFAgregarEvento.txtActividad.setText(evento.getActividad());
            this.jFAgregarEvento.txtLugar.setText(evento.getLugar());

            this.jFAgregarEvento.btn_GuardarEvento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (validarEvento(jFAgregarEvento)) {
                        Extramuro event = new Extramuro();
                        try {
                            event = getInfoFormEvent(jFAgregarEvento);
                            event.setIdExtramuro(extramuro.getIdExtramuro());
                            if (updateEvent(event)) {
                                MensajeExitoso msg = new MensajeExitoso(jFAgregarEvento, true);
                                msg.TituloMsg.setText("Actualización exitosa");
                                msg.msg.setText("<html>La actualización del evento se<br> ha realizado correctamente.</html>");
                                msg.setVisible(true);
                                JFEventos jFEventos = new JFEventos();
                                ControladorEventos controladorEventos = new ControladorEventos(jFEventos, extramuro);
                                jFEventos.setVisible(true);
                                jFAgregarEvento.dispose();

                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(ControladorAccionesEvento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

            });

        } else {
            this.jFAgregarEvento.listaempleados.setSelectedIndex(0);
            this.jFAgregarEvento.tp_HoraEvento.setTime(LocalTime.now());

            if (jFEventos.datePickerEventos.getDate().isBefore(LocalDate.now())) {
                jFAgregarEvento.pdFechaEvento.setDate(LocalDate.now());
            } else {
                jFAgregarEvento.pdFechaEvento.setDate(jFEventos.datePickerEventos.getDate());
            }

            this.jFAgregarEvento.btn_GuardarEvento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    try {

                        if (validarEvento(jFAgregarEvento)) {
                            Extramuro extramuro = getInfoFormEvent(jFAgregarEvento);
                            List responsables = getEmployeerForm(jFAgregarEvento.listaempleados);
                            addEvent(extramuro, responsables);
                            JFEventos jFEventos = new JFEventos();
                            extramuro = new Extramuro();
                            
                            ControladorEventos controladorEventos = new ControladorEventos(jFEventos, extramuro);
                            jFEventos.setVisible(true);
                            jFAgregarEvento.dispose();
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorAccionesEvento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
        }

        this.jFAgregarEvento.btn_CancelarRegistroEvento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Coordinador.JFEventos jFEventos = new JFEventos();
                Extramuro extramuro = new Extramuro();
                try {
                    ControladorEventos controladorEventos = new ControladorEventos(jFEventos, extramuro);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAccionesEvento.class.getName()).log(Level.SEVERE, null, ex);
                }
                jFEventos.setVisible(true);
                jFAgregarEvento.dispose();
            }

        });

    }

    public boolean addEvent(Extramuro extramuro, List emp) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            Transaction transaction = s.beginTransaction();
            int id = (Integer) s.save(extramuro);
            //s.getTransaction().commit();
            s.flush();
            s.close();
            return addEmployeerFromEvento(id, emp);
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean updateEvent(Extramuro extramuro) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(extramuro);
            session.getTransaction().commit();
            session.flush();
            session.clear();
            session.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean addEmployeerFromEvento(int idEvento, List e) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            ExtramuroEmpleado ee = new ExtramuroEmpleado();

            s = sf.openSession();
            Transaction transaction = s.beginTransaction();

            for (int i = 0; i < e.size(); i++) {
                ee = new ExtramuroEmpleado();
                ee.setIdEmpleado(Integer.parseInt(e.get(i).toString()));
                ee.setIdExtramuro(idEvento);
                s.save(ee);
            }
            s.getTransaction().commit();
            s.flush();
            s.close();
            return true;
        } catch (HibernateException ex) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
            s.getTransaction().rollback();
            return false;
        }
    }

    public Extramuro getInfoFormEvent(JFAgregarEvento formEventos) throws ParseException {
        Extramuro extramuro = new Extramuro();
        //DatePicker ps = new DatePicker();

        String fecha = formEventos.pdFechaEvento.getDate().toString();
        //.format(DateTimeFormatter.ISO_DATE);
        String hora = formEventos.tp_HoraEvento.getTime().format(DateTimeFormatter.ISO_TIME);

        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        Date convertido = fechaHora.parse(fecha);

        fechaHora = new SimpleDateFormat("HH:mm");
        Date convertido2 = fechaHora.parse(hora);

        try {
            extramuro.setActividad(formEventos.txtActividad.getText());
            extramuro.setLugar(formEventos.txtLugar.getText());
            extramuro.setFecha(convertido);
            extramuro.setHora(convertido2);
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }

        return extramuro;
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

    //Consulta join empleado extramuro 
    public ArrayList getEmployeerForm(JList empleados) {
        ArrayList responsables = new ArrayList();
        String datosempleado;
        String[] parts;
        String id;
        int no = empleados.getModel().getSize();

        Object[] s = empleados.getSelectedValues();

        try {
            int e = 0;
            for (int i = 0; i < no; i++) {
                if (empleados.isSelectedIndex(i)) {
                    datosempleado = empleados.getModel().getElementAt(i).toString();
                    parts = datosempleado.split(" - ");
                    id = parts[0];
                    e = Integer.parseInt(id);

                    responsables.add(e);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }
        return responsables;
    }

    public boolean validarEvento(JFAgregarEvento jf) {
        String txtError = "<html>Favor de verificar los datos.<br><br>";
        Boolean errores = false;

        if (jf.txtActividad.getText().isEmpty() ){
                //|| !jf.txtActividad.getText().matches("([ñÑÀÁÉÈÌÍÓÒÙÚàèìòùáéíóúa-zA-Z0-9.]+[ ]*)+")) {
            txtError += "- Nombre del evento inválido.<br>";
            errores = true;
            jf.txtActividad.setText("");
        }

        if (jf.txtLugar.getText().isEmpty()) {
            txtError += "- Nombre del lugar inválido.<br>";
            errores = true;
            jf.txtActividad.setText("");
        }

        if (jFAgregarEvento.pdFechaEvento.getText().isEmpty() || jFAgregarEvento.pdFechaEvento.getDate().isBefore(LocalDate.now())) {
            txtError += "- Fecha inválida<br>";
            errores = true;
            jf.pdFechaEvento.setText("");
        }
        LocalTime hora_in = (LocalTime.of(8, 0)), hora_fin = (LocalTime.of(14, 30));

        if (jFAgregarEvento.tp_HoraEvento.getText().isEmpty() || jFAgregarEvento.tp_HoraEvento.getTime().isBefore(hora_in) || jFAgregarEvento.tp_HoraEvento.getTime().isAfter(hora_fin)) {
            txtError += "- La hora debe estar en el horario de la clinica<br> (8:00am - 2:30pm)";
            errores = true;
            jf.tp_HoraEvento.setText("");
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

    public Extramuro getEvent(int id_evento) {
        Extramuro extramuro = new Extramuro();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            extramuro = (Extramuro) s.createQuery("from Extramuro where idExtramuro=?")
                    .setParameter(0, id_evento)
                    .uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }

        return extramuro;
    }

    public List getEmpleadosEvento(int id_evento) {
        List responsables = null;
        Empleado empleado = new Empleado();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;

        try {
            s = sf.openSession();
            responsables = s.createSQLQuery("select empleado.id_empleado, empleado.nombre, empleado.apellido_paterno, "
                    + "empleado.apellido_materno from empleado join extramuroempleado on "
                    + "empleado.id_empleado=extramuroempleado.id_empleado where extramuroempleado.id_extramuro=?")
                    .addScalar("id_empleado").addScalar("nombre").addScalar("apellido_paterno").addScalar("apellido_materno")
                    .setParameter(0, id_evento).list();
            s.flush();
            s.clear();
            s.close();
        } catch (HibernateException e) {
            Logger.getLogger(ControladorAccionesEstablecimiento.class.getName()).log(Level.SEVERE, null, e);
        }

        return responsables;
    }

    public void listaEmpleados(JFAgregarEvento jf) {
        DefaultListModel modelo = new DefaultListModel();
        List<Empleado> listaEmpleados = getInfoEmployees();
        String emp;

        for (int i = 0; i < listaEmpleados.size(); i++) {
            emp = listaEmpleados.get(i).getIdEmpleado() + " - " + listaEmpleados.get(i).getNombre() + " " + listaEmpleados.get(i).getApellidoPaterno() + " " + listaEmpleados.get(i).getApellidoMaterno();
            modelo.addElement(emp);
        }

        jf.listaempleados.setModel(modelo);

    }

    public void cargarEmpleadosResponsablesALista(JList list, int evento) {
        DefaultListModel modelo = new DefaultListModel();
        List<Empleado> listaResponsables = getEmpleadosEvento(evento);
        int i = 0;
        String resp;
        for (Iterator iterator = listaResponsables.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            resp = objects[0] + " - " + objects[1] + " " + objects[2] + " " + objects[3];
            modelo.addElement(resp);
        }
        list.setModel(modelo);
    }
}
