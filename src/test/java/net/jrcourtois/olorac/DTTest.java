/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import junit.framework.TestCase;
import net.jrcourtois.olorac.championship.Ligue1;

/**
 *
 * @author jrc
 */
public abstract class DTTest extends TestCase {

    /**
     * a a team.
     */
    protected Team homeTeam;
    /**
     * a b team.
     */
    protected Team awayTeam;
    /**
     * a match.
     */
    protected Match m;

    /**
     * @param testName the name of the test
     */
    public DTTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Ligue1 l = new Ligue1();
        homeTeam = new Team("HomeTeam", l);
        awayTeam = new Team("AwayTeam", l);
        m = new Match(homeTeam, awayTeam);
    }
}
