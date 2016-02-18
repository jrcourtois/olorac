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
public class RankingException extends Exception {

    /**
     * @param reason the reason why the exception has been raised
     */
    public RankingException(final String reason) {
        super(reason);
    }
}
