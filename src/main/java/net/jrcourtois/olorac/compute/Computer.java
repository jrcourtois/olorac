package net.jrcourtois.olorac.compute;

import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.exceptions.MatchException;

/**
 * Hello world!
 *
 */
public class Computer {
    
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Computer.class.getName());


    /**
     * @param c the calendar which is referrenced
     * @param ag the return list
     * @param d the day from when to run the alternative
     * @throws MatchException if a match can not be created
     */
    public void computePossibleResults(
            final Calendar c,
            final RankingAggregator ag,
            final int d) throws MatchException {
        LOGGER.fine("computePossibleResults");
        if (c == null) {
            LOGGER.severe("No calendar provided");
            return;
        }
        if (ag == null) {
            return;
        }
        if (d > c.getNbMatch() || d < 0) {
            return;
        }
        recAlternate(c, d, ag, 1.0);
    }

    /**
     * @param c the calendar which is referenced
     * @param ag the return list
     * @param nbTimes the number of times to compute
     * 
     * @throws MatchException if a match can not be created
     */
    public void computeNumberOfAlternatives(
            final Calendar c,
            final RankingAggregator ag,
            final int nbTimes) throws MatchException {
        if (c == null) {
            return;
        }
        if (ag == null) {
            return;
        }
        for (int i = 0; i < nbTimes; i++) {
            recRandom(c, 1, ag, 1.0);
        }
    }

    /**
     * @param cal the calendar which is referrenced
     * @param day the day from when to run the alternative
     * @param ag the return list
     * @param proba the probability of this result to occur
     * @throws MatchException if the match cannot be created
     */
    private void recAlternate(
            final Calendar cal,
            final int day,
            final RankingAggregator ag,
            final double proba) throws MatchException {
        Match m = cal.getMatch(day);
        // Exit condition
        if (m != null) {
            Team home = m.getHomeTeam();
            Team away = m.getAwayTeam();
            AlternativesMatch alt = new AlternativesMatch(m);
            for (Match match : alt) {
                if (match.getProba() > 0.0) {
                    home.addPlayedMatch(match);
                    away.addPlayedMatch(match);
                    recAlternate(cal, day + 1, ag, proba * match.getProba());
                    home.removeLastMatch();
                    away.removeLastMatch();
                }
            }
        } else {
            ag.add(cal.getRanking(proba));
        }
    }

    /**
     * @param cal the calendar which is referrenced
     * @param day the day from when to run the alternative
     * @param ag the return list
     * @param proba the probability of this result to occur
     * @throws MatchException if the match has not been played
     */
    private void recRandom(
            final Calendar cal,
            final int day,
            final RankingAggregator ag,
            final double proba) throws MatchException {
        Match m = cal.getMatch(day);
        // Exit condition
        if (m != null) {
//            Team home = m.getHomeTeam();
//            Team away = m.getAwayTeam();
            AlternativesMatch alt = new AlternativesMatch(m);
            Match match = alt.getRandomMatch();
            cal.setMatch(day,match);
//            home.addPlayedMatch(match);
//            away.addPlayedMatch(match);
            recRandom(cal, day + 1, ag, proba * match.getProba());
            cal.setMatch(day, m);
//            home.removeLastMatch();
//            away.removeLastMatch();
        } else {
            ag.add(cal.getRanking(proba));
        }
    }
}
