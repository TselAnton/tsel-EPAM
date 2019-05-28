package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XmlValidator {

    private final static Logger logger = LoggerFactory.getLogger(XmlValidator.class.getName());
    private final static Marker MARKER = MarkerFactory.getMarker("Exception ");

    private final static String pathToXsd = "src/main/resources/store.xsd";

    public static boolean validateXml(String pathToXml) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new StreamSource(new File(pathToXsd)))
                    .newValidator()
                    .validate(new StreamSource(new File(pathToXml)));
        } catch (Exception e) {
            logger.error(MARKER, "", e);
            return false;
        }
        return true;
    }
}
