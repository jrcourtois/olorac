package net.jrcourtois.olorac.compute;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;

/**
 *
 * @author jrc
 */
@SuppressWarnings("serial")
public class PossibleRankingsAggregator
        extends HashMap<Team, Integer[]>
        implements RankingAggregator {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PossibleRankingsAggregator.class.getName());
    /**
     * number of events happening.
     */
    private int nbEvents;

    /**
     * Constructor.
     *
     * @param teams the list of teams to know the ranking
     */
    public PossibleRankingsAggregator(final Set<Team> teams) {
        nbEvents = 0;
        for (Team t : teams) {
            Integer[] nb = new Integer[teams.size()];
            for (int i = 0; i < teams.size(); i++) {
                nb[i] = 0;
            }
            put(t, nb);
        }
    }

    @Override
    public final boolean add(final Ranking r) {
        LOGGER.log(Level.FINE, "adding rank: {1}", r);
        nbEvents++;
        int i = 0;
        for (Team t : r) {
            Integer[] nb = get(t);
            nb[i] += 1;
            i++;
            put(t, nb);
        }
        return true;
    }

    @Override
    public final int size() {
        return nbEvents;
    }

    /**
     * @param t
     * @return
     */
    public String toString(Team t) {
        Integer[] rank = get(t);
        String ret = "";
        for (Integer i : rank) {
            ret = ret.concat("***    " + i.toString() + "\n");
        }
        return ret;
    }

    /**
     * @param t the team to get the probability
     * @param rank the rank expected
     * @return the total nb times a team has a rank
     */
    public int getProba(Team t, int rank) {
        if (t == null) {
            return 0;
        }
        Integer[] ranks = get(t);
        if (rank <= ranks.length) {
            return ranks[rank];
        } else {
            return 0;
        }
    }

    /**
     * @param t the team to get the probability
     * @param rank the rank expected
     * @return the percent times a team has a certain rank
     */
    public double getProbaPercent(Team t, int rank) {
        final double CENT = 100.0;
        if (t == null) {
            return 0;
        }
        Integer[] ranks = get(t);
        if (rank <= ranks.length) {
            return ((double) ranks[rank]) / (double) nbEvents * CENT;
        } else {
            return 0;
        }
    }
}
