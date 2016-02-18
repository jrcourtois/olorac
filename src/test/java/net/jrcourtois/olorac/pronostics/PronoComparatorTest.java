/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.pronostics;

import java.util.ArrayList;
import junit.framework.Assert;
import junit.framework.TestCase;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.parser.MaxiFootCalendar;

/**
 *
 * @author jrc
 */
public class PronoComparatorTest extends TestCase {

    public PronoComparatorTest(String testName) {
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
     * Test of compareWithCalendar method, of class PronoComparator.
     * @throws java.lang.Exception
     */
    public void testCompareWithCalendar() throws Exception {
        MaxiFootCalendar parser = new MaxiFootCalendar("calendrier-ligue1.php");
        Calendar c = parser.getCalendar();
        ArrayList<PronoMaker> pc = new ArrayList<PronoMaker>();
        pc.add(new FromStatsProno());
        PronoComparator test = new PronoComparator(pc);
        Assert.assertTrue(test.compareWithCalendar(c) >= 0);
    }
}
