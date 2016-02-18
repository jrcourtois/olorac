/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import junit.framework.TestCase;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.championship.Ligue1;

/**
 *
 * @author jrc
 */
public class FromXLMLTest extends TestCase {
    public void testReadFile() {
        FromXML f = new FromXML("src/test/resources/Championship.xml");
        assertNotNull(f);
        Calendar c = f.getCalendar();
        assertNotNull(c);
        assertEquals(6, c.getNbMatch());
        assertEquals(c.getChampionship().getClass(), Ligue1.class);
    }
}
