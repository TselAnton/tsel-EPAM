package view.impl.extend;

import dto.ProductDto;
import exceptions.InvalidInputFormat;
import exceptions.RowTooLargeExeption;
import exceptions.XmlFileNotValid;
import generator.collection.ArraysGenerator;
import generator.entity.ProductGenerator;
import generator.utils.ScannerGenerator;
import model.impl.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.ViewUtils;
import utils.XmlValidator;
import view.impl.ViewAbstract;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Админ-панель
 */
public class AdminPanelView extends ViewAbstract {

    @Autowired
    private ViewUtils viewUtils;

    @Autowired
    private XmlFileNotValid xmlFileNotValid;

    @Autowired
    private RowTooLargeExeption rowTooLargeExeption;

    @Autowired
    private InvalidInputFormat invalidInputFormat;

    @Autowired
    @Qualifier("productArrayList")
    private ArraysGenerator arraysGenerator;

    @Autowired
    private ProductGenerator productGenerator;

    private Scanner scanner;
    private final Logger LOGGER = LoggerFactory.getLogger(AdminPanelView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");


    public AdminPanelView(int level, ScannerGenerator scannerGenerator) {
        super(level);
        scanner = scannerGenerator.generateScanner();
    }

    @Override
    public int showMenu() {
        System.out.println("==== Административная панель ====");
        System.out.println("1. Установить скилку на товар");
        System.out.println("2. Загрузить товары из xml файла");
        System.out.println("3. Вернуться в основное меню");
        int pointCount = 3;

        return viewUtils.getMenuPoint(scanner, pointCount, LOGGER, MARKER);
    }

    public ProductDto setDiscount(List<ProductDto> products) {

        showShortProductsList(products);

        System.out.print("Выберете номер товара: ");
        int number = viewUtils.inputNumber(scanner, products.size(), LOGGER, MARKER);

        ProductDto productDto = null;
        if (number != 0) {
            productDto = products.get(number - 1);

            float discount = inputDiscountValue(productDto);

            if (discount != -1) {
                productDto.setDiscount((int)(discount * 100));
            }
        }

        System.out.println();
        return productDto;
    }

    public List<Product> getProductsFromXml() {

        List<Product> productsList = null;
        scanner.nextLine();
        System.out.println("==== Добавление товаров через XML файл ====");
        System.out.print("Введите путь к файлу в двойных кавычках: ");
        String path = scanner.nextLine();

        path = path.replace("\"", "");
        boolean isValid = XmlValidator.validateXml(path);
        LOGGER.debug("isValid = " + isValid);
        try {
            if (!isValid) {
                throw xmlFileNotValid;
            } else {
                productsList = arraysGenerator.generateArrayList();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(path);

                document.getDocumentElement().normalize();
                NodeList products = document.getElementsByTagName("PRODUCT");

                for (int i = 0; i < products.getLength(); i++) {
                    Node node = products.item(i);
                    Product product = productGenerator.generateNewProduct();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element)node;

                        product.setName(element.getElementsByTagName("NAME").item(0).getTextContent());

                        if (product.getName().length() > 30)
                            throw rowTooLargeExeption;

                        product.setCategoryId(Integer.valueOf(element.getElementsByTagName("CATEGORY_ID").item(0).getTextContent()));
                        product.setBrand(element.getElementsByTagName("BRAND").item(0).getTextContent());

                        if (product.getBrand().length() > 30)
                            throw rowTooLargeExeption;

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
            LOGGER.error(MARKER, e.getMessage(), e);
        } catch (ParserConfigurationException e){
            LOGGER.error(MARKER, e.getMessage(), e);
        } catch (SAXException e) {
            LOGGER.error(MARKER, e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(MARKER, e.getMessage(), e);
        } catch (RowTooLargeExeption e) {
            productsList = null;
            LOGGER.error(MARKER, e.getMessage(), e);
            System.out.println("Имя должно быть не более 30 символов!");
        } finally {
            if (productsList == null)
                System.out.println("Не удалось добавить новые товары!\nПроверьте правильность структуры XML файла," +
                        "а так же правильность указанного пути!");
        }

        return productsList;
    }

    private void showShortProductsList(List<ProductDto> products) {
        System.out.println("==== Изменение скидки товара ====");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(String.format("%4d. ", i+1) +
                    products.get(i).toShortString());
        }
        System.out.println();
    }

    private float inputDiscountValue(ProductDto product) {
        System.out.print("Введите размер скидки в рублях: ");
        float discount = -1;
        try {
            try {
                discount = scanner.nextFloat();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw invalidInputFormat;
            } finally {
                if (discount != -1 && (discount * 100 > product.getPrice() || discount < 0)) {
                    discount = -1;
                    throw invalidInputFormat;
                }
            }
        } catch (InvalidInputFormat e) {
            LOGGER.error(MARKER, "Discount less than zero or more than the price of the goods", e);
            System.out.println("Не верно указана скидка! Ошибка ввода, либо скидка меньше нуля или больше цены товара!");
        }
        return discount;
    }
}
