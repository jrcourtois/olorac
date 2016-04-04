/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.championship.Top14;

/**
 *
 * @author jr
 */
public class JsonParserTest extends TestCase {
    
    public JsonParserTest(String testName) {
        super(testName);
    }

    public void testReadFile() {
        JsonParser f = new JsonParser("src/test/resources/top14.json", new Top14());
        assertNotNull(f);
        Calendar c = f.getCalendar();
        assertNotNull(c);
        assertEquals(c.getChampionship().getClass(), Top14.class);
        assertEquals(182, c.getNbMatch());
        assertEquals(8,c.getTeams());
        
    }
    
}
