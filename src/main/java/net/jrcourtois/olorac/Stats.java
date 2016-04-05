/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 * This class is attached to a team
 * 
 * @author jrc
 */
public class Stats {

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Stats.class.getName());
    /**
     * home goals.
     */
    private int homeScoredGoal = 0;
    /**
     * home goals conceded.
     */
    private int homeConcededGoal = 0;
    /**
     * away goals.
     */
    private int awayScoredGoal = 0;
    /**
     * away goals conceded.
     */
    private int awayConcededGoal = 0;
    /**
     * home wins.
     */
    private int homeWins = 0;
    /**
     * away wins.
     */
    private int awayWins = 0;
    /**
     * home draws.
     */
    private int homeDraws = 0;
    /**
     * away draws.
     */
    private int awayDraws = 0;
    /**
     * home lost matches.
     */
    private int homeLosts = 0;
    /**
     * away lost matches.
     */
    private int awayLosts = 0;

    /**
     * Constructor.
     *
     * @param aTeam the team to build stats on
     */
    public Stats(final Team aTeam) {
        this(aTeam, aTeam.getPlayedMatches());
    }

    /**
     *
     * @param t
     * @param list
     */
    public Stats(Team t, Iterable<Match> list) {
        computeStats(t, list);
    }

    /**
     * Copy Constructor.
     *
     * @param stats the stats to copy
     */
    public Stats(final Stats stats) {
        this.homeScoredGoal = stats.homeScoredGoal;
        this.homeConcededGoal = stats.homeConcededGoal;
        this.awayScoredGoal = stats.awayScoredGoal;
        this.awayConcededGoal = stats.awayConcededGoal;
        this.awayDraws = stats.awayDraws;
        this.awayLosts = stats.awayLosts;
        this.awayWins = stats.awayWins;
        this.homeDraws = stats.homeDraws;
        this.homeLosts = stats.homeLosts;
        this.homeWins = stats.homeWins;
    }

    /**
     * @return the scored goal
     */
    public final int getScoredGoal() {
        return homeScoredGoal + awayScoredGoal;
    }

    /**
     * @return the conceded goal
     */
    public final int getConcededGoal() {
        return homeConcededGoal + awayConcededGoal;
    }

    /**
     * @return the goal diff
     */
    public final int getGoalDiff() {
        return getScoredGoal() - getConcededGoal();
    }

    /**
     * @return the away draws matches
     */
    public final int getAwayDraws() {
        return awayDraws;
    }

    /**
     * @return the away lost matches
     */
    public final int getAwayLosts() {
        return awayLosts;
    }

    /**
     * @return the away wins matches
     */
    public final int getAwayWins() {
        return awayWins;
    }

    /**
     * @return the home draws matches
     */
    public final int getHomeDraws() {
        return homeDraws;
    }

    /**
     * @return the home lost matches
     */
    public final int getHomeLosts() {
        return homeLosts;
    }

    /**
     * @return the away wins matches
     */
    public final int getHomeWins() {
        return homeWins;
    }

    /**
     * @return the won matches
     */
    public final int getWins() {
        return homeWins + awayWins;
    }

    /**
     * @return the lost matches
     */
    public final int getLosts() {
        return homeLosts + awayLosts;
    }

    /**
     * @return the number of draws
     */
    public final int getDraws() {
        return awayDraws + homeDraws;
    }

    /**
     * @return the number of matches
     */
    public final int getNbMatches() {
        return awayDraws + homeDraws
                + awayLosts + homeLosts
                + awayWins + homeWins;
    }

    /**
     * @return the number of home matches
     */
    public final int getHomeMatches() {
        return homeLosts + homeDraws + homeWins;
    }

    /**
     * @return the number of away matches
     */
    public final int getAwayMatches() {
        return awayLosts + awayDraws + awayWins;
    }

    /**
     * @return the number of goals scored at home
     */
    public final int getHomeGoals() {
        return homeScoredGoal;
    }
    /**
     * @return the number of goals scored at home
     */
    public final int getAwayGoals() {
        return awayScoredGoal;
    }
    /**
     * @return the number of goals scored at home
     */
    public final int getHomeConcededGoals() {
        return homeConcededGoal;
    }
    /**
     * @return the number of goals scored at home
     */
    public final int getAwayConcededGoals() {
        return awayConcededGoal;
    }

    /**
     * @param t the team concerned by the stats
     * @param matches
     */
    protected void computeStats(final Team t, final Iterable<Match> matches) {
        for (Match m : matches) {
            try {

                boolean homeTeam = m.isHomeTeam(t);
                if (m.teamWins(t)) {
                    if (homeTeam) {
                        homeWins += 1;
                    } else {
                        awayWins += 1;
                    }
                } else if (m.teamDraw(t)) {
                    if (homeTeam) {
                        homeDraws += 1;
                    } else {
                        awayDraws += 1;
                    }

                } else {
                    if (homeTeam) {
                        homeLosts += 1;
                    } else {
                        awayLosts += 1;
                    }
                }
                if (homeTeam) {
                    homeScoredGoal += m.getScore().getHomeScore();
                    homeConcededGoal += m.getScore().getAwayScore();
                } else {
                    homeConcededGoal += m.getScore().getHomeScore();
                    homeScoredGoal += m.getScore().getAwayScore();
                }
                computeSpecificStats(m, homeTeam);
            } catch (TeamNotInMatch e) {
                LOGGER.log(Level.WARNING, "a match {0} not played by team {1}",
                        new Object[]{m.toString(), t.getName()});
            }
        }
    }
    
    protected void computeSpecificStats(Match m, Boolean isHome) {
        
    }
}