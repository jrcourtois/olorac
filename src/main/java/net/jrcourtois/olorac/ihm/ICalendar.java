package net.jrcourtois.olorac.ihm;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.ihm.helper.AlternateList;

/**
 *
 * @author jrc
 */
public class ICalendar extends JPanel {

    private Calendar theCalendar;

    /**
     * Constructor.
     * @param c the calendar
     */
    public ICalendar(Calendar c) {
        theCalendar = c;
        Ranking r = c.getRanking();
        Component add = this.add(new IRanking(r));
        this.add(buildMatchList());
    }
    /**
     * @return 
     */
    public final JPanel buildMatchList() {
        JPanel matchList = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        for (Match m : theCalendar.getMatches()) {
            listModel.addElement(new IMatch(m));
        }
        
        JList theList = new JList(listModel);

        theList.setCellRenderer(new AlternateList());
        JScrollPane listScroller = new JScrollPane(theList);
        listScroller.setPreferredSize(new Dimension(250, 180));
        matchList.add(listScroller);
        return matchList;
    }
}
