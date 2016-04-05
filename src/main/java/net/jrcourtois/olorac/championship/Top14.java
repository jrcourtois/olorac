/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

import net.jrcourtois.olorac.RugbyStats;
import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;

/**
 *
 * @author jr
 */
public class Top14 implements Championship {

    /**
     * The number of points awarded for a win.
     */
    private static final int NB_POINTS_FOR_WIN = 4;
    /**
     * The number of points awarded for a draw.
     */
    private static final int NB_POINTS_FOR_DRAW = 2;

    @Override
    public final int getNbPoints(final Stats s) {
        if (s != null && s instanceof RugbyStats) {
            RugbyStats rs = (RugbyStats) s;
            return (NB_POINTS_FOR_WIN * rs.getWins()) + NB_POINTS_FOR_DRAW * rs.getDraws() + rs.getBonuses();
        }
        return 0;

    }

    @Override
    public final int compareTeams(final Team a, final Team b) {

        if (a == null || b == null) {
            return 0;
        }
        RugbyStats statsA = (RugbyStats) a.getStats();
        RugbyStats statsB = (RugbyStats) b.getStats();
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
        return new RugbyStats(aThis);
    }

}
