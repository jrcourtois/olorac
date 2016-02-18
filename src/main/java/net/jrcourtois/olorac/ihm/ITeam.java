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
public class ITeam extends JPanel {

    /**
     * the team associated to the JPanel
     */
    private Team theTeam;
    /**
     * The label containing the name
     */
    private JLabel lName;
    /**
     * The label containing the name
     */
    private JLabel lPoints;

    /**
     * Constructor
     * @param t the team associated to the label
     */
    public ITeam(Team t) {
        super();
        theTeam = t;
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
        lName = new JLabel(t.getName());
        lPoints = new JLabel(t.getNbPoints().toString());
        this.add(lName);
        this.add(lPoints);
    }

}
