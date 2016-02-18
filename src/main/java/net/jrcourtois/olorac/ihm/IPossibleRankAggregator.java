/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.compute.PossibleRankingsAggregator;
import net.jrcourtois.olorac.ihm.helper.AlternateList;

/**
 *
 * @author jrc
 */
public final class IPossibleRankAggregator extends JPanel implements ActionListener {

    private JPanel matchList;
    private DefaultListModel list;
    private JComboBox btn;
    /**
     *
     */
    private PossibleRankingsAggregator aggregator;

    /**
     *
     * @param ag
     * @param t
     */
    public IPossibleRankAggregator(
            PossibleRankingsAggregator ag) {
        aggregator = ag;
        buildPanel();
        this.add(matchList);
        this.add(btn);
    }

    /**
     *
     */
    public void buildPanel() {
        matchList = new JPanel();

        list = new DefaultListModel();
        JList theList = new JList(list);
        theList.setCellRenderer(new AlternateList());
        JScrollPane listScroller = new JScrollPane(theList);
        listScroller.setPreferredSize(new Dimension(250, 180));
        matchList.add(listScroller);
        btn = new JComboBox(aggregator.keySet().toArray());
        btn.addActionListener(this);
    }

    /**
     * refresh a list with a team.
     *
     * @param t
     */
    public void refreshListWithTeam(Team t) {
        list.removeAllElements();
        for (int i = 0; i < aggregator.keySet().size(); i++) {
            Double proba =  aggregator.getProbaPercent(t, i);
            list.addElement(new ITeamRankProba(t, i, proba));
        }

    }
    
    // :TODO: managing the actions... 
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        final Team t = (Team)cb.getSelectedItem();
        refreshListWithTeam(t);

    }    
}
