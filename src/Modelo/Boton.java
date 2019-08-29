/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author YareMtz
 */
public class Boton {
    

    public Boton() {
     
    }
    
    
    
    public void setColorButtonLogin(JPanel panel){
        panel.setBackground(new Color(233, 251, 245));
    }
    
    public void resetColorButtonLogin(JPanel panel){
        panel.setBackground(new Color(255,255,255));
    }
    
    
    public void setColorSelectedMenu(JPanel panel) {
        panel.setBackground(new Color(58, 156, 112));
    }

    public void resetColorMenu(JPanel panel) {
        panel.setBackground(new Color(0, 109, 75));
    }
    
    
}
