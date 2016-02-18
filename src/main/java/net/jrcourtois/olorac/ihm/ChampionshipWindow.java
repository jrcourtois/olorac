package net.jrcourtois.olorac.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.jrcourtois.olorac.calendar.Calendar;
import net.jrcourtois.olorac.compute.Computer;
import net.jrcourtois.olorac.compute.PossibleRankingsAggregator;
import net.jrcourtois.olorac.exceptions.MatchException;

public class ChampionshipWindow extends JFrame implements ActionListener {

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ChampionshipWindow.class.getName());
    /**
     * Elements of the window.
     */
    private JPanel panel;
    private JButton btn;
    private IPossibleRankAggregator aggregator;
    private PossibleRankingsAggregator classements = null;
    /**
     *
     */
    private final Calendar calendar;
    private final Computer app;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param c
     * @param name
     */
    public ChampionshipWindow(Calendar c, String name) {
        super();
        calendar = c;
        app = new Computer();
        classements = new PossibleRankingsAggregator(calendar.getTeams());
        setTitle("Championship: " + name);
        build();
    }

    /**
     * Method to init the window.
     */
    private void build() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    /**
     * Method that build the default pane.
     *
     * @return
     */
    private JPanel buildContentPane() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        btn = new JButton("Cliquez");
        btn.addActionListener(this);

        if (calendar != null) {

            panel.add(new ICalendar(calendar));
        }
        panel.add(btn);
        aggregator = new IPossibleRankAggregator(classements);
        panel.add(aggregator);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            app.computeNumberOfAlternatives(calendar, classements, 10000);
        } catch (MatchException ex) {
            LOGGER.log(Level.SEVERE, "Exception raised: {0}", ex.getMessage());
        }
    }
}
