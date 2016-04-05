package net.jrcourtois.olorac.championship;

import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.exceptions.RankingException;

/**
 *
 * @author jrc
 */
public class Euro implements Championship {

    /**
     * The number of points awarded for a win.
     */
    private static final int NB_POINTS_FOR_WIN = 3;
    /**
     * The number of points awarded for a draw.
     */
    private static final int NB_POINTS_FOR_DRAW = 1;

    @Override
    public final int getNbPoints(final Stats s) {
        if (s == null) {
            return 0;
        }
        return (NB_POINTS_FOR_WIN * s.getWins()) + NB_POINTS_FOR_DRAW * s.getDraws();
    }

    @Override
    public final int compareTeams(final Team a, final Team b)
            throws RankingException {
        if (a == null || b == null) {
            return 0;
        }
        Stats statsA = a.getStats();
        Stats statsB = b.getStats();
        if (statsA == null || statsB == null) {
            return 0;
        }
        // comparing the number of points
        int res = getNbPoints(statsA) - getNbPoints(statsB);
        if (res != 0) {
            return res;
        }
        for (Match m : a.getPlayedMatches()) {
            if (m.teamWins(b)) {
                return -1;
            }
        }
        for (Match m : b.getPlayedMatches()) {
            if (m.teamWins(a)) {
                return 1;
            }
        }
        // comparing the goal difference
        res = statsA.getGoalDiff() - statsB.getGoalDiff();
        if (res != 0) {
            return res;

        }
        // then compare the amount of goals scored
        res = statsA.getScoredGoal() - statsB.getScoredGoal();
        if (res != 0) {
            return res;
        }
        throw new RankingException("The two teams are perfecly equals: " + a.getName() + " - " + b.getName());
    }

    @Override
    public Ranking checkRanking(Ranking r) {
        int score = -1;
        int nbScoreSame = 0;
        for (Team t : r) {
            int s = t.getNbPoints();
            if (s == score) {
                nbScoreSame++;
            } else {
                if (nbScoreSame <= 2) {
                    nbScoreSame = 1;
                    score = s;
                } else {
                    nbScoreSame = 0;
                }
            }
        }
        return r;
    }

    /**
     * 
     * @param aThis
     * @return 
     */
    @Override
    public Stats getStats(Team aThis) {
        return new Stats(aThis);
    }
}
