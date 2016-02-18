/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.ihm.helper.AlternateList;

/**
 *
 * @author jrc
 */
public class IRanking extends JPanel {
    /** The ranking to display. */
    private Ranking ranking;
    
    private JList theList;
    /**
     * Constructor.
     * @param r the ranking to display
     */
    public IRanking(Ranking r) {
        this.ranking = r;
        DefaultListModel list = new DefaultListModel();
        for (Team t : r) {
            list.addElement(new ITeam(t));
        }
        theList = new JList(list);

        theList.setCellRenderer(new AlternateList());
        JScrollPane listScroller = new JScrollPane(theList);
        listScroller.setPreferredSize(new Dimension(250, 180));
        this.add(listScroller);
    }
    
    
}
