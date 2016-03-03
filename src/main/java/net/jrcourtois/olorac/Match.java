package net.jrcourtois.olorac;

import java.util.Date;
import net.jrcourtois.olorac.exceptions.MatchException;
import net.jrcourtois.olorac.exceptions.MatchNotPlayed;
import net.jrcourtois.olorac.exceptions.TeamNotInMatch;

/**
 *
 * @author jrc
 */
public class Match {

    /**
     * the home team.
     */
    private Team homeTeam = null;
    /**
     * the away team.
     */
    private Team awayTeam = null;
    /**
     * the score of the meeting.
     */
    private Score score = null;
    /**
     * the probability for the home team to win.
     */
    private Probability probability;
    /**
     * the date of the match
     */
    private Date date;

    /**
     * Constructor.
     *
     * @param h the home team
     * @param a the away team
     */
    public Match(final Team h, final Team a) {
        homeTeam = h;
        awayTeam = a;
        probability = new Probability();
    }

    /**
     * Copy constructor.
     *
     * @param m the match to copy
     */
    public Match(final Match m) {
        copyMembers(m);
    }

    /**
     * Copy constructor.
     *
     * @param m the match to copy
     * @param s the score
     */
    public Match(final Match m, Score s) {
        copyMembers(m);
        score = s;
    }

    /**
     * @return whether the match has been played or not
     */
    public final boolean isPlayed() {
        return score != null;
    }

    /**
     * @return the probability of away team to win.
     */
    public final int getProbaAwayWin() {
        if (probability == null) {
            return 1;
        }
        return probability.getProbability(
                Probability.FIELD.AWAY,
                Probability.RESULT.WIN);
    }

    /**
     * @return the probability of a draw.
     */
    public final int getProbaDraw() {
        if (probability == null) {
            return 1;
        }
        return probability.getProbability(
                Probability.FIELD.HOME,
                Probability.RESULT.DRAW);

    }

    /**
     * @return the probability for the home team to win.
     */
    public final int getProbaHomeWin() {
        if (probability == null) {
            return 1;
        }
        return probability.getProbability(
                Probability.FIELD.HOME,
                Probability.RESULT.WIN);

    }

    /**
     * @return the home team
     */
    public final Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * @return the away team
     */
    public final Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * to set the probability in a correct way. there can not be negative
     * probability, and one must at least be 1.
     *
     * @param h the probability for the home team to win
     * @param d the probability for the draw
     * @param a the probability for the away team to win
     */
    public final void setProbas(final int h, final int d, final int a) {
        probability = new Probability(h, d, a);
    }

    /**
     * @param h home score
     * @param a away score
     */
    public final void setScore(final int h, final int a) {
        score = new Score(h, a);
    }

    /**
     * @param s the score to set
     */
    public final void setScore(final Score s) {
        score = s;
    }

    /**
     * @return the probabability of the
     * @throws MatchException
     */
    public final double getProba() throws MatchException {
        Team t = getHomeTeam();
        if (this.checkGamePlayed(t)) {
            if (teamDraw(t)) {
                return probability.getProbability(Probability.RESULT.DRAW);
            }
            if (teamWins(t)) {
                return probability.getProbability(Probability.RESULT.WIN);
            } else {
                return probability.getProbability(Probability.RESULT.LOSE);
            }
        }
        throw new MatchNotPlayed();
    }

    /**
     * @param t the team
     * @return true if this team wins
     */
    public final boolean teamWins(final Team t) {
        if (score != null) {
            try {
                if (isHomeTeam(t)) {
                    return score.homeTeamWins();
                } else {
                    return score.awayTeamWins();
                }
            } catch (TeamNotInMatch ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @param t the team
     * @return true if this team wins
     */
    public final boolean teamDraw(final Team t) {
        if (checkGamePlayed(t)) {
            return score.drawMatch();
        }
        return false;
    }

    /**
     * @param t a team or null
     * @return true if the game has been played by this team or if the game has
     * been played if t is null
     */
    public final boolean checkGamePlayed(final Team t) {
        if (homeTeam == null || awayTeam == null) {
            return false;
        }
        if (t == null) {
            return (score != null);
        }

        if (score != null) {
            if (awayTeam.equals(t) || homeTeam.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the score
     */
    public final Score getScore() {
        return score;
    }

    /**
     * @param t the team to determine whether it is home or not
     * @return true if this is home team, false if it is away team
     * @throws TeamNotInMatch if the team does not participate in this match
     */
    public final boolean isHomeTeam(final Team t) throws TeamNotInMatch {
        if (homeTeam != null && homeTeam.equals(t)) {
            return true;
        }
        if (awayTeam != null && awayTeam.equals(t)) {
            return false;
        }
        throw new TeamNotInMatch();
    }

    @Override
    public final String toString() {
        return homeTeam.getName() + " - " + awayTeam.getName();
    }

    /**
     * Utilitary function to copy all the members.
     *
     * @param m
     */
    private void copyMembers(Match m) {
        homeTeam = m.homeTeam;
        awayTeam = m.awayTeam;
        if (m.probability != null) {
            probability = new Probability(m.probability);
        } else {
            probability = null;
        }
        score = m.score;
    }

    /**
     * @param makeProno the prono to set.
     */
    public void setProba(Probability makeProno) {
        probability = makeProno;
    }

    /**
     * @param tDate the date of the match
     */
    public void setDate(Date tDate) {
        date = tDate;
    }
    
    /**
     * @return the date of the match
     */
    public Date getDate() {
        return date;
    }
}
