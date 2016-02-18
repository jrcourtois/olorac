/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.parser.IndexXml;
import net.jrcourtois.olorac.parser.ParserFactory;

/**
 *
 * @author jrc
 */
public class ChoiceWindow extends JFrame implements ActionListener{

    /**
     * Elements of the window.
     */
    private JPanel panel;
    private JComboBox btn;
    private List<ParserFactory> list;
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public ChoiceWindow(IndexXml index) {
        super();
        list = index.getListParser();
        build();
    }

    /**
     * Method to init the window.
     */
    private void build() {
        setTitle("Choose your championship");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    /**
     * Method that build the default pane.
     *
     * @return the default pane
     */
    private JPanel buildContentPane() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        btn = new JComboBox(list.toArray());
        btn.addActionListener(this);
        panel.add(btn);
        return panel;
    }

    // :TODO: managing the actions... 
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        final ParserFactory facto = (ParserFactory)cb.getSelectedItem();
        
        final Calendar cal = facto.getParser().getCalendar();
        
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ChampionshipWindow fenetre = new ChampionshipWindow(cal, facto.getName());
                fenetre.setVisible(true);
            }
        });

    }    
}
