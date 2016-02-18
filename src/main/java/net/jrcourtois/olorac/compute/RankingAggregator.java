/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.compute;

import net.jrcourtois.olorac.calendar.Ranking;

/**
 *
 * @author jrc
 */
public interface RankingAggregator {

    /**
     * @param r the ranking to add into the aggregator
     * @return true if the
     */
    boolean add(Ranking r);

    /**
     * @return the number of elements aggregated
     */
    int size();
}
