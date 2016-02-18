/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

/**
 *
 * @author jrc
 */
public class ParserFactory {

    /**
     * Enum parserType.
     */
    public enum ParserType {

        XML, MaxiFoot;
    }
    private String name;
    private String tags;
    private String url;
    private ParserType type;

    /**
     * Constructor.
     *
     * @param n the name of the parser
     * @param parser the type of the parser
     * @param t the tags of the parser
     */
    public ParserFactory(String n, String parser, String t) {
        this.name = n;
        this.tags = t;
        this.type = ParserType.valueOf(parser);
    }

    /**
     * @param s the url
     */
    public void setUrl(String s) {
        this.url = s;
    }

    /**
     * @return the parser corresponding to the config
     */
    public ChampionshipParser getParser() {
        switch (type) {
            case XML:
                return new FromXML(url);
            case MaxiFoot:
                return new MaxiFootCalendar(url);
            default:
                return null;
        }
    }

    /**
     * @return the name of the parser
     */
    public String getName() {
        return name;
    }

    /**
     * @return the tags associated with the parser
     */
    public String getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getName();
    }
}
