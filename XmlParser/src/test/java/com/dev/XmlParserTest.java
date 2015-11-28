package com.dev;

import static org.junit.Assert.*;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;

public class XmlParserTest {

    @Test
    public void test() throws Exception{
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser saxParser = factory.newSAXParser();
        String pathname = "src/test/resources/oas.xml";
        final File file = new File(pathname);
        final InputStream inputStream= new FileInputStream(file);
        final Reader reader = new InputStreamReader(inputStream,"UTF-8");

        final InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");

        final DefaultHandler saxHandler = new SaxHandler();
        saxParser.parse(is, saxHandler);
        final List<TimeCarrier> timeCarriers = ((SaxHandler) saxHandler).getTimeCarriers();
        assertNotNull(timeCarriers);
        assertFalse(timeCarriers.isEmpty());
        assertEquals(4,timeCarriers.size());
        assertEquals(484297,timeCarriers.get(0).getEco().intValue());
        assertEquals(460334,timeCarriers.get(1).getEco().intValue());
        assertEquals(533623,timeCarriers.get(2).getEco().intValue());
        assertEquals(485956,timeCarriers.get(3).getEco().intValue());
        assertEquals("2013.10.1",timeCarriers.get(0).getGeneralTime());
        assertEquals("2013.02.1",timeCarriers.get(1).getGeneralTime());
        assertEquals("2013.10.1",timeCarriers.get(2).getGeneralTime());
        assertEquals("2012.02.1",timeCarriers.get(3).getGeneralTime());
        assertEquals("2013.05.1",timeCarriers.get(0).getDeviatingTime());
        assertEquals("2012.05.1",timeCarriers.get(1).getDeviatingTime());
        assertNull(timeCarriers.get(2).getDeviatingTime());
        assertEquals("2011.02.1",timeCarriers.get(3).getDeviatingTime());
    }
}
