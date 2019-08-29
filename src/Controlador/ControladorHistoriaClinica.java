/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.RespuestasEncuesta;
import Vista.Medico.JFHistoriaClinica;
import Vista.Medico.JFPacientesMedico;
import Vista.MensajeExitoso;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes;

/**
 *
 * @author YareMtz
 */
public class ControladorHistoriaClinica {

    JFHistoriaClinica jFHistoriaClinica;
    Expediente expediente;
    Empleado empleado;

    public ControladorHistoriaClinica(JFHistoriaClinica jFHistoriaClinica, Expediente expediente, Empleado empleado) throws ParseException {
        this.jFHistoriaClinica = jFHistoriaClinica;
        this.expediente = expediente;
        this.empleado = empleado;
        
        boolean verifica = false;
        ManejadorEncuestas me = new ManejadorEncuestas();
        if (!me.existeEncuesta(expediente, 1)) {
            if (me.crearEncuesta(empleado, expediente, 1)) {
                verifica = true;
            }
        } else {
            verifica = true;
        }

        if (verifica) {
            List<RespuestasEncuesta> lista = me.obtieneRespuestasEncuestaHistoriaClinica(expediente);
            cargarDatosFormulario(lista);
            this.jFHistoriaClinica.btnSiguiente1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario1(jFHistoriaClinica);
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSiguiente2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario2();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSiguiente3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario3();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSiguiente4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario4();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }

            });
            this.jFHistoriaClinica.btnSiguiente5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario5();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSiguiente6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario6();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSiguiente7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario7();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.btnSave8.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> listaRespuestas = obtieneRespuestasFormulario8();
                    if (me.guardarRespuestas(listaRespuestas, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFHistoriaClinica, true);
                        msg.msg.setText("<html>Se han guardado los datos de <br>la historia clinica correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFHistoriaClinica.goBack1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.btngoBack2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.goBack8.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFHistoriaClinica.btnAnterior2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(0);
                }
            });
            this.jFHistoriaClinica.btnAnterior3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.jFHistoriaClinica.btnAnterior4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.jFHistoriaClinica.btnAnterior5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.jFHistoriaClinica.btnAnterior6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.jFHistoriaClinica.btnAnterior7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(5);
                }
            });
            this.jFHistoriaClinica.btnAnterior8.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(6);
                }
            });
            this.jFHistoriaClinica.btnNext1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.jFHistoriaClinica.btnNext2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.jFHistoriaClinica.btnNext3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.jFHistoriaClinica.btnNext4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.jFHistoriaClinica.btnNext5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(5);
                }
            });
            this.jFHistoriaClinica.btnNext6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(6);
                }
            });
            this.jFHistoriaClinica.btnNext7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFHistoriaClinica.jTabbedPane1.setSelectedIndex(7);
                }
            });
        }

    }

    public void goBack() {
        JFPacientesMedico fj = new JFPacientesMedico();
        ControladorPacientesMedico controladorPacientesMedico = new ControladorPacientesMedico(fj, empleado, expediente);
        fj.setVisible(true);
        jFHistoriaClinica.dispose();
    }

    //Metodos para obtener respuestas del fomularios
    public List obtieneRespuestasFormulario1(JFHistoriaClinica jf) {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        if (jf.rbNo1_1.isSelected()) {
            rp.setValorRespuesta("No");
        } else if (jf.rbSi1_1.isSelected()) {
            rp.setValorRespuesta("Si");
        } else {
            rp.setValorRespuesta("");
        }
        rp.setIdPregunta(1);
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(2);
        rp.setValorRespuesta(jf.HC1_2.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(3);
        if (jf.rbNo1_3.isSelected()) {
            rp.setValorRespuesta("No");
        } else if (jf.rbSi1_3.isSelected()) {
            rp.setValorRespuesta("Si");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(4);
        rp.setValorRespuesta(jf.HC1_4.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(5);
        rp.setValorRespuesta(jf.HC1_5.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(6);
        rp.setValorRespuesta(jf.HC1_6.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(7);
        rp.setValorRespuesta(jf.HC1_7.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 1), 8);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 2), 9);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 3), 10);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 4), 11);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 5), 12);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 6), 13);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 0, 7), 14);
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 1), 15);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 2), 16);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 3), 17);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 4), 18);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 5), 19);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 6), 20);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 1, 7), 21);
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 1), 22);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 2), 23);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 3), 24);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 4), 25);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 5), 26);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 6), 27);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 2, 7), 28);
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 1), 29);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 2), 30);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 3), 31);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 4), 32);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 5), 33);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 6), 34);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 3, 7), 35);
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 1), 36);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 2), 37);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 3), 38);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 4), 39);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 5), 40);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 6), 41);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 4, 7), 42);
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 1), 43);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 2), 44);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 3), 45);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 4), 46);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 5), 47);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 6), 48);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jf.tablaHeredofamiliares, 5, 7), 49);
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(50);
        rp.setValorRespuesta(jf.HC2_b_a.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(51);
        rp.setValorRespuesta(jf.HC2_b_b.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(52);
        rp.setValorRespuesta(jf.HC2_b_c.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(53);
        rp.setValorRespuesta(jf.HC2_b_d.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(54);
        rp.setValorRespuesta(jf.HC2_b_e.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario2() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(55);
        rp.setValorRespuesta(jFHistoriaClinica.txtFiebresEruptivas.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(56);
        rp.setValorRespuesta(jFHistoriaClinica.txtCuadrosinfecciosos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(57);
        rp.setValorRespuesta(jFHistoriaClinica.Ictericos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(58);
        rp.setValorRespuesta(jFHistoriaClinica.txtHormonales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(59);
        rp.setValorRespuesta(jFHistoriaClinica.txtEpilepsia.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(60);
        rp.setValorRespuesta(jFHistoriaClinica.txtAlergicos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(61);
        rp.setValorRespuesta(jFHistoriaClinica.txtQuirurgicos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(62);
        rp.setValorRespuesta(jFHistoriaClinica.txtTraumaticos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(63);
        rp.setValorRespuesta(jFHistoriaClinica.txtTransfusionales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(64);
        rp.setValorRespuesta(jFHistoriaClinica.txtGinecoobstetricos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(65);
        rp.setValorRespuesta(jFHistoriaClinica.txtConsumodeSustancias.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta(jFHistoriaClinica.Menarca.getText(), 66);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.ciclos.getText(), 67);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.fum.getText(), 68);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.ivsa.getText(), 69);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.parejas_sexuales.getText(), 70);
        lista.add(rp);

        if (jFHistoriaClinica.dpFechaUltimoEvento.getDate() == null) {
            resp = "";
        } else {
            resp = jFHistoriaClinica.dpFechaUltimoEvento.getDate().toString();
        }
        rp = new RespuestasEncuesta(resp, 71);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.txtG.getText(), 72);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.txtP.getText(), 73);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.txtA.getText(), 74);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFHistoriaClinica.txtC.getText(), 75);
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(76);
        rp.setValorRespuesta(jFHistoriaClinica.txtAltoriesgo.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario3() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(77);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaEmbarazo.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(78);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaNucleoFamiliar.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(79);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaEscolaridad.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(80);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaVidaLaboral.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(81);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaVidaSexual.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(82);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaVidaConyugal.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(83);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaVidaSocial.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(84);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaInstanciasLegales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(85);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicobiografiaFamiliares.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario4() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";
//duda
        rp.setIdPregunta(86);
        rp.setValorRespuesta(jFHistoriaClinica.txt12.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(87);
        //duda
        rp.setValorRespuesta(jFHistoriaClinica.txt13.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(88);
        rp.setValorRespuesta(jFHistoriaClinica.txtInterrogatorioAparatos1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(89);
        rp.setValorRespuesta(jFHistoriaClinica.txtPesoIdeal1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(90);
        rp.setValorRespuesta(jFHistoriaClinica.txtPesoReal1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(91);
        rp.setValorRespuesta(jFHistoriaClinica.txtTalla1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(92);
        rp.setValorRespuesta(jFHistoriaClinica.txtTA1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(93);
        rp.setValorRespuesta(jFHistoriaClinica.txtFC1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(94);
        rp.setValorRespuesta(jFHistoriaClinica.txtTemperatura1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(95);
        rp.setValorRespuesta(jFHistoriaClinica.txtPulso1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(96);
        rp.setValorRespuesta(jFHistoriaClinica.txtCabezaCuello1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(97);
        rp.setValorRespuesta(jFHistoriaClinica.txtTorax1.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(98);
        rp.setValorRespuesta(jFHistoriaClinica.txtAbdomen1.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario5() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(99);
        rp.setValorRespuesta(jFHistoriaClinica.txtGenitales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(100);
        rp.setValorRespuesta(jFHistoriaClinica.txtColumnaVertebral.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(101);
        rp.setValorRespuesta(jFHistoriaClinica.txtExtremidades.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(102);
        rp.setValorRespuesta(jFHistoriaClinica.txtParesCraneales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(103);
        rp.setValorRespuesta(jFHistoriaClinica.txtSistemaMotor.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(104);
        rp.setValorRespuesta(jFHistoriaClinica.txtSensibilidad.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(105);
        rp.setValorRespuesta(jFHistoriaClinica.txtReflejos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(106);
        rp.setValorRespuesta(jFHistoriaClinica.txtVestibulocerebeloso.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(107);
        rp.setValorRespuesta(jFHistoriaClinica.txtMarcha.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(108);
        rp.setValorRespuesta(jFHistoriaClinica.txtDominanciaCerebral.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(109);
        rp.setValorRespuesta(jFHistoriaClinica.txtExamenesLab.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario6() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(110);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientosFisicos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(111);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientoFarmacologico.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(112);
        rp.setValorRespuesta(jFHistoriaClinica.txtPsicoterapeuticos.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(113);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientoAlternativo.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(114);
        rp.setValorRespuesta(jFHistoriaClinica.txtInspeccionGeneral.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(115);
        rp.setValorRespuesta(jFHistoriaClinica.txtLenguajePensamiento.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario7() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(116);
        rp.setValorRespuesta(jFHistoriaClinica.txtFuncionesIntelectuales.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(117);
        rp.setValorRespuesta(jFHistoriaClinica.txtAfectividad.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(118);
        rp.setValorRespuesta(jFHistoriaClinica.txtSensopercepcion.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(119);
        rp.setValorRespuesta(jFHistoriaClinica.txtIdeacion.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(120);
        rp.setValorRespuesta(jFHistoriaClinica.txtObservaciones.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 0, 0), 121);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 0, 1), 122);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 1, 0), 123);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 1, 1), 124);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 2, 0), 125);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 2, 1), 126);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 3, 0), 127);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 3, 1), 128);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 4, 0), 129);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFHistoriaClinica.tablaHC81, 4, 1), 130);
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(131);
        rp.setValorRespuesta(jFHistoriaClinica.txtObservacionesDiagnostico.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(132);
        rp.setValorRespuesta(jFHistoriaClinica.txtObservacionesDiagnostico.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(133);
        rp.setValorRespuesta(jFHistoriaClinica.txtPronosticoVida.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(134);
        rp.setValorRespuesta(jFHistoriaClinica.txtPronosticoFuncion.getText());
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario8() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();
        String resp = "";

        rp.setIdPregunta(134);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientoIndividual.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(135);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratGrupal.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(136);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientoFamiliar.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(137);
        rp.setValorRespuesta(jFHistoriaClinica.txtTratamientoGrupalFamiliar.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(138);
        rp.setValorRespuesta(jFHistoriaClinica.txtHCConsejoBreve94.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(139);
        rp.setValorRespuesta(jFHistoriaClinica.txtHCJustificacion95.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(140);
        rp.setValorRespuesta(jFHistoriaClinica.txtHCPlan96.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(141);
        rp.setValorRespuesta(jFHistoriaClinica.txtHC97.getText());
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(142);
        rp.setValorRespuesta(jFHistoriaClinica.horaFin.getFormatedTime());
        lista.add(rp);

        return lista;
    }

    public String obtenerRespuestaTabla(JTable tabla, int row, int col) {
        String resp;
        if (tabla.getValueAt(row, col) == null || tabla.getValueAt(row, col).toString().isEmpty()) {
            resp = "";
        } else {
            resp = tabla.getValueAt(row, col).toString();
        }
        return resp;
    }

    //Carga los datos en los formularios
    public void cargarDatosFormulario(List<RespuestasEncuesta> lista) throws ParseException {
        DatePicker dp = new DatePicker();
//Formulario 1
        //Respuestas radiobutton
        if (lista.get(0).getValorRespuesta().equals("No")) {
            jFHistoriaClinica.rbNo1_1.setSelected(true);
        } else if (lista.get(0).getValorRespuesta().equals("Si")) {
            jFHistoriaClinica.rbSi1_1.setSelected(true);
        } else {
            jFHistoriaClinica.rbNo1_1.setSelected(false);
            jFHistoriaClinica.rbSi1_1.setSelected(false);
        }
        if (lista.get(2).getValorRespuesta().equals("No")) {
            jFHistoriaClinica.rbNo1_3.setSelected(true);
        } else if (lista.get(2).getValorRespuesta().equals("Si")) {
            jFHistoriaClinica.rbSi1_3.setSelected(true);
        } else {
            jFHistoriaClinica.rbNo1_3.setSelected(false);
            jFHistoriaClinica.rbSi1_3.setSelected(false);
        }
        //Respuestas texto
        jFHistoriaClinica.HC1_2.setText(lista.get(1).getValorRespuesta());
        jFHistoriaClinica.HC1_4.setText(lista.get(3).getValorRespuesta());
        jFHistoriaClinica.HC1_5.setText(lista.get(4).getValorRespuesta());
        jFHistoriaClinica.HC1_6.setText(lista.get(5).getValorRespuesta());
        jFHistoriaClinica.HC1_7.setText(lista.get(6).getValorRespuesta());
        jFHistoriaClinica.HC2_b_a.setText(lista.get(49).getValorRespuesta());
        jFHistoriaClinica.HC2_b_b.setText(lista.get(50).getValorRespuesta());
        jFHistoriaClinica.HC2_b_c.setText(lista.get(51).getValorRespuesta());
        jFHistoriaClinica.HC2_b_d.setText(lista.get(52).getValorRespuesta());
        jFHistoriaClinica.HC2_b_e.setText(lista.get(53).getValorRespuesta());
        //Tabla
        int cont_r = 7;
        String res = "";
        for (int i = 0; i < jFHistoriaClinica.tablaHeredofamiliares.getRowCount(); i++) {
            for (int j = 1; j < 8; j++) {
                res = lista.get(cont_r).getValorRespuesta();
                jFHistoriaClinica.tablaHeredofamiliares.setValueAt(res, i, j);
                cont_r++;
            }
        }

        //Formulario 2
        jFHistoriaClinica.txtFiebresEruptivas.setText(lista.get(54).getValorRespuesta());
        jFHistoriaClinica.txtCuadrosinfecciosos.setText(lista.get(55).getValorRespuesta());
        jFHistoriaClinica.Ictericos.setText(lista.get(56).getValorRespuesta());
        jFHistoriaClinica.txtHormonales.setText(lista.get(57).getValorRespuesta());
        jFHistoriaClinica.txtEpilepsia.setText(lista.get(58).getValorRespuesta());
        jFHistoriaClinica.txtAlergicos.setText(lista.get(59).getValorRespuesta());
        jFHistoriaClinica.txtQuirurgicos.setText(lista.get(60).getValorRespuesta());
        jFHistoriaClinica.txtTraumaticos.setText(lista.get(61).getValorRespuesta());
        jFHistoriaClinica.txtTransfusionales.setText(lista.get(62).getValorRespuesta());
        jFHistoriaClinica.txtGinecoobstetricos.setText(lista.get(63).getValorRespuesta());
        jFHistoriaClinica.txtConsumodeSustancias.setText(lista.get(64).getValorRespuesta());

        jFHistoriaClinica.Menarca.setText(lista.get(65).getValorRespuesta());
        jFHistoriaClinica.ciclos.setText(lista.get(66).getValorRespuesta());
        jFHistoriaClinica.fum.setText(lista.get(67).getValorRespuesta());
        jFHistoriaClinica.ivsa.setText(lista.get(68).getValorRespuesta());
        jFHistoriaClinica.parejas_sexuales.setText(lista.get(69).getValorRespuesta());

        if (!lista.get(70).getValorRespuesta().isEmpty()) {
            jFHistoriaClinica.dpFechaUltimoEvento.setDate(dp.formatoStringtoLocalDate(lista.get(70).getValorRespuesta().toString()));
        }

        jFHistoriaClinica.txtG.setText(lista.get(71).getValorRespuesta());
        jFHistoriaClinica.txtP.setText(lista.get(72).getValorRespuesta());
        jFHistoriaClinica.txtA.setText(lista.get(73).getValorRespuesta());
        jFHistoriaClinica.txtC.setText(lista.get(74).getValorRespuesta());

        jFHistoriaClinica.txtAltoriesgo.setText(lista.get(75).getValorRespuesta());
        //Falta tabla femenino

        //Formulario 3
        jFHistoriaClinica.txtPsicobiografiaEmbarazo.setText(lista.get(76).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaNucleoFamiliar.setText(lista.get(77).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaEscolaridad.setText(lista.get(78).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaVidaLaboral.setText(lista.get(79).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaVidaSexual.setText(lista.get(80).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaVidaConyugal.setText(lista.get(81).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaVidaSocial.setText(lista.get(82).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaInstanciasLegales.setText(lista.get(83).getValorRespuesta());
        jFHistoriaClinica.txtPsicobiografiaFamiliares.setText(lista.get(84).getValorRespuesta());

        //Formulario 4 duda
        jFHistoriaClinica.txt12.setText(lista.get(85).getValorRespuesta());
        jFHistoriaClinica.txt13.setText(lista.get(86).getValorRespuesta());
        jFHistoriaClinica.txtInterrogatorioAparatos1.setText(lista.get(87).getValorRespuesta());
        jFHistoriaClinica.txtPesoIdeal1.setText(lista.get(88).getValorRespuesta());
        jFHistoriaClinica.txtPesoReal1.setText(lista.get(89).getValorRespuesta());
        jFHistoriaClinica.txtTalla1.setText(lista.get(90).getValorRespuesta());
        jFHistoriaClinica.txtTA1.setText(lista.get(91).getValorRespuesta());
        jFHistoriaClinica.txtFC1.setText(lista.get(92).getValorRespuesta());
        jFHistoriaClinica.txtTemperatura1.setText(lista.get(93).getValorRespuesta());
        jFHistoriaClinica.txtPulso1.setText(lista.get(94).getValorRespuesta());
        jFHistoriaClinica.txtCabezaCuello1.setText(lista.get(95).getValorRespuesta());
        jFHistoriaClinica.txtTorax1.setText(lista.get(96).getValorRespuesta());
        jFHistoriaClinica.txtAbdomen1.setText(lista.get(97).getValorRespuesta());

        //Formulario 5
        jFHistoriaClinica.txtGenitales.setText(lista.get(98).getValorRespuesta());
        jFHistoriaClinica.txtColumnaVertebral.setText(lista.get(99).getValorRespuesta());
        jFHistoriaClinica.txtExtremidades.setText(lista.get(100).getValorRespuesta());
        jFHistoriaClinica.txtParesCraneales.setText(lista.get(101).getValorRespuesta());
        jFHistoriaClinica.txtSistemaMotor.setText(lista.get(102).getValorRespuesta());
        jFHistoriaClinica.txtSensibilidad.setText(lista.get(103).getValorRespuesta());
        jFHistoriaClinica.txtReflejos.setText(lista.get(104).getValorRespuesta());
        jFHistoriaClinica.txtVestibulocerebeloso.setText(lista.get(105).getValorRespuesta());
        jFHistoriaClinica.txtMarcha.setText(lista.get(106).getValorRespuesta());
        jFHistoriaClinica.txtDominanciaCerebral.setText(lista.get(107).getValorRespuesta());
        jFHistoriaClinica.txtExamenesLab.setText(lista.get(108).getValorRespuesta());

        //Formulario 6
        jFHistoriaClinica.txtTratamientosFisicos.setText(lista.get(109).getValorRespuesta());
        jFHistoriaClinica.txtTratamientoFarmacologico.setText(lista.get(110).getValorRespuesta());
        jFHistoriaClinica.txtPsicoterapeuticos.setText(lista.get(111).getValorRespuesta());
        jFHistoriaClinica.txtTratamientoAlternativo.setText(lista.get(112).getValorRespuesta());
        jFHistoriaClinica.txtInspeccionGeneral.setText(lista.get(113).getValorRespuesta());
        jFHistoriaClinica.txtLenguajePensamiento.setText(lista.get(114).getValorRespuesta());

        //Formulario 7
        jFHistoriaClinica.txtFuncionesIntelectuales.setText(lista.get(115).getValorRespuesta());
        jFHistoriaClinica.txtAfectividad.setText(lista.get(116).getValorRespuesta());
        jFHistoriaClinica.txtSensopercepcion.setText(lista.get(117).getValorRespuesta());
        jFHistoriaClinica.txtIdeacion.setText(lista.get(118).getValorRespuesta());
        jFHistoriaClinica.txtObservaciones.setText(lista.get(119).getValorRespuesta());

        //Tabla 110 - 120
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(120).getValorRespuesta(), 0, 0);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(121).getValorRespuesta(), 0, 1);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(122).getValorRespuesta(), 1, 0);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(123).getValorRespuesta(), 1, 1);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(124).getValorRespuesta(), 2, 0);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(125).getValorRespuesta(), 2, 1);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(126).getValorRespuesta(), 3, 0);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(127).getValorRespuesta(), 3, 1);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(128).getValorRespuesta(), 4, 0);
        jFHistoriaClinica.tablaHC81.setValueAt(lista.get(129).getValorRespuesta(), 4, 1);
        jFHistoriaClinica.txtObservacionesDiagnostico.setText(lista.get(130).getValorRespuesta());
        jFHistoriaClinica.txtPronosticoVida.setText(lista.get(131).getValorRespuesta());
        jFHistoriaClinica.txtPronosticoFuncion.setText(lista.get(132).getValorRespuesta());

        //Formulario 8
        jFHistoriaClinica.txtTratamientoIndividual.setText(lista.get(133).getValorRespuesta());
        jFHistoriaClinica.txtTratGrupal.setText(lista.get(134).getValorRespuesta());
        jFHistoriaClinica.txtTratamientoFamiliar.setText(lista.get(135).getValorRespuesta());
        jFHistoriaClinica.txtTratamientoGrupalFamiliar.setText(lista.get(136).getValorRespuesta());
        jFHistoriaClinica.txtHCConsejoBreve94.setText(lista.get(137).getValorRespuesta());
        jFHistoriaClinica.txtHCJustificacion95.setText(lista.get(138).getValorRespuesta());
        jFHistoriaClinica.txtHCPlan96.setText(lista.get(139).getValorRespuesta());
        jFHistoriaClinica.txtHC97.setText(lista.get(140).getValorRespuesta());

        if (!lista.get(141).getValorRespuesta().isEmpty()) {
        
            jFHistoriaClinica.horaFin.setTime(dp.formatoStringToDateTime(lista.get(141).getValorRespuesta().toString()));
        }
    }

}
