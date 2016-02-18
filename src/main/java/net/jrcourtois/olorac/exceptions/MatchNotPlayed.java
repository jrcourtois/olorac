/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.exceptions;

/**
 *
 * @author jrc
 */
@SuppressWarnings("serial")
public class MatchNotPlayed extends MatchException {
    /**
     * Constructor.
     */
    public MatchNotPlayed() {
        super("The match has not been played yet");
    }
}
