package utilities.xmlParcers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.LoggerUtil;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StaxXmlParser {

    private Logger logger = LoggerFactory.getLogger(StaxXmlParser.class);

    public void parse(String fileName) {
        try {
            XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
            logger.debug("Создан объект " + xmlFactory.toString());
            try (FileReader fileReader = new FileReader(fileName)) {
                logger.debug("Открыт поток " + fileReader.toString());
                XMLEventReader reader = xmlFactory.createXMLEventReader(fileReader);
                logger.debug("Создан объект " + reader.toString());
                int level = 0;
                logger.debug("Создан примитив int level со значением " + level);
                boolean endTagFlag = false;
                logger.debug("Создан примитив boolean endTagFlag со значением " + endTagFlag);

                logger.info("   ");
                logger.info("Парсирование xml документа при помощи StAX");
                while (reader.hasNext()) {
                    XMLEvent event = reader.nextEvent();
                    logger.debug("Создан объект " + event.toString());

                    if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                        logger.info(getSomeSpace(level) + "<" + event.asStartElement().getName().getLocalPart() + ">");
                        level += 1;
                        logger.debug("Значение примитива int level изменён на " + level);
                        endTagFlag = false;
                        logger.debug("Значение примитива boolean endTagFlag изменён на " + endTagFlag);
                    } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                        String value = event.asCharacters().getData().trim().replaceAll(" ", "")
                                .replaceAll("\n", "").replaceAll("  ", "");
                        if (!value.equals("")) {
                            logger.info(getSomeSpace(level) + event.asCharacters().getData());
                            level -= 1;
                            logger.debug("Значение примитива int level изменён на " + level);
                        }
                    } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                        if (!endTagFlag) {
                            endTagFlag = true;
                            logger.debug("Значение примитива boolean endTagFlag изменён на " + endTagFlag);
                        } else {
                            level -= 1;
                            logger.debug("Значение примитива int level изменён на " + level);
                        }
                        logger.info(getSomeSpace(level) + "</" + event.asEndElement().getName().getLocalPart() + ">");
                    }
                }

            } catch (FileNotFoundException e) {
                LoggerUtil.logExceptions(e, logger);
            }
        } catch (IOException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (XMLStreamException e) {
            LoggerUtil.logExceptions(e, logger);
        }
        logger.debug("Метод parse() завершён");
    }

    private String getSomeSpace(int n) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < n; i++) {
            spaces.append("     ");
        }
        return spaces.toString();
    }
}
