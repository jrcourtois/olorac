package net.jrcourtois.olorac.championship;

import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;

/**
 * Championship class that allows to compute the number of points for a Ligue 1
 * championship
 *
 * @author jrc
 */
public class Ligue1 implements Championship {

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
    public final int compareTeams(final Team a, final Team b) {

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
        // comparing the goal difference
        res = statsA.getGoalDiff() - statsB.getGoalDiff();
        if (res != 0) {
            return res;

        }
        // then compare the amount of goals scored
        return statsA.getScoredGoal() - statsB.getScoredGoal();
    }

    @Override
    public Ranking checkRanking(Ranking r) {
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
