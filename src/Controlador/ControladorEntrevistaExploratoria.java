/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DatePicker;
import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.Paciente;
import Modelo.RespuestasEncuesta;
import Vista.MensajeExitoso;
import Vista.Psicologo.JFEntrevistaExploratoria;
import Vista.Psicologo.JFPacientesPsicologo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author YareMtz
 */
public class ControladorEntrevistaExploratoria {

    JFEntrevistaExploratoria entrevistaExploratoria;
    Expediente expediente;
    Empleado empleado;

    public ControladorEntrevistaExploratoria(JFEntrevistaExploratoria entrevistaExploratoria, Expediente expediente, Empleado empleado) throws ParseException {
        this.entrevistaExploratoria = entrevistaExploratoria;
        this.expediente = expediente;
        this.empleado = empleado;
        boolean verifica = false;
        ManejadorEncuestas me = new ManejadorEncuestas();
        if (!me.existeEncuesta(expediente, 3)) {
            if (me.crearEncuesta(empleado, expediente, 3)) {
                verifica = true;
            }
        } else {
            verifica = true;
        }
        if (verifica) {
            List<RespuestasEncuesta> listaRespuesta = me.obtenerRespuestasEntrevistaExploratoria(expediente);
            cargarDatosBDDaFormulario(listaRespuesta);
            
            this.entrevistaExploratoria.btnSave1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario1();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario2();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario3();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario4();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario5();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario6();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.btnSave7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    List<RespuestasEncuesta> lista = obtieneRespuestasFormulario7();
                    if(me.guardarRespuestas(lista, expediente)){
                        MensajeExitoso msg = new MensajeExitoso(entrevistaExploratoria, true);
                        msg.msg.setText("<html>Se han guardado los datos de la <br>entrevista exploratoria correctamente.</html>");
                        msg.setVisible(true);
                   }
                }
                
            });
            this.entrevistaExploratoria.goBack4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.entrevistaExploratoria.goBack5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.entrevistaExploratoria.goBack6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }
            });
            this.entrevistaExploratoria.goBack7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.entrevistaExploratoria.goBack8.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            
            this.entrevistaExploratoria.btnRegresar6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }

            });
            this.entrevistaExploratoria.btnRegresar7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    goBack();
                }
            });
            
            this.entrevistaExploratoria.btnSiguiente1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.entrevistaExploratoria.btnSiguiente2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.entrevistaExploratoria.btnSiguiente3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.entrevistaExploratoria.btnSiguiente4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.entrevistaExploratoria.btnSiguiente5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(5);
                }
            });
            this.entrevistaExploratoria.btnSiguiente6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(6);
                }
            });
            
            this.entrevistaExploratoria.btnAnterior2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(0);
                }
            });
            this.entrevistaExploratoria.btnAnterior3.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(1);
                }
            });
            this.entrevistaExploratoria.btnAnterior4.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(2);
                }
            });
            this.entrevistaExploratoria.btnAnterior5.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(3);
                }
            });
            this.entrevistaExploratoria.btnAnterior6.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(4);
                }
            });
            this.entrevistaExploratoria.btnAnterior7.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entrevistaExploratoria.jTabbedPane1.setSelectedIndex(5);
                }
            });
        }else{
            goBack();
        }
    }
    
    public List obtieneRespuestasFormulario1(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt1_MotivoDeclarado.getText(), 269);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt2_MotivoConsultaRelacin.getText(), 270);
        l.add(rp);
        
        if(entrevistaExploratoria.dpFecha.getDate()==null){
            resp="";
        }else{
            resp=entrevistaExploratoria.dpFecha.getDate().toString();
        }
        rp = new RespuestasEncuesta(resp, 271);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt4_Descripcion.getText(), 272);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5_FactoresDesencadenantes.getText(), 273);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6_EvolucionEstadoActual.getText(), 274);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt7_Antecedentes.getText(), 275);
        l.add(rp);
        
        
        return l;
    }
    public List obtieneRespuestasFormulario2(){
       List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt4ExamenMental.getText(), 276);
        l.add(rp);
        //Tabla
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 0), 277);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 1), 278);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 2), 279);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 3), 280);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 4), 281);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 5), 282);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 0, 6), 283);
        l.add(rp);
        
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 0), 284);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 1), 285);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 2), 286);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 3), 287);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 4), 288);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 5), 289);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 1, 6), 290);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 0), 291);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 1), 292);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 2), 293);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 3), 294);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 4), 295);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 5), 296);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 2, 6), 297);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 0), 298);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 1), 299);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 2), 300);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 3), 301);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 4), 302);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 5), 303);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 3, 6), 304);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 0), 305);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 1), 306);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 2), 307);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 3), 308);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 4), 309);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 5), 310);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 4, 6), 311);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 0), 312);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 1), 313);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 2), 314);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 3), 315);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 4), 316);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 5), 317);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 5, 6), 318);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 0), 319);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 1), 320);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 2), 321);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 3), 322);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 4), 323);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 5), 324);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 6, 6), 325);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 0), 326);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 1), 327);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 2), 328);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 3), 329);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 4), 330);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 5), 331);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 7, 6), 332);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 0), 333);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 1), 334);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 2), 335);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 3), 336);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 4), 337);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 5), 338);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 8, 6), 339);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 0), 340);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 1), 341);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 2), 342);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 3), 343);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 4), 344);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 5), 345);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tabla5, 9, 6), 346);
        l.add(rp);
       
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5_1DrogaImpacto.getText(), 347);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(348);
        if(entrevistaExploratoria.rbSiDrogasInyectadas.isSelected()){
            rp.setValorRespuesta("si");
        }else if(entrevistaExploratoria.rbNoDrogasInyectadas.isSelected()){
            rp.setValorRespuesta("no");
        }else{
            rp.setValorRespuesta("");
        }
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(349);
        if(entrevistaExploratoria.rbSi3.isSelected()){
            rp.setValorRespuesta("si");
        }else if(entrevistaExploratoria.rbNo3.isSelected()){
            rp.setValorRespuesta("no");
        }else{
            rp.setValorRespuesta("");
        }
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt4_Cuales.getText(), 350);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5_EdadConsumoProblem.getText(), 351);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6_Porque.getText(), 352);
        l.add(rp);
        
        return l;
    }
    public List obtieneRespuestasFormulario3(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(353);
        if(entrevistaExploratoria.rb1NadaImportante.isSelected()){
            rp.setValorRespuesta("Nada importante");
        }else if(entrevistaExploratoria.rb1PocoImportante.isSelected()){
            rp.setValorRespuesta("Poco importante");
        }else if(entrevistaExploratoria.rb1AlgoImportante.isSelected()){
            rp.setValorRespuesta("Algo importante");
        }else if(entrevistaExploratoria.rb1Importante.isSelected()){
            rp.setValorRespuesta("Importante");
        }else{
            rp.setValorRespuesta("");
        }
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt2RazonesParaDejarConsumir.getText(), 354);
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.cbEscalaDejarDeConsumir.getSelectedIndex()+1+"", 355);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(356);
        if(entrevistaExploratoria.rb5_8SinProblem.isSelected()){
            rp.setValorRespuesta("1");
        }else if(entrevistaExploratoria.rb5_8UnPequeProblema.isSelected()){
            rp.setValorRespuesta("2");
        }else if(entrevistaExploratoria.rb5_8ProblemaMenor.isSelected()){
            rp.setValorRespuesta("3");
        }else if(entrevistaExploratoria.rb5_8ProblemaMayor.isSelected()){
            rp.setValorRespuesta("4");
        }else if(entrevistaExploratoria.rb5_8GranProblema.isSelected()){
            rp.setValorRespuesta("5");
        }else{
            rp.setValorRespuesta("");
        }
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5_8Consecuencia.getText(), 357);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6_1ConsumoActualTiem.getText(), 358);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6_2TiempoEntreUnaOtra.getText(), 359);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6_3TiempoEpisodio.getText(), 360);
        l.add(rp);
        
        return l;
    }
    public List obtieneRespuestasFormulario4(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(361);
        if(entrevistaExploratoria.chk1AcidoUrico.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(362);
        if(entrevistaExploratoria.chk3Gastritis.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(363);
        if(entrevistaExploratoria.chk2Ulcera.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(364);
        if(entrevistaExploratoria.chk4Pancreatitis.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(365);
        if(entrevistaExploratoria.chk5HigadoGraso.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(366);
        if(entrevistaExploratoria.chk6Cirrosis.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(367);
        if(entrevistaExploratoria.chk6OtrasAlteraciones.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(368);
        if(entrevistaExploratoria.chk7Desnutricion.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(369);
        if(entrevistaExploratoria.chk8Hinchazon.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(370);
        if(entrevistaExploratoria.chk9Delirios.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(371);
        if(entrevistaExploratoria.chk10ideas.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(372);
        if(entrevistaExploratoria.chk11Intentos.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(373);
        if(entrevistaExploratoria.chk12Ansiedad.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(374);
        if(entrevistaExploratoria.chk13Alteraciones.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(375);
        if(entrevistaExploratoria.chk14Alucionaciones.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(376);
        if(entrevistaExploratoria.chk15Lagunas.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(377);
        if(entrevistaExploratoria.chk16Depresion.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(378);
        if(entrevistaExploratoria.chk17Insomnio.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(379);
        if(entrevistaExploratoria.chk18Otros.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt19Cuales7_1.getText(), 380);
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(381);
        if(entrevistaExploratoria.chk20PerdidaConfianza.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(382);
        if(entrevistaExploratoria.chk21AgresionesFisica.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(383);
        if(entrevistaExploratoria.chk22AgresionesVerbales.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(384);
        if(entrevistaExploratoria.chk23SeparacionFam.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(385);
        if(entrevistaExploratoria.chk24Otros.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt25CualesProbFam.getText(), 386);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(387);
        if(entrevistaExploratoria.chk26AgresionVerbal.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(388);
        if(entrevistaExploratoria.chk27AgresionFisica.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(389);
        if(entrevistaExploratoria.chk28Separaciones.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(390);
        if(entrevistaExploratoria.chk29Divorcio.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(391);
        if(entrevistaExploratoria.chk30Celos.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(392);
        if(entrevistaExploratoria.chk31Infidelidad.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(393);
        if(entrevistaExploratoria.chk32NohayCom.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(394);
        if(entrevistaExploratoria.chk33InsatisfaccionSex.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(395);
        if(entrevistaExploratoria.chk34Impotencia.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(396);
        if(entrevistaExploratoria.chk35Otros.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(397);
        if(entrevistaExploratoria.chk36NoAplica.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt37CualesProbPar.getText(), 398);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(399);
        if(entrevistaExploratoria.chk38Aislamiento.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(400);
        if(entrevistaExploratoria.chk39PerdidaAmigos.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(401);
        if(entrevistaExploratoria.chk40AgresionFisica.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(402);
        if(entrevistaExploratoria.chk41AgresionVerbal.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(403);
        if(entrevistaExploratoria.chk42Otros.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt43Cuales7_4.getText(), 404);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(405);
        if(entrevistaExploratoria.chk50Ausentismo.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(406);
        if(entrevistaExploratoria.chk51Desempleo.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(407);
        if(entrevistaExploratoria.chk52Conflictos.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(408);
        if(entrevistaExploratoria.chk56AccidentesLab.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(409);
        if(entrevistaExploratoria.chk57ProblemasJefe.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(410);
        if(entrevistaExploratoria.chk58ProblComp.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(411);
        if(entrevistaExploratoria.chk59Otros.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt60Cuales7_5.getText(), 412);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt61DiasNoTrabajoDrogas.getText(), 413);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt62VecesPerdioEmpleo.getText(), 414);
        l.add(rp);
        
        
        return l;
    }
    public List obtieneRespuestasFormulario5(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(415);
        if(entrevistaExploratoria.chk1Detenciones1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(416);
        if(entrevistaExploratoria.chk2Prision1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(417);
        if(entrevistaExploratoria.chk3Demandas1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(418);
        if(entrevistaExploratoria.chk4Robos1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(419);
        if(entrevistaExploratoria.chk5Violacion1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(420);
        if(entrevistaExploratoria.chk6Armas1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(421);
        if(entrevistaExploratoria.chk7Homicidio1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(422);
        if(entrevistaExploratoria.chk8Otros1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt10Cuales1.getText(), 423);
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(424);
        if(entrevistaExploratoria.chk9Ninguno1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt7_9VecesArrestado.getText(), 425);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt7_10PorcentajeDrogas.getText(), 426);
        l.add(rp);
        
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(427);
        if(entrevistaExploratoria.chk25GastoExcesivo1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(428);
        if(entrevistaExploratoria.chk26Deudas1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(429);
        if(entrevistaExploratoria.chk27Empeñar1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        rp =  new RespuestasEncuesta();
        rp.setIdPregunta(430);
        if(entrevistaExploratoria.chk28PedirPRestado1.isSelected()){rp.setValorRespuesta("si");}else{rp.setValorRespuesta("no");}
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt7_12TipoProblemas.getText(), 431);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt30VecesDejarConsumir.getText(), 432);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt31PorqueAbstuvo.getText(), 433);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt32MayorPeriodoAbstinencia.getText(), 434);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt8_4Factores.getText(),435);
        l.add(rp);
        
        return l;
    }
    public List obtieneRespuestasFormulario6(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 0, 1), 436);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 0, 2), 437);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 0, 3), 438);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 0, 4), 439);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 1, 1), 440);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 1, 2), 441);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 1, 3), 442);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 1, 4), 443);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 2, 1), 444);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 2, 2), 445);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 2, 3), 446);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 2, 4), 447);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 3, 1), 448);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 3, 2), 449);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 3, 3), 450);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 3, 4), 451);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 4, 1), 452);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 4, 2), 453);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 4, 3), 454);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 4, 4), 455);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 5, 1), 456);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 5, 2), 457);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 5, 3), 458);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 5, 4), 459);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 6, 1), 460);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 6, 2), 461);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 6, 3), 462);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 6, 4), 463);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 7, 1), 464);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 7, 2), 465);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 7, 3), 466);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 7, 4), 467);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 8, 1), 468);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 8, 2), 469);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 8, 3), 470);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 8, 4), 471);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 9, 1), 472);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 9, 2), 473);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 9, 3), 474);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 9, 4), 475);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 10, 1), 476);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 10, 2), 477);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 10, 3), 478);
        l.add(rp);
        rp = new RespuestasEncuesta(obtenerRespuestaTabla(entrevistaExploratoria.tablaAbstinencia, 10, 4), 479);
        l.add(rp);

        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt1Modelo.getText(), 480);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt2OtrosTrat.getText(), 481);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt3Observaciones.getText(), 482);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt4ExpectativasTrat.getText(), 483);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5Compromiso.getText(), 484);
        l.add(rp);
        
        
        return l;
    }
    public List obtieneRespuestasFormulario7(){
        List<RespuestasEncuesta> l = new ArrayList<>();
        RespuestasEncuesta rp;
        String resp = "";
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt3CompromisoFam.getText(), 485);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt13Apariencia.getText(), 486);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt5Codigo.getText(), 487);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt6Diagnostico.getText(), 488);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt7Observaciones.getText(), 489);
        l.add(rp);
        
        rp = new RespuestasEncuesta();
        rp.setIdPregunta(490);
        if(entrevistaExploratoria.chk8Reservado.isSelected()){
            rp.setValorRespuesta("reservado");
        }else if(entrevistaExploratoria.chk8Favorable.isSelected()){
            rp.setValorRespuesta("favorable");
        }else if(entrevistaExploratoria.chk8Desfavorable.isSelected()){
            rp.setValorRespuesta("desfavorable");
        }else{
            rp.setValorRespuesta("");
        }
        l.add(rp);
        
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt9PlanTratamiento.getText(), 491);
        l.add(rp);
        rp = new RespuestasEncuesta(entrevistaExploratoria.txt10Recomendaciones.getText(), 492);
        l.add(rp);
        
        
        return l;
    }
    
    public void cargarDatosBDDaFormulario(List<RespuestasEncuesta> l) throws ParseException{
        int cont = 0;
        String res = "";
        DatePicker dp = new DatePicker();
        
        //formulario 1
        entrevistaExploratoria.txt1_MotivoDeclarado.setText(l.get(0).getValorRespuesta());
        entrevistaExploratoria.txt2_MotivoConsultaRelacin.setText(l.get(1).getValorRespuesta());
        
        if(!l.get(2).getValorRespuesta().equals("")){
            entrevistaExploratoria.dpFecha.setDate(dp.formatoStringtoLocalDate(l.get(2).getValorRespuesta()));
        }
        
        
        entrevistaExploratoria.txt4_Descripcion.setText(l.get(3).getValorRespuesta());
        entrevistaExploratoria.txt5_FactoresDesencadenantes.setText(l.get(4).getValorRespuesta());
        entrevistaExploratoria.txt6_EvolucionEstadoActual.setText(l.get(5).getValorRespuesta());
        entrevistaExploratoria.txt7_Antecedentes.setText(l.get(6).getValorRespuesta());
        
        
        //Formulario 2
        entrevistaExploratoria.txt4ExamenMental.setText(l.get(7).getValorRespuesta());
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(8).getValorRespuesta(),0, 0);
        if(l.get(9).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 0, 1);
        }else if(l.get(9).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 0, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(10).getValorRespuesta(),0, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(11).getValorRespuesta(),0, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(12).getValorRespuesta(),0, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(13).getValorRespuesta(),0, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(14).getValorRespuesta(),0, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(15).getValorRespuesta(),1, 0);
        if(l.get(16).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 1, 1);
        }else if(l.get(16).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 1, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(17).getValorRespuesta(),1, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(18).getValorRespuesta(),1, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(19).getValorRespuesta(),1, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(20).getValorRespuesta(),1, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(21).getValorRespuesta(),1, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(22).getValorRespuesta(),2, 0);
        if(l.get(23).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 2, 1);
        }else if(l.get(23).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 2, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(24).getValorRespuesta(),2, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(25).getValorRespuesta(),2, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(26).getValorRespuesta(),2, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(27).getValorRespuesta(),2, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(28).getValorRespuesta(),2, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(29).getValorRespuesta(),3, 0);
        if(l.get(30).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 3, 1);
        }else if(l.get(30).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 3, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(31).getValorRespuesta(),3, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(32).getValorRespuesta(),3, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(33).getValorRespuesta(),3, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(34).getValorRespuesta(),3, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(35).getValorRespuesta(),3, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(36).getValorRespuesta(),4, 0);
        if(l.get(37).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 4, 1);
        }else if(l.get(37).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 4, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(38).getValorRespuesta(),4, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(39).getValorRespuesta(),4, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(40).getValorRespuesta(),4, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(41).getValorRespuesta(),4, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(42).getValorRespuesta(),4, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(43).getValorRespuesta(),5, 0);
        if(l.get(44).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 5, 1);
        }else if(l.get(44).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 5, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(45).getValorRespuesta(),5, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(46).getValorRespuesta(),5, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(47).getValorRespuesta(),5, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(48).getValorRespuesta(),5, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(49).getValorRespuesta(),5, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(50).getValorRespuesta(),6, 0);
        if(l.get(51).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 6, 1);
        }else if(l.get(51).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 6, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(52).getValorRespuesta(),6, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(53).getValorRespuesta(),6, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(54).getValorRespuesta(),6, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(55).getValorRespuesta(),6, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(56).getValorRespuesta(),6, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(57).getValorRespuesta(),7, 0);
        if(l.get(58).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 7, 1);
        }else if(l.get(58).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 7, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(59).getValorRespuesta(),7, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(60).getValorRespuesta(),7, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(61).getValorRespuesta(),7, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(62).getValorRespuesta(),7, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(63).getValorRespuesta(),7, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(64).getValorRespuesta(),8, 0);
        if(l.get(65).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 8, 1);
        }else if(l.get(65).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 8, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(66).getValorRespuesta(),8, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(67).getValorRespuesta(),8, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(68).getValorRespuesta(),8, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(69).getValorRespuesta(),8, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(70).getValorRespuesta(),8, 6);
        
        entrevistaExploratoria.tabla5.setValueAt(l.get(71).getValorRespuesta(),9, 0);
        if(l.get(72).getValorRespuesta().equals("false")){
            entrevistaExploratoria.tabla5.setValueAt(false, 9, 1);
        }else if(l.get(72).getValorRespuesta().equals("true")){
            entrevistaExploratoria.tabla5.setValueAt(true, 9, 1);
        }
        entrevistaExploratoria.tabla5.setValueAt(l.get(73).getValorRespuesta(),9, 2);
        entrevistaExploratoria.tabla5.setValueAt(l.get(74).getValorRespuesta(),9, 3);
        entrevistaExploratoria.tabla5.setValueAt(l.get(75).getValorRespuesta(),9, 4);
        entrevistaExploratoria.tabla5.setValueAt(l.get(76).getValorRespuesta(),9, 5);
        entrevistaExploratoria.tabla5.setValueAt(l.get(77).getValorRespuesta(),9, 6);
        entrevistaExploratoria.txt5_1DrogaImpacto.setText(l.get(78).getValorRespuesta());
        if(l.get(79).getValorRespuesta().equals("si")){
            entrevistaExploratoria.rbSiDrogasInyectadas.setSelected(true);
        }else if(l.get(79).getValorRespuesta().equals("no")){
            entrevistaExploratoria.rbNoDrogasInyectadas.setSelected(true);
        }
        
        if(l.get(80).getValorRespuesta().equals("si")){
            entrevistaExploratoria.rbSi3.setSelected(true);
        }else if(l.get(80).getValorRespuesta().equals("no")){
            entrevistaExploratoria.rbNo3.setSelected(true);
        }
        entrevistaExploratoria.txt4_Cuales.setText(l.get(81).getValorRespuesta());
        entrevistaExploratoria.txt5_EdadConsumoProblem.setText(l.get(82).getValorRespuesta());
        entrevistaExploratoria.txt6_Porque.setText(l.get(83).getValorRespuesta());
        
        
        //Formulario 3
        if(l.get(84).getValorRespuesta().equals("Nada importante")){
            entrevistaExploratoria.rb1NadaImportante.setSelected(true);
        }else if(l.get(84).getValorRespuesta().equals("Poco importante")){
            entrevistaExploratoria.rb1PocoImportante.setSelected(true);
        }else if(l.get(84).getValorRespuesta().equals("Algo importante")){
            entrevistaExploratoria.rb1AlgoImportante.setSelected(true);
        }else if(l.get(84).getValorRespuesta().equals("Importante")){
            entrevistaExploratoria.rb1Importante.setSelected(true);
        }
        entrevistaExploratoria.txt2RazonesParaDejarConsumir.setText(l.get(85).getValorRespuesta());
        
        if(!l.get(86).getValorRespuesta().equals("")){
            entrevistaExploratoria.cbEscalaDejarDeConsumir.setSelectedIndex(Integer.parseInt(l.get(86).getValorRespuesta())-1);
        }
        
        
        if(l.get(87).getValorRespuesta().equals("1")){
            entrevistaExploratoria.rb5_8SinProblem.setSelected(true);
        }else if(l.get(87).getValorRespuesta().equals("2")){
            entrevistaExploratoria.rb5_8UnPequeProblema.setSelected(true);
        }else if(l.get(87).getValorRespuesta().equals("3")){
            entrevistaExploratoria.rb5_8ProblemaMenor.setSelected(true);
        }else if(l.get(87).getValorRespuesta().equals("4")){
            entrevistaExploratoria.rb5_8ProblemaMayor.setSelected(true);
        }else if(l.get(87).getValorRespuesta().equals("5")){
            entrevistaExploratoria.rb5_8GranProblema.setSelected(true);
        }
        
        entrevistaExploratoria.txt5_8Consecuencia.setText(l.get(88).getValorRespuesta());
        entrevistaExploratoria.txt6_1ConsumoActualTiem.setText(l.get(89).getValorRespuesta());
        entrevistaExploratoria.txt6_2TiempoEntreUnaOtra.setText(l.get(90).getValorRespuesta());
        entrevistaExploratoria.txt6_3TiempoEpisodio.setText(l.get(91).getValorRespuesta());
        
        //Formulario 4
         if(l.get(92).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk1AcidoUrico.setSelected(true);
         }
         if(l.get(94).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk2Ulcera.setSelected(true);
         }
         if(l.get(93).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk3Gastritis.setSelected(true);
         }
         if(l.get(95).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk4Pancreatitis.setSelected(true);
         }
         if(l.get(96).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk5HigadoGraso.setSelected(true);
         }
         if(l.get(97).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk6Cirrosis.setSelected(true);
         }
         if(l.get(98).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk6OtrasAlteraciones.setSelected(true);
         }
         if(l.get(99).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk7Desnutricion.setSelected(true);
         }
         if(l.get(100).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk8Hinchazon.setSelected(true);
         }
         if(l.get(101).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk9Delirios.setSelected(true);
         }
         if(l.get(102).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk10ideas.setSelected(true);
         }
         if(l.get(103).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk11Intentos.setSelected(true);
         }
         if(l.get(104).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk12Ansiedad.setSelected(true);
         }
         if(l.get(105).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk13Alteraciones.setSelected(true);
         }
         if(l.get(106).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk14Alucionaciones.setSelected(true);
         }
         if(l.get(107).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk15Lagunas.setSelected(true);
         }
         if(l.get(108).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk16Depresion.setSelected(true);
         }
         if(l.get(109).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk17Insomnio.setSelected(true);
         }
         if(l.get(110).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk18Otros.setSelected(true);
         }
         
         entrevistaExploratoria.txt19Cuales7_1.setText(l.get(111).getValorRespuesta());
         if(l.get(112).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk20PerdidaConfianza.setSelected(true);
         }
         if(l.get(113).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk21AgresionesFisica.setSelected(true);
         }
         if(l.get(114).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk22AgresionesVerbales.setSelected(true);
         }
         if(l.get(115).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk23SeparacionFam.setSelected(true);
         }
         if(l.get(116).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk24Otros.setSelected(true);
         }
         entrevistaExploratoria.txt25CualesProbFam.setText(l.get(117).getValorRespuesta());
         if(l.get(118).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk26AgresionVerbal.setSelected(true);
         }
         if(l.get(119).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk27AgresionFisica.setSelected(true);
         }
         if(l.get(120).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk28Separaciones.setSelected(true);
         }
         if(l.get(121).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk29Divorcio.setSelected(true);
         }
         if(l.get(122).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk30Celos.setSelected(true);
         }
         if(l.get(123).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk31Infidelidad.setSelected(true);
         }
         if(l.get(124).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk32NohayCom.setSelected(true);
         }
         if(l.get(125).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk33InsatisfaccionSex.setSelected(true);
         }
         if(l.get(126).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk34Impotencia.setSelected(true);
         }
         if(l.get(127).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk35Otros.setSelected(true);
         }
         if(l.get(128).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk36NoAplica.setSelected(true);
         }
         entrevistaExploratoria.txt37CualesProbPar.setText(l.get(129).getValorRespuesta());
         if(l.get(130).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk38Aislamiento.setSelected(true);
         }
         if(l.get(131).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk39PerdidaAmigos.setSelected(true);
         }
         if(l.get(132).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk40AgresionFisica.setSelected(true);
         }
         if(l.get(133).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk41AgresionVerbal.setSelected(true);
         }
         if(l.get(134).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk42Otros.setSelected(true);
         }
         entrevistaExploratoria.txt43Cuales7_4.setText(l.get(135).getValorRespuesta());
         if(l.get(136).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk50Ausentismo.setSelected(true);
         }
         if(l.get(137).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk51Desempleo.setSelected(true);
         }
         
         if(l.get(138).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk52Conflictos.setSelected(true);
         }
         if(l.get(139).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk56AccidentesLab.setSelected(true);
         }
         if(l.get(140).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk57ProblemasJefe.setSelected(true);
         }
         if(l.get(141).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk58ProblComp.setSelected(true);
         }
         if(l.get(142).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk59Otros.setSelected(true);
         }
         entrevistaExploratoria.txt60Cuales7_5.setText(l.get(143).getValorRespuesta());
         entrevistaExploratoria.txt61DiasNoTrabajoDrogas.setText(l.get(144).getValorRespuesta());
         entrevistaExploratoria.txt62VecesPerdioEmpleo.setText(l.get(145).getValorRespuesta());
         if(l.get(146).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk1Detenciones1.setSelected(true);
         }
         if(l.get(147).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk2Prision1.setSelected(true);
         }
         
         if(l.get(148).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk3Demandas1.setSelected(true);
         }
         if(l.get(149).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk4Robos1.setSelected(true);
         }
         if(l.get(150).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk5Violacion1.setSelected(true);
         }
         if(l.get(151).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk6Armas1.setSelected(true);
         }
         if(l.get(152).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk7Homicidio1.setSelected(true);
         }
         if(l.get(153).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk8Otros1.setSelected(true);
         }
         entrevistaExploratoria.txt10Cuales1.setText(l.get(154).getValorRespuesta());
         if(l.get(155).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk9Ninguno1.setSelected(true);
         }
         entrevistaExploratoria.txt7_9VecesArrestado.setText(l.get(156).getValorRespuesta());
         entrevistaExploratoria.txt7_10PorcentajeDrogas.setText(l.get(157).getValorRespuesta());
         
         if(l.get(158).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk25GastoExcesivo1.setSelected(true);
         }
         if(l.get(159).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk26Deudas1.setSelected(true);
         }
         if(l.get(160).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk27Empeñar1.setSelected(true);
         }
         if(l.get(161).getValorRespuesta().equals("si")){
             entrevistaExploratoria.chk28PedirPRestado1.setSelected(true);
         }
         entrevistaExploratoria.txt7_12TipoProblemas.setText(l.get(162).getValorRespuesta());
         entrevistaExploratoria.txt30VecesDejarConsumir.setText(l.get(163).getValorRespuesta());
         entrevistaExploratoria.txt31PorqueAbstuvo.setText(l.get(164).getValorRespuesta());
         entrevistaExploratoria.txt32MayorPeriodoAbstinencia.setText(l.get(165).getValorRespuesta());
         entrevistaExploratoria.txt8_4Factores.setText(l.get(166).getValorRespuesta());
         
         //Tabla
         cont = 167;
         for(int i = 0 ; i<entrevistaExploratoria.tablaAbstinencia.getRowCount(); i++){
             for(int j = 1; j<5; j++){
                 entrevistaExploratoria.tablaAbstinencia.setValueAt(l.get(cont).getValorRespuesta(), i, j);
                 cont++;
             }
         }
         entrevistaExploratoria.txt1Modelo.setText(l.get(211).getValorRespuesta());
         entrevistaExploratoria.txt2OtrosTrat.setText(l.get(212).getValorRespuesta());
         entrevistaExploratoria.txt3Observaciones.setText(l.get(213).getValorRespuesta());
         entrevistaExploratoria.txt4ExpectativasTrat.setText(l.get(214).getValorRespuesta());
         entrevistaExploratoria.txt5Compromiso.setText(l.get(215).getValorRespuesta());
         entrevistaExploratoria.txt3CompromisoFam.setText(l.get(216).getValorRespuesta());
         entrevistaExploratoria.txt13Apariencia.setText(l.get(217).getValorRespuesta());
         entrevistaExploratoria.txt5Codigo.setText(l.get(218).getValorRespuesta());
         entrevistaExploratoria.txt6Diagnostico.setText(l.get(219).getValorRespuesta());
         entrevistaExploratoria.txt7Observaciones.setText(l.get(220).getValorRespuesta());
         if(l.get(221).getValorRespuesta().equals("reservado")){entrevistaExploratoria.chk8Reservado.setSelected(true);}
         if(l.get(221).getValorRespuesta().equals("favorable")){entrevistaExploratoria.chk8Favorable.setSelected(true);}
         if(l.get(221).getValorRespuesta().equals("desfavorable")){entrevistaExploratoria.chk8Desfavorable.setSelected(true);}
         entrevistaExploratoria.txt9PlanTratamiento.setText(l.get(222).getValorRespuesta());
         entrevistaExploratoria.txt10Recomendaciones.setText(l.get(223).getValorRespuesta());
         
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

    public void goBack() {
        JFPacientesPsicologo jFPacientesPsicologo = new JFPacientesPsicologo();
        Paciente paciente = new Paciente();
        ControladorPacientesPsicologo controladorPacientesPsicologo = new ControladorPacientesPsicologo(jFPacientesPsicologo, empleado, expediente);
        jFPacientesPsicologo.setVisible(true);
        entrevistaExploratoria.dispose();
    }
}
