/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import javax.swing.SwingUtilities;

/**
 *
 * @author jrc
 */
class IHMTest {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                IMatchTest fenetre = new IMatchTest();
                fenetre.setVisible(true);
            }
        });

    }
}
