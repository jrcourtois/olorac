/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import java.io.File;
import net.jrcourtois.olorac.calendar.Calendar;
import org.json.JSONObject;

/**
 *
 * @author jrc
 */
public class JsonParser implements ChampionshipParser{
    
    File myFile;
    
    
    /**
     * Constructor
     * 
     * @param filename 
     */
    public JsonParser(String filename) {
        myFile = new File(filename);
    }

    public Calendar getCalendar() {
        return null;
    }
    
}
