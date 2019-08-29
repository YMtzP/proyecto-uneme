/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.JFConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class ConnectionMySQL {
    
    public static Connection getConnection(String ip,String port,String user,String password){
        Connection cn = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/carshopDEV", user, password);
            System.out.println("Connection succesful");
        
        }catch(SQLException sqle){
            System.out.println("Error: getConnection()->SQLException"+sqle.getMessage());
            JOptionPane.showMessageDialog(null,"Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor","Mensaje de error",JOptionPane.WARNING_MESSAGE);
            cn =null;
        }catch(Exception e){
            System.out.println("Error: getConnection()->Exception"+e.getMessage());
            JOptionPane.showMessageDialog(null,"Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor","Mensaje de error",JOptionPane.WARNING_MESSAGE);
            cn =null;
        }
        return cn;
    }
    
    public static boolean testingConnection(JFConfig jframe,String ip,String port,String user,String password){
         Connection cn = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/carshopDEV", user, password);
            System.out.println("Connection succesful");
        return true;
        }catch(SQLException sqle){
            System.out.println("Error: getConnection()->SQLException"+sqle.getMessage());
            JOptionPane.showMessageDialog(jframe,"Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor","Mensaje de error",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            System.out.println("Error: getConnection()->Exception"+e.getMessage());
            JOptionPane.showMessageDialog(jframe,"Posibles errores: \n\t-Datos de configuracion de la base de datos incorrectos.\n\t-Checar conexión del servidor","Mensaje de error",JOptionPane.WARNING_MESSAGE);

        }
        return false;
    }
}
