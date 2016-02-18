/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

/**
 *
 * @author jrc
 */
public class Probability implements Cloneable {

    /**
     * the probability for the away team to win.
     */
    private int probaAwayWin = 1;
    private int probaHomeWin = 1;
    private int probaDraw = 1;

    public enum FIELD {

        HOME,
        AWAY,
        NEUTRAL
    };

    public enum RESULT {

        WIN,
        DRAW,
        LOSE
    }

    /**
     * Default Constructor.
     */
    public Probability() {
    }

    /**
     * Constructor.
     *
     * @param h the probability for the home team to win
     * @param d the probability for the draw
     * @param a the probability for the away team to win
     */
    public Probability(int h, int d, int a) {
        if (h < 0 || a < 0 || d < 0) {
            return;
        }
        if (0 == h + d + a) {
            return;
        }
        this.probaAwayWin = a;
        this.probaHomeWin = h;
        this.probaDraw = d;
    }

    /**
     * Copy Constructor.
     *
     * @param probability the object to copy
     */
    Probability(Probability probability) {
        this.probaAwayWin = probability.probaAwayWin;
        this.probaDraw = probability.probaDraw;
        this.probaHomeWin = probability.probaHomeWin;
    }

    /**
     * 
     * @param where
     * @param res
     * @return 
     */
    int getProbability(FIELD where, RESULT res) {
        if (where == FIELD.AWAY) {
            switch (res) {
                case LOSE:
                    return probaHomeWin;
                case WIN:
                    return probaAwayWin;
            }
        }
        switch (res) {
            case LOSE:
                return probaAwayWin;
            case WIN:
                return probaHomeWin;
        }
        return probaDraw;
    }

    /**
     * 
     * @param res
     * @return 
     */
    public final double getProbability(RESULT res) {
        switch (res) {
            case DRAW:
                return ((double) probaDraw
                        / (double) (probaDraw + probaAwayWin + probaHomeWin));
            case WIN:
                return ((double) probaHomeWin
                        / (double) (probaDraw + probaAwayWin + probaHomeWin));
            case LOSE:
                return ((double) probaAwayWin
                        / (double) (probaDraw + probaAwayWin + probaHomeWin));
            default:
                return 0.0;
        }
    }
    /**
     * @return the most probable result
     */
    public final RESULT getMostProbable() {
        if (probaAwayWin > probaDraw) {
            if (probaHomeWin > probaAwayWin) {
                return RESULT.WIN;
            } else {
                return RESULT.LOSE;
            }
        }
        if (probaHomeWin > probaDraw) {
            return RESULT.WIN;
        }
        return RESULT.DRAW;
    }
}