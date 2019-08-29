/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_unemecapa;

import Vista.JFLogin;
import Controlador.ControladorSesion;
import Modelo.Empleado;
import Modelo.Sesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author YareMtz
 */
public class UNEME_CAPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            Sesion sesion = new Sesion();
            Empleado empleado = new Empleado();
            JFLogin jFLogin = new JFLogin();
            
            ControladorSesion controladorSesion = new ControladorSesion(jFLogin, sesion, empleado);
            jFLogin.setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UNEME_CAPA.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(UNEME_CAPA.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(UNEME_CAPA.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(UNEME_CAPA.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        } catch(Exception ex){
            Logger.getLogger(UNEME_CAPA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
