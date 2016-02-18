/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Score;

/**
 *
 * @author jrc
 */
public class IMatch extends JPanel {

    /**
     * the match associated with the Panel
     */
    private Match theMatch;
    /**
     * The swing components of the panel.
     */
    private JLabel lHomeTeam;
    private JLabel lAwayTeam;
    private JLabel lScore;

    /**
     * Constructor
     *
     * @param m the match
     */
    public IMatch(Match m) {
        super();
        theMatch = m;
        setLayout(new FlowLayout());
        setOpaque(false);
        lHomeTeam = new JLabel(m.getHomeTeam().getName());
        lScore = new JLabel();
        lAwayTeam = new JLabel(m.getAwayTeam().getName());
        this.add(lHomeTeam);
        this.add(lScore);
        this.add(lAwayTeam);
        refreshScore();
    }

    /**
     * function that refresh the score of a match.
     */
    public final void refreshScore() {
        String txt = "-";
        if (theMatch.isPlayed()) {
            Score s = theMatch.getScore();
            txt = s.getHomeScore() + " - " + s.getAwayScore();
            if (s.homeTeamWins()) {
                lHomeTeam.setForeground(Color.GREEN);
                lAwayTeam.setForeground(Color.orange);
            } else if (s.drawMatch()) {
                lHomeTeam.setForeground(Color.lightGray);
                lAwayTeam.setForeground(Color.lightGray);
            } else {
                lHomeTeam.setForeground(Color.orange);
                lAwayTeam.setForeground(Color.green);

            }
        } else {
            lHomeTeam.setForeground(Color.GRAY);
            lAwayTeam.setForeground(Color.GRAY);
        }
        lScore.setText(txt);
        lScore.setBackground(Color.YELLOW);
    }
}
