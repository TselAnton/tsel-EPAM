package utilities.xmlParcers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import utilities.LoggerUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 *  Парсер XML через DOM
 */
public class DomXmlParser {

    private Logger logger = LoggerFactory.getLogger(DomXmlParser.class);

    public void parse(String fileName) {
        logger.debug("Запущен метод parse()");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            logger.debug("Создан объект " + factory.toString());

            DocumentBuilder builder = factory.newDocumentBuilder();
            logger.debug("Создан объект " + builder.toString());

            Document document = builder.parse(new File(fileName));
            logger.debug("Создан объект " + document.toString());

            logger.info("Парсирование xml документа при помощи DOM");
            document.getDocumentElement().normalize();
            logger.info(document.getDocumentElement().getNodeName());
            NodeList plants = document.getElementsByTagName("PLANT");
            logger.debug("Создан объект " + plants.toString());

            for (int i = 0; i < plants.getLength(); i++) {
                Node plant = plants.item(i);
                logger.debug("Создан объект " + plant.toString());
                logger.info("   " + plant.getNodeName());

                if (plant.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) plant;
                    logger.debug("Создан объект " + element.toString());
                    logger.info("        COMMON: " +
                            element.getElementsByTagName("COMMON")
                                    .item(0)
                                    .getTextContent());
                    logger.info("        BOTANICAL: " +
                            element.getElementsByTagName("BOTANICAL")
                                    .item(0)
                                    .getTextContent());
                    logger.info("        ZONE: " +
                            element.getElementsByTagName("ZONE")
                                    .item(0)
                                    .getTextContent());
                    logger.info("        LIGHT: " +
                            element.getElementsByTagName("LIGHT")
                                    .item(0)
                                    .getTextContent());
                    logger.info("        PRICE: " +
                            element.getElementsByTagName("PRICE")
                                    .item(0)
                                    .getTextContent());
                    logger.info("        AVAILABILITY: " +
                            element.getElementsByTagName("AVAILABILITY")
                                    .item(0)
                                    .getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (IOException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (SAXException e) {
            LoggerUtil.logExceptions(e, logger);
        }
        logger.debug("Метод parse() завершён");
    }
}
