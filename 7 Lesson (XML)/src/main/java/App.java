import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.XmlWorkerService;
import utilities.Validator;
import utilities.XmlGenerator;
import utilities.xmlParcers.DomXmlParser;
import utilities.xmlParcers.StaxXmlParser;

import java.util.Scanner;

public class App {

    private static final String PATH_TO_XML = "src/main/resources/plant_catalog.xml";
    private static final String PATH_TO_XSD = "src/main/resources/books.xsd";
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug("Приложение запущено");

        DomXmlParser domXmlParser = new DomXmlParser();
        logger.debug("Создан объект " + domXmlParser.toString());
        StaxXmlParser staxXmlParser = new StaxXmlParser();
        logger.debug("Создан объект " + staxXmlParser.toString());

        domXmlParser.parse(PATH_TO_XML);
        staxXmlParser.parse(PATH_TO_XML);

        XmlGenerator generator = new XmlGenerator();
        logger.debug("Создан объект " + generator.toString());
        //generator.generateXml();

        System.out.println("Проверка на валидность book.xml: " + Validator.validateXml("books.xml", PATH_TO_XSD));    // Валидная схема
        logger.info("Проверяем на валидность документ books.xml — " + Validator.validateXml("books.xml", PATH_TO_XSD));
        System.out.println("Проверка на валидность " + PATH_TO_XML + " " + Validator.validateXml(PATH_TO_XML, PATH_TO_XML));    //Невалидная схема
        logger.info("Проверяем на валидность документ " + PATH_TO_XML + " — " + Validator.validateXml(PATH_TO_XML, PATH_TO_XSD));

        XmlWorkerService worker = new XmlWorkerService(PATH_TO_XML);
        logger.debug("Создан объект " + worker.toString());

        // Работаем с первым документом
        while (true) {
            logger.debug("Цикл while");
            System.out.println("\n1.  Вывести все растения\n" +
                    "2.  Добавить растение\n" +
                    "3.  Удалить растение\n" +
                    "4.  Сохранить xml документ и выйти");
            System.out.print("Выберете пункт меню: ");
            int menuPoint = new Scanner(System.in).nextInt();
            logger.debug("Новый примитив int menuPoint со значением " + menuPoint);
            if (menuPoint == 1) {
                worker.showPlants();
            } else if (menuPoint == 2) {
                worker.addPlant();
            } else if (menuPoint == 3) {
                worker.deletePlant();
            } else if (menuPoint == 4) {
                System.out.print("Введите название сохраняемого xml документа: ");
                String fileName = new Scanner(System.in).nextLine();
                worker.saveXml(fileName);
                break;
            } else {
                System.out.println("Не верно выбран пункт меню!");
            }
        }
        logger.debug("Приложение завершено \n");
    }
}
