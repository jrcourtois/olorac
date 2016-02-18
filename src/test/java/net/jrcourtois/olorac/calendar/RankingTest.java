/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.calendar;

import java.util.HashSet;
import java.util.Set;
import net.jrcourtois.olorac.DTTest;
import net.jrcourtois.olorac.Team;

/**
 *
 * @author jrc
 */
public class RankingTest extends DTTest {

    /**
     * a list of teams.
     */
    private Set<Team> teams;

    /**
     * @param testName the name of the test
     */
    public RankingTest(final String testName) {
        super(testName);
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        teams = new HashSet<Team>();
        m.setScore(1, 0);
        homeTeam.addPlayedMatch(m);
        awayTeam.addPlayedMatch(m);
        teams.add(homeTeam);
        teams.add(awayTeam);
    }

    /**
     * Test of iterator method, of class Ranking.
     */
    public final void testIterator() {
        System.out.println("iterator");
        Ranking instance = new Ranking(null, 0.0);
        assertNotNull(instance.iterator());
        instance = new Ranking(teams, 2);
        assertNotNull(instance.iterator());
    }

    /**
     * Test of getTeam method, of class Ranking.
     */
    public final void testGetTeam() {
        System.out.println("getTeam");
        Ranking instance = new Ranking(teams, 2);
        assertNull(instance.getTeam(2));
        assertNull(instance.getTeam(-1));
        assertNotNull(instance.getTeam(1));
        assertNotNull(instance.getTeam(0));
    }

    /**
     * Test of getProba method, of class Ranking.
     */
    public final void testGetProba() {
        System.out.println("getProba");
        Ranking instance = new Ranking(null, 0.0);
        assertEquals(0.0, instance.getProba());
        instance = new Ranking(teams, 1.0);
        assertEquals(1.0, instance.getProba());
    }

    /**
     * Test of getRank method, of class Ranking.
     */
    public final void testGetRank() {
        System.out.println("getRank");
        Ranking instance = new Ranking(null, 1.0);
        assertEquals(0, instance.getRank("nimportequoi"));
        instance = new Ranking(teams, 1.0);
        assertEquals(1, instance.getRank("HomeTeam"));
        assertEquals(2, instance.getRank("AwayTeam"));
        assertEquals(0, instance.getRank("nimportequoi"));
    }
}
