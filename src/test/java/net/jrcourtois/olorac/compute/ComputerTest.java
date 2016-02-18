package net.jrcourtois.olorac.compute;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.calendar.Ranking;
import net.jrcourtois.olorac.Team;
import net.jrcourtois.olorac.championship.Handball;
import net.jrcourtois.olorac.exceptions.CalendarException;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.parser.MaxiFootCalendar;

/**
 * Unit test for simple Computer.
 */
public class ComputerTest
        extends TestCase {

    /**
     * The number of times to test.
     */
    private static final int NBMEASURE = 200;
    /**
     * the computer.
     */
    private final Computer app;

    /**
     * Create the test case.
     *
     * @param testName name of the test case
     */
    public ComputerTest(final String testName) {
        super(testName);
        app = new Computer();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ComputerTest.class);
    }

    /**
     * failing tests...
     */
    public final void NOtestRecursiveWithCalendarNull() {
        RankingAggregator classements = new RankingAggregatorList();
        try {
            app.computePossibleResults(null, classements, 2);
            assertEquals(0, classements.size());
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * failing tests...
     */
    public final void NOtestRecursiveWithAgregatorNull() {
        RankingAggregator classements = new RankingAggregatorList();
        try {
            app.computePossibleResults(new Calendar(null), null, 2);
            assertEquals(0, classements.size());
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * failing tests...
     */
    public final void NOtestRecursiveWithEmptyCalendar() {
        RankingAggregator classements = new RankingAggregatorList();
        try {
            app.computePossibleResults(new Calendar(null), classements, 2);
            assertEquals(0, classements.size());
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
    }

    /**
     * to make a complicated test.
     */
    public final void NOtestRecursiveAlternative() {
        Handball h = new Handball();
        Calendar cal = new Calendar(h);
        Team fr = new Team("France", h);
        Team es = new Team("Espagne", h);
        Team cr = new Team("Croatie", h);
        Team fi = new Team("Finlande", h);
        try {
            Match m1 = new Match(fr, es);
            Match m2 = new Match(cr, fi);
            m1.setProbas(5, 3, 2);
            m2.setProbas(2, 15, 3);
            cal.addMatch(m1);
            cal.addMatch(m2);
        } catch (CalendarException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        RankingAggregatorList classements = new RankingAggregatorList();
        try {
            app.computePossibleResults(cal, classements, 1);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }

        final int neuf = 9;
        assertEquals(neuf, classements.size());

        double sum = 0.0;
        for (Ranking r : classements) {
            sum += r.getProba();
        }
        assertEquals(1.0, sum);
    }

    /**
     * to make a complicated test.
     */
    public final void testRecursiveNumber() {
        Handball h = new Handball();
        Calendar cal = new Calendar(h);
        Team fr = new Team("France", h);
        Team es = new Team("Espagne", h);
        Team cr = new Team("Croatie", h);
        Team fi = new Team("Finlande", h);
        try {
            Match m1 = new Match(fr, es);
            Match m2 = new Match(cr, fi);
            Match m3 = new Match(cr, fr);
            Match m4 = new Match(es, fi);
            Match m5 = new Match(fr, fi);
            Match m6 = new Match(cr, es);
            cal.addMatch(m1);
            cal.addMatch(m2);
            cal.addMatch(m3);
            cal.addMatch(m4);
            cal.addMatch(m5);
            cal.addMatch(m6);
        } catch (CalendarException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        TeamQualifiedAggregator cNumber = new TeamQualifiedAggregator(cal.getTeams(), 1);
        TeamQualifiedAggregator cWhole = new TeamQualifiedAggregator(cal.getTeams(), 3);
        try {
            app.computeNumberOfAlternatives(cal, cNumber, NBMEASURE);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        try {
            app.computePossibleResults(cal, cWhole, 1);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }
        for (Team t : cNumber.keySet()) {
            System.out.println(" * " + t.getName()
                    + " First: " + cNumber.getProba(t)
                    + " - " + cWhole.getProba(t));
        }
    }

    /**
     * test sur la ligue 1 en ligne.
     */
    public final void NOtestLigue1() {
        PossibleRankingsAggregator classements = null;
        try {
//            Calendar cal = new MaxiFootCalendar("calendrier-bundesliga.htm");
            MaxiFootCalendar parser = new MaxiFootCalendar("calendrier-ligue1.htm");
            Calendar cal = parser.getCalendar();
//            cal.setProbas("Auxerre", 1, 0, 0, 0, 0, 1);
//            cal.makeProbas(new FromStatsProno());
            classements = new PossibleRankingsAggregator(cal.getTeams());
            app.computeNumberOfAlternatives(cal, classements, NBMEASURE);

        } catch (MatchException ex) {
            Assert.fail("Should not raise an exception: " + ex);
        }

        System.out.println(" ********* LIGUE 1 **************");
        if (classements != null) {
            for (Team t : classements.keySet()) {
                System.out.println(" * " + t.getName()
                        + " Qualified: " + classements.toString(t));
            }
        }
    }

    /**
     * testEuroHand.
     */
    public final void NOtestEuroHand() {
        // Initialisation
        Handball h = new Handball();
        Team fr = new Team("France", h);
        Team es = new Team("Espagne", h);
        Team cr = new Team("Croatie", h);
        Team hon = new Team("Hongrie", h);
        Team slo = new Team("Slovénie", h);
        Team isl = new Team("Islande", h);
        Calendar cal = new Calendar(h);
        try {
            Match frSlo = new Match(fr, slo);
            frSlo.setProbas(1, 0, 0);
            cal.addMatch(frSlo);
            cal.addMatch(new Match(hon, isl));
            cal.addMatch(new Match(es, cr));
            cal.addMatch(new Match(es, isl));
            cal.addMatch(new Match(slo, hon));
            Match frCr = new Match(fr, cr);
            frCr.setProbas(1, 2, 0);
            cal.addMatch(frCr);
            cal.addMatch(new Match(slo, es));
            cal.addMatch(new Match(cr, hon));
            Match islFr = new Match(isl, fr);
            islFr.setProbas(0, 0, 1);
            cal.addMatch(islFr);
        } catch (CalendarException ex) {
            fail("|" + ex + "| should not have been raised.");
        }

        TeamQualifiedAggregator mapQualified = new TeamQualifiedAggregator(cal.getTeams(), 2);
        try {
            app.computePossibleResults(cal, mapQualified, 1);
        } catch (MatchException ex) {

            fail("|" + ex + "| should not have been raised.");
        }

        System.out.println("*** Before first match.");
        for (Team t : mapQualified.keySet()) {
            System.out.println(" * " + t.getName()
                    + " Qualified: " + mapQualified.getProba(t));
        }
        // Premier match
        /*
         * fr.addPoints(2); isl.addPoints(2); es.addPoints(2);
         *
         */
        mapQualified = new TeamQualifiedAggregator(cal.getTeams(), 2);
        try {
            app.computePossibleResults(cal, mapQualified, 4);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }

        System.out.println("*** Before second match.");
        for (Team t : mapQualified.keySet()) {
            System.out.println(" * " + t.getName()
                    + " Qualified: " + mapQualified.getProba(t));

        }
        // Deuxieme match
        /*
         * slo.addPoints(2); cr.addPoints(2); es.addPoints(2);
         *
         */
        mapQualified = new TeamQualifiedAggregator(cal.getTeams(), 2);
        try {
            app.computePossibleResults(cal, mapQualified, 7);
        } catch (MatchException ex) {
            fail("|" + ex + "| should not have been raised.");
        }

        System.out.println("*** Before third match.");
        for (Team t : mapQualified.keySet()) {
            System.out.println(" * " + t.getName()
                    + " Qualified: " + mapQualified.getProba(t));
        }
    }
}
