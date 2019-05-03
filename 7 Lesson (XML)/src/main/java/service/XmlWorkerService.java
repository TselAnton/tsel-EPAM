package service;

import entity.Plant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import utilities.LoggerUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class XmlWorkerService {

    private Logger logger = LoggerFactory.getLogger(XmlWorkerService.class);
    private List<Plant> plants;

    public XmlWorkerService(String pathToXml) {
        ArrayList<Plant> plantsList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(pathToXml));

            NodeList plantsNode = document.getDocumentElement().getElementsByTagName("PLANT");

            for (int i = 0; i < plantsNode.getLength(); i++) {
                Node plantNode = plantsNode.item(i);
                String atributes = plantNode.getTextContent().trim()
                        .replaceAll("   ", "").replaceAll("\n", ",")
                        .replaceAll("\t\t", "");
                String[] values = atributes.split(",");
                plantsList.add(new Plant(values[0], values[1], values[2], values[3], values[4], values[5]));
            }
        } catch (ParserConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (SAXException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (IOException e) {
            LoggerUtil.logExceptions(e, logger);
        }
        plants = plantsList;
        logger.debug("Создан List plants размером " + plants.size());
    }

    public void addPlant() {
        logger.debug("Вход в метод addPlant()");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nCommon: ");
        String common = scanner.nextLine();
        System.out.print("Botanical: ");
        String botanical = scanner.nextLine();
        System.out.print("Zone: ");
        String zone = scanner.nextLine();
        System.out.print("Light: ");
        String light = scanner.nextLine();
        System.out.print("Price: ");
        String price = scanner.nextLine();
        System.out.print("Availability: ");
        String avail = scanner.nextLine();

        Plant newPlant = new Plant(common, botanical, zone, light, price, avail);
        plants.add(newPlant);
        System.out.println("Цветок успешно добавлен!");
        logger.debug("Метод addPlant() завершён");
    }

    public void deletePlant() {
        logger.debug("Вход в метод deletePlant()");
        System.out.println("\nСписок цветов:");
        for (int i = 0; i < plants.size(); i++) {
            System.out.println((i + 1) + ". " + plants.get(i).getCommon());
        }
        System.out.print("Выберете номер удаляемого цветка: ");
        List<Plant> tempPlants = plants;

        int num = -1;
        try {
            num = new Scanner(System.in).nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Не верный номер! Удаление не выполнено!");
            LoggerUtil.logExceptions(e, logger);
            return;
        }

        if (num >= 0 && num < plants.size()) {
                plants.remove(num);
                System.out.println("Цветок успешно удалён!");
                logger.debug("Метод deletePlant() успешно завершён");
        } else {
            System.out.println("Не верный номер! Удаление не выполнено!");
            logger.debug("Метод deletePlant() завершён с ошибкой: выход за пределы массива plants(size = " +
                    plants.size() + ") < " + num);
        }
    }

    public void showPlants() {
        logger.debug("Вход в метод showPlants()");
        System.out.println("\nСписок цветов:");
        for (int i = 0; i < plants.size(); i++) {
            System.out.println((i + 1) + ". Common: " + plants.get(i).getCommon() +
                    "\n    Botanical: " + plants.get(i).getBotanical() +
                    "\n    Zone: " + plants.get(i).getZone() +
                    "\n    Light: " + plants.get(i).getLight() +
                    "\n    Price: " + plants.get(i).getPrice() +
                    "\n    Availability: " + plants.get(i).getAvailability() + "\n");
        }
        logger.debug("Завершение метода showPlants()");
    }

    public void saveXml(String fileName) {
        logger.debug("Вход в метод saveXml()");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element catalog = doc.createElement("CATALOG");
            doc.appendChild(catalog);
            for (int i = 0; i < plants.size(); i++) {
                Element plant = doc.createElement("PLANT");
                catalog.appendChild(plant);

                Element common = doc.createElement("COMMON");
                plant.appendChild(common);
                common.appendChild(doc.createTextNode(plants.get(i).getCommon()));

                Element botanical = doc.createElement("BOTANICAL");
                plant.appendChild(botanical);
                botanical.appendChild(doc.createTextNode(plants.get(i).getBotanical()));

                Element zone = doc.createElement("ZONE");
                plant.appendChild(zone);
                zone.appendChild(doc.createTextNode(plants.get(i).getZone()));

                Element light = doc.createElement("LIGHT");
                plant.appendChild(light);
                light.appendChild(doc.createTextNode(plants.get(i).getLight()));

                Element price = doc.createElement("PRICE");
                plant.appendChild(price);
                price.appendChild(doc.createTextNode(plants.get(i).getPrice()));

                Element aval = doc.createElement("AVAILABILITY");
                plant.appendChild(aval);
                price.appendChild(doc.createTextNode(plants.get(i).getAvailability()));
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(fileName));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);

        } catch (ParserConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (TransformerConfigurationException e) {
            LoggerUtil.logExceptions(e, logger);
        } catch (TransformerException e) {
            LoggerUtil.logExceptions(e, logger);
        }
        logger.debug("Метод saveXml() завершён");
    }
}
