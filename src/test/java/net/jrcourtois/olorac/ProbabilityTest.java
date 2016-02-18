/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import net.jrcourtois.olorac.Probability;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 *
 * @author jrc
 */
public class ProbabilityTest extends TestCase {

    public ProbabilityTest(String testName) {
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
     * Test of getProbability method, of class Probability.
     */
    public void testGetProbabilityDouble() {
        Probability p = new Probability();
        Assert.assertEquals((double) 1 / 3, p.getProbability(Probability.RESULT.WIN));
        Assert.assertEquals((double) 1 / 3, p.getProbability(Probability.RESULT.LOSE));
        Assert.assertEquals((double) 1 / 3, p.getProbability(Probability.RESULT.DRAW));
        p = new Probability(1, 0, 0);
        Assert.assertEquals(1.0, p.getProbability(Probability.RESULT.WIN));
        Assert.assertEquals(0.0, p.getProbability(Probability.RESULT.LOSE));
        Assert.assertEquals(0.0, p.getProbability(Probability.RESULT.DRAW));
        p = new Probability(0, 4, 6);
        Assert.assertEquals(0.0, p.getProbability(Probability.RESULT.WIN));
        Assert.assertEquals(0.6, p.getProbability(Probability.RESULT.LOSE));
        Assert.assertEquals(0.4, p.getProbability(Probability.RESULT.DRAW));

    }

    /**
     * Test of getProbability method, of class Probability.
     */
    public void testGetProbability() {
        Probability p = new Probability();
        Assert.assertEquals(1,
                p.getProbability(Probability.FIELD.AWAY, Probability.RESULT.WIN));
    }

    /**
     * Test of MostProbable.
     */
    public void testMostProbable() {
        Probability p = new Probability(1, 1, 1);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.DRAW);
        p = new Probability(2, 1, 1);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.WIN);
        p = new Probability(1, 2, 1);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.DRAW);
        p = new Probability(1, 1, 2);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.LOSE);
        p = new Probability(2, 1, 2);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.LOSE);
        p = new Probability(2, 2, 1);
        Assert.assertEquals(p.getMostProbable(), Probability.RESULT.DRAW);

    }
}
