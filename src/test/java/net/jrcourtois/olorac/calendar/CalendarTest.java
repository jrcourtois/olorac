/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.calendar;

import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import net.jrcourtois.olorac.DTTest;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.CalendarException;

/**
 *
 * @author jrc
 */
public class CalendarTest extends DTTest {

    /**
     * Constructor.
     *
     * @param testName the name of the test
     */
    public CalendarTest(final String testName) {
        super(testName);
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addMatch method, of class Calendar.
     */
    public final void testAddMatch() {
        System.out.println("addMatch");
        Match match = null;
        Calendar instance = new Calendar(null);
        try {
            instance.addMatch(match);
            Assert.fail("An exception should have been raised");
        } catch (CalendarException e) {
            Assert.assertNotNull(e);
        }
        try {
            instance.addMatch(m);
            Assert.assertEquals(1, instance.getNbMatch());
        } catch (CalendarException e) {
            Assert.fail("Should not raise an exception: " + e);
        }
    }

    /**
     * Test of getMatch method, of class Calendar.
     */
    public final void testGetMatch() {
        System.out.println("getMatch");
        Calendar instance = new Calendar(null);
        assertNull(instance.getMatch(0));
        try {
            instance.addMatch(m);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        assertNull(instance.getMatch(2));
        assertEquals(m, instance.getMatch(1));
    }

    /**
     * Test of getTeams method, of class Calendar.
     */
    public final void testGetTeams() {
        System.out.println("getTeams");
        Calendar instance = new Calendar(null);
        Set<Team> result = instance.getTeams();
        assertTrue(result.isEmpty());
        try {
            instance.addMatch(m);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        result = instance.getTeams();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    /**
     * Test Get Nb Match.
     */
    public final void testGetNbMatch() {
        System.out.println("getNbMatch");
        Calendar instance = new Calendar(null);
        Assert.assertEquals(0, instance.getNbMatch());
        try {
            instance.addMatch(m);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        Assert.assertEquals(1, instance.getNbMatch());
    }

    /**
     * Test Get Team.
     */
    public final void testGetTeam() {
        System.out.println("getTeam");
        Calendar instance = new Calendar(null);
        Assert.assertNull(instance.getTeam(null));
        Assert.assertNull(instance.getTeam(""));
        Assert.assertNull(instance.getTeam("HomeTeam"));
        try {
            instance.addMatch(m);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        Assert.assertNull(instance.getTeam(null));
        Assert.assertNull(instance.getTeam(""));
        Assert.assertNotNull(instance.getTeam("HomeTeam"));
        Assert.assertNotNull(instance.getTeam("awayteam"));
        Assert.assertNull(instance.getTeam("nimportequoi"));
    }

    public final void testSetProbas() {
        System.out.println("setProbas");
        Calendar instance = new Calendar(null);
        try {
            instance.addMatch(m);
            instance.setProbas("HomeTeam", 1, 0, 0, 0, 0, 1);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        Match m2 = instance.getMatch(1);
        Assert.assertEquals(0, m2.getProbaAwayWin());
        Assert.assertEquals(0, m2.getProbaDraw());
        Assert.assertEquals(1, m2.getProbaHomeWin());

    }

    public final void testGetNbMatchPerDay() {
        System.out.println("setProbas");
        Calendar instance = new Calendar(null);
        try {
            instance.addMatch(m);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }

        Assert.assertEquals(1, instance.getNbMatchesPerDay());
    }

    public final void testGetMatchesOfTheDay() {
        System.out.println("setProbas");
        Calendar instance = new Calendar(null);
        Team t3 = new Team("ThirdTeam", null);
        Team t4 = new Team("winner", null);
        Match m2 = new Match(t4, t3);
        Match m3 = new Match(awayTeam, t3);
        Match m4 = new Match(t4, homeTeam);
        Match m5 = new Match(t3, homeTeam);
        Match m6 = new Match(t4, awayTeam);
        try {
            instance.addMatch(m);
            instance.addMatch(m2);
            instance.addMatch(m3);
            instance.addMatch(m4);
            instance.addMatch(m5);
            instance.addMatch(m6);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        List<Match> expectedResult = instance.getMatchesOfTheDay(2);
        Assert.assertEquals(2, expectedResult.size());
        expectedResult = instance.getMatchesOfTheDay(5);
        Assert.assertNull(expectedResult);
    }

    public final void testGetMatchesFromTeam() {
        System.out.println("setProbas");
        Calendar instance = new Calendar(null);
        Team t3 = new Team("ThirdTeam", null);
        Match m2 = new Match(homeTeam, t3);
        Match m3 = new Match(awayTeam, t3);
        Match m4 = new Match(awayTeam, homeTeam);
        Match m5 = new Match(t3, homeTeam);
        Match m6 = new Match(t3, awayTeam);
        try {
            instance.addMatch(m);
            instance.addMatch(m2);
            instance.addMatch(m3);
            instance.addMatch(m4);
            instance.addMatch(m5);
            instance.addMatch(m6);
        } catch (CalendarException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }
        List<Match> expectedResult = instance.getMatchesFromTeam(homeTeam, t3);
        Assert.assertEquals(2, expectedResult.size());
    }
}
