/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

/**
 *
 * @author jr
 */
public class RugbyScore extends Score{
    public final Integer homeTryScored;
    public final Integer homeTransformationScored;
    public final Integer homeDropScored;
    public final Integer homePenaltyScored;
    public final Integer homeTryConceded;
    public final Integer homeTransformationConceded;
    public final Integer homeDropConceded;
    public final Integer homePenaltyConceded;
    
    public static final int DIFF_TRY = 3;
    public static final int DIFF_POINTS = 4;

    /**
     * 
     * @param homeTry
     * @param homeTrans
     * @param homeDrop
     * @param homePena
     * @param awayTry
     * @param awayTrans
     * @param awayDrop
     * @param awayPena 
     */
    public RugbyScore(int homeTry, int homeTrans, int homeDrop, int homePena, int awayTry, int awayTrans, int awayDrop, int awayPena) {
        super(homeTry*5+homeTrans*2+homeDrop*3+homePena*3, awayTry*5+awayTrans*2+awayDrop*3+awayPena*3);
        homeTryScored = homeTry;
        homeTransformationScored = homeTrans;
        homeDropScored = homeDrop;
        homePenaltyScored = homePena;
        homeTryConceded = awayTry;
        homeTransformationConceded = awayTrans;
        homeDropConceded = awayDrop;
        homePenaltyConceded = awayPena;
    }
    
    /**
     * 
     * @param homeScore
     * @param homeTry
     * @param awayScore
     * @param awayTry
     */
    public RugbyScore(Integer homeScore, Integer homeTry, Integer awayScore, Integer awayTry) {
        super(homeScore, awayScore);
        homeTryScored = homeTry;
        homeTransformationScored = null;
        homeDropScored = null;
        homePenaltyScored = null;
        homeTryConceded = awayTry;
        homeTransformationConceded = null;
        homeDropConceded = null;
        homePenaltyConceded = null;
    }

    public Boolean getHomeBonusDefensif() {
        if (homeTeamWins() || drawMatch()) {
            return false;
        }
        return ( getAwayScore() - getHomeScore() <= DIFF_POINTS);
    }
    
    public Boolean getAwayBonusDefensif() {
        if (homeTeamWins() ) {
            return ( getHomeScore() - getAwayScore() <= DIFF_POINTS);
        }
        return false;
    }
    public Boolean getHomeBonusOffensif() {
        return ((homeTryScored-homeTryConceded) >= DIFF_TRY);
    }
    public Boolean getAwayBonusOffensif() {
        return ((homeTryConceded-homeTryScored) >= DIFF_TRY);
    }
}
