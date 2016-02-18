/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 *
 * @author jrc
 */
public class MatchTest extends DTTest {

    /**
     * @param testName the name of the test
     */
    public MatchTest(final String testName) {
        super(testName);
    }


    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of setScore method, of class Match.
     */
    public final void testSetScore() {
        System.out.println("setScore");
        assertNull(m.getScore());
        int h = 0;
        int a = 0;
        m.setScore(h, a);
        assertNotNull(m.getScore());
    }

    /**
     * Test of getHomeTeam method, of class Match.
     */
    public final void testGetHomeTeam() {
        System.out.println("getHomeTeam");
        Team expResult = homeTeam;
        Team result = m.getHomeTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAwayTeam method, of class Match.
     */
    public final void testGetAwayTeam() {
        System.out.println("getHomeTeam");
        Team expResult = awayTeam;
        Team result = m.getAwayTeam();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of teamWins method, of class Match.
     */
    public final void testTeamWins() {
        System.out.println("teamWins");
        assertFalse(m.teamWins(homeTeam));
        assertFalse(m.teamWins(awayTeam));
        m.setScore(0, 0);
        assertFalse(m.teamWins(homeTeam));
        assertFalse(m.teamWins(awayTeam));
        m.setScore(1, 0);
        assertTrue(m.teamWins(homeTeam));
        assertFalse(m.teamWins(awayTeam));
        m.setScore(0, 1);
        assertFalse(m.teamWins(homeTeam));
        assertTrue(m.teamWins(awayTeam));
    }

    /**
     * Test of teamDraw method, of class Match.
     */
    public final void testTeamDraw() {
        System.out.println("teamDraw");
        assertFalse(m.teamDraw(homeTeam));
        assertFalse(m.teamDraw(awayTeam));
        m.setScore(0, 0);
        assertTrue(m.teamDraw(homeTeam));
        assertTrue(m.teamDraw(awayTeam));
        m.setScore(1, 0);
        assertFalse(m.teamDraw(homeTeam));
        assertFalse(m.teamDraw(awayTeam));
        m.setScore(0, 1);
        assertFalse(m.teamDraw(homeTeam));
        assertFalse(m.teamDraw(awayTeam));
    }

    /**
     * Test of checkGamePlayed method, of class Match.
     */
    public final void testCheckGamePlayed() {
        System.out.println("checkGamePlayed");
        assertFalse(m.checkGamePlayed(homeTeam));
        assertFalse(m.checkGamePlayed(awayTeam));
        assertFalse(m.checkGamePlayed(null));
        m.setScore(1, 1);
        assertTrue(m.checkGamePlayed(homeTeam));
        assertTrue(m.checkGamePlayed(awayTeam));
        assertTrue(m.checkGamePlayed(null));
    }

    /**
     * Test of getScore method, of class Match.
     */
    public final void testGetScore() {
        System.out.println("getScore");
        assertNull(m.getScore());
        m.setScore(1, 0);
        assertNotNull(m.getScore());
    }

    /**
     * Test of isHomeTeam method, of class Match.
     */
    public final void testIsHomeTeam() {
        try {
            System.out.println("isHomeTeam");
            assertFalse(m.isHomeTeam(awayTeam));
            assertTrue(m.isHomeTeam(homeTeam));
        } catch (TeamNotInMatch ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }
}
