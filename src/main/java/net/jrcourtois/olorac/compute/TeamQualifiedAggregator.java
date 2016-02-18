/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.compute;

import java.util.HashMap;
import java.util.Set;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.Team;

/**
 *
 * @author jrc
 */
@SuppressWarnings("serial")
public class TeamQualifiedAggregator
        extends HashMap<Team, Integer>
        implements RankingAggregator {

    /**
     * number of events happening.
     */
    private int nbEvents;
    /**
     * limit where the teams are qualified.
     */
    private final Integer nbQ;

    /**
     * Constructor.
     *
     * @param teams the list of teams
     * @param nbQualified the number of teams that are qualified per group
     */
    public TeamQualifiedAggregator(final Set<Team> teams, final int nbQualified) {
        nbEvents = 0;
        nbQ = nbQualified;
        for (Team t : teams) {
            put(t, 0);
        }
    }

    /**
     * @param t the team to getTeam information
     * @return the proba to be qualified
     */
    public final double getProba(final Team t) {
        final double cent = 100;
        return (double) get(t) / (double) nbEvents * cent;
    }

    @Override
    public final boolean add(final Ranking r) {
        if (r == null) {
            return false;
        }
        nbEvents++;
        for (int i = 0; i < nbQ; i++) {
            Team t = r.getTeam(i);
            int nb = get(t);
            put(t, nb + 1);
        }
        return true;
    }

    @Override
    public final int size() {
        return nbEvents;
    }
}
