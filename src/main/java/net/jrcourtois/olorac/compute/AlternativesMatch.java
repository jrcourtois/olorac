/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.compute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.jrcourtois.olorac.Match;
import net.jrcourtois.olorac.Score;

/**
 *
 * @author jrc
 */
public class AlternativesMatch implements Iterable<Match> {

    /**
     * list of matches.
     */
    private final List<Match> alternates = new ArrayList<Match>();;
    /**
     * First match.
     */
    private final Match myMatch;

    /**
     * Constructor.
     *
     * @param m the match to give distinct results
     */
    public AlternativesMatch(final Match m) {
        myMatch = m;
        if (m.isPlayed()) {
            alternates.add(new Match(m));
        } else {
            // home team wins
            alternates.add(new Match(m, new Score(1, 0)));
            // away team wins
            alternates.add(new Match(m, new Score(0, 1)));
            // draw
            alternates.add(new Match(m, new Score(0, 0)));
        }
    }

    @Override
    public final Iterator<Match> iterator() {
        if (alternates != null) {
            return alternates.iterator();
        } else {
            return null;
        }
    }

    /**
     * @return a random match result.
     */
    public final Match getRandomMatch() {
        if (myMatch.isPlayed()) {
            return new Match(myMatch);
        }
        Random randomizer = new Random();
        int h = myMatch.getProbaHomeWin();
        int a = myMatch.getProbaAwayWin();
        int d = myMatch.getProbaDraw();
        int alea = randomizer.nextInt(h + a + d);
        if (alea < h) {
            return getHomeWin(myMatch);
        }
        if (alea < a + h) {
            return getAwayWin(myMatch);
        }
        return getDraw(myMatch);
    }
    /**
     * 
     * @param m
     * @return 
     */
    public Match getHomeWin(Match m) {
        Random rzer = new Random();
        int home = rzer.nextInt(4);
        int away = 0;
        if (home != 0) {
            away = rzer.nextInt(home);
        }
        return new Match(m, new Score(home+1, away));
    }
    /**
     * 
     * @param m
     * @return 
     */
    public Match getAwayWin(Match m) {
        Random rzer = new Random();
        int away = rzer.nextInt(3);
        int home = 0;
        if (away !=0 ) {
            home = rzer.nextInt(away);
        }
        return new Match(m, new Score(home, away+1));
    }
    /**
     * 
     * @param m
     * @return 
     */
    public Match getDraw(Match m) {
        Random rzer = new Random();
        int s = rzer.nextInt(2);
        return new Match(m, new Score(s, s));
    }
}
