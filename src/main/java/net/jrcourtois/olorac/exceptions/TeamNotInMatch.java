/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.exceptions;

import net.jrcourtois.olorac.exceptions.MatchException;

/**
 *
 * @author jrc
 */
@SuppressWarnings("serial")
public class TeamNotInMatch extends MatchException {

    /**
     * Constructor.
     */
    public TeamNotInMatch() {
        super("The team do not play in this match.");
    }
}
