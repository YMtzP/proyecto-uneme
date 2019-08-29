/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AbstractJasperReports;
import Modelo.Connection;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.NotaMedica;
import Modelo.Tabla;
import Vista.Medico.JFAgregarNotaMedica;
import Vista.Medico.JFNotasMedicasPaciente;
import Vista.Medico.JFPacientesMedico;
import Vista.Mensaje;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ControladorNotaMedicaPaciente {

    JFNotasMedicasPaciente fNotasMedicasPaciente;
    Expediente expediente;
    Empleado empleado;
    String paciente;

    public ControladorNotaMedicaPaciente(JFNotasMedicasPaciente fNotasMedicasPaciente, Expediente expediente, Empleado empleado, String paciente) {
        this.fNotasMedicasPaciente = fNotasMedicasPaciente;
        this.expediente = expediente;
        this.empleado = empleado;
        this.paciente = paciente;
        tablaNotasMedicas();
        this.fNotasMedicasPaciente.btnAgregarNotaMed1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFAgregarNotaMedica agregarNotaMedica = new JFAgregarNotaMedica();
                agregarNotaMedica.labeNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());
                agregarNotaMedica.etiquetaAccion.setText("Registrar Nota Médica");
                agregarNotaMedica.datePickerFechaNM.setDateToToday();
                NotaMedica notaMedica = new NotaMedica();
                try {
                    ControladorAccionesNotaMedica controladorAccionesNotaMedica = new ControladorAccionesNotaMedica(agregarNotaMedica, expediente, empleado, notaMedica, paciente);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorNotaMedicaPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }

                agregarNotaMedica.setVisible(true);
                fNotasMedicasPaciente.dispose();
            }
        });
        
        this.fNotasMedicasPaciente.btnGenerarPDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int fila = fNotasMedicasPaciente.tablaNotasMedicasPac.getSelectedRow();
                if (fila >= 0) { //se selecciono un paciente
                    int id_nota = (int) fNotasMedicasPaciente.tablaNotasMedicasPac.getValueAt(fila, 0);
                    NotaMedica notaMedica = obtenerNotaMedica(id_nota);
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("notaTratamiento", id_nota);
                    parametros.put("fecha_nota", notaMedica.getFechaNotaMedica().toString());
                    AbstractJasperReports.createNotaTratamiento(parametros, Connection.getConnection(), "src/Pdf/nota_medica.jasper");
                    AbstractJasperReports.showViewer();
                }
            }
        });

        this.fNotasMedicasPaciente.btnCancelarNotaMed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Vista.Medico.JFPacientesMedico jFPacientesMedico = new JFPacientesMedico();
                Expediente expediente = new Expediente();
                ControladorPacientesMedico controladorPacientesMedico = new ControladorPacientesMedico(jFPacientesMedico, empleado, expediente);
                jFPacientesMedico.setVisible(true);
                fNotasMedicasPaciente.dispose();
            }
        });

        this.fNotasMedicasPaciente.btnVerNotaMed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                JFAgregarNotaMedica agregarNotaMedica = new JFAgregarNotaMedica();
                agregarNotaMedica.labeNumExp.setText("Núm. Expediente: " + expediente.getNumExpediente());

                int fila = fNotasMedicasPaciente.tablaNotasMedicasPac.getSelectedRow();
                if (fila >= 0) { //se selecciono un paciente
                    int id_nota = (int) fNotasMedicasPaciente.tablaNotasMedicasPac.getValueAt(fila, 0);
                    NotaMedica notaMedica = obtenerNotaMedica(id_nota);
                    
                    try {
                        
                        agregarNotaMedica.etiquetaAccion.setText("Actualizar Nota Médica");
                        ControladorAccionesNotaMedica controladorAccionesNotaMedica = new ControladorAccionesNotaMedica(agregarNotaMedica, expediente, empleado, notaMedica, paciente);
                        agregarNotaMedica.setVisible(true);
                        fNotasMedicasPaciente.dispose();
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorNotaMedicaPaciente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Mensaje jf = new Mensaje(fNotasMedicasPaciente, true);
                    jf.TituloMsg.setText("Error:");
                    jf.msg.setText("<html>Debe seleccionar una nota médica para visualizarla/editarla.</html>");
                    jf.setVisible(true);
                }

            }

        });

    }

    public List getNotasMedicas(Expediente expediente) {
        List<NotaMedica> lista = new ArrayList<>();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            lista = s.createQuery("from NotaMedica where idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "aqui es:cc");
        }
        return lista;
    }

    public NotaMedica obtenerNotaMedica(int id) {
        NotaMedica nm = new NotaMedica();
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s;
        try {
            s = sf.openSession();
            nm = (NotaMedica) s.createQuery("from NotaMedica nm where nm.idNotaMedica=?")
                    .setParameter(0, id).uniqueResult();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return nm;
    }

    public void tablaNotasMedicas() {
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
        fNotasMedicasPaciente.tablaNotasMedicasPac.setRowSorter(elQueOrdena);
        Tabla t;
        modelo.addColumn("Id    ");
        modelo.addColumn("Fecha");
        modelo.addColumn("Resumen                                       .");
            

        List<NotaMedica> listaNotas = getNotasMedicas(expediente);
        for (int i = 0; i < listaNotas.size(); i++) {
            Object[] filas = new Object[4];
            filas[0] = listaNotas.get(i).getIdNotaMedica();
            filas[1] = listaNotas.get(i).getFechaNotaMedica();
            filas[2] = listaNotas.get(i).getResumen();
            modelo.addRow(filas);
        }
        fNotasMedicasPaciente.tablaNotasMedicasPac.setModel(modelo);
        
        t = new Tabla(fNotasMedicasPaciente.tablaNotasMedicasPac);
        t.packColumn(0);
        t.packColumn(1);
        t.packColumn(2);
    }
}
