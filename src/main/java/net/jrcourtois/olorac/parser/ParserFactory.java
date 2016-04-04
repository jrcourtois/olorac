/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jrcourtois.olorac.parser;

import net.jrcourtois.olorac.championship.Top14;

/**
 *
 * @author jrc
 */
public class ParserFactory {

    /**
     * Enum parserType.
     */
    public static enum ParserType {
        XML, MaxiFoot, JSON, RugbyRama;
    }
    private final String name;
    private final String tags;
    private String url;
    private final ParserType type;

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
            case RugbyRama:
                return new RugbyRama(url);
            case JSON:
                return new JsonParser(url, new Top14());
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
