/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

import junit.framework.Assert;
import net.jrcourtois.olorac.DTTest;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Stats;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.RankingException;

/**
 *
 * @author jrc
 */
public abstract class ChampionshipTest extends DTTest {

    /**
     * 
     */
    private Championship championship = null;

    private int pointsAfterMatch[];
    
    public ChampionshipTest(String testName) {
        super(testName);
    }
    
    protected void setPoints(int[] points) {
        pointsAfterMatch = points;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Assert.assertNotNull(championship);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * @param c the championship to set
     */
    public void setChampionShip(Championship c) {
        this.championship = c;
    }

    /**
     * @return the championship
     */
    public Championship getChampionShip() {
        return this.championship;
    }

    /**
     * To test whether the getNbPoints function return 0 with null stats
     */
    public void testGetNbPointsWithNullStats() {
        Assert.assertEquals(0, championship.getNbPoints(null));
    }

    /**
     * To test whether the getNbPoints function return 0 with null stats
     * @throws RankingException
     */
    public void testGetCompareTeamWithNullTeams() throws RankingException {
        Assert.assertEquals(0, championship.compareTeams(null, null));
        Assert.assertEquals(0, championship.compareTeams(
                new Team("une", championship), null));
        Assert.assertEquals(0, championship.compareTeams(
                new Team("une", championship),
                new Team("deux", championship)));
    }

    /**
     * Test of getNbPoints method, of class Championship.
     * @throws Exception
     */
    public void testGetNbPoints() throws Exception{
        m.setScore(1, 1);
        homeTeam.addPlayedMatch(m);
        Stats s = new Stats(homeTeam);
        Assert.assertEquals(pointsAfterMatch[0], championship.getNbPoints(s));
        Match m2 = new Match(m);
        m2.setScore(2,1);
        homeTeam.addPlayedMatch(m2);
        s = new Stats(homeTeam);
        Assert.assertEquals(pointsAfterMatch[1], championship.getNbPoints(s));
        Match m3 = new Match(m);
        m3.setScore(0,1);
        homeTeam.addPlayedMatch(m3);
        s = new Stats(homeTeam);
        Assert.assertEquals(pointsAfterMatch[2], championship.getNbPoints(s));
        
    }

    /**
     * Test of compareTeams method, of class Championship.
     */
    public abstract void testCompareTeams();
}
