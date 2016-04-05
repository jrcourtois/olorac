/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.exceptions.RankingException;

/**
 *
 * @author jrc
 */
public interface Championship {

    /**
     * @param s the set of stats
     * @return a number of points for a given set of states
     */
    int getNbPoints(Stats s);

    /**
     * @param a the first team to compare
     * @param b the second team to compare
     * @return which is the best team
     */
    int compareTeams(Team a, Team b) throws RankingException;

    /**
     * @param r the rank to check
     * @return the rank which has been checked
     */
    Ranking checkRanking(Ranking r);

    /**
     * 
     * @param aThis
     * @return 
     */
    public Stats getStats(Team aThis);
}
