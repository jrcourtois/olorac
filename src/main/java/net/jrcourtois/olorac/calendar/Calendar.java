/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.calendar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.championship.Championship;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;
import net.jrcourtois.olorac.pronostics.PronoMaker;

/**
 *
 * @author jrc
 */
public class Calendar {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Calendar.class.getName());
    /**
     * the list of matches played during the tournament.
     */
    private final List<Match> matches;
    /**
     * the list of teams participating to this championship.
     */
    private final Set<Team> teamsPlaying;
    private final Set<Team> teamsPlayed;
    private final Championship myChampionship;

    /**
     * Constructor.
     *
     * @param c the championship the calendar is associated with
     */
    public Calendar(Championship c) {
        this.matches = new ArrayList<Match>();
        this.teamsPlaying = new HashSet<Team>();
        this.teamsPlayed = new HashSet<Team>();

        myChampionship = c;
    }

    /**
     * @param d the date of the match in the calendar
     * @return the match of the date
     */
    public final Match getMatch(final int d) {
        if (d <= 0 || d > matches.size()) {
            return null;
        }
        return matches.get(d - 1);
    }

    /**
     * @param teamName the name of the team to get
     * @return null if the team is not present, the team otherwise
     */
    public final Team getTeam(final String teamName) {
        for (Team t : teamsPlaying) {
            if (t.getName().equalsIgnoreCase(teamName)) {
                return t;
            }
        }
        return null;
    }

    /**
     * @param team the team to retrieve
     * @return null if the team is not present, the team otherwise
     */
    public final Team getTeamPlayed(final Team team) {
        for (Team t : teamsPlayed) {
            if (t.getName().equalsIgnoreCase(team.getName())) {
                return t;
            }
        }
        return null;
    }

    /**
     * To add a match to the calendar.
     *
     * @param match the match to add
     * @throws CalendarException if match is null
     */
    public final void addMatch(final Match match) throws CalendarException {
        if (match == null) {
            throw new CalendarException("Null match has been passed");
        }
        if (matches.add(match)) {
            saveMatchPlayed(match);
        } else {
            throw new CalendarException(
                    "The match (" + match + ") has not been added.");
        }
    }
    
    /**
     * 
     * @param match 
     */
    private void saveMatchPlayed(Match match) {
        Team homeTeam = match.getHomeTeam();
            Team awayTeam = match.getAwayTeam();
            if (!teamsPlaying.contains(homeTeam)) {
                teamsPlaying.add(homeTeam);
                teamsPlayed.add(new Team(homeTeam));
            }
            if (!teamsPlaying.contains(awayTeam)) {
                teamsPlaying.add(awayTeam);
                teamsPlayed.add(new Team(awayTeam));
            }
            if (match.isPlayed()) {
                try {
                    getTeamPlayed(homeTeam).addPlayedMatch(match);
                    getTeamPlayed(awayTeam).addPlayedMatch(match);
                } catch (MatchException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }

    }
    /**
     * @return the list of teams
     */
    public final Set<Team> getTeams() {
        return teamsPlaying;
    }

    /**
     * @return the number of match in the calendar
     */
    public final int getNbMatch() {
        if (matches != null) {
            return matches.size();
        }
        return 0;
    }

    /**
     * @param day the day of calendar
     * @return the list of the match of the day, null if there is no match
     * during this day
     */
    public final List<Match> getMatchesOfTheDay(final int day) {
        int indice = (day - 1) * getNbMatchesPerDay();
        if (indice > getNbMatch() || indice + getNbMatchesPerDay() > getNbMatch()) {
            return null;
        }
        return matches.subList(indice, indice + getNbMatchesPerDay());
    }

    /**
     * @param a the first team to check
     * @param b the second team
     * @return all the match in calendar when the two teams played
     */
    public final List<Match> getMatchesFromTeam(final Team a, final Team b) {
        List<Match> returnList = new ArrayList<Match>();
        for (Match m : matches) {
            if (m.getHomeTeam() == a && m.getAwayTeam() == b) {
                returnList.add(m);
            }
            if (m.getAwayTeam() == a && m.getHomeTeam() == b) {
                returnList.add(m);
            }
        }
        return returnList;
    }

    /**
     * @return the number of match per day
     */
    public final int getNbMatchesPerDay() {
        return teamsPlaying.size() / 2;
    }

    /**
     * @param teamName the team wins all his matches
     * @param hWin proba to a win at home
     * @param hDraw proba to a draw at home
     * @param hLoose proba to a defeate at home
     * @param aWin proba for a visitor to win
     * @param aDraw proba for a visitor to draw
     * @param aLoose  proba for a visitor to lose
     */
    public void setProbas(String teamName,
            int hWin, int hDraw, int hLoose,
            int aWin, int aDraw, int aLoose) {
        Team t = getTeam(teamName);
        if (t == null) {
            return;
        }
        for (Match m : matches) {
            try {
                if (m.isHomeTeam(t)) {
                    m.setProbas(hWin, hDraw, hLoose);
                } else {
                    m.setProbas(aWin, aDraw, aLoose);
                }
            } catch (TeamNotInMatch ex) {
                LOGGER.log(Level.FINE, "Team not in match !");
            }
        }
    }

    /**
     * generate pronos for each match given a prono maker.
     *
     * @param pMaker the prono maker that generates the pronos
     */
    public final void makeProbas(final PronoMaker pMaker) {
        for (Match m : matches) {
            m.setProba(pMaker.makeProno(m));
        }
    }

    /**
     * @return the championship of the calendar
     */
    public Championship getChampionship() {
        return myChampionship;
    }

    /**
     * @return the ranking of a calendar.
     */
    public Ranking getRanking() {
        return getRanking(1.0);
    }

    /**
     * @param proba
     * @return the ranking of a calendar.
     */
    public Ranking getRanking(double proba) {
        this.teamsPlayed.clear();
        this.teamsPlaying.clear();
        try {
            for (Match m : matches) {
                saveMatchPlayed(m);
            }
        } catch (Exception e) {
            LOGGER.severe("Exception catched");
            return null;
        }
        Ranking r = new Ranking(teamsPlayed, proba);
        return myChampionship.checkRanking(r);
    }

    /**
     * @return the list of matches of the calendar
     */
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatch(int day, Match match) {
        matches.set(day - 1, match);
    }
}
