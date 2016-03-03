package net.jrcourtois.olorac.championship;

/**
 * Utility class to build the Championships.
 * @author jrc
 */
public final class ChampionshipFactory {
    /**
     * Utility class, does not need a default constructor.
     */
    private ChampionshipFactory() {
        
    }
    /**
     * @param championshipName the name of the championship
     * @return a championship
     */
    public static Championship getChampionship(final String championshipName) {
        if (championshipName.equalsIgnoreCase("Ligue1")) {
            return new Ligue1();
        }
        if (championshipName.equalsIgnoreCase("Euro")) {
            return new Euro();
        }
        if (championshipName.equalsIgnoreCase("Handball")) {
            return new Handball();
        }
        if (championshipName.equalsIgnoreCase("Top14")) {
            return new Top14();
        }
        return null;
    }
}
