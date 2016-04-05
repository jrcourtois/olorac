/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.RugbyScore;
import net.jrcourtois.olorac.Score;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.championship.Championship;
import net.jrcourtois.olorac.championship.Ligue1;
import net.jrcourtois.olorac.championship.Top14;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jrc
 */
public class JsonParser implements ChampionshipParser {

    /**
     *
     */
    private final File myFile;

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class.getName());

    private Calendar myCalendar;
    private final Championship myChampionship;

    /**
     * Constructor
     *
     * @param filename
     * @param c
     */
    public JsonParser(String filename, Championship c) {
        myFile = new File(filename);
        myCalendar = new Calendar(c);
        myChampionship = c;
        String t_text;
        JSONArray obj;
        try {
            t_text = new String(Files.readAllBytes(myFile.toPath()), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            t_text = "";
        }
        try {
            obj = new JSONArray(t_text);
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problem parsing JSON file: {0}", myFile.getName());
            LOGGER.log(Level.FINE, "JSONException: {0}", e);
            obj = null;
        }
        if (obj == null) {
            LOGGER.severe("Unable to init calendar");
            return;
        }

        buildCalendar(obj);
    }

    /**
     *
     * @param o
     * @return
     */
    private void buildCalendar(JSONArray a) {
        for (int i = 0; i < a.length(); i++) {
            try {
                myCalendar.addMatch(parseLine(a.getJSONObject(i)));
            } catch (MatchException ex) {
                LOGGER.log(Level.WARNING, "Unable to create a match.", ex);
            } catch (CalendarException ex) {
                LOGGER.log(Level.WARNING, "Unable to add a match.", ex);
            }
        }
    }

    /**
     * @param line the line to parse
     * @throws MatchException if a match can not be created
     * @throws CalendarException if the calendar can not be created
     * @return a match
     */
    protected final Match parseLine(final JSONObject line)
            throws MatchException, CalendarException {
        if (line == null) {
            return null;
        }
        try {
            Team t1 = myCalendar.getTeam(line.getString("home"));
            if (t1 == null) {
                t1 = new Team(line.getString("home"), myChampionship);
            }
            Team t2 = myCalendar.getTeam(line.getString("away"));
            if (t2 == null) {
                t2 = new Team(line.getString("away"), myChampionship);
            }
            Score score = null;
            if (line.has("hScore")) {

                score = new RugbyScore(getInt(line, "hScore"), getInt(line, "htry"), getInt(line, "aScore"), getInt(line, "atry"));
            }
            Match m = new Match(t1, t2);
            if (score != null) {
                m.setScore(score);
            }
            return m;
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Error in parsing line: {0}", line.toString());
            return null;
        }
    }

    private Integer getInt(JSONObject o, String key) {
        if (o.has(key)) {
            return Integer.parseInt(o.getString(key));
        } else {
            return null;
        }

    }

    public Calendar getCalendar() {
        return myCalendar;
    }

}
