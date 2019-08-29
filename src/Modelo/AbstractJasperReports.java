/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author YareMtz
 */
public class AbstractJasperReports {
    private static JasperReport pdf;
    private static JasperPrint pdfFilled;
    private static JasperViewer viewer;
    
    public static void createConsentimientoInformado(Map parameter, Connection cn, String path){
        try{
            
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parameter, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void prueba(String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, null, new JREmptyDataSource());
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void tamizaje(String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, null, new JREmptyDataSource());
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void createFichaIdentificacion(Map parametro,Connection cn, String path){
        try{
            
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parametro, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createCartaEgresoVoluntario(Map parametro,Connection cn, String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parametro, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createEstudio(Map parametro, Connection cn, String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parametro, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createNotaTratamiento(Map parametros, Connection cn, String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parametros, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createNotaMedica(Map parametros, Connection cn, String path){
        try{
            pdf = (JasperReport) JRLoader.loadObjectFromFile(path);
            pdfFilled = JasperFillManager.fillReport(pdf, parametros, cn);
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AbstractJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showViewer(){
        viewer = new JasperViewer(pdfFilled, false);
        viewer.setVisible(true);
    }
    
}
