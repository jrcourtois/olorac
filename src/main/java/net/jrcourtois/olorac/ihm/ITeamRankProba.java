/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.jrcourtois.olorac.Team;

/**
 *
 * @author jrc
 */
public class ITeamRankProba extends JPanel {

    /**
     * The label containing the name
     */
    private JLabel lName;
    /**
     * The label containing the name
     */
    private JLabel lProba;
    /**
     * The label containing the name
     */
    private JLabel lRank;

    /**
     * Constructor
     *
     * @param t the team associated to the label
     */
    public ITeamRankProba(Team t, Integer rank, Double proba) {
        super();
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
        lRank = new JLabel(rank.toString());
        lName = new JLabel(t.getName());
        lProba = new JLabel(proba.toString());
        lRank.setOpaque(true);
        lRank.setBackground(Color.yellow);
        this.add(lRank);
        this.add(lName);
        this.add(lProba);
    }
}
