/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.compute;

import java.util.ArrayList;
import net.jrcourtois.olorac.calendar.Ranking;

/**
 *
 * @author jrc
 */
@SuppressWarnings("serial")
class RankingAggregatorList
        extends ArrayList<Ranking>
        implements RankingAggregator {

    /**
     * Constructor.
     */
    public RankingAggregatorList() {
        super();
    }
}
