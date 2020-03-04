package xmlws.roombooking.xmltools;

import xmlws.roombooking.xmltools.samples.RoomBookingBasicSaxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class RoomBookingSaxParser implements RoomBookingParser {

    @Override
    public RoomBooking parse(InputStream inputStream) {
        RoomBooking rb = new RoomBooking();
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingBasicSaxParser.RoomBookingBasicHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rb;
    }

    private class RoomBookingBasicHandler extends DefaultHandler {

        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
                throws SAXException {
            System.out.println("In element: " + localName);
        }
    }
}
