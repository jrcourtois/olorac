/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import java.util.Set;
import junit.framework.TestCase;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;

/**
 *
 * @author jrc
 */
public class MaxiFootCalendarTest extends TestCase {

    /**
     * @param testName the name of the test
     */
    public MaxiFootCalendarTest(final String testName) {
        super(testName);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getMaxiFootCalendar method, of class MaxiFootCalendar.
     */
    public final void NOtestGetMaxiFootCalendar() {
        final int nbTeam = 20;
        System.out.println("getCalendar");
        MaxiFootCalendar parser = new MaxiFootCalendar("calendrier-ligue1.php");
        Calendar cal = parser.getCalendar();
        Set<Team> result = cal.getTeams();
        assertEquals(nbTeam, result.size());
        assertEquals((nbTeam * (nbTeam - 1)), cal.getNbMatch());
    }

    /**
     * Test of parseLine method, of class MaxiFootCalendar.
     */
    public final void testParseLine() {
        try {
            System.out.println("parseLine");
            MaxiFootCalendar instance = new MaxiFootCalendar();
            assertNull(instance.parseLine(""));
            assertNull(instance.parseLine(null));
            assertNull(instance.parseLine("nimportequoi"));
            assertNull(instance.parseLine("g<>"));
            assertNull(instance.parseLine("g<>5-4"));
            Match m = instance.parseLine("g<>d<>5-4");
            assertNotNull(m);
            assertTrue(m.isPlayed());
            m = instance.parseLine("g<>d");
            assertFalse(m.isPlayed());
            m = instance.parseLine("g<>d<>5");
            assertFalse(m.isPlayed());
            m = instance.parseLine("g<>5-2<>d");
            assertTrue(m.isPlayed());
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        } catch (CalendarException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }
}
