package net.jrcourtois.olorac.parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Score;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.championship.ChampionshipFactory;
import net.jrcourtois.olorac.exceptions.CalendarException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FromXML extends DefaultHandler implements ChampionshipParser {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FromXML.class.getName());
    /**
     * the calendar which is build.
     */
    private Calendar myCalendar;
    /**
     * parsing variables.
     */
    private String currentElement;
    private Team tHomeTeam;
    private Team tAwayTeam;
    private Score tScore;
    private Date tDate;

    /**
     * Constructor.
     *
     * @param filename
     */
    public FromXML(String filename) {
        this.parseFile(filename);

    }

    /**
     * @return the calendar
     */
    public Calendar getCalendar() {
        return myCalendar;
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes)
            throws SAXException {
        currentElement = qName;
        if (qName.equalsIgnoreCase("championship")) {
            String type = attributes.getValue("type");
            myCalendar = new Calendar( ChampionshipFactory.getChampionship(type));
        }
        if (qName.equalsIgnoreCase("match")) {
            String date = attributes.getValue("day");
            if (date != null) {
                SimpleDateFormat format = new SimpleDateFormat("d/m/y");
                try {
                    tDate = format.parse(date);
                } catch (ParseException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            } else {
                tDate = null;
            }
        }
    }

    @Override
    public void endElement(
            String uri, String localName,
            String qName) throws SAXException {
        currentElement = "";
        if (qName.equalsIgnoreCase("match")) {
            Match m = new Match(tHomeTeam, tAwayTeam);
            if (tScore != null) {
                m.setScore(tScore);
            }
            if (tDate != null) {
                m.setDate(tDate);
            }
            try {
                myCalendar.addMatch(m);
            } catch (CalendarException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            tHomeTeam = null;
            tAwayTeam = null;
            tScore = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        if (currentElement.equalsIgnoreCase("homeTeam")) {
            tHomeTeam = getXMLTeam(new String(ch, start, length));
        }
        if (currentElement.equalsIgnoreCase("awayTeam")) {
            tAwayTeam = getXMLTeam(new String(ch, start, length));
        }
        if (currentElement.equalsIgnoreCase("score")) {
            tScore = getXMLScore(new String(ch, start, length));
        }
    }

    /**
     * Method to parse the file.
     *
     * @param filename the path to the file
     * @return false if an error occur during parsing file
     */
    public final boolean parseFile(String filename) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(filename, this);
            return true;

        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, null, e);
            myCalendar = null;
            return false;
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, null, e);
            myCalendar = null;
            return false;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, null, e);
            myCalendar = null;
            return false;
        }

    }

    /**
     * @param teamName the name of the team
     * @return a team given a team name
     */
    private Team getXMLTeam(String teamName) {
        Team t = null;
        if (!teamName.isEmpty()) {
            t = myCalendar.getTeam(teamName);
            if (t == null) {
                t = new Team(teamName, myCalendar.getChampionship());
            }
        }
        return t;
    }

    /**
     * @param string the score in the form 0-0
     * @return the score
     */
    private Score getXMLScore(String string) {
        if (!string.isEmpty()) {
            String[] score;
            score = string.split("-", 2);
            return new Score(Integer.parseInt(score[0]), Integer.parseInt(score[1]));
        }
        return null;
    }
}