package view.impl.extend;

import dto.ProductDto;
import entity.Product;
import exeptions.InvalidInputFormat;
import exeptions.NotPointFound;
import exeptions.RowTooLargeExeption;
import exeptions.XmlFileNotValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.XmlValidator;
import view.impl.ViewAbstract;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Админ-панель
 *
 * Level 4
 */
public class AdminPanelView extends ViewAbstract {

    private Scanner scanner = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(AdminPanelView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");


    public AdminPanelView(int level) {super(level);}

    @Override
    @SuppressWarnings("Duplicates")
    public int showMenu() {
        System.out.println("==== Административная панель ====");
        System.out.println("1. Установить скилку на товар");
        System.out.println("2. Загрузить товары из xml файла");
        System.out.println("3. Вернуться в основное меню");
        int pointCount = 3;

        System.out.print("Выберете пункт меню: ");
        int point = 0;


        try {
            try {
                point = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (point != 0 && (point > pointCount || point < 1)) {
                    throw new NotPointFound(point);
                }
            }
        } catch (NotPointFound e) {
            logger.error(MARKER, "NotPointFound", e);
            System.out.println("Не верно указан пункт меню!");
        }

        System.out.println();
        return point;
    }

    @SuppressWarnings("Duplicates")
    public ProductDto setDiscount(List<ProductDto> products) {

        System.out.println("==== Изменение скидки товара ====");

        for (int i = 0; i < products.size(); i++) {
            System.out.println(String.format("%4d. ", i+1) +
                    products.get(i).shortToString());
        }

        System.out.println();
        System.out.print("Выберете номер товара: ");
        int number = -1;

        try {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (number != -1 && (number > products.size() || number < 1)) {
                    number = 0;
                    throw new NotPointFound(number);
                }
            }
        } catch (NotPointFound e) {
            logger.error(MARKER, "NotPointFound", e);
            System.out.println("Такого товара не существует!");
        }

        ProductDto p = null;
        if (number != 0) {
            p = products.get(number - 1);

            System.out.print("Введите размер скидки в рублях: ");
            float discount = -1;
            try {
                try {
                    discount = scanner.nextFloat();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    throw new InvalidInputFormat(e);
                } finally {
                    if (discount != -1 && (discount * 100 > p.getPrice() || discount < 0)) {
                        discount = -1;
                        throw new InvalidInputFormat("Discount less than zero or more than the price of the goods!");
                    }
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "NotPointFound", e);
                System.out.println("Не верно указана скидка! Ошибка ввода, либо скидка меньше нуля или больше цены товара!");
            }

            if (discount != -1) {
                p.setDiscount((int)discount * 100);
            }
        }

        System.out.println();
        return p;
    }

    public List<Product> getProductsFromXml() {

        List<Product> productsList = null;
        scanner.nextLine();
        System.out.println("==== Добавление товаров через XML файл ====");
        System.out.print("Введите путь к файлу в двойных кавычках: ");
        String path = scanner.nextLine();

        path = path.replace("\"", "");
        boolean isValid = XmlValidator.validateXml(path);
        logger.debug("isValid = " + isValid);
        try {
            if (!isValid) {
                throw new XmlFileNotValid("File " + path + " not found or xml file is not valid!");
            } else {
                productsList = new ArrayList<>();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(path);

                document.getDocumentElement().normalize();
                NodeList products = document.getElementsByTagName("PRODUCT");

                for (int i = 0; i < products.getLength(); i++) {
                    Node node = products.item(i);
                    Product product = new Product();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element)node;

                        product.setName(element.getElementsByTagName("NAME").item(0).getTextContent());

                        if (product.getName().length() > 30)
                            throw new RowTooLargeExeption("Product name must not exceed 30 characters!");

                        product.setCategoryId(Integer.valueOf(element.getElementsByTagName("CATEGORY_ID").item(0).getTextContent()));
                        product.setBrand(element.getElementsByTagName("BRAND").item(0).getTextContent());

                        if (product.getBrand().length() > 30)
                            throw new RowTooLargeExeption("Brand name must not exceed 30 characters!");

                        product.setPrice((int)(Float.valueOf(element.getElementsByTagName("PRICE").item(0).getTextContent()
                                        .replace(",", ".")) * 100));
                        product.setQty(Integer.valueOf(element.getElementsByTagName("QTY").item(0).getTextContent()));
                        product.setDiscount((int)(Float.valueOf(element.getElementsByTagName("DISCOUNT").item(0)
                                .getTextContent().replace(",", ".")) * 100));
                        product.setDescription(element.getElementsByTagName("DESCRIPTION").item(0).getTextContent());
                    }

                    productsList.add(product);
                }
            }
        } catch (XmlFileNotValid e) {
            logger.error(MARKER, "XmlFileNotValid", e);
        } catch (ParserConfigurationException e) {
            logger.error(MARKER, "ParserConfigurationException", e);
        } catch (SAXException e) {
            logger.error(MARKER, "SAXException", e);
        } catch (IOException e) {
            logger.error(MARKER, "IOException", e);
        } catch (RowTooLargeExeption e) {
            productsList = null;
            logger.error(MARKER, "RowTooLargeExeption", e);
            System.out.println("Имя должно быть не более 30 символов!");
        } finally {
            if (productsList == null)
                System.out.println("Не удалось добавить новые товары!\nПроверьте правильность структуры XML файла," +
                        "а так же правильность указанного пути!");
        }

        return productsList;
    }
}
