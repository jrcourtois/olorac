package net.jrcourtois.olorac.ihm;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import net.jrcourtois.olorac.parser.IndexXml;

public class DecisionTree {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String filename = "src/test/resources/index.xml";
        if (args.length == 1) {
            File f = new File(args[0]);
            if (!f.exists()) {
                filename = args[0];
            }
        }
        final IndexXml index = new IndexXml(filename);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ChoiceWindow fenetre = new ChoiceWindow(index);
                fenetre.setVisible(true);
            }
        });

    }
}
