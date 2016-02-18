/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import net.jrcourtois.olorac.calendar.Calendar;

/**
 *
 * @author jrc
 */
public interface ChampionshipParser {
    /**
     * @return the calendar build by the parser
     */
    Calendar getCalendar();
}
