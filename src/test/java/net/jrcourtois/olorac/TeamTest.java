/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import net.jrcourtois.olorac.Probability;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.championship.Championship;
import net.jrcourtois.olorac.championship.Ligue1;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 *
 * @author jrc
 */
public class TeamTest extends DTTest {

    /**
     * a championship to test.
     */
    private Championship l;

    /**
     * Constructor.
     *
     * @param testName the name of the test
     */
    public TeamTest(final String testName) {
        super(testName);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        l = new Ligue1();
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addPlayedMatch method, of class Team.
     */
    public final void testAddMatch() {
        System.out.println("addMatch");
        Team instance = new Team(homeTeam);
        try {
            m.setScore(0, 0);
            instance.addPlayedMatch(m);
            assertTrue("No Exception has been raised", true);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * Test of addPlayedMatch method, of class Team.
     */
    public final void testAddMatchNotPlayed() {
        System.out.println("addMatchNotPlayed");
        Team instance = new Team(homeTeam);
        try {
            instance.addPlayedMatch(m);
            fail("An Exception should have been raised");
        } catch (MatchException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test of addPlayedMatch method, of class Team.
     */
    public final void testAddMatchNull() {
        System.out.println("addMatchNull");
        Team instance = new Team(homeTeam);
        try {
            instance.addPlayedMatch(null);
            assertTrue("No Exception has been raised", true);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * Test of addPlayedMatch method, of class Team.
     */
    public final void testAddMatchANonParticipating() {
        System.out.println("addMatch with a non particpant");
        Team instance = new Team("anotherTeam", l);
        try {
            m.setScore(0, 0);
            instance.addPlayedMatch(m);
            fail("an exception should have been raised.");
        } catch (TeamNotInMatch ex) {
            assertNotNull(ex);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * Test of delMatch method, of class Team.
     */
    public final void testDelMatch() {
        System.out.println("delMatch");
        Team instance = new Team(homeTeam);

        assertEquals(0, instance.nbMatchPlayed());
        m.setScore(1, 0);
        try {
            instance.addPlayedMatch(m);
            instance.addPlayedMatch(m);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        assertEquals(2, instance.nbMatchPlayed());
        instance.delMatch(null);
        assertEquals(2, instance.nbMatchPlayed());
        instance.delMatch(m);
        assertEquals(1, instance.nbMatchPlayed());
        instance.delMatch(m);
        assertEquals(0, instance.nbMatchPlayed());
    }

    /**
     * Test of delMatch method, of class Team.
     */
    public final void testRemoveMatch() {
        System.out.println("remove match");

        Team instance = new Team(homeTeam);
        try {
            m.setScore(0, 0);
            instance.addPlayedMatch(m);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        assertEquals(1, instance.nbMatchPlayed());
        instance.removeLastMatch();
        assertEquals(0, instance.nbMatchPlayed());
        instance.removeLastMatch();
        assertEquals(0, instance.nbMatchPlayed());
    }

    /**
     * Test of getName method, of class Team.
     */
    public final void testGetName() {
        System.out.println("getName");
        Team instance = new Team("Gogo", l);
        assertEquals("Gogo", instance.getName());
        assertNotNull(instance.toString());
        assertNotNull(instance.toString());
    }

    /**
     * Test equality.
     */
    public final void testEquals() {
        assertTrue(homeTeam.equals(homeTeam));
        assertFalse(homeTeam.equals(awayTeam));
        assertFalse(homeTeam.equals(this));
        assertFalse(homeTeam.equals(null));
    }
    
    /**
     * Test of compareTo method, of class Team.
     */
    public final void testCompareTo() {
        try {
            m.setScore(0, 1);
            homeTeam.addPlayedMatch(m);
            awayTeam.addPlayedMatch(m);
            int result = homeTeam.compareTo(awayTeam);
            assertTrue(result > 0);
            assertEquals(0, homeTeam.compareTo(new Team("HomeTeam", l)));
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * to test the getLastMatches method.
     */
    public final void testGetLastMatches() {
        try {

            m.setScore(0, 1);
            homeTeam.addPlayedMatch(m);
            awayTeam.addPlayedMatch(m);
            Iterable<Match> lastMatches = homeTeam.getLastMatches(5, Probability.FIELD.HOME);
            assertTrue(lastMatches.iterator().hasNext());
            lastMatches = awayTeam.getLastMatches(1, Probability.FIELD.HOME);
            assertFalse(lastMatches.iterator().hasNext());
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }
}
