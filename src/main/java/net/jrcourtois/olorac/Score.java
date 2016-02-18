/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

/**
 *
 * @author jrc
 */
public class Score {

    /**
     * the home score.
     */
    private int homeScore;
    /**
     * the away score.
     */
    private int awayScore;

    /**
     * Constructor.
     *
     * @param home the home score
     * @param away the away score
     */
    public Score(final int home, final int away) {
        homeScore = home;
        awayScore = away;
    }

    /**
     * @return the home score
     */
    public final int getHomeScore() {
        return homeScore;
    }

    /**
     * @return the away score
     */
    public final int getAwayScore() {
        return awayScore;
    }

    /**
     * @return true if home team wins, false otherwise
     */
    public final boolean homeTeamWins() {
        return homeScore > awayScore;
    }

    /**
     * @return true if away team wins, false otherwise
     */
    public final boolean awayTeamWins() {
        return awayScore > homeScore;
    }

    /**
     * @return true if the match has been a draw, false otherwise
     */
    public final boolean drawMatch() {
        return awayScore == homeScore;
    }

}
