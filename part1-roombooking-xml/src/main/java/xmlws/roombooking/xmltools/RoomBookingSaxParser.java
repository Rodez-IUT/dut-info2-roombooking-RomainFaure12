package xmlws.roombooking.xmltools;

import xmlws.roombooking.xmltools.samples.RoomBookingBasicSaxParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RoomBookingSaxParser implements RoomBookingParser {

    RoomBooking rb = new RoomBooking();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @Override
    public RoomBooking parse(InputStream inputStream) {
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

        boolean label;
        boolean username;
        boolean startDate;
        boolean endDate;

        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
                throws SAXException {

            /*lorsqu'un rentre dans un balise*/
            if (localName.equalsIgnoreCase("label")) {
                label = true;
            }

            if (localName.equalsIgnoreCase("username")) {
                username = true;
            }else{
                username = false;
            }

            if (localName.equalsIgnoreCase("startDate")) {
                startDate = true;
            }else{
                startDate =false;
            }

            if (localName.equalsIgnoreCase("endDate")) {
                endDate = true;
            }else{
                endDate = false;
            }
        }

        public void characters(char ch[], int start, int length)
                throws SAXException {

            /*si la balise actuelle est celle rechercher*/
            if(label){
                rb.setRoomLabel(new String(ch, start, length));
            }

            if(username){
                rb.setUsername(new String(ch, start, length));
            }

            if(startDate){
                try {
                    rb.setEndDate(sdf.parse(new String(ch, start, length)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(endDate){
                try {
                    rb.setStartDate(sdf.parse( new String(ch, start, length)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
