/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author YareMtz
 */
public class Tabla {

    JTable table;
    int margin = 10;
    
    public Tabla(JTable table) {
        this.table=table;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    
    
    public void packColumns() {
        for (int c = 0; c < table.getColumnCount(); c++) {
            packColumn(c);
        }
    }
    
    public void ancho(int ancho, int vColIndex){
        
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.
                getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;           
        TableCellRenderer renderer = col.getHeaderRenderer();
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        renderer = table.getCellRenderer(0, vColIndex);
        
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(0, vColIndex), false, false, 0,
                    vColIndex);
            width = Math.max(200, comp.getPreferredSize().width);
            width += 100 * margin;            // Configura a largura
        col.setPreferredWidth(width);
    }

    public void packColumn(int vColIndex) {
        TableModel model = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.
                getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;            
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;            
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r,
                    vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 10 * margin;            // Configura a largura
        col.setPreferredWidth(width);
    }
}
