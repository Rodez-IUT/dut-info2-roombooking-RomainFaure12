package xmlws.roombooking.xmltools;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomBookingDomParser implements RoomBookingParser {

    @Override
    public RoomBooking parse(InputStream inputStream) {
        RoomBooking rb = new RoomBooking();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            rb.setRoomLabel(doc.getElementsByTagName("label").item(0).getTextContent());
            rb.setUsername(doc.getElementsByTagName("username").item(0).getTextContent());
            rb.setStartDate(sdf.parse(doc.getElementsByTagName("startDate").item(0).getTextContent()));
            rb.setEndDate(sdf.parse(doc.getElementsByTagName("endDate").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rb;
    }
}
