/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.pronostics;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Probability;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.MatchException;

/**
 *
 * @author jrc
 */
public class FromStatsPronoTest extends TestCase {

    public FromStatsPronoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of makeProno method, of class FromStatsProno.
     * @throws net.jrcourtois.olorac.exceptions.MatchException
     */
    public void testMakeProno() throws MatchException {
        Team t1 = new Team("t1", null);
        Team t2 = new Team("t2", null);
        Match m = new Match(t1, t2);
        m.setScore(1, 0);
        t1.addPlayedMatch(m);
        t2.addPlayedMatch(m);
        FromStatsProno f = new FromStatsProno();
        Probability p = f.makeProno(m);
        Assert.assertEquals(1.0, p.getProbability(Probability.RESULT.WIN));
        Assert.assertEquals(0.0, p.getProbability(Probability.RESULT.LOSE));
        Assert.assertEquals(0.0, p.getProbability(Probability.RESULT.DRAW));
    }

    /**
     * Test of checkProba method, of class FromStatsProno.
     * @throws java.lang.Exception
     */
    public void testCheckProba() throws Exception {
        Team t1 = new Team("t1", null);
        Team t2 = new Team("t2", null);
        Match m = new Match(t1, t2);
        m.setScore(0, 0);

        FromStatsProno f = new FromStatsProno();
        Assert.assertTrue(f.checkProba(m));
        m.setScore(1, 0);
        Assert.assertFalse(f.checkProba(m));

    }
}
