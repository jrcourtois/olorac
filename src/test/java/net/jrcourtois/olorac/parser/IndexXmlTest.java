package net.jrcourtois.olorac.parser;

import java.util.List;
import junit.framework.TestCase;

/**
 * Test file for the IndexXml class.
 *
 * @author jrc
 */
public class IndexXmlTest extends TestCase {

    public IndexXmlTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of getListParser method, of class IndexXml.
     */
    public void testGetListParser() {
        System.out.println("getListParser");
        IndexXml instance = new IndexXml("src/test/resources/index.xml");
        List result = instance.getListParser();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
