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

    private int homeTryScored;
    private int homeTryConceded;
    private int homeTransformationScored;
    private int homeTransformationConceded;
    private int homeDropScored;
    private int homeDropConceded;
    private int homePenaltyScored;
    private int homePenaltyConceded;
    private int homeDefensiveBonus;
    private int homeOffensiveBonus;

    private int awayTryScored;
    private int awayTryConceded;
    private int awayTransformationScored;
    private int awayTransformationConceded;
    private int awayDropScored;
    private int awayDropConceded;
    private int awayPenaltyScored;
    private int awayPenaltyConceded;
    private int awayDefensiveBonus;
    private int awayOffensiveBonus;

    public RugbyStats(Team aTeam) {
        this(aTeam, aTeam.getPlayedMatches());
    }

    public RugbyStats(Team aTeam, Iterable<Match> list) {
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
                if (s.getHomeBonusDefensif()) {
                    System.out.println("BDef: "+ m + s);
                    this.homeDefensiveBonus++;
                }
                if (s.getHomeBonusOffensif()) {
                    System.out.println("BOff: "+ m + s);
                    this.homeOffensiveBonus++;
                }
            } else {
                this.awayTryScored += s.homeTryConceded;
                this.awayTryConceded += s.homeTryScored;
                this.awayTransformationScored += s.homeTransformationConceded;
                this.awayTransformationConceded += s.homeTransformationScored;
                this.awayDropScored += s.homeDropConceded;
                this.awayDropConceded += s.homeDropScored;
                this.awayPenaltyScored += s.homePenaltyConceded;
                this.awayPenaltyConceded += s.homePenaltyScored;
                if (s.getAwayBonusDefensif()) {
                    System.out.println("BDef: "+ m + s);
                    this.awayDefensiveBonus++;
                }
                if (s.getAwayBonusOffensif()) {
                    System.out.println("BOff: "+ m + s);
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

    @Override
    public String toString() {
        return "[Wins: " + this.getWins() + ", BO: " + this.offBonuses() + ", BD: " + this.defBonuses() + "]";
    }
}
