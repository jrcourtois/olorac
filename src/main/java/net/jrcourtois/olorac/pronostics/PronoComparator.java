/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.pronostics;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.exceptions.MatchNotPlayed;

/**
 *
 * @author jrc
 */
public class PronoComparator {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PronoComparator.class.getName());
    /**
     * the list of the prono makers to compare.
     */
    private final List<PronoMaker> pronoMakers;

    /**
     * Constructor
     * @param p
     */
    public PronoComparator(List<PronoMaker> p) {
        pronoMakers = p;
    }

    /**
     * @param cal the calendar to compare with
     * @return 
     * @throws CalendarException
     * @throws MatchException
     */
    public int compareWithCalendar(final Calendar cal)
            throws CalendarException, MatchException {
        int nbOK = 0;
        if (cal.getNbMatch() == 0) {
            return nbOK;
        }
        Calendar c = new Calendar(cal.getChampionship());
        final int nbDays = cal.getNbMatch() / cal.getNbMatchesPerDay();
        for (int i = 1; i <= nbDays; i++) {
            for (Match m : cal.getMatchesOfTheDay(i)) {
                if (m.checkGamePlayed(null)) {
                    c.addMatch(new Match(m));
                    Team home = m.getHomeTeam();
                    Team away = m.getAwayTeam();
                    for (PronoMaker pm : pronoMakers) {
                        try {
                            if (pm.checkProba(m)) {
                                nbOK++;
                            }
                        } catch (MatchNotPlayed ex) {
                            LOGGER.log(Level.FINE, "Match not played: {1}", m);
                        }
                    }
                    home.addPlayedMatch(m);
                    away.addPlayedMatch(m);
                }
            }
        }
        return nbOK;
    }
}
