/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.exceptions;

/**
 *
 * @author jrc@SuppressWarnings("serial")

 */
@SuppressWarnings("serial")
public class MatchException extends Exception {

    /**
     * @param reason the reason why the exception has been raised
     */
    public MatchException(final String reason) {
        super(reason);
    }
}
