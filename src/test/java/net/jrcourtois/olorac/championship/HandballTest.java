/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.championship;

/**
 *
 * @author jrc
 */
public class HandballTest extends ChampionshipTest {

    public HandballTest(String testName) {
        super(testName);
        int points[] = {1,3,3};
        setPoints(points);
    }

    @Override
    protected void setUp() throws Exception {
        setChampionShip(new Handball());
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of compareTeams method, of class Handball.
     */
    @Override
    public void testCompareTeams() {
    }
}
