/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.compute;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.championship.Ligue1;
import net.jrcourtois.olorac.exceptions.MatchException;

/**
 *
 * @author jrc
 */
public class AlternativesMatchTest extends TestCase {

    /**
     * a match to build alternative upon it.
     */
    private final Match match;

    /**
     * Constructor.
     *
     * @param testName the name of the test
     */
    public AlternativesMatchTest(final String testName) {
        super(testName);
        Ligue1 l = new Ligue1();
        Team a = new Team("AwayTeam", l);
        Team b = new Team("HomeTeam", l);
        match = new Match(b, a);
    }

    /**
     * Test of iterator method, of class AlternativesMatch.
     */
    public final void testIterator() {
        System.out.println("iterator");
        AlternativesMatch instance = new AlternativesMatch(match);
        Iterator<Match> result = instance.iterator();
        assertNotNull(result);
    }

    /**
     * Test of getRandomMatch method, of class AlternativesMatch.
     */
    public final void testGetRandomMatch() {
        try {
            System.out.println("getRandomMatch");
            AlternativesMatch instance = new AlternativesMatch(match);
            Match result = instance.getRandomMatch();
            Assert.assertTrue(result.checkGamePlayed(match.getHomeTeam()));
            Assert.assertTrue(result.getProba() > 0.0);
        } catch (MatchException ex) {
            Assert.fail("No exception should have been raised: " + ex.getMessage());
        }
    }

    /**
     * Test of getRandomMatch method, of class AlternativesMatch.
     */
    public final void testGetRandomMatchNul() {
        try {
            System.out.println("getRandomMatchNul");
            match.setProbas(0, 1, 0);
            AlternativesMatch instance = new AlternativesMatch(match);
            Match result = instance.getRandomMatch();
            Assert.assertTrue(result.teamDraw(match.getHomeTeam()));
            Assert.assertTrue(result.getProba() > 0.0);
        } catch (MatchException ex) {
            Assert.fail("No exception should have been raised: " + ex.getMessage());
        }
    }

    public final void testGetAwayWin() {
        System.out.println("testGetAwayWin");
        AlternativesMatch instance = new AlternativesMatch(match);
        Match result = instance.getAwayWin(match);
        Assert.assertTrue(result.checkGamePlayed(match.getHomeTeam()));
        Assert.assertTrue(result.teamWins(match.getAwayTeam()));
    }
    public final void testGetHomeWin() {
        System.out.println("HomeWin");
        AlternativesMatch instance = new AlternativesMatch(match);
        Match result = instance.getHomeWin(match);
        Assert.assertTrue(result.checkGamePlayed(match.getHomeTeam()));
        Assert.assertTrue(result.teamWins(match.getHomeTeam()));
    }
    public final void testGetDraw() {
        System.out.println("testGetDraw");
        AlternativesMatch instance = new AlternativesMatch(match);
        Match result = instance.getDraw(match);
        Assert.assertTrue(result.checkGamePlayed(match.getHomeTeam()));
        Assert.assertTrue(result.teamDraw(match.getHomeTeam()));
    }
}
