/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author YareMtz
 */
public class Connection {
    
    public static java.sql.Connection getConnection() {
        java.sql.Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "%TargetHxC");
            //System.out.println("Connection succesful");

        } catch (SQLException sqle) {
            System.out.println("Error: getConnection()->SQLException" + sqle.getMessage());
            JOptionPane.showMessageDialog(null, "Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            cn = null;
        } catch (Exception e) {
            System.out.println("Error: getConnection()->Exception" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            cn = null;
        }
        return cn;
    }
    
}
