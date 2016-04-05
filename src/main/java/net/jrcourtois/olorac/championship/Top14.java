/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.RugbyStats;
import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

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
        // 2. points terrain obtenus dans les matchs entre équipes concernées ;
        // 3. différence de points dans les matchs entre équipes concernées ;
        // 4. différence entre essais marqués et concédés dans les matchs entre équipes concernées ;
        res = compareMatches(a, b);
        if (res != 0) {
            return res;
        }
        // 5. différence de points 
        // comparing the goal difference
        res = statsA.getGoalDiff() - statsB.getGoalDiff();
        if (res != 0) {
            return res;

        }
        // 6. différence entre essais marqués et concédés ;
        res = statsA.getTryDiff() - statsB.getTryDiff();
        if (res != 0) {
            return res;
        }
        // 7. nombre de points marqués ;
        res = statsA.getScoredGoal() - statsB.getScoredGoal();
        if (res != 0) {
            return res;
        }
        // 8. nombre d'essais marqués
        res = statsA.getTry() - statsB.getTry();
        if (res != 0) {
            return res;
        }
        return 0;
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

    private int compareMatches(Team a, Team b) {
        for (Match m : a.getPlayedMatches()) {
            if (b.playedMatch(m)) {
                
            }
        }
        // 2. points terrain obtenus dans les matchs entre équipes concernées ;
        // 3. différence de points dans les matchs entre équipes concernées ;
        // 4. différence entre essais marqués et concédés dans les matchs entre équipes concernées ;
        return 0;
    }

}
