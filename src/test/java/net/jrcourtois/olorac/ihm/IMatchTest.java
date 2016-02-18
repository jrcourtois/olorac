/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;

/**
 *
 * @author jrc
 */
public class IMatchTest extends JFrame {

    /**
     * Constructor
     */
    public IMatchTest() {
        super();
        build();
    }

    /**
     * Method to init the window.
     */
    private void build() {
        setTitle("IMatchTest");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBackground(Color.white);

        Match m = new Match(new Team("A", null), new Team("B", null));
        IMatch im1 = new IMatch(m);
        m.setScore(2, 0);
        IMatch im2 = new IMatch(m);

        contentPanel.add(im1);
        contentPanel.add(im2);

        setContentPane(contentPanel);

    }
}
