/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author jr
 */
public class RugbyStats extends Stats {

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RugbyStats.class.getName());

    private int homeTryScored = 0;
    private int homeTryConceded = 0;
    private int homeTransformationScored = 0;
    private int homeTransformationConceded = 0;
    private int homeDropScored = 0;
    private int homeDropConceded = 0;
    private int homePenaltyScored = 0;
    private int homePenaltyConceded = 0;
    private int homeDefensiveBonus = 0;
    private int homeOffensiveBonus = 0;

    private int awayTryScored = 0;
    private int awayTryConceded = 0;
    private int awayTransformationScored = 0;
    private int awayTransformationConceded = 0;
    private int awayDropScored = 0;
    private int awayDropConceded = 0;
    private int awayPenaltyScored = 0;
    private int awayPenaltyConceded = 0;
    private int awayDefensiveBonus = 0;
    private int awayOffensiveBonus = 0;

    public RugbyStats(Team aTeam) {
        super(aTeam);
    }

    public RugbyStats(Team aTeam, List<Match> list) {
        super(aTeam, list);
    }

    /**
     * Copy constructor
     *
     * @param stats
     */
    public RugbyStats(RugbyStats stats) {
        super(stats);
        this.homeTryScored = stats.homeTryScored;
        this.homeTryConceded = stats.homeTryConceded;
        this.homeTransformationScored = stats.homeTransformationScored;
        this.homeTransformationConceded = stats.homeTransformationConceded;
        this.homeDropScored = stats.homeDropScored;
        this.homeDropConceded = stats.homeDropConceded;
        this.homePenaltyScored = stats.homePenaltyScored;
        this.homePenaltyConceded = stats.homePenaltyConceded;
        this.homeDefensiveBonus = stats.homeDefensiveBonus;
        this.homeOffensiveBonus = stats.homeOffensiveBonus;

        this.awayTryScored = stats.awayTryScored;
        this.awayTryConceded = stats.awayTryConceded;
        this.awayTransformationScored = stats.awayTransformationScored;
        this.awayTransformationConceded = stats.awayTransformationConceded;
        this.awayDropScored = stats.awayDropScored;
        this.awayDropConceded = stats.awayDropConceded;
        this.awayPenaltyScored = stats.awayPenaltyScored;
        this.awayPenaltyConceded = stats.awayPenaltyConceded;
        this.awayDefensiveBonus = stats.awayDefensiveBonus;
        this.awayOffensiveBonus = stats.awayOffensiveBonus;
    }

    @Override
    protected void computeSpecificStats(Match m, Boolean isHome) {
        try {
            RugbyScore s = (RugbyScore) m.getScore();
            if (isHome) {
                this.homeTryScored += s.homeTryScored;
                this.homeTryConceded += s.homeTryConceded;
                this.homeTransformationScored += s.homeTransformationScored;
                this.homeTransformationConceded += s.homeTransformationConceded;
                this.homeDropScored += s.homeDropScored;
                this.homeDropConceded += s.homeDropConceded;
                this.homePenaltyScored += s.homePenaltyScored;
                this.homePenaltyConceded += s.homePenaltyConceded;
                if( s.getHomeBonusDefensif()){
                    this.homeDefensiveBonus++;
                }
                if (s.getHomeBonusOffensif()) {
                    this.homeOffensiveBonus++;
                }
            } else {
                this.awayTryScored += s.homeTryConceded;
                this.awayTryConceded = s.homeTryScored;
                this.awayTransformationScored = s.homeTransformationConceded;
                this.awayTransformationConceded = s.homeTransformationScored;
                this.awayDropScored = s.homeDropConceded;
                this.awayDropConceded = s.homeDropScored;
                this.awayPenaltyScored = s.homePenaltyConceded;
                this.awayPenaltyConceded = s.homePenaltyScored;
                if( s.getAwayBonusDefensif()){
                    this.awayDefensiveBonus++;
                }
                if (s.getAwayBonusOffensif()) {
                    this.awayOffensiveBonus++;
                }
            }
        } catch (ClassCastException e) {
            LOGGER.severe("Trying to compute stats on a non rugby score");
        }
    }

    public int getBonuses() {
        return defBonuses() + offBonuses();
    }

    public int defBonuses() {
        return homeDefensiveBonus + awayDefensiveBonus;
    }

    public int offBonuses() {
        return homeOffensiveBonus + awayOffensiveBonus;
    }
}
