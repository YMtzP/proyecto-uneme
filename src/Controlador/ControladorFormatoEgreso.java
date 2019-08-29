/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.Paciente;
import Modelo.RespuestasEncuesta;
import Vista.MensajeExitoso;
import Vista.Psicologo.JFFormatoEgreso;
import Vista.Psicologo.JFPacientesPsicologo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YareMtz
 */
public class ControladorFormatoEgreso {

    JFFormatoEgreso formatoEgreso;
    Expediente expediente;
    Empleado empleado;

    public ControladorFormatoEgreso(JFFormatoEgreso formatoEgreso, Expediente expediente, Empleado empleado) {
        this.formatoEgreso = formatoEgreso;
        this.expediente = expediente;
        this.empleado = empleado;
        boolean verifica = false;
        this.formatoEgreso.etiquetaExp.setText("Formato de egreso - Núm. Expediente: "+expediente.getNumExpediente());
        
        ManejadorEncuestas me = new ManejadorEncuestas();
        if (!me.existeEncuesta(expediente, 4)) {
            if (me.crearEncuesta(empleado, expediente, 4)) {
                verifica = true;
            }
        } else {
            verifica = true;
        }
        if (verifica) {
            validarLimiteTexto();
            List<RespuestasEncuesta> lista = me.obtenerRespuestasFormatoEgreso(expediente);
            //System.out.println(lista.size());
            cargarDatosBDDaFormulario(lista);

            this.formatoEgreso.btnSiguiente1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    formatoEgreso.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.formatoEgreso.btnAnterior2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    formatoEgreso.jTabbedPane1.setSelectedIndex(0);
                }
            });

            this.formatoEgreso.btnSave1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtenerDatosFormulario1();
                    if (me.guardarRespuestas(lista, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(formatoEgreso, true);
                        msg.msg.setText("<html>Se han guardado los datos del formato de egreso voluntario correctamente.</html>");
                        msg.setVisible(true);
                    }
                }

            });
            this.formatoEgreso.btnSave2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtenerDatosFormulario2();
                    if (me.guardarRespuestas(lista, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(formatoEgreso, true);
                        msg.msg.setText("<html>Se han guardado los datos del formato de egreso voluntario correctamente.</html>");
                        msg.setVisible(true);
                    }
                }

            });
            this.formatoEgreso.btnRegresar2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }
            });
            this.formatoEgreso.goBack1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });

        } else {
            goBack();
        }

    }

    public void cargarDatosBDDaFormulario(List<RespuestasEncuesta> l) {
        if (l.get(0).getValorRespuesta().equals("Cumplio con los objetivos del Tx")) {
            formatoEgreso.rb1CumplioObjetivosdelTx.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Alta por mejoria")) {
            formatoEgreso.rb1AltaPorMejoria.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Abandono del tx con mejoria")) {
            formatoEgreso.rb1AbandonodelTexConMejoria.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Abandono del tx sin mejoria")) {
            formatoEgreso.rb1AbandonodelTxSinMejoria.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Voluntario sin mejoria")) {
            formatoEgreso.rb1VoluntarioSinMejoria.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Voluntario con mejoria")) {
            formatoEgreso.rb1VoluntarioConMejoria.setSelected(true);
        } else if (l.get(0).getValorRespuesta().equals("Baja")) {
            formatoEgreso.rb1Baja.setSelected(true);
        }

        if (l.get(1).getValorRespuesta().equals("Fase diagnostica")) {
            formatoEgreso.rb2FaseDiagnostica.setSelected(true);
        } else if (l.get(1).getValorRespuesta().equals("Tratamiento y reinsercion social")) {
            formatoEgreso.rb2TratamientoReinsercionSocial.setSelected(true);
        }

        formatoEgreso.txt3_MotivoEgreso.setText(l.get(2).getValorRespuesta());
        formatoEgreso.txt4_areamedica.setText(l.get(3).getValorRespuesta());
        formatoEgreso.txt4_areapsicologia.setText(l.get(4).getValorRespuesta());
        formatoEgreso.txt4_areasocial.setText(l.get(5).getValorRespuesta());
        formatoEgreso.txt5_areamedica.setText(l.get(6).getValorRespuesta());
        formatoEgreso.txt5_areapsicologia.setText(l.get(7).getValorRespuesta());
        formatoEgreso.txt5_areasocial.setText(l.get(8).getValorRespuesta());
        formatoEgreso.txt5_Dareamedica.setText(l.get(9).getValorRespuesta());
        formatoEgreso.txt5_Dareasocial.setText(l.get(10).getValorRespuesta());
        formatoEgreso.txt5_Dareapsicologia.setText(l.get(11).getValorRespuesta());
        
        formatoEgreso.txt6_PronosticoEgreso.setText(l.get(12).getValorRespuesta());
        formatoEgreso.txt7_Sugerencias.setText(l.get(13).getValorRespuesta());
        formatoEgreso.txt8_Observaciones.setText(l.get(14).getValorRespuesta());

    }

    public List obtenerDatosFormulario1() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp = new RespuestasEncuesta();

        rp.setIdPregunta(493);
        if (formatoEgreso.rb1CumplioObjetivosdelTx.isSelected()) {
            rp.setValorRespuesta("Cumplio con los objetivos del Tx");
        } else if (formatoEgreso.rb1AltaPorMejoria.isSelected()) {
            rp.setValorRespuesta("Alta por mejoria");
        } else if (formatoEgreso.rb1AbandonodelTexConMejoria.isSelected()) {
            rp.setValorRespuesta("Abandono del tx con mejoria");
        } else if (formatoEgreso.rb1AbandonodelTxSinMejoria.isSelected()) {
            rp.setValorRespuesta("Abandono del tx sin mejoria");
        } else if (formatoEgreso.rb1VoluntarioSinMejoria.isSelected()) {
            rp.setValorRespuesta("Voluntario sin mejoria");
        } else if (formatoEgreso.rb1VoluntarioConMejoria.isSelected()) {
            rp.setValorRespuesta("Voluntario con mejoria");
        } else if (formatoEgreso.rb1Baja.isSelected()) {
            rp.setValorRespuesta("Baja");
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);


        if (formatoEgreso.rb2FaseDiagnostica.isSelected()) {
            rp= new RespuestasEncuesta("Fase diagnostica",494);
        } else if (formatoEgreso.rb2TratamientoReinsercionSocial.isSelected()) {
            rp = new RespuestasEncuesta("Tratamiento y reinsercion social",494);
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);

        rp = new RespuestasEncuesta(formatoEgreso.txt3_MotivoEgreso.getText(), 495);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt4_areamedica.getText(), 496);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt4_areapsicologia.getText(), 497);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt4_areasocial.getText(), 498);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt5_areamedica.getText(), 499);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt5_areapsicologia.getText(), 500);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt5_areasocial.getText(), 501);
        lista.add(rp);

        return lista;
    }

    public List obtenerDatosFormulario2() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;

        rp = new RespuestasEncuesta(formatoEgreso.txt5_Dareamedica.getText(), 502);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt5_Dareapsicologia.getText(), 503);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt5_Dareasocial.getText(), 504);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt6_PronosticoEgreso.getText(), 505);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt7_Sugerencias.getText(), 506);
        lista.add(rp);
        rp = new RespuestasEncuesta(formatoEgreso.txt8_Observaciones.getText(), 507);
        lista.add(rp);

        return lista;
    }

    public void goBack() {
        JFPacientesPsicologo jFPacientesPsicologo = new JFPacientesPsicologo();
        Paciente paciente = new Paciente();
        ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(jFPacientesPsicologo, empleado, expediente);
        jFPacientesPsicologo.setVisible(true);
        formatoEgreso.dispose();
    }

    public void validarLimiteTexto() {
        this.formatoEgreso.txt3_MotivoEgreso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt3_MotivoEgreso.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt4_areamedica.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt4_areamedica.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt4_areasocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt4_areasocial.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt4_areapsicologia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt4_areapsicologia.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_areamedica.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_areamedica.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_areasocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_areasocial.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_areapsicologia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_areapsicologia.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_Dareamedica.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_Dareamedica.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_Dareasocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_Dareasocial.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt5_Dareapsicologia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt5_Dareapsicologia.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt6_PronosticoEgreso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt6_PronosticoEgreso.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt7_Sugerencias.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt7_Sugerencias.getText().length() == 500) {
                    e.consume();
                }
            }
        });
        this.formatoEgreso.txt8_Observaciones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (formatoEgreso.txt8_Observaciones.getText().length() == 500) {
                    e.consume();
                }
            }
        });
    }

}
