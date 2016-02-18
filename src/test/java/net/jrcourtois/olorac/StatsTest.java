/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Probability;
import net.jrcourtois.olorac.championship.Ligue1;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 *
 * @author jrc
 */
public class StatsTest extends DTTest {

    /**
     * Stats to test.
     */
    private Stats myStats;

    /**
     * Constructor.
     *
     * @param testName the name of the test
     */
    public StatsTest(final String testName) {
        super(testName);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        m.setScore(1, 1);
        homeTeam.addPlayedMatch(m);
        myStats = new Stats(homeTeam);
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getNbPoints method, of class Stats.
     */
    public final void testGetNbPoints() {
        Ligue1 l = new Ligue1();
        assertEquals(1, l.getNbPoints(myStats));
    }

    /**
     * Test of getScoredGoal method, of class Stats.
     */
    public final void testGetGoals() {
        assertEquals(1, myStats.getScoredGoal());
        assertEquals(0, myStats.getGoalDiff());
        assertEquals(1, myStats.getConcededGoal());
        assertEquals(1, myStats.getHomeGoals());
        assertEquals(0, myStats.getAwayGoals());
        assertEquals(1, myStats.getHomeConcededGoals());
        assertEquals(0, myStats.getAwayConcededGoals());
    }

    /**
     * Test of getGoalDiff method, of class Stats.
     */
    public final void testGetResult() {
        assertEquals(0, myStats.getAwayDraws());
        assertEquals(0, myStats.getAwayWins());
        assertEquals(0, myStats.getAwayLosts());
        assertEquals(0, myStats.getHomeLosts());
        assertEquals(1, myStats.getHomeDraws());
        assertEquals(0, myStats.getHomeWins());
    }
    /**
     * Test of getGoalDiff method, of class Stats.
     */
    public final void testGetMatches() {
        assertEquals(0, myStats.getAwayMatches());
        assertEquals(1, myStats.getHomeMatches());
        assertEquals(1, myStats.getNbMatches());
    }

    /**
     * Test of compareTo method, of class Stats.
     */
    public void testConstructor() throws TeamNotInMatch {
        Stats s = new Stats(myStats);
        assertNotNull(s);
        s = new Stats(homeTeam, homeTeam.getLastMatches(1, Probability.FIELD.NEUTRAL));
        assertNotNull(s);
    }
}
