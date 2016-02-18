/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm.helper;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author jrc
 */
public class AlternateList implements ListCellRenderer{
    
    @Override
    public Component getListCellRendererComponent(
            JList jlist, Object o, int i, boolean bln, boolean bln1) {
        JPanel t = (JPanel) o;
        if (i % 2 == 0) {
            t.setBackground(Color.decode("0xDDDDDD"));
        } else {
            t.setBackground(Color.decode("0xEEEEEE"));
        }
        return t;
    }
    
}
