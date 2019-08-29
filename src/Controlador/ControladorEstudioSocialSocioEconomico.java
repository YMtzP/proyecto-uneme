/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Modelo.Expediente;
import Vista.MensajeExitoso;
import Vista.TrabajadorSocial.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author YareMtz
 */
public class ControladorEstudioSocialSocioEconomico {

    JFEstudioSocialSocioEconomico jFEstudioSocialSocioeconomico;
    Empleado empleado;
    Expediente expediente;
    FichaDeIdentificacion ficha;

    public ControladorEstudioSocialSocioEconomico(JFEstudioSocialSocioEconomico jFEstudioSocialSocioEconomico, Empleado empleado, Expediente expediente, FichaDeIdentificacion ficha) {
        this.jFEstudioSocialSocioeconomico = jFEstudioSocialSocioEconomico;
        this.empleado = empleado;
        this.expediente = expediente;
        this.ficha = ficha;
        ManejadorEncuestas me = new ManejadorEncuestas();
        boolean verifica = false;
        validaCamposTyped();
        this.jFEstudioSocialSocioeconomico.etiquetaExp.setText("Estudio social/socioeconómico - Expediente: " + expediente.getNumExpediente());

        if (!me.existeEncuesta(expediente, 2)) {
            if (me.crearEncuesta(empleado, expediente, 2)) {
                verifica = true;
            }
        } else {
            verifica = true;
        }
        if (verifica) {
            List<RespuestasEncuesta> listaRespuestas = me.obtenerRespuestasEncuestaEstudioSocialSocioeconomico(expediente);
            cargarDatosBDDaFormulario(listaRespuestas);

            this.jFEstudioSocialSocioeconomico.btnSave1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuetasFormulario1();

                    if (me.guardarRespuestas(lista, expediente)) {

                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);

                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.btnSave4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario4();

                    if (me.guardarRespuestas(lista, expediente)) {
                        // System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdRespuesta());
                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.btnSave5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario5();
                    //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdRespuesta());

                    if (me.guardarRespuestas(lista, expediente)) {
                        //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdRespuesta());
                        //System.out.println("aqui");
                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.btnSave6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario6();
                    //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdRespuesta());
                    if (me.guardarRespuestas(lista, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.btnSavePte3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario3();
                    //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdPregunta());
                    if (me.guardarRespuestas(lista, expediente)) {
                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.btnSave2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    List<RespuestasEncuesta> lista = obtieneRespuetasFormulario2();
                    //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdPregunta());
                    if (me.guardarRespuestas(lista, expediente)) {
                        //System.out.println(lista.get(0).getValorRespuesta()+" - "+lista.get(0).getIdPregunta());
                        MensajeExitoso msg = new MensajeExitoso(jFEstudioSocialSocioEconomico, true);
                        msg.msg.setText("<html>Se han guardado los datos del <br>Estudio social/socioeconomico correctamente.</html>");
                        msg.setVisible(true);
                    }
                }
            });
            this.jFEstudioSocialSocioeconomico.goBack1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFEstudioSocialSocioeconomico.goBack3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFEstudioSocialSocioeconomico.goBack4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFEstudioSocialSocioeconomico.goBack5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFEstudioSocialSocioeconomico.btnRegresar6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.jFEstudioSocialSocioeconomico.btnRegresar2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }
            });

            this.jFEstudioSocialSocioeconomico.btnAnterior2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(0);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnAnterior3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnAnterior4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnAnterior5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnAnterior6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnNext1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnNext2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnNext3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnNext4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.jFEstudioSocialSocioeconomico.btnNext5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jFEstudioSocialSocioEconomico.jTabbedPane1.setSelectedIndex(5);
                }
            });

        }

    }

    public List obtieneRespuetasFormulario1() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";

        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 0), 143);
        //System.out.println(rp.getValorRespuesta()+": id preg"+rp.getIdPregunta());
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 1), 144);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 2), 145);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 3), 146);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 4), 147);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 5), 148);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 6), 149);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 0, 7), 150);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 0), 151);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 1), 152);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 2), 153);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 3), 154);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 4), 155);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 5), 156);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 6), 157);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 1, 7), 158);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 0), 159);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 1), 160);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 2), 161);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 3), 162);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 4), 163);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 5), 164);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 6), 165);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 2, 7), 166);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 0), 167);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 1), 168);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 2), 169);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 3), 170);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 4), 171);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 5), 172);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 6), 173);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 3, 7), 174);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 0), 175);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 1), 176);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 2), 177);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 3), 178);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 4), 179);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 5), 180);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 6), 181);
        lista.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1, 4, 7), 182);
        //lista.add(rp);
        
        if(jFEstudioSocialSocioeconomico.rbSinSalario.isSelected()){
            resp="1";
        }else if(jFEstudioSocialSocioeconomico.rbMenos2000.isSelected()){
            resp="2";
        }else if(jFEstudioSocialSocioeconomico.rb2000a3000.isSelected()){
            resp="3";
        }else if(jFEstudioSocialSocioeconomico.rb3001a4000.isSelected()){
            
            resp="4";
        }else if(jFEstudioSocialSocioeconomico.rb4000a5000.isSelected()){
            
            resp="5";
        }else if(jFEstudioSocialSocioeconomico.rbMasde5001.isSelected()){
            resp="6";
        }
        rp = new RespuestasEncuesta(resp, 183);
        lista.add(rp);
        
        
        if(jFEstudioSocialSocioeconomico.rbPersonas8omas.isSelected()){
            resp="1";
        }else if(jFEstudioSocialSocioeconomico.rbPersonas5a7int.isSelected()){
            resp="2";
        }else if(jFEstudioSocialSocioeconomico.rbPersonas3a4int.isSelected()){
            resp="3";
        }else if(jFEstudioSocialSocioeconomico.rbPersonas1a2int.isSelected()){
            resp="4";
        }
        rp = new RespuestasEncuesta(resp, 184);
        lista.add(rp);
        
        if(jFEstudioSocialSocioeconomico.rbDependientes8ymas.isSelected()){
            resp="1";
        }else if(jFEstudioSocialSocioeconomico.rbDependientes5a7.isSelected()){
            resp="2";
        }else if(jFEstudioSocialSocioeconomico.rbDependientes3a4.isSelected()){
            resp="3";
        }else if(jFEstudioSocialSocioeconomico.rbDependientes1a2.isSelected()){
            resp="4";
        }
         rp = new RespuestasEncuesta(resp, 185);
        lista.add(rp);

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAlimentacion2.getText(), 186);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtRenta1.getText(), 187);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txxLuzAgua1.getText(), 188);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtCombustible1.getText(), 189);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtEducacion1.getText(), 190);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtVestido1.getText(), 191);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtMedicamentos1.getText(), 192);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtTransporte1.getText(), 193);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtDiversiones1.getText(), 194);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAbonos.getText(), 195);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtOtros1.getText(), 196);
        lista.add(rp);

        float total = 0;
        if(!jFEstudioSocialSocioeconomico.txtOtros1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtOtros1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtAbonos.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtAbonos.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtDiversiones1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtDiversiones1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtTransporte1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtTransporte1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtMedicamentos1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtMedicamentos1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtAlimentacion2.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtAlimentacion2.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtRenta1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtRenta1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txxLuzAgua1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txxLuzAgua1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtCombustible1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtCombustible1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtEducacion1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtEducacion1.getText());
        }
        if(!jFEstudioSocialSocioeconomico.txtVestido1.getText().equals("")){
            total += Float.parseFloat(jFEstudioSocialSocioeconomico.txtVestido1.getText());
        }
        
        
        rp = new RespuestasEncuesta(total + "", 197);
        lista.add(rp);
        jFEstudioSocialSocioeconomico.txtTotal1.setText(total + "");

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(198);
        if (jFEstudioSocialSocioeconomico.rbSi2_1.isSelected()) {
            rp.setValorRespuesta("si");
        } else if (jFEstudioSocialSocioeconomico.rbNo2_1.isSelected()) {
            rp.setValorRespuesta("no");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtPorqueSiNo.getText(), 199);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtComoEnfrentarPE.getText(), 200);
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuetasFormulario2() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(201);
        if (jFEstudioSocialSocioeconomico.rbPrestada2.isSelected()) {
            rp.setValorRespuesta("Prestada");
        } else if (jFEstudioSocialSocioeconomico.rbPropia2.isSelected()) {
            rp.setValorRespuesta("Propia");
        } else if (jFEstudioSocialSocioeconomico.rbRentada2.isSelected()) {
            rp.setValorRespuesta("Rentada");
        } else {
            rp.setValorRespuesta("");
        }

        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(202);
        if (jFEstudioSocialSocioeconomico.rbCasaRural2.isSelected()) {
            rp.setValorRespuesta("Casa rural");
        } else if (jFEstudioSocialSocioeconomico.rbCasaSola2.isSelected()) {
            rp.setValorRespuesta("Casa sola");
        } else if (jFEstudioSocialSocioeconomico.rbCuarto2.isSelected()) {
            rp.setValorRespuesta("Cuarto");
        } else if (jFEstudioSocialSocioeconomico.rbJacalChoza2.isSelected()) {
            rp.setValorRespuesta("Jacal/Choza");
        } else if (jFEstudioSocialSocioeconomico.rbVecindad2.isSelected()) {
            rp.setValorRespuesta("Vecindad");
        } else if (jFEstudioSocialSocioeconomico.rbDepartamento2.isSelected()) {
            rp.setValorRespuesta("Departamento");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(203);
        if (jFEstudioSocialSocioeconomico.rbRural2.isSelected()) {
            
            rp.setValorRespuesta("rural");
        } else if (jFEstudioSocialSocioeconomico.rbSubUrbano2.isSelected()) {
            
            rp.setValorRespuesta("suburbana");
        } else if (jFEstudioSocialSocioeconomico.rbUrbana2.isSelected()) {
            
            rp.setValorRespuesta("urbana");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.cbMuros.getSelectedItem()+"", 204);
        lista.add(rp);
        rp = new RespuestasEncuesta(""+ jFEstudioSocialSocioeconomico.cbTechos.getSelectedItem(), 205);
        lista.add(rp);
        rp = new RespuestasEncuesta(""+ jFEstudioSocialSocioeconomico.cbPisos.getSelectedItem(), 206);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtRecamaras2.getText(), 207);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtBano2.getText(), 208);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtSalacomedor2.getText(), 209);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtPAtio2.getText(), 210);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtCocina2.getText(), 211);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtOTra2.getText(), 212);
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(213);
        if (jFEstudioSocialSocioeconomico.rb1_2pers2.isSelected()) {
            rp.setValorRespuesta("1 ó 2 personas");
        } else if (jFEstudioSocialSocioeconomico.rb3pers2.isSelected()) {
            rp.setValorRespuesta("3");
        } else if (jFEstudioSocialSocioeconomico.rb4pers2.isSelected()) {
            rp.setValorRespuesta("4 o más personas");
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(214);
        if (jFEstudioSocialSocioeconomico.rb3_7Insatis2.isSelected()) {
            rp.setValorRespuesta("Insatisfecho");
        } else if (jFEstudioSocialSocioeconomico.rb3_7ModInsa2.isSelected()) {
            rp.setValorRespuesta("Moderadamente insatisfecho");
        } else if (jFEstudioSocialSocioeconomico.rb3_7ModSatis2.isSelected()) {
            rp.setValorRespuesta("Moderadamente satisfecho");
        } else if (jFEstudioSocialSocioeconomico.rb3_7NiSatifNiIns2.isSelected()) {
            rp.setValorRespuesta("Ni satisfecho ni insatisfecho");
        } else if (jFEstudioSocialSocioeconomico.rb3_7TotSatis2.isSelected()) {
            rp.setValorRespuesta("Totalmente satisfecho");
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(215);
        if (jFEstudioSocialSocioeconomico.checkAguaPot2.isSelected()) {
            rp.setValorRespuesta("Agua potable");
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(216);
        if (jFEstudioSocialSocioeconomico.checkLuz2.isSelected()) {
            rp.setValorRespuesta("Luz");
        } else {
            rp.setValorRespuesta("");
        }
        
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(217);
        if (jFEstudioSocialSocioeconomico.checkDrenaje2.isSelected()) {
            rp.setValorRespuesta("Drenaje");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(218);
        if (jFEstudioSocialSocioeconomico.checkTele2.isSelected()) {
            rp.setValorRespuesta("Telefono");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(219);
        if (jFEstudioSocialSocioeconomico.checkAlumb2.isSelected()) {
            rp.setValorRespuesta("Alumbrado público");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(220);
        if (jFEstudioSocialSocioeconomico.checkPav2.isSelected()) {
            rp.setValorRespuesta("Pavimento");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(221);
        if (jFEstudioSocialSocioeconomico.checkAlcant2.isSelected()) {
            rp.setValorRespuesta("Alcantarillado");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(222);
        if (jFEstudioSocialSocioeconomico.checkServLimp2.isSelected()) {
            rp.setValorRespuesta("Servicio de limpia");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(223);
        if (jFEstudioSocialSocioeconomico.checkTransp2.isSelected()) {
            rp.setValorRespuesta("Transporte");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(224);
        if (jFEstudioSocialSocioeconomico.rb4_1WC2.isSelected()) {
            rp.setValorRespuesta("WC");
        } else if (jFEstudioSocialSocioeconomico.rb4_1Aire2.isSelected()) {
            rp.setValorRespuesta("Aire libre");
        } else if (jFEstudioSocialSocioeconomico.rb4_1Fosa2.isSelected()) {
            rp.setValorRespuesta("Fosa séptica");
        } else if (jFEstudioSocialSocioeconomico.rb4_1Letrina2.isSelected()) {
            rp.setValorRespuesta("Letrina");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);

        rp = new RespuestasEncuesta();
        rp.setIdPregunta(225);
        if (jFEstudioSocialSocioeconomico.rb4_2No2.isSelected()) {
            rp.setValorRespuesta("No");
        } else if (jFEstudioSocialSocioeconomico.rb4_2Si2.isSelected()) {
            rp.setValorRespuesta("Si");
        } else {
            rp.setValorRespuesta("");
        }
        lista.add(rp);
        return lista;
    }

    public List obtieneRespuestasFormulario3() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbVecesComida.getSelectedItem(), 226);
        lista.add(rp);

        if (jFEstudioSocialSocioeconomico.chkDesayuno.isSelected()) {
            rp = new RespuestasEncuesta("Si", 227);
            lista.add(rp);
        }
        if (jFEstudioSocialSocioeconomico.chkColacion.isSelected()) {
            rp = new RespuestasEncuesta("Si", 228);
            lista.add(rp);
        }
        if (jFEstudioSocialSocioeconomico.chkComida.isSelected()) {
            rp = new RespuestasEncuesta("Si", 229);
            lista.add(rp);
        }
        if (jFEstudioSocialSocioeconomico.chkColacion2.isSelected()) {
            rp = new RespuestasEncuesta("Si", 230);
            lista.add(rp);
        }
        if (jFEstudioSocialSocioeconomico.chkCena.isSelected()) {
            rp = new RespuestasEncuesta("Si", 231);
            lista.add(rp);
        }

        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbFrutas13.getSelectedItem(), 232);
        lista.add(rp);
        rp = new RespuestasEncuesta(""+ jFEstudioSocialSocioeconomico.cbVerduras.getSelectedItem(), 233);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbCereales.getSelectedItem(), 234);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbLeguminosas.getSelectedItem(), 235);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbCarneRes.getSelectedItem(), 236);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbCarneCerdo.getSelectedItem(), 237);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbPollo.getSelectedItem(), 238);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbPescado.getSelectedItem(), 239);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbHuevos.getSelectedItem(), 240);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbEnlatados.getSelectedItem(), 241);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbEmbutidos.getSelectedItem(), 242);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbFrituras.getSelectedItem(), 243);
        lista.add(rp);
        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbRefresco.getSelectedItem(), 244);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAlimentoObservaciones.getText(), 245);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt6DatosFamssig.getText(), 246);
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario4() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAmbienteFamiliar.getText(), 247);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAMbienteEscolar.getText(), 248);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAmbienteLaboral1.getText(), 249);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtPeriodoEmpleo1.getText(), 250);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtAmbComunitario.getText(), 251);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt11ActividadesTiempoLibre.getText(), 252);
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario5() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtCulturales.getText(), 253);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtDeportivos.getText(), 254);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtRecreativos.getText(), 255);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtEducativos.getText(), 256);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtOtros.getText(), 257);
        lista.add(rp);

        if (jFEstudioSocialSocioeconomico.rbSi13.isSelected()) {
            rp = new RespuestasEncuesta("Si", 258);
        } else if (jFEstudioSocialSocioeconomico.rbNo13.isSelected()) {
            rp = new RespuestasEncuesta("No", 258);
        }
        lista.add(rp);

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtVisitarDomicilioRazon.getText(), 259);
        lista.add(rp);

        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbMedioTransporteUNEMECAPA.getSelectedItem(), 260);
        lista.add(rp);

        rp = new RespuestasEncuesta("" + jFEstudioSocialSocioeconomico.cbTiempo.getSelectedItem(), 261);
        lista.add(rp);

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txtCosto.getText(), 262);
        lista.add(rp);

        return lista;
    }

    public List obtieneRespuestasFormulario6() {
        List<RespuestasEncuesta> lista = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";

        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt14InfoRecabada.getText(), 263);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt14ImpresionDiagnostica.getText(), 264);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt14PlanRehabi.getText(), 265);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt14PronosticoSocial.getText(), 266);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt15Cuota.getText(), 267);
        lista.add(rp);
        rp = new RespuestasEncuesta(jFEstudioSocialSocioeconomico.txt16Observaciones.getText(), 268);
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

    public void cargarDatosBDDaFormulario(List<RespuestasEncuesta> l) {
        //Formulario 1
        int cont = 0;
        String res = "";

        for (int i = 0; i < jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1.getRowCount(); i++) {
            for (int j = 0; j < 8; j++) {
                res = l.get(cont).getValorRespuesta();
                jFEstudioSocialSocioeconomico.tablaIdentificacionFamiliar1.setValueAt(res, i, j);
                cont++;
            }
        }
        
        if(l.get(40).getValorRespuesta().equals("1")){
            jFEstudioSocialSocioeconomico.rbSinSalario.setSelected(true);
        }else if(l.get(40).getValorRespuesta().equals("2")){
            jFEstudioSocialSocioeconomico.rbMenos2000.setSelected(true);
        }else if(l.get(40).getValorRespuesta().equals("3")){
            jFEstudioSocialSocioeconomico.rb2000a3000.setSelected(true);
        }else if(l.get(40).getValorRespuesta().equals("4")){
            jFEstudioSocialSocioeconomico.rb3001a4000.setSelected(true);
        }else if(l.get(40).getValorRespuesta().equals("5")){
            jFEstudioSocialSocioeconomico.rb4000a5000.setSelected(true);
        }else if(l.get(40).getValorRespuesta().equals("6")){
            jFEstudioSocialSocioeconomico.rbMasde5001.setSelected(true);
        }
        
        
        
        if(l.get(41).getValorRespuesta().equals("1")){
            jFEstudioSocialSocioeconomico.rbPersonas8omas.setSelected(true);
        }else if(l.get(41).getValorRespuesta().equals("2")){
            jFEstudioSocialSocioeconomico.rbPersonas5a7int.setSelected(true);
        }else if(l.get(41).getValorRespuesta().equals("3")){
            jFEstudioSocialSocioeconomico.rbPersonas3a4int.setSelected(true);
        }else if(l.get(41).getValorRespuesta().equals("4")){
            jFEstudioSocialSocioeconomico.rbPersonas1a2int.setSelected(true);
        }
        
        
        if(l.get(42).getValorRespuesta().equals("1")){
            jFEstudioSocialSocioeconomico.rbDependientes8ymas.setSelected(true);
        }else if(l.get(42).getValorRespuesta().equals("2")){
            jFEstudioSocialSocioeconomico.rbDependientes5a7.setSelected(true);
        }else if(l.get(42).getValorRespuesta().equals("3")){
            jFEstudioSocialSocioeconomico.rbDependientes3a4.setSelected(true);
        }else if(l.get(42).getValorRespuesta().equals("4")){
            jFEstudioSocialSocioeconomico.rbDependientes1a2.setSelected(true);
        }
        

        jFEstudioSocialSocioeconomico.txtAlimentacion2.setText(l.get(43).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtRenta1.setText(l.get(44).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txxLuzAgua1.setText(l.get(45).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtCombustible1.setText(l.get(46).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtEducacion1.setText(l.get(47).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtVestido1.setText(l.get(48).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtMedicamentos1.setText(l.get(49).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtTransporte1.setText(l.get(50).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtDiversiones1.setText(l.get(51).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtAbonos.setText(l.get(52).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtOtros1.setText(l.get(53).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtTotal1.setText(l.get(54).getValorRespuesta());

       
        if (l.get(55).getValorRespuesta().equals("si")) {
            jFEstudioSocialSocioeconomico.rbSi2_1.setSelected(true);
        } else if (l.get(55).getValorRespuesta().equals("no")) {
            jFEstudioSocialSocioeconomico.rbNo2_1.setSelected(true);
        }

        jFEstudioSocialSocioeconomico.txtPorqueSiNo.setText(l.get(56).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtComoEnfrentarPE.setText(l.get(57).getValorRespuesta());

        //Formulario 2
        if (l.get(58).getValorRespuesta().equals("Prestada")) {
            jFEstudioSocialSocioeconomico.rbPrestada2.setSelected(true);
        }
        if (l.get(58).getValorRespuesta().equals("Rentada")) {
            jFEstudioSocialSocioeconomico.rbRentada2.setSelected(true);
        }
        if (l.get(58).getValorRespuesta().equals("Propia")) {
            jFEstudioSocialSocioeconomico.rbPropia2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Casa rural")) {
            jFEstudioSocialSocioeconomico.rbCasaRural2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Jacal/Choza")) {
            jFEstudioSocialSocioeconomico.rbJacalChoza2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Cuarto")) {
            jFEstudioSocialSocioeconomico.rbCuarto2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Vecindad")) {
            jFEstudioSocialSocioeconomico.rbVecindad2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Departamento")) {
            jFEstudioSocialSocioeconomico.rbDepartamento2.setSelected(true);
        }
        if (l.get(59).getValorRespuesta().equals("Casa sola")) {
            jFEstudioSocialSocioeconomico.rbCasaSola2.setSelected(true);
        }

        if (l.get(60).getValorRespuesta().equals("rural")) {
            jFEstudioSocialSocioeconomico.rbRural2.setSelected(true);
        }
        if (l.get(60).getValorRespuesta().equals("suburbana")) {
            jFEstudioSocialSocioeconomico.rbSubUrbano2.setSelected(true);
        }
        if (l.get(60).getValorRespuesta().equals("urbana")) {
            jFEstudioSocialSocioeconomico.rbUrbana2.setSelected(true);
        }
        
        
        if (!l.get(61).getValorRespuesta().isEmpty()||!l.get(61).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbMuros.setSelectedItem(l.get(61).getValorRespuesta());
        }
        
        if (!l.get(62).getValorRespuesta().isEmpty()||!l.get(62).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbTechos.setSelectedItem(l.get(62).getValorRespuesta());
        }
        
        if (!l.get(63).getValorRespuesta().isEmpty()||!l.get(63).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbPisos.setSelectedItem(l.get(63).getValorRespuesta());
        }

        

        jFEstudioSocialSocioeconomico.txtRecamaras2.setText(l.get(64).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtBano2.setText(l.get(65).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtSalacomedor2.setText(l.get(66).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtPAtio2.setText(l.get(67).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtCocina2.setText(l.get(68).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtOTra2.setText(l.get(69).getValorRespuesta());

        if (l.get(70).getValorRespuesta().equals("4 o más personas")) {
            jFEstudioSocialSocioeconomico.rb4pers2.setSelected(true);
        }
        if (l.get(70).getValorRespuesta().equals("3")) {
            jFEstudioSocialSocioeconomico.rb3pers2.setSelected(true);
        }
        if (l.get(70).getValorRespuesta().equals("1 ó 2 personas")) {
            jFEstudioSocialSocioeconomico.rb1_2pers2.setSelected(true);
        }

        if (l.get(71).getValorRespuesta().equals("Totalmente satisfecho")) {
            jFEstudioSocialSocioeconomico.rb3_7TotSatis2.setSelected(true);
        }
        if (l.get(71).getValorRespuesta().equals("Moderadamente insatisfecho")) {
            jFEstudioSocialSocioeconomico.rb3_7ModInsa2.setSelected(true);
        }
        
        if (l.get(71).getValorRespuesta().equals("Moderadamente satisfecho")) {
            jFEstudioSocialSocioeconomico.rb3_7ModSatis2.setSelected(true);
        }
        if (l.get(71).getValorRespuesta().equals("Insatisfecho")) {
            jFEstudioSocialSocioeconomico.rb3_7Insatis2.setSelected(true);
        }
        if (l.get(71).getValorRespuesta().equals("Ni satisfecho ni insatisfecho")) {
            jFEstudioSocialSocioeconomico.rb3_7NiSatifNiIns2.setSelected(true);
        }
        if (l.get(72).getValorRespuesta().equals("Agua potable")) {
            jFEstudioSocialSocioeconomico.checkAguaPot2.setSelected(true);
        }
        if (l.get(73).getValorRespuesta().equals("Luz")) {
            jFEstudioSocialSocioeconomico.checkLuz2.setSelected(true);
        }
        if (l.get(74).getValorRespuesta().equals("Drenaje")) {
            jFEstudioSocialSocioeconomico.checkDrenaje2.setSelected(true);
        }

        if (l.get(75).getValorRespuesta().equals("Telefono")) {
            jFEstudioSocialSocioeconomico.checkTele2.setSelected(true);
        }
        if (l.get(76).getValorRespuesta().equals("Alumbrado público")) {
            jFEstudioSocialSocioeconomico.checkAlumb2.setSelected(true);
        }
        if (l.get(77).getValorRespuesta().equals("Pavimento")) {
            jFEstudioSocialSocioeconomico.checkPav2.setSelected(true);
        }
        if (l.get(78).getValorRespuesta().equals("Alcantarillado")) {
            jFEstudioSocialSocioeconomico.checkAlcant2.setSelected(true);
        }
        if (l.get(79).getValorRespuesta().equals("Servicio de limpia")) {
            jFEstudioSocialSocioeconomico.checkServLimp2.setSelected(true);
        }
        if (l.get(80).getValorRespuesta().equals("Transporte")) {
            jFEstudioSocialSocioeconomico.checkTransp2.setSelected(true);
        }

        if (l.get(81).getValorRespuesta().equals("WC")) {
            jFEstudioSocialSocioeconomico.rb4_1WC2.setSelected(true);
        }
        if (l.get(81).getValorRespuesta().equals("Letrina")) {
            jFEstudioSocialSocioeconomico.rb4_1Letrina2.setSelected(true);
        }
        if (l.get(81).getValorRespuesta().equals("Fosa séptica")) {
            jFEstudioSocialSocioeconomico.rb4_1Fosa2.setSelected(true);
        }
        if (l.get(81).getValorRespuesta().equals("Aire Libre")) {
            jFEstudioSocialSocioeconomico.rb4_1Aire2.setSelected(true);
        }
        if (l.get(82).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.rb4_2Si2.setSelected(true);
        }
        if (l.get(82).getValorRespuesta().equals("No")) {
            jFEstudioSocialSocioeconomico.rb4_2No2.setSelected(true);
        }

        //Formulario 3
        
        if (!l.get(83).getValorRespuesta().isEmpty()||!l.get(83).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbVecesComida.setSelectedItem(l.get(83).getValorRespuesta());
        }

        if (l.get(84).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.chkDesayuno.setSelected(true);
        }
        if (l.get(85).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.chkColacion.setSelected(true);
        }
        if (l.get(86).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.chkComida.setSelected(true);
        }
        if (l.get(87).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.chkColacion2.setSelected(true);
        }
        if (l.get(88).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.chkCena.setSelected(true);
        }

        if (!l.get(89).getValorRespuesta().isEmpty()||!l.get(89).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbFrutas13.setSelectedItem(l.get(89).getValorRespuesta());
        }

        if (!l.get(90).getValorRespuesta().isEmpty()||!l.get(90).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbVerduras.setSelectedItem(l.get(90).getValorRespuesta());
        }

        if (!l.get(91).getValorRespuesta().isEmpty()||!l.get(91).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbCereales.setSelectedItem(l.get(91).getValorRespuesta());
        }

        if (!l.get(92).getValorRespuesta().isEmpty()||!l.get(92).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbLeguminosas.setSelectedItem(l.get(92).getValorRespuesta());
        }

        if (!l.get(93).getValorRespuesta().isEmpty()||!l.get(93).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbCarneRes.setSelectedItem(l.get(93).getValorRespuesta());
        }

        if (!l.get(94).getValorRespuesta().isEmpty()||!l.get(94).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbCarneCerdo.setSelectedItem(l.get(94).getValorRespuesta());
        }

        if (!l.get(95).getValorRespuesta().isEmpty()||!l.get(95).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbPollo.setSelectedItem(l.get(95).getValorRespuesta());
        }

        if (!l.get(96).getValorRespuesta().isEmpty()||!l.get(96).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbPescado.setSelectedItem(l.get(96).getValorRespuesta());
        }

        if (!l.get(97).getValorRespuesta().isEmpty()||!l.get(97).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbHuevos.setSelectedItem(l.get(97).getValorRespuesta());
        }

        if (!l.get(98).getValorRespuesta().isEmpty()||!l.get(98).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbEnlatados.setSelectedItem(l.get(98).getValorRespuesta());
        }

        if (!l.get(99).getValorRespuesta().isEmpty()||!l.get(99).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbEmbutidos.setSelectedItem(l.get(99).getValorRespuesta());
        }

        if (!l.get(100).getValorRespuesta().isEmpty()||!l.get(100).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbFrituras.setSelectedItem(l.get(100).getValorRespuesta());
        }

         if (!l.get(101).getValorRespuesta().isEmpty()||!l.get(101).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbRefresco.setSelectedItem(l.get(101).getValorRespuesta());
        }

        jFEstudioSocialSocioeconomico.txtAlimentoObservaciones.setText(l.get(102).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt6DatosFamssig.setText(l.get(103).getValorRespuesta());

        //Formulario 4
        jFEstudioSocialSocioeconomico.txtAmbienteFamiliar.setText(l.get(104).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtAMbienteEscolar.setText(l.get(105).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtAmbienteLaboral1.setText(l.get(106).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtPeriodoEmpleo1.setText(l.get(107).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtAmbComunitario.setText(l.get(108).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt11ActividadesTiempoLibre.setText(l.get(109).getValorRespuesta());

        //Formulario 5
        jFEstudioSocialSocioeconomico.txtCulturales.setText(l.get(110).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtDeportivos.setText(l.get(111).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtRecreativos.setText(l.get(112).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtEducativos.setText(l.get(113).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txtOtros.setText(l.get(114).getValorRespuesta());

        if (l.get(115).getValorRespuesta().equals("Si")) {
            jFEstudioSocialSocioeconomico.rbSi13.setSelected(true);
        }
        if (l.get(115).getValorRespuesta().equals("No")) {
            jFEstudioSocialSocioeconomico.rbNo13.setSelected(true);
        }

        jFEstudioSocialSocioeconomico.txtVisitarDomicilioRazon.setText(l.get(116).getValorRespuesta());
        if (!l.get(117).getValorRespuesta().isEmpty()||!l.get(117).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbMedioTransporteUNEMECAPA.setSelectedItem(l.get(117).getValorRespuesta());
        }

        if (!l.get(118).getValorRespuesta().isEmpty()||!l.get(118).getValorRespuesta().equals("")) {
            jFEstudioSocialSocioeconomico.cbTiempo.setSelectedItem(l.get(118).getValorRespuesta());
        }

        jFEstudioSocialSocioeconomico.txtCosto.setText(l.get(119).getValorRespuesta());

        //Formulario 6
        jFEstudioSocialSocioeconomico.txt14InfoRecabada.setText(l.get(120).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt14ImpresionDiagnostica.setText(l.get(121).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt14PlanRehabi.setText(l.get(122).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt14PronosticoSocial.setText(l.get(123).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt15Cuota.setText(l.get(124).getValorRespuesta());
        jFEstudioSocialSocioeconomico.txt16Observaciones.setText(l.get(125).getValorRespuesta());

    }

    public void goBack() {
        JFPacientesTrabajadorSocial jFFichasIdentificacion = new JFPacientesTrabajadorSocial();
        jFFichasIdentificacion.NumEmpleado.setText(empleado.getIdEmpleado() + "");
        jFFichasIdentificacion.NumEmpleado.setVisible(false);
        ControladorFichaIdentificacion controladorFichaIdentificacion = new ControladorFichaIdentificacion(jFFichasIdentificacion, ficha, empleado);
        jFFichasIdentificacion.setVisible(true);
        jFEstudioSocialSocioeconomico.dispose();
    }

    public void validaCamposTyped() {
        this.jFEstudioSocialSocioeconomico.txtAlimentacion2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtAlimentacion2.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txxLuzAgua1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txxLuzAgua1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtOtros1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtOtros1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtRenta1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtRenta1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtDiversiones1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtDiversiones1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtVestido1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtVestido1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtTransporte1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtTransporte1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtEducacion1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtEducacion1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtAbonos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtAbonos.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtCombustible1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtCombustible1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtMedicamentos1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtMedicamentos1.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtRecamaras2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtRecamaras2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtSalacomedor2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtSalacomedor2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtCocina2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtCocina2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtBano2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtBano2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtPAtio2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtPAtio2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        this.jFEstudioSocialSocioeconomico.txtOTra2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE))) {
                    e.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtOTra2.getText().length() == 2) {
                    e.consume();
                }
            }
        });
        
        this.jFEstudioSocialSocioeconomico.txtCosto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == '.' || (c == KeyEvent.VK_BACK_SPACE))) {
                    evt.consume();
                }
                if (jFEstudioSocialSocioeconomico.txtCosto.getText().length() == 6) {
                    evt.consume();
                }
            }
        });
    }

}
