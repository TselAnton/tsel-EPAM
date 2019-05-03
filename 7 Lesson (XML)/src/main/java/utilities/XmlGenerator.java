package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Random;

public class XmlGenerator {

    private Logger logger = LoggerFactory.getLogger(XmlGenerator.class);

    private String[][] books = {
            {"Михаил", "Афанасьевич", "Булгаков", "512", "Мастер и Маргарита", "Вече"},
            {"Федор", "Михайлович", "Достоевский", "532", "Преступление и наказание", "Речь"},
            {"Лев", "Николаевич", "Толстой", "1120", "Война и мир", "СЗКЭО"},
            {"Николай", "Васильевич", "Гоголь", "4001", "Мертвые души", "Речь"},
            {"Александр", "Сергеевич", "Пушкин", "320", "Евгений Онегин", "Речь"}
    };

    public void generateXml() {
        logger.debug("Запущен метод generateXml()");
        int countOfBooks = new Random().nextInt(4) + 1;     // Кол-во книг в XML документе
        logger.debug("Создан int countOfBook со значением " + countOfBooks);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            logger.debug("Создан объект " + docFactory.toString());
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            logger.debug("Создан объект " + docBuilder.toString());
            Document document = docBuilder.newDocument();
            logger.debug("Создан объект " + document.toString());

            Element library = document.createElement("LIBRARY");
            logger.debug("Создан объект " + library.toString());
            document.appendChild(library);
            logger.debug("Объект " + library.toString() + " вложен в объект " + document.toString());

            for (int i = 0; i < countOfBooks; i++) {
                Element book = document.createElement("BOOK");
                logger.debug("Создан объект " + book.toString());
                library.appendChild(book);
                logger.debug("Объект " + book.toString() + " вложен в объект " + library.toString());

                Element author = document.createElement("AUTHOR");
                logger.debug("Создан объект " + author.toString());
                book.appendChild(author);
                logger.debug("Объект " + author.toString() + " вложен в объект " + book.toString());

                Element firstName = document.createElement("FIRSTNAME");
                logger.debug("Создан объект " + firstName.toString());
                author.appendChild(firstName);
                firstName.appendChild(document.createTextNode(books[i][0]));
                logger.debug("Объект " + firstName.toString() + " вложен в объект " + author.toString());

                Element lastName = document.createElement("LASTNAME");
                logger.debug("Создан объект " + lastName.toString());
                author.appendChild(lastName);
                lastName.appendChild(document.createTextNode(books[i][1]));
                logger.debug("Объект " + lastName.toString() + " вложен в объект " + author.toString());

                Element secondName = document.createElement("SECONDNAME");
                logger.debug("Создан объект " + secondName.toString());
                author.appendChild(secondName);
                secondName.appendChild(document.createTextNode(books[i][2]));
                logger.debug("Объект " + secondName.toString() + " вложен в объект " + author.toString());

                Element pages = document.createElement("PAGES");
                logger.debug("Создан объект " + pages.toString());
                book.appendChild(pages);
                pages.appendChild(document.createTextNode(books[i][3]));
                logger.debug("Объект " + pages.toString() + " вложен в объект " + book.toString());

                Element title = document.createElement("TITLE");
                logger.debug("Создан объект " + title.toString());
                book.appendChild(title);
                title.appendChild(document.createTextNode(books[i][4]));
                logger.debug("Объект " + title.toString() + " вложен в объект " + book.toString());

                Element publisher = document.createElement("PUBLISHER");
                logger.debug("Создан объект " + publisher.toString());
                book.appendChild(publisher);
                publisher.appendChild(document.createTextNode(books[i][5]));
                logger.debug("Объект " + publisher.toString() + " вложен в объект " + book.toString());
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            logger.debug("Создан объект " + transformer.toString());
            Result output = new StreamResult(new File("books.xml"));
            logger.debug("Создан объект " + output);
            Source input = new DOMSource(document);
            logger.debug("Создан объект " + input);

            transformer.transform(input, output);
        } catch (ParserConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (TransformerConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (TransformerException e) {
            LoggerUtil.logExceptions(e, logger);
        }

        logger.debug("Метод generateXml() завершён");
    }

}
