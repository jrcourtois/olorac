/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.calendar;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Team;

/**
 *
 * @author jrc
 */
public class Ranking implements Iterable<Team> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Ranking.class.getName());

    /**
     * the list.
     */
    private List<Team> orderedList;
    /**
     * the probability for this ranking to occur.
     */
    private double proba;

    /**
     * Constructor.
     *
     * @param e the list of equipe
     * @param p the probability to occur
     */
    protected Ranking(
            final Set<Team> e,
            final double p) {

        orderedList = new ArrayList<Team>();
        if (e != null) {
            SortedSet<Team> rank = new ConcurrentSkipListSet<Team>();
            for (Team t : e) {
                rank.add(new Team(t));
            }
            for (Team t : rank) {
                orderedList.add(t);
            }
        }
        this.proba = p;
        LOGGER.log(Level.FINEST, this.toString());
    }

    @Override
    public final Iterator<Team> iterator() {
        return orderedList.iterator();
    }

    /**
     * @param rank the rank asked
     * @return team the team wich is ranked rank
     */
    public final Team getTeam(final int rank) {
        if (orderedList != null && rank < orderedList.size() && rank >= 0) {
            return orderedList.get(rank);
        }
        return null;
    }

    /**
     * @return the probability to occur
     */
    public final double getProba() {
        return proba;
    }

    /**
     * @param teamName the name of the team to know is rank
     * @return the rank of the team, 0 if the team is not in this ranking
     */
    public final int getRank(final String teamName) {
        int i = 0;
        for (Team t : orderedList) {
            i++;
            if (t.getName().equalsIgnoreCase(teamName)) {
                return i;
            }
        }
        return 0;
    }
    @Override
    public final String toString() {
        String s="----\n";
        for (Team t: orderedList) {
            s += t.getName() + " => " + t.getNbPoints() + "\n";
        }
        return s;
    }
}
