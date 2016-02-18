package net.jrcourtois.olorac.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IndexXml extends DefaultHandler {

    /**
     * the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IndexXml.class.getName());
    /**
     * the variables for parsing.
     */
    private String currentElement;
    private ParserFactory currentParser;
    /**
     * the list of parsers.
     */
    private List<ParserFactory> listParser;
    private String documentRoot;

    /**
     * Constructor.
     *
     * @param filename
     */
    public IndexXml(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            
            listParser = new ArrayList<ParserFactory>();
            this.documentRoot = f.getParent();
            this.parseFile(filename);

        }
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes)
            throws SAXException {
        currentElement = qName;
        if (qName.equalsIgnoreCase("championship")) {
            String name = attributes.getValue("name");
            String parser = attributes.getValue("parser");
            String tags = attributes.getValue("tags");
            currentParser = new ParserFactory(name, parser, tags);
        }
    }

    @Override
    public void endElement(
            String uri, String localName,
            String qName) throws SAXException {
        currentElement = "";
        if (qName.equalsIgnoreCase("championship")) {
            listParser.add(currentParser);
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        if (currentElement.equalsIgnoreCase("localfile")) {
            currentParser.setUrl(documentRoot + "/" + new String(ch, start, length));
        }
        if (currentElement.equalsIgnoreCase("url")) {
            currentParser.setUrl(new String(ch, start, length));
        }
    }

    /**
     * Method to parse the file
     *
     * @param filename
     */
    private boolean parseFile(String filename) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(filename, this);
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            listParser = null;
            return false;
        }

    }

    /**
     * @return the list of parser
     */
    public List<ParserFactory> getListParser() {
        return listParser;
    }
}