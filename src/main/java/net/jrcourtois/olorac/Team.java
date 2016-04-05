/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.championship.Championship;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.exceptions.MatchNotPlayed;
import net.jrcourtois.olorac.exceptions.RankingException;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 *
 * @author jrc
 */
public class Team implements Comparable<Team> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Team.class.getName());
    /**
     * The name of the team.
     */
    private String name;
    /**
     * the stats.
     */
    private Stats stats;
    /**
     * the match that have been played.
     */
    private Deque<Match> playedMatches;
    /**
     * The championship.
     */
    private Championship championship = null;

    /**
     * Constructor.
     *
     * @param n as the name of the team
     * @param c the championship
     */
    public Team(final String n, final Championship c) {
        name = n;
        stats = null;
        playedMatches = new LinkedList<Match>();
        championship = c;
    }

    /**
     * Copy Constructor.
     *
     * @param t the team to copy
     */
    public Team(final Team t) {
        name = t.name;
        playedMatches = new LinkedList<Match>(t.playedMatches);
        stats = null;
        championship = t.championship;
    }

    /**
     * @param m the match to add
     * @throws MatchException if the team did not played the match or the match
     * has not been played yet
     */
    public final void addPlayedMatch(final Match m) throws MatchException {
        if (m == null) {
            return;
        }
        m.isHomeTeam(this);
        if (m.isPlayed()) {
            playedMatches.addFirst(m);
            stats = null;
        } else {
            throw new MatchNotPlayed();
        }
    }

    /**
     * @param m the match to remove
     * @throws MatchException when a match has not been played
     */
    public final void delMatch(final Match m) {
        if (m == null) {
            return;
        }
        if (playedMatches.remove(m)) {
            stats = null;
        }
    }

    /**
     * @param m the match to remove
     * @throws MatchException when a match has not been played
     */
    public final void removeLastMatch() {
        if (playedMatches.isEmpty()) {
            return;
        }
        playedMatches.removeFirst();
        stats = null;
    }

    /**
     * @return the name of the team
     */
    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return getName();
    }

    /**
     * @param other a team
     * @return true if both teams have the same name
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass().equals(this.getClass())) {
            Team otherTeam = (Team) other;
            return otherTeam.getName().equalsIgnoreCase(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }


    @Override
    public int compareTo(Team t) {
        // First we check this are not the same teams
        if (t.equals(this)) {
            return 0;
        }
        int res = 0;
        if (championship != null) {
            try {
                res = championship.compareTeams(t, this);
            } catch (RankingException ex) {
                LOGGER.log(Level.WARNING, ex.getMessage());
                res = 0;
            }
        }
        if (res != 0) {
            return res;
        } else {
            return t.getName().compareTo(name);
        }
    }

    /**
     * @return the number of match played of this team
     */
    public final int nbMatchPlayed() {
        return playedMatches.size();
    }

    /**
     * @return the matches that have been played
     */
    public Iterable<Match> getPlayedMatches() {
        return playedMatches;
    }

    /**
     * @return the stats
     */
    public final Stats getStats() {
        if (stats == null) {
            System.out.println("Computing stats for " + this.getName());
            stats = championship.getStats(this);
            System.out.println(stats.toString());
        }
        return stats;
    }

    /**
     * @param nbMatch the number of match to return
     * @param where tell if the matches are played at home or away, Neutral is
     * for all matches
     * @return a list of matches
     * @throws TeamNotInMatch if there is a match in the list but was not played
     * by the team
     */
    public final List<Match> getLastMatches(
            final int nbMatch,
            final Probability.FIELD where)
            throws TeamNotInMatch {
        LinkedList<Match> l = new LinkedList<Match>();
        if (nbMatch < 1) {
            return l;
        }
        int nb = nbMatch;
        for (Match m : playedMatches) {
            switch (where) {
                case AWAY:
                    if (!m.isHomeTeam(this)) {
                        l.add(m);
                        nb--;
                    }
                    break;
                case HOME:
                    if (m.isHomeTeam(this)) {
                        l.add(m);
                        nb--;
                    }
                    break;
                default:
                    l.add(m);
                    nb--;
            }
            if (nb <= 0) {
                return l;
            }
        }
        return l;
    }

    /**
     * @return the number of points.
     */
    public Integer getNbPoints() {
        if (championship != null) {
            return championship.getNbPoints(stats);
        } else {
            return -1;
        }
    }
}
