/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.championship.Top14;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;

/**
 * Method to parse the championship from the website maxifoot
 * @author jrc
 */
public class RugbyRama implements ChampionshipParser {

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MaxiFootCalendar.class.getName());
    /**
     * The calendar.
     */
    private final Calendar myCalendar;
    /**
     * The host url.
     */
    private static final String HOSTURL = "http://www.rugbyrama.fr/";

    /**
     * Constructor.
     */
    public RugbyRama() {
        myCalendar = new Calendar(new Top14());
    }

    /**
     * Constructor.
     *
     * @param page the page url of the calendar to retrieve
     */
    public RugbyRama(final String page) {
        myCalendar = new Calendar(new Top14());
        getRugbyCalendar(HOSTURL + page);
    }

    /**
     *
     * @param urlString the address of the file to parse
     */
    public final void getRugbyCalendar(final String urlString) {
        try {
            URL url;
            URLConnection urlConn;
            InputStreamReader is;
            BufferedReader dis;

            url = new URL(urlString);

            urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            is = new InputStreamReader(urlConn.getInputStream());
            dis = new BufferedReader(is);
            String s;
            while ((s = dis.readLine()) != null) {
                if (s.matches(".*class=cl.*")) {
                    try {
                        myCalendar.addMatch(parseLine(s));
                    } catch (MatchException ex) {
                        LOGGER.log(Level.WARNING, "Unable to create a match.", ex);
                    } catch (CalendarException ex) {
                        LOGGER.log(Level.WARNING, "Unable to add a match.", ex);
                    }
                }
            }
            dis.close();
        } catch (MalformedURLException mue) {
            LOGGER.log(Level.SEVERE, "The url is malformed [{0}]", urlString);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "An exception occured during reading {0}", ioe);
        }
    }

    /**
     * @param line the line to parse
     * @throws MatchException if a match can not be created
     * @throws CalendarException if the calendar can not be created
     * @return a match
     */
    protected final Match parseLine(final String line)
            throws MatchException, CalendarException {
        if (line == null) {
            return null;
        }
        String[] strings = line.split("<.*?>");
        Team t1 = null;
        Team t2 = null;
        Team t;
        String[] score = null;
        for (String teamName : strings) {
            if (!teamName.isEmpty()) {
                if (teamName.matches("\\d+-\\d+")) {
                    score = teamName.split("-", 2);
                    continue;
                }
                t = myCalendar.getTeam(teamName);
                if (t == null && t2 == null) {
                    t = new Team(teamName, new Top14());
                }
                if (t1 == null) {
                    t1 = t;
                } else if (t2 == null) {
                    t2 = t;
                }
            }

        }
        if (t1 == null || t2 == null) {
            return null;
        }
        Match m = new Match(t1, t2);
        if (score != null) {
            m.setScore(Integer.parseInt(score[0]), Integer.parseInt(score[1]));
        }
        return m;
    }

    @Override
    public Calendar getCalendar() {
        return myCalendar;
    }
}
