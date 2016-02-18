/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.pronostics;

import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Probability;
import net.jrcourtois.olorac.exceptions.MatchNotPlayed;

/**
 *
 * @author jrc
 */
public interface PronoMaker {

    /**
     * @param m the match to make prono on
     * @return the probability object of the match
     */
    Probability makeProno(Match m);
    /**
     * @param m the match to check
     * @return true if the probability equals to the result
     * @throws MatchNotPlayed
     */
    boolean checkProba(Match m) throws MatchNotPlayed ;
}
