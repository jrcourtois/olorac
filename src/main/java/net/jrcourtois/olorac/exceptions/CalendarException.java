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
public class CalendarException extends Exception {

    /**
     * Constructor.
     *
     * @param reason the reason why the exception araised
     */
    public CalendarException(final String reason) {
        super(reason);
    }
}
