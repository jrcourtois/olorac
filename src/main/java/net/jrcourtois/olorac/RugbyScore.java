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
    public final int homeTryScored;
    public final int homeTransformationScored;
    public final int homeDropScored;
    public final int homePenaltyScored;
    public final int homeTryConceded;
    public final int homeTransformationConceded;
    public final int homeDropConceded;
    public final int homePenaltyConceded;
    
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
