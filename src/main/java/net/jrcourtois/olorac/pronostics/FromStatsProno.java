/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.pronostics;

import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Probability;
import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.MatchNotPlayed;

/**
 *
 * @author jrc
 */
public class FromStatsProno implements PronoMaker {

    @Override
    public Probability makeProno(final Match m) {
        Stats homeStats = m.getHomeTeam().getStats();
        Stats awayStats = m.getAwayTeam().getStats();
        int homeWin = homeStats.getHomeWins() + awayStats.getAwayLosts();
        int awayWin = homeStats.getHomeLosts() + awayStats.getAwayWins();
        int draw = homeStats.getHomeDraws() + awayStats.getAwayDraws();
        return new Probability(homeWin, draw, awayWin);
    }

    @Override
    public boolean checkProba(final Match m) throws MatchNotPlayed {
        if (! m.checkGamePlayed(null)) {
            throw new MatchNotPlayed();
        }
        Probability p = makeProno(m);
        Team homeTeam = m.getHomeTeam();
        switch (p.getMostProbable()) {
            case WIN:
                return m.teamWins(homeTeam);
            case LOSE:
                return !m.teamWins(homeTeam);
            case DRAW:
            default:
                return m.teamDraw(homeTeam);
        }
    }
}
