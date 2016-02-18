/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

/**
 *
 * @author jrc
 */
public class Ligue1Test extends ChampionshipTest {
    
    public Ligue1Test(String testName) {
        super(testName);
        int points[] = {1,4,4};
        setPoints(points);
    }
    
    @Override
    protected void setUp() throws Exception {
        setChampionShip(new Ligue1());
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of compareTeams method, of class Ligue1.
     */
    @Override
    public void testCompareTeams() {
    }
}
